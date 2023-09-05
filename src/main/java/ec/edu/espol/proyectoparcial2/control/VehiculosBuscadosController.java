/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoparcial2.control;

import ec.edu.espol.proyectoparcial2.modelo.Camioneta;
import ec.edu.espol.proyectoparcial2.modelo.Carro;
import ec.edu.espol.proyectoparcial2.modelo.Oferta;
import ec.edu.espol.proyectoparcial2.modelo.Usuario;
import ec.edu.espol.proyectoparcial2.modelo.Vehiculo;
import java.io.File;
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
public class VehiculosBuscadosController{

    private ArrayList<Vehiculo> vehiculos;
    private Usuario usuario;
    private Vehiculo vehi;
    private ArrayList<Vehiculo> vehisTot;
    
    @FXML
    private Label numVehisLabel;
    @FXML
    private ChoiceBox<Vehiculo> vehisCbox;
    @FXML
    private Button botonOfertar;
    @FXML
    private TextField ofertarTxt;
    @FXML
    private TextField usertxt;
    @FXML
    private TextField motortxt;
    @FXML
    private TextField combustxt;
    @FXML
    private TextField colortxt;
    @FXML
    private TextField transmitxt;
    @FXML
    private TextField prectxt;
    @FXML
    private TextField vehitxt;
    @FXML
    private TextField aniotxt;
    @FXML
    private TextField recortxt;
    @FXML
    private TextField vidriostxt;
    @FXML
    private TextField tractxt;
    @FXML
    private ImageView vehiImg;
    @FXML
    private Button botonAtras;
    
    public void setTexto(int num){
        numVehisLabel.setText("Se han encontrado: "+ num +" vehículo(s) acorde a sus parámetros");
    }
    
    public void setVehisTot(ArrayList<Vehiculo> v){
        this.vehisTot=v;
    }
    
    public void setVehiculos(ArrayList<Vehiculo> v){
        this.vehiculos=v;
        vehisCbox.getItems().setAll(vehiculos);
        vehisCbox.setOnAction(this::cambioVehi);
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }

    public void cambioVehi(ActionEvent e){
        clear();
        vehi=vehisCbox.getValue();
        usertxt.setText(vehi.getVendedor().toString());
        vehitxt.setText(vehi.toString());
        motortxt.setText(vehi.getTipoMotor());
        aniotxt.setText(vehi.getAnio()+"");
        combustxt.setText(vehi.getTipoCombustible());
        recortxt.setText(vehi.getRecorrido()+"");
        colortxt.setText(vehi.getColor());
        vidriostxt.setText("N/A");
        transmitxt.setText("N/A");
        tractxt.setText("N/A");
        if(vehi instanceof Carro){
            Carro car=(Carro) vehi;
            vidriostxt.setText(car.getVidrios()+"");
            transmitxt.setText(car.getTransmision());
        }
        if(vehi instanceof Camioneta){
            Camioneta cam=(Camioneta) vehi;
            vidriostxt.setText(cam.getVidrios()+"");
            transmitxt.setText(cam.getTransmision());
            tractxt.setText(cam.getTraccion());
        }
        prectxt.setText(vehi.getPrecio()+"");
        
        String imagePath;
        if (getClass().getProtectionDomain().getCodeSource().getLocation().getPath().endsWith(".jar")) {
            String jarDir = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
            imagePath = jarDir + "/" + vehi.getImagen(); 
        } else {
            imagePath = vehi.getImagen();
        }

        vehiImg.setImage(new Image(new File(imagePath).toURI().toString()));
    }
    
    public void clear(){
        usertxt.setText("");
        vehitxt.setText("");
        motortxt.setText("");
        aniotxt.setText("");
        combustxt.setText("");
        recortxt.setText("");
        colortxt.setText("");
        vidriostxt.setText("");
        transmitxt.setText("");
        tractxt.setText("");
        prectxt.setText("");
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
    private void regresar(ActionEvent event) throws IOException{
        Alert alertaLogout=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere regresar al menú principal?");
        if(alertaLogout.showAndWait().get()==ButtonType.OK){
            cambiarPantallaUsua(event);
        }
    }

    @FXML
    private void ofertar(ActionEvent event) throws IOException{
        if(!vehi.getVendedor().equals(usuario)){
            Alert alerta=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere ofertar por "+vehi+" ?");
            if(alerta.showAndWait().get()==ButtonType.OK){
                if(vehi!=null){
                    try{
                        double precioOferta=Double.parseDouble(ofertarTxt.getText());
                        Oferta oferta=new Oferta(usuario,vehi,precioOferta);
                        vehi.getOfertas().add(oferta);
                        for(Vehiculo v:vehisTot){
                            if(vehi.equals(v))
                                v=vehi;
                        }
                        Vehiculo.saveListFileSer("vehiculos.ser", vehisTot);
                        Alert a=new Alert(Alert.AlertType.INFORMATION,"Se ha puesto su oferta");
                        a.show();
                        cambiarPantallaUsua(event);
                    }
                    catch(NumberFormatException e){
                        Alert a=new Alert(Alert.AlertType.ERROR,"Ingrese una oferta válida");
                        a.show();
                    }
                }
                else{
                    Alert a=new Alert(Alert.AlertType.ERROR,"No ha seleccionado vehículo");
                    a.show();
                }
            }
        }
        else{
            Alert a=new Alert(Alert.AlertType.ERROR,"No puede ofertar por su propio vehículo");
            a.show();
        }
    }
}
