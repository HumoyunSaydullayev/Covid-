package OperatorControllers;

import com.example.covid.Database;
import com.example.covid.HelloApplication;
import com.example.covid.InformationOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Login_OperatorController implements Initializable {
    public Label undo;
    public TextField login;
    public PasswordField password;
    public Label labell;
    public Label labelp;
    public Button submit;
    public Button clear;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        undo.setOnMouseClicked(e->{
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("Asosiy_page.fxml")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Stage window = (Stage) undo.getScene().getWindow();
            window.setTitle("Covid-19 vaccination system");
            window.setResizable(false);
            Scene secne=new Scene(Objects.requireNonNull(root), 1550, 800);
            secne.getStylesheets().add(String.valueOf(getClass().getResource("Style.css")));
            window.setScene(secne);
        });
    }


    public void clearDisplay(ActionEvent actionEvent) {
        login.setText("");
        password.setText("");
        labell.setText("");
        labelp.setText("");

    }

    public void result(ActionEvent actionEvent) throws Exception {
            boolean bool=true;
            if (login.getText().equals("") && password.getText().equals("")) {
                labell.setTextFill(Paint.valueOf("red"));
                labelp.setTextFill(Paint.valueOf("red"));
                labell.setText("*Login kiritilmadi !!!");
                labelp.setText("*Maxfiy so'z kiritilmadi !!!");
                bool=false;
            }else if (login.getText().equals("")){
                labelp.setText("");
                labell.setTextFill(Paint.valueOf("red"));
                labell.setText("*Login kiritilmadi !!!");
                bool=false;
            }else if(password.getText().equals("")){
                labell.setText("");
                labelp.setTextFill(Paint.valueOf("red"));
                labelp.setText("*Maxfiy so'z kiritilmadi !!!");
                bool=false;
            }
            if(bool){
                int sanash = 0;
                Database db = new Database();
                db.getInformationOperator();
                ArrayList<InformationOperator> InformationOperators = db.getInformationOperators();
                for (InformationOperator item : InformationOperators) {
                    if (item.getLogin().equals(login.getText()) && item.getParol().equals(password.getText())) {
                        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("OperatorFXmls/Operator.fxml"));
                        Parent root = loader.load();
                        Operator_Controller ctrl = loader.getController();
                        ctrl.setId(item.getId());
                        Stage newStage =(Stage) submit.getScene().getWindow();
                        newStage.setScene(new Scene(root, 1550, 800));
                        newStage.setTitle("Operator");
                        newStage.show();
                    }else if(item.getParol().equals(password.getText()) && !item.getLogin().equals(login.getText())){
                        labelp.setTextFill(Paint.valueOf("green"));
                        labell.setTextFill(Paint.valueOf("red"));
                        labelp.setText("*Tog'ri");
                        labell.setText("*Login ma'lumotlari nato'g'ri !!!");
                    }else if (item.getLogin().equals(login.getText())){
                        labell.setTextFill(Paint.valueOf("green"));
                        labelp.setTextFill(Paint.valueOf("red"));
                        labell.setText("*Tog'ri");
                        labelp.setText("*Maxfiy so'z ma'lumotlari nato'g'ri !!!");
                    } else {
                        sanash++;
                        if (sanash >= InformationOperators.size()) {
                            labell.setText("*Login ma'lumotlari nato'g'ri !!!");
                            labelp.setText("*Maxfiy so'z ma'lumotlari nato'g'ri !!!");
                        }
                    }
                }
            }
    }
}

