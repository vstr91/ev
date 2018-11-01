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
public class ComboTableModel extends AbstractTableModel {

    private List<Combo> combos;
    private String[] colunas = new String[]{"Nome"};

    /**
     * Creates a new instance of DevmediaTableModel
     */
    public ComboTableModel(List<Combo> combos) {
        this.combos = combos;
    }

    public ComboTableModel() {
        this.combos = new ArrayList<Combo>();
    }

    public int getRowCount() {
        return combos.size();
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

    public void setValueAt(Combo aValue, int rowIndex) {
        Combo combo = combos.get(rowIndex);

        combo.setId(aValue.getId());
        combo.setNome(aValue.getNome());

//        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Combo combo = combos.get(rowIndex);

        switch (columnIndex) {
//            case 0:
//                produto.setId((Integer) aValue);
            case 0:
                combo.setNome(aValue.toString());
            default:
                System.err.println("Índice da coluna inválido");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Combo comboSelecionado = combos.get(rowIndex);
        String valueObject = null;
        switch (columnIndex) {
//            case 0:
//                valueObject = String.valueOf(produtoSelecionado.getId());
//                break;
            case 0:
                valueObject = comboSelecionado.getNome();
                break;
            default:
                System.err.println("Índice inválido para propriedade do bean Evento.class");
        }

        return valueObject;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Combo getCombo(int indiceLinha) {
        return combos.get(indiceLinha);
    }

    public void addCombo(Combo e) {
        combos.add(e);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeCombo(int indiceLinha) {
        combos.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeEventos(List<Combo> novosCombos) {

        int tamanhoAntigo = getRowCount();
        combos.addAll(novosCombos);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void limpar() {
        combos.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return combos.isEmpty();
    }

}
