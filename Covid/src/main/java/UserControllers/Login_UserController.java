package UserControllers;

import com.example.covid.Database;
import com.example.covid.HelloApplication;
import com.example.covid.InformationUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

public class Login_UserController implements Initializable {
    public Label undo;
    public Button clear;
    public Button submit;
    public Label labelp;
    public Label labelc;
    public Label code;
    public TextField passport;
    public TextField check;

    public void Genaration(){
        StringBuilder soz= new StringBuilder();
        String[] harflar={"A","B","C","D","E","F","G","I","H","J","K","L","Z","X","C","V","N","M","Q","W","R","T","Y","U","O","P","a","b","c","d","e","f","g","h","i","j","k","l","z","x","c","v","n","m","q","w","r","t","y","u","o","p"};
        for (int i = 0; i < 4; i++) {
            Random rand = new Random();
            int son =rand.nextInt(harflar.length);
            soz.append(harflar[son]);
        }
        code.setText(soz.toString());
    }

//    Boshlang'ich metod
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        passport.setText("AC0537540");
        Genaration();
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
        code.setOnMouseClicked(e->{
            code.setText("");
            Genaration();
        });

    }



//    Ekran tozalash
    public void clearDisplay(ActionEvent actionEvent) {
        passport.setText("");
        labelp.setText("");
        check.setText("");
        labelc.setText("");
    }




//    Jo'natish
    public void Submit(ActionEvent ActionEvent) throws ClassNotFoundException, IOException {
        boolean bool=true;
        if (check.getText().equals("") && passport.getText().equals("")) {
            labelp.setTextFill(Paint.valueOf("red"));
            labelc.setTextFill(Paint.valueOf("red"));
            labelp.setText("Passport seriya kiritilmadi !!!");
            labelc.setText("Tasdiqlash kodi kiritilmadi !!!");
            bool=false;
        }else if(check.getText().equals("")){
            labelp.setText("");
            labelc.setTextFill(Paint.valueOf("red"));
            labelc.setText("Tasdiqlash kodi kiritilmadi !!!");
            bool=false;
        }else if (passport.getText().equals("")){
            labelc.setText("");
            labelp.setTextFill(Paint.valueOf("red"));
            labelp.setText("Passport seriya kiritilmadi !!!");
            bool=false;
        }
        if(bool){
            int sanash = 0;
            Database db = new Database();
            db.getInformationUser();
            ArrayList<InformationUser> informationUsers = db.getInformationUsers();
            System.out.println(informationUsers);
            for (InformationUser users : informationUsers) {
                if (users.getSeria().equals(passport.getText()) && check.getText().equals(code.getText())) {
                    System.out.println("Kirdi ");
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("UserFXmls/User.fxml"));
                    Parent root = loader.load();
                    UserController ctrl = loader.getController();
                    ctrl.setId(users.getId());
                    Stage newStage =(Stage) submit.getScene().getWindow();
                    newStage.setTitle("User");
                    newStage.setScene(new Scene(root, 1550, 800));
                    newStage.show();
                }else if (users.getSeria().equals(passport.getText()) && !check.getText().equals(code.getText())){
                    System.out.println("Hammasi natog'ri ");
                    labelp.setTextFill(Paint.valueOf("#00ff22"));
                    labelp.setText("Passport seriya raqami to'g'ri !!!");
                    labelc.setTextFill(Paint.valueOf("red"));
                    labelc.setText("*Tasdiqlash kodi nato'g'ri !!!");
                    Genaration();
                }else if(check.getText().equals(code.getText()) && !users.getSeria().equals(passport.getText())){
                    System.out.println("Pasport yo'q ");
                    labelc.setTextFill(Paint.valueOf("#00ff22"));
                    labelc.setText("Tasdiqlash kodi to'g'ri !!!");
                    labelp.setTextFill(Paint.valueOf("red"));
                    labelp.setText("*Passport ma'lumotlari nato'g'ri !!!");
                }
                else {
                    System.out.println("Ma'lumot yo'q");
                    sanash++;
                    if (sanash >= informationUsers.size()) {
                        labelp.setTextFill(Paint.valueOf("red"));
                        labelc.setTextFill(Paint.valueOf("red"));
                        labelp.setText("*Passport ma'lumotlari nato'g'ri !!!");
                        labelc.setText("*Tasdiqlash kodi nato'g'ri !!!");
                        Genaration();
                    }
                }
            }
        }
    }

}
