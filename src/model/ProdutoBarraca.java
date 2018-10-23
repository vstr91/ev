/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 */
public class ProdutoBarraca {
    
    private ProdutoEvento produto;
    private BarracaEvento barraca;
    private int quantidade;

    public ProdutoEvento getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEvento produto) {
        this.produto = produto;
    }

    public BarracaEvento getBarraca() {
        return barraca;
    }

    public void setBarraca(BarracaEvento barraca) {
        this.barraca = barraca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
