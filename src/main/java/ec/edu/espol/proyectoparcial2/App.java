package ec.edu.espol.proyectoparcial2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("PantallaLogin"));
        stage.setScene(scene);
        stage.setTitle("Compra-Venta de Vehiculos");
        Image icono=new Image("/imgs/carroIcono.png");
        stage.getIcons().add(icono);
        
        stage.setOnCloseRequest((evento) -> {
                                evento.consume();
                                cerrarPrograma(stage); });
        stage.show();
    }

    private static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public void cerrarPrograma(Stage escenario){
        Alert alertaCerrar=new Alert(Alert.AlertType.CONFIRMATION,"¿Desea salir del programa?");
        if(alertaCerrar.showAndWait().get()==ButtonType.OK)
            escenario.close();
    }
}