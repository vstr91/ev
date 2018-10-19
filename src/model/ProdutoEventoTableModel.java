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
import model.dao.ProdutoDAO;
import model.dao.ProdutoEventoDAO;

/**
 *
 */
public class ProdutoEventoTableModel extends AbstractTableModel {

    private List<ProdutoEvento> produtos;
    private String[] colunas = new String[]{"Nome", "Valor Custo", "Valor Venda", "Estoque"};
    ProdutoEventoDAO produtoEventoDAO = new ProdutoEventoDAO();

    /**
     * Creates a new instance of DevmediaTableModel
     */
    public ProdutoEventoTableModel(List<ProdutoEvento> produtos) {
        this.produtos = produtos;
    }

    public ProdutoEventoTableModel() {
        this.produtos = new ArrayList<ProdutoEvento>();
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

    public void setValueAt(ProdutoEvento aValue, int rowIndex) {
        ProdutoEvento produto = produtos.get(rowIndex);

        produto.setEvento(aValue.getEvento());
        produto.setProduto(aValue.getProduto());
        produto.setValorCusto(aValue.getValorCusto());
        produto.setValorVenda(aValue.getValorVenda());
        produto.setEstoque(aValue.getEstoque());

//        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        fireTableCellUpdated(rowIndex, 3);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ProdutoEvento produto = produtos.get(rowIndex);

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
            case 3:
                produto.setEstoque(BigDecimal.valueOf(Integer.valueOf((String) aValue)));
                break;
            default:
                System.err.println("Índice da coluna inválido");
                break;
        }
        
        try {
            produtoEventoDAO.editar(produto);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoEventoTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ProdutoEvento produtoSelecionado = produtos.get(rowIndex);
        String valueObject = null;
        switch (columnIndex) {
//            case 0:
//                valueObject = String.valueOf(produtoSelecionado.getId());
//                break;
            case 0:
                valueObject = produtoSelecionado.getProduto().getNome();
                break;
            case 1:
                valueObject = String.valueOf(produtoSelecionado.getValorCusto());
                break;
            case 2:
                valueObject = String.valueOf(produtoSelecionado.getValorVenda());
                break;
            case 3:
                valueObject = String.valueOf(produtoSelecionado.getEstoque());
                break;
            default:
                System.err.println("Índice inválido para propriedade do bean ProdutoEvento.class");
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
            case 3:
                return true;
            default:
                return false;
        }

    }

    public ProdutoEvento getProduto(int indiceLinha) {
        return produtos.get(indiceLinha);
    }

    public void addProduto(ProdutoEvento p) {
        produtos.add(p);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeProduto(int indiceLinha) {
        produtos.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeProdutos(List<ProdutoEvento> novosProdutos) {

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
