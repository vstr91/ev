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
import model.Combo;
import model.Produto;
import model.ProdutoCaixa;
import model.ProdutoCombo;

/**
 *
 */
public class ProdutoComboDAO {

    public ProdutoComboDAO() {

    }
    
    public List<ProdutoCombo> listarTodosProdutos() throws SQLException {

        String query = "SELECT pc.ID, p.ID, pc.COMBO, pc.QUANTIDADE " +
"FROM produto p LEFT JOIN produto_combo pc ON pc.PRODUTO = p.ID";
        PreparedStatement ps = null;
        List<ProdutoCombo> produtos = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ComboDAO comboDAO = new ComboDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoCombo produtoCombo = new ProdutoCombo();
                produtoCombo.setId(rs.getInt(1));
                
                Produto produto = new Produto();
                produto.setId(rs.getInt(2));
                produto = produtoDAO.carregar(produto);
                
                Combo combo = new Combo();
                combo.setId(rs.getInt(3));
                combo = comboDAO.carregar(combo);
                
                produtoCombo.setCombo(combo);
                produtoCombo.setProduto(produto);

                produtoCombo.setQuantidade(rs.getInt(4));
                
                produtos.add(produtoCombo);
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
    
    public List<ProdutoCombo> listarTodosProdutosPorCombo(Combo c) throws SQLException {

        String query = "SELECT pc.ID, pc.PRODUTO, pc.COMBO, pc.QUANTIDADE " +
"FROM produto p LEFT JOIN produto_combo pc ON pc.PRODUTO = p.ID WHERE pc.combo = ?";
        PreparedStatement ps = null;
        List<ProdutoCombo> produtos = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ComboDAO comboDAO = new ComboDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, c.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ProdutoCombo produtoCombo = new ProdutoCombo();
                produtoCombo.setId(rs.getInt(1));
                
                Produto produto = new Produto();
                produto.setId(rs.getInt(2));
                produto = produtoDAO.carregar(produto);
                
                Combo combo = new Combo();
                combo.setId(rs.getInt(3));
                combo = comboDAO.carregar(combo);
                
                produtoCombo.setCombo(combo);
                produtoCombo.setProduto(produto);

                produtoCombo.setQuantidade(rs.getInt(4));
                
                produtos.add(produtoCombo);
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

    public void salvar(ProdutoCombo produtoCombo) throws SQLException {

        String query = "INSERT INTO produto_combo (produto, combo, quantidade) "
                + "VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, produtoCombo.getProduto().getId());
            ps.setInt(2,produtoCombo.getCombo().getId());
            ps.setInt(3, produtoCombo.getQuantidade());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public void editar(ProdutoCombo produtoCombo) throws SQLException {

        String queryCheca = "SELECT * FROM produto_combo WHERE produto = ? "
                + "and combo = ?";
        PreparedStatement psCheca = null;
        
        String query = "UPDATE produto_combo SET quantidade = ? WHERE produto = ? "
                + "and combo = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            psCheca = con.prepareStatement(queryCheca);
            psCheca.setInt(1, produtoCombo.getProduto().getId());
            psCheca.setInt(2, produtoCombo.getCombo().getId());
            
            ResultSet rs = psCheca.executeQuery();
            
            if(rs.next()){
                ps = con.prepareStatement(query);
                ps.setInt(1, produtoCombo.getQuantidade());
                ps.setInt(2,produtoCombo.getProduto().getId());
                ps.setInt(3, produtoCombo.getCombo().getId());
                ps.execute();
                ps.close();
            } else{
                salvar(produtoCombo);
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
    
    public void excluir(ProdutoCombo produtoCombo) throws SQLException {

        String query = "DELETE FROM produto_combo WHERE id = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, produtoCombo.getId());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }

}
