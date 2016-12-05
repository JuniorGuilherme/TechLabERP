/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.bean.BeanVenda;
import net.proteanit.sql.DbUtils;

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
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConexaoBD.closeConnection(con, stmt);
        }
    }
    public List<BeanVenda> read(JTable jt){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BeanVenda> list = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("select * from venda;");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                BeanVenda v = new BeanVenda();
                v.setIdProduto(rs.getInt("id_produto"));
                v.setQtd(rs.getInt("qtd"));
                v.setData(rs.getString("data_venda"));
                v.setValor(rs.getDouble("valor"));
                v.setCusto(rs.getDouble("custo"));
                v.setLucro(v.getValor()-v.getCusto());
                list.add(v);
                
            }
        } catch (Exception e) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, stmt, rs);
        }
        return list;
    }
    
    public String dataJava(String data){
        String ano = data.substring(0, 4);
        String mes = data.substring(4, 8);
        String dia = data.substring(8, 10);
        data = dia+mes+ano;
        return data;
    }
    
    public List<BeanVenda> readVenda(){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BeanVenda> list = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("select P.nome, V.qtd, V.data_venda, V.valor, VI.custo_unit from venda_info VI"
                    + " inner join venda V"
                    + " on V.id=VI.id_venda"
                    + " inner join produto P"
                    + " on V.id_produto=P.id"
                    + " order by V.data_venda desc;");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                BeanVenda v = new BeanVenda();
                v.setNomeProduto(rs.getString("P.nome"));
                v.setQtd(rs.getInt("qtd"));
                v.setData(dataJava(rs.getString("data_venda")));
                v.setValor(rs.getDouble("valor"));
                v.setCusto(rs.getDouble("custo_unit"));
                v.setLucro(v.getValor()-v.getCusto());
                list.add(v);
                
            }
        } catch (Exception e) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, stmt, rs);
        }
        return list;
    }
        public BeanVenda readForId(int id){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        BeanVenda v = new BeanVenda();
        
        try {
            st = con.prepareStatement("select P.nome, V.qtd, V.valor, V.data_venda from produto P inner join venda V on P.id=V.id_produto where V.id="+id);
            rs = st.executeQuery();
            
            while(rs.next()){
                v.setNomeProduto(rs.getString("P.nome"));
                v.setQtd(rs.getInt("V.qtd"));
                v.setData(rs.getString("V.data_venda"));
                v.setValor(rs.getDouble("V.valor"));
                
            }
        } catch (Exception e) {
            Logger.getLogger(DaoLote.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, st, rs);
        }
        return v;
    }
    public void readVendaJIF(JTable jt){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<BeanVenda> list = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("select V.id as Codigo, P.nome as Produto, V.qtd as Quantidade, V.data_venda as Data, V.valor as Valor, VI.custo_unit as Custo, V.valor-VI.custo_unit as Lucro from venda_info VI"
                    + " inner join venda V"
                    + " on V.id=VI.id_venda"
                    + " inner join produto P"
                    + " on V.id_produto=P.id"
                    + " order by V.data_venda desc;");
            rs = stmt.executeQuery();
            jt.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, stmt, rs);
        }
    }
    public void remove(int id){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement st = null;
        
        try {
            st=con.prepareStatement("delete from venda where id="+id);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DaoLote.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, st);
        }
    }
    public void readJtableRS2(String busca, JTable jt){
        ResultSet rs;
        PreparedStatement st = null;
        Connection con = ConexaoBD.getConnection();
        String sql = "select V.id as Codigo, P.nome as Produto, V.qtd as Quantidade, V.data_venda as Data, V.valor as Valor, VI.custo_unit as Custo, V.valor-VI.custo_unit as Lucro from venda_info VI"
                    + " inner join venda V"
                    + " on V.id=VI.id_venda"
                    + " inner join produto P"
                    + " on V.id_produto=P.id"
                    + " where P.nome like '%"+busca+"%'"
                    + " order by V.data_venda desc;";
        try {
            
            st = con.prepareStatement(sql);
            rs=st.executeQuery();
            jt.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, st);
        }
    }
    public void update(BeanVenda v){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement st = null;
        try {
            st=con.prepareStatement("update venda set qtd = ?, valor=?, data_venda=? where id=?;");
            st.setInt(1, v.getQtd());
            st.setDouble(2, v.getValor());
            st.setDate(3, v.getDataSql());
            st.setInt(4, v.getId());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso.");
        } catch (SQLException e) {
            Logger.getLogger(DaoLote.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, st);
        }
    }
}
