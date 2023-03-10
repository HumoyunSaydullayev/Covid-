package UserControllers;

import com.example.covid.Database;
import com.example.covid.InformationOperator;
import com.example.covid.InformationUser;
import com.example.covid.Vaksina;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableViewSkin;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class ShifokorlarController implements Initializable {
    public VBox effect;
    public TableView<InformationOperator>  doctors;
    public TableColumn<InformationOperator, String> ism;
    public TableColumn<InformationOperator, String> familya;
    public TableColumn<InformationOperator, String> lavozim;
    public TableColumn<InformationOperator, String> malakasi;
    public TableColumn<InformationOperator, Date> date;
    public TextField keywordTextField;
    ObservableList shifokorlar= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Database db = new Database();
        try {
            db.getInformationOperator();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<InformationOperator> operators = db.getInformationOperators();
        for (InformationOperator item : operators) {
            shifokorlar.add(new InformationOperator(item.getId(),item.getLogin(), item.getParol(), item.getIsm(), item.getFamilya(),item.getTugulgan_yili(),item.getLavozimi(),item.getMalakasi())
            );
        }
        ism.setCellValueFactory(new PropertyValueFactory<InformationOperator,String>("ism"));
        familya.setCellValueFactory(new PropertyValueFactory<InformationOperator,String>("familya"));
        lavozim.setCellValueFactory(new PropertyValueFactory<InformationOperator,String>("lavozimi"));
        malakasi.setCellValueFactory(new PropertyValueFactory<InformationOperator,String>("malakasi"));
        date.setCellValueFactory(new PropertyValueFactory<InformationOperator,Date>("tugulgan_yili"));
        doctors.setItems(shifokorlar);

        FilteredList<InformationOperator> filteredData = new FilteredList<>(shifokorlar, b->true);
        keywordTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredData.setPredicate(operator -> {
                //bo'shlikka yoki null qiymat ga tekshirish
                if (newValue.isEmpty() || newValue.isBlank()){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if (operator.getIsm().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (operator.getFamilya().contains(searchKeyword)) {
                    return true;
                } else if (operator.getLavozimi().contains(searchKeyword)) {
                    return true;
                } else if (operator.getMalakasi().contains(searchKeyword)) {
                    return true;
                } else if (operator.getTugulgan_yili().toString().contains(searchKeyword)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<InformationOperator> sortedData = new SortedList<>(filteredData);

        //Jadval ko'rinishida tartiblangan natijani bog'lash
        sortedData.comparatorProperty().bind(doctors.comparatorProperty());

        //Filtrlangan va tartiblangan ma'lumotlarni Jadval ko'rinishiga qo'llang
        doctors.setItems(sortedData);

        FadeTransition f=new FadeTransition(Duration.seconds(1),effect);
        f.setFromValue(0);
        f.setToValue(1);
        f.play();
    }

}
