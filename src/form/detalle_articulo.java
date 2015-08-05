/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.mysql.jdbc.Statement;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import prgs.ver_conex;

/**
 *
 * @author Lucas
 */
public class detalle_articulo extends javax.swing.JPanel {


int articulo,posicion;
Boolean existe;
String codigo_art,codigo_descri;
javax.swing.table.DefaultTableModel cursor;
        
DefaultTableModel mbebidas = new DefaultTableModel(){
 @Override
    public boolean isCellEditable(int row, int column) {
    return false;
    }
};


DefaultTableModel mdetalle = new DefaultTableModel(){
 @Override
    public boolean isCellEditable(int row, int column) {
    return false;
    }
};

;

public detalle_articulo() throws SQLException {
        initComponents();
        cursor = (javax.swing.table.DefaultTableModel) gdetalle.getModel();
        cargaarticulo();
        cabeceras();
        cargagrillas();
    }

public int buscaarticulo(String a) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
    String SQL = "SELECT * from articulo where art_descri = '" + cboarticulos.getSelectedItem() +"'" ;
    ver_conex  conn = new ver_conex();
    conn.sentencia = conn.conexion.createStatement();
    conn.resultado = conn.sentencia.executeQuery(SQL);
    conn.resultado.next();
    return(conn.resultado.getInt("art_codigo"));
}

private void cabeceras(){
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    tcr.setHorizontalAlignment(SwingConstants.RIGHT);
    
    mbebidas.addColumn("COD");
    mbebidas.addColumn("DESCRIPCIÓN");
    gbebidas.getColumnModel().getColumn(0).setPreferredWidth(31);
    gbebidas.getColumnModel().getColumn(1).setPreferredWidth(191);
    
    mdetalle.addColumn("COD");
    mdetalle.addColumn("DESCRIPCIÓN");
    mdetalle.addColumn("CANT.");
    gdetalle.getColumnModel().getColumn(0).setPreferredWidth(31);
    gdetalle.getColumnModel().getColumn(1).setPreferredWidth(171);
    gdetalle.getColumnModel().getColumn(2).setCellRenderer(tcr);
}

private void cargagrillas() throws SQLException{
    ver_conex  conn = new ver_conex();
    conn.sentencia = conn.conexion.createStatement();
    conn.resultado = conn.sentencia.executeQuery("SELECT art_codigo,art_descri FROM ARTICULO,CLASIFICACION WHERE articulo.cla_codigo = clasificacion.cla_codigo AND cla_descri LIKE \"BEBIDAS\" AND art_estado LIKE 'AC' ORDER BY art_descri");
    mbebidas.setRowCount(0);
    Object [] fila = new Object[2];
    while (conn.resultado.next()){
        fila[0] = conn.resultado.getObject(1);
        fila[1] = conn.resultado.getObject(2);
        mbebidas.addRow(fila);
    }
    conn.resultado.first();
    
}


    public void cargaarticulo() throws SQLException{
    String SQL = "SELECT * FROM articulo where art_estado like 'AC' and cla_codigo != 1 order by art_descri";
    ver_conex  conn = new ver_conex();
    conn.sentencia = conn.conexion.createStatement();
    conn.resultado = conn.sentencia.executeQuery(SQL);
    cboarticulos.removeAllItems();
    cboarticulos.addItem("");
    while (conn.resultado.next()) {
        cboarticulos.addItem(conn.resultado.getString("art_descri"));
    }
}
     public double Redondear(double numero){
    return Math.rint(numero*100)/100;
}   
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        detalle = new javax.swing.JDialog();
        jLabel15 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cboarticulos = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gdetalle = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gbebidas = new javax.swing.JTable();
        btnlimpiar = new javax.swing.JButton();

        detalle.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        detalle.setTitle("Forma de Cobro");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Cantidad:");

        txtcantidad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtcantidad.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtcantidadMouseClicked(evt);
            }
        });
        txtcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidadActionPerformed(evt);
            }
        });
        txtcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcantidadKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout detalleLayout = new javax.swing.GroupLayout(detalle.getContentPane());
        detalle.getContentPane().setLayout(detalleLayout);
        detalleLayout.setHorizontalGroup(
            detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        detalleLayout.setVerticalGroup(
            detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detalleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel3.setText("ARTICULO:");

        cboarticulos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboarticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboarticulosMouseClicked(evt);
            }
        });
        cboarticulos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboarticulosItemStateChanged(evt);
            }
        });
        cboarticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboarticulosActionPerformed(evt);
            }
        });
        cboarticulos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cboarticulosPropertyChange(evt);
            }
        });
        cboarticulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboarticulosKeyPressed(evt);
            }
        });

        gdetalle.setModel(mdetalle);
        gdetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gdetalleKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(gdetalle);

        jLabel1.setText("BEBIDAS CARGADAS AL COMBO:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );

        gbebidas.setModel(mbebidas);
        gbebidas.setEnabled(false);
        gbebidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gbebidasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(gbebidas);

        btnlimpiar.setText("Limpiar");
        btnlimpiar.setMaximumSize(new java.awt.Dimension(73, 23));
        btnlimpiar.setMinimumSize(new java.awt.Dimension(73, 23));
        btnlimpiar.setPreferredSize(new java.awt.Dimension(73, 23));
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboarticulos, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboarticulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboarticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboarticulosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboarticulosActionPerformed

    private void gdetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gdetalleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
            try {
                codigo_art = mdetalle.getValueAt(gdetalle.getSelectedRow(), 0).toString();
                String cantidad_art = mdetalle.getValueAt(gdetalle.getSelectedRow(), 2).toString();
                
                ver_conex  conn = new ver_conex();
                Statement stmta=null;
                stmta=(Statement) conn.conexion.createStatement();
                String delete="DELETE FROM `detalle_articulo` WHERE `art_cabecera` = "+ articulo +" AND `art_codigo`= "+codigo_art+" AND `det_cantidad` = "+cantidad_art;
                stmta.executeUpdate(delete);
            
                cursor.removeRow(gdetalle.getSelectedRow());
            } catch (SQLException ex) {
                Logger.getLogger(detalle_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            try {                
                codigo_art = mdetalle.getValueAt(gdetalle.getSelectedRow(), 0).toString();
                String cantidad_art = mdetalle.getValueAt(gdetalle.getSelectedRow(), 2).toString();
                
                ver_conex  conn = new ver_conex();
                Statement stmta=null;
                stmta=(Statement) conn.conexion.createStatement();
                String delete="DELETE FROM `detalle_articulo` WHERE `art_cabecera` = "+ articulo +" AND `art_codigo`= "+codigo_art+" AND `det_cantidad` = "+cantidad_art;
                stmta.executeUpdate(delete);
            
                cursor.removeRow(gdetalle.getSelectedRow());
            } catch (SQLException ex) {
                Logger.getLogger(detalle_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_gdetalleKeyPressed

    private void gbebidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gbebidasMouseClicked
        //btngrabar.setEnabled(true);
        String item = "";
        existe = false;
        posicion = 0;

        codigo_art = mbebidas.getValueAt(gbebidas.getSelectedRow(), 0).toString();
        codigo_descri = mbebidas.getValueAt(gbebidas.getSelectedRow(), 1).toString();

        int z = gdetalle.getRowCount();
        for (int a = 0; a < z; a++){
            item  = (String) gdetalle.getValueAt(a, 0);
            if (item.equals(codigo_art)){
                existe = true;
                posicion = a;
                break;
            }
        }
    txtcantidad.setText("");
    txtcantidad.setEnabled(true);
    ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
    detalle.setIconImage(icono.getImage());
    detalle.setTitle("CANTIDAD");
    detalle.pack();
    detalle.setLocationRelativeTo(null);
    detalle.setModal(true);
    txtcantidad.requestFocus();
    detalle.setVisible(true);
    }//GEN-LAST:event_gbebidasMouseClicked

    private void txtcantidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcantidadMouseClicked

    }//GEN-LAST:event_txtcantidadMouseClicked

    private void txtcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidadActionPerformed
        String campo = txtcantidad.getText();
        if (campo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo está vacío, Cargue los datos correctamente");
            return;
        } else {
            try {
                txtcantidad.setText(String.valueOf(Redondear(Float.parseFloat(txtcantidad.getText()))));
                txtcantidad.setEnabled(false);
                //btngrabar.requestFocus();
                detalle.setResizable(false);
                detalle.setVisible(false);
                gdetalle.getModel();
                Object[] datos = new Object[3];
                datos[0] = codigo_art;
                datos[1] = codigo_descri;
                datos[2] = txtcantidad.getText();
                if (existe == false){
                    mdetalle.addRow(datos);
                } else {
                    mdetalle.insertRow(posicion, datos);
                }    
                ver_conex  conn = new ver_conex();
                Statement stmta=null;
                stmta=(Statement) conn.conexion.createStatement();
                String detalle="INSERT INTO `detalle_articulo`(`art_cabecera`,`art_codigo`,`det_cantidad`) VALUES ("+articulo+","+codigo_art+","+txtcantidad.getText()+")";
                stmta.executeUpdate(detalle);
            } catch (SQLException ex) {
                Logger.getLogger(detalle_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtcantidadActionPerformed

    private void txtcantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyPressed

        char c = evt.getKeyChar();
        if (((c > '0') || (c < '9')) || (c == '.')) {
            TimerTask tarea = new TimerTask(){
                public void run() {
                    //precio();
                }
            };
            Timer temp = new Timer();
            temp.schedule(tarea, 50);}
    }//GEN-LAST:event_txtcantidadKeyPressed

    private void txtcantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
            && (c != '.')) {
            evt.consume();
        }
        if (c == '.' && txtcantidad.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtcantidadKeyTyped

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        mdetalle.setRowCount(0);
        cboarticulos.setSelectedItem("");
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void cboarticulosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboarticulosItemStateChanged
        
    }//GEN-LAST:event_cboarticulosItemStateChanged

    private void cboarticulosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboarticulosKeyPressed
String arti= cboarticulos.getSelectedItem().toString();
        if (arti != "" ){
            gbebidas.setEnabled(true);
        } else {
             gbebidas.setEnabled(false);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cboarticulosKeyPressed

    private void cboarticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboarticulosMouseClicked
         // TODO add your handling code here:
    }//GEN-LAST:event_cboarticulosMouseClicked

    private void cboarticulosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cboarticulosPropertyChange
String arti= cboarticulos.getSelectedItem().toString();
        if (arti != "" ){
            try {
                gbebidas.setEnabled(true);
                articulo = buscaarticulo((String) cboarticulos.getSelectedItem());
                
                ver_conex  conn = new ver_conex();
                conn.sentencia = conn.conexion.createStatement();
                conn.resultado = conn.sentencia.executeQuery("SELECT detalle_articulo.art_codigo,art_descri,det_cantidad FROM `detalle_articulo`,articulo WHERE  art_cabecera = articulo.art_codigo AND `art_cabecera` = "+articulo);
                System.out.println("hi");
                if (conn.resultado.first() == true){
                      
                    conn.sentencia = conn.conexion.createStatement();
                    conn.resultado = conn.sentencia.executeQuery("SELECT detalle_articulo.art_codigo,art_descri,det_cantidad FROM `detalle_articulo`,articulo WHERE  art_cabecera = articulo.art_codigo AND `art_cabecera` = "+articulo);

                    mdetalle.setRowCount(0);
                    Object [] fila3 = new Object[3];
                    while (conn.resultado.next()){
                        fila3[0] = conn.resultado.getObject(1).toString();
                        fila3[1] = conn.resultado.getObject(2).toString();
                        fila3[2] = conn.resultado.getObject(3).toString();
                        mdetalle.addRow(fila3);
                        System.out.println("hay");
                    }
                    conn.resultado.first();
                } else {
                    mdetalle.setRowCount(0);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(detalle_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(detalle_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(detalle_articulo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(detalle_articulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
             gbebidas.setEnabled(false);
             mdetalle.setRowCount(0);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_cboarticulosPropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JComboBox cboarticulos;
    private javax.swing.JDialog detalle;
    private javax.swing.JTable gbebidas;
    private javax.swing.JTable gdetalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtcantidad;
    // End of variables declaration//GEN-END:variables
}
