/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import model.BarracaEvento;
import model.CaixaEvento;
import model.Evento;
import model.dao.BarracaEventoDAO;
import model.dao.CaixaEventoDAO;
import model.dao.EventoDAO;
import org.joda.time.format.DateTimeFormat;

/**
 *
 */
public class NovoEventoView extends javax.swing.JDialog {

    Evento evento;
    EventoDAO eventoDAO;
    Integer idEvento;
    boolean flagEdicao = false;
    Integer idGerado = -1;
    
    /**
     * Creates new form NovoEventoView
     */
    public NovoEventoView() {
        initComponents();
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
        jLabel1 = new javax.swing.JLabel();
        textFieldNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        spinnerBares = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        spinnerBarracas = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaObservacao = new javax.swing.JTextArea();
        btnCadastrar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        textFieldData = new javax.swing.JTextField();
        try{
            javax.swing.text.MaskFormatter data= new javax.swing.text.MaskFormatter("##/##/####");
            textFieldData = new javax.swing.JFormattedTextField(data);
        }
        catch (Exception e){
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Novo Evento");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nome");

        jLabel2.setText("Quantidade de Caixas");

        jLabel3.setText("Quantidade de Barracas");

        jLabel4.setText("Observações");

        textAreaObservacao.setColumns(20);
        textAreaObservacao.setRows(5);
        jScrollPane1.setViewportView(textAreaObservacao);

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        jLabel5.setText("Data (dd/mm/yyyy)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCadastrar))
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(spinnerBares, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(textFieldNome))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spinnerBarracas)
                            .addComponent(textFieldData))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerBares, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerBarracas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        try {
            spinnerBares.commitEdit();
            spinnerBarracas.commitEdit();
        } catch (ParseException ex) {
            Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(textFieldNome.getText() == null || textFieldNome.getText().trim().equals("") || textFieldNome.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Por Favor digite o nome do evento", "Dados não Informados", JOptionPane.ERROR_MESSAGE);
        } else if((Integer) spinnerBares.getValue() < 1 && (Integer) spinnerBarracas.getValue() < 1){
            JOptionPane.showMessageDialog(this, "Por Favor informe a quantidade de caixas e/ou barracas do evento", "Dados não Informados", JOptionPane.ERROR_MESSAGE);
        } else{
            int totalBares = (int) spinnerBares.getValue();
            int totalBarracas = (int) spinnerBarracas.getValue();
            
            salvar();
            JOptionPane.showMessageDialog(this, "Evento cadastrado com sucesso!", "Produto Cadastrado", JOptionPane.INFORMATION_MESSAGE);
            CaixaEventoDAO caixaEventoDAO = new CaixaEventoDAO();
            BarracaEventoDAO barracaEventoDAO = new BarracaEventoDAO();
            
            for(int i = 0; i < totalBares; i++){
                CaixaEvento caixaEvento = new CaixaEvento();
                caixaEvento.setNome("Caixa "+(i+1));
                caixaEvento.setNumero(i+1);
                
                Evento umEvento = new Evento();
                umEvento.setId(idGerado);
                
                caixaEvento.setEvento(umEvento);
                
                try {
                    caixaEventoDAO.salvar(caixaEvento);
                } catch (SQLException ex) {
                    Logger.getLogger(NovoEventoView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            for(int i = 0; i < totalBarracas; i++){
                BarracaEvento barracaEvento = new BarracaEvento();
                barracaEvento.setNome("Barraca "+(i+1));
                barracaEvento.setNumero(i+1);
                
                Evento umEvento = new Evento();
                umEvento.setId(idGerado);
                
                barracaEvento.setEvento(umEvento);
                
                try {
                    barracaEventoDAO.salvar(barracaEvento);
                } catch (SQLException ex) {
                    Logger.getLogger(NovoEventoView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            dispose();
            
            evento.setId(idGerado);
            
            FechamentoView fechamentoView = new FechamentoView(evento);
            fechamentoView.setLocationRelativeTo(this);
            fechamentoView.setAlwaysOnTop(true);
            fechamentoView.setModal(true);
            fechamentoView.setVisible(true);
            
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void salvar() {
        //SALVA PRODUTO
        evento = new Evento();
        evento.setNome(textFieldNome.getText().trim());
        evento.setData(DateTimeFormat.forPattern("dd/MM/YYYY").parseDateTime(textFieldData.getText()));
        evento.setObservacao(textAreaObservacao.getText().trim());

        if (eventoDAO == null) {
            eventoDAO = new EventoDAO();
        }

        if (flagEdicao && idEvento != null) {
            evento.setId(idEvento);
            try {
                eventoDAO.editar(evento);
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
//                flagEdicao = false;
                idEvento = null;
                btnCadastrar.setText("Cadastrar");
            }
        } else {
            try {
                idGerado = eventoDAO.salvar(evento);
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spinnerBares;
    private javax.swing.JSpinner spinnerBarracas;
    private javax.swing.JTextArea textAreaObservacao;
    private javax.swing.JTextField textFieldData;
    private javax.swing.JTextField textFieldNome;
    // End of variables declaration//GEN-END:variables
}
