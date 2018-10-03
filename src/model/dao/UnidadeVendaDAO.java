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
import model.UnidadeVenda;

/**
 *
 */
public class UnidadeVendaDAO {

    public UnidadeVendaDAO() {

    }

    public List<UnidadeVenda> listarTodos() throws SQLException {

        String query = "SELECT * FROM unidade_venda";
        PreparedStatement ps = null;
        List<UnidadeVenda> unidades = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                UnidadeVenda unidadeVenda = new UnidadeVenda();
                unidadeVenda.setId(rs.getInt(1));
                unidadeVenda.setNome(rs.getString(2));
                unidades.add(unidadeVenda);
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return unidades;
        
    }

}
