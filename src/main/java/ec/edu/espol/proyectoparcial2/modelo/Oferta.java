/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoparcial2.modelo;

import java.io.Serializable;

/**
 *
 * @author luisa
 */
public class Oferta implements Serializable{
    
    
    private static final long serialVersionUID = 1L;
    private int id_comprador;
    private Usuario comprador;
    private String placa_vehiculo;
    private Vehiculo vehiculo;
    private double precioOferta;
    
    
    public Oferta(Usuario comprador, Vehiculo vehiculo, double precioOferta){
        this.id_comprador=comprador.getID();
        this.comprador=comprador;
        this.placa_vehiculo=vehiculo.getPlaca();
        this.vehiculo=vehiculo;
        this.precioOferta=precioOferta;
    }
    
    public Oferta(int id_comprador, String id_vehiculo, double precioOferta){
        this.id_comprador=id_comprador;
        this.comprador=Utilitaria.obtenerUsuarioPorID(id_comprador);
        this.placa_vehiculo=id_vehiculo;
        this.vehiculo=Utilitaria.obtenerVehiculoPorPlaca(placa_vehiculo);
        this.precioOferta=precioOferta;
    }

    public int getId_comprador() {
        return id_comprador;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public double getPrecioOferta() {
        return precioOferta;
    }
    
    @Override
    public String toString(){
        return comprador.toString();
    }
    
}


