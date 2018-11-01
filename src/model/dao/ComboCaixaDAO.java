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
import model.CaixaEvento;
import model.Combo;
import model.ComboCaixa;
import model.ComboEvento;
import model.ProdutoCaixa;

/**
 *
 */
public class ComboCaixaDAO {

    public ComboCaixaDAO() {

    }
    
    public List<ComboCaixa> listarTodosPorCaixaEvento(CaixaEvento ev) throws SQLException {

        String query = "SELECT c.ID, ce.EVENTO, cc.CAIXA, cc.QUANTIDADE " +
"FROM combo c LEFT JOIN combo_evento ce ON ce.COMBO = c.ID LEFT JOIN " +
"COMBO_CAIXA cc ON (cc.COMBO_EVENTO = ce.ID AND (cc.CAIXA = ? OR cc.CAIXA IS NULL)) " +
"WHERE ce.evento = ? AND (cc.CAIXA = ? OR cc.CAIXA IS NULL)";
        PreparedStatement ps = null;
        List<ComboCaixa> produtos = new ArrayList<>();
        ComboDAO comboDAO = new ComboDAO();
        ComboEventoDAO comboEventoDAO = new ComboEventoDAO();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, ev.getId());
            ps.setInt(2, ev.getEvento().getId());
            ps.setInt(3, ev.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                ComboCaixa comboCaixa = new ComboCaixa();
                
                Combo combo = new Combo();
                combo.setId(rs.getInt(1));
                combo = comboDAO.carregar(combo);
                
                ComboEvento comboEvento = new ComboEvento();
                comboEvento.setEvento(ev.getEvento());
                comboEvento.setCombo(combo);
                
                comboEvento = comboEventoDAO.carregar(comboEvento);
                
                comboCaixa.setProduto(comboEvento);
                
                comboCaixa.setCaixa(ev);
                comboCaixa.setQuantidade(rs.getInt(4));
                
                produtos.add(comboCaixa);
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

    public void salvar(ComboCaixa comboCaixa) throws SQLException {

        String query = "INSERT INTO combo_caixa (combo_evento, caixa, quantidade) "
                + "VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setInt(1, comboCaixa.getProduto().getId());
            ps.setInt(2,comboCaixa.getCaixa().getId());
            ps.setInt(3, comboCaixa.getQuantidade());
            ps.execute();
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e);
            
            if(ps != null){
                ps.close();
            }
            
        }
        
    }
    
    public void editar(ComboCaixa comboCaixa) throws SQLException {

        String queryCheca = "SELECT * FROM combo_caixa WHERE combo_evento = ? "
                + "and caixa = ?";
        PreparedStatement psCheca = null;
        
        String query = "UPDATE combo_caixa SET quantidade = ? WHERE combo_evento = ? "
                + "and caixa = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            psCheca = con.prepareStatement(queryCheca);
            psCheca.setInt(1, comboCaixa.getProduto().getId());
            psCheca.setInt(2, comboCaixa.getCaixa().getId());
            
            ResultSet rs = psCheca.executeQuery();
            
            if(rs.next()){
                ps = con.prepareStatement(query);
                ps.setInt(1, comboCaixa.getQuantidade());
                ps.setInt(2,comboCaixa.getProduto().getId());
                ps.setInt(3, comboCaixa.getCaixa().getId());
                ps.execute();
                ps.close();
            } else{
                salvar(comboCaixa);
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

}
