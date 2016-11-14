package techlaberp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jrg_c
 */
public class ConexaoBD {
    Connection c;
    Statement stmp = null;
    
    public void getConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:techlab.db");
        }catch (Exception ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        try{
            c.close();
        }catch(SQLException ex){
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void criarBanco() {
        try {
            stmp = c.createStatement();
            String sql = "create table cliente"
                    + "( id serial primary key,"
                    + "nome text not null,"
                    + "fone char(13) not null,"
                    + "email text not null);";

            stmp.executeUpdate(sql);
            stmp.close();
        }catch(SQLException ex){
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void createProdutos(){
        try {
            stmp = c.createStatement();
            String sql = "create table produtos"
                        +"(id serial primary key,"
                        +"descricao varchar(45));";
            stmp.execute(sql);
            stmp.close();
                       
        }catch(SQLException ex){
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertProdutos(String nome){
        try {
            stmp = c.createStatement();
            String sql = "insert into produtos(descricao) values (" + nome + ")";
            stmp.execute(sql);
            stmp.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
