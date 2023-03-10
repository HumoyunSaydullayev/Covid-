package UserControllers;

import com.example.covid.Database;
import com.example.covid.Vaksina;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VaksinaController implements Initializable{
    public VBox effect;
    public TableView<Vaksina> vaksina;
    public TableColumn<Vaksina,String> nomi;
    public TableColumn<Vaksina,Integer> mavjud_soni;
    public TableColumn<Vaksina,Integer> qabul_qilish_soni;
    public TableColumn<Vaksina,String> malumotlari;
    public TableColumn<Vaksina,Integer> oraliq_kuni;
    public TextField keywordTextField;
    ObservableList informationvaccines = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Database db = new Database();
        try {
            db.getInformationvaksina();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Vaksina> Vaksinalar = db.getInformationvaccines();
        for (Vaksina item : Vaksinalar) {
            informationvaccines.add(new Vaksina(item.getId(), item.getNomi(), item.getMavjud_soni(),item.getQabul_qilish_soni(),item.getMalumotlari(), item.getQabul_qilish_soni())
            );
        };
        nomi.setCellValueFactory(new PropertyValueFactory<Vaksina,String>("nomi"));
        mavjud_soni.setCellValueFactory(new PropertyValueFactory<Vaksina,Integer>("mavjud_soni"));
        qabul_qilish_soni.setCellValueFactory(new PropertyValueFactory<Vaksina,Integer>("qabul_qilish_soni"));
        malumotlari.setCellValueFactory(new PropertyValueFactory<Vaksina,String>("malumotlari"));
        oraliq_kuni.setCellValueFactory(new PropertyValueFactory<Vaksina,Integer>("oraliq_kuni"));
        vaksina.setItems(informationvaccines);

        FilteredList<Vaksina> filteredData = new FilteredList<>(informationvaccines, b->true);
        keywordTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredData.setPredicate(Vaksina -> {
                //bo'shlikka yoki null qiymat ga tekshirish
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if (Vaksina.getNomi().toLowerCase().contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(Vaksina.getMavjud_soni()).contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(Vaksina.getQabul_qilish_soni()).contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(Vaksina.getMalumotlari()).contains(searchKeyword)) {
                    return true;
                } else if (String.valueOf(Vaksina.getOraliq_kuni()).contains(searchKeyword)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Vaksina> sortedData = new SortedList<>(filteredData);

        //Jadval ko'rinishida tartiblangan natijani bog'lash
        sortedData.comparatorProperty().bind(vaksina.comparatorProperty());

        //Filtrlangan va tartiblangan ma'lumotlarni Jadval ko'rinishiga qo'llang
        vaksina.setItems(sortedData);
        
        FadeTransition f=new FadeTransition(Duration.seconds(1),effect);
        f.setFromValue(0);
        f.setToValue(1);
        f.play();
    }
}
