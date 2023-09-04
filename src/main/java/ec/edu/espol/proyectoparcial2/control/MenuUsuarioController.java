package ec.edu.espol.proyectoparcial2.control;

import ec.edu.espol.proyectoparcial2.modelo.Usuario;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuUsuarioController {

    @FXML
    private Label saludoLabel;
    @FXML
    private Button botonLogout;
    @FXML
    private Button regisVehi;
    @FXML
    private Button busVehi;

    private Usuario usuario;
    @FXML
    private Button consultarOfer;
    @FXML
    private Button botonPerfil;
    
    public void setUsuario(Usuario usuario){
        this.usuario=usuario;
    }
    
    private void cambiarPantallaLogin(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectoparcial2/PantallaLogin.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }
    
    public void mostrarUsuario(String nombre){
        saludoLabel.setText("Hola, "+nombre);
    }
    
    @FXML
    private void cerrarSesion(ActionEvent event) throws IOException {
        Alert alertaLogout=new Alert(Alert.AlertType.CONFIRMATION,"¿Quiere cerrar la sesión?");
        if(alertaLogout.showAndWait().get()==ButtonType.OK){
            cambiarPantallaLogin(event);
        }
    }

    @FXML
    private void cambiarBuscarVehi(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectoparcial2/BuscarVehiculo.fxml"));
        Parent root = (Parent) loader.load();
        BuscarVehiculoController vehiculoControlador=loader.getController();
        vehiculoControlador.setUsuario(usuario);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }
    
    @FXML
    private void cambiarRegistroVehi(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectoparcial2/RegistrarVehiculo.fxml"));
        Parent root = (Parent) loader.load();
        RegistrarVehiculoController vehiculoControlador=loader.getController();
        vehiculoControlador.setUsuario(usuario);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }

    @FXML
    private void cambiarConsultaOfer(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectoparcial2/OfertasVehiculos.fxml"));
        Parent root = (Parent) loader.load();
        OfertasVehiculosController ofertaControlador=loader.getController();
        ofertaControlador.setUsuario(usuario);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }

    @FXML
    private void verPerfil(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/ec/edu/espol/proyectoparcial2/PerfilUsuario.fxml"));
        Parent root = (Parent) loader.load();
        PerfilUsuarioController userControlador=loader.getController();
        userControlador.setUsuario(usuario);
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene escena=new Scene(root);
        stage.setScene(escena);
        stage.show();
    }

}