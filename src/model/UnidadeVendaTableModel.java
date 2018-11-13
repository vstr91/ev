/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 *
 */
public class UnidadeVendaTableModel extends AbstractTableModel {

    private List<UnidadeVenda> unidades;
    private String[] colunas = new String[]{"Nome"};

    /**
     * Creates a new instance of DevmediaTableModel
     */
    public UnidadeVendaTableModel(List<UnidadeVenda> unidades) {
        this.unidades = unidades;
    }

    public UnidadeVendaTableModel() {
        this.unidades = new ArrayList<UnidadeVenda>();
    }

    public int getRowCount() {
        return unidades.size();
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

    public void setValueAt(UnidadeVenda aValue, int rowIndex) {
        UnidadeVenda unidade = unidades.get(rowIndex);

        unidade.setId(aValue.getId());
        unidade.setNome(aValue.getNome());

//        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        UnidadeVenda unidade = unidades.get(rowIndex);

        switch (columnIndex) {
//            case 0:
//                produto.setId((Integer) aValue);
            case 0:
                unidade.setNome(aValue.toString());
            default:
                System.err.println("Índice da coluna inválido");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        UnidadeVenda unidadeSelecionada = unidades.get(rowIndex);
        String valueObject = null;
        switch (columnIndex) {
//            case 0:
//                valueObject = String.valueOf(produtoSelecionado.getId());
//                break;
            case 0:
                valueObject = unidadeSelecionada.getNome();
                break;
            default:
                System.err.println("Índice inválido para propriedade do bean UnidadeVenda.class");
        }

        return valueObject;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public UnidadeVenda getUnidade(int indiceLinha) {
        return unidades.get(indiceLinha);
    }

    public void addUnidade(UnidadeVenda e) {
        unidades.add(e);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeUnidade(int indiceLinha) {
        unidades.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeEventos(List<UnidadeVenda> novasUnidades) {

        int tamanhoAntigo = getRowCount();
        unidades.addAll(novasUnidades);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void limpar() {
        unidades.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return unidades.isEmpty();
    }

}
