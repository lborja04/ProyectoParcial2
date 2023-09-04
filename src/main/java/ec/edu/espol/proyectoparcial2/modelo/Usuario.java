/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoparcial2.modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author luisa
 */
public class Usuario implements Serializable{
    
    private static final long serialVersionUID=1L;
    private int id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String organizacion;
    private String clave;
    private ArrayList<Vehiculo> vehiculosEnVenta;

    public Usuario(String nombres, String apellidos, String correo, String organizacion, String clave) {
        this.id=Utilitaria.generarID("usuarios.ser");
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.organizacion = organizacion;
        this.clave = clave;
        this.vehiculosEnVenta=new ArrayList<>();
    }
    
    public Usuario(){
    }

    public ArrayList<Vehiculo> getVehiculos(){
        return vehiculosEnVenta;
    }
    
    public int getID(){
        return this.id;
    }
    
    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getOrganizacion() {
        return this.organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    public static void saveListFileSer(String nomfile, ArrayList<Usuario> usuarios){
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(nomfile))){
            out.writeObject(usuarios);
        }
        catch(IOException a){
            System.out.println(a.getMessage());
        }
        catch(Exception b){
            System.out.println(b.getMessage());
        }
    }
    
    public static ArrayList<Usuario> readListFileSer(String nomfile){
        ArrayList<Usuario> usuarios=new ArrayList<>();
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream(nomfile))){
            usuarios=(ArrayList<Usuario>) in.readObject();
        }
        catch(IOException a){
            System.out.println(a.getMessage());
        }
        catch(ClassNotFoundException b){
            System.out.println(b.getMessage());
        }
        catch(Exception c){
            System.out.println(c.getMessage());
        }
        return usuarios;
    }
    
    @Override
    public String toString(){
        return this.nombres+" "+this.apellidos;
    }
    
    @Override
    public boolean equals(Object o){
        if(o==null || this.getClass()!=o.getClass())
            return false;
        if(this==o)
            return true;
        Usuario other=(Usuario) o;
        if(this.id==other.id)
            return true;
        return false;
    }
    
}
