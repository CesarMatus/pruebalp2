/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import modelo.dao.MensajeDAO;
import vista.BandejaEntrada;
import vista.NuevoMensaje;

/**
 *
 * @author Isidora Albayay
 */
public class BanjedaEntradaController implements ActionListener{
    BandejaEntrada be; 
    MensajeDAO mDAO = new MensajeDAO(); 
    javax.swing.JTable jTable1; 
    
    public BanjedaEntradaController(BandejaEntrada be, javax.swing.JTable jTable1) {
        this.be = be;
        this.jTable1 = this.jTable1; 
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        NuevoMensaje nm = new NuevoMensaje(be);
        nm.setVisible(true);
        
    }
    
    
    
    
    
}