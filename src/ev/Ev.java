/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ev;

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
    }
    
}
