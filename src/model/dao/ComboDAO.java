/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Combo;
import model.Evento;
import org.joda.time.format.DateTimeFormat;

/**
 *
 */
public class ComboDAO {

    public ComboDAO() {

    }
    
    public List<Combo> listarTodos() throws SQLException {

        String query = "SELECT * FROM combo";
        PreparedStatement ps = null;
        List<Combo> combos = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Combo combo = new Combo();
                combo.setId(rs.getInt(1));
                combo.setNome(rs.getString(2));
                combos.add(combo);
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return combos;
        
    }

    public Integer salvar(Combo combo) throws SQLException {

        String query = "INSERT INTO combo (nome) VALUES (?)";
        PreparedStatement ps = null;
        Integer id = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, combo.getNome());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            
            ps.close();
            
            
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return id;
        
    }
    
    public void editar(Combo combo) throws SQLException {

        String query = "UPDATE combo SET nome = ? WHERE id = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, combo.getNome());
            ps.setInt(2, combo.getId());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public Combo carregar(Combo umCombo) throws SQLException {

        String query = "SELECT * FROM combo WHERE id = ?";
        PreparedStatement ps = null;
        Combo combo = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, umCombo.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                combo = new Combo();
                combo.setId(rs.getInt(1));
                combo.setNome(rs.getString(2));
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return combo;
        
    }

}
