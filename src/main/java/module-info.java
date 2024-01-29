module com.example.snlgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;


    opens com.example.snlgame to javafx.fxml;
    exports com.example.snlgame;
}