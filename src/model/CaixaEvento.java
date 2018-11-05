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
public class CaixaEvento {
    
    private Integer id;
    private String nome;
    private Integer numero;
    private Evento evento;
    
    private Integer totalVendido;
    private BigDecimal valorTotalVendido;
    
    private BigDecimal vendaDebito;
    private BigDecimal vendaCredito;
    private BigDecimal vendaDinheiro;
    private BigDecimal vendaVale;

    public Integer getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Integer totalVendido) {
        this.totalVendido = totalVendido;
    }

    public BigDecimal getValorTotalVendido() {
        return valorTotalVendido;
    }

    public void setValorTotalVendido(BigDecimal valorTotalVendido) {
        this.valorTotalVendido = valorTotalVendido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public BigDecimal getVendaDebito() {
        return vendaDebito;
    }

    public void setVendaDebito(BigDecimal vendaDebito) {
        this.vendaDebito = vendaDebito;
    }

    public BigDecimal getVendaCredito() {
        return vendaCredito;
    }

    public void setVendaCredito(BigDecimal vendaCredito) {
        this.vendaCredito = vendaCredito;
    }

    public BigDecimal getVendaDinheiro() {
        return vendaDinheiro;
    }

    public void setVendaDinheiro(BigDecimal vendaDinheiro) {
        this.vendaDinheiro = vendaDinheiro;
    }

    public BigDecimal getVendaVale() {
        return vendaVale;
    }

    public void setVendaVale(BigDecimal vendaVale) {
        this.vendaVale = vendaVale;
    }

    @Override
    public boolean equals(Object obj) {
        
        CaixaEvento ev = (CaixaEvento) obj;
        
        return this.getId().equals(ev.getId());
    }
    
}
