/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.mysql.jdbc.ResultSet;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import prgs.ver_conex;

/**
 *
 * @author LUke
 */
public class infocc extends javax.swing.JPanel {
DefaultTableModel modelo = new DefaultTableModel(){
     @Override
        public boolean isCellEditable(int row, int column) {
        return false;
    }
};

    public ResultSet resu;
    public String sentencia;
    public String reporte;
    public int cliente;


    public infocc() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        initComponents();
        cabecera();
        cargadatos();
        des_op();
        des_btn();
    }
    
    private void hab_btn(){
    btnnuevo.setEnabled(false);
    btngenerar.setEnabled(false);
    btncancelar.setEnabled(true);
    }
    
    private void des_btn(){
    btnnuevo.setEnabled(true);
    btnnuevo.requestFocus();
    btngenerar.setEnabled(false);
    btncancelar.setEnabled(false);
    }
    
    private void hab_op(){
    op1.setEnabled(true);  
    op2.setEnabled(true);  
    op1.setSelected(false);  
    op2.setSelected(false);  
    }
    
    private void des_op(){
    op1.setEnabled(false);  
    op2.setEnabled(false);  
    op1.setSelected(false);  
    op2.setSelected(false);  
    }

    public void cargadatos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String SQL = "SELECT UCASE(cli_nomape),SUM(`cco_moncom`) AS `cco_moncom`, (SUM(`cco_moncom`)-SUM(`cco_monent`)) AS cc_saldo FROM `cc_test`, cliente WHERE `cc_test`.`cli_codigo` = cliente.`cli_codigo` AND cliente.`cli_nomape` != 'SIN CLIENTE' AND `cco_estado` = 'ACTIVO' GROUP BY `cc_test`.`cli_codigo` ORDER BY cli_nomape";
        ver_conex  conn = new ver_conex();
        conn.sentencia = conn.conexion.createStatement();
        conn.resultado = conn.sentencia.executeQuery(SQL);

        modelo.setRowCount(0);
        Object [] fila = new Object[3];
        while (conn.resultado.next()){
            fila[0] = conn.resultado.getObject(1);
            fila[1] = conn.resultado.getObject(2);
            fila[2] = conn.resultado.getObject(3);
        modelo.addRow(fila);
     }
        conn.resultado.first();
     }
    
    private void cabecera(){
    modelo.addColumn("CLIENTE");
    modelo.addColumn("TOTAL COMPRA");
    modelo.addColumn("TOTAL SALDO");
    cc.getColumnModel().getColumn(0).setPreferredWidth(151);
    cc.getColumnModel().getColumn(1).setPreferredWidth(151);
    cc.getColumnModel().getColumn(2).setPreferredWidth(151);
}
    
    public void gen_reporte() throws JRException {
    try {
    if (op1.isSelected()){
    sentencia = "SELECT cli_nomape,SUM(`cco_moncom`) AS `cco_moncom`,SUM(`cco_monent`) AS `cco_monent`, (SUM(`cco_moncom`)-SUM(`cco_monent`)) AS cco_saldo FROM `cc_test`, cliente WHERE `cc_test`.`cli_codigo` = cliente.`cli_codigo` AND cliente.`cli_nomape` != 'SIN CLIENTE' AND `cco_estado` = 'ACTIVO' GROUP BY `cc_test`.`cli_codigo`";
    } else if (op2.isSelected()){
    sentencia = "SELECT cli_nomape, `cco_moncom`,`cco_monent`, (`cco_moncom`-`cco_monent`) AS cco_saldo,`cco_fecha`,IFNULL(`cco_segun`,CONCAT('cobro',' ',cco_codigo)) as cco_segun FROM `cc_test`, cliente WHERE `cc_test`.`cli_codigo` = cliente.`cli_codigo` AND `cco_estado` = 'ACTIVO' AND cliente.cli_codigo = "+cliente;
    } 
    menu.jgenerador.setModal(false);
    menu.jgenerador.setVisible(false);
    ////////////////////////////////instanciamos
    ver_conex conn =new ver_conex();
    conn.sentencia = conn.conexion.createStatement();
    resu = (com.mysql.jdbc.ResultSet) conn.sentencia.executeQuery(sentencia);//OJO LE PASO LA SENTENCIA
    } catch (Exception ex) {
    }
    JRResultSetDataSource jrRS = new JRResultSetDataSource(resu);
    des_op();
    des_btn();
    cc.setEnabled(false);
    HashMap parameters = new HashMap();
    try{
    ////////////////////////////// preapra el patch
    
    URL urlMaestro = getClass().getClassLoader().getResource("repo/"+reporte+".jasper");
    System.out.println(urlMaestro);
    ////////////////////////////// Cargamos el reporte
    JasperReport masterReport = null;
    masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
    JasperPrint masterPrint = null;
    ////////////////////////////// pasa el patch y paametros
    masterPrint = JasperFillManager.fillReport(masterReport, parameters,jrRS);
    JasperViewer ventana = new JasperViewer(masterPrint,false);
    ventana.setTitle("Vista Previa");
    ventana.setVisible(true);
    ventana.setExtendedState(ventana.MAXIMIZED_BOTH);
    ventana.addWindowListener(new WindowAdapter () {
         public void windowClosing(WindowEvent  e) {
       menu.jgenerador.setModal(true);
       menu.jgenerador.setVisible(true);
    }});
    }catch(JRException e){
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Ocurrio un error "+e.toString(),"Atención ", JOptionPane.INFORMATION_MESSAGE);
    }   
    resu = null;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        cc = new javax.swing.JTable(modelo);
        jPanel1 = new javax.swing.JPanel();
        op1 = new javax.swing.JRadioButton();
        op2 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        btnnuevo = new javax.swing.JButton();
        btngenerar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();

        cc.setEnabled(false);
        cc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ccMouseClicked(evt);
            }
        });
        cc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ccKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(cc);

        op1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        op1.setText("Todas");
        op1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op1ActionPerformed(evt);
            }
        });
        op1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                op1KeyPressed(evt);
            }
        });

        op2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        op2.setText("Una");
        op2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op2ActionPerformed(evt);
            }
        });
        op2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                op2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(op1)
                .addGap(56, 56, 56)
                .addComponent(op2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(op1)
                    .addComponent(op2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnnuevo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnnuevo.setText("Nuevo");
        btnnuevo.setToolTipText("Nuevo Reporte");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });
        btnnuevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnnuevoKeyPressed(evt);
            }
        });

        btngenerar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btngenerar.setText("Generar");
        btngenerar.setToolTipText("Generar Reporte");
        btngenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerarActionPerformed(evt);
            }
        });
        btngenerar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btngenerarKeyPressed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.setToolTipText("Nueva Venta");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        btncancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btncancelarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btngenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btngenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void op1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op1ActionPerformed
op2.setSelected(false);
op2.setEnabled(false);
btngenerar.setEnabled(true);
btngenerar.requestFocus();
reporte = "rcc";
    }//GEN-LAST:event_op1ActionPerformed

    private void op2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op2ActionPerformed
op1.setSelected(false);
op1.setEnabled(false);
cc.setEnabled(true);
cc.requestFocus();
reporte = "rcc_detalle";
    }//GEN-LAST:event_op2ActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        hab_op();
        hab_btn();
        op1.requestFocus();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btngenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerarActionPerformed
        try {
            gen_reporte();
        } catch (JRException ex) {
            Logger.getLogger(infocc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btngenerarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        des_op();
        des_btn();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void ccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ccMouseClicked
        try {
            String Dato = (String) modelo.getValueAt(cc.getSelectedRow(), 0).toString();
            ver_conex conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("select cli_codigo from cliente where cli_nomape = '"+Dato+"'");
            conn.resultado.next();
            cliente = Integer.parseInt(conn.resultado.getString("cli_codigo"));
            btngenerar.setEnabled(true);
            btngenerar.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(infocc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ccMouseClicked

    private void op1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_op1KeyPressed
if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
op1.doClick();
}        // TODO add your handling code here:
    }//GEN-LAST:event_op1KeyPressed

    private void op2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_op2KeyPressed
if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
op2.doClick();
}        // TODO add your handling code here:
    }//GEN-LAST:event_op2KeyPressed

    private void ccKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ccKeyPressed
if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER)
        {
       try {
            String Dato = (String) modelo.getValueAt(cc.getSelectedRow(), 0).toString();
            ver_conex conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("select cli_codigo from cliente where cli_nomape = '"+Dato+"'");
            conn.resultado.next();
            cliente = Integer.parseInt(conn.resultado.getString("cli_codigo"));
            btngenerar.setEnabled(true);
            btngenerar.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(infocc.class.getName()).log(Level.SEVERE, null, ex);
        }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_ccKeyPressed

    private void btnnuevoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnnuevoKeyPressed
  if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.btnnuevo.doClick();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnnuevoKeyPressed

    private void btngenerarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btngenerarKeyPressed
  if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.btngenerar.doClick();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btngenerarKeyPressed

    private void btncancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncancelarKeyPressed
  if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.btncancelar.doClick();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelarKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btngenerar;
    public static javax.swing.JButton btnnuevo;
    private javax.swing.JTable cc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton op1;
    private javax.swing.JRadioButton op2;
    // End of variables declaration//GEN-END:variables
}
