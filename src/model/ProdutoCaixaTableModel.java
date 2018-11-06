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
import model.dao.ProdutoCaixaDAO;
import utils.FormatUtils;

/**
 *
 */
public class ProdutoCaixaTableModel extends AbstractTableModel {

    private List<ProdutoCaixa> produtos;
    private String[] colunas = new String[]{"Produto", "Valor Venda", "Quantidade", "Total"};
    ProdutoCaixaDAO produtoCaixaDAO = new ProdutoCaixaDAO();

    public ProdutoCaixaTableModel(List<ProdutoCaixa> produtos) {
        this.produtos = produtos;
    }

    public ProdutoCaixaTableModel() {
        this.produtos = new ArrayList<ProdutoCaixa>();
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

    public void setValueAt(ProdutoCaixa aValue, int rowIndex) {
        ProdutoCaixa produto = produtos.get(rowIndex);

        produto.setCaixa(aValue.getCaixa());
        produto.setProduto(aValue.getProduto());
        produto.setQuantidade(aValue.getQuantidade());

//        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        fireTableCellUpdated(rowIndex, 3);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ProdutoCaixa produto = produtos.get(rowIndex);

        switch (columnIndex) {
//            case 0:
//                produto.setId((Integer) aValue);
//            case 0:
//                produto.setEvento((Evento) aValue);
//            case 1:
//                produto.setProduto((Produto) aValue);
            case 2:
                produto.setQuantidade(Integer.valueOf((String) aValue));
                break;
            default:
                System.err.println("Índice da coluna inválido");
                break;
        }
        
        try {
            produtoCaixaDAO.editar(produto);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoCaixaTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fireTableCellUpdated(rowIndex, columnIndex);
        fireTableCellUpdated(rowIndex, 3);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ProdutoCaixa produtoSelecionado = produtos.get(rowIndex);
        String valueObject = null;
        switch (columnIndex) {
//            case 0:
//                valueObject = String.valueOf(produtoSelecionado.getId());
//                break;
            case 0:
                valueObject = produtoSelecionado.getProduto().getProduto().getNome();
                break;
            case 1:
                valueObject = FormatUtils.formataDinheiroExibicao(produtoSelecionado.getProduto().getValorVenda());
                break;
            case 2:
                valueObject = String.valueOf(produtoSelecionado.getQuantidade());
                break;
            case 3:
                BigDecimal total = produtoSelecionado.getProduto()
                        .getValorVenda().multiply(new BigDecimal(produtoSelecionado.getQuantidade()));
                valueObject = FormatUtils.formataDinheiroExibicao(total);
                break;
            default:
                System.err.println("Índice inválido para propriedade do bean ProdutoCaixa.class");
        }

        return valueObject;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        
        switch(columnIndex){
            case 0:
            case 1:
                return false;
            case 2:
                return true;
            default:
                return false;
        }

    }

    public ProdutoCaixa getProduto(int indiceLinha) {
        return produtos.get(indiceLinha);
    }

    public void addProduto(ProdutoCaixa p) {
        produtos.add(p);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeProduto(int indiceLinha) {
        produtos.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeProdutos(List<ProdutoCaixa> novosProdutos) {

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
