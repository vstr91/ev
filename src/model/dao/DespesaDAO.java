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
import model.Despesa;
import model.Evento;
import model.Produto;
import model.UnidadeVenda;

/**
 *
 */
public class DespesaDAO {

    public DespesaDAO() {

    }
    
    public List<Despesa> listarTodos() throws SQLException {

        String query = "SELECT * FROM despesa";
        PreparedStatement ps = null;
        List<Despesa> despesas = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            EventoDAO eventoDAO = new EventoDAO();
            
            while(rs.next()){
                Despesa despesa = new Despesa();
                despesa.setId(rs.getInt(1));
                despesa.setNome(rs.getString(2));
                despesa.setValorUnitario(rs.getBigDecimal(3));
                despesa.setQuantidade(rs.getInt(4));
                despesa.setValorPago(rs.getBigDecimal(5));
                despesa.setObservacao(rs.getString(6));
                
                Evento evento = new Evento();
                evento.setId(rs.getInt(7));
                
                evento = eventoDAO.carregar(evento);
                
                despesa.setEvento(evento);
                
                despesas.add(despesa);
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return despesas;
        
    }
    
    public List<Despesa> listarTodosPorEvento(Evento ev) throws SQLException {

        String query = "SELECT id, nome, valor_unitario, quantidade, valor_pago, observacao, evento FROM despesa WHERE evento = ?";
        PreparedStatement ps = null;
        List<Despesa> despesas = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, ev.getId());
            ResultSet rs = ps.executeQuery();
            EventoDAO eventoDAO = new EventoDAO();
            
            while(rs.next()){
                Despesa despesa = new Despesa();
                despesa.setId(rs.getInt(1));
                despesa.setNome(rs.getString(2));
                despesa.setValorUnitario(rs.getBigDecimal(3));
                despesa.setQuantidade(rs.getInt(4));
                despesa.setValorPago(rs.getBigDecimal(5));
                despesa.setObservacao(rs.getString(6));
                
                Evento evento = new Evento();
                evento.setId(rs.getInt(7));
                
                evento = eventoDAO.carregar(evento);
                
                despesa.setEvento(evento);
                
                despesas.add(despesa);
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return despesas;
        
    }

    public void salvar(Despesa despesa) throws SQLException {

        String query = "INSERT INTO despesa (nome, valor_unitario, quantidade, valor_pago, observacao, evento) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, despesa.getNome());
            ps.setBigDecimal(2, despesa.getValorUnitario());
            ps.setInt(3, despesa.getQuantidade());
            ps.setBigDecimal(4, despesa.getValorPago());
            ps.setString(5, despesa.getObservacao());
            ps.setInt(6, despesa.getEvento().getId());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public void editar(Despesa despesa) throws SQLException {

        String query = "UPDATE despesa SET nome = ?, valor_unitario = ?, quantidade = ?, valor_pago = ?, evento = ?, "
                + "observacao = ? WHERE id = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, despesa.getNome());
            ps.setBigDecimal(2, despesa.getValorUnitario());
            ps.setInt(3, despesa.getQuantidade());
            ps.setBigDecimal(4, despesa.getValorPago());
            ps.setInt(5, despesa.getEvento().getId());
            ps.setString(6, despesa.getObservacao());
            ps.setInt(7, despesa.getId());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public Despesa carregar(Despesa umaDespesa) throws SQLException {

        String query = "SELECT * FROM despesa WHERE id = ?";
        PreparedStatement ps = null;
        Despesa despesa = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            EventoDAO eventoDAO = new EventoDAO();
            
            while(rs.next()){
                despesa = new Despesa();
                despesa.setId(rs.getInt(1));
                despesa.setNome(rs.getString(2));
                despesa.setValorUnitario(rs.getBigDecimal(3));
                despesa.setQuantidade(rs.getInt(4));
                despesa.setValorPago(rs.getBigDecimal(5));
                despesa.setObservacao(rs.getString(6));
                
                Evento evento = new Evento();
                evento.setId(rs.getInt(7));
                
                evento = eventoDAO.carregar(evento);
                
                despesa.setEvento(evento);
            }
            
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
        return despesa;
        
    }

}
