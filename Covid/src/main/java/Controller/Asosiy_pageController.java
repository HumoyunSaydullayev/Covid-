package Controller;

import com.example.covid.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Asosiy_pageController {
    public Button BtnOperator;
    public Button BtnUser;

    public void ClickUser(ActionEvent ActionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("UserFXmls/Login_for_User.fxml")));
        Stage window = (Stage) BtnUser.getScene().getWindow();
        window.setTitle("Dasturga kirish oynasi");
        window.setResizable(false);
        window.setScene(new Scene(root, 1550, 800));
    }
    public void ClickOperator(ActionEvent ActionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("OperatorFXmls/Login_for_Operator.fxml")));
        Stage window = (Stage) BtnUser.getScene().getWindow();
        window.setTitle("Dasturga kirish oynasi");
        window.setResizable(false);
        window.setScene(new Scene(root, 1550, 800));
    }
}