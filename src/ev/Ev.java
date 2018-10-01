/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ev;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Produto;
import model.dao.ConnectionFactory;
import model.dao.ProdutoDAO;
import view.MainView;

/**
 *
 * @author Almir
 */
public class Ev {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainView mainView = new MainView();
        mainView.setResizable(false);
        mainView.setTitle("Gerenciador de Eventos");
        mainView.setVisible(true);
        
        Produto p = new Produto();
        p.setNome("teste");
        p.setDoses(12);
        p.setObservacao("Teste");
        
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            produtoDAO.salvar(p);
        } catch (SQLException ex) {
            Logger.getLogger(Ev.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
