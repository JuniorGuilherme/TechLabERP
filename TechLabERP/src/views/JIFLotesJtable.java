/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import connection.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.bean.BeanLote;
import model.bean.Produto;
import model.dao.DaoLote;
import model.dao.DaoProduto;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Junior Guilherme
 */
public final class JIFLotesJtable extends javax.swing.JInternalFrame {
    DaoLote dao = new DaoLote();
    /**
     * Creates new form JIFLotesJtable
     */
    public JIFLotesJtable() {
        initComponents();
        dao.readLoteProduto(jtLote);
        jtLote.isCellEditable(0, 0);
    }
    
    public boolean isValidDate(String date, String format) {         
	SimpleDateFormat dateFormat = new SimpleDateFormat(format);     
	dateFormat.setLenient(false);                                   
	try {                                                           
		dateFormat.parse(date);                                     
		return true;                                                
	} catch (ParseException e) {  
                JOptionPane.showMessageDialog(null, "Data invalida.");
		return false;                                               
	}                                                               
}   public boolean isValidNumbers(int qtd, double valor){
    if(qtd>=0 && valor>=0){
        return true;
    }
    else{
        JOptionPane.showMessageDialog(null, "Não informe numeros negativos nos campos 'Quantidade' e 'Valor'.");
        return false;
    }
    }
    public void limpaCampos(){
        jfData.setText("");
        txtQTD.setText("");
        txtValor.setText("");
        txtID.setText("");
    }
    public String dataSql(String data){
        String ano = data.substring(6, 10);
        String mes = data.substring(2, 6);
        String dia = data.substring(0, 2);
        data = ano+mes+dia;
        return data;
    }
    public String dataJava(String data){
        String ano = data.substring(0, 4);
        String mes = data.substring(4, 8);
        String dia = data.substring(8, 10);
        data = dia+mes+ano;
        return data;
    }
    public void confirmaUpdate(BeanLote l){
        l.setQtd(Integer.parseInt(txtQTD.getText()));
        l.setTotal(Double.parseDouble(txtValor.getText()));
        l.setId(Integer.parseInt(txtID.getText()));
        l.setNomeProduto(jtLote.getValueAt(jtLote.getSelectedRow(), 1).toString());
        int reply = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente atualizar este lote com estas informações?\n ID Lote: " + l.getId() + " \nProduto: " + l.getNomeProduto() + "\nQuantidade: " + String.valueOf(l.getQtd()) + "\nValor: " + String.valueOf(l.getTotal()) + "\nData: " + dataJava(l.getDataSql().toString()));
            if (reply == JOptionPane.YES_OPTION) {
                dao.update(l);
                dao.readLoteProduto(jtLote);;
                limpaCampos();
            }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAtualizarLote = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtBusca = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txtValor = new javax.swing.JTextField();
        txtQTD = new javax.swing.JTextField();
        btnRemoverLote = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtLote = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        ckbHoje = new javax.swing.JCheckBox();
        jfData = new javax.swing.JFormattedTextField();

        setClosable(true);

        btnAtualizarLote.setText("Atualizar");
        btnAtualizarLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarLoteActionPerformed(evt);
            }
        });

        jLabel2.setText("Quantidade");

        jLabel3.setText("Valor");

        jLabel4.setText("Data");

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnRemoverLote.setText("Remover");
        btnRemoverLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverLoteActionPerformed(evt);
            }
        });

        jtLote.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Produto", "Quantidade", "Title 3", "Title 4"
            }
        ));
        jtLote.setShowHorizontalLines(false);
        jtLote.setShowVerticalLines(false);
        jtLote.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtLoteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtLote);

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ID");

        txtID.setEditable(false);

        ckbHoje.setText("Hoje");

        try {
            jfData.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-##-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAtualizarLote)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemoverLote)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtQTD)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addComponent(jfData))
                                .addGap(19, 19, 19)
                                .addComponent(ckbHoje))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtQTD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ckbHoje)
                            .addComponent(jfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAtualizarLote)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)
                        .addComponent(btnRemoverLote)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dao.readJtableRS2(txtBusca.getText(), jtLote);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnAtualizarLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarLoteActionPerformed
        // TODO add your handling code here:
        BeanLote l = new BeanLote();
        DaoLote dao = new DaoLote();
       
        if (ckbHoje.isSelected()) {
            Date dataA = new Date(System.currentTimeMillis());
            l.setDataSql(dataA);
            if (isValidNumbers(Integer.parseInt(txtQTD.getText()), Double.parseDouble(txtValor.getText()))) {
                confirmaUpdate(l);
            }
        } else {
            if (isValidDate(jfData.getText(), "dd-MM-yyyy") && isValidNumbers(Integer.parseInt(txtQTD.getText()), Double.parseDouble(txtValor.getText()))) {
                Date dataA;
                dataA = Date.valueOf(dataSql(jfData.getText()));
                l.setDataSql(dataA);
                confirmaUpdate(l);
            }
        }
    }//GEN-LAST:event_btnAtualizarLoteActionPerformed

    private void jtLoteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtLoteMouseClicked
        // TODO add your handling code here:
        if(jtLote.getSelectedRow()!= -1){
            txtQTD.setText(jtLote.getValueAt(jtLote.getSelectedRow(), 2).toString());
            txtValor.setText(jtLote.getValueAt(jtLote.getSelectedRow(), 4).toString());
            jfData.setText(jtLote.getValueAt(jtLote.getSelectedRow(), 3).toString());
            txtID.setText(jtLote.getValueAt(jtLote.getSelectedRow(), 0).toString());
        }
    }//GEN-LAST:event_jtLoteMouseClicked

    private void btnRemoverLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverLoteActionPerformed
        // TODO add your handling code here:
        BeanLote l;
        l=dao.readId(Integer.parseInt(txtID.getText()));
        String data = jtLote.getValueAt(jtLote.getSelectedRow(), 3).toString();
        l.setData(dao.dataJava(data));
        int reply = JOptionPane.showConfirmDialog(rootPane, "Deseja realmente remover este lote? \nProduto: "+l.getNomeProduto()+"\nQuantidade: "+String.valueOf(l.getQtd())+"\nValor:"+String.valueOf(l.getTotal())+"\nData: "+l.getData());
        if(reply==JOptionPane.YES_OPTION){
            dao.remove(Integer.parseInt(txtID.getText()));
            dao.readLoteProduto(jtLote);
            limpaCampos();
        }
    }//GEN-LAST:event_btnRemoverLoteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizarLote;
    private javax.swing.JButton btnRemoverLote;
    private javax.swing.JCheckBox ckbHoje;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField jfData;
    private javax.swing.JTable jtLote;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtQTD;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
