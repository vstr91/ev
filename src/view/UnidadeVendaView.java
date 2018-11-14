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
import javax.swing.JOptionPane;
import model.Combo;
import model.UnidadeVenda;
import model.UnidadeVendaTableModel;
import model.dao.UnidadeVendaDAO;

/**
 *
 * @author Almir
 */
public class UnidadeVendaView extends javax.swing.JDialog {

    List<UnidadeVenda> unidades;
    UnidadeVendaDAO unidadeVendaDAO = new UnidadeVendaDAO();
    UnidadeVendaTableModel tableModelUnidadeVenda;
    UnidadeVenda unidadeVenda;
    int idGerado = -1;
    
    boolean flagEdicao = false;
    Integer idUnidade;
    
    /**
     * Creates new form UnidadeVendaView
     */
    public UnidadeVendaView() {
        initComponents();
        carregarRegistrosTabela();
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
        tableUnidadesVenda = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciar Unidades de Venda");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tableUnidadesVenda.setModel(new javax.swing.table.DefaultTableModel(
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
        tableUnidadesVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUnidadesVendaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableUnidadesVenda);

        jLabel1.setText("Nome");

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(textFieldNome)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCadastrar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btnCadastrar)
                .addContainerGap())
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

    private void tableUnidadesVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUnidadesVendaMouseClicked
        if (evt.getClickCount() >= 2) {
            
            int linha = tableUnidadesVenda.getSelectedRow();

            UnidadeVenda u = unidades.get(linha);
            
            flagEdicao = true;

            textFieldNome.setText(u.getNome());

            idUnidade = u.getId();
            btnCadastrar.setText("Editar");

        }
    }//GEN-LAST:event_tableUnidadesVendaMouseClicked

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        if(textFieldNome.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Por Favor digite o nome da unidade de venda", "Dados não Informados", JOptionPane.ERROR_MESSAGE);
        } else{
            salvar();
            JOptionPane.showMessageDialog(this, "Produto cadastrado com sucesso!", "Produto Cadastrado", JOptionPane.INFORMATION_MESSAGE);
            textFieldNome.setText("");

            if (flagEdicao) {
                flagEdicao = false;
            }
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void salvar() {
        //SALVA UNIDADE
        unidadeVenda = new UnidadeVenda();
        unidadeVenda.setNome(textFieldNome.getText().trim());

        if (unidadeVendaDAO == null) {
            unidadeVendaDAO = new UnidadeVendaDAO();
        }
        
        if (flagEdicao && idUnidade != null) {
            unidadeVenda.setId(idUnidade);
            try {
                unidadeVendaDAO.editar(unidadeVenda);
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
//                flagEdicao = false;
                idUnidade = null;
                btnCadastrar.setText("Cadastrar");
            }
        } else {
            try {
                unidadeVendaDAO.salvar(unidadeVenda);
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        try {
//            idGerado = unidadeVendaDAO.salvar(unidadeVenda);
//        } catch (SQLException ex) {
//            Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        textFieldNome.setText("");
        
        carregarRegistrosTabela();

    }
    
    private void carregarRegistrosTabela() {
        try {
            unidades = unidadeVendaDAO.listarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableModelUnidadeVenda = new UnidadeVendaTableModel(unidades);
        tableUnidadesVenda.setModel(tableModelUnidadeVenda);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableUnidadesVenda;
    private javax.swing.JTextField textFieldNome;
    // End of variables declaration//GEN-END:variables
}
