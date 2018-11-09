/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 */
public class MessageUtils {

    public static void exibeMensagem(JDialog parent, String titulo, String mensagem, int tipo){
        JOptionPane.showMessageDialog(parent, mensagem, titulo, tipo);
    }
    
}
