/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ComboEvento;
import model.ComboEventoTableModel;
import model.Evento;
import model.ProdutoEvento;
import model.ProdutoEventoTableModel;
import model.dao.ComboEventoDAO;
import model.dao.ProdutoEventoDAO;

/**
 *
 */
public class ProdutoEventoView extends javax.swing.JDialog {

    boolean returnToParent = false;
    Evento evento;
    List<ProdutoEvento> produtos;
    ProdutoEventoDAO produtoEventoDAO = new ProdutoEventoDAO();
    ProdutoEventoTableModel tableModelProdutoEvento;
    
    List<ComboEvento> combos;
    ComboEventoDAO comboEventoDAO = new ComboEventoDAO();
    ComboEventoTableModel tableModelComboEvento;
    
    Integer tipo;

    /**
     * Creates new form ProdutoEventoView
     */
    public ProdutoEventoView(Evento e, int tipo) {
        initComponents();
        this.evento = e;
        this.tipo = tipo;
        
        this.setTitle("Produtos à Venda no Evento "+e.getNome());

        try {
            produtos = produtoEventoDAO.listarTodosPorEvento(evento, tipo);
            tableModelProdutoEvento = new ProdutoEventoTableModel(produtos);
            tableProdutos.setModel(tableModelProdutoEvento);
            
            combos = comboEventoDAO.listarTodosPorEvento(evento);
            tableModelComboEvento = new ComboEventoTableModel(combos);
            tableCombos.setModel(tableModelComboEvento);
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoEventoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        btnNovoCombo.setVisible(false);
        
        if(tipo == 1){
            jScrollPane2.setVisible(false);
            jLabel1.setVisible(false);
        } else{
            jScrollPane2.setVisible(true);
            jLabel1.setVisible(true);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProdutos = new javax.swing.JTable();
        btnNovoProduto = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCombos = new javax.swing.JTable();
        btnNovoCombo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produtos à Venda no Evento");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableProdutos);

        btnNovoProduto.setText("Novo Produto");
        btnNovoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoProdutoActionPerformed(evt);
            }
        });

        tableCombos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableCombos);

        btnNovoCombo.setText("Novo Combo");
        btnNovoCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoComboActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Combos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 734, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNovoCombo, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNovoProduto, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNovoProduto)
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNovoCombo)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoProdutoActionPerformed
        dispose();
        
        ProdutoView produtoView = new ProdutoView(tipo);
        produtoView.setEvento(evento);
        produtoView.setLocationRelativeTo(this);
        produtoView.setAlwaysOnTop(true);
        produtoView.setModal(true);
        produtoView.setVisible(true);
    }//GEN-LAST:event_btnNovoProdutoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        if (returnToParent) {
            FechamentoView fechamentoView = new FechamentoView(evento);
            fechamentoView.setLocationRelativeTo(this);
            fechamentoView.setAlwaysOnTop(true);
            fechamentoView.setModal(true);
            fechamentoView.setVisible(true);
        }
    }//GEN-LAST:event_formWindowClosed

    private void btnNovoComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoComboActionPerformed
        dispose();
        
        ComboView comboView = new ComboView();
        comboView.setEvento(evento);
        comboView.setLocationRelativeTo(this);
        comboView.setAlwaysOnTop(true);
        comboView.setModal(true);
        comboView.setVisible(true);
    }//GEN-LAST:event_btnNovoComboActionPerformed

    public void returnToParent(boolean val) {
        returnToParent = val;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNovoCombo;
    private javax.swing.JButton btnNovoProduto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableCombos;
    private javax.swing.JTable tableProdutos;
    // End of variables declaration//GEN-END:variables
}
