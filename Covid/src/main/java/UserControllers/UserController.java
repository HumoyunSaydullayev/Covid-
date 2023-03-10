package UserControllers;

import Controller.StatisticController;
import com.example.covid.HelloApplication;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public Label undo;
    public Label menu;
    public StackPane ContentArea;
    public Button vaksinatsiya;
    public Button shifokorlar;
    public Button vaksinalar;
    public Button statistika;
    public VBox translate;
    private boolean bool=true;
    public int id;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        undo.setOnMouseClicked(e -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("Asosiy_page.fxml")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Stage window = (Stage) undo.getScene().getWindow();
            window.setTitle("User");
            window.setResizable(false);
            Scene secne = new Scene(Objects.requireNonNull(root), 1548, 800);
            secne.getStylesheets().add(String.valueOf(getClass().getResource("Style.css")));
            window.setScene(secne);
        });
        menu.setOnMouseClicked(e -> {

            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {

                            menu.setDisable(false);
                        }
                    }, 1000
            );

            FadeTransition fade = new FadeTransition(Duration.seconds(1), translate);
            if (bool) {
                fade.setFromValue(1);
                fade.setToValue(0.2);

                TranslateTransition widthtranslate = new TranslateTransition(Duration.seconds(1), translate);
                widthtranslate.setByX(-358);

                ParallelTransition p = new ParallelTransition(fade, widthtranslate);
                p.play();
                bool = false;
                menu.setDisable(true);

            } else {
                fade.setFromValue(0.2);
                fade.setToValue(1);

                TranslateTransition widthtranslate = new TranslateTransition(Duration.seconds(1), translate);
                widthtranslate.setByX(358);

                ParallelTransition p = new ParallelTransition(fade, widthtranslate);
                p.play();
                bool = true;
                menu.setDisable(true);
            }
        });

    }


    public void Vaksinatsiya(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        shifokorlar.setStyle("-fx-background-color: transparent; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        vaksinatsiya.setStyle("-fx-background-color: #00ff22; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        vaksinalar.setStyle("-fx-background-color: transparent; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        statistika.setStyle("-fx-background-color: transparent; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("UserFXmls/Vaksinatsiya.fxml"));
        Parent root = loader.load();
        VaksinatsiyaController ctrl = loader.getController();
        ctrl.setId(getId());
        ctrl.about();
        ContentArea.getChildren().removeAll();
        ContentArea.getChildren().setAll(root);
    }
    public void Shifokorlar(ActionEvent actionEvent) throws IOException {
        shifokorlar.setStyle("-fx-background-color: #00ff22; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        vaksinatsiya.setStyle("-fx-background-color: transparent; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        vaksinalar.setStyle("-fx-background-color: transparent; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        statistika.setStyle("-fx-background-color: transparent; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        Parent root=FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("UserFXmls/Shifokorlar.fxml")));
        ContentArea.getChildren().removeAll();
        ContentArea.getChildren().setAll(root);
    }
    public void Vaksinalar(ActionEvent actionEvent) throws IOException {
        shifokorlar.setStyle("-fx-background-color: transparent; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        vaksinatsiya.setStyle("-fx-background-color: transparent; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        vaksinalar.setStyle("-fx-background-color: #00ff22; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        statistika.setStyle("-fx-background-color: transparent; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        Parent root=FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("UserFXmls/Vaksinalar.fxml")));
        ContentArea.getChildren().removeAll();
        ContentArea.getChildren().setAll(root);
    }
    public void Statistika(ActionEvent actionEvent) throws IOException {
        shifokorlar.setStyle("-fx-background-color: transparent; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        vaksinatsiya.setStyle("-fx-background-color: transparent; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        vaksinalar.setStyle("-fx-background-color: transparent; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        statistika.setStyle("-fx-background-color: #00ff22; -fx-background-radius: 20; -fx-border-radius: 20; -fx-border-color: #00ff22; -fx-border-width: 2 0 2 0;");
        FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("Statistika.fxml"));
        Parent root = loader.load();
        StatisticController ctrl = loader.getController();
        ctrl.about();
        ContentArea.getChildren().removeAll();
        ContentArea.getChildren().setAll(root);
    }
}
