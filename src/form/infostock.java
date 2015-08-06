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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import prgs.ver_conex;

public class infostock extends javax.swing.JPanel {

    DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    public ResultSet resu;
    public String sentencia;
    public String reporte;
    public String codigo;

    public infostock() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        initComponents();
        cabecera();
        des_op();
        des_btn();
    }

    private void hab_btn() {
        btnnuevo.setEnabled(false);
        btngenerar.setEnabled(false);
        btncancelar.setEnabled(true);
    }

    private void des_btn() {
        btnnuevo.setEnabled(true);
        btnnuevo.requestFocus();
        btngenerar.setEnabled(false);
        btncancelar.setEnabled(false);
    }

    private void hab_op() {
        op1.setEnabled(true);
        op1.setSelected(false);
        op5.setEnabled(true);
        op5.setSelected(false);
    }

    private void des_op() {
        op1.setEnabled(false);
        op1.setSelected(false);
        op5.setEnabled(false);
        op5.setSelected(false);
    }

    public void cargadatos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String SQL = null;
        if (op5.isSelected()) {
            SQL = "select art_codigo,art_descri from articulo where art_codigo != 0 and cla_codigo = 1 and art_estado like 'AC' group by art_descri";
        }
        ver_conex conn = new ver_conex();
        conn.sentencia = conn.conexion.createStatement();
        conn.resultado = conn.sentencia.executeQuery(SQL);

        modelo.setRowCount(0);
        Object[] fila = new Object[2];
        while (conn.resultado.next()) {
            fila[0] = conn.resultado.getObject(1);
            fila[1] = conn.resultado.getObject(2);
            modelo.addRow(fila);
        }
        conn.resultado.first();
    }

    private void cabecera() {
        modelo.addColumn("CÓDIGO");
        modelo.addColumn("DESCRIPCIÓN");
        cc.getColumnModel().getColumn(0).setPreferredWidth(151);
        cc.getColumnModel().getColumn(1).setPreferredWidth(151);
    }

    public void gen_reporte() throws JRException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            if (op1.isSelected()) {
                sentencia = "SELECT 'TODOS' AS dep_descri,articulo.`art_descri` AS articulo,clasificacion.`cla_descri` AS clasificacion,SUM(stock.`art_canact`) AS stockactual,SUM(stock.`art_canven`) AS vendido FROM `articulo` articulo INNER JOIN `stock` stock ON articulo.`art_codigo` = stock.`art_codigo` INNER JOIN `clasificacion` clasificacion ON articulo.`cla_codigo` = clasificacion.`cla_codigo` GROUP BY articulo.`art_descri` HAVING articulo.`art_descri` != '' ORDER BY articulo.`art_descri`";
            } else if (op5.isSelected()) {
                sentencia = "SELECT 'TODOS' AS dep_descri,articulo.`art_descri` AS articulo,clasificacion.`cla_descri` AS clasificacion,SUM(stock.`art_canact`) AS stockactual,SUM(stock.`art_canven`) AS vendido FROM `articulo` articulo INNER JOIN `stock` stock ON articulo.`art_codigo` = stock.`art_codigo` INNER JOIN `clasificacion` clasificacion ON articulo.`cla_codigo` = clasificacion.`cla_codigo` AND stock.art_codigo = " + codigo + " ORDER BY dep_descri";
            } 
            menu.jgenerador.setModal(false);
            menu.jgenerador.setVisible(false);
            ////////////////////////////////instanciamos
            ver_conex conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            resu = (com.mysql.jdbc.ResultSet) conn.sentencia.executeQuery(sentencia);//OJO LE PASO LA SENTENCIA
        } catch (Exception ex) {
        }
        JRResultSetDataSource jrRS = new JRResultSetDataSource(resu);
        des_op();
        des_btn();
        modelo.setRowCount(0);
        cc.setEnabled(false);
        HashMap parameters = new HashMap();
        try {
            ////////////////////////////// preapra el patch
            URL urlMaestro = getClass().getClassLoader().getResource("repo/" + reporte + ".jasper");
            ////////////////////////////// Cargamos el reporte
            JasperReport masterReport = null;
            masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
            JasperPrint masterPrint = null;
            ////////////////////////////// pasa el patch y paametros
            masterPrint = JasperFillManager.fillReport(masterReport, parameters, jrRS);
            JasperViewer ventana = new JasperViewer(masterPrint, false);
            ventana.setTitle("Vista Previa");
            ventana.setVisible(true);
            ventana.setExtendedState(ventana.MAXIMIZED_BOTH);
            ventana.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    menu.jgenerador.setModal(true);
                    menu.jgenerador.setVisible(true);
                }
            });
        } catch (JRException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio un error " + e.toString(), "Atención ", JOptionPane.INFORMATION_MESSAGE);
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
        op5 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        btnnuevo = new javax.swing.JButton();
        btngenerar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(382, 310));
        setMinimumSize(new java.awt.Dimension(382, 310));
        setPreferredSize(new java.awt.Dimension(382, 310));

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
        op1.setText("Todos");
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

        op5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        op5.setText("Artículo");
        op5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                op5ActionPerformed(evt);
            }
        });
        op5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                op5KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(op1)
                .addGap(55, 55, 55)
                .addComponent(op5)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(op1)
                    .addComponent(op5))
                .addContainerGap(24, Short.MAX_VALUE))
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
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnnuevoKeyTyped(evt);
            }
        });

        btngenerar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btngenerar.setText("Generar");
        btngenerar.setToolTipText("Generar Reporte");
        btngenerar.setMaximumSize(new java.awt.Dimension(63, 23));
        btngenerar.setMinimumSize(new java.awt.Dimension(63, 23));
        btngenerar.setPreferredSize(new java.awt.Dimension(63, 23));
        btngenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerarActionPerformed(evt);
            }
        });
        btngenerar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btngenerarKeyTyped(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.setToolTipText("Nueva Venta");
        btncancelar.setMaximumSize(new java.awt.Dimension(63, 23));
        btncancelar.setMinimumSize(new java.awt.Dimension(63, 23));
        btncancelar.setPreferredSize(new java.awt.Dimension(63, 23));
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        btncancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btncancelarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btngenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnnuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btngenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void op1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op1ActionPerformed
        op5.setSelected(false);
        op5.setEnabled(false);
        btngenerar.setEnabled(true);
        btngenerar.requestFocus();
        reporte = "rstock";
    }//GEN-LAST:event_op1ActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        hab_op();
        hab_btn();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btngenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerarActionPerformed
        try {
            try {
                gen_reporte();
            } catch (SQLException ex) {
                Logger.getLogger(infostock.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(infostock.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(infostock.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(infostock.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JRException ex) {
            Logger.getLogger(infostock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btngenerarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed

        des_op();
        des_btn();
        modelo.setRowCount(0);
    }//GEN-LAST:event_btncancelarActionPerformed

    private void ccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ccMouseClicked
        codigo = (String) modelo.getValueAt(cc.getSelectedRow(), 0).toString();
        btngenerar.setEnabled(true);
        btngenerar.requestFocus();
    }//GEN-LAST:event_ccMouseClicked

    private void btnnuevoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnnuevoKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            this.btnnuevo.doClick();
        }
    }//GEN-LAST:event_btnnuevoKeyTyped

    private void btngenerarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btngenerarKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            this.btngenerar.doClick();
        }
    }//GEN-LAST:event_btngenerarKeyTyped

    private void btncancelarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncancelarKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            this.btncancelar.doClick();
        }
    }//GEN-LAST:event_btncancelarKeyTyped

    private void op1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_op1KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            op1.doClick();
        }
    }//GEN-LAST:event_op1KeyPressed

    private void ccKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ccKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            codigo = (String) modelo.getValueAt(cc.getSelectedRow(), 0).toString();
            btngenerar.setEnabled(true);
            btngenerar.requestFocus();
        }
    }//GEN-LAST:event_ccKeyPressed

    private void op5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_op5ActionPerformed
        try {
            op1.setSelected(false);
            op1.setEnabled(false);
            cc.setEnabled(true);
            cc.requestFocus();
            reporte = "rstock";
            cargadatos();
        } catch (SQLException ex) {
            Logger.getLogger(infostock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(infostock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(infostock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(infostock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_op5ActionPerformed

    private void op5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_op5KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            op5.doClick();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_op5KeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btngenerar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JTable cc;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton op1;
    private javax.swing.JRadioButton op5;
    // End of variables declaration//GEN-END:variables
}
