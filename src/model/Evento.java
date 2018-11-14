/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.math.BigDecimal;
import org.joda.time.DateTime;

/**
 *
 */
public class Evento {

    private int id;
    private String nome;
    private DateTime data;
    private String observacao;
    
    private BigDecimal vendaChurros;
    private BigDecimal patrocinio;
    private BigDecimal vendaComida;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public DateTime getData() {
        return data;
    }

    public void setData(DateTime data) {
        this.data = data;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getVendaChurros() {
        return vendaChurros;
    }

    public void setVendaChurros(BigDecimal vendaChurros) {
        this.vendaChurros = vendaChurros;
    }

    public BigDecimal getPatrocinio() {
        return patrocinio;
    }

    public void setPatrocinio(BigDecimal patrocinio) {
        this.patrocinio = patrocinio;
    }

    public BigDecimal getVendaComida() {
        return vendaComida;
    }

    public void setVendaComida(BigDecimal vendaComida) {
        this.vendaComida = vendaComida;
    }
    
}
