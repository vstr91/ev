/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import model.dao.ComboEventoDAO;

/**
 *
 */
public class ComboEventoTableModel extends AbstractTableModel {

    private List<ComboEvento> produtos;
    private String[] colunas = new String[]{"Nome", "Valor Custo", "Valor Venda"};
    ComboEventoDAO comboEventoDAO = new ComboEventoDAO();

    /**
     * Creates a new instance of DevmediaTableModel
     */
    public ComboEventoTableModel(List<ComboEvento> produtos) {
        this.produtos = produtos;
    }

    public ComboEventoTableModel() {
        this.produtos = new ArrayList<ComboEvento>();
    }

    public int getRowCount() {
        return produtos.size();
    }

    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public void setValueAt(ComboEvento aValue, int rowIndex) {
        ComboEvento produto = produtos.get(rowIndex);

        produto.setEvento(aValue.getEvento());
        produto.setCombo(aValue.getCombo());
        produto.setValorCusto(aValue.getValorCusto());
        produto.setValorVenda(aValue.getValorVenda());

//        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ComboEvento produto = produtos.get(rowIndex);

        switch (columnIndex) {
//            case 0:
//                produto.setId((Integer) aValue);
//            case 0:
//                produto.setEvento((Evento) aValue);
//            case 1:
//                produto.setProduto((Produto) aValue);
            case 1:
                produto.setValorCusto(BigDecimal.valueOf(Integer.valueOf((String) aValue)));
                break;
            case 2:
                produto.setValorVenda(BigDecimal.valueOf(Integer.valueOf((String) aValue)));
                break;
            default:
                System.err.println("Índice da coluna inválido");
                break;
        }
        
        try {
            comboEventoDAO.editar(produto);
        } catch (SQLException ex) {
            Logger.getLogger(ComboEventoTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fireTableCellUpdated(rowIndex, columnIndex);
        fireTableCellUpdated(rowIndex, 5);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ComboEvento produtoSelecionado = produtos.get(rowIndex);
        String valueObject = null;
        switch (columnIndex) {
//            case 0:
//                valueObject = String.valueOf(produtoSelecionado.getId());
//                break;
            case 0:
                valueObject = produtoSelecionado.getCombo().getNome();
                break;
            case 1:
                valueObject = String.valueOf(produtoSelecionado.getValorCusto());
                break;
            case 2:
                valueObject = String.valueOf(produtoSelecionado.getValorVenda());
                break;
            default:
                System.err.println("Índice inválido para propriedade do bean ComboEvento.class");
        }

        return valueObject;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        switch(columnIndex){
            case 0:
                return false;
            case 1:
            case 2:
                return true;
            default:
                return false;
        }

    }

    public ComboEvento getProduto(int indiceLinha) {
        return produtos.get(indiceLinha);
    }

    public void addProduto(ComboEvento p) {
        produtos.add(p);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeProduto(int indiceLinha) {
        produtos.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeProdutos(List<ComboEvento> novosProdutos) {

        int tamanhoAntigo = getRowCount();
        produtos.addAll(novosProdutos);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void limpar() {
        produtos.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return produtos.isEmpty();
    }

}
