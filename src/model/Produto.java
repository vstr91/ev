/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author Almir
 */
@Entity
@Table(name="produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nome;
    private UnidadeVenda tipoUnidade;
    private Integer doses;
    private String observacao;
    
    // 0 = bar | 1 = barraca
    private int tipo;

    public Produto() {
    }

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

    public UnidadeVenda getTipoUnidade() {
        return tipoUnidade;
    }

    public void setTipoUnidade(UnidadeVenda tipoUnidade) {
        this.tipoUnidade = tipoUnidade;
    }

    public Integer getDoses() {
        return doses;
    }

    public void setDoses(Integer doses) {
        this.doses = doses;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
}
