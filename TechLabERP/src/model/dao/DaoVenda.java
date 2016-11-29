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
            stmt = con.prepareStatement("insert into venda (id_produto, valor, qtd, data_venda) values (?,?,?,?);");
            stmt.setInt(1, v.getIdProduto());
            stmt.setDouble(2, v.getValor());
            stmt.setInt(3, v.getQtd());
            stmt.setString(4, v.getData());
            
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
                BeanVenda v = new BeanVenda();
                v.setIdProduto(rs.getInt("id_produto"));
                v.setQtd(rs.getInt("qtd"));
                v.setData(rs.getString("data_venda"));
                v.setValor(rs.getDouble("valor"));
                list.add(v);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler.");
        }finally{
            ConexaoBD.closeConnection(con, stmt, rs);
        }
        return list;
    }
}
