/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import prgs.ver_conex;

/**
 *
 * @author Luke
 */
public class menu extends javax.swing.JFrame {
    DefaultTableModel modelo = new DefaultTableModel(){
     @Override
        public boolean isCellEditable(int row, int column) {
        return false;
    }
};

   
    int permiso,usuario;
    public static int cotizaciong = 0;
    static JDialog jgenerador;
    String Fecha,fechahora;
    static int apertura = 0;
    static String operacion = "";
    static float mapertura;
    String wqqqq;
    private int ab;
    java.sql.Statement snt;
    ResultSet recur1;  
    
    public menu() throws SQLException, IOException, InterruptedException {
        initComponents();
        this.setLocationRelativeTo(null);
        setExtendedState(menu.MAXIMIZED_BOTH);
        String icon = "\\amnesia\\src\\imag\\software.png";
        ImageIcon icono = new ImageIcon(icon);
        this.setIconImage(icono.getImage());
        usuario = acceso.usuario;
        titulo();
        cabecerabuscador();
    }


 private void backup() throws IOException, InterruptedException{
System.out.println("llego antes de fecha");
Fecha(); 
System.out.println("llego antes del vbs");
Process p = Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + "C:\\amnesia\\src\\back\\correr.vbs");
System.out.println("llego despues del vbs");   
    String dirWeb = "www.google.com";
        int puerto = 80;
    try{
                        Socket s = new Socket(dirWeb, puerto);
                        if(s.isConnected()){
                        
                           try
        {
            // se obtiene el objeto Session. La configuración es para
          // una cuenta de gmail.
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "backupsuarezlucas28@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);
            // session.setDebug(true);

            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText("Este es un backup de base de datos.-");

            // Se compone el adjunto con la imagen
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(
                new DataHandler(new FileDataSource("\\amnesia\\src\\back\\amnesia_"+fechahora+".sql")));
            adjunto.setFileName("amnesia_"+fechahora+".sql");

            // Una MultiParte para agrupar texto e imagen.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);

            // Se compone el correo, dando to, from, subject y el
            // contenido.
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("yo@yo.com"));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress("backupsuarezlucas28@gmail.com"));
            message.setSubject("amnesia_"+fechahora+".sql");
            message.setContent(multiParte);

            // Se envia el correo.
            Transport t = session.getTransport("smtp");
            t.connect("backupsuarezlucas28@gmail.com", "infogeniusjs");
            t.sendMessage(message, message.getAllRecipients());
            t.close();           
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } 
                            
                        }
        }catch(Exception e){
                System.err.println("No se pudo establecer conexión con: " + dirWeb + " a travez del puerto: " + puerto);
        }

}
       
   private void Fecha(){
Calendar c = new GregorianCalendar();
String dia, mes, annio,hora,minuto;
dia = Integer.toString(c.get(Calendar.DATE));
int a = Integer.parseInt(dia);
if (a <= 9)dia = "0"+dia;
mes = Integer.toString(c.get(Calendar.MONTH)+1);
int b = Integer.parseInt(mes);
if (b <= 9)mes = "0"+mes;
annio = Integer.toString(c.get(Calendar.YEAR));
hora = Integer.toString(c.get(Calendar.HOUR_OF_DAY));
int ihora = Integer.parseInt(hora);
if (ihora >= 12)ihora = ihora-12;
hora = String.valueOf(ihora);
int d = Integer.parseInt(hora);
if (d <= 9)hora = "0"+hora;
minuto = Integer.toString(c.get(Calendar.MINUTE));
int e = Integer.parseInt(minuto);
if (e <= 9)minuto = "0"+minuto;
fechahora = (annio + "-" + mes + "-" + dia + "@" + hora + "-" + minuto);
Fecha = (annio + "-" + mes + "-" + dia);
}
    

   private void titulo() throws SQLException{
        ver_conex  conn = new ver_conex();
        conn.sentencia = conn.conexion.createStatement();
        conn.resultado = conn.sentencia.executeQuery("SELECT UCASE(usu_usuari) FrOM USUARIOS WHERE usu_codigo = " + usuario);
        conn.resultado.next();
        String nombrep = conn.resultado.getString("UCASE(usu_usuari)");
        setTitle("MENU PRINCIPAL - USUARIO: "+nombrep);
   }
   
           private void cabecerabuscador(){
        modelo.addColumn("CODIGO");
        modelo.addColumn("DESCRIPCIÓN");
        grilla.getColumnModel().getColumn(0).setPreferredWidth(51);
        grilla.getColumnModel().getColumn(1).setPreferredWidth(151);
}
   
    public void grilla1()
       {
         String ac;
          modelo.setRowCount(0);
        
        try {
            ver_conex conn2 =new ver_conex();//instanciamos
            snt = conn2.conexion.createStatement();
            recur1=snt.executeQuery("SELECT art_codigo,art_descri FROM articulo WHERE art_estado LIKE 'ACTIVO' AND art_descri LIKE '%"+wqqqq+"%'");
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
                    
                    modelo.addRow(new Object[]{ab,ac});
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
     
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        aviso = new javax.swing.JDialog();
        jLabel4 = new javax.swing.JLabel();
        buscador = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        grilla = new javax.swing.JTable();
        txtbuscador = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cotizacion = new javax.swing.JDialog();
        txtprecio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jMenuItem20 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Espere por favor, el sistema esta realizando un resguardo de la base de datos.-");

        javax.swing.GroupLayout avisoLayout = new javax.swing.GroupLayout(aviso.getContentPane());
        aviso.getContentPane().setLayout(avisoLayout);
        avisoLayout.setHorizontalGroup(
            avisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avisoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        avisoLayout.setVerticalGroup(
            avisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(avisoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

        grilla.setModel(modelo);
        grilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grillaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(grilla);

        txtbuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscadorKeyPressed(evt);
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
                    .addGroup(buscadorLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(buscadorLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscador)))
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

        txtprecio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtprecio.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtprecio.setEnabled(false);
        txtprecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtprecioMouseClicked(evt);
            }
        });
        txtprecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprecioActionPerformed(evt);
            }
        });
        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Cotización Guaraní:");

        jButton1.setText("Listo");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout cotizacionLayout = new javax.swing.GroupLayout(cotizacion.getContentPane());
        cotizacion.getContentPane().setLayout(cotizacionLayout);
        cotizacionLayout.setHorizontalGroup(
            cotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cotizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(14, 14, 14)
                .addComponent(txtprecio, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(cotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cotizacionLayout.createSequentialGroup()
                    .addGap(24, 24, 24)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addGap(25, 25, 25)))
        );
        cotizacionLayout.setVerticalGroup(
            cotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cotizacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(cotizacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(cotizacionLayout.createSequentialGroup()
                    .addGap(51, 51, 51)
                    .addComponent(jButton1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("CD PRODUCCIONES: MENU PRINCIPAL");
        setMinimumSize(new java.awt.Dimension(1024, 700));

        jMenuBar1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jMenu3.setText("MOVIMIENTOS");
        jMenu3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem3.setText("VENTAS EN BARRA");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);
        jMenu3.add(jSeparator2);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem5.setText("ANULAR VENTAS");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);
        jMenu3.add(jSeparator3);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem7.setText("COBROS");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);
        jMenu3.add(jSeparator5);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem8.setText("ANULAR COBROS");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);
        jMenu3.add(jSeparator9);

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem12.setText("VENTAS EN MESAS");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem12);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("REFERENCIALES");
        jMenu1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem1.setText("USUARIOS");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem2.setText("PERMISOS");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator4);

        jMenuItem6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem6.setText("ARTÍCULOS");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);
        jMenu1.add(jSeparator15);

        jMenuItem20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem20.setText("CLIENTES");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem20);
        jMenu1.add(jSeparator8);

        jMenuItem21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem21.setText("MESAS");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem21);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("INFORMES");
        jMenu2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jMenuItem9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem9.setText("INFORME DE VENTAS");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);
        jMenu2.add(jSeparator6);

        jMenuItem10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem10.setText("INFORME DE COBROS");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);
        jMenu2.add(jSeparator7);

        jMenuItem11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem11.setText("INFORME DE CUENTA CORRIENTE");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("SALIR");
        jMenu4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jMenuItem4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jMenuItem4.setText("CONFIRMAR");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 734, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1039, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
/*if (apertura > 0){
            int mensaje = JOptionPane.showConfirmDialog(this, "Aun no se ha registrado el cierre de caja,desea hacerlo ahora?", "Atención", JOptionPane.YES_NO_OPTION);
            if (mensaje == JOptionPane.YES_OPTION) {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new apercierre());
                jgenerador.setTitle("APERTURA Y CIERRE DE CAJA");
                ImageIcon icono = new ImageIcon("\\SISTEMA\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                apercierre.btnabrir.requestFocus();
                jgenerador.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "No se Puede Cerrar el Sistema, sin un Cierre de Caja ", "Atencion", JOptionPane.ERROR_MESSAGE);
            }
        } else {
               TimerTask tarea = new TimerTask(){
                public void run() {               
                       try {
                           backup();
                           System.out.println("salgo");*/
                           System.exit(0);/*
                       } catch (IOException ex) {
                           Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                       } catch (InterruptedException ex) {
                           Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                       }
                    }
                    };
                    Timer temp = new Timer();
            temp.schedule(tarea, 100);            
                aviso.setTitle("ATENCIÓN, AGUARDE..");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                aviso.setIconImage(icono.getImage());
                aviso.pack();
                aviso.setModal(true);
                aviso.setLocationRelativeTo(null);
                aviso.setResizable(false);
                aviso.setVisible(true);
                
            //} // TODO add your handling code here:
                // TODO add your handling code here:*/
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (usuario != 1){
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 11 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new usuarios());
                jgenerador.setTitle("USUARIOS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                usuarios.btnagregar.requestFocus();
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        }
        } else {
            try {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new usuarios());
                jgenerador.setTitle("USUARIOS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                usuarios.btnagregar.requestFocus();
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (usuario != 1){
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 7 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new permisos());
                jgenerador.setTitle("PERMISOS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                permisos.btngestionar.requestFocus();
                jgenerador.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        }
        } else {
     try {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new permisos());
                jgenerador.setTitle("PERMISOS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                permisos.btngestionar.requestFocus();
                jgenerador.setVisible(true);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
}
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        if (usuario != 1){   
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 5 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new articulos());
                jgenerador.setTitle("ARTÍCULOS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                articulos.btnagregar.requestFocus();
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        }
        } else {
            try {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new articulos());
                jgenerador.setTitle("ARTÍCULOS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                articulos.btnagregar.requestFocus();
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        if (usuario != 1){
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 6 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new clientes());
                jgenerador.setTitle("CLIENTES");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                clientes.btnagregar.requestFocus();
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        } 
        } else {
          try {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new clientes());
                jgenerador.setTitle("CLIENTES");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                clientes.btnagregar.requestFocus();
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void grillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grillaMouseClicked
        /*String codigo = modelo.getValueAt(grilla.getSelectedRow(), 0).toString();
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
            conn.resultado = conn.sentencia.executeQuery("SELECT art_pvemin,art_pcosto,art_pvemay,art_descri,lab_descri,art_canact FROM `articulo`,laboratorios,stock WHERE articulo.lab_codigo = laboratorios.lab_codigo and articulo.art_codigo = stock.art_codigo and articulo.`art_codigo` = " + txtcodigo.getText() + " and art_estado like 'ACTIVO'");
            conn.resultado.next();
            txtminorista.setText(conn.resultado.getString("art_pvemin"));
            txtmayorista.setText(conn.resultado.getString("art_pvemay"));
            txtdescripcion.setText(conn.resultado.getString("art_descri"));
            txtdescripcion1.setText(conn.resultado.getString("lab_descri"));
            txtmayorista1.setText(conn.resultado.getString("art_pcosto"));
            txtmayorista2.setText(conn.resultado.getString("art_canact"));
            String SQL = "select * from descripcion where art_codigo = " + txtcodigo.getText();
            conn.sentencia = conn.conexion.createStatement();
            conn.resultado = conn.sentencia.executeQuery(SQL);
            boolean encontro = conn.resultado.next();
            if(encontro == true){
            jTextArea1.setText(conn.resultado.getString("des_descri"));
            } else {
            jTextArea1.setText("");
            }            
            txtcodigo.setText("");
            txtcodigo.requestFocus();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No posee este articulo en el sistema" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
            txtcodigo.setText("");
            txtcodigo.requestFocus();
        }  */  
    }//GEN-LAST:event_grillaMouseClicked

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked

    }//GEN-LAST:event_jScrollPane2MouseClicked

    private void txtbuscadorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscadorKeyPressed
        wqqqq = txtbuscador.getText();
        grilla1();
    }//GEN-LAST:event_txtbuscadorKeyPressed

    private void txtprecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtprecioMouseClicked

    }//GEN-LAST:event_txtprecioMouseClicked

    private void txtprecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprecioActionPerformed
        String campo = txtprecio.getText();
        if (campo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo está vacío, Cargue los datos correctamente");
            return;
        } else {
            //txtprecio.setText(String.valueOf(Redondear(Float.parseFloat(txtprecio.getText()))));
            jButton1.setEnabled(true);
            jButton1.requestFocus();
            txtprecio.setEnabled(false);
        }
    }//GEN-LAST:event_txtprecioActionPerformed

    private void txtprecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
            ) {
            evt.consume();
        }
        if (c == '.' && txtprecio.getText().contains(".")) {
            evt.consume();
        }
    }//GEN-LAST:event_txtprecioKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cotizaciong = Integer.parseInt(txtprecio.getText());
        cotizacion.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
if (evt.getKeyChar() == KeyEvent.VK_ENTER)
        {
            this.jButton1.doClick();
        }  
    }//GEN-LAST:event_jButton1KeyPressed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
     if (usuario != 1){
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 1 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new venta());
                jgenerador.setTitle("VENTAS EN BARRA");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        } 
        } else {
         try {
             jgenerador = new JDialog();
             jgenerador.getContentPane().add(new venta());
             jgenerador.setTitle("VENTAS EN BARRA");
             ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
             jgenerador.setIconImage(icono.getImage());
             jgenerador.pack();
             jgenerador.setLocationRelativeTo(null);
             jgenerador.setModal(true);
             jgenerador.setResizable(false);  
             jgenerador.setVisible(true);
         } catch (SQLException ex) {
             Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (usuario != 1){
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 2 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                try {
                 jgenerador.getContentPane().add(new anularventa());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                jgenerador.setTitle("ANULAR VENTAS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        } 
        } else {
         try {
             jgenerador = new JDialog();
             try {
                 jgenerador.getContentPane().add(new anularventa());
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InstantiationException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             }
             jgenerador.setTitle("ANULAR VENTAS");
             ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
             jgenerador.setIconImage(icono.getImage());
             jgenerador.pack();
             jgenerador.setLocationRelativeTo(null);
             jgenerador.setModal(true);
             jgenerador.setResizable(false);  
             jgenerador.setVisible(true);
         } catch (SQLException ex) {
             Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        if (usuario != 1){
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 3 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                try {
                 jgenerador.getContentPane().add(new cobros());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                jgenerador.setTitle("COBROS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        } 
        } else {
         try {
             jgenerador = new JDialog();
             try {
                 jgenerador.getContentPane().add(new cobros());
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InstantiationException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             }
             jgenerador.setTitle("COBROS");
             ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
             jgenerador.setIconImage(icono.getImage());
             jgenerador.pack();
             jgenerador.setLocationRelativeTo(null);
             jgenerador.setModal(true);
             jgenerador.setResizable(false);  
             jgenerador.setVisible(true);
         } catch (SQLException ex) {
             Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
    if (usuario != 1){
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 4 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                try {
                 jgenerador.getContentPane().add(new anularcobros());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                jgenerador.setTitle("ANULAR COBROS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        } 
        } else {
         try {
             jgenerador = new JDialog();
             try {
                 jgenerador.getContentPane().add(new anularcobros());
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InstantiationException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             }
             jgenerador.setTitle("ANULAR COBROS");
             ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
             jgenerador.setIconImage(icono.getImage());
             jgenerador.pack();
             jgenerador.setLocationRelativeTo(null);
             jgenerador.setModal(true);
             jgenerador.setResizable(false);  
             jgenerador.setVisible(true);
         } catch (SQLException ex) {
             Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
if (usuario != 1){
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 8 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                try {
                    jgenerador.getContentPane().add(new infoventa());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                jgenerador.setTitle("INFORME DE VENTAS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        } 
        } else {
         try {
             jgenerador = new JDialog();
             try {
                 jgenerador.getContentPane().add(new infoventa());
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InstantiationException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             }
             jgenerador.setTitle("INFORME DE VENTAS");
             ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
             jgenerador.setIconImage(icono.getImage());
             jgenerador.pack();
             jgenerador.setLocationRelativeTo(null);
             jgenerador.setModal(true);
             jgenerador.setResizable(false);  
             jgenerador.setVisible(true);
         } catch (SQLException ex) {
             Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
   if (usuario != 1){
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 9 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                try {
                    jgenerador.getContentPane().add(new infocobros());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                jgenerador.setTitle("INFORME DE COBROS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        } 
        } else {
         try {
             jgenerador = new JDialog();
             try {
                 jgenerador.getContentPane().add(new infocobros());
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InstantiationException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             }
             jgenerador.setTitle("INFORME DE COBROS");
             ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
             jgenerador.setIconImage(icono.getImage());
             jgenerador.pack();
             jgenerador.setLocationRelativeTo(null);
             jgenerador.setModal(true);
             jgenerador.setResizable(false);  
             jgenerador.setVisible(true);
         } catch (SQLException ex) {
             Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
    if (usuario != 1){
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 10 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                try {
                    jgenerador.getContentPane().add(new infocc());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                jgenerador.setTitle("INFORME DE CUENTA CORRIENTE");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        } 
        } else {
         try {
             jgenerador = new JDialog();
             try {
                 jgenerador.getContentPane().add(new infocc());
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InstantiationException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IllegalAccessException ex) {
                 Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
             }
             jgenerador.setTitle("INFORME DE CUENTA CORRIENTE");
             ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
             jgenerador.setIconImage(icono.getImage());
             jgenerador.pack();
             jgenerador.setLocationRelativeTo(null);
             jgenerador.setModal(true);
             jgenerador.setResizable(false);  
             jgenerador.setVisible(true);
         } catch (SQLException ex) {
             Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
if (usuario != 1){
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 11 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new mesas());
                jgenerador.setTitle("MESAS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                clientes.btnagregar.requestFocus();
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        } 
        } else {
          try {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new mesas());
                jgenerador.setTitle("MESAS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                mesas.btnagregar.requestFocus();
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
if (usuario != 1){
        ver_conex conn =new ver_conex();//instanciamos
        try {
            conn.sentencia = conn.conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado = conn.sentencia.executeQuery("SELECT pan_codigo,usu_codigo,per_permis FROM permisos WHERE pan_codigo = 12 and usu_codigo = " + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            conn.resultado.next();
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            permiso = conn.resultado.getInt("per_permis");
        } catch (SQLException ex) {
            Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(permiso == 1) // todo bien
        {
            try {
                jgenerador = new JDialog();
                jgenerador.getContentPane().add(new pedido());
                jgenerador.setTitle("VENTAS EN MESAS");
                ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
                jgenerador.setIconImage(icono.getImage());
                jgenerador.pack();
                jgenerador.setLocationRelativeTo(null);
                jgenerador.setModal(true);
                jgenerador.setResizable(false);
                jgenerador.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else // error
        {
            JOptionPane.showMessageDialog(null, "No tiene Permisos para acceder a este formulario" , "Verifique",
            JOptionPane.INFORMATION_MESSAGE);// salir del sistema
        } 
        } else {
         try {
             jgenerador = new JDialog();
             jgenerador.getContentPane().add(new pedido());
             jgenerador.setTitle("VENTAS EN MESAS");
             ImageIcon icono = new ImageIcon("\\amnesia\\src\\imag\\software.png");
             jgenerador.setIconImage(icono.getImage());
             jgenerador.pack();
             jgenerador.setLocationRelativeTo(null);
             jgenerador.setModal(true);
             jgenerador.setResizable(false);  
             jgenerador.setVisible(true);
         } catch (SQLException ex) {
             Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
         }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    try {
                        new menu().setVisible(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog aviso;
    private javax.swing.JDialog buscador;
    private javax.swing.JDialog cotizacion;
    private javax.swing.JTable grilla;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JTextField txtbuscador;
    private javax.swing.JTextField txtprecio;
    // End of variables declaration//GEN-END:variables
}
