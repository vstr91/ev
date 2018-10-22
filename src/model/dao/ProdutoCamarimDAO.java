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
import model.Evento;
import model.Produto;
import model.ProdutoCamarim;
import model.ProdutoEvento;

/**
 *
 */
public class ProdutoCamarimDAO {

    public ProdutoCamarimDAO() {

    }
    
    public List<ProdutoCamarim> listarTodosPorEvento(Evento ev) throws SQLException {

        String query = "SELECT p.ID, pe.EVENTO, pc.AVARIA, pc.PRODUCAO, pc.SOCIOS, pc.CACAU " +
"FROM produto p LEFT JOIN produto_evento pe ON pe.PRODUTO = p.ID LEFT JOIN " +
"PRODUTO_CAMARIM pc ON pc.PRODUTO_EVENTO = pe.ID " +
"WHERE pe.evento = ?";
        PreparedStatement ps = null;
        List<ProdutoCamarim> produtos = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ProdutoEventoDAO produtoEventoDAO = new ProdutoEventoDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, ev.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoCamarim produtoCamarim = new ProdutoCamarim();
                
                Produto produto = new Produto();
                produto.setId(rs.getInt(1));
                produto = produtoDAO.carregar(produto);
                
                ProdutoEvento produtoEvento = new ProdutoEvento();
                produtoEvento.setEvento(ev);
                produtoEvento.setProduto(produto);
                
                produtoEvento = produtoEventoDAO.carregar(produtoEvento);
                
                produtoCamarim.setProduto(produtoEvento);
                
                produtoCamarim.setAvaria(rs.getInt(3));
                produtoCamarim.setProducao(rs.getInt(4));
                produtoCamarim.setSocios(rs.getInt(5));
                produtoCamarim.setCacau(rs.getInt(6));
                
                produtos.add(produtoCamarim);
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

    public void salvar(ProdutoCamarim produtoCamarim) throws SQLException {

        String query = "INSERT INTO produto_camarim (produto_evento, avaria, producao, socios, cacau) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, produtoCamarim.getProduto().getId());
            ps.setInt(2,produtoCamarim.getAvaria());
            ps.setInt(3, produtoCamarim.getProducao());
            ps.setInt(4, produtoCamarim.getSocios());
            ps.setInt(5, produtoCamarim.getCacau());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public void editar(ProdutoCamarim produtoCamarim) throws SQLException {

        String queryCheca = "SELECT * FROM produto_camarim WHERE produto_evento = ?";
        PreparedStatement psCheca = null;
        
        String query = "UPDATE produto_camarim SET avaria = ?, producao = ?, socios = ?, cacau = ? WHERE produto_evento = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            psCheca = con.prepareStatement(queryCheca);
            psCheca.setInt(1, produtoCamarim.getProduto().getId());
            
            ResultSet rs = psCheca.executeQuery();
            
            if(rs.next()){
                ps = con.prepareStatement(query);
                ps.setInt(1, produtoCamarim.getAvaria());
                ps.setInt(2,produtoCamarim.getProducao());
                ps.setInt(3, produtoCamarim.getSocios());
                ps.setInt(4, produtoCamarim.getCacau());
                ps.setInt(5, produtoCamarim.getProduto().getEvento().getId());
                ps.execute();
                ps.close();
            } else{
                salvar(produtoCamarim);
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
