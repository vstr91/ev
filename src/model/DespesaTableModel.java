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

/**
 *
 */
public class DespesaTableModel extends AbstractTableModel {

    private List<Despesa> despesas;
    private String[] colunas = new String[]{"Nome", "Valor Unitário", "Quantidade", "Valor Total", "Valor Pago", "Falta Pagar", "Observação"};
    private BigDecimal valorTotalDespesas = BigDecimal.ZERO;

    public BigDecimal getValorTotalDespesas() {
        return valorTotalDespesas;
    }

    public void setValorTotalDespesas(BigDecimal valorTotalDespesas) {
        this.valorTotalDespesas = valorTotalDespesas;
    }

    /**
     * Creates a new instance of DevmediaTableModel
     */
    public DespesaTableModel(List<Despesa> despesas) {
        this.despesas = despesas;
        somarValorDespesas();
    }

    public DespesaTableModel() {
        this.despesas = new ArrayList<Despesa>();
        somarValorDespesas();
    }

    public int getRowCount() {
        return despesas.size();
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

    public void setValueAt(Despesa aValue, int rowIndex) {
        Despesa despesa = despesas.get(rowIndex);

        despesa.setId(aValue.getId());
        despesa.setNome(aValue.getNome());
        despesa.setValorUnitario(aValue.getValorUnitario());
        despesa.setQuantidade(aValue.getQuantidade());
        despesa.setValorPago(aValue.getValorPago());
        despesa.setObservacao(aValue.getObservacao());
        despesa.setEvento(aValue.getEvento());

//        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        fireTableCellUpdated(rowIndex, 3);
        fireTableCellUpdated(rowIndex, 4);
        fireTableCellUpdated(rowIndex, 5);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Despesa despesa = despesas.get(rowIndex);

        switch (columnIndex) {
//            case 0:
//                produto.setId((Integer) aValue);
            case 0:
                despesa.setNome(aValue.toString());
            case 1:
                despesa.setValorUnitario(new BigDecimal((String) aValue));
            case 2:
                despesa.setQuantidade((Integer) aValue);
            case 4:
                despesa.setValorPago(new BigDecimal((String) aValue));
            case 6:
                despesa.setObservacao(aValue.toString());
            default:
                System.err.println("Índice da coluna inválido");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Despesa despesaSelecionado = despesas.get(rowIndex);
        String valueObject = null;
        switch (columnIndex) {
//            case 0:
//                valueObject = String.valueOf(produtoSelecionado.getId());
//                break;
            case 0:
                valueObject = despesaSelecionado.getNome();
                break;
            case 1:
                valueObject = despesaSelecionado.getValorUnitario().toString();
                break;
            case 2:
                valueObject = String.valueOf(despesaSelecionado.getQuantidade());
                break;
            case 3:
                valueObject = String.valueOf(despesaSelecionado.getValorUnitario().multiply(new BigDecimal(despesaSelecionado.getQuantidade())));
                break;
            case 4:
                valueObject = despesaSelecionado.getValorPago().toString();
                break;
            case 5:
                valueObject = despesaSelecionado.getValorUnitario().multiply(new BigDecimal(despesaSelecionado.getQuantidade()))
                        .subtract(despesaSelecionado.getValorPago()).toString();
                break;
            case 6:
                valueObject = despesaSelecionado.getObservacao();
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

    public Despesa getDespesa(int indiceLinha) {
        return despesas.get(indiceLinha);
    }

    public void addDespesa(Despesa d) {
        despesas.add(d);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeDespesa(int indiceLinha) {
        despesas.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeDespesas(List<Despesa> novasDespesas) {

        int tamanhoAntigo = getRowCount();
        despesas.addAll(novasDespesas);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void limpar() {
        despesas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return despesas.isEmpty();
    }
    
    public void somarValorDespesas(){
        
        for(Despesa despesa : despesas){
            
            if(despesa.getValorUnitario() != null){
                BigDecimal val = despesa.getValorUnitario().multiply(new BigDecimal(despesa.getQuantidade())).subtract(despesa.getValorPago());
                valorTotalDespesas = valorTotalDespesas.add(val);
            }
            
        }
        
    }

}
