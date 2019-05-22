/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelo.Mensaje;
import modelo.Usuario;
import modelo.dao.MensajeDAO;
import modelo.dao.UsuarioDAO;
import vista.BandejaEntrada;
import vista.NuevoMensaje;

/**
 *
 * @author Isidora Albayay
 */
public class NuevoMensajeController implements ActionListener{
    NuevoMensaje nm;
    javax.swing.JComboBox<Usuario> destinatarioCB;
    BandejaEntrada be; 
    private UsuarioDAO usuarioDAO= new UsuarioDAO(); 
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    int uActual; 
    

    public NuevoMensajeController(BandejaEntrada be, NuevoMensaje nm, JComboBox<Usuario> destinatarioCB) {
        this.be = be; 
        this.nm = nm;
        this.destinatarioCB = destinatarioCB;
        
        usuarios = usuarioDAO.getUsuarios();
        
        for (int i = 0; i < usuarios.size(); i++) {
            this.destinatarioCB.addItem(usuarios.get(i)); 
        }
        
        uActual = be.getIdUsuarioLogueado(); 
    }

    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Usuario receptor = (Usuario) nm.getDestinatarioCB().getSelectedItem(); 
        String mensaje = (String) nm.getCuerpoMensajeTA().getText(); 
        Usuario emisor = usuarioDAO.getUsuariosById(uActual);
        
        Mensaje nuevoMensaje = new Mensaje(emisor, receptor, mensaje); 
        
        
        MensajeDAO mDAO = new MensajeDAO(); 
        
        try{
            
            boolean resp= mDAO.EnviarMensaje(nuevoMensaje);
           
            if(resp){
                JOptionPane.showMessageDialog(nm, "Venta Ingresada con exito");
                nm.dispose();
                
            }else{
               System.out.println("Error al registrar venta"); 
            }
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Error al registrar venta");
        }
    }
    
    
}
