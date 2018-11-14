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
import model.dao.ProdutoCamarimDAO;
import utils.FormatUtils;

/**
 *
 */
public class ProdutoCamarimTableModel extends AbstractTableModel {

    private List<ProdutoCamarim> produtos;
    private String[] colunas = new String[]{"Produto", "Valor Venda", "Avaria", "Produção", "Sócios", "Cacau", "Total", "Valor Total"};
    ProdutoCamarimDAO produtoCamarimDAO = new ProdutoCamarimDAO();

    public ProdutoCamarimTableModel(List<ProdutoCamarim> produtos) {
        this.produtos = produtos;
    }

    public ProdutoCamarimTableModel() {
        this.produtos = new ArrayList<ProdutoCamarim>();
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

    public void setValueAt(ProdutoCamarim aValue, int rowIndex) {
        ProdutoCamarim produto = produtos.get(rowIndex);

        produto.setAvaria(aValue.getAvaria());
        produto.setCacau(aValue.getCacau());
        produto.setProducao(aValue.getProducao());
        produto.setSocios(aValue.getSocios());
        produto.setProduto(aValue.getProduto());

//        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        fireTableCellUpdated(rowIndex, 3);
        fireTableCellUpdated(rowIndex, 4);
        fireTableCellUpdated(rowIndex, 5);
        fireTableCellUpdated(rowIndex, 6);
        fireTableCellUpdated(rowIndex, 7);

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ProdutoCamarim produto = produtos.get(rowIndex);

        String val = (String) aValue;
        
        if(val.matches("[0-9.,]+")){
            switch (columnIndex) {
    //            case 0:
    //                produto.setId((Integer) aValue);
    //            case 0:
    //                produto.setEvento((Evento) aValue);
    //            case 1:
    //                produto.setProduto((Produto) aValue);
                case 2:
                    produto.setAvaria(Integer.valueOf((String) aValue));
                    break;
                case 3:
                    produto.setProducao(Integer.valueOf((String) aValue));
                    break;
                case 4:
                    produto.setSocios(Integer.valueOf((String) aValue));
                    break;
                case 5:
                    produto.setCacau(Integer.valueOf((String) aValue));
                    break;
                default:
                    System.err.println("Índice da coluna inválido");
                    break;
            }
        } else{
            switch (columnIndex) {
    //            case 0:
    //                produto.setId((Integer) aValue);
    //            case 0:
    //                produto.setEvento((Evento) aValue);
    //            case 1:
    //                produto.setProduto((Produto) aValue);
                case 2:
                    produto.setAvaria(0);
                    break;
                case 3:
                    produto.setProducao(0);
                    break;
                case 4:
                    produto.setSocios(0);
                    break;
                case 5:
                    produto.setCacau(0);
                    break;
                default:
                    System.err.println("Índice da coluna inválido");
                    break;
            }
        }
        
        
        
        try {
            produtoCamarimDAO.editar(produto);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoCaixaTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        fireTableCellUpdated(rowIndex, columnIndex);
        fireTableCellUpdated(rowIndex, 6);
        fireTableCellUpdated(rowIndex, 7);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        ProdutoCamarim produtoSelecionado = produtos.get(rowIndex);
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
                valueObject = String.valueOf(produtoSelecionado.getAvaria());
                break;
            case 3:
                valueObject = String.valueOf(produtoSelecionado.getProducao());
                break;
            case 4:
                valueObject = String.valueOf(produtoSelecionado.getSocios());
                break;
            case 5:
                valueObject = String.valueOf(produtoSelecionado.getCacau());
                break;
            case 6:
                valueObject = String
                        .valueOf(produtoSelecionado.getAvaria()+produtoSelecionado.getCacau()+produtoSelecionado.getProducao()+produtoSelecionado.getSocios());
                break;
            case 7:
                Integer totalQuantidade = produtoSelecionado.getAvaria()+produtoSelecionado.getCacau()+produtoSelecionado.getProducao()+produtoSelecionado.getSocios();
                BigDecimal total = produtoSelecionado.getProduto()
                        .getValorVenda().multiply(new BigDecimal(totalQuantidade));
                valueObject = FormatUtils.formataDinheiroExibicao(total);
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
            case 1:
                return false;
            case 2:
            case 3:
            case 4:
            case 5:
                return true;
            default:
                return false;
        }

    }

    public ProdutoCamarim getProduto(int indiceLinha) {
        return produtos.get(indiceLinha);
    }

    public void addProduto(ProdutoCamarim p) {
        produtos.add(p);

        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void removeProduto(int indiceLinha) {
        produtos.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeProdutos(List<ProdutoCamarim> novosProdutos) {

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
