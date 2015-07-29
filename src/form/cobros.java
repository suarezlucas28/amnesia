/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.mysql.jdbc.Statement;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import prgs.data;
import prgs.ver_conex;

/**
 *
 * @author LUke
 */

public class cobros extends javax.swing.JPanel {
data mostra_data;
float punitario,cmonto,newsaldo,entrega;
int stock,ccodigo,cliente,permiso;
String fechahora,tipoc,Fecha,ctipo;
Boolean ok = null;
DefaultTableModel modelo = new DefaultTableModel(){
     @Override
        public boolean isCellEditable(int row, int column) {
        return false;
    }
};

    public cobros() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        initComponents();
        mostra_data = new data();
        mostra_data.le_data();
        timer2.start();
        cabecera();
        cargadatos();
        btnregistrar.setEnabled(false);
    }
    
    public void cargadatos() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        String SQL = "SELECT cli_nomape,(SUM(`cco_moncom`)-SUM(`cco_monent`)) AS cco_saldo FROM `cc_test`, cliente WHERE `cc_test`.`cli_codigo` = cliente.`cli_codigo` AND `cco_estado` = 'ACTIVO' and cliente.cli_nomape != 'SIN CLIENTE' GROUP BY `cc_test`.`cli_codigo` HAVING (SUM(`cco_moncom`)-SUM(`cco_monent`)) > 0 ORDER BY cli_nomape ";
        ver_conex  conn = new ver_conex();
        conn.sentencia = conn.conexion.createStatement();
        conn.resultado = conn.sentencia.executeQuery(SQL);

        modelo.setRowCount(0);
        Object [] fila = new Object[2];
        while (conn.resultado.next()){
            fila[0] = conn.resultado.getObject(1);
            fila[1] = conn.resultado.getObject(2);
        modelo.addRow(fila);
     }
        conn.resultado.first();
     }
    
    private void cabecera(){
    modelo.addColumn("CLIENTE");
    modelo.addColumn("TOTAL SALDO");
    cc.getColumnModel().getColumn(0).setPreferredWidth(151);
    cc.getColumnModel().getColumn(1).setPreferredWidth(151);
}

private void lim_text(){
txtsaldo.setText("");
txtcliente.setText("");
txtentrega.setText("");
}
  

public int buscacliente(String a) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
String SQL = "SELECT * FROM cliente WHERE cli_nomape ='" + a +"'";
ver_conex  conn = new ver_conex();
conn.sentencia = conn.conexion.createStatement();
conn.resultado = conn.sentencia.executeQuery(SQL);
conn.resultado.next();
return(conn.resultado.getInt("cli_codigo"));
}

public void Fecha(String fecha){
Calendar c = new GregorianCalendar();
String dia, mes, annio,hora,minuto,segundo;
dia = Integer.toString(c.get(Calendar.DATE));
mes = Integer.toString(c.get(Calendar.MONTH)+1);
annio = Integer.toString(c.get(Calendar.YEAR));
hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
minuto = Integer.toString(c.get(Calendar.MINUTE));
segundo = Integer.toString(c.get(Calendar.SECOND));
fecha = (annio + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":" + segundo);
fechahora = fecha;
Fecha = (annio + "-" + mes + "-" + dia);
}

private void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
try{
Fecha("Fecha");
String SQL1 = "INSERT INTO `cc_test` (`cli_codigo`,`cco_monent`,`cco_fecha`) VALUES ("+cliente+","+String.valueOf(Float.parseFloat(txtentrega.getText()))+",'"+fechahora+"')";   
ver_conex conn =new ver_conex();//instanciamos
conn.sentencia = conn.conexion.createStatement();
conn.sentencia.executeUpdate(SQL1);
conn.sentencia.close();
JOptionPane.showMessageDialog(null, "El registro se ha grabado satisfactoriamente.", "", JOptionPane.INFORMATION_MESSAGE);
 }catch (SQLException exceptionSql)
{
JOptionPane.showMessageDialog(null, exceptionSql.getMessage(), "Error de Conexion con la Base de Datos", JOptionPane.ERROR_MESSAGE);
}
}


public double Redondear(double numero)
{
 return Math.rint(numero*100)/100;
}
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timer2 = new org.netbeans.examples.lib.timerbean.Timer();
        jgenerador = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cc = new javax.swing.JTable(modelo);
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JTextField();
        txtsaldo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtentrega = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        txthora = new javax.swing.JTextField();
        btnregistrar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();

        timer2.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                timer2OnTime(evt);
            }
        });

        javax.swing.GroupLayout jgeneradorLayout = new javax.swing.GroupLayout(jgenerador.getContentPane());
        jgenerador.getContentPane().setLayout(jgeneradorLayout);
        jgeneradorLayout.setHorizontalGroup(
            jgeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jgeneradorLayout.setVerticalGroup(
            jgeneradorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setMaximumSize(new java.awt.Dimension(650, 320));
        setMinimumSize(new java.awt.Dimension(650, 320));
        setPreferredSize(new java.awt.Dimension(650, 320));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Hora y Fecha:");

        cc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ccMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ccMouseEntered(evt);
            }
        });
        cc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ccKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(cc);

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Saldo:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Cliente:");

        txtcliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtcliente.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcliente.setEnabled(false);

        txtsaldo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtsaldo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtsaldo.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Entrega:");

        txtentrega.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtentrega.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtentrega.setEnabled(false);
        txtentrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtentregaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtsaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtentrega, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtsaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtentrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imag/Refresh.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jButton6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton6KeyPressed(evt);
            }
        });

        txthora.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txthora.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txthora.setEnabled(false);

        btnregistrar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnregistrar.setText("Grabar");
        btnregistrar.setToolTipText("");
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });
        btnregistrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnregistrarKeyPressed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btncancelar.setText("Limpiar");
        btncancelar.setToolTipText("");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txthora))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnregistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txthora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void timer2OnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timer2OnTime
        mostra_data.le_hora();
        txthora.setText(mostra_data.ano+"/"+mostra_data.mes+"/"+mostra_data.dia+" " + mostra_data.hora);    
    }//GEN-LAST:event_timer2OnTime
  
    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        try {
            cargadatos();
            lim_text();
            txtentrega.setEnabled(false);
            btnregistrar.setEnabled(false);
        } catch (SQLException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed
        try {
           String campo=txtentrega.getText();
            if (campo.isEmpty()) {
             JOptionPane.showMessageDialog(this,"El campo está vacío, Cargue los datos correctamente");
             return;
            } else {
            grabar();
            btncancelar.doClick();
            } 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnregistrarActionPerformed

    private void ccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ccMouseClicked
try {
            String Dato = (String) modelo.getValueAt(cc.getSelectedRow(), 0).toString();
            ver_conex conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("select cli_codigo from cliente where cli_nomape = '"+Dato+"'");
            conn.resultado.next();
            cliente = Integer.parseInt(conn.resultado.getString("cli_codigo"));
            txtcliente.setText(Dato);
            txtsaldo.setText((String) modelo.getValueAt(cc.getSelectedRow(), 1).toString());
            txtentrega.setEnabled(true);
            btnregistrar.setEnabled(true);
            txtentrega.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ccMouseClicked

    private void txtentregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtentregaActionPerformed
String campo=txtentrega.getText();
if (campo.isEmpty()) {
 JOptionPane.showMessageDialog(this,"El campo está vacío, Cargue los datos correctamente");
 return;
} else {
 txtentrega.setText(String.valueOf(Redondear(Float.parseFloat(txtentrega.getText()))));
 txtentrega.setEnabled(false);
  if (ctipo.equals("EFECTIVO") && (Float.parseFloat(txtsaldo.getText()) < Float.parseFloat(txtentrega.getText()))){
  JOptionPane.showMessageDialog(this,"Debera dar vuelto de ->" + (Redondear(Float.parseFloat(txtsaldo.getText()) - Float.parseFloat(txtentrega.getText()))));  
  entrega = Float.parseFloat(txtsaldo.getText());
  btnregistrar.setEnabled(true);
  btnregistrar.requestFocus();
 } else {
  entrega = Float.parseFloat(txtentrega.getText());
  btnregistrar.setEnabled(true);
  btnregistrar.requestFocus(); 
 }
}         
    }//GEN-LAST:event_txtentregaActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            modelo.setRowCount(0);
            cargadatos();
        } catch (SQLException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnregistrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnregistrarKeyPressed
       if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.btnregistrar.doClick();
        }
    }//GEN-LAST:event_btnregistrarKeyPressed

    private void btncancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncancelarKeyPressed
if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.btncancelar.doClick();
        }
    }//GEN-LAST:event_btncancelarKeyPressed

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
            txtcliente.setText(Dato);
            txtsaldo.setText((String) modelo.getValueAt(cc.getSelectedRow(), 1).toString());
            txtentrega.setEnabled(true);
            btnregistrar.setEnabled(true);
            txtentrega.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(cobros.class.getName()).log(Level.SEVERE, null, ex);
        }

        }
    }//GEN-LAST:event_ccKeyPressed

    private void ccMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ccMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ccMouseEntered

    private void jButton6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton6KeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.jButton6.doClick();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6KeyPressed

     public double Redondear(Double numero)
{
       return Math.rint(numero*100)/100;
}    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnregistrar;
    private javax.swing.JTable cc;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JDialog jgenerador;
    private org.netbeans.examples.lib.timerbean.Timer timer2;
    private javax.swing.JTextField txtcliente;
    private javax.swing.JTextField txtentrega;
    private javax.swing.JTextField txthora;
    private javax.swing.JTextField txtsaldo;
    // End of variables declaration//GEN-END:variables
}
