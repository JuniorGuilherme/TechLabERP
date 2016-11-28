/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.BeanVenda;

/**
 *
 * @author Junior Guilherme
 */
public class DaoVenda {
    public void create(BeanVenda v){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into venda_itens(idProduto, valor, qtd) values (?,?,?)");
            stmt.setInt(1, v.getIdProduto());
            stmt.setDouble(2, v.getValor());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConexaoBD.closeConnection(con, stmt);
        }
    }
    public List<BeanVenda> read(){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BeanVenda> list = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("select * from produto");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Venda v = new Produto();
                v.setNome(rs.getString("nome"));
                p.setId(rs.getInt("id"));
                list.add(p);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler.");
        }finally{
            ConexaoBD.closeConnection(con, stmt, rs);
        }
        return list;
    }
}
