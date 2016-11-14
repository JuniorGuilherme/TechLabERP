/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techlaberp;

/**
 *
 * @author jrg_c
 */
public class Produto {
    String nome;
    int codigo;
    
    public void insert(){
        ConexaoBD bd = new ConexaoBD();
        bd.criarBanco();
        bd.getConnection();
    }
}
