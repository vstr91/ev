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
import model.BarracaEvento;
import model.Produto;
import model.ProdutoBarraca;
import model.ProdutoEvento;

/**
 *
 */
public class ProdutoBarracaDAO {

    public ProdutoBarracaDAO() {

    }
    
    public List<ProdutoBarraca> listarTodosPorBarracaEvento(BarracaEvento ev) throws SQLException {

        String query = "SELECT p.ID, pe.EVENTO, pb.BARRACA, pb.QUANTIDADE " +
"FROM produto p LEFT JOIN produto_evento pe ON pe.PRODUTO = p.ID LEFT JOIN " +
"PRODUTO_BARRACA pb ON (pb.PRODUTO_EVENTO = pe.ID AND (pb.BARRACA = ? OR pb.BARRACA IS NULL)) " +
"WHERE pe.evento = ? AND (pb.BARRACA = ? OR pb.BARRACA IS NULL) AND p.tipo = 1";
        PreparedStatement ps = null;
        List<ProdutoBarraca> produtos = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ProdutoEventoDAO produtoEventoDAO = new ProdutoEventoDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, ev.getId());
            ps.setInt(2, ev.getEvento().getId());
            ps.setInt(3, ev.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoBarraca produtoBarraca = new ProdutoBarraca();
                
                Produto produto = new Produto();
                produto.setId(rs.getInt(1));
                produto = produtoDAO.carregar(produto);
                
                ProdutoEvento produtoEvento = new ProdutoEvento();
                produtoEvento.setEvento(ev.getEvento());
                produtoEvento.setProduto(produto);
                
                produtoEvento = produtoEventoDAO.carregar(produtoEvento);
                
                produtoBarraca.setProduto(produtoEvento);
                
                produtoBarraca.setBarraca(ev);
                produtoBarraca.setQuantidade(rs.getInt(4));
                
                produtos.add(produtoBarraca);
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

    public void salvar(ProdutoBarraca produtoBarraca) throws SQLException {

        String query = "INSERT INTO produto_barraca (produto_evento, barraca, quantidade) "
                + "VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, produtoBarraca.getProduto().getId());
            ps.setInt(2,produtoBarraca.getBarraca().getId());
            ps.setInt(3, produtoBarraca.getQuantidade());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public void editar(ProdutoBarraca produtoBarraca) throws SQLException {

        String queryCheca = "SELECT * FROM produto_barraca WHERE produto_evento = ? "
                + "and barraca = ?";
        PreparedStatement psCheca = null;
        
        String query = "UPDATE produto_barraca SET quantidade = ? WHERE produto_evento = ? "
                + "and barraca = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            psCheca = con.prepareStatement(queryCheca);
            psCheca.setInt(1, produtoBarraca.getProduto().getId());
            psCheca.setInt(2, produtoBarraca.getBarraca().getId());
            
            ResultSet rs = psCheca.executeQuery();
            
            if(rs.next()){
                ps = con.prepareStatement(query);
                ps.setInt(1, produtoBarraca.getQuantidade());
                ps.setInt(2,produtoBarraca.getProduto().getId());
                ps.setInt(3, produtoBarraca.getBarraca().getId());
                ps.execute();
                ps.close();
            } else{
                salvar(produtoBarraca);
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
