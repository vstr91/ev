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
import model.CaixaEvento;
import model.Evento;

/**
 *
 */
public class BarracaEventoDAO {

    public BarracaEventoDAO() {

    }
    
    public List<BarracaEvento> listarTodos() throws SQLException {

        String query = "SELECT * FROM barraca_evento";
        PreparedStatement ps = null;
        List<BarracaEvento> barracas = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                BarracaEvento barracaEvento = new BarracaEvento();
                barracaEvento.setId(rs.getInt(1));
                barracaEvento.setNome(rs.getString(2));
                barracaEvento.setNumero(rs.getInt(3));
                
                Evento evento = new Evento();
                evento.setId(rs.getInt(4));
                
                barracaEvento.setEvento(evento);
                
                barracas.add(barracaEvento);
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return barracas;
        
    }
    
    public List<BarracaEvento> listarTodosPorEvento(Evento ev) throws SQLException {

        String query = "SELECT c.*, SUM(pc.quantidade), SUM(pe.VALOR_VENDA * pc.QUANTIDADE) " +
"                FROM barraca_evento c LEFT JOIN " +
"                     produto_barraca pc ON pc.BARRACA = c.ID LEFT JOIN " +
"                     produto_evento pe ON pe.ID = pc.PRODUTO_EVENTO AND pe.evento = ? " +
"                WHERE c.evento = ?" +
"                GROUP BY c.id, c.NOME, c.NUMERO, c.EVENTO";
        PreparedStatement ps = null;
        List<BarracaEvento> barracas = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, ev.getId());
            ps.setInt(2, ev.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                BarracaEvento barracaEvento = new BarracaEvento();
                barracaEvento.setId(rs.getInt(1));
                barracaEvento.setNome(rs.getString(2));
                barracaEvento.setNumero(rs.getInt(3));
                
                Evento evento = new Evento();
                evento.setId(rs.getInt(4));
                
                barracaEvento.setEvento(evento);
                
                barracaEvento.setTotalVendido(rs.getInt(5));
                barracaEvento.setValorTotalVendido(rs.getBigDecimal(6));
                
                barracas.add(barracaEvento);
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return barracas;
        
    }

    public void salvar(BarracaEvento barracaEvento) throws SQLException {

        String query = "INSERT INTO barraca_evento (nome, numero, evento) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, barracaEvento.getNome());
            ps.setInt(2,barracaEvento.getNumero());
            ps.setInt(3, barracaEvento.getEvento().getId());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public void editar(BarracaEvento barracaEvento) throws SQLException {

        String query = "UPDATE barraca_evento SET nome = ?, numero = ?, evento = ? WHERE id = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, barracaEvento.getNome());
            ps.setInt(2,barracaEvento.getNumero());
            ps.setInt(3, barracaEvento.getEvento().getId());
            ps.setInt(4, barracaEvento.getId());
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
