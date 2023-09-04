/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.proyectoparcial2.modelo;

/**
 *
 * @author luisa
 */
public class Camioneta extends Carro{
    private String traccion;
    
    public String getTraccion(){
        return traccion;
    }
    
    public Camioneta(int id_vendedor, String placa, String marca, String modelo, String tipoMotor,int anio, double reco, String color, String tipoComb,double precio,int vidrios, String transmision, String traccion){
        super(id_vendedor,placa,marca,modelo,tipoMotor,anio,reco,color,tipoComb,precio,vidrios,transmision);
        this.tipo=TipoVehiculo.CAMIONETA;
        this.traccion=traccion;
    }

}