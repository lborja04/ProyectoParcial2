/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoparcial2.control;

import ec.edu.espol.proyectoparcial2.modelo.Oferta;
import ec.edu.espol.proyectoparcial2.modelo.Usuario;
import ec.edu.espol.proyectoparcial2.modelo.Utilitaria;
import ec.edu.espol.proyectoparcial2.modelo.Vehiculo;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luisa
 */
public class OfertasVehiculosController {

    @FXML
    private ChoiceBox<Vehiculo> vehisCbox;
    @FXML
    private ChoiceBox<Oferta> ofertasCbox;
    @FXML
    private Label tituloTxt;
    @FXML
    private TextField vehiPlacatxt;
    @FXML
    private TextField vehiTxt;
    @FXML
    private ImageView vehiImg;
    @FXML
    private TextField compradorOfertatxt;
    @FXML
    private TextField precioOfertaTxt;
    @FXML
    private Button aceptarBoton;
    @FXML
    private Button botonAtras;

    private Usuario usuario;
    private ArrayList<Vehiculo> vehisTot=Vehiculo.readListFileSer("vehiculos.ser");
    private Oferta oferta;
    
    public void setUsuario(Usuario user){
        usuario=user;
        ArrayList<Vehiculo> v=new ArrayList<>();
        for(Vehiculo vehi:vehisTot){
            if(vehi.getVendedor().equals(usuario))
                v.add(vehi);
        }
        vehisCbox.getItems().setAll(v);
        vehisCbox.setOnAction(this::llenarOfertas);
    }

    public void llenarOfertas(ActionEvent e){
        clearInfoVehi();
        Vehiculo vehi=vehisCbox.getValue();
        vehiPlacatxt.setText(vehi.getPlaca());
        vehiTxt.setText(vehi.toString());
        vehiImg.setImage(new Image(vehi.getImagen()));
        ArrayList<Oferta> ofertas=new ArrayList<>();
        for(Vehiculo v:vehisTot){
            if(vehi.equals(v))
                ofertas=v.getOfertas();
        }
        ofertasCbox.getItems().addAll(ofertas);
        ofertasCbox.setOnAction(this::infoOferta);
    }
    
    public void infoOferta(ActionEvent e){
        clearInfoOferta();
        oferta=ofertasCbox.getValue();
        compradorOfertatxt.setText(oferta.getComprador().toString());
        precioOfertaTxt.setText(oferta.getPrecioOferta()+"");
    }

    public void clearInfoVehi(){
        ofertasCbox.getItems().clear();
        oferta=null;
        vehiPlacatxt.clear();
        vehiTxt.clear();
        vehiImg.setImage(null);
    }
    
    public void clearInfoOferta(){
        compradorOfertatxt.clear();
        precioOfertaTxt.clear();
    }
    
    @FXML
    private void aceptarOferta(ActionEvent event) throws IOException{
        if(oferta!=null){
            Alert a=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere aceptar esta oferta?");
            if(a.showAndWait().get()==ButtonType.OK){
                String asunto="OFERTA ACEPTADA";
                String cuerpo="SU OFERTA POR "+oferta.getVehiculo()+" HA SIDO ACEPTADA, EL VENDEDOR PRONTO SE PONDRÁ EN CONTACTO.";                                                
                Utilitaria.enviarConGMail(oferta.getComprador().getCorreo(), asunto, cuerpo);
                for(int i=0;i<vehisTot.size();i++){
                    if(vehisTot.get(i).equals(oferta.getVehiculo()))
                        vehisTot.remove(i);
                }
                Vehiculo.saveListFileSer("vehiculos.ser", vehisTot);
                cambiarPantallaUsua(event);
            }
        }
        else{
            Alert a=new Alert(Alert.AlertType.ERROR,"No ha seleccionado una oferta");
            a.show();
        }
    }

    private void cambiarPantallaUsua(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectoparcial2/MenuUsuario.fxml"));
        Parent root = (Parent) loader.load();
        MenuUsuarioController menuController=loader.getController();
        menuController.setUsuario(usuario);
        menuController.mostrarUsuario(usuario.toString());
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }
    
    @FXML
    private void atras(ActionEvent event) throws IOException{
        Alert alertaLogout=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere retroceder?");
        if(alertaLogout.showAndWait().get()==ButtonType.OK){
            cambiarPantallaUsua(event);
        }
    }
    
}
