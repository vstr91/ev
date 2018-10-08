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
import java.util.ArrayList;
import java.util.List;
import model.Caixa;
import model.CaixaEvento;
import model.Evento;

/**
 *
 */
public class CaixaEventoDAO {

    public CaixaEventoDAO() {

    }
    
    public List<CaixaEvento> listarTodos() throws SQLException {

        String query = "SELECT * FROM caixa_evento";
        PreparedStatement ps = null;
        List<CaixaEvento> caixas = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                CaixaEvento caixaEvento = new CaixaEvento();
                caixaEvento.setId(rs.getInt(1));
                caixaEvento.setNome(rs.getString(2));
                caixaEvento.setNumero(rs.getInt(3));
                
                Evento evento = new Evento();
                evento.setId(rs.getInt(4));
                
                caixaEvento.setEvento(evento);
                
                caixas.add(caixaEvento);
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return caixas;
        
    }

    public void salvar(CaixaEvento caixaEvento) throws SQLException {

        String query = "INSERT INTO caixa_evento (nome, numero, evento) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, caixaEvento.getNome());
            ps.setInt(2,caixaEvento.getNumero());
            ps.setInt(3, caixaEvento.getEvento().getId());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public void editar(CaixaEvento caixaEvento) throws SQLException {

        String query = "UPDATE caixa_evento SET nome = ?, numero = ?, evento = ? WHERE id = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, caixaEvento.getNome());
            ps.setInt(2,caixaEvento.getNumero());
            ps.setInt(3, caixaEvento.getEvento().getId());
            ps.setInt(4, caixaEvento.getId());
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
