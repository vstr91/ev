/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 */
public class ProdutoTableModel extends AbstractTableModel {

    private List<Produto> produtos;
    private String[] colunas = new String[]{"Nome", "Un. Venda", "Doses", "Observação"};

    /**
     * Creates a new instance of DevmediaTableModel
     */
    public ProdutoTableModel(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public ProdutoTableModel() {
        this.produtos = new ArrayList<Produto>();
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

    public void setValueAt(Produto aValue, int rowIndex) {
        Produto produto = produtos.get(rowIndex);

        produto.setId(aValue.getId());
        produto.setNome(aValue.getNome());
        produto.setTipoUnidade(aValue.getTipoUnidade());
        produto.setDoses(aValue.getDoses());
        produto.setObservacao(aValue.getObservacao());

//        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        fireTableCellUpdated(rowIndex, 3);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);

        switch (columnIndex) {
//            case 0:
//                produto.setId((Integer) aValue);
            case 0:
                produto.setNome(aValue.toString());
            case 1:
                produto.setTipoUnidade((UnidadeVenda) aValue);
            case 2:
                produto.setDoses((Integer) aValue);
            case 3:
                produto.setObservacao(aValue.toString());
            default:
                System.err.println("Índice da coluna inválido");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produtoSelecionado = produtos.get(rowIndex);
        String valueObject = null;
        switch (columnIndex) {
//            case 0:
//                valueObject = String.valueOf(produtoSelecionado.getId());
//                break;
            case 0:
                valueObject = produtoSelecionado.getNome();
                break;
            case 1:
                valueObject = produtoSelecionado.getTipoUnidade().getNome();
                break;
            case 2:
                if(produtoSelecionado.getTipoUnidade().getNome().equals("Dose")){
                    valueObject = String.valueOf(produtoSelecionado.getDoses());
                } else{
                    valueObject = "-";
                }
                
                break;
            case 3:
                valueObject = produtoSelecionado.getObservacao();
                break;
            default:
                System.err.println("Índice inválido para propriedade do bean Produto.class");
        }

        return valueObject;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Produto getProduto(int indiceLinha) {
        return produtos.get(indiceLinha);
    }

    public void addProduto(Produto p) {
        produtos.add(p);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeProduto(int indiceLinha) {
        produtos.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeProdutos(List<Produto> novosProdutos) {

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
