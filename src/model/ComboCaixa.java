/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 */
public class ComboCaixa {
    
    private ComboEvento produto;
    private CaixaEvento caixa;
    private int quantidade;

    public ComboEvento getProduto() {
        return produto;
    }

    public void setProduto(ComboEvento produto) {
        this.produto = produto;
    }

    public CaixaEvento getCaixa() {
        return caixa;
    }

    public void setCaixa(CaixaEvento caixa) {
        this.caixa = caixa;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
