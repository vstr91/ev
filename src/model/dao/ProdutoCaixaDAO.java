/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CaixaEvento;
import model.Produto;
import model.ProdutoCaixa;
import model.ProdutoEvento;

/**
 *
 */
public class ProdutoCaixaDAO {

    public ProdutoCaixaDAO() {

    }
    
    public List<ProdutoCaixa> listarTodosPorCaixaEvento(CaixaEvento ev) throws SQLException {

        String query = "SELECT p.ID, pe.EVENTO, pc.CAIXA, pc.QUANTIDADE " +
"FROM produto p LEFT JOIN produto_evento pe ON pe.PRODUTO = p.ID LEFT JOIN " +
"PRODUTO_CAIXA pc ON (pc.PRODUTO = pe.PRODUTO AND pc.EVENTO = pe.EVENTO) " +
"WHERE pe.evento = ? AND (pc.CAIXA = ? OR pc.CAIXA IS NULL)";
        PreparedStatement ps = null;
        List<ProdutoCaixa> produtos = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        EventoDAO eventoDAO = new EventoDAO();
        ProdutoEventoDAO produtoEventoDAO = new ProdutoEventoDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, ev.getEvento().getId());
            ps.setInt(2, ev.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoCaixa produtoCaixa = new ProdutoCaixa();
                
                Produto produto = new Produto();
                produto.setId(rs.getInt(1));
                produto = produtoDAO.carregar(produto);
                
                ProdutoEvento produtoEvento = new ProdutoEvento();
                produtoEvento.setEvento(ev.getEvento());
                produtoEvento.setProduto(produto);
                
                produtoEvento = produtoEventoDAO.carregar(produtoEvento);
                
                produtoCaixa.setProduto(produtoEvento);
                
                produtoCaixa.setCaixa(ev);
                produtoCaixa.setQuantidade(rs.getInt(3));
                
                produtos.add(produtoCaixa);
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return produtos;
        
    }

    public void salvar(ProdutoEvento produtoEvento) throws SQLException {

        String query = "INSERT INTO produto_evento (valor_custo, valor_venda, evento, produto) "
                + "VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setBigDecimal(1, produtoEvento.getValorCusto());
            ps.setBigDecimal(2,produtoEvento.getValorVenda());
            ps.setInt(3, produtoEvento.getEvento().getId());
            ps.setInt(4, produtoEvento.getProduto().getId());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public void editar(ProdutoCaixa produtoCaixa) throws SQLException {

        String queryCheca = "SELECT * FROM produto_caixa WHERE evento = ? "
                + "and produto = ?";
        PreparedStatement psCheca = null;
        
        String query = "UPDATE produto_evento SET valor_custo = ?, valor_venda = ? WHERE evento = ? "
                + "and produto = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            psCheca = con.prepareStatement(queryCheca);
            psCheca.setInt(1, produtoCaixa.getEvento().getId());
            psCheca.setInt(2, produtoEvento.getProduto().getId());
            
            ResultSet rs = psCheca.executeQuery();
            
            if(rs.next()){
                ps = con.prepareStatement(query);
                ps.setBigDecimal(1, produtoEvento.getValorCusto());
                ps.setBigDecimal(2,produtoEvento.getValorVenda());
                ps.setInt(3, produtoEvento.getEvento().getId());
                ps.setInt(4, produtoEvento.getProduto().getId());
                ps.execute();
                ps.close();
            } else{
                salvar(produtoEvento);
            }
            
            psCheca.close();
            
            
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
            if(psCheca != null){
                psCheca.close();
            }
            
        }
        
    }

}
