/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;
import prgs.ver_conex;

/**
 *
 * @author LUke
 */
public class articulos extends javax.swing.JPanel {
int operacion = 0,clasificacion1,proveedor,permiso;
String rutareal;
static JDialog jgenerador;
String wqqqq;
private int ab;
java.sql.Statement snt;
ResultSet recur1; 
double canuni;

DefaultTableModel modelo = new DefaultTableModel(){
     @Override
        public boolean isCellEditable(int row, int column) {
        return false;
    }
};
    DefaultTableModel modelo1 = new DefaultTableModel(){
     @Override
        public boolean isCellEditable(int row, int column) {
        return false;
    }
};

    public articulos() throws SQLException {
        initComponents();
        des_btn();
        cabeceragrilla();
        cargagrilla();
        grilla.setEnabled(false);
        cargaclasificacion();
        cabecerabuscador();
    }

private void gencod(){
    try
	{
	ver_conex  conn = new ver_conex();
        conn.sentencia = conn.conexion.createStatement();
        conn.resultado = conn.sentencia.executeQuery("SELECT IFNULL(MAX(art_codigo),0)+1 AS xxx FROM articulo");
        conn.resultado.next();
        txtcodigo.setText(conn.resultado.getString("xxx"));

 	}
	catch (SQLException exceptionSql)
	{
		JOptionPane.showMessageDialog(null, exceptionSql.getMessage(), "Error de Conexion con la Base de Datos", JOptionPane.ERROR_MESSAGE);
	}
}

private void cargagrilla() throws SQLException{
    ver_conex  conn = new ver_conex();
    conn.sentencia = conn.conexion.createStatement();
    conn.resultado = conn.sentencia.executeQuery("SELECT ART_codigo,art_descri from ARTICULO where art_estado like 'AC' order by art_descri");
    modelo.setRowCount(0);
    Object [] fila = new Object[2];
    while (conn.resultado.next()){
        fila[0] = conn.resultado.getObject(1);
        fila[1] = conn.resultado.getObject(2);
        modelo.addRow(fila);
    }
    conn.resultado.first();
}

private void cabeceragrilla(){
        modelo.addColumn("CODIGO");
        modelo.addColumn("DESCRIPCIÓN");
        grilla.getColumnModel().getColumn(0).setPreferredWidth(31);
        grilla.getColumnModel().getColumn(1).setPreferredWidth(191);
}

private void hab_btn(){
btnagregar.setEnabled(false);   
btnborrar.setEnabled(false);
btnmodificar.setEnabled(false);
btngrabar.setEnabled(false);
btncancelar.setEnabled(true);
}

private void des_btn(){
btnagregar.setEnabled(true);   
btnagregar.requestFocus();
btnborrar.setEnabled(true);
btnmodificar.setEnabled(true);
btngrabar.setEnabled(false);
btncancelar.setEnabled(false);
}

private void operaciones (){
        try {
            ver_conex conn =new ver_conex();//instanciamos         
            String SQL = "call abm_articulos("+operacion+","+txtcodigo.getText()+",'"+txtdescripcion.getText()+"',"+clasificacion1+","+txtminorista.getText()+")";
            conn.sentencia = conn.conexion.createStatement();          
            conn.sentencia.executeUpdate(SQL);//OJO LE PASO LA SENTENCIA
            conn.sentencia.close();
            JOptionPane.showMessageDialog(null, "La operacion se a realizado con éxito satisfactoriamente.", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Se ha producido un error, comuniquese con el administrador del sistema", "", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
}

private void lim_text(){
txtcodigo.setText("");
txtdescripcion.setText("");
txtminorista.setText("");
txtminorista.setText("");
}

private void des_text(){
txtcodigo.setEnabled(false);
txtdescripcion.setEnabled(false);
txtminorista.setEnabled(false);
cboclasificacion.setEnabled(false);
txtminorista.setEnabled(false);
}

public double Redondear(double numero)
{
 return Math.rint(numero*100)/100;
}

 public void cargaclasificacion() throws SQLException{
            String SQL = "SELECT * FROM clasificacion order by cla_descri";
            ver_conex  conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery(SQL);
            cboclasificacion.removeAllItems();
            while (conn.resultado.next()) {
            cboclasificacion.addItem(conn.resultado.getString("cla_descri"));
}
}

 public int buscaclasificacion(String a) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
    String SQL = "SELECT * from clasificacion where cla_descri = '" + cboclasificacion.getSelectedItem() +"'" ;
    ver_conex  conn = new ver_conex();
    conn.sentencia = conn.conexion.createStatement();
    conn.resultado = conn.sentencia.executeQuery(SQL);
    conn.resultado.next();
    return(conn.resultado.getInt("cla_codigo"));
}
  
  private void busqueda(){
      wqqqq = txtbuscador.getText();
        grilla1();
  }
  
  
  /*private void precio(){
    Double a = canuni;
    float b = Float.parseFloat(txtminorista.getText());
    txtminorista.setText(String.valueOf(Redondear(a+((a*b)/100))));
    jTextField1.setText(String.valueOf(Redondear(((a+((a*b)/100)))-a)));
  }
  
  private void precio1(){
    float a = Float.parseFloat(txtprecio.getText());
    float b = Float.parseFloat(txtmayorista.getText());
    txtmayorista.setText(String.valueOf(Redondear(a+((a*b)/100))));
    jTextField2.setText(String.valueOf(Redondear(((a+((a*b)/100)))-a)));
  }*/
  
  public void grilla1()
       {
         String ac;
          modelo1.setRowCount(0);
        
        try {
            ver_conex conn2 =new ver_conex();//instanciamos
            snt = conn2.conexion.createStatement();
            recur1=snt.executeQuery("SELECT art_codigo,art_descri FROM articulo WHERE art_estado LIKE 'AC' AND art_descri LIKE '%"+wqqqq+"%'");
            System.out.println(recur1);
            if (recur1.wasNull())
            {
                    ab=0;
                    return;
            }
            
            try
            {
                if(recur1.next()) {
                  do {
                    ab = recur1.getInt("art_codigo");
                    ac = recur1.getString("art_descri");
                    
                    modelo1.addRow(new Object[]{ab,ac});
                } while(recur1.next());
                } else {
                    ab=0;
                }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de Sintaxis" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de Sintaxis" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);
        }
    }
  
             private void cabecerabuscador(){
        modelo1.addColumn("CODIGO");
        modelo1.addColumn("DESCRIPCIÓN");
        grilla1.getColumnModel().getColumn(0).setPreferredWidth(51);
        grilla1.getColumnModel().getColumn(1).setPreferredWidth(151);
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buscador = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        grilla1 = new javax.swing.JTable();
        txtbuscador = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnagregar = new javax.swing.JButton();
        btnborrar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btngrabar = new javax.swing.JButton();
        txtcodigo = new javax.swing.JTextField();
        txtdescripcion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        grilla = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cboclasificacion = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtminorista = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        grilla1.setModel(modelo1);
        grilla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grilla1MouseClicked(evt);
            }
        });
        grilla1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                grilla1KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(grilla1);

        txtbuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscadorActionPerformed(evt);
            }
        });
        txtbuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscadorKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscadorKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Buscador:");

        javax.swing.GroupLayout buscadorLayout = new javax.swing.GroupLayout(buscador.getContentPane());
        buscador.getContentPane().setLayout(buscadorLayout);
        buscadorLayout.setHorizontalGroup(
            buscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(buscadorLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscador, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)))
                .addContainerGap())
        );
        buscadorLayout.setVerticalGroup(
            buscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscadorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(buscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setMaximumSize(new java.awt.Dimension(670, 244));
        setMinimumSize(new java.awt.Dimension(670, 244));
        setPreferredSize(new java.awt.Dimension(670, 244));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Código:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Descripción:");

        btnagregar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnagregar.setText("Agregar");
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });
        btnagregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnagregarKeyPressed(evt);
            }
        });

        btnborrar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnborrar.setText("Borrar");
        btnborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnborrarActionPerformed(evt);
            }
        });
        btnborrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnborrarKeyPressed(evt);
            }
        });

        btnmodificar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btnmodificar.setText("Modificar");
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });
        btnmodificar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnmodificarKeyPressed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btncancelar.setText("Cancelar");
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

        btngrabar.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        btngrabar.setText("Grabar");
        btngrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngrabarActionPerformed(evt);
            }
        });
        btngrabar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btngrabarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(btnagregar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnborrar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btngrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnborrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnagregar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnmodificar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btngrabar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        txtcodigo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtcodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtcodigo.setEnabled(false);
        txtcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodigoActionPerformed(evt);
            }
        });
        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoKeyTyped(evt);
            }
        });

        txtdescripcion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdescripcion.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtdescripcion.setEnabled(false);
        txtdescripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtdescripcionMouseClicked(evt);
            }
        });
        txtdescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdescripcionActionPerformed(evt);
            }
        });
        txtdescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescripcionKeyTyped(evt);
            }
        });

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

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

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Clasificación:");

        cboclasificacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboclasificacion.setEnabled(false);
        cboclasificacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboclasificacionMouseClicked(evt);
            }
        });
        cboclasificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboclasificacionKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Precio:");

        txtminorista.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtminorista.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtminorista.setEnabled(false);
        txtminorista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtminoristaMouseClicked(evt);
            }
        });
        txtminorista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtminoristaActionPerformed(evt);
            }
        });
        txtminorista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtminoristaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtminoristaKeyTyped(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imag/Search.png"))); // NOI18N
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtminorista, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboclasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboclasificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtminorista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodigoActionPerformed
    }//GEN-LAST:event_txtcodigoActionPerformed

    private void txtdescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescripcionActionPerformed
        String campo = txtdescripcion.getText();
        if (campo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo está vacío, Cargue los datos correctamente");
            return;
        } else {
                 txtdescripcion.setText(campo.toUpperCase());
                 cboclasificacion.setEnabled(true);
                 cboclasificacion.requestFocus();
                 txtdescripcion.setEnabled(false);
        }
    }//GEN-LAST:event_txtdescripcionActionPerformed

    private void txtdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyTyped
        if (txtdescripcion.getText().length() == 30) {
            txtdescripcion.setText(txtdescripcion.getText().toUpperCase());
            txtdescripcion.setEnabled(false);
            cboclasificacion.setEnabled(true);
            cboclasificacion.requestFocus();
        }
        if (evt.getKeyCode() == evt.VK_BACK_SPACE) {
            if (txtdescripcion.getText().length() == 30) {
                txtdescripcion.setEnabled(true);
            }
        }
    }//GEN-LAST:event_txtdescripcionKeyTyped

    private void txtcodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyTyped
int k=(int)evt.getKeyChar();
//Este if no permite el ingreso de letras y otros símbolos
if ((k >= 32 && k <= 45) ||(k >= 58 && k <= 126)  ){
 evt.setKeyChar((char)KeyEvent.VK_CLEAR);
 evt.consume();
}
//Esteif no permite el ingreso de "ñ" ," Ñ" ni "/"
if(k==241 || k==209|| k==47){
 evt.setKeyChar((char)KeyEvent.VK_CLEAR);
 evt.consume();
}
    }//GEN-LAST:event_txtcodigoKeyTyped

    private void btngrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngrabarActionPerformed
        try {
            clasificacion1 = buscaclasificacion((String) cboclasificacion.getSelectedItem());  
            operaciones();
            btncancelar.doClick();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(articulos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(articulos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(articulos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btngrabarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        try {
            jButton1.setEnabled(false);
            operacion = 0;
            cargagrilla();
            grilla.setEnabled(false);
            des_btn();
            lim_text();
            des_text();
            rutareal = "";
        } catch (SQLException ex) {
            Logger.getLogger(articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        jButton1.setEnabled(true);
        operacion = 3;
        hab_btn();
        grilla.setEnabled(true);
        grilla.requestFocus();
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnborrarActionPerformed
        jButton1.setEnabled(true);
        operacion = 2;
        hab_btn();
        grilla.setEnabled(true);
        grilla.requestFocus();
    }//GEN-LAST:event_btnborrarActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
        operacion = 1;
        hab_btn();
        gencod();
        txtdescripcion.setEnabled(true);
        txtdescripcion.requestFocus();
    }//GEN-LAST:event_btnagregarActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void grillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grillaMouseClicked
        try {
            String codigo = modelo.getValueAt(grilla.getSelectedRow(), 0).toString();
            String descrip = modelo.getValueAt(grilla.getSelectedRow(), 1).toString();
            txtcodigo.setText(codigo);
            txtdescripcion.setText(descrip);
            ver_conex conn = new ver_conex();//instanciamos
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM `articulo` WHERE `art_codigo` = " + codigo);
            conn.resultado.next();
            txtminorista.setText(conn.resultado.getString("art_pvemin"));
            int cestablecimiento = conn.resultado.getInt("cla_codigo");
            Float pvemin = Float.parseFloat(conn.resultado.getString("art_pvemin"));
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM clasificacion WHERE `cla_codigo` = " + cestablecimiento);
            conn.resultado.next();
            cboclasificacion.setSelectedItem(conn.resultado.getString("cla_descri"));           
            if(operacion == 3){
            txtdescripcion.setEnabled(true);
            cboclasificacion.setEnabled(true);
            txtminorista.setEnabled(true);
            txtdescripcion.requestFocus();
            grilla.setEnabled(true);
            } else {
                  int mensaje = JOptionPane.showConfirmDialog(this,"Deseas Borrar-->"+txtdescripcion.getText(),"Confirmar",JOptionPane.YES_NO_OPTION);
                  if(mensaje == JOptionPane.YES_OPTION)//si quiere borrar
                  {
                      operaciones();
                  }
                  else // no quiere borrar
                  {
                      JOptionPane.showMessageDialog(null, "Operacion Cancelada", "Atención", JOptionPane.ERROR_MESSAGE);
                  }
                  btncancelar.doClick();
            }
        } catch (SQLException ex) {
            Logger.getLogger(articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_grillaMouseClicked

    private void btnagregarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnagregarKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.btnagregar.doClick();
        }
    }//GEN-LAST:event_btnagregarKeyPressed

    private void btnborrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnborrarKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.btnborrar.doClick();
        }
    }//GEN-LAST:event_btnborrarKeyPressed

    private void btnmodificarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnmodificarKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.btnmodificar.doClick();
        }
    }//GEN-LAST:event_btnmodificarKeyPressed

    private void btngrabarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btngrabarKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.btngrabar.doClick();
        }
    }//GEN-LAST:event_btngrabarKeyPressed

    private void btncancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btncancelarKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.btncancelar.doClick();
        }
    }//GEN-LAST:event_btncancelarKeyPressed

    private void cboclasificacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboclasificacionKeyPressed
        if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            cboclasificacion.setEnabled(false);
            txtminorista.setEnabled(true);
            txtminorista.requestFocus();
        }
    }//GEN-LAST:event_cboclasificacionKeyPressed

    private void txtminoristaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtminoristaActionPerformed
        String campo = txtminorista.getText();
        if (campo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo está vacío, Cargue los datos correctamente");
            return;
        } else {
            txtminorista.setText(String.valueOf(Redondear(Float.parseFloat(txtminorista.getText()))));
            txtminorista.setEnabled(false);
            btngrabar.setEnabled(true);
            btngrabar.requestFocus();
        }
    }//GEN-LAST:event_txtminoristaActionPerformed

    private void txtminoristaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtminoristaKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
            && (c != '.')) {
            evt.consume();
        }
        if (c == '.' && txtminorista.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtminoristaKeyTyped

    private void txtminoristaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtminoristaKeyPressed
      
char c = evt.getKeyChar();
        if (((c > '0') || (c < '9')) || (c == '.')) {
           TimerTask tarea = new TimerTask(){
            public void run() {               
            //precio();
            }
            };
            Timer temp = new Timer();
            temp.schedule(tarea, 50);}
    }//GEN-LAST:event_txtminoristaKeyPressed

    private void txtdescripcionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtdescripcionMouseClicked
if (operacion != 0){
txtdescripcion.setEnabled(true);
cboclasificacion.setEnabled(false);
txtminorista.setEnabled(false);
txtdescripcion.requestFocus();
}
    }//GEN-LAST:event_txtdescripcionMouseClicked

    private void cboclasificacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboclasificacionMouseClicked
if (operacion != 0){
cboclasificacion.setEnabled(true);
txtdescripcion.setEnabled(false);
txtminorista.setEnabled(false);
cboclasificacion.requestFocus();
}
    }//GEN-LAST:event_cboclasificacionMouseClicked

    private void txtminoristaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtminoristaMouseClicked
if (operacion != 0){
txtminorista.setEnabled(true);
txtdescripcion.setEnabled(false);
cboclasificacion.setEnabled(false);
txtminorista.requestFocus();
}
    }//GEN-LAST:event_txtminoristaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            ver_conex  conn = new ver_conex();
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT art_codigo,art_descri FROM articulo WHERE art_estado LIKE 'AC'");
            modelo1.setRowCount(0);
            Object [] fila = new Object[2];
            while (conn.resultado.next()){
                fila[0] = conn.resultado.getObject(1);
                fila[1] = conn.resultado.getObject(2);
                modelo1.addRow(fila);
            }
            conn.resultado.first();
            ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
            buscador.setIconImage(icono.getImage());
            buscador.setTitle("Articulo");
            buscador.pack();
            buscador.setLocationRelativeTo(null);
            buscador.setModal(true);
            txtbuscador.requestFocus();
            buscador.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void grilla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grilla1MouseClicked
         String codigo = modelo1.getValueAt(grilla1.getSelectedRow(), 0).toString();
        String descri = modelo1.getValueAt(grilla1.getSelectedRow(), 1).toString();
        txtcodigo.setText(codigo);
        txtdescripcion.setText(descri);
        txtcodigo.setEnabled(true);
        buscador.setVisible(false);
        buscador.setModal(false);
        txtbuscador.setText("");
        try {
            ver_conex conn = new ver_conex();//instanciamos
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM `articulo` WHERE `art_codigo` = " + codigo);
            conn.resultado.next();
            txtminorista.setText(conn.resultado.getString("art_pvemin"));
            int cestablecimiento = conn.resultado.getInt("cla_codigo");
            Float pvemin = Float.parseFloat(conn.resultado.getString("art_pvemin"));
            txtminorista.setText(conn.resultado.getString("art_pvemin"));
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM clasificacion WHERE `cla_codigo` = " + cestablecimiento);
            conn.resultado.next();
            if(operacion == 3){
            txtdescripcion.setEnabled(true);
            cboclasificacion.setEnabled(true);
            txtminorista.setEnabled(true);
            txtdescripcion.requestFocus();
            grilla.setEnabled(true);
            } else {
                  int mensaje = JOptionPane.showConfirmDialog(this,"Deseas Borrar-->"+txtdescripcion.getText(),"Confirmar",JOptionPane.YES_NO_OPTION);
                  if(mensaje == JOptionPane.YES_OPTION)//si quiere borrar
                  {
                      operaciones();
                  }
                  else // no quiere borrar
                  {
                      JOptionPane.showMessageDialog(null, "Operacion Cancelada", "Atención", JOptionPane.ERROR_MESSAGE);
                  }
                  btncancelar.doClick();
               ////
            }
        } catch (SQLException ex) {
            Logger.getLogger(articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_grilla1MouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked

    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void txtbuscadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscadorKeyPressed
               
        if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_DOWN) {
            grilla1.requestFocus();
           evt.setKeyCode(java.awt.event.KeyEvent.VK_DOWN);
           {
             //este codigo lo que hace es convertir el enter en tab
           }
            
        } else {

        char c = evt.getKeyChar();
        if (c != ' ') {
           TimerTask tarea = new TimerTask(){
            public void run() {               
            busqueda();
            }
            };
            Timer temp = new Timer();
            temp.schedule(tarea, 50);}
        }
    }//GEN-LAST:event_txtbuscadorKeyPressed

    private void txtbuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscadorActionPerformed

    private void txtbuscadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscadorKeyTyped
        
    }//GEN-LAST:event_txtbuscadorKeyTyped

    private void grilla1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grilla1KeyPressed
         if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER)
        {
           String codigo = modelo1.getValueAt(grilla1.getSelectedRow(), 0).toString();
        String descri = modelo1.getValueAt(grilla1.getSelectedRow(), 1).toString();
        txtcodigo.setText(codigo);
        txtdescripcion.setText(descri);
        txtcodigo.setEnabled(true);
        buscador.setVisible(false);
        buscador.setModal(false);
        txtbuscador.setText("");
        try {
            ver_conex conn = new ver_conex();//instanciamos
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM `articulo` WHERE `art_codigo` = " + codigo);
            conn.resultado.next();
            txtminorista.setText(conn.resultado.getString("art_pvemin"));
            int cestablecimiento = conn.resultado.getInt("cla_codigo");
            Float pvemin = Float.parseFloat(conn.resultado.getString("art_pvemin"));
            txtminorista.setText(conn.resultado.getString("art_pvemin"));
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM clasificacion WHERE `cla_codigo` = " + cestablecimiento);
            conn.resultado.next();
            if(operacion == 3){
            txtdescripcion.setEnabled(true);
            cboclasificacion.setEnabled(true);
            txtminorista.setEnabled(true);
            txtdescripcion.requestFocus();
            grilla.setEnabled(true);
            } else {
                  int mensaje = JOptionPane.showConfirmDialog(this,"Deseas Borrar-->"+txtdescripcion.getText(),"Confirmar",JOptionPane.YES_NO_OPTION);
                  if(mensaje == JOptionPane.YES_OPTION)//si quiere borrar
                  {
                      operaciones();
                  }
                  else // no quiere borrar
                  {
                      JOptionPane.showMessageDialog(null, "Operacion Cancelada", "Atención", JOptionPane.ERROR_MESSAGE);
                  }
                  btncancelar.doClick();
               ////
            }
        } catch (SQLException ex) {
            Logger.getLogger(articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_grilla1KeyPressed

    private void grillaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_grillaKeyPressed
if (evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER)
        {
        String codigo = modelo.getValueAt(grilla.getSelectedRow(), 0).toString();
        String descri = modelo.getValueAt(grilla.getSelectedRow(), 1).toString();
        txtcodigo.setText(codigo);
        txtdescripcion.setText(descri);
        txtcodigo.setEnabled(true);
        buscador.setVisible(false);
        buscador.setModal(false);
        txtbuscador.setText("");
        try {
            ver_conex conn = new ver_conex();//instanciamos
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM `articulo` WHERE `art_codigo` = " + codigo);
            conn.resultado.next();
            txtminorista.setText(conn.resultado.getString("art_pvemin"));
            int cestablecimiento = conn.resultado.getInt("cla_codigo");
            Float pvemin = Float.parseFloat(conn.resultado.getString("art_pvemin"));;
            txtminorista.setText(conn.resultado.getString("art_pvemin"));
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery("SELECT * FROM clasificacion WHERE `cla_codigo` = " + cestablecimiento);
            conn.resultado.next();
            cboclasificacion.setSelectedItem(conn.resultado.getString("cla_descri"));
            if(operacion == 3){
            txtdescripcion.setEnabled(true);
            cboclasificacion.setEnabled(true);
            txtminorista.setEnabled(true);
            txtdescripcion.requestFocus();
            grilla.setEnabled(true);
            } else {
                  int mensaje = JOptionPane.showConfirmDialog(this,"Deseas Borrar-->"+txtdescripcion.getText(),"Confirmar",JOptionPane.YES_NO_OPTION);
                  if(mensaje == JOptionPane.YES_OPTION)//si quiere borrar
                  {
                      operaciones();
                  }
                  else // no quiere borrar
                  {
                      JOptionPane.showMessageDialog(null, "Operacion Cancelada", "Atención", JOptionPane.ERROR_MESSAGE);
                  }
                  btncancelar.doClick();
               ////
            }
        } catch (SQLException ex) {
            Logger.getLogger(articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_grillaKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btnagregar;
    private javax.swing.JButton btnborrar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btngrabar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JDialog buscador;
    private javax.swing.JComboBox cboclasificacion;
    private javax.swing.JTable grilla;
    private javax.swing.JTable grilla1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtbuscador;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtdescripcion;
    private javax.swing.JTextField txtminorista;
    // End of variables declaration//GEN-END:variables
}

