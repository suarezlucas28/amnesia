/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import prgs.ver_conex;

/**
 *
 * @author LUke
 */
public class anularcobros extends javax.swing.JPanel {
DefaultTableModel modelo = new DefaultTableModel(){
     @Override
        public boolean isCellEditable(int row, int column) {
        return false;
    }
};

    public anularcobros() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        initComponents();
        CabDetalle();
        cargadatos();
    }

private void CabDetalle(){
modelo.addColumn("CÓDIGO");
modelo.addColumn("CLIENTE");
modelo.addColumn("FECHA Y HORA");
grilla.getColumnModel().getColumn(0).setPreferredWidth(31);
grilla.getColumnModel().getColumn(1).setPreferredWidth(151);
grilla.getColumnModel().getColumn(2).setPreferredWidth(151);
}

public void cargadatos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
String SQL = "SELECT `cco_codigo`,cli_nomape,`cco_fecha` FROM `cc_test`,cliente WHERE `cc_test`.`cli_codigo` = cliente.`cli_codigo` AND cco_estado LIKE 'ACTIVO' AND `cco_segun` IS NULL";
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnanular = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        grilla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblcodigo = new javax.swing.JTextField();
        lblcliente = new javax.swing.JTextField();
        lblhorafecha = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(317, 484));
        setMinimumSize(new java.awt.Dimension(317, 484));
        setPreferredSize(new java.awt.Dimension(317, 484));

        btnanular.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnanular.setText("Anular");
        btnanular.setToolTipText("Anular Cobro");
        btnanular.setEnabled(false);
        btnanular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnanularActionPerformed(evt);
            }
        });
        btnanular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnanularKeyPressed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.setToolTipText("Cancelar Anulación");
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnanular, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnanular, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        grilla.setModel(modelo);
        grilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grillaMouseClicked(evt);
            }
        });
        grilla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                grillaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(grilla);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Fecha y hora:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Cobro:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Cliente:");

        lblcodigo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblcodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblcodigo.setEnabled(false);

        lblcliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblcliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblcliente.setEnabled(false);

        lblhorafecha.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblhorafecha.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblhorafecha.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(lblcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblhorafecha, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(lblcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblhorafecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        try {
            btnanular.setEnabled(false);
            lblcodigo.setText("");
            lblcliente.setText("");
            lblhorafecha.setText("");
            modelo.setRowCount(0);
            cargadatos();
        } catch (SQLException ex) {
            Logger.getLogger(anularcobros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(anularcobros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(anularcobros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(anularcobros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btncancelarActionPerformed

    private void grillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grillaMouseClicked
 String Dato = (String) modelo.getValueAt(grilla.getSelectedRow(),0).toString();
 String Dato1 = (String) modelo.getValueAt(grilla.getSelectedRow(),1).toString();
 String Dato2 = (String) modelo.getValueAt(grilla.getSelectedRow(),2).toString();
 lblcodigo.setText(Dato);
 lblcliente.setText(Dato1);
 lblhorafecha.setText(Dato2);
 JOptionPane.showMessageDialog(null, "Para anular presione Anular", "Atención", JOptionPane.INFORMATION_MESSAGE);
 btnanular.setEnabled(true);
 btnanular.requestFocus();
    }//GEN-LAST:event_grillaMouseClicked

    private void btnanularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnanularActionPerformed
        try {
            ver_conex  conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            conn.sentencia.executeUpdate("UPDATE `cc_test` SET `cco_estado` = 'ANULADO' WHERE `cco_codigo` = "+lblcodigo.getText()+""); //OJO LE PASO LA SENTENCIA  
            JOptionPane.showMessageDialog(null, "El registro se ha anulado correctamente", "Atención", JOptionPane.INFORMATION_MESSAGE);
            btncancelar.doClick();
        } catch (SQLException ex) {
            Logger.getLogger(anularcobros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnanularActionPerformed

    private void btnanularKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnanularKeyPressed
if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.btnanular.doClick();
        }
    }//GEN-LAST:event_btnanularKeyPressed

    private void btncancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncancelarKeyPressed
if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.btncancelar.doClick();
        }
    }//GEN-LAST:event_btncancelarKeyPressed

    private void grillaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grillaKeyPressed
if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER)
        {
 String Dato = (String) modelo.getValueAt(grilla.getSelectedRow(),0).toString();
 String Dato1 = (String) modelo.getValueAt(grilla.getSelectedRow(),1).toString();
 String Dato2 = (String) modelo.getValueAt(grilla.getSelectedRow(),2).toString();
 lblcodigo.setText(Dato);
 lblcliente.setText(Dato1);
 lblhorafecha.setText(Dato2);
 JOptionPane.showMessageDialog(null, "Para anular presione Anular", "Atención", JOptionPane.INFORMATION_MESSAGE);
 btnanular.setEnabled(true);
 btnanular.requestFocus();
        }
    }//GEN-LAST:event_grillaKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnanular;
    private javax.swing.JButton btncancelar;
    public static javax.swing.JTable grilla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lblcliente;
    private javax.swing.JTextField lblcodigo;
    private javax.swing.JTextField lblhorafecha;
    // End of variables declaration//GEN-END:variables
}
