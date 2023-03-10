package UserControllers;

import com.example.covid.Database;
import com.example.covid.InformationUser;
import com.example.covid.Vaksina;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class VaksinatsiyaController{
    public VBox effect;
    public Label seria;
    public Label fish;
    public Label jinsi;
    public Label date;
    public Label manzil;
    public Label emlanganlik;
    public Label vaksina_nomi;

    public int id;
    public Label seria2;
    public Label seria1;
    public Label seria3;
    public Text txt1;
    public Text txt2;
    public Text txt3;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void about() throws IOException, ClassNotFoundException {
        txt1.setVisible(false);
        txt2.setVisible(false);
        txt3.setVisible(false);
        FadeTransition f=new FadeTransition(Duration.seconds(1),effect);
        f.setFromValue(0);
        f.setToValue(1);
        f.play();
        Database db = new Database();
        db.getInformationUser();
        ArrayList<InformationUser> informationUsers = db.getInformationUsers();
        for (InformationUser users : informationUsers) {
            if(this.getId()==users.getId()) {
                seria.setText(users.getSeria());
                fish.setText(users.getIsm()+" "+users.getFamilya()+" "+users.getSharifi());
                jinsi.setText(users.getJinsi());
                date.setText(String.valueOf(users.getTugulgan_yili()));
                manzil.setText(users.getYashash_manzili());
                db.getInformationvaksina();
                ArrayList<Vaksina> Vaksinalar = db.getInformationvaccines();
                for (Vaksina item : Vaksinalar) {
                    if (item.getId()==users.getVaksina_id()) {
                        vaksina_nomi.setText(item.getNomi());
                    }
                }
                if(users.getEmlanganligi()){
                    emlanganlik.setText("Siz to'liq emlanganiz ");
                    try {
                        String file_nomi = "D:\\Hujjatlar\\2-kurs 2-smester\\2-kurs 2-smester PBL\\PBL Final\\Covid\\Database\\Sertifikat\\" + users.getSeria() + ".pdf";
                        com.itextpdf.text.Document document = new Document();
                        com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(file_nomi));
                        document.open();
                        Paragraph para = new Paragraph("                                           O'zbekiston Respublikasi sog'liqni saqlash " + "\n                                           vazirligi koronavirus(COVID-19)ga qarshi " + "\n                                                   Emlanganlik to'g'risidagi sertifikat " +
                                "\n\n   Passport seriyasi: " + users.getSeria() + "\n   To'liq ismi: " + users.getIsm() + " " + users.getFamilya() + " " + users.getSharifi() + "\n   Tug'ulgan sanasi: " + users.getTugulgan_yili() + "\n   Jinsi: " + users.getJinsi() + "\n   Qabul qilgan vaksina nomi: " + vaksina_nomi.getText() +
                                "\n\n                                          To'liq emlanganligingiz uchun minnatdorchilik bildiramiz !!!");
                        document.add(para);
                        document.add(new Paragraph(" "));
                        document.close();
                    }
                    catch (Exception e){
                        System.out.println();
                    }
                }else{
                    if (users.getTakrorlanganligi()==3 || users.getTakrorlanganligi()==2 || users.getTakrorlanganligi()==1){
                        emlanganlik.setText("Siz emlanmoqdasiz ");
                    }else {
                        emlanganlik.setText("Siz emlanmagansiz");
                    }
                }
            }
        }
    }

}
