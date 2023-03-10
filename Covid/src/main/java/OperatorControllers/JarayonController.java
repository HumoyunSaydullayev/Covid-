package OperatorControllers;

import com.example.covid.Database;
import com.example.covid.InformationUser;
import com.example.covid.Vaksina;
import com.example.covid.Vaksina_jarayoni;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class JarayonController implements Initializable {
    public int id;
    public Button new_process;
    public Button old_process;
    public VBox effect;
    public TextField passport;
    public ComboBox<String> vaksina;
    public TextField now_date;
    public TextField next_date;
    public Label error;
    public Button Save;
    public Pane new_pane;
    public int vaksina_id;
    public int old_vaksina_id;
    public int yes_user_id;
    public int mavjud_soni;
    public Button clear;
    public TextField old_passport;
    public TextField old_vaksina;
    public TextField old_now_date;
    public TextField old_next_date;
    public Label yes_error;
    public Button yes_save;
    public Pane yes_new_pane;
    public Button yes_clear;
    public Button search;
    public Text txt1;
    public Text txt2;
    public Text txt3;
    Database db = new Database();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition f=new FadeTransition(Duration.seconds(1),effect);
        f.setFromValue(0);
        f.setToValue(1);
        f.play();
        new_pane.setVisible(false);
        yes_new_pane.setVisible(false);
        Database db = new Database();
        try {
            db.getInformationvaksina();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Vaksina> Vaksinalar = db.getInformationvaccines();
        ObservableList names =FXCollections.observableArrayList();
        for (Vaksina item : Vaksinalar) {
            if (item.getMavjud_soni() > 0) {
                names.add(item.getNomi());
            }
        }
        vaksina.setItems(FXCollections.observableArrayList(names));
    }

    public void SelectVaksina(ActionEvent actionEvent) throws ClassNotFoundException {
        db.getInformationvaksina();
        ArrayList<Vaksina> Vaksinalar = db.getInformationvaccines();
        for (Vaksina item : Vaksinalar) {
                if (item.getNomi().equalsIgnoreCase(vaksina.getValue())){
                    LocalDate now = LocalDate.now();
                    LocalDate date=now.plusDays(item.getOraliq_kuni());
                    next_date.setText(String.valueOf(date));
                    vaksina_id=item.getId();
                    mavjud_soni=item.getMavjud_soni();
                }
        }
    }

    public void new_process(ActionEvent actionEvent) throws IOException {
        new_pane.setVisible(true);
        new_process.setVisible(false);
        FadeTransition fade = new FadeTransition(Duration.seconds(1), new_pane);
        fade.setFromValue(0);
        fade.setToValue(1);

        TranslateTransition widthtranslate = new TranslateTransition(Duration.seconds(1), new_pane);
        widthtranslate.setByX(550);

        ParallelTransition p = new ParallelTransition(fade, widthtranslate);
        p.play();
        now_date.setText(String.valueOf(LocalDate.now()));
    }

    public void Save(ActionEvent actionEvent) throws ClassNotFoundException, IOException {
        boolean mavjudligi=true;
        db.getInformationUser();
        db.getInformation();
        ArrayList<Vaksina_jarayoni> information = db.getInformations();
        ArrayList<InformationUser> informationUsers = db.getInformationUsers();
        for (Vaksina_jarayoni item : information) {
            if (passport.getText().equalsIgnoreCase(item.getFuqaro_seria())){
                mavjudligi=false;
            }else{

            }
        }
        for (InformationUser users : informationUsers) {
            if (passport.getText().equalsIgnoreCase(users.getSeria())){
                 if(users.getEmlanganligi()) {
                     error.setTextFill(Paint.valueOf("red"));
                     error.setText("Bu fuqaro to'liq emlangan !!!");
                 }
                 else{
                     if(mavjudligi){
                         boolean bool=db.useradd(users.getId(),vaksina_id,getId(),now_date.getText(),next_date.getText());
                         if (bool){
                             mavjud_soni=mavjud_soni-1;
                             db.ChangeVaksina(vaksina_id,mavjud_soni);
                             error.setTextFill(Paint.valueOf("#00ff22"));
                             error.setText("Fuqaro ma'lumotlari qo'shildi !!!");
                             new java.util.Timer().schedule(
                                     new java.util.TimerTask() {
                                         @Override
                                         public void run() {
                                             FadeTransition fade = new FadeTransition(Duration.seconds(1), new_pane);
                                             fade.setFromValue(1);
                                             fade.setToValue(0);

                                             TranslateTransition widthtranslate = new TranslateTransition(Duration.seconds(1), new_pane);
                                             widthtranslate.setByX(-550);

                                             ParallelTransition p = new ParallelTransition(fade, widthtranslate);
                                             p.play();
                                             new java.util.Timer().schedule(
                                                     new java.util.TimerTask() {
                                                         @Override
                                                         public void run() {
                                                             new_pane.setVisible(false);
                                                             new_process.setVisible(true);
                                                         }
                                                     }, 1100
                                             );
                                         }
                                     }, 1000
                             );
                         }
                     }else{
                         next_date.setText("");
                         vaksina.setValue("");
                         error.setTextFill(Paint.valueOf("red"));
                         error.setText("Fuqaro 1-dozani qabul qilib bo'lgan !!!");
                     }

                 }
            }
        }
    }

    public void Clear(ActionEvent actionEvent) {
        passport.setText("");
        next_date.setText("");
        error.setText("");
        vaksina.setValue("");
    }



///Mavjud jarayonni qo'shish
    public void Visible(boolean bool){
        yes_save.setVisible(bool);
        old_vaksina.setVisible(bool);
        old_now_date.setVisible(bool);
        old_next_date.setVisible(bool);
        txt1.setVisible(bool);
        txt2.setVisible(bool);
        txt3.setVisible(bool);
    }
    public void old_process(ActionEvent actionEvent) throws ClassNotFoundException {
        yes_new_pane.setVisible(true);
        old_process.setVisible(false);
        FadeTransition fade = new FadeTransition(Duration.seconds(1), yes_new_pane);
        fade.setFromValue(0);
        fade.setToValue(1);

        TranslateTransition widthtranslate = new TranslateTransition(Duration.seconds(1), yes_new_pane);
        widthtranslate.setByX(-550);

        ParallelTransition p = new ParallelTransition(fade, widthtranslate);
        p.play();
        Visible(false);
    }

    public void search(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        boolean Emlanganligi=true;
        int user_id = 0;
        db.getInformationUser();
        ArrayList<InformationUser> informationUsers = db.getInformationUsers();
        for (InformationUser users : informationUsers) {
            if (old_passport.getText().equalsIgnoreCase(users.getSeria())) {
                user_id=users.getId();
                if (users.getEmlanganligi()) {
                    Emlanganligi=false;
                }
            }
        }
        if(!old_passport.getText().equalsIgnoreCase("")){
            if (Emlanganligi){
                int count=0;
                db.getInformation();
                db.getInformationvaksina();
                ArrayList<Vaksina_jarayoni> information = db.getInformations();
                ArrayList<Vaksina> Vaksinalar = db.getInformationvaccines();
                for (Vaksina_jarayoni item : information) {
                    if (old_passport.getText().equalsIgnoreCase(item.getFuqaro_seria())) {
                        if(db.checkSana(user_id)){
                            for (Vaksina vaksina : Vaksinalar){
                                if (vaksina.getNomi().equalsIgnoreCase(item.getVaksina_nomi()) && vaksina.getMavjud_soni()>0){
                                    LocalDate now = LocalDate.now();
                                    LocalDate date = now.plusDays(vaksina.getOraliq_kuni());
                                    yes_user_id=user_id;
                                    old_now_date.setText(String.valueOf(now));
                                    old_vaksina.setText(item.getVaksina_nomi());
                                    old_vaksina_id=vaksina.getId();
                                    old_next_date.setText(String.valueOf(date));
                                    Visible(true);
                                }
                            }
                        } else{
                            yes_error.setTextFill(Paint.valueOf("red"));
                            yes_error.setText((db.errorson+1)+"-doza olish vaqti "+db.errorDate+" !!!");
                        }
                    }else{
                        count++;
                        if(count>=information.size()){
                            yes_error.setTextFill(Paint.valueOf("red"));
                            yes_error.setText("Fuqaro hali 1-dozani qabul olmagan !!!");
                            Visible(false);
                        }
                    }
                }
            }else{
                yes_error.setTextFill(Paint.valueOf("red"));
                yes_error.setText("Fuqaro to'liq emlangan !!!");
                Visible(false);
            }
        }else{
            yes_error.setTextFill(Paint.valueOf("red"));
            yes_error.setText("Ma'lumot kiritilmadi !!!");
            Visible(false);
        }

    }

    public void yes_clear(ActionEvent actionEvent) {
        old_passport.setText("");
        yes_error.setText("");
        Visible(false);
    }

    public void yes_save(ActionEvent actionEvent) throws ClassNotFoundException, IOException {
            boolean bool=db.yesuseradd(yes_user_id,old_vaksina_id,getId(),old_now_date.getText(),old_next_date.getText());
            if (bool){
                mavjud_soni=mavjud_soni-1;
                db.ChangeVaksina(vaksina_id,mavjud_soni);
                yes_error.setTextFill(Paint.valueOf("#00ff22"));
                yes_error.setText("Fuqaro ma'lumotlari qo'shildi !!!");
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                FadeTransition fade = new FadeTransition(Duration.seconds(1), yes_new_pane);
                                fade.setFromValue(1);
                                fade.setToValue(0);

                                TranslateTransition widthtranslate = new TranslateTransition(Duration.seconds(1), yes_new_pane);
                                widthtranslate.setByX(550);

                                ParallelTransition p = new ParallelTransition(fade, widthtranslate);
                                p.play();
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                            @Override
                                            public void run() {
                                                yes_new_pane.setVisible(false);
                                                old_process.setVisible(true);
                                            }
                                        }, 1100
                                );
                            }
                        }, 1000
                );
            }
    }
}
