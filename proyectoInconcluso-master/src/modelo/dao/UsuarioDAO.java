/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author Isidora Albayay
 */
public class UsuarioDAO {
    Conexion con;

    public UsuarioDAO() {
        this.con = new Conexion();
    }
    
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection accesoBD = con.getConexion();
        try{
            String sql="SELECT * FROM usuario ";
            
            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
            
            while ( resultados.next() ) {
                int id_usuarior= Integer.parseInt(resultados.getString("id_usuario"));
                String nombre = resultados.getString("nombre");              
                
                usuarios.add(new Usuario(id_usuarior ,nombre));
            }
            accesoBD.close();
            return usuarios;
            
        }catch(Exception e){
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
    }
    
    public Usuario getUsuariosById(int id){
        Connection accesoBD = con.getConexion();
        Usuario usr = null; 
        try{
            String sql="SELECT * FROM usuario WHERE id = "+id;
            
            //System.out.println(sql);
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
            
            while ( resultados.next() ) {
                int id_usuarior= Integer.parseInt(resultados.getString("id_usuario"));
                String nombre = resultados.getString("nombre");              
                
                usr = new Usuario(id_usuarior ,nombre);
            }
            accesoBD.close();
            return usr;
            
        }catch(Exception e){
            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
        }
    }
}
