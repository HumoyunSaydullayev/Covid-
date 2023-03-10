module com.example.covid {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;
    requires org.testng;
    requires itextpdf;


    opens com.example.covid to javafx.fxml;
    exports com.example.covid;
    exports Controller;
    opens Controller to javafx.fxml;
    exports UserControllers;
    opens UserControllers to javafx.fxml;
    exports OperatorControllers;
    opens OperatorControllers to javafx.fxml;
}