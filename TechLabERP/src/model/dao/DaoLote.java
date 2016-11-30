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
import model.bean.BeanLote;
import views.MetodosGlobais;

/**
 *
 * @author Junior Guilherme
 */
public class DaoLote {
    public void create(BeanLote l){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into lote (id_produto, valor, qtd, data_lote) values (?,?,?,?);");
            stmt.setInt(1, l.getId_produto());
            stmt.setDouble(2, l.getTotal());
            stmt.setInt(3, l.getQtd());
            stmt.setString(4, l.getData());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConexaoBD.closeConnection(con, stmt);
        }
    }
    public List<BeanLote> read(){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BeanLote> list = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("select * from venda;");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                BeanLote l = new BeanLote();
                l.setId_produto(rs.getInt("id_produto"));
                l.setQtd(rs.getInt("qtd"));
                l.setData(rs.getString("data_venda"));
                l.setTotal(rs.getDouble("valor"));
                list.add(l);
                
            }
        } catch (SQLException e) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, stmt, rs);
        }
        return list;
    }
    
    public String dataJava(String data){
        String ano = data.substring(6, 10);
        String mes = data.substring(3, 5);
        String dia = data.substring(0, 2);
        data = dia+"-"+mes+"-"+ano;
        return data;
    }
    
    public List<BeanLote> readLote(){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BeanLote> list = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("select P.nome, L.qtd, L.data_lote, L.valor from lote L"
                    + " inner join roduto P"
                    + " on P.id=L.id_produto order by data_lote desc;");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                BeanLote l = new BeanLote();
                l.setNomeProduto(rs.getString("P.nome"));
                l.setQtd(rs.getInt("L.qtd"));
                l.setData(dataJava(rs.getString("L.data_lote")));
                l.setTotal(rs.getDouble("L.valor"));
                
            }
        } catch (Exception e) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, stmt, rs);
        }
        return list;
    }
    
    public List<BeanLote> readForDesc(String busca){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BeanLote> list = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("select P.nome, L.qtd, L.data_lote, L.valor from lote L " 
                    + "inner join produto P on P.id=L.id_produto " 
                    + "where P.nome like '%"+busca+"%';");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                BeanLote l = new BeanLote();
                l.setNomeProduto(rs.getString("P.nome"));
                l.setQtd(rs.getInt("qtd"));
                l.setData(rs.getString("data_lote"));
                l.setTotal(rs.getDouble("valor"));
                list.add(l);
                
            }
        } catch (SQLException e) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, stmt, rs);
        }
        return list;
    }
   
}
