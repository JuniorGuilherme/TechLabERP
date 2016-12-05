/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Junior Guilherme
 */
public class Produto {
    private String nome;
    private int id, idCategoria, qtd, qtd_estoque;
    private double total_investido;

    public double getTotal_investido() {
        return total_investido;
    }

    public void setTotal_investido(double total_investido) {
        this.total_investido = total_investido;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getQtd_estoque() {
        return qtd_estoque;
    }

    public void setQtd_estoque(int qtd_estoque) {
        this.qtd_estoque = qtd_estoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setidCategoria(int id){
        this.idCategoria = id;
    }
    public int getIdCategoria(){
        return idCategoria;
    }
    public String toString(){
        return nome;
    }
}
