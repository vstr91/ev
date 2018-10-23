/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import model.BarracaEvento;
import model.BarracaTableModel;
import model.CaixaEvento;
import model.CaixaTableModel;
import model.Despesa;
import model.DespesaTableModel;
import model.Evento;
import model.ProdutoCamarim;
import model.ProdutoCamarimTableModel;
import model.dao.BarracaEventoDAO;
import model.dao.CaixaEventoDAO;
import model.dao.DespesaDAO;
import model.dao.ProdutoCamarimDAO;
import org.joda.time.format.DateTimeFormat;

/**
 *
 */
public class FechamentoView extends javax.swing.JDialog {

    JDialog parent;
    private Evento evento;
    CaixaTableModel tableModelCaixa;
    CaixaEventoDAO caixaEventoDAO = new CaixaEventoDAO();
    List<CaixaEvento> caixas;
    BarracaTableModel tableModelBarraca;
    BarracaEventoDAO barracaEventoDAO = new BarracaEventoDAO();
    List<BarracaEvento> barracas;
    Despesa despesa;
    List<Despesa> despesas;
    DespesaTableModel tableModelDespesa;
    DespesaDAO despesaDAO = new DespesaDAO();
    boolean flagEdicao = false;
    Integer idDespesa;

    List<ProdutoCamarim> produtosCamarim;
    ProdutoCamarimTableModel tableModelCamarim;
    ProdutoCamarimDAO produtoCamarimDAO = new ProdutoCamarimDAO();

    BigDecimal totalVendaCaixas = BigDecimal.ZERO;
    BigDecimal totalVendaBarracas = BigDecimal.ZERO;
    BigDecimal totalConsumoCamarim = BigDecimal.ZERO;

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /**
     * Creates new form FechamentoView
     */
    public FechamentoView(Evento e) {
        initComponents();

        this.evento = e;

        parent = this;

        if (evento != null) {
            this.setTitle("Detalhes do Evento " + evento.getNome() + " - " + DateTimeFormat.forPattern("dd/MM/YYYY")
                    .print(evento.getData()));

            try {
//                caixas = caixaEventoDAO.listarTodosPorEvento(evento);
//                tableModelCaixa = new CaixaTableModel(caixas);
//                tableVendasBar.setModel(tableModelCaixa);
//                labelVendasBar.setText(String.valueOf(tableModelCaixa.getQtdTotalVendida()));

                recarregarTabelaCaixa();
                recarregarTabelaBarraca();

                //Despesas
                despesas = despesaDAO.listarTodosPorEvento(evento);
                tableModelDespesa = new DespesaTableModel(despesas);
                tableDespesas.setModel(tableModelDespesa);
                tableDespesas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                //Camarim
                produtosCamarim = produtoCamarimDAO.listarTodosPorEvento(evento);
                tableModelCamarim = new ProdutoCamarimTableModel(produtosCamarim);
                tableConsumoCamarim.setModel(tableModelCamarim);
                tableConsumoCamarim.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                textFieldBarracaChurros.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        calculaTotalBrutoBar();
                    }
                });

            } catch (SQLException ex) {
                Logger.getLogger(FechamentoView.class.getName()).log(Level.SEVERE, null, ex);
            }

            // vendas bares
            for (CaixaEvento c : caixas) {

                if (c.getValorTotalVendido() != null) {
                    totalVendaCaixas = totalVendaCaixas.add(c.getValorTotalVendido());
                }

            }

            labelVendasBar.setText(totalVendaCaixas.toString());

            // vendas barracas
            for (BarracaEvento b : barracas) {

                if (b.getValorTotalVendido() != null) {
                    totalVendaBarracas = totalVendaBarracas.add(b.getValorTotalVendido());
                }

            }

            labelVendasBarracas.setText(totalVendaBarracas.toString());

            // consumo camarim
            for (ProdutoCamarim b : produtosCamarim) {

                if (b.getValorTotalVendido() != null) {
                    totalConsumoCamarim = totalConsumoCamarim.add(b.getValorTotalVendido());
                }

            }

            labelConsumoCamarim.setText(totalConsumoCamarim.toString());

            tableConsumoCamarim.getModel().addTableModelListener(new TableModelListener() {
                @Override
                public void tableChanged(TableModelEvent e) {
                    totalConsumoCamarim = BigDecimal.ZERO;

                    for (ProdutoCamarim b : produtosCamarim) {

                        if (b.getValorTotalVendido() != null) {
                            totalConsumoCamarim = totalConsumoCamarim.add(b.getValorTotalVendido());
                        }

                    }

                    labelConsumoCamarim.setText(totalConsumoCamarim.toString());

                }
            });

            calculaTotalBrutoBar();

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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableVendasBar = new javax.swing.JTable();
        labelVendasBar = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelVendasBarracas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableVendasBarracas = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        labelTotalBrutoBar = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelDespesas = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textFieldRepasseProducao = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableDespesas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        textFieldNomeDespesa = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        textFieldValorUnitario = new javax.swing.JTextField();
        labelValorTotal = new javax.swing.JLabel();
        labelFaltaPagar = new javax.swing.JLabel();
        textFieldValorPago = new javax.swing.JTextField();
        textFieldObservacao = new javax.swing.JTextField();
        btnDespesa = new javax.swing.JButton();
        spinnerQuantidade = new javax.swing.JSpinner();
        labelConsumoCamarim = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableConsumoCamarim = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        textFieldBarracaChurros = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        labelRepasseFinal = new javax.swing.JLabel();
        btnRelatorio = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnProdutos = new javax.swing.JButton();
        btnProdutosBarraca = new javax.swing.JButton();
        btnAdicionarBarraca = new javax.swing.JButton();
        btnAdicionarCaixa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalhes do Evento");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Vendas Bar:");

        tableVendasBar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Caixa", "Quantidade", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVendasBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVendasBarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableVendasBar);

        labelVendasBar.setText("100.000");

        jLabel2.setText("Vendas Barracas:");

        labelVendasBarracas.setText("30.000");

        tableVendasBarracas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Barraca", "Quantidade", "Valor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableVendasBarracas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableVendasBarracasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableVendasBarracas);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Total Bruto do Bar:");

        labelTotalBrutoBar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        labelTotalBrutoBar.setText("130.000");

        jLabel5.setText("Total Despesas (A + B):");

        labelDespesas.setText("30.000");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Total Líquido Bar:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("100.000");

        jLabel8.setText("Administração Bar 13%:");

        jLabel9.setText("1300");

        jLabel10.setText("Repasse Produção:");

        jLabel11.setText("Consumo Camarim e Produção:");

        jLabel13.setText("Despesas (B)");

        tableDespesas.setModel(new javax.swing.table.DefaultTableModel(
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
        tableDespesas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDespesasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableDespesas);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastrar Despesa (B)"));

        jLabel15.setText("Nome:");

        jLabel16.setText("Valor Unitário:");

        jLabel17.setText("Quantidade:");

        jLabel18.setText("Valor Total:");

        jLabel19.setText("Valor Pago:");

        jLabel20.setText("Falta Pagar");

        jLabel21.setText("Obs.:");

        textFieldValorUnitario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textFieldValorUnitarioFocusLost(evt);
            }
        });

        labelValorTotal.setText("0");

        labelFaltaPagar.setText("0");

        textFieldValorPago.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textFieldValorPagoFocusLost(evt);
            }
        });

        btnDespesa.setText("Cadastrar Despesa");
        btnDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDespesaActionPerformed(evt);
            }
        });

        spinnerQuantidade.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerQuantidadeStateChanged(evt);
            }
        });
        spinnerQuantidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                spinnerQuantidadeFocusLost(evt);
            }
        });
        spinnerQuantidade.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                spinnerQuantidadeCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textFieldNomeDespesa)
                                    .addComponent(textFieldValorUnitario)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(spinnerQuantidade))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(labelValorTotal))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(textFieldValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labelFaltaPagar)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(textFieldObservacao)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDespesa)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(textFieldNomeDespesa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(textFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(spinnerQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(labelValorTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(textFieldValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(labelFaltaPagar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(textFieldObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDespesa)
                .addContainerGap())
        );

        labelConsumoCamarim.setText("1000");

        tableConsumoCamarim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Produto", "Valor", "Avaria", "Produção", "Sócios", "Cacau", "Total", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableConsumoCamarim);

        jLabel14.setText("Barraca Churros + Bala + Batata:");

        textFieldBarracaChurros.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textFieldBarracaChurrosFocusLost(evt);
            }
        });
        textFieldBarracaChurros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldBarracaChurrosActionPerformed(evt);
            }
        });
        textFieldBarracaChurros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldBarracaChurrosKeyTyped(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 0, 0));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Repasse Final Sócios");

        labelRepasseFinal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        labelRepasseFinal.setForeground(new java.awt.Color(255, 255, 255));
        labelRepasseFinal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRepasseFinal.setText("50.000");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelRepasseFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelRepasseFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        btnRelatorio.setText("Gerar Relatório");

        btnSalvar.setText("Salvar Informações");

        btnProdutos.setText("Gerenciar Produtos de Bar do Evento");
        btnProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdutosActionPerformed(evt);
            }
        });

        btnProdutosBarraca.setText("Gerenciar Produtos de Barraca do Evento");
        btnProdutosBarraca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProdutosBarracaActionPerformed(evt);
            }
        });

        btnAdicionarBarraca.setText("Adicionar Barraca");
        btnAdicionarBarraca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarBarracaActionPerformed(evt);
            }
        });

        btnAdicionarCaixa.setText("Adicionar Caixa");
        btnAdicionarCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarCaixaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(labelVendasBar)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnAdicionarCaixa))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(labelVendasBarracas)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnAdicionarBarraca))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelDespesas))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(textFieldRepasseProducao))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelTotalBrutoBar))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelConsumoCamarim))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(textFieldBarracaChurros, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btnRelatorio))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane3)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnProdutos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnProdutosBarraca)
                        .addGap(371, 462, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {textFieldBarracaChurros, textFieldRepasseProducao});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProdutos)
                    .addComponent(btnProdutosBarraca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelVendasBar)
                    .addComponent(jLabel13)
                    .addComponent(btnAdicionarCaixa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(labelVendasBarracas)
                            .addComponent(btnAdicionarBarraca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(textFieldBarracaChurros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(labelTotalBrutoBar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(labelDespesas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(textFieldRepasseProducao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(labelConsumoCamarim))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRelatorio)
                    .addComponent(btnSalvar))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnProdutos, btnProdutosBarraca});

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

    private void btnDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDespesaActionPerformed

        try {
            spinnerQuantidade.commitEdit();
        } catch (ParseException ex) {
            Logger.getLogger(FechamentoView.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (textFieldNomeDespesa.getText().trim().isEmpty() || textFieldValorUnitario.getText().trim().isEmpty()
                || textFieldValorPago.getText().trim().isEmpty() || (Integer) spinnerQuantidade.getValue() < 1) {
            JOptionPane.showMessageDialog(this, "Por Favor insira todos os dados.", "Dados não Informados", JOptionPane.ERROR_MESSAGE);
        } else {
            salvarDespesa();
            JOptionPane.showMessageDialog(this, "Despesa cadastrada com sucesso!", "Despesa Cadastrada", JOptionPane.INFORMATION_MESSAGE);
            textFieldNomeDespesa.setText("");
            textFieldValorUnitario.setText("");
            textFieldValorPago.setText("");
            textFieldObservacao.setText("");
            spinnerQuantidade.setValue(0);
            labelValorTotal.setText("0");
            labelFaltaPagar.setText("0");

            if (flagEdicao) {
                carregarRegistrosTabela();
                flagEdicao = false;
            } else {
                tableModelDespesa.addDespesa(despesa);
            }

            btnDespesa.setText("Cadastrar Despesa");

        }

    }//GEN-LAST:event_btnDespesaActionPerformed

    private void btnProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutosActionPerformed
        dispose();

        ProdutoEventoView produtoEventoView = new ProdutoEventoView(evento, 0);
        produtoEventoView.setLocationRelativeTo(this);
        produtoEventoView.setAlwaysOnTop(true);
        produtoEventoView.setModal(true);
        produtoEventoView.setVisible(true);
        produtoEventoView.returnToParent(true);
    }//GEN-LAST:event_btnProdutosActionPerformed

    private void tableVendasBarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVendasBarMouseClicked
        if (evt.getClickCount() >= 2) {
            dispose();

            int linha = tableVendasBar.getSelectedRow();

            CaixaEvento caixa = caixas.get(linha);
            caixa.setEvento(evento);

            CaixaView caixaView = new CaixaView(caixa);
            caixaView.setLocationRelativeTo(null);
            caixaView.setAlwaysOnTop(true);
            caixaView.setModal(true);
            caixaView.setVisible(true);

        }
    }//GEN-LAST:event_tableVendasBarMouseClicked

    private void tableDespesasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDespesasMouseClicked
        if (evt.getClickCount() >= 2) {
            int linha = tableDespesas.getSelectedRow();

            Despesa d = despesas.get(linha);

            flagEdicao = true;

            textFieldNomeDespesa.setText(d.getNome());
            textFieldObservacao.setText(d.getObservacao());
            spinnerQuantidade.setValue(d.getQuantidade());

            try {
                spinnerQuantidade.commitEdit();
            } catch (ParseException ex) {
                Logger.getLogger(FechamentoView.class.getName()).log(Level.SEVERE, null, ex);
            }

            textFieldValorUnitario.setText(d.getValorUnitario().toString());
            textFieldValorPago.setText(d.getValorPago().toString());

            labelValorTotal.setText(d.getValorUnitario().multiply(new BigDecimal(d.getQuantidade())).toString());
            labelFaltaPagar.setText(new BigDecimal(labelValorTotal.getText()).subtract(d.getValorPago()).toString());

            idDespesa = d.getId();
            btnDespesa.setText("Editar Despesa");

        }
    }//GEN-LAST:event_tableDespesasMouseClicked

    private void spinnerQuantidadeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_spinnerQuantidadeFocusLost

    }//GEN-LAST:event_spinnerQuantidadeFocusLost

    private void textFieldValorUnitarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFieldValorUnitarioFocusLost
        String valorUnitario = textFieldValorUnitario.getText();
        Integer quantidade = (Integer) spinnerQuantidade.getValue();

        labelValorTotal.setText(new BigDecimal(valorUnitario)
                .multiply(new BigDecimal(String.valueOf(quantidade))).toString());

        String valorTotal = labelValorTotal.getText();
        String valorPago = textFieldValorPago.getText();

        if (!valorTotal.isEmpty() && !valorPago.isEmpty()) {
            labelFaltaPagar.setText(new BigDecimal(valorTotal).subtract(new BigDecimal(valorPago)).toString());
        }

    }//GEN-LAST:event_textFieldValorUnitarioFocusLost

    private void textFieldValorPagoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFieldValorPagoFocusLost
        labelFaltaPagar.setText(new BigDecimal(labelValorTotal.getText()).subtract(new BigDecimal(textFieldValorPago.getText())).toString());
    }//GEN-LAST:event_textFieldValorPagoFocusLost

    private void spinnerQuantidadeCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_spinnerQuantidadeCaretPositionChanged

    }//GEN-LAST:event_spinnerQuantidadeCaretPositionChanged

    private void spinnerQuantidadeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerQuantidadeStateChanged
        if (!textFieldValorUnitario.getText().trim().isEmpty()) {

            String valorUnitario = textFieldValorUnitario.getText();
            Integer quantidade = (Integer) spinnerQuantidade.getValue();

            labelValorTotal.setText(new BigDecimal(valorUnitario)
                    .multiply(new BigDecimal(String.valueOf(quantidade))).toString());

            String valorTotal = labelValorTotal.getText();
            String valorPago = textFieldValorPago.getText();

            if (!valorTotal.isEmpty() && !valorPago.isEmpty()) {
                labelFaltaPagar.setText(new BigDecimal(valorTotal).subtract(new BigDecimal(valorPago)).toString());
            }
        }
    }//GEN-LAST:event_spinnerQuantidadeStateChanged

    private void tableVendasBarracasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableVendasBarracasMouseClicked
        if (evt.getClickCount() >= 2) {
            dispose();

            int linha = tableVendasBarracas.getSelectedRow();

            BarracaEvento barraca = barracas.get(linha);
            barraca.setEvento(evento);

            BarracaView barracaView = new BarracaView(barraca);
            barracaView.setLocationRelativeTo(null);
            barracaView.setAlwaysOnTop(true);
            barracaView.setModal(true);
            barracaView.setVisible(true);

        }
    }//GEN-LAST:event_tableVendasBarracasMouseClicked

    private void btnProdutosBarracaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProdutosBarracaActionPerformed
        dispose();

        ProdutoEventoView produtoEventoView = new ProdutoEventoView(evento, 1);
        produtoEventoView.setLocationRelativeTo(this);
        produtoEventoView.setAlwaysOnTop(true);
        produtoEventoView.setModal(true);
        produtoEventoView.setVisible(true);
        produtoEventoView.returnToParent(true);
    }//GEN-LAST:event_btnProdutosBarracaActionPerformed

    private void textFieldBarracaChurrosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textFieldBarracaChurrosFocusLost
        calculaTotalBrutoBar();
    }//GEN-LAST:event_textFieldBarracaChurrosFocusLost

    private void textFieldBarracaChurrosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldBarracaChurrosKeyTyped

    }//GEN-LAST:event_textFieldBarracaChurrosKeyTyped

    private void textFieldBarracaChurrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldBarracaChurrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldBarracaChurrosActionPerformed

    private void btnAdicionarBarracaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarBarracaActionPerformed
        BarracaEvento barracaEvento = new BarracaEvento();
        barracaEvento.setNome("Barraca " + (barracas.size() + 1));
        barracaEvento.setNumero(barracas.size() + 1);

        Evento umEvento = new Evento();
        umEvento.setId(evento.getId());

        barracaEvento.setEvento(umEvento);

        try {
            barracaEventoDAO.salvar(barracaEvento);
        } catch (SQLException ex) {
            Logger.getLogger(NovoEventoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        recarregarTabelaBarraca();
        
    }//GEN-LAST:event_btnAdicionarBarracaActionPerformed

    private void btnAdicionarCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarCaixaActionPerformed
        CaixaEvento caixaEvento = new CaixaEvento();
        caixaEvento.setNome("Caixa " + (caixas.size() + 1));
        caixaEvento.setNumero(caixas.size() + 1);

        Evento umEvento = new Evento();
        umEvento.setId(evento.getId());

        caixaEvento.setEvento(umEvento);

        try {
            caixaEventoDAO.salvar(caixaEvento);
        } catch (SQLException ex) {
            Logger.getLogger(NovoEventoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        recarregarTabelaCaixa();
        
    }//GEN-LAST:event_btnAdicionarCaixaActionPerformed

    private void salvarDespesa() {
        //SALVA PRODUTO
        despesa = new Despesa();
        despesa.setNome(textFieldNomeDespesa.getText().trim());
        despesa.setQuantidade((Integer) spinnerQuantidade.getValue());
        despesa.setValorUnitario(new BigDecimal(Double.valueOf(textFieldValorUnitario.getText())));
        despesa.setValorPago(new BigDecimal(Double.valueOf(textFieldValorPago.getText())));
        despesa.setEvento(evento);
        despesa.setObservacao(textFieldObservacao.getText().trim());

        if (despesaDAO == null) {
            despesaDAO = new DespesaDAO();
        }

        if (flagEdicao && idDespesa != null) {
            despesa.setId(idDespesa);
            try {
                despesaDAO.editar(despesa);
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
//                flagEdicao = false;
                idDespesa = null;
            }
        } else {
            try {
                despesaDAO.salvar(despesa);
            } catch (SQLException ex) {
                Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void carregarRegistrosTabela() {
        try {
            despesas = despesaDAO.listarTodosPorEvento(evento);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }

        tableModelDespesa = new DespesaTableModel(despesas);
        tableDespesas.setModel(tableModelDespesa);
    }

    private void calculaTotalBrutoBar() {

        BigDecimal totalBruto = totalVendaCaixas.add(totalVendaBarracas);

        if (!textFieldBarracaChurros.getText().trim().isEmpty()) {
            totalBruto = totalBruto.add(new BigDecimal(textFieldBarracaChurros.getText().trim()));
        }

        labelTotalBrutoBar.setText(totalBruto.toString());

    }
    
    private void recarregarTabelaCaixa(){
        try {
            //Barracas
            caixas = caixaEventoDAO.listarTodosPorEvento(evento);
        } catch (SQLException ex) {
            Logger.getLogger(FechamentoView.class.getName()).log(Level.SEVERE, null, ex);
        }
                tableModelCaixa = new CaixaTableModel(caixas);
                tableVendasBar.setModel(tableModelCaixa);
                labelVendasBar.setText(String.valueOf(tableModelCaixa.getQtdTotalVendida()));
    }
    
    private void recarregarTabelaBarraca(){
        try {
            //Barracas
            barracas = barracaEventoDAO.listarTodosPorEvento(evento);
        } catch (SQLException ex) {
            Logger.getLogger(FechamentoView.class.getName()).log(Level.SEVERE, null, ex);
        }
                tableModelBarraca = new BarracaTableModel(barracas);
                tableVendasBarracas.setModel(tableModelBarraca);
                labelVendasBarracas.setText(String.valueOf(tableModelBarraca.getQtdTotalVendida()));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarBarraca;
    private javax.swing.JButton btnAdicionarCaixa;
    private javax.swing.JButton btnDespesa;
    private javax.swing.JButton btnProdutos;
    private javax.swing.JButton btnProdutosBarraca;
    private javax.swing.JButton btnRelatorio;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelConsumoCamarim;
    private javax.swing.JLabel labelDespesas;
    private javax.swing.JLabel labelFaltaPagar;
    private javax.swing.JLabel labelRepasseFinal;
    private javax.swing.JLabel labelTotalBrutoBar;
    private javax.swing.JLabel labelValorTotal;
    private javax.swing.JLabel labelVendasBar;
    private javax.swing.JLabel labelVendasBarracas;
    private javax.swing.JSpinner spinnerQuantidade;
    private javax.swing.JTable tableConsumoCamarim;
    private javax.swing.JTable tableDespesas;
    private javax.swing.JTable tableVendasBar;
    private javax.swing.JTable tableVendasBarracas;
    private javax.swing.JTextField textFieldBarracaChurros;
    private javax.swing.JTextField textFieldNomeDespesa;
    private javax.swing.JTextField textFieldObservacao;
    private javax.swing.JTextField textFieldRepasseProducao;
    private javax.swing.JTextField textFieldValorPago;
    private javax.swing.JTextField textFieldValorUnitario;
    // End of variables declaration//GEN-END:variables
}
