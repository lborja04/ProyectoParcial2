module ec.edu.espol.proyectoparcial2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.mail;

    opens ec.edu.espol.proyectoparcial2 to javafx.fxml;
    opens ec.edu.espol.proyectoparcial2.control to javafx.fxml;
    exports ec.edu.espol.proyectoparcial2;
    exports ec.edu.espol.proyectoparcial2.control;
}
