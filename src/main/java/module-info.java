module com.example {
    requires  javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;
    requires transitive java.sql;
    requires java.desktop;
    


    opens com.example to javafx.fxml;
    exports com.example.character;
    exports com.example.pellet;
    exports com.example;
}
