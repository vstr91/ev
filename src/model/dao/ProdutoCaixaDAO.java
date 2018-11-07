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

        String query = "SELECT p.ID, pe.EVENTO, pc.CAIXA, pc.QUANTIDADE,"
                + "(" +
"    SELECT SUM(quantidade) " +
"    FROM PRODUTO_CAIXA pc2 INNER JOIN " +
"         PRODUTO_EVENTO pe2 ON pe2.PRODUTO = pc2.PRODUTO_EVENTO " +
"    WHERE pc2.PRODUTO_EVENTO = pc.PRODUTO_EVENTO " +
"    AND pe2.EVENTO = pe.EVENTO " +
") " +
"FROM produto p LEFT JOIN produto_evento pe ON pe.PRODUTO = p.ID LEFT JOIN " +
"PRODUTO_CAIXA pc ON (pc.PRODUTO_EVENTO = pe.ID AND (pc.CAIXA = ? OR pc.CAIXA IS NULL)) " +
"WHERE pe.evento = ? AND (pc.CAIXA = ? OR pc.CAIXA IS NULL) AND p.tipo = 0";

        PreparedStatement ps = null;
        List<ProdutoCaixa> produtos = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ProdutoEventoDAO produtoEventoDAO = new ProdutoEventoDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, ev.getId());
            ps.setInt(2, ev.getEvento().getId());
            ps.setInt(3, ev.getId());
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
                
                produtoEvento.setVendas(rs.getBigDecimal(5));
                
                produtoCaixa.setProduto(produtoEvento);
                
                produtoCaixa.setCaixa(ev);
                produtoCaixa.setQuantidade(rs.getInt(4));
                
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

    public void salvar(ProdutoCaixa produtoCaixa) throws SQLException {

        String query = "INSERT INTO produto_caixa (produto_evento, caixa, quantidade) "
                + "VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, produtoCaixa.getProduto().getId());
            ps.setInt(2,produtoCaixa.getCaixa().getId());
            ps.setInt(3, produtoCaixa.getQuantidade());
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

        String queryCheca = "SELECT * FROM produto_caixa WHERE produto_evento = ? "
                + "and caixa = ?";
        PreparedStatement psCheca = null;
        
        String query = "UPDATE produto_caixa SET quantidade = ? WHERE produto_evento = ? "
                + "and caixa = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            psCheca = con.prepareStatement(queryCheca);
            psCheca.setInt(1, produtoCaixa.getProduto().getId());
            psCheca.setInt(2, produtoCaixa.getCaixa().getId());
            
            ResultSet rs = psCheca.executeQuery();
            
            if(rs.next()){
                ps = con.prepareStatement(query);
                ps.setInt(1, produtoCaixa.getQuantidade());
                ps.setInt(2,produtoCaixa.getProduto().getId());
                ps.setInt(3, produtoCaixa.getCaixa().getId());
                ps.execute();
                ps.close();
            } else{
                salvar(produtoCaixa);
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
