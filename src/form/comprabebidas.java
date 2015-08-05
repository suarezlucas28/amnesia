/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import com.mysql.jdbc.Statement;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import prgs.data;
import prgs.ver_conex;

/**
 *
 * @author Lucas
 */
public class comprabebidas extends javax.swing.JPanel {
data mostra_data;
int proveedor,posicion;
Boolean existe;
int codigo_ticket;
String Fecha,fechahora;
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

    public comprabebidas() throws SQLException {
        initComponents();
        cursor = (javax.swing.table.DefaultTableModel) gdetalle.getModel();
        mostra_data = new data();
        mostra_data.le_data();
        timer2.start();
        cargaproveedor();
        cabeceras();
        cargagrillas();
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
    mdetalle.addColumn("P. UNITARIO");
    mdetalle.addColumn("CANT.");
    mdetalle.addColumn("P. TOTAL");
    gdetalle.getColumnModel().getColumn(0).setPreferredWidth(31);
    gdetalle.getColumnModel().getColumn(1).setPreferredWidth(171);
    gdetalle.getColumnModel().getColumn(2).setCellRenderer(tcr);
    gdetalle.getColumnModel().getColumn(3).setCellRenderer(tcr);
    gdetalle.getColumnModel().getColumn(4).setCellRenderer(tcr);
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

public void cargaproveedor() throws SQLException{
    String SQL = "SELECT * FROM proveedor order by pro_nombre";
    ver_conex  conn = new ver_conex();
    conn.sentencia = conn.conexion.createStatement();
    conn.resultado = conn.sentencia.executeQuery(SQL);
    cboproveedor.removeAllItems();
    while (conn.resultado.next()) {
        cboproveedor.addItem(conn.resultado.getString("pro_nombre"));
    }
}

 public int buscaproveedor(String a) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
    String SQL = "SELECT * from proveedor where pro_nomape = '" + cboproveedor.getSelectedItem() +"'" ;
    ver_conex  conn = new ver_conex();
    conn.sentencia = conn.conexion.createStatement();
    conn.resultado = conn.sentencia.executeQuery(SQL);
    conn.resultado.next();
    return(conn.resultado.getInt("pro_codigo"));
}
 
 public double Redondear(double numero){
    return Math.rint(numero*100)/100;
}
 
 private void recorrergrilla () throws SQLException {
    javax.swing.table.TableModel model=gdetalle.getModel();
    int c = 0;
    float total_1 = 0;
    for (int i = 0; i < model.getRowCount();i++) {
     java.lang.String value=(String)model.getValueAt(i,4);
     Float ccc = Float.parseFloat(value);
     if (ccc != 0 ) {
      total_1=total_1+ccc;
      c++;
     }
    }
    DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
    simbolo.setGroupingSeparator('.');
    DecimalFormat formateador = new DecimalFormat("###,###.##",simbolo);
    txttotal.setText(formateador.format(total_1));
    txttotal.setHorizontalAlignment(SwingConstants.RIGHT); 
}
 
 public void Fecha(String fecha){
    Calendar c = new GregorianCalendar();
    String dia, mes, annio,hora,minuto,segundo;
    dia = Integer.toString(c.get(Calendar.DATE));
    int a = Integer.parseInt(dia);
    if (a <= 9)dia = "0"+dia;
    mes = Integer.toString(c.get(Calendar.MONTH)+1);
    int b = Integer.parseInt(mes);
    if (b <= 9)mes = "0"+mes;
    annio = Integer.toString(c.get(Calendar.YEAR));
    hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
    minuto = Integer.toString(c.get(Calendar.MINUTE));
    segundo = Integer.toString(c.get(Calendar.SECOND));
    fecha = (annio + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":" + segundo);
    fechahora = fecha;
    Fecha = (annio + "-" + mes + "-" + dia);
}
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timer2 = new org.netbeans.examples.lib.timerbean.Timer();
        detalle = new javax.swing.JDialog();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtcostounitario = new javax.swing.JTextField();
        txtcantidad = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        gbebidas = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gdetalle = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboproveedor = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        txtfechahora = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btngrabar = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();

        timer2.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                timer2OnTime(evt);
            }
        });

        detalle.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        detalle.setTitle("Forma de Cobro");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Cantidad:");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Costo Unitario:");

        txtcostounitario.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtcostounitario.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcostounitario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtcostounitarioMouseClicked(evt);
            }
        });
        txtcostounitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcostounitarioActionPerformed(evt);
            }
        });
        txtcostounitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcostounitarioKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcostounitarioKeyTyped(evt);
            }
        });

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
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtcostounitario, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        detalleLayout.setVerticalGroup(
            detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(detalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtcostounitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setMaximumSize(new java.awt.Dimension(790, 600));
        setMinimumSize(new java.awt.Dimension(790, 600));
        setPreferredSize(new java.awt.Dimension(790, 600));

        gbebidas.setModel(mbebidas);
        gbebidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gbebidasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(gbebidas);

        jTabbedPane1.addTab("BEBIDAS", jScrollPane1);

        gdetalle.setModel(mdetalle);
        gdetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gdetalleKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(gdetalle);

        jLabel1.setText("ARTÍCULOS CARGADOS A LA COMPRA:");

        txttotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txttotal.setEnabled(false);

        jLabel2.setText("TOTAL:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        cboproveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboproveedorActionPerformed(evt);
            }
        });

        jLabel3.setText("PROVEEDOR:");

        txtfechahora.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtfechahora.setEnabled(false);

        jLabel4.setText("FECHA Y HORA:");

        btngrabar.setText("Grabar");
        btngrabar.setEnabled(false);
        btngrabar.setMaximumSize(new java.awt.Dimension(73, 23));
        btngrabar.setMinimumSize(new java.awt.Dimension(73, 23));
        btngrabar.setPreferredSize(new java.awt.Dimension(73, 23));
        btngrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngrabarActionPerformed(evt);
            }
        });

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btngrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfechahora, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfechahora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btngrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void timer2OnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timer2OnTime
        mostra_data.le_hora();
        txtfechahora.setText(mostra_data.ano+"/"+mostra_data.mes+"/"+mostra_data.dia+" " + mostra_data.hora);
    }//GEN-LAST:event_timer2OnTime

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
    txtcostounitario.setText("");
    txtcostounitario.setEnabled(true);
    txtcantidad.setText("");
    txtcantidad.setEnabled(true);
    ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
    detalle.setIconImage(icono.getImage());
    detalle.setTitle("DETALLE");
    detalle.pack();
    detalle.setLocationRelativeTo(null);
    detalle.setModal(true);
    txtcostounitario.requestFocus();
    detalle.setVisible(true);
    }//GEN-LAST:event_gbebidasMouseClicked

    private void gdetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gdetalleKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
        try {
            cursor.removeRow(gdetalle.getSelectedRow());
            recorrergrilla();
        } catch (SQLException ex) {
            Logger.getLogger(comprabebidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
        try {
            cursor.removeRow(gdetalle.getSelectedRow());
            recorrergrilla();
        } catch (SQLException ex) {
            Logger.getLogger(comprabebidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
    }//GEN-LAST:event_gdetalleKeyPressed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
mdetalle.setRowCount(0);
txttotal.setText("");   
cboproveedor.setSelectedItem("SIN CLIENTE");
btngrabar.setEnabled(false);
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btngrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngrabarActionPerformed
try {                                          
    Fecha("Fecha");
    proveedor = buscaproveedor((String) cboproveedor.getSelectedItem());
    String SQL = "INSERT INTO `venta` (`usu_codigo`,`cli_codigo`,`ven_horfec`,`ven_total`) VALUES ("+acceso.usuario+","+proveedor+",'"+fechahora+"',"+String.valueOf(Float.parseFloat(txttotal.getText()))+")";
    ver_conex conn =new ver_conex();//instanciamos
    conn.sentencia = conn.conexion.createStatement();
    conn.sentencia.executeUpdate(SQL);//OJO LE PASO LA SENTENCIA
    String Codigo = "SELECT * FROM venta ORDER BY ven_codigo DESC LIMIT 1" ;
    conn.sentencia = conn.conexion.createStatement();
    conn.resultado = conn.sentencia.executeQuery(Codigo);
    conn.resultado.next();
    int codigo_venta = (conn.resultado.getInt("ven_codigo"));
    codigo_ticket = codigo_venta;
    try {
        javax.swing.table.TableModel model = gdetalle.getModel();
        int c = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            
            Object codigo    =gdetalle.getValueAt(i,0);Statement stmta=null;
            stmta=(Statement) conn.conexion.createStatement();
            Object subtotal    =gdetalle.getValueAt(i,2);
            conn.sentencia = conn.conexion.createStatement();
            String insercionSQL1="INSERT INTO `venta_detalle` (`ven_codigo`,`art_codigo`,`vde_cantid`,`vde_subtot`) VALUES ("+codigo_venta+","+codigo+",1,"+subtotal+")";
            stmta.executeUpdate(insercionSQL1);
            c++;
        }
        if (cboproveedor.getSelectedItem().toString().equals("SIN CLIENTE")){
        } else {
            Statement stmta=null;
            stmta=(Statement) conn.conexion.createStatement();
            conn.sentencia = conn.conexion.createStatement();
            String insercionSQL1="INSERT INTO `cc_test` (`cli_codigo`,`cco_moncom`,`cco_monent`,`cco_segun`,`cco_fecha`) VALUES ("+proveedor+","+String.valueOf(Float.parseFloat(txttotal.getText()))+",0,'venta "+codigo_venta+"','"+Fecha+"')";
            stmta.executeUpdate(insercionSQL1);
        }
    }catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Ocurrio un error "+e.toString(),"Atención ", JOptionPane.INFORMATION_MESSAGE);
    }
    btnlimpiar.doClick();
}catch (ClassNotFoundException ex) {
        Logger.getLogger(comprabebidas.class.getName()).log(Level.SEVERE, null, ex);
}   catch (InstantiationException ex) {
        Logger.getLogger(comprabebidas.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        Logger.getLogger(comprabebidas.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(comprabebidas.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btngrabarActionPerformed

    private void cboproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboproveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboproveedorActionPerformed

    private void txtcostounitarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtcostounitarioMouseClicked

    }//GEN-LAST:event_txtcostounitarioMouseClicked

    private void txtcostounitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcostounitarioActionPerformed
        String campo = txtcostounitario.getText();
        if (campo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo está vacío, Cargue los datos correctamente");
            return;
        } else {
            txtcostounitario.setText(String.valueOf(Redondear(Float.parseFloat(txtcostounitario.getText()))));
            //txtcostounitario.setEnabled(false);
            //btngrabar.setEnabled(true);
            //btngrabar.requestFocus();
        }
    }//GEN-LAST:event_txtcostounitarioActionPerformed

    private void txtcostounitarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcostounitarioKeyPressed

        char c = evt.getKeyChar();
        if (((c > '0') || (c < '9')) || (c == '.')) {
            TimerTask tarea = new TimerTask(){
                public void run() {
                    //precio();
                }
            };
            Timer temp = new Timer();
            temp.schedule(tarea, 50);}
    }//GEN-LAST:event_txtcostounitarioKeyPressed

    private void txtcostounitarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcostounitarioKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
            && (c != '.')) {
            evt.consume();
        }
        if (c == '.' && txtcostounitario.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtcostounitarioKeyTyped

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
                txtcostounitario.setText(String.valueOf(Redondear(Float.parseFloat(txtcostounitario.getText()))));
                txtcantidad.setEnabled(false);
                txtcostounitario.setEnabled(false);
                btngrabar.setEnabled(true);
                //btngrabar.requestFocus();
                detalle.setResizable(false);
                detalle.setVisible(false);
                gdetalle.getModel();
                Object[] datos = new Object[5];
                datos[0] = codigo_art;
                datos[1] = codigo_descri;
                datos[2] = txtcostounitario.getText();
                datos[3] = txtcantidad.getText();
                datos[4] = String.valueOf(Float.parseFloat(txtcantidad.getText())*Float.parseFloat(txtcostounitario.getText()));
                if (existe == false){
                    mdetalle.addRow(datos);
                } else {
                    mdetalle.insertRow(posicion, datos);
                }

                recorrergrilla();
            } catch (SQLException ex) {
                Logger.getLogger(comprabebidas.class.getName()).log(Level.SEVERE, null, ex);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btngrabar;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JComboBox cboproveedor;
    private javax.swing.JDialog detalle;
    private javax.swing.JTable gbebidas;
    private javax.swing.JTable gdetalle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.netbeans.examples.lib.timerbean.Timer timer2;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtcostounitario;
    private javax.swing.JTextField txtfechahora;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
