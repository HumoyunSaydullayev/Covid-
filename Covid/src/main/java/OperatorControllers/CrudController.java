package OperatorControllers;

import com.example.covid.Database;
import com.example.covid.Vaksina;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CrudController implements Initializable {
    public VBox effect;
    public TableView<Vaksina> vaksina;
    public TableColumn<Vaksina,String> nomi;
    public TableColumn<Vaksina,Integer> mavjud_soni;
    public TableColumn<Vaksina,Integer> qabul_qilish_soni;
    public TableColumn<Vaksina,String> malumotlari;
    public TableColumn<Vaksina,Integer> oraliq_kuni;
    public Button Save;
    public Button Delete;
    public Button Update;
    public Button Insert;
    public TextField new_nomi;
    public TextField new_qabul_qilish_soni;
    public TextField new_mavjud_soni;
    public TextField new_malumotlari;
    public TextField new_oraliq_kuni;
    public Label error;
    @FXML
    private TextField keywordTextField;
    boolean add=false,edit=false;
    int id;

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
            informationvaccines.add(new Vaksina(item.getId(), item.getNomi(), item.getMavjud_soni(),item.getQabul_qilish_soni(),item.getMalumotlari(), item.getOraliq_kuni())
            );
        };
        nomi.setCellValueFactory(new PropertyValueFactory<Vaksina,String>("nomi"));
        mavjud_soni.setCellValueFactory(new PropertyValueFactory<Vaksina,Integer>("mavjud_soni"));
        qabul_qilish_soni.setCellValueFactory(new PropertyValueFactory<Vaksina,Integer>("qabul_qilish_soni"));
        malumotlari.setCellValueFactory(new PropertyValueFactory<Vaksina,String>("malumotlari"));
        oraliq_kuni.setCellValueFactory(new PropertyValueFactory<Vaksina,Integer>("oraliq_kuni"));
        vaksina.setItems(informationvaccines);


        FilteredList<Vaksina>  filteredData = new FilteredList<>(informationvaccines, b->true);
        keywordTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredData.setPredicate(Vaksina -> {
                //bo'shlikka yoki null qiymat ga tekshirish
                if (newValue.isEmpty() || newValue.isBlank()){
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

    public void RefreshTable(){
        informationvaccines.clear();
        Database db = new Database();
        try {
            db.getInformationvaksina();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Vaksina> Vaksinalar = db.getInformationvaccines();
        for (Vaksina item : Vaksinalar) {
            informationvaccines.add(new Vaksina(item.getId(), item.getNomi(), item.getMavjud_soni(),item.getQabul_qilish_soni(),item.getMalumotlari(), item.getOraliq_kuni())
            );
        };
        nomi.setCellValueFactory(new PropertyValueFactory<Vaksina,String>("nomi"));
        mavjud_soni.setCellValueFactory(new PropertyValueFactory<Vaksina,Integer>("mavjud_soni"));
        qabul_qilish_soni.setCellValueFactory(new PropertyValueFactory<Vaksina,Integer>("qabul_qilish_soni"));
        malumotlari.setCellValueFactory(new PropertyValueFactory<Vaksina,String>("malumotlari"));
        oraliq_kuni.setCellValueFactory(new PropertyValueFactory<Vaksina,Integer>("oraliq_kuni"));
        vaksina.setItems(informationvaccines);

        FilteredList<Vaksina>  filteredData = new FilteredList<>(informationvaccines, b->true);
        keywordTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredData.setPredicate(Vaksina -> {
                //bo'shlikka yoki null qiymat ga tekshirish
                if (newValue.isEmpty() || newValue.isBlank()){
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
    }
    public void Delete(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        Vaksina selected=vaksina.getSelectionModel().getSelectedItem();
        if(selected==null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ogohlantirish");
            alert.setHeaderText("Ma'lumot tanlanmadi");
            alert.setContentText("Iltimos Vaksinani tanlang");
            alert.showAndWait();
        }else{

            id=selected.getId();
            Database db=new Database();
            db.vaksinadelete(id);
            id=0;
            new_nomi.setText("");
            new_mavjud_soni.setText("");
            new_malumotlari.setText("");
            new_qabul_qilish_soni.setText("");
            new_oraliq_kuni.setText("");
            RefreshTable();
        }

    }

    public void Update(ActionEvent actionEvent) {
        Vaksina selected=vaksina.getSelectionModel().getSelectedItem();
         id=selected.getId();
        new_nomi.setText(selected.getNomi());
        new_mavjud_soni.setText(String.valueOf(selected.getMavjud_soni()));
        new_qabul_qilish_soni.setText(String.valueOf(selected.getQabul_qilish_soni()));
        new_malumotlari.setText(selected.getMalumotlari());
        new_oraliq_kuni.setText(String.valueOf(selected.getOraliq_kuni()));
            add=false;
            edit=true;
    }

    public void Insert(ActionEvent actionEvent) {
        if(new_nomi.getText().equals("") && new_malumotlari.getText().equals("") && new_oraliq_kuni.getText().equals("") && new_mavjud_soni.getText().equals("") && new_qabul_qilish_soni.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ogohlantirish");
            alert.setHeaderText("Ma'lumotlar kiritalmadi");
            alert.setContentText("Iltimos maydonlarni to'ldiring");
            alert.showAndWait();
        }else{
            add=true;
            edit=false;
        }
    }
    public void Save(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        if (add){
            Database db=new Database();
            if (db.vaksinaadd(new_nomi.getText(),Integer.parseInt(new_mavjud_soni.getText()),Integer.parseInt(new_qabul_qilish_soni.getText()),new_malumotlari.getText(),Integer.parseInt(new_oraliq_kuni.getText()))){
                error.setTextFill(Paint.valueOf("#00ff22"));
                error.setText("Vaksina ma'lumotlari qo'shildi !!!");
            }else{
                error.setTextFill(Paint.valueOf("red"));
                error.setText("Vaksina ma'lumotlari qo'shilmadi !!!");
            }
            new_nomi.setText("");
            new_mavjud_soni.setText("");
            new_malumotlari.setText("");
            new_qabul_qilish_soni.setText("");
            new_oraliq_kuni.setText("");
            RefreshTable();
        }else if(edit){
            if(new_nomi.getText().equals("") && new_malumotlari.getText().equals("") && new_oraliq_kuni.getText().equals("") && new_mavjud_soni.getText().equals("") && new_qabul_qilish_soni.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Ogohlantirish");
                alert.setHeaderText("Ma'lumotlar kiritalmadi");
                alert.setContentText("Iltimos maydonlarni to'ldiring");
                alert.showAndWait();
            }
            Database db=new Database();
            if(db.vaksinaUpdate(id,new_nomi.getText(),Integer.parseInt(new_mavjud_soni.getText()),Integer.parseInt(new_qabul_qilish_soni.getText()),new_malumotlari.getText(),Integer.parseInt(new_oraliq_kuni.getText()))){
                error.setTextFill(Paint.valueOf("#00ff22"));
                error.setText("Vaksina ma'lumotlari yangilandi !!!");
            }else{
                error.setTextFill(Paint.valueOf("red"));
                error.setText("Vaksina ma'lumotlari yangilanmadi !!!");
            }

            new_nomi.setText("");
            new_mavjud_soni.setText("");
            new_malumotlari.setText("");
            new_qabul_qilish_soni.setText("");
            new_oraliq_kuni.setText("");
            id=0;
            RefreshTable();
        }
    }

    public void Clear(ActionEvent actionEvent) {
        new_nomi.setText("");
        new_mavjud_soni.setText("");
        new_malumotlari.setText("");
        new_qabul_qilish_soni.setText("");
        new_oraliq_kuni.setText("");
    }
}

