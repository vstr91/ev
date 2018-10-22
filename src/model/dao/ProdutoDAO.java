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
import model.Produto;
import model.UnidadeVenda;

/**
 *
 */
public class ProdutoDAO {

    public ProdutoDAO() {

    }
    
    public List<Produto> listarTodos(int tipo) throws SQLException {

        String query = "SELECT * FROM produto WHERE tipo = ?";
        PreparedStatement ps = null;
        List<Produto> produtos = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(0, tipo);
            ResultSet rs = ps.executeQuery();
            UnidadeVendaDAO unidadeVendaDAO = new UnidadeVendaDAO();
            
            while(rs.next()){
                Produto produto = new Produto();
                produto.setId(rs.getInt(1));
                produto.setNome(rs.getString(2));
                
                UnidadeVenda unidadeVenda = unidadeVendaDAO.carregar(rs.getInt(3));
                
                produto.setTipoUnidade(unidadeVenda);
                produto.setDoses(rs.getInt(4));
                produto.setObservacao(rs.getString(5));
                produtos.add(produto);
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

    public void salvar(Produto produto) throws SQLException {

        String query = "INSERT INTO produto (nome, unidade_venda, doses, observacao, tipo) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getTipoUnidade().getId());
            ps.setInt(3, produto.getDoses());
            ps.setString(4, produto.getObservacao());
            ps.setInt(5, produto.getTipo());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public void editar(Produto produto) throws SQLException {

        String query = "UPDATE produto SET nome = ?, unidade_venda = ?, doses = ?, "
                + "observacao = ?, tipo = ? WHERE id = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getTipoUnidade().getId());
            ps.setInt(3, produto.getDoses());
            ps.setString(4, produto.getObservacao());
            ps.setInt(5, produto.getTipo());
            ps.setInt(6, produto.getId());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public Produto carregar(Produto umProduto) throws SQLException {

        String query = "SELECT * FROM produto WHERE id = ?";
        PreparedStatement ps = null;
        List<Produto> produtos = new ArrayList<>();
        Produto produto = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, umProduto.getId());
            ResultSet rs = ps.executeQuery();
            UnidadeVendaDAO unidadeVendaDAO = new UnidadeVendaDAO();
            
            while(rs.next()){
                produto = new Produto();
                produto.setId(rs.getInt(1));
                produto.setNome(rs.getString(2));
                
                UnidadeVenda unidadeVenda = unidadeVendaDAO.carregar(rs.getInt(3));
                
                produto.setTipoUnidade(unidadeVenda);
                produto.setDoses(rs.getInt(4));
                produto.setObservacao(rs.getString(5));
                produto.setTipo(rs.getInt(6));
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
