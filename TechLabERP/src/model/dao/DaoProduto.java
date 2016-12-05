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
import model.bean.Categoria;
import model.bean.Produto;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author jrg_c
 */
public class DaoProduto {
    
    public void create(Produto P){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("insert into produto(nome, id_cat) values (?,?)");
            stmt.setString(1, P.getNome());
            stmt.setInt(2, P.getIdCategoria());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso.");
        } catch (SQLException ex) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConexaoBD.closeConnection(con, stmt);
        }
    }
    public void readJtable(JTable jt){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> list = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("select P.id as Codigo, P.nome as Descricao, C.descricao as Categoria, P.preco_medio as CustoUnitario, P.qtd as UnidadesCompradas, P.qtd_estoque as Estoque, P.total_investido as Investido from produto P inner join Categoria C on P.id_cat=C.id");
            rs = stmt.executeQuery();
            jt.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, stmt, rs);
        }
    }
    
    public List<Produto> read(){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> list = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement("select P.id as Codigo, P.nome as Descricao, C.descricao as Categoria, P.preco_medio as CustoUnitario, P.qtd as UnidadesCompradas, P.qtd_estoque as Estoque from produto P inner join Categoria C on P.id_cat=C.id");
            rs = stmt.executeQuery();
            while(rs.next()){
                Produto p = new Produto();
                p.setId(rs.getInt("Codigo"));
                p.setNome(rs.getString("Descricao"));
                p.setQtd(rs.getInt("UnidadesCompradas"));
                p.setQtd_estoque(rs.getInt("Estoque"));
                list.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoProduto.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, stmt, rs);
        }
        return list;
    }
    
    public void remove(int id){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement st = null;
        
        try {
            st=con.prepareStatement("delete from produto where id="+id);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DaoLote.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, st);
        }
    }
    public Produto readForId(int id){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        Produto p = new Produto();
        
        try {
            st = con.prepareStatement("select P.id as Codigo, P.nome as Descricao, P.preco_medio as CustoUnitario, P.qtd as UnidadesCompradas, P.qtd_estoque as Estoque, P.total_investido as Investido from produto P where P.id="+id);
            rs = st.executeQuery();
            
            while(rs.next()){
                p.setNome(rs.getString("Descricao"));
                p.setQtd(rs.getInt("UnidadesCompradas"));
                p.setId(rs.getInt("Codigo"));
                p.setQtd_estoque(rs.getInt("Estoque"));
                p.setTotal_investido(rs.getDouble("Investido"));
            }
        } catch (Exception e) {
            Logger.getLogger(DaoLote.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            ConexaoBD.closeConnection(con, st, rs);
        }
        return p;
    }
    public void update(Produto p){
        Connection con = ConexaoBD.getConnection();
        PreparedStatement st = null;
        try {
            st=con.prepareStatement("update produto set nome = ? where id=?;");
            st.setString(1, p.getNome());
            st.setInt(2, p.getId());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso.");
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
        String sql = "select P.id as Codigo, P.nome as Descricao, C.descricao as Categoria, P.preco_medio as CustoUnitario, P.qtd as UnidadesCompradas, P.qtd_estoque as Estoque, P.total_investido as Investido from produto P " 
                    + " inner join categoria C on P.id_cat=C.id" 
                    + " where P.nome like '%"+busca+"%';";
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
    public void readJtableMaisVendidos(JTable jt){
        ResultSet rs;
        PreparedStatement st = null;
        Connection con = ConexaoBD.getConnection();
        String sql = "select P.id as Codigo, P.nome as Descricao, C.descricao as Categoria, P.preco_medio as CustoUnitario, sum(V.qtd) as Vendidos, P.qtd_estoque as Estoque, P.total_investido as Investido from categoria C " 
                    + " inner join produto P on P.id_cat=C.id"
                    + " inner join venda V on V.id_produto=P.id" 
                    + " group by Codigo"
                    + " order by Vendidos desc;";
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
}
