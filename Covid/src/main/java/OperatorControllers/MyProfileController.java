package OperatorControllers;

import com.example.covid.Database;
import com.example.covid.InformationOperator;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class MyProfileController {
    public VBox effect;
    public Label Login;
    public Label Parol;
    public Label birthday;
    public Label lavozim;
    public Label malaka;
    public Label admin_name;
    public int id;
    public TextField new_parol;
    public TextField confirm_new_parol;
    public Button change;
    public Label error;
    public Pane change_pane;
    public TextField old_parol;
    public Button change_password;
    public ImageView rasm;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void about() throws IOException, ClassNotFoundException {
        FadeTransition f = new FadeTransition(Duration.seconds(1), effect);
        f.setFromValue(0);
        f.setToValue(1);
        f.play();
        Database db = new Database();
        db.getInformationOperator();
        ArrayList<InformationOperator> informationoperator = db.getInformationOperators();
        for (InformationOperator admins : informationoperator) {
            if (this.getId() == admins.getId()) {
                Login.setText(admins.getLogin());
                Parol.setText(admins.getParol());
                admin_name.setText(admins.getIsm() + " " + admins.getFamilya());
                lavozim.setText(admins.getLavozimi());
                birthday.setText(String.valueOf(admins.getTugulgan_yili()));
                malaka.setText(admins.getMalakasi());
            }
        }
        change_pane.setVisible(false);
    }
    public void Refresh() throws IOException, ClassNotFoundException {
        Database db = new Database();
        db.getInformationOperator();
        ArrayList<InformationOperator> informationoperator = db.getInformationOperators();
        for (InformationOperator admins : informationoperator) {
            if (this.getId() == admins.getId()) {
                Login.setText(admins.getLogin());
                Parol.setText(admins.getParol());
                admin_name.setText(admins.getIsm() + " " + admins.getFamilya());
                lavozim.setText(admins.getLavozimi());
                birthday.setText(String.valueOf(admins.getTugulgan_yili()));
                malaka.setText(admins.getMalakasi());
            }
        }
        change_pane.setVisible(false);
    }
    public void change(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Database db=new Database();
        db.getInformationOperator();
        ArrayList<InformationOperator> informationoperator = db.getInformationOperators();
        for (InformationOperator admins : informationoperator) {
            if (old_parol.getText().equals(admins.getParol())) {
                if (new_parol.getText().equals("") && confirm_new_parol.getText().equals("")) {
                    error.setTextFill(Paint.valueOf("red"));
                    error.setText("*Ma'lumotlar kiritilmadi !!!");
                } else if (new_parol.getText().length() < 8 && confirm_new_parol.getText().length() < 8) {
                    error.setTextFill(Paint.valueOf("red"));
                    error.setText("*Ma'lumotlar 8ta simvoldan kam !!!");
                } else if (!new_parol.getText().equals(confirm_new_parol.getText())) {
                    error.setTextFill(Paint.valueOf("red"));
                    error.setText("*Ma'lumotlar bir-biriga mos emas !!!");
                } else {
                    boolean bool = db.changePassword(getId(), new_parol.getText());
                    if (bool) {
                        error.setTextFill(Paint.valueOf("green"));
                        error.setText("Parol o'zgartirildi !!!");
                        new_parol.setText("");
                        confirm_new_parol.setText("");
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        FadeTransition fade = new FadeTransition(Duration.seconds(1), change_pane);
                                        fade.setFromValue(1);
                                        fade.setToValue(0);

                                        TranslateTransition widthtranslate = new TranslateTransition(Duration.seconds(1), change_pane);
                                        widthtranslate.setByX(-750);

                                        ParallelTransition p = new ParallelTransition(fade, widthtranslate);
                                        p.play();
                                        new java.util.Timer().schedule(
                                                new java.util.TimerTask() {
                                                    @Override
                                                    public void run() {
                                                        change_pane.setVisible(false);
                                                    }
                                                }, 1100
                                        );
                                    }
                                }, 1000
                        );
                        Refresh();
                    } else {
                        error.setTextFill(Paint.valueOf("red"));
                        error.setText("Qayta urunib ko'ring");
                    }
                }
            }else{
                error.setTextFill(Paint.valueOf("red"));
                error.setText("Joriy parol ma'lumotlari nato'g'ri !!!");
            }
        }
    }

    public void change_password(ActionEvent actionEvent) {
        change_pane.setVisible(true);
        FadeTransition fade = new FadeTransition(Duration.seconds(1), change_pane);
        fade.setFromValue(0);
        fade.setToValue(1);

        TranslateTransition widthtranslate = new TranslateTransition(Duration.seconds(1), change_pane);
        widthtranslate.setByX(760);

        ParallelTransition p = new ParallelTransition(fade, widthtranslate);
        p.play();
    }
}


