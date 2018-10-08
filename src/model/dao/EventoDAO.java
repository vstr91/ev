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
import java.util.Calendar;
import java.util.List;
import model.Evento;
import model.Produto;
import model.UnidadeVenda;
import org.joda.time.DateTime;

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
                evento.setData(new DateTime(rs.getDate(2)));
                evento.setObservacao(rs.getString(5));
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

    public void salvar(Evento evento) throws SQLException {

        String query = "INSERT INTO evento (nome, data, observacao) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, evento.getNome());
            ps.setDate(2, evento.getData().toDate(), Calendar.getInstance());
            ps.setString(4, produto.getObservacao());
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
                + "observacao = ? WHERE id = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getTipoUnidade().getId());
            ps.setInt(3, produto.getDoses());
            ps.setString(4, produto.getObservacao());
            ps.setInt(5, produto.getId());
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
