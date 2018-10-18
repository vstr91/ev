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
import model.Evento;
import model.Produto;
import model.ProdutoEvento;

/**
 *
 */
public class ProdutoEventoDAO {

    public ProdutoEventoDAO() {

    }
    
    public List<ProdutoEvento> listarTodos() throws SQLException {

        String query = "SELECT * FROM produto_evento";
        PreparedStatement ps = null;
        List<ProdutoEvento> produtos = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        EventoDAO eventoDAO = new EventoDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoEvento produtoEvento = new ProdutoEvento();
                
                produtoEvento.setId(rs.getInt(1));
                
                Produto produto = new Produto();
                produto.setId(rs.getInt(2));
                produto = produtoDAO.carregar(produto);
                
                produtoEvento.setProduto(produto);
                
                Evento evento = new Evento();
                evento.setId(rs.getInt(3));
                evento = eventoDAO.carregar(evento);
                
                produtoEvento.setEvento(evento);
                produtoEvento.setValorCusto(rs.getBigDecimal(4));
                produtoEvento.setValorVenda(rs.getBigDecimal(5));
                
                produtos.add(produtoEvento);
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
    
    public List<ProdutoEvento> listarTodosPorEvento(Evento ev) throws SQLException {

        String query = "SELECT p.id, -1 AS evento, valor_custo, valor_venda, pe.id " +
"FROM produto p LEFT JOIN produto_evento pe ON (p.id = pe.PRODUTO OR pe.PRODUTO IS NULL) " +
"AND evento = ?";
        PreparedStatement ps = null;
        List<ProdutoEvento> produtos = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        EventoDAO eventoDAO = new EventoDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, ev.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoEvento produtoEvento = new ProdutoEvento();
                
                Produto produto = new Produto();
                produto.setId(rs.getInt(1));
                produto = produtoDAO.carregar(produto);
                
                produtoEvento.setProduto(produto);
                
                produtoEvento.setEvento(ev);
                produtoEvento.setValorCusto(rs.getBigDecimal(3));
                produtoEvento.setValorVenda(rs.getBigDecimal(4));
                produtoEvento.setId(rs.getInt(5));
                
                produtos.add(produtoEvento);
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
    
    public void editar(ProdutoEvento produtoEvento) throws SQLException {

        String queryCheca = "SELECT * FROM produto_evento WHERE evento = ? "
                + "and produto = ?";
        PreparedStatement psCheca = null;
        
        String query = "UPDATE produto_evento SET valor_custo = ?, valor_venda = ? WHERE evento = ? "
                + "and produto = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            psCheca = con.prepareStatement(queryCheca);
            psCheca.setInt(1, produtoEvento.getEvento().getId());
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
    
    public ProdutoEvento carregar(ProdutoEvento umProduto) throws SQLException {

        String query = "SELECT produto, evento, valor_custo, valor_venda, id FROM produto_evento WHERE produto = ? AND evento = ?";
        PreparedStatement ps = null;
        ProdutoEvento produto = null;
        ProdutoDAO produtoDAO = new ProdutoDAO();
        EventoDAO eventoDAO = new EventoDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, umProduto.getProduto().getId());
            ps.setInt(2, umProduto.getEvento().getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                produto = new ProdutoEvento();
                
                Produto p = new Produto();
                p.setId(rs.getInt(1));
                p = produtoDAO.carregar(p);
                
                produto.setProduto(p);
                
                Evento e = new Evento();
                e.setId(rs.getInt(2));
                e = eventoDAO.carregar(e);
                produto.setEvento(e);
                
                produto.setValorCusto(rs.getBigDecimal(3));
                produto.setValorVenda(rs.getBigDecimal(4));
                produto.setId(rs.getInt(5));
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return produto;
        
    }

}
