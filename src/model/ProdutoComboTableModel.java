/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import model.dao.ProdutoComboDAO;

/**
 *
 */
public class ProdutoComboTableModel extends AbstractTableModel {

    private List<ProdutoCombo> produtos;
    private String[] colunas = new String[]{"Nome", "Quantidade"};
    ProdutoComboDAO produtoComboDAO = new ProdutoComboDAO();

    /**
     * Creates a new instance of DevmediaTableModel
     */
    public ProdutoComboTableModel(List<ProdutoCombo> produtos) {
        this.produtos = produtos;
    }

    public ProdutoComboTableModel() {
        this.produtos = new ArrayList<ProdutoCombo>();
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

    public void setValueAt(ProdutoCombo aValue, int rowIndex) {
        ProdutoCombo produto = produtos.get(rowIndex);

        produto.setCombo(aValue.getCombo());
        produto.setProduto(aValue.getProduto());
        produto.setQuantidade(aValue.getQuantidade());

//        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ProdutoCombo produto = produtos.get(rowIndex);

        switch (columnIndex) {
//            case 0:
//                produto.setId((Integer) aValue);
//            case 0:
//                produto.setEvento((Evento) aValue);
//            case 1:
//                produto.setProduto((Produto) aValue);
            case 1:
                produto.setQuantidade(Integer.valueOf((String) aValue));
                break;
            default:
                System.err.println("Índice da coluna inválido");
                break;
        }
        
        try {
            produtoComboDAO.editar(produto);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoEventoTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ProdutoCombo produtoSelecionado = produtos.get(rowIndex);
        String valueObject = null;
        switch (columnIndex) {
//            case 0:
//                valueObject = String.valueOf(produtoSelecionado.getId());
//                break;
            case 0:
                valueObject = produtoSelecionado.getProduto().getNome();
                break;
            case 1:
                valueObject = String.valueOf(produtoSelecionado.getQuantidade());
                break;
            default:
                System.err.println("Índice inválido para propriedade do bean ProdutoCombo.class");
        }

        return valueObject;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        switch(columnIndex){
//            case 0:
//                return false;
//            case 1:
//                return true;
            default:
                return false;
        }

    }

    public ProdutoCombo getProduto(int indiceLinha) {
        return produtos.get(indiceLinha);
    }

    public void addProduto(ProdutoCombo p) {
        produtos.add(p);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeProduto(int indiceLinha) {
        produtos.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeProdutos(List<ProdutoCombo> novosProdutos) {

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
