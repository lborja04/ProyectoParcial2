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
public class Vehiculo implements Serializable{
    
    protected static final long serialVersionUID = 1L;
    protected int id_vendedor;
    protected Usuario vendedor;
    protected TipoVehiculo tipo;
    protected String placa;
    protected String marca;
    protected String modelo;
    protected String tipoMotor;
    protected int anio;
    protected double recorrido;
    protected String color;
    protected String tipoCombustible;
    protected double precio;
    protected ArrayList<Oferta> ofertas;
    protected String imagen;
    
    
    public Vehiculo(int id_vendedor, String placa, String marca, String modelo, String tipoMotor,int anio, double reco, String color, String tipoComb,double precio){
        this.id_vendedor=id_vendedor;
        this.vendedor=Utilitaria.obtenerUsuarioPorID(id_vendedor);
        this.tipo=TipoVehiculo.MOTO;
        this.placa=placa;
        this.marca=marca;
        this.modelo=modelo;
        this.tipoMotor=tipoMotor;
        this.anio=anio;
        this.recorrido=reco;
        this.color=color;
        this.tipoCombustible=tipoComb;
        this.precio=precio;
        this.ofertas=new ArrayList<>();
        this.imagen="";
    }

    public Vehiculo(){}
    
    public void setImagen(String imagen){
        this.imagen=imagen;
    }
    
    public String getImagen(){
        return this.imagen;
    }
    
    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public TipoVehiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public double getPrecio() {
        return precio;
    }

    public String getModelo(){
        return this.modelo;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    @Override
    public String toString(){
        return marca+" "+modelo;
    }
    
    public static void saveListFileSer(String nomfile, ArrayList<Vehiculo> vehiculos){
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(nomfile))){
            out.writeObject(vehiculos);
        }
        catch(IOException a){
            System.out.println(a.getMessage());
        }
        catch(Exception b){
            System.out.println(b.getMessage());
        }
    }
    
    public static ArrayList<Vehiculo> readListFileSer(String nomfile){
        ArrayList<Vehiculo> vehiculos=new ArrayList<>();
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream(nomfile))){
            vehiculos=(ArrayList<Vehiculo>) in.readObject();
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
        return vehiculos;
    }
 
    @Override
    public boolean equals(Object o){
        if(o==null || this.getClass()!=o.getClass())
            return false;
        if(this==o)
            return true;
        Vehiculo other=(Vehiculo) o;
        if(this.placa.equals(other.placa))
            return true;
        return false;
    }
    
}