/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Categoria;

/**
 *
 * @author Junior Guilherme
 */
public class CategoriaDAO {
    public void create(Categoria c){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into categoria(descricao) values (?)");
            stmt.setString(1, c.getDescricao());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Categoria adicionada com sucesso.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar.");
        }finally{
            ConexaoBD.closeConnection(con, stmt);
        }
    }
}
