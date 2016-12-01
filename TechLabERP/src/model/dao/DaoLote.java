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
import javax.swing.JTable;
import model.bean.BeanLote;
import net.proteanit.sql.DbUtils;

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
            stmt = con.prepareStatement("select * from lote;");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                BeanLote l = new BeanLote();
                l.setId_produto(rs.getInt("id_produto"));
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
    public BeanLote readId(int id){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        BeanLote ld = new BeanLote();
        
        try {
            stmt = con.prepareStatement("select P.nome as Produto, L.qtd as Quantidade, L.valor as Valor from produto P " 
                    + " inner join lote L on P.id=L.id_produto " 
                    + " where L.id="+id);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                ld.setNomeProduto(rs.getString("Produto"));
                ld.setQtd(rs.getInt("Quantidade"));
                ld.setTotal(rs.getDouble("Valor"));
                
            }
        } catch (SQLException e) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, stmt, rs);
        }
        return ld;
    }
    public BeanLote readForId(int id){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        BeanLote ld = new BeanLote();
        
        try {
            st = con.prepareStatement("select P.nome, L.qtd, L.data_lote, L.valor, L.data_lote from produto P inner join lote L on P.id=L.id_produto where L.id="+id);
            rs = st.executeQuery();
            
            while(rs.next()){
                ld.setNomeProduto(rs.getString("P.nome"));
                ld.setData(rs.getString("L.id_produto"));
                ld.setQtd(rs.getInt("L.qtd"));
                ld.setTotal(rs.getDouble("L.valor"));
            }
        } catch (Exception e) {
            Logger.getLogger(DaoLote.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, st, rs);
        }
        return ld;
    }
    
    public String dataJava(String data){
        String ano = data.substring(0, 4);
        String mes = data.substring(4, 8);
        String dia = data.substring(8, 10);
        data = dia+mes+ano;
        return data;
    }
    
    public void readJtableRS2(String busca, JTable jt){
        ResultSet rs;
        PreparedStatement st = null;
        Connection con = ConexaoBD.getConnection();
        String sql = "select L.id as Codigo, P.nome as Produto, L.qtd as Quantidade, L.data_lote as Data, L.valor as Valor from lote L " 
                    + "inner join produto P on P.id=L.id_produto " 
                    + "where P.nome like '%"+busca+"%';";
    
        try {
            
            st = con.prepareStatement(sql);
            rs=st.executeQuery();
            jt.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, st);
        }
}
    
    public void readLoteProduto(JTable jt){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement st = null;
        ResultSet rs;
        try {
            st = con.prepareStatement("select L.id as Codigo, P.nome Produto, L.qtd as Quantidade, L.data_lote as Data, L.valor as Valor from lote L"
                    + " inner join produto P"
                    + " on P.id=L.id_produto order by data_lote desc;");
            rs = st.executeQuery();
            jt.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, st);
        }
    }
    public void remove(int id){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement st = null;
        
        try {
            st=con.prepareStatement("delete from lote where id="+id);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DaoLote.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, st);
        }
    }
}
