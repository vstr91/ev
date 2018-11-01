/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Combo;
import model.ComboEvento;
import model.Evento;
import model.Produto;
import model.ProdutoEvento;

/**
 *
 */
public class ComboEventoDAO {

    public ComboEventoDAO() {

    }
    
    public List<ComboEvento> listarTodos() throws SQLException {

        String query = "SELECT * FROM combo_evento";
        PreparedStatement ps = null;
        List<ComboEvento> combos = new ArrayList<>();
        ComboDAO comboDAO = new ComboDAO();
        EventoDAO eventoDAO = new EventoDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ComboEvento comboEvento = new ComboEvento();
                
                comboEvento.setId(rs.getInt(1));
                
                Combo combo = new Combo();
                combo.setId(rs.getInt(2));
                combo = comboDAO.carregar(combo);
                
                comboEvento.setCombo(combo);
                
                Evento evento = new Evento();
                evento.setId(rs.getInt(3));
                evento = eventoDAO.carregar(evento);
                
                comboEvento.setEvento(evento);
                comboEvento.setValorCusto(rs.getBigDecimal(4));
                comboEvento.setValorVenda(rs.getBigDecimal(5));
                
                combos.add(comboEvento);
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
    
    public List<ComboEvento> listarTodosPorEvento(Evento ev) throws SQLException {

        String query = "SELECT c.id, -1 AS evento, valor_custo, valor_venda, ce.id " +
"FROM combo c LEFT JOIN combo_evento ce ON (c.id = ce.COMBO OR ce.COMBO IS NULL) " +
"AND evento = ?";
        PreparedStatement ps = null;
        List<ComboEvento> produtos = new ArrayList<>();
        ComboDAO comboDAO = new ComboDAO();
        EventoDAO eventoDAO = new EventoDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, ev.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ComboEvento comboEvento = new ComboEvento();
                
                Combo combo = new Combo();
                combo.setId(rs.getInt(1));
                combo = comboDAO.carregar(combo);
                
                comboEvento.setCombo(combo);
                
                comboEvento.setEvento(ev);
                comboEvento.setValorCusto(rs.getBigDecimal(3));
                comboEvento.setValorVenda(rs.getBigDecimal(4));
                comboEvento.setId(rs.getInt(5));
                
                produtos.add(comboEvento);
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

    public void salvar(ComboEvento comboEvento) throws SQLException {

        String query = "INSERT INTO combo_evento (valor_custo, valor_venda, evento, combo) "
                + "VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setBigDecimal(1, comboEvento.getValorCusto());
            ps.setBigDecimal(2,comboEvento.getValorVenda());
            ps.setInt(3, comboEvento.getEvento().getId());
            ps.setInt(4, comboEvento.getCombo().getId());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println("ADICAO: "+e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public void editar(ComboEvento comboEvento) throws SQLException {

        String queryCheca = "SELECT * FROM combo_evento WHERE evento = ? "
                + "and combo = ?";
        PreparedStatement psCheca = null;
        
        String query = "UPDATE combo_evento SET valor_custo = ?, valor_venda = ? WHERE evento = ? "
                + "and combo = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            psCheca = con.prepareStatement(queryCheca);
            psCheca.setInt(1, comboEvento.getEvento().getId());
            psCheca.setInt(2, comboEvento.getCombo().getId());
            
            ResultSet rs = psCheca.executeQuery();
            
            if(rs.next()){
                ps = con.prepareStatement(query);
                ps.setBigDecimal(1, comboEvento.getValorCusto());
                ps.setBigDecimal(2,comboEvento.getValorVenda());
                ps.setInt(3, comboEvento.getEvento().getId());
                ps.setInt(4, comboEvento.getCombo().getId());
                ps.execute();
                ps.close();
            } else{
                salvar(comboEvento);
            }
            
            psCheca.close();
            
            
            
        } catch (SQLException e) {
            System.out.println("EDICAO: "+e);
            
            if(ps != null){
                ps.close();
            }
            
            if(psCheca != null){
                psCheca.close();
            }
            
        }
        
    }
    
    public ComboEvento carregar(ComboEvento umProduto) throws SQLException {

        String query = "SELECT combo, evento, valor_custo, valor_venda, id FROM combo_evento WHERE combo = ? AND evento = ?";
        PreparedStatement ps = null;
        ComboEvento produto = null;
        ComboDAO comboDAO = new ComboDAO();
        EventoDAO eventoDAO = new EventoDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, umProduto.getCombo().getId());
            ps.setInt(2, umProduto.getEvento().getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                produto = new ComboEvento();
                
                Combo c = new Combo();
                c.setId(rs.getInt(1));
                c = comboDAO.carregar(c);
                
                produto.setCombo(c);
                
                Evento e = new Evento();
                e.setId(rs.getInt(2));
                e = eventoDAO.carregar(e);
                produto.setEvento(e);
                
                produto.setValorCusto(rs.getBigDecimal(3));
                produto.setValorVenda(rs.getBigDecimal(4));
                produto.setId(rs.getInt(5));
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
