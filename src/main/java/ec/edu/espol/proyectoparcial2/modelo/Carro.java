/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ec.edu.espol.proyectoparcial2.modelo;

/**
 *
 * @author luisa
 */
public class Carro extends Vehiculo{
    protected int vidrios;
    protected String transmision;
    
    public int getVidrios(){
        return vidrios;
    }
    
    public String getTransmision(){
        return transmision;
    }
    
    public Carro(int id_vendedor, String placa, String marca, String modelo, String tipoMotor,int anio, double reco, String color, String tipoComb,double precio,int vidrios, String transmision){
        super(id_vendedor,placa,marca,modelo,tipoMotor,anio,reco,color,tipoComb,precio);
        this.tipo=TipoVehiculo.CARRO;
        this.vidrios=vidrios;
        this.transmision=transmision;
    }

}