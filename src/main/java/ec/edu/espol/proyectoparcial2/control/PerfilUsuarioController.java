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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author luisa
 */
public class PerfilUsuarioController{

    @FXML
    private Button botonMenu;
    @FXML
    private TextField newpasstxt;
    @FXML
    private TextField oldpasstxt;
    @FXML
    private Button contranuevaBoton;
    @FXML
    private TextField nomtxt;
    @FXML
    private TextField orgtxt;
    @FXML
    private TextField mailtxt;

    private Usuario usuario;
    private ArrayList<Usuario> usuarios=Usuario.readListFileSer("usuarios.ser");
    /**
     * Initializes the controller class.
     */
    public void setUsuario(Usuario user) {
        usuario=user;
        nomtxt.setText(usuario.getNombres()+" "+usuario.getApellidos());
        mailtxt.setText(usuario.getCorreo());
        orgtxt.setText(usuario.getOrganizacion()); 
    }    

    @FXML
    private void cambiarMenu(ActionEvent event) throws IOException{
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
    private void cambiarPass(ActionEvent event) {
        if(!newpasstxt.getText().isEmpty()){
            String contra=Utilitaria.calcularHash(oldpasstxt.getText());
            if(usuario.getClave().equals(contra)){
                String nuevaContra=Utilitaria.calcularHash(newpasstxt.getText());
                usuario.setClave(nuevaContra);
                int indice=usuarios.indexOf(usuario);
                if (indice != -1)
                    usuarios.set(indice, usuario);
                Usuario.saveListFileSer("usuarios.ser", usuarios);
                Alert a=new Alert(Alert.AlertType.INFORMATION,"Su contraseña ha sido cambiada con éxito");
                a.show();
                newpasstxt.clear();
                oldpasstxt.clear();
            }
            else{
                Alert a=new Alert(Alert.AlertType.ERROR,"Ingrese su antigua contraseña para confirmar");
                a.show();
            }
        }
        else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Ingrese una nueva contraseña");
            a.show();
        }
        
    }
    
}
