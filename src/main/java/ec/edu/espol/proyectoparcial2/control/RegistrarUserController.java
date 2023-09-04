/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoparcial2.control;

import ec.edu.espol.proyectoparcial2.modelo.Usuario;
import ec.edu.espol.proyectoparcial2.modelo.Utilitaria;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class RegistrarUserController  {

    @FXML
    private Button regi;
    @FXML
    private TextField nom;
    @FXML
    private TextField ape;
    @FXML
    private TextField corre;
    @FXML
    private TextField org;
    @FXML
    private TextField cla;
   
   private void cambiarPantallaBusVehi(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectoparcial2/PantallaLogin.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }
   
    @FXML
    private void Atras(ActionEvent event) throws IOException {
        Alert alertaLogout=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere retroceder?");
        if(alertaLogout.showAndWait().get()==ButtonType.OK){
            cambiarPantallaBusVehi(event);
        }
    }
    
    @FXML
    private void Registrar(ActionEvent event) throws IOException{
        String nombre=nom.getText();
        String apellido=ape.getText();
        String correo=corre.getText();
        String organizacion=org.getText();
        String clave=Utilitaria.calcularHash(cla.getText());
        if(Utilitaria.correoDisponible(correo)){
            Usuario nuevo=new Usuario(nombre,apellido,correo,organizacion,clave);
            ArrayList<Usuario> usuarios=Usuario.readListFileSer("usuarios.ser");
            usuarios.add(nuevo);
            Usuario.saveListFileSer("usuarios.ser", usuarios);
            Alert alerta=new Alert(Alert.AlertType.INFORMATION,"Usuario se ha registrado correctamente");
            alerta.show();
            cambiarPantallaBusVehi(event);
        }
        else{
            Alert alerta=new Alert(Alert.AlertType.ERROR,"Usuario ya se encuentra registrado");
            alerta.show();
        }
    }

}
