/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 */
public class ProdutoCamarim {
    
    private ProdutoEvento produto;
    private int avaria;
    private int producao;
    private int socios;
    private int cacau;

    public ProdutoEvento getProduto() {
        return produto;
    }

    public void setProduto(ProdutoEvento produto) {
        this.produto = produto;
    }

    public int getAvaria() {
        return avaria;
    }

    public void setAvaria(int avaria) {
        this.avaria = avaria;
    }

    public int getProducao() {
        return producao;
    }

    public void setProducao(int producao) {
        this.producao = producao;
    }

    public int getSocios() {
        return socios;
    }

    public void setSocios(int socios) {
        this.socios = socios;
    }

    public int getCacau() {
        return cacau;
    }

    public void setCacau(int cacau) {
        this.cacau = cacau;
    }
    
}
