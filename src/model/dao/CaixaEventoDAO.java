/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<CaixaEvento> listarTodosPorEvento(Evento ev) throws SQLException {

        String queryProduto = "SELECT c.*, SUM(pc.quantidade), SUM(pe.VALOR_VENDA * pc.QUANTIDADE) " +
                "FROM caixa_evento c LEFT JOIN " +
                "     produto_caixa pc ON pc.CAIXA = c.ID LEFT JOIN " +
                "     produto_evento pe ON pe.ID = pc.PRODUTO_EVENTO AND pe.evento = ? " +
                "WHERE c.evento = ?" +
                " GROUP BY c.id, c.NOME, c.NUMERO, c.EVENTO, c.VENDA_DEBITO, c.VENDA_CREDITO, c.VENDA_DINHEIRO, c.VENDA_VALE";
        
//        String queryCombo = "SELECT c.*, SUM(pc.quantidade), SUM(pe.VALOR_VENDA * pc.QUANTIDADE) " +
//                "FROM caixa_evento c LEFT JOIN " +
//                "     combo_caixa pc ON pc.CAIXA = c.ID LEFT JOIN " +
//                "     combo_evento pe ON pe.ID = pc.COMBO_EVENTO AND pe.evento = ? " +
//                "WHERE c.evento = ?" +
//                " GROUP BY c.id, c.NOME, c.NUMERO, c.EVENTO, c.VENDA_DEBITO, c.VENDA_CREDITO, c.VENDA_DINHEIRO, c.VENDA_VALE";

        String queryCombo = "SELECT c.*, SUM(pco.quantidade * pc.quantidade) AS qtd_und_prod_combos_vend, " +
"pe.VALOR_VENDA * pc.QUANTIDADE AS vl_venda_combos " +
"                FROM caixa_evento c LEFT JOIN " +
"                     combo_caixa pc ON pc.CAIXA = c.ID LEFT JOIN " +
"                     combo_evento pe ON pe.ID = pc.COMBO_EVENTO AND pe.evento = ? LEFT JOIN " +
"                     PRODUTO_COMBO pco ON pco.COMBO = pe.COMBO LEFT JOIN " +
"                     produto p ON p.ID = pco.PRODUTO " +
"                WHERE c.evento = ? " +
"                 GROUP BY c.id, c.NOME, c.NUMERO, c.EVENTO, c.VENDA_DEBITO, c.VENDA_CREDITO, c.VENDA_DINHEIRO, c.VENDA_VALE, " +
"                         pe.VALOR_VENDA * pc.QUANTIDADE";
        
        PreparedStatement ps = null;
        List<CaixaEvento> caixas = new ArrayList<>();

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(queryProduto);
            ps.setInt(1, ev.getId());
            ps.setInt(2, ev.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                CaixaEvento caixaEvento = new CaixaEvento();
                caixaEvento.setId(rs.getInt(1));
                caixaEvento.setNome(rs.getString(2));
                caixaEvento.setNumero(rs.getInt(3));
                
                Evento evento = new Evento();
                evento.setId(rs.getInt(4));
                
                caixaEvento.setEvento(evento);
                
                caixaEvento.setVendaDebito(rs.getBigDecimal(5));
                caixaEvento.setVendaCredito(rs.getBigDecimal(6));
                caixaEvento.setVendaDinheiro(rs.getBigDecimal(7));
                caixaEvento.setVendaVale(rs.getBigDecimal(8));
                
                caixaEvento.setTotalVendido(rs.getInt(9));
                caixaEvento.setValorTotalVendido(rs.getBigDecimal(10));
                
                caixas.add(caixaEvento);
            }
            
            ps.close();
            ps = null;
            
            //combo
            
            ps = con.prepareStatement(queryCombo);
            ps.setInt(1, ev.getId());
            ps.setInt(2, ev.getId());
            ResultSet rsCombo = ps.executeQuery();
            
            while(rsCombo.next()){
                CaixaEvento caixaEvento = new CaixaEvento();
                caixaEvento.setId(rsCombo.getInt(1));

                CaixaEvento ce = caixas.get(caixas.indexOf(caixaEvento));
                
                ce.setTotalVendido(ce.getTotalVendido()+rsCombo.getInt(9));
                
                BigDecimal bd = ce.getValorTotalVendido();
                BigDecimal bd2 = rsCombo.getBigDecimal(10);
                
                if(ce.getValorTotalVendido() == null){
                    bd = BigDecimal.ZERO;
                }
                
                if(bd2 != null){
                    ce.setValorTotalVendido(bd.add(bd2));
                }
                
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

        String query = "UPDATE caixa_evento SET nome = ?, numero = ?, evento = ?, "
                + "venda_debito = ?, venda_credito = ?, venda_dinheiro = ?, venda_vale = ? WHERE id = ?";
        PreparedStatement ps = null;

        try (Connection con = new ConnectionFactory().getConnection()) {
            
            ps = con.prepareStatement(query);
            ps.setString(1, caixaEvento.getNome());
            ps.setInt(2,caixaEvento.getNumero());
            ps.setInt(3, caixaEvento.getEvento().getId());
            ps.setBigDecimal(4, caixaEvento.getVendaDebito());
            ps.setBigDecimal(5, caixaEvento.getVendaCredito());
            ps.setBigDecimal(6, caixaEvento.getVendaDinheiro());
            ps.setBigDecimal(7, caixaEvento.getVendaVale());
            ps.setInt(8, caixaEvento.getId());
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
