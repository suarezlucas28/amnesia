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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class pedido extends javax.swing.JPanel {
data mostra_data;
int cliente;
int codigo_ticket;
String Fecha,fechahora;
javax.swing.table.DefaultTableModel cursor;

DefaultTableModel mbebidas = new DefaultTableModel(){
 @Override
    public boolean isCellEditable(int row, int column) {
    return false;
    }
};

DefaultTableModel mminutas = new DefaultTableModel(){
 @Override
    public boolean isCellEditable(int row, int column) {
    return false;
    }
};

DefaultTableModel motros = new DefaultTableModel(){
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

DefaultTableModel mmesas = new DefaultTableModel(){
 @Override
    public boolean isCellEditable(int row, int column) {
    return false;
    }
};


    public pedido() throws SQLException {
        initComponents();
        cursor = (javax.swing.table.DefaultTableModel) gdetalle.getModel();
        mostra_data = new data();
        mostra_data.le_data();
        timer2.start();
        cabeceras();
        cargagrillas();
        des_grillas();
    }
    
private void des_grillas(){
    
    gbebidas.setEnabled(false);
    gminutas.setEnabled(false);
    gotros.setEnabled(false);
    
}

private void hab_grillas(){
    
    gbebidas.setEnabled(true);
    gminutas.setEnabled(true);
    gotros.setEnabled(true);
    
}


    
private void cabeceras(){
    DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    tcr.setHorizontalAlignment(SwingConstants.RIGHT);
    
    mbebidas.addColumn("COD");
    mbebidas.addColumn("DESCRIPCIÓN");
    mbebidas.addColumn("PRECIO");
    gbebidas.getColumnModel().getColumn(0).setPreferredWidth(31);
    gbebidas.getColumnModel().getColumn(1).setPreferredWidth(191);
    gbebidas.getColumnModel().getColumn(2).setCellRenderer(tcr);

    mminutas.addColumn("COD");
    mminutas.addColumn("DESCRIPCIÓN");
    mminutas.addColumn("PRECIO");
    gminutas.getColumnModel().getColumn(0).setPreferredWidth(31);
    gminutas.getColumnModel().getColumn(1).setPreferredWidth(191);
    gminutas.getColumnModel().getColumn(2).setCellRenderer(tcr);
    
    motros.addColumn("COD");
    motros.addColumn("DESCRIPCIÓN");
    motros.addColumn("PRECIO");
    gotros.getColumnModel().getColumn(0).setPreferredWidth(31);
    gotros.getColumnModel().getColumn(1).setPreferredWidth(191);
    gotros.getColumnModel().getColumn(2).setCellRenderer(tcr);
    
    mdetalle.addColumn("COD");
    mdetalle.addColumn("DESCRIPCIÓN");
    mdetalle.addColumn("PRECIO");
    gdetalle.getColumnModel().getColumn(0).setPreferredWidth(31);
    gdetalle.getColumnModel().getColumn(1).setPreferredWidth(191);
    gdetalle.getColumnModel().getColumn(2).setCellRenderer(tcr);
    
    mmesas.addColumn("COD");
    mmesas.addColumn("DESCRIPCIÓN");
    gmesas.getColumnModel().getColumn(0).setPreferredWidth(31);
    gmesas.getColumnModel().getColumn(1).setPreferredWidth(191);
}

private void cargagrillas() throws SQLException{
    ver_conex  conn = new ver_conex();
    conn.sentencia = conn.conexion.createStatement();
    conn.resultado = conn.sentencia.executeQuery("SELECT art_codigo,art_descri,art_pvemin FROM ARTICULO,CLASIFICACION WHERE articulo.cla_codigo = clasificacion.cla_codigo AND cla_descri LIKE \"BEBIDAS\" AND art_estado LIKE 'AC' ORDER BY art_descri");
    mbebidas.setRowCount(0);
    Object [] fila = new Object[3];
    while (conn.resultado.next()){
        fila[0] = conn.resultado.getObject(1);
        fila[1] = conn.resultado.getObject(2);
        fila[2] = conn.resultado.getObject(3);
        mbebidas.addRow(fila);
    }
    conn.resultado.first();
    
    conn.sentencia = conn.conexion.createStatement();
    conn.resultado = conn.sentencia.executeQuery("SELECT art_codigo,art_descri,art_pvemin FROM ARTICULO,CLASIFICACION WHERE articulo.cla_codigo = clasificacion.cla_codigo AND cla_descri LIKE \"MINUTAS\" AND art_estado LIKE 'AC' ORDER BY art_descri");
    mminutas.setRowCount(0);
    Object [] fila1 = new Object[3];
    while (conn.resultado.next()){
        fila1[0] = conn.resultado.getObject(1);
        fila1[1] = conn.resultado.getObject(2);
        fila1[2] = conn.resultado.getObject(3);
        mminutas.addRow(fila1);
    }
    conn.resultado.first();
    
    conn.sentencia = conn.conexion.createStatement();
    conn.resultado = conn.sentencia.executeQuery("SELECT art_codigo,art_descri,art_pvemin FROM ARTICULO,CLASIFICACION WHERE articulo.cla_codigo = clasificacion.cla_codigo AND cla_descri LIKE \"OTROS\" AND art_estado LIKE 'AC' ORDER BY art_descri");
    motros.setRowCount(0);
    Object [] fila2 = new Object[3];
    while (conn.resultado.next()){
        fila2[0] = conn.resultado.getObject(1);
        fila2[1] = conn.resultado.getObject(2);
        fila2[2] = conn.resultado.getObject(3);
        motros.addRow(fila2);
    }
    conn.resultado.first();
    
    conn.sentencia = conn.conexion.createStatement();
    conn.resultado = conn.sentencia.executeQuery("SELECT mes_codigo,mes_descri FROM mesas ORDER BY mes_codigo");
    mmesas.setRowCount(0);
    Object [] fila3 = new Object[2];
    while (conn.resultado.next()){
        fila3[0] = conn.resultado.getObject(1);
        fila3[1] = conn.resultado.getObject(2);
        mmesas.addRow(fila3);
    }
    conn.resultado.first();
}

 public double Redondear(double numero){
    return Math.rint(numero*100)/100;
}
 
 private void recorrergrilla () throws SQLException {
    javax.swing.table.TableModel model=gdetalle.getModel();
    int c = 0;
    float total_1 = 0;
    for (int i = 0; i < model.getRowCount();i++) {
     java.lang.String value=(String)model.getValueAt(i,2);
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
 
  private void factura(){

    try{
       /////////////////////////////////instanciamos
       ver_conex conn =new ver_conex();
       HashMap parameters = new HashMap();
       ///////////////////////////////// preparamos parametros del reporte
       parameters.put("id",codigo_ticket);
       ///////////////////////////////// patch de los reporetes
       URL urlMaestro = getClass().getClassLoader().getResource("repo/ticket_1.jasper");
       ///////////////////////////////// Cargamos el reporte
       JasperReport masterReport = null;
       masterReport = (JasperReport) JRLoader.loadObject(urlMaestro);
       JasperPrint masterPrint = null;
       //////////////////////////////// paso de parametros y patch
       masterPrint = JasperFillManager.fillReport(masterReport, parameters,conn.conexion);
       JasperViewer ventana = new JasperViewer(masterPrint,false);
       ventana.setTitle("Vista Previa");
       ventana.setVisible(false);
       JasperPrintManager.printReport(masterPrint,false);
       }catch(JRException e){
       e.printStackTrace();
       JOptionPane.showMessageDialog(null, "Ocurrio un error "+e.toString(),"ATENCION ", JOptionPane.INFORMATION_MESSAGE);
       }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timer2 = new org.netbeans.examples.lib.timerbean.Timer();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        gbebidas = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        gminutas = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        gotros = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        gdetalle = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtfechahora = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        gmesas = new javax.swing.JTable();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        btnlimpiar1 = new javax.swing.JButton();

        timer2.addTimerListener(new org.netbeans.examples.lib.timerbean.TimerListener() {
            public void onTime(java.awt.event.ActionEvent evt) {
                timer2OnTime(evt);
            }
        });

        setMaximumSize(new java.awt.Dimension(950, 380));
        setMinimumSize(new java.awt.Dimension(950, 380));
        setPreferredSize(new java.awt.Dimension(950, 380));

        gbebidas.setModel(mbebidas);
        gbebidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gbebidasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(gbebidas);

        jTabbedPane1.addTab("BEBIDAS", jScrollPane1);

        gminutas.setModel(mminutas);
        gminutas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gminutasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(gminutas);

        jTabbedPane1.addTab("MINUTAS", jScrollPane3);

        gotros.setModel(motros);
        gotros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gotrosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(gotros);

        jTabbedPane1.addTab("OTROS", jScrollPane4);

        gdetalle.setModel(mdetalle);
        gdetalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gdetalleKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(gdetalle);

        jLabel1.setText("ARTÍCULOS CARGADOS A LA MESA:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtfechahora.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtfechahora.setEnabled(false);

        jLabel4.setText("FECHA Y HORA:");

        jLabel5.setText("MESAS:");

        gmesas.setModel(mmesas);
        gmesas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gmesasMouseClicked(evt);
            }
        });
        gmesas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gmesasKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(gmesas);

        jCheckBox1.setText("Ticket");

        jLabel2.setText("TOTAL:");

        txttotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txttotal.setEnabled(false);

        btnlimpiar1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnlimpiar1.setText("Cobrar");
        btnlimpiar1.setEnabled(false);
        btnlimpiar1.setMaximumSize(new java.awt.Dimension(73, 23));
        btnlimpiar1.setMinimumSize(new java.awt.Dimension(73, 23));
        btnlimpiar1.setPreferredSize(new java.awt.Dimension(73, 23));
        btnlimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txttotal))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnlimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfechahora, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtfechahora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnlimpiar1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox1)))
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void timer2OnTime(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timer2OnTime
        mostra_data.le_hora();
        txtfechahora.setText(mostra_data.ano+"/"+mostra_data.mes+"/"+mostra_data.dia+" " + mostra_data.hora);
    }//GEN-LAST:event_timer2OnTime

    private void gminutasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gminutasMouseClicked
    String mesa = jLabel3.getText().trim();
    if (mesa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado mesa.");
            return;
    } else {
        try {
            ver_conex  conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("select mes_codigo from mesas where mes_descri like '"+mesa+"'");
            conn.resultado.next();
            
            int int_mesa = conn.resultado.getInt("mes_codigo");
            
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM pedidos WHERE mes_codigo = "+int_mesa+" and ped_estado like 'ACTIVO'");
            if (conn.resultado.first() == true){
                
                int int_pedido = conn.resultado.getInt("ped_codigo");
                
                String item = "";
                Boolean existe = false;
                int posicion = 0;

                String codigo = mminutas.getValueAt(gminutas.getSelectedRow(), 0).toString();
                String descri = mminutas.getValueAt(gminutas.getSelectedRow(), 1).toString();
                String precio = mminutas.getValueAt(gminutas.getSelectedRow(), 2).toString();

                Statement stmta=null;
                stmta=(Statement) conn.conexion.createStatement();
                String detalle="INSERT INTO `pedidos_detalle` (`ped_codigo`,`art_codigo`,`ped_cantid`,`ped_subtot`) VALUES ("+int_pedido+","+codigo+",1,"+precio+")";
                stmta.executeUpdate(detalle);
                
                int z = gdetalle.getRowCount();
                for (int a = 0; a < z; a++){
                    item  = (String) gdetalle.getValueAt(a, 0);
                    if (item.equals(codigo)){
                        existe = true;
                        posicion = a;
                        break; 
                    }
                }

                gdetalle.getModel();
                Object[] datos = new Object[3];
                datos[0] = codigo;
                datos[1] = descri;
                datos[2] = precio;

                if (existe == false){
                    mdetalle.addRow(datos);
                } else {
                    mdetalle.insertRow(posicion, datos);
                }

                recorrergrilla();
                
                String cabecera="update `pedidos` set ped_total = "+String.valueOf(Float.parseFloat(txttotal.getText()))+" where ped_codigo = "+int_pedido;
                stmta.executeUpdate(cabecera);
                
            } else {
                
                Fecha("Fecha");
                String SQL = "INSERT INTO `pedidos` (`usu_codigo`,mes_codigo,`ped_horfec`) VALUES ("+acceso.usuario+","+int_mesa+",'"+fechahora+"')";
                conn.sentencia = conn.conexion.createStatement();
                conn.sentencia.executeUpdate(SQL);//OJO LE PASO LA SENTENCIA
                String Codigo = "SELECT * FROM pedidos ORDER BY ped_codigo DESC LIMIT 1" ;
                conn.sentencia = conn.conexion.createStatement();
                conn.resultado = conn.sentencia.executeQuery(Codigo);
                conn.resultado.next();
                int int_pedido = conn.resultado.getInt("ped_codigo");
                
                String item = "";
                Boolean existe = false;
                int posicion = 0;

                String codigo = mminutas.getValueAt(gminutas.getSelectedRow(), 0).toString();
                String descri = mminutas.getValueAt(gminutas.getSelectedRow(), 1).toString();
                String precio = mminutas.getValueAt(gminutas.getSelectedRow(), 2).toString();

                Statement stmta=null;
                stmta=(Statement) conn.conexion.createStatement();
                String detalle="INSERT INTO `pedidos_detalle` (`ped_codigo`,`art_codigo`,`ped_cantid`,`ped_subtot`) VALUES ("+int_pedido+","+codigo+",1,"+precio+")";
                stmta.executeUpdate(detalle);
                
                int z = gdetalle.getRowCount();
                for (int a = 0; a < z; a++){
                    item  = (String) gdetalle.getValueAt(a, 0);
                    if (item.equals(codigo)){
                        existe = true;
                        posicion = a;
                        break; 
                    }
                }

                gdetalle.getModel();
                Object[] datos = new Object[3];
                datos[0] = codigo;
                datos[1] = descri;
                datos[2] = precio;

                if (existe == false){
                    mdetalle.addRow(datos);
                } else {
                    mdetalle.insertRow(posicion, datos);
                }

                recorrergrilla();
                
                String cabecera="update `pedidos` set ped_total = "+String.valueOf(Float.parseFloat(txttotal.getText()))+" where ped_codigo = "+int_pedido;
                stmta.executeUpdate(cabecera);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_gminutasMouseClicked

    private void gotrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gotrosMouseClicked
    String mesa = jLabel3.getText().trim();
    if (mesa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado mesa.");
            return;
    } else {
        try {
            ver_conex  conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("select mes_codigo from mesas where mes_descri like '"+mesa+"'");
            conn.resultado.next();
            
            int int_mesa = conn.resultado.getInt("mes_codigo");
            
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM pedidos WHERE mes_codigo = "+int_mesa+" and ped_estado like 'ACTIVO'");
            if (conn.resultado.first() == true){
                
                int int_pedido = conn.resultado.getInt("ped_codigo");
                
                String item = "";
                Boolean existe = false;
                int posicion = 0;

                String codigo = motros.getValueAt(gotros.getSelectedRow(), 0).toString();
                String descri = motros.getValueAt(gotros.getSelectedRow(), 1).toString();
                String precio = motros.getValueAt(gotros.getSelectedRow(), 2).toString();
                
                Statement stmta=null;
                stmta=(Statement) conn.conexion.createStatement();
                String detalle="INSERT INTO `pedidos_detalle` (`ped_codigo`,`art_codigo`,`ped_cantid`,`ped_subtot`) VALUES ("+int_pedido+","+codigo+",1,"+precio+")";
                stmta.executeUpdate(detalle);

                int z = gdetalle.getRowCount();
                for (int a = 0; a < z; a++){
                    item  = (String) gdetalle.getValueAt(a, 0);
                    if (item.equals(codigo)){
                        existe = true;
                        posicion = a;
                        break; 
                    }
                }

                gdetalle.getModel();
                Object[] datos = new Object[3];
                datos[0] = codigo;
                datos[1] = descri;
                datos[2] = precio;

                if (existe == false){
                    mdetalle.addRow(datos);
                } else {
                    mdetalle.insertRow(posicion, datos);
                }

                recorrergrilla();
                
                String cabecera="update `pedidos` set ped_total = "+String.valueOf(Float.parseFloat(txttotal.getText()))+" where ped_codigo = "+int_pedido;
                stmta.executeUpdate(cabecera);
                
            } else {
                
                Fecha("Fecha");
                String SQL = "INSERT INTO `pedidos` (`usu_codigo`,mes_codigo,`ped_horfec`) VALUES ("+acceso.usuario+","+int_mesa+",'"+fechahora+"')";
                conn.sentencia = conn.conexion.createStatement();
                conn.sentencia.executeUpdate(SQL);//OJO LE PASO LA SENTENCIA
                String Codigo = "SELECT * FROM pedidos ORDER BY ped_codigo DESC LIMIT 1" ;
                conn.sentencia = conn.conexion.createStatement();
                conn.resultado = conn.sentencia.executeQuery(Codigo);
                conn.resultado.next();
                int int_pedido = conn.resultado.getInt("ped_codigo");
                
                String item = "";
                Boolean existe = false;
                int posicion = 0;

                String codigo = motros.getValueAt(gotros.getSelectedRow(), 0).toString();
                String descri = motros.getValueAt(gotros.getSelectedRow(), 1).toString();
                String precio = motros.getValueAt(gotros.getSelectedRow(), 2).toString();
                
                Statement stmta=null;
                stmta=(Statement) conn.conexion.createStatement();
                String detalle="INSERT INTO `pedidos_detalle` (`ped_codigo`,`art_codigo`,`ped_cantid`,`ped_subtot`) VALUES ("+int_pedido+","+codigo+",1,"+precio+")";
                stmta.executeUpdate(detalle);

                int z = gdetalle.getRowCount();
                for (int a = 0; a < z; a++){
                    item  = (String) gdetalle.getValueAt(a, 0);
                    if (item.equals(codigo)){
                        existe = true;
                        posicion = a;
                        break; 
                    }
                }

                gdetalle.getModel();
                Object[] datos = new Object[3];
                datos[0] = codigo;
                datos[1] = descri;
                datos[2] = precio;

                if (existe == false){
                    mdetalle.addRow(datos);
                } else {
                    mdetalle.insertRow(posicion, datos);
                }

                recorrergrilla();
                
                String cabecera="update `pedidos` set ped_total = "+String.valueOf(Float.parseFloat(txttotal.getText()))+" where ped_codigo = "+int_pedido;
                stmta.executeUpdate(cabecera);
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_gotrosMouseClicked

    private void gdetalleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gdetalleKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
        try {
            String mesa = jLabel3.getText().trim();
                       
            ver_conex  conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("select mes_codigo from mesas where mes_descri like '"+mesa+"'");
            conn.resultado.next();
            
            int int_mesa = conn.resultado.getInt("mes_codigo");
            
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM pedidos WHERE mes_codigo = "+int_mesa+" and ped_estado like 'ACTIVO'");
            conn.resultado.first();
            
            int int_pedido = conn.resultado.getInt("ped_codigo");
            
            String codigo = mdetalle.getValueAt(gdetalle.getSelectedRow(), 0).toString();
            
            Statement stmta=null;
            stmta=(Statement) conn.conexion.createStatement();
            String delete="delete from `pedidos_detalle` where `ped_codigo` = "+int_pedido+" and `art_codigo` = "+codigo;
            stmta.executeUpdate(delete);
            
            cursor.removeRow(gdetalle.getSelectedRow());
            recorrergrilla();
            
            String cabecera="update `pedidos` set ped_total = "+String.valueOf(Float.parseFloat(txttotal.getText()))+" where ped_codigo = "+int_pedido;
            stmta.executeUpdate(cabecera);
            
        } catch (SQLException ex) {
            Logger.getLogger(venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
        try {
            cursor.removeRow(gdetalle.getSelectedRow());
            recorrergrilla();
        } catch (SQLException ex) {
            Logger.getLogger(venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }      
    }//GEN-LAST:event_gdetalleKeyPressed

    private void gbebidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gbebidasMouseClicked
    String mesa = jLabel3.getText();
    if (mesa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado mesa.");
            return;
    } else {
        try {
            
            ver_conex  conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("select mes_codigo from mesas where mes_descri like '"+mesa+"'");
            conn.resultado.next();
            
            int int_mesa = conn.resultado.getInt("mes_codigo");
            
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM pedidos WHERE mes_codigo = "+int_mesa+" and ped_estado like 'ACTIVO'");
            if (conn.resultado.first() == true){
                
                int int_pedido = conn.resultado.getInt("ped_codigo");
                
                String item = "";
                Boolean existe = false;
                int posicion = 0;

                String codigo = mbebidas.getValueAt(gbebidas.getSelectedRow(), 0).toString();
                String descri = mbebidas.getValueAt(gbebidas.getSelectedRow(), 1).toString();
                String precio = mbebidas.getValueAt(gbebidas.getSelectedRow(), 2).toString();
                
                Statement stmta=null;
                stmta=(Statement) conn.conexion.createStatement();
                String detalle="INSERT INTO `pedidos_detalle` (`ped_codigo`,`art_codigo`,`ped_cantid`,`ped_subtot`) VALUES ("+int_pedido+","+codigo+",1,"+precio+")";
                stmta.executeUpdate(detalle);

                int z = gdetalle.getRowCount();
                for (int a = 0; a < z; a++){
                    item  = (String) gdetalle.getValueAt(a, 0);
                    if (item.equals(codigo)){
                        existe = true;
                        posicion = a;
                        break;
                    }
                }

                gdetalle.getModel();
                Object[] datos = new Object[3];
                datos[0] = codigo;
                datos[1] = descri;
                datos[2] = precio;

                if (existe == false){
                    mdetalle.addRow(datos);
                } else {
                    mdetalle.insertRow(posicion, datos);
                }

                recorrergrilla();
                
                String cabecera="update `pedidos` set ped_total = "+String.valueOf(Float.parseFloat(txttotal.getText()))+" where ped_codigo = "+int_pedido;
                stmta.executeUpdate(cabecera);
                
            } else {
                
                Fecha("Fecha");
                String SQL = "INSERT INTO `pedidos` (`usu_codigo`,mes_codigo,`ped_horfec`) VALUES ("+acceso.usuario+","+int_mesa+",'"+fechahora+"')";
                conn.sentencia = conn.conexion.createStatement();
                conn.sentencia.executeUpdate(SQL);//OJO LE PASO LA SENTENCIA
                String Codigo = "SELECT * FROM pedidos ORDER BY ped_codigo DESC LIMIT 1" ;
                conn.sentencia = conn.conexion.createStatement();
                conn.resultado = conn.sentencia.executeQuery(Codigo);
                conn.resultado.next();
                int int_pedido = conn.resultado.getInt("ped_codigo");
                
                String item = "";
                Boolean existe = false;
                int posicion = 0;

                String codigo = mbebidas.getValueAt(gbebidas.getSelectedRow(), 0).toString();
                String descri = mbebidas.getValueAt(gbebidas.getSelectedRow(), 1).toString();
                String precio = mbebidas.getValueAt(gbebidas.getSelectedRow(), 2).toString();
                
                Statement stmta=null;
                stmta=(Statement) conn.conexion.createStatement();
                String detalle="INSERT INTO `pedidos_detalle` (`ped_codigo`,`art_codigo`,`ped_cantid`,`ped_subtot`) VALUES ("+int_pedido+","+codigo+",1,"+precio+")";
                stmta.executeUpdate(detalle);

                int z = gdetalle.getRowCount();
                for (int a = 0; a < z; a++){
                    item  = (String) gdetalle.getValueAt(a, 0);
                    if (item.equals(codigo)){
                        existe = true;
                        posicion = a;
                        break;
                    }
                }

                gdetalle.getModel();
                Object[] datos = new Object[3];
                datos[0] = codigo;
                datos[1] = descri;
                datos[2] = precio;

                if (existe == false){
                    mdetalle.addRow(datos);
                } else {
                    mdetalle.insertRow(posicion, datos);
                }

                recorrergrilla();
                
                String cabecera="update `pedidos` set ped_total = "+String.valueOf(Float.parseFloat(txttotal.getText()))+" where ped_codigo = "+int_pedido;
                stmta.executeUpdate(cabecera);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(venta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }//GEN-LAST:event_gbebidasMouseClicked

    private void gmesasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gmesasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_gmesasKeyPressed

    private void btnlimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiar1ActionPerformed
        try {
            String mesa = jLabel3.getText();
            ver_conex  conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("select mes_codigo from mesas where mes_descri like '"+mesa+"'");
            conn.resultado.next();

            int int_mesa = conn.resultado.getInt("mes_codigo");

            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM pedidos WHERE mes_codigo = "+int_mesa+" and ped_estado like 'ACTIVO'");
            conn.resultado.next();
            
            int int_pedido = conn.resultado.getInt("ped_codigo");   
            String str_horfec = conn.resultado.getString("ped_horfec");
            float flo_total = conn.resultado.getFloat("ped_total");
            
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM cliente WHERE cli_nomape like 'SIN CLIENTE'");
            conn.resultado.next();
            
            int int_cliente = conn.resultado.getInt("cli_codigo");
            
            Statement stmta=null;
            stmta=(Statement) conn.conexion.createStatement();
            String cabecera="INSERT INTO `venta` (`usu_codigo`,`cli_codigo`,`ven_horfec`,`ven_total`) VALUES ("+acceso.usuario+","+int_cliente+",'"+str_horfec+"',"+flo_total+")";
            stmta.executeUpdate(cabecera);
            
            String Codigo = "SELECT * FROM venta ORDER BY ven_codigo DESC LIMIT 1" ;
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery(Codigo);
            conn.resultado.next();
            int codigo_venta = (conn.resultado.getInt("ven_codigo"));
            
            codigo_ticket = codigo_venta;
            
            javax.swing.table.TableModel model = gdetalle.getModel();
            int c = 0;
            for (int i = 0; i < model.getRowCount(); i++) {

                Object codigo    =gdetalle.getValueAt(i,0);
                Object subtotal    =gdetalle.getValueAt(i,2);
                conn.sentencia = conn.conexion.createStatement();
                String insercionSQL1="INSERT INTO `venta_detalle` (`ven_codigo`,`art_codigo`,`vde_cantid`,`vde_subtot`) VALUES ("+codigo_venta+","+codigo+",1,"+subtotal+")";
                stmta.executeUpdate(insercionSQL1);
                c++;
            }
            
            String cabecera_1="update `pedidos` set ped_estado = 'COBRADO' where ped_codigo = "+ int_pedido;
            stmta.executeUpdate(cabecera_1);
            
            if(jCheckBox1.isSelected()){
                    factura();
                }
            
            jLabel3.setText("");
            btnlimpiar1.setEnabled(false);
            des_grillas();
            jCheckBox1.setSelected(false);
            gmesas.requestFocus();
            txttotal.setText("");
            mdetalle.setRowCount(0);
            
        } catch (SQLException ex) {
            Logger.getLogger(pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnlimpiar1ActionPerformed

    private void gmesasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gmesasMouseClicked
        try {
            String codigo = mmesas.getValueAt(gmesas.getSelectedRow(), 0).toString();
            String descripcion = mmesas.getValueAt(gmesas.getSelectedRow(), 1).toString();
            mdetalle.setRowCount(0);
            jLabel3.setText(descripcion);
            btnlimpiar1.setEnabled(false);
            txttotal.setText("");
            
            ver_conex  conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM pedidos WHERE mes_codigo = "+codigo+" and ped_estado like 'ACTIVO'");
            if (conn.resultado.first() == true){
                
                int new_codigo = conn.resultado.getInt("ped_codigo");
                
                conn.sentencia = conn.conexion.createStatement();
                conn.resultado = conn.sentencia.executeQuery("SELECT pedidos_detalle.art_codigo,art_descri,ped_subtot FROM pedidos_detalle,articulo WHERE pedidos_detalle.art_codigo = articulo.art_codigo and ped_codigo = "+new_codigo);
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
                btnlimpiar1.setEnabled(true);
                
                recorrergrilla();
                
                hab_grillas();
                
            } else {
                
                 int mensaje = JOptionPane.showConfirmDialog(this,"La "+descripcion+" no tiene artículos, desea agregar artículos?","Atención",JOptionPane.YES_NO_OPTION);
                  if(mensaje == JOptionPane.YES_OPTION)//si quiere borrar
                  {
                     
                      hab_grillas();
                      btnlimpiar1.setEnabled(true);
                      
                  }
                  else // no quiere borrar
                  {
                      JOptionPane.showMessageDialog(null, "Operacion Cancelada", "Atención", JOptionPane.ERROR_MESSAGE);
                      jLabel3.setText("");
                      btnlimpiar1.setEnabled(false);
                      des_grillas();
                      gmesas.requestFocus();
                      txttotal.setText("");
                  }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_gmesasMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnlimpiar1;
    private javax.swing.JTable gbebidas;
    private javax.swing.JTable gdetalle;
    private javax.swing.JTable gmesas;
    private javax.swing.JTable gminutas;
    private javax.swing.JTable gotros;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.netbeans.examples.lib.timerbean.Timer timer2;
    private javax.swing.JTextField txtfechahora;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
