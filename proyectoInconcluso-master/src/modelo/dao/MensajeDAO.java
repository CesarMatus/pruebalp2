/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Mensaje;
import modelo.Usuario;

/**
 *
 * @author Isidora Albayay
 */
public class MensajeDAO {
    Conexion con;
    
    public MensajeDAO(){
        this.con=new Conexion();
    }
    
 
    
    public boolean EnviarMensaje(Mensaje nuevoMensaje) throws SQLException{
        Connection accesoBD = con.getConexion();
        
        
        String contenido = nuevoMensaje.getContenido();
        int id_usr_emisor = nuevoMensaje.getEmisor().getId_usuario();
        int id_usr_receptor= nuevoMensaje.getRemitente().getId_usuario();

        try{
            String sql="INSERT INTO venta (id_venta, sucursal, monto, fecha, id_vendedor) VALUES (NULL, '"+contenido+"', '"+id_usr_emisor+"', '"+id_usr_receptor+"')";
            
            
            //ejemplo: SELECT * FROM Customers WHERE Country='Mexico'; 
            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            
            
            st.executeUpdate(sql);
            System.out.println("Mensaje eviado con exito");
            accesoBD.close();
            return true;
            
           
        }catch (Exception e){
            System.out.println("Error al enviar mensaje");
            accesoBD.close();
            return false;
        }
        
    }
    
    public ArrayList<Mensaje> getMensajes() throws SQLException{
        ArrayList<Mensaje> mensajes = new ArrayList();
        Connection accesoBD = con.getConexion();

        try{
            String sql="SELECT * FROM mensaje";
            
            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
           
            
            while ( resultados.next() ) {
                int id_mensaje= Integer.parseInt(resultados.getString("id_mensaje"));
                String contenido = resultados.getString("contenido");
                int id_usr_emisor = resultados.getInt("id_usr_emisor");
                int id_usr_receptor= resultados.getInt("id_usr_receptorr");
                UsuarioDAO uDAO = new UsuarioDAO(); 
                
                Usuario emisor = uDAO.getUsuariosById(id_usr_emisor);
                Usuario receptor = uDAO.getUsuariosById(id_usr_receptor);
                
                try{
                    ResultSet resVendedor = st.executeQuery(sql);
                    while(resVendedor.next()){
                        
                        mensajes.add(new Mensaje(id_mensaje, emisor, receptor, contenido));
                    }
          
                }catch(Exception ex2){
                    System.out.println("Error al usuarios");
                }
            }
            accesoBD.close();
            return mensajes;
        }catch (Exception e){
            System.out.println("Error al obtener mensaje");
             accesoBD.close();
            return null;
        }
        
        
    }
    
}
