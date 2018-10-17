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
public class EventoTableModel extends AbstractTableModel {

    private List<Evento> eventos;
    private String[] colunas = new String[]{"Nome", "Data", "Observação"};

    /**
     * Creates a new instance of DevmediaTableModel
     */
    public EventoTableModel(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public EventoTableModel() {
        this.eventos = new ArrayList<Evento>();
    }

    public int getRowCount() {
        return eventos.size();
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

    public void setValueAt(Evento aValue, int rowIndex) {
        Evento evento = eventos.get(rowIndex);

        evento.setId(aValue.getId());
        evento.setNome(aValue.getNome());
        evento.setData(aValue.getData());
        evento.setObservacao(aValue.getObservacao());

//        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        fireTableCellUpdated(rowIndex, 3);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Evento evento = eventos.get(rowIndex);

        switch (columnIndex) {
//            case 0:
//                produto.setId((Integer) aValue);
            case 0:
                evento.setNome(aValue.toString());
            case 1:
                evento.setData((DateTime)aValue);
            case 3:
                evento.setObservacao(aValue.toString());
            default:
                System.err.println("Índice da coluna inválido");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Evento eventoSelecionado = eventos.get(rowIndex);
        String valueObject = null;
        switch (columnIndex) {
//            case 0:
//                valueObject = String.valueOf(produtoSelecionado.getId());
//                break;
            case 0:
                valueObject = eventoSelecionado.getNome();
                break;
            case 1:
                valueObject = DateTimeFormat.forPattern("dd/MM/YYYY").print(eventoSelecionado.getData());
                break;
            case 2:
                valueObject = eventoSelecionado.getObservacao();
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

    public Evento getEvento(int indiceLinha) {
        return eventos.get(indiceLinha);
    }

    public void addEvento(Evento e) {
        eventos.add(e);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeEvento(int indiceLinha) {
        eventos.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeEventos(List<Evento> novosEventos) {

        int tamanhoAntigo = getRowCount();
        eventos.addAll(novosEventos);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void limpar() {
        eventos.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return eventos.isEmpty();
    }

}
