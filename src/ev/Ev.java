/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ev;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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

        try {
            // Set cross-platform Java L&F (also called "Metal")
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        MainView mainView = new MainView();
        mainView.setResizable(false);
        mainView.setLocationRelativeTo(null);
        mainView.setTitle("Gerenciador de Eventos");
        mainView.setVisible(true);

//        Produto p = new Produto();
//        p.setNome("teste");
//        p.setDoses(12);
//        p.setObservacao("Teste");
//        
//        ProdutoDAO produtoDAO = new ProdutoDAO();
//        try {
//            produtoDAO.salvar(p);
//        } catch (SQLException ex) {
//            Logger.getLogger(Ev.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
