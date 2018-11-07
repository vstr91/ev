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
    private BigDecimal sobra;
    
    private BigDecimal vendas;

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

    public BigDecimal getSobra() {
        return sobra;
    }

    public void setSobra(BigDecimal sobra) {
        this.sobra = sobra;
    }

    public BigDecimal getVendas() {
        return vendas;
    }

    public void setVendas(BigDecimal vendas) {
        this.vendas = vendas;
    }

    @Override
    public boolean equals(Object obj) {
        
        ProdutoEvento p = (ProdutoEvento) obj;
        
        return this.getId().equals(p.getId());
    }
    
}
