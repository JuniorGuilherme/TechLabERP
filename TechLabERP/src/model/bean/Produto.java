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
    private int id, idCategoria;

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
