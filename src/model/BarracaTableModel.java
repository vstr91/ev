/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import utils.FormatUtils;

/**
 *
 */
public class BarracaTableModel extends AbstractTableModel {

    private List<BarracaEvento> barracas;
    private String[] colunas = new String[]{"Barraca", "Quantidade", "Valor"};
    private int qtdTotalVendida = 0;
    private BigDecimal valorTotalVendido = BigDecimal.ZERO;

    public int getQtdTotalVendida() {
        return qtdTotalVendida;
    }

    public void setQtdTotalVendida(int qtdTotalVendida) {
        this.qtdTotalVendida = qtdTotalVendida;
    }

    public BigDecimal getValorTotalVendido() {
        return valorTotalVendido;
    }

    public void setValorTotalVendido(BigDecimal valorTotalVendido) {
        this.valorTotalVendido = valorTotalVendido;
    }    
    

    /**
     * Creates a new instance of DevmediaTableModel
     */
    public BarracaTableModel(List<BarracaEvento> barracas) {
        this.barracas = barracas;
        somarQuantidadeVendida();
        somarValorVendido();
    }

    public BarracaTableModel() {
        this.barracas = new ArrayList<BarracaEvento>();
    }

    public int getRowCount() {
        return barracas.size();
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

    public void setValueAt(BarracaEvento aValue, int rowIndex) {
        BarracaEvento barracaEvento = barracas.get(rowIndex);

        barracaEvento.setId(aValue.getId());
        barracaEvento.setNome(aValue.getNome());
        barracaEvento.setNumero(aValue.getNumero());

//        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        BarracaEvento barracaEvento = barracas.get(rowIndex);

        switch (columnIndex) {
//            case 0:
//                produto.setId((Integer) aValue);
            case 0:
                barracaEvento.setNome(aValue.toString());
            case 1:
                
            case 2:
//                caixaEvento.set
            default:
                System.err.println("Índice da coluna inválido");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        BarracaEvento barracaSelecionada = barracas.get(rowIndex);
        String valueObject = null;
        switch (columnIndex) {
//            case 0:
//                valueObject = String.valueOf(produtoSelecionado.getId());
//                break;
            case 0:
                valueObject = barracaSelecionada.getNome();
                break;
            case 1:
                valueObject = String.valueOf(barracaSelecionada.getTotalVendido()) == "null" ? "0" : String.valueOf(barracaSelecionada.getTotalVendido());
                break;
            case 2:
                valueObject = String.valueOf(barracaSelecionada.getValorTotalVendido()) == "null" ? "R$ 0" : FormatUtils.formataDinheiroExibicao(barracaSelecionada.getValorTotalVendido());               
                break;
            default:
                System.err.println("Índice inválido para propriedade do bean BarracaEvento.class");
        }

        return valueObject;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public BarracaEvento getBarraca(int indiceLinha) {
        return barracas.get(indiceLinha);
    }

    public void addBarraca(BarracaEvento c) {
        barracas.add(c);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeBarraca(int indiceLinha) {
        barracas.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeBarracas(List<BarracaEvento> novasBarracas) {

        int tamanhoAntigo = getRowCount();
        barracas.addAll(novasBarracas);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void limpar() {
        barracas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return barracas.isEmpty();
    }
    
    public void somarQuantidadeVendida(){
        
        for(BarracaEvento barraca : barracas){
            qtdTotalVendida = qtdTotalVendida + barraca.getTotalVendido();
        }
        
    }
    
    public void somarValorVendido(){

        for(BarracaEvento barraca : barracas){
            
            if(barraca.getValorTotalVendido() != null){
                valorTotalVendido.add(barraca.getValorTotalVendido());
            }
            
        }
        
    }

}
