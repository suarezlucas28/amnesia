/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prgs;

import form.acceso;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class Amnesia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            new acceso().setVisible(true);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Amnesia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Amnesia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Amnesia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Amnesia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Amnesia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
