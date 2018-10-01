/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Produto;

/**
 *
 */
public class ProdutoDAO {

    public ProdutoDAO() {

    }

    public void salvar(Produto produto) throws SQLException {

        String query = "INSERT INTO produto (nome, doses, observacao) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getDoses());
            ps.setString(3, produto.getObservacao());
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
