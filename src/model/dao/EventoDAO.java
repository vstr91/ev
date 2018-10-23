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
import java.util.Calendar;
import java.util.List;
import model.Evento;
import model.Produto;
import model.UnidadeVenda;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 *
 */
public class EventoDAO {

    public EventoDAO() {

    }
    
    public List<Evento> listarTodos() throws SQLException {

        String query = "SELECT * FROM evento";
        PreparedStatement ps = null;
        List<Evento> eventos = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Evento evento = new Evento();
                evento.setId(rs.getInt(1));
                evento.setNome(rs.getString(2));
                evento.setData(DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss.S")
                        .parseDateTime(rs.getString(3)));
                evento.setObservacao(rs.getString(4));
                eventos.add(evento);
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return eventos;
        
    }

    public Integer salvar(Evento evento) throws SQLException {

        String query = "INSERT INTO evento (nome, data, observacao) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        Integer id = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, evento.getNome());
            ps.setDate(2, new Date(evento.getData().getMillis()));
            ps.setString(3, evento.getObservacao());
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
    
    public void editar(Evento evento) throws SQLException {

        String query = "UPDATE evento SET nome = ?, data = ?, observacao = ? WHERE id = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, evento.getNome());
            ps.setDate(2, new Date(evento.getData().getMillis()));
            ps.setString(3, evento.getObservacao());
            ps.setInt(4, evento.getId());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public Evento carregar(Evento umEvento) throws SQLException {

        String query = "SELECT * FROM evento WHERE id = ?";
        PreparedStatement ps = null;
        Evento evento = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, umEvento.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                evento = new Evento();
                evento.setId(rs.getInt(1));
                evento.setNome(rs.getString(2));
                evento.setData(DateTimeFormat.forPattern("YYYY-MM-dd HH:mm:ss.S")
                        .parseDateTime(rs.getString(3)));
                evento.setObservacao(rs.getString(4));
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return evento;
        
    }

}
