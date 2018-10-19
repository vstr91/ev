/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.math.BigDecimal;

/**
 *
 */
public class ProdutoEvento {

    private Integer id;
    private Produto produto;
    private Evento evento;
    private BigDecimal valorCusto;
    private BigDecimal valorVenda;
    private BigDecimal estoque;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        this.valorCusto = valorCusto;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public BigDecimal getEstoque() {
        return estoque;
    }

    public void setEstoque(BigDecimal estoque) {
        this.estoque = estoque;
    }
    
}
