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
public class CaixaTableModel extends AbstractTableModel {

    private List<CaixaEvento> caixas;
    private String[] colunas = new String[]{"Caixa", "Quantidade", "Valor"};
    private int qtdTotalVendida = 0;

    public int getQtdTotalVendida() {
        return qtdTotalVendida;
    }

    public void setQtdTotalVendida(int qtdTotalVendida) {
        this.qtdTotalVendida = qtdTotalVendida;
    }

    /**
     * Creates a new instance of DevmediaTableModel
     */
    public CaixaTableModel(List<CaixaEvento> caixas) {
        this.caixas = caixas;
        somarQuantidadeVendida();
    }

    public CaixaTableModel() {
        this.caixas = new ArrayList<CaixaEvento>();
    }

    public int getRowCount() {
        return caixas.size();
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

    public void setValueAt(CaixaEvento aValue, int rowIndex) {
        CaixaEvento caixaEvento = caixas.get(rowIndex);

        caixaEvento.setId(aValue.getId());
        caixaEvento.setNome(aValue.getNome());
        caixaEvento.setNumero(aValue.getNumero());

//        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CaixaEvento caixaEvento = caixas.get(rowIndex);

        switch (columnIndex) {
//            case 0:
//                produto.setId((Integer) aValue);
            case 0:
                caixaEvento.setNome(aValue.toString());
            case 1:
                
            case 2:
//                caixaEvento.set
            default:
                System.err.println("Índice da coluna inválido");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        CaixaEvento caixaSelecionado = caixas.get(rowIndex);
        String valueObject = null;
        switch (columnIndex) {
//            case 0:
//                valueObject = String.valueOf(produtoSelecionado.getId());
//                break;
            case 0:
                valueObject = caixaSelecionado.getNome();
                break;
            case 1:
                valueObject = "0";
                break;
            case 2:
                valueObject = "0";                
                break;
            default:
                System.err.println("Índice inválido para propriedade do bean CaixaEvento.class");
        }

        return valueObject;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public CaixaEvento getCaixa(int indiceLinha) {
        return caixas.get(indiceLinha);
    }

    public void addCaixa(CaixaEvento c) {
        caixas.add(c);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeCaixa(int indiceLinha) {
        caixas.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeCaixas(List<CaixaEvento> novosCaixas) {

        int tamanhoAntigo = getRowCount();
        caixas.addAll(novosCaixas);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void limpar() {
        caixas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return caixas.isEmpty();
    }
    
    public void somarQuantidadeVendida(){
        
        for(CaixaEvento caixa : caixas){
            qtdTotalVendida = qtdTotalVendida + caixa.getNumero();
        }
        
    }

}
