package Controller;

import com.example.covid.Database;
import com.example.covid.Statistika;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StatisticController implements Initializable {
    public PieChart statisticchart;

    public VBox effect;

    public void Animation(){
        ScaleTransition s=new ScaleTransition(Duration.seconds(2),statisticchart);
        s.setByX(0.1f);
        s.setByY(0.1f);
        s.play();
        FadeTransition f=new FadeTransition(Duration.seconds(2),statisticchart);
        f.setFromValue(0);
        f.setToValue(1);
        f.play();

        ParallelTransition p=new ParallelTransition(s,f);
        p.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            about();
    }
    public void about(){
        Animation();
        Database db = new Database();
        try {
            db.getStatistic();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ObservableList<PieChart.Data> bigdata= FXCollections.observableArrayList();
        ArrayList<Statistika> informationstatistika = db.getStatistics();
        for (Statistika statistic : informationstatistika) {
            System.out.println(statistic.getEmlanganlar());
            bigdata= FXCollections.observableArrayList(
                    new PieChart.Data("Emlanganlar",statistic.getEmlanganlar()),
                    new PieChart.Data("Emlanayotgalar",statistic.getEmlanayotganlar()),
                    new PieChart.Data("Emlanmaganlar",statistic.getEmlanmaganlar())
            );
        }
        statisticchart.setTitle("Statistika");
        statisticchart.setData(bigdata);

        statisticchart.setLegendSide(Side.BOTTOM);

        for(PieChart.Data data: statisticchart.getData()){
            data.nameProperty().set(data.getName()+" : "+(int)data.getPieValue());
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Statistika");
                    alert.setHeaderText("Grafik bo'limi ma'lumotlari");
                    alert.setContentText(data.getName());
                    alert.showAndWait();
                }
            });
        }
    }
}
