package com.example.covid;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class Database {
    Connection conn=null;
    PreparedStatement pst;
    ResultSet rs;
    public int errorson=0;
    public Date errorDate=null;
    ArrayList<InformationUser> informationUsers = new ArrayList<>();

    public void getInformationUser() throws ClassNotFoundException {
        try {
            System.out.println();
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("asdfghj");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");
            System.out.println(conn);
            pst = conn.prepareStatement("select * from fuqaro_malumotlari");
            System.out.println(pst);
            rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String seria = rs.getString("passport_seriya");
                String ism = rs.getString("ism");
                String familya = rs.getString("familya");
                String sharifi = rs.getString("sharifi");
                String jinsi = rs.getString("jinsi");
                Date tugulgan_yili = rs.getDate("tugulgan_yili");
                String yashash_manzili = rs.getString("yashash_manzili");
                boolean emlanganligi = rs.getBoolean("emlanganligi");
                PreparedStatement pst1 = conn.prepareStatement("select Count(fuqaro_id) as son,vaksina_id from vaksina_jarayoni where fuqaro_id=?;");
                pst1.setInt(1, id);
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    int raqam = rs1.getInt("son");
                    int vaksina_nomi=rs1.getInt("vaksina_id");
                    informationUsers.add(new InformationUser(id, seria, ism, familya, sharifi, jinsi, tugulgan_yili, yashash_manzili, emlanganligi,raqam,vaksina_nomi));
                    System.out.println(seria);
                }
            }
            pst.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println();
        }
    }



    public ArrayList<InformationUser> getInformationUsers() {
        return informationUsers;
    }

    public void getMalumot(int passport) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");
            Date emlangan_sana1 = null;
            Date emlangan_sana2 = null;
            Date emlangan_sana3=null;
            PreparedStatement pst1 = conn.prepareStatement("SELECT hozirgi_sana_vaqt FROM vaksina_jarayoni WHERE fuqaro_id=? AND  (SELECT Count(fuqaro_id) FROM vaksina_jarayoni WHERE fuqaro_id=?)=?");

            pst1.setInt(1, passport);
            pst1.setInt(2, passport);
            pst1.setInt(3, 1);
            ResultSet rs1 = pst1.executeQuery();
            while (rs1.next()) {
                emlangan_sana1 = rs1.getDate("hozirgi_sana_vaqt");
            }

            PreparedStatement pst2 = conn.prepareStatement("SELECT hozirgi_sana_vaqt FROM vaksina_jarayoni WHERE fuqaro_id=? AND  (SELECT Count(fuqaro_id) FROM vaksina_jarayoni WHERE fuqaro_id=?)=?");
            pst2.setInt(1, passport);
            pst2.setInt(2, passport);
            pst2.setInt(3, 2);
            ResultSet rs2 = pst2.executeQuery();
            while (rs2.next()) {
                emlangan_sana2 = rs2.getDate("hozirgi_sana_vaqt");
            }

            PreparedStatement pst3 = conn.prepareStatement("SELECT hozirgi_sana_vaqt FROM vaksina_jarayoni WHERE fuqaro_id=? AND  (SELECT Count(fuqaro_id) FROM vaksina_jarayoni WHERE fuqaro_id=?)=?");
            pst3.setInt(1, passport);
            pst3.setInt(2, passport);
            pst3.setInt(3, 1);
            ResultSet rs3 = pst3.executeQuery();
            while (rs2.next()) {
                emlangan_sana3 = rs3.getDate("hozirgi_sana_vaqt");
            }

            pst.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println();
        }
    }

    ArrayList<InformationOperator> informationOperators = new ArrayList<>();

    public void getInformationOperator() throws  ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");
            pst = conn.prepareStatement("select * from Xodimlar");
            rs = pst.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");
                String login = rs.getString("login");
                String parol = rs.getString("parol");
                String ism = rs.getString("ism");
                String familya = rs.getString("familya");
                Date tugulgan_yili = rs.getDate("tugulgan_yili");
                String lavozimi = rs.getString("lavozimi");
                String malakasi = rs.getString("malakasi");
                informationOperators.add(new InformationOperator(id,login, parol, ism, familya, tugulgan_yili, lavozimi, malakasi));
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println();
        }
    }

    public ArrayList<InformationOperator> getInformationOperators() {
        return informationOperators;
    }

    ArrayList<Vaksina> informationvaccines = new ArrayList<>();

    public boolean changePassword(int id,String new_password) throws IOException, ClassNotFoundException {
        boolean bool=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");

            pst = conn.prepareStatement("update Xodimlar set parol=? where id=?");
            pst.setString(1, new_password);
            pst.setInt(2, id);
            if (pst.executeUpdate() == 1) {
                bool = true;
            }
            conn.close();
        } catch (SQLException e) {
            bool = false;
        }
        return bool;
    }

    public boolean vaksinaadd(String nomi,int mavjud_soni,int qabul_qilish_soni, String malumotlari,int oraliq_kuni) throws IOException, ClassNotFoundException {
        boolean bool=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");
            pst = conn.prepareStatement("insert into vaksina (nomi,mavjud_soni,qabul_qilish_soni,malumotlari,oraliq_kuni) values(?,?,?,?,?)");
            pst.setString(1, nomi);
            pst.setInt(2, mavjud_soni);
            pst.setInt(3, qabul_qilish_soni);
            pst.setString(4, malumotlari);
            pst.setInt(5, oraliq_kuni);
            if(pst.executeUpdate()==1){
                bool=true;
            }
            pst.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println();
        }
        return bool;
    }

    public boolean vaksinaUpdate(int id,String nomi,int mavjud_soni,int qabul_qilish_soni, String malumotlari,int oraliq_kuni) throws IOException, ClassNotFoundException {
        boolean bool=false;
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");

                pst = conn.prepareStatement("update vaksina  set nomi=?,mavjud_soni=?,qabul_qilish_soni=?,malumotlari=?,oraliq_kuni=? where id=?");

                pst.setString(1, nomi);
                pst.setInt(2, mavjud_soni);
                pst.setInt(3, qabul_qilish_soni);
                pst.setString(4, malumotlari);
                pst.setInt(5, oraliq_kuni);
                pst.setInt(6, id);

                if(pst.executeUpdate()==1){
                    bool=true;
                }
                conn.close();

            } catch (SQLException e) {
                System.out.println();
            }
            return bool;
        }

    public void vaksinadelete(int id) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");

            pst = conn.prepareStatement("delete from vaksina where id=?");
            pst.setInt(1, id);
            pst.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            System.out.println();
        }
    }

    public void getInformationvaksina() throws  ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");
            pst = conn.prepareStatement("select * from vaksina");
            rs = pst.executeQuery();
            while (rs.next()) {
                int id=rs.getInt("id");
                String nomi = rs.getString("nomi");
                int mavjud_soni = rs.getInt("mavjud_soni");
                int qabul_qilish_soni = rs.getInt("qabul_qilish_soni");
                String malumotlari = rs.getString("malumotlari");
                int oraliq_kuni = rs.getInt("oraliq_kuni");
                informationvaccines.add(new Vaksina(id,nomi, mavjud_soni, qabul_qilish_soni, malumotlari, oraliq_kuni));
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println();
        }
    }

    public ArrayList<Vaksina> getInformationvaccines() {
        return informationvaccines;
    }

    public void ChangeVaksina(int id,int mavjud_soni) throws IOException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");

            pst = conn.prepareStatement("update vaksina  set mavjud_soni=? where id=?");

            pst.setInt(1, mavjud_soni);
            pst.setInt(2, id);

            pst.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            System.out.println();
        }
    }
    ArrayList<Vaksina_jarayoni> Information= new ArrayList<>();

    public boolean useradd(int passport, int vaksina, int xodim, String now_date, String next_date) throws IOException, ClassNotFoundException {
        boolean bool=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");
            pst = conn.prepareStatement("insert into vaksina_jarayoni(fuqaro_id,vaksina_id,xodim_id,hozirgi_sana_vaqt,belgilangan_sana_vaqt) values(?,?,?,?,?)");
            pst.setInt(1, passport);
            pst.setInt(2, vaksina);
            pst.setInt(3, xodim);
            pst.setDate(4, java.sql.Date.valueOf(now_date));
            pst.setDate(5, java.sql.Date.valueOf(next_date));
            if(pst.executeUpdate()==1){
                bool=true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return bool;
    }
    public boolean checkSana(int passport) throws ClassNotFoundException, SQLException {
        boolean bool = false;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");
        pst = conn.prepareStatement("SELECT COUNT(fuqaro_id) AS Soni FROM vaksina_jarayoni WHERE fuqaro_id=?");
        pst.setInt(1, passport);
        rs = pst.executeQuery();

        while (rs.next()) {
            errorson = rs.getInt("Soni");
        }
        PreparedStatement pst1 = conn.prepareStatement("SELECT belgilangan_sana_vaqt FROM vaksina_jarayoni WHERE fuqaro_id=? AND  (SELECT Count(fuqaro_id) FROM vaksina_jarayoni WHERE fuqaro_id=?)=?");

        pst1.setInt(1, passport);
        pst1.setInt(2, passport);
        pst1.setInt(3, errorson);
        ResultSet rs1 = pst1.executeQuery();
        while (rs1.next()) {
            errorDate = rs1.getDate("belgilangan_sana_vaqt");
        }

        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date convert = Date.from(LocalDate.now().atStartOfDay(defaultZoneId).toInstant());
        if (convert.after(errorDate)){
            bool=true;
        }
        return bool;
    }
    public boolean yesuseradd(int passport, int vaksina, int xodim, String now_date, String next_date) throws IOException, ClassNotFoundException {
        boolean bool=false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");
            pst = conn.prepareStatement("insert into vaksina_jarayoni(fuqaro_id,vaksina_id,xodim_id,hozirgi_sana_vaqt,belgilangan_sana_vaqt) values(?,?,?,?,?)");
            pst.setInt(1, passport);
            pst.setInt(2, vaksina);
            pst.setInt(3, xodim);
            pst.setDate(4, java.sql.Date.valueOf(now_date));
            pst.setDate(5, java.sql.Date.valueOf(next_date));
            if(pst.executeUpdate()==1){
                PreparedStatement pst1=conn.prepareStatement("update fuqaro_malumotlari set emlanganligi=true where id=? and (select count(fuqaro_id) as soni from vaksina_jarayoni where fuqaro_id=?)=(select qabul_qilish_soni from vaksina where id=(select distinct vaksina_id from vaksina_jarayoni where fuqaro_id=?));");
                pst1.setInt(1,passport);
                pst1.setInt(2,passport);
                pst1.setInt(3,passport);
                if(pst1.executeUpdate()==1){
                    bool=true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return bool;
    }

    public void getInformation() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");
            pst = conn.prepareStatement("select  fuqaro_malumotlari.passport_seriya,fuqaro_malumotlari.ism,fuqaro_malumotlari.familya,vaksina.nomi,Xodimlar.ism,Xodimlar.familya,vaksina_jarayoni.belgilangan_sana_vaqt,vaksina_jarayoni.hozirgi_sana_vaqt from vaksina_jarayoni inner join fuqaro_malumotlari on vaksina_jarayoni.fuqaro_id=fuqaro_malumotlari.id inner join Xodimlar on vaksina_jarayoni.xodim_id=Xodimlar.id inner join vaksina on vaksina_jarayoni.vaksina_id=vaksina.id;");
            rs = pst.executeQuery();
            while (rs.next()) {
                String Fuqaro_seria=rs.getString("fuqaro_malumotlari.passport_seriya");
                String Fuqaro_ismi=rs.getString("fuqaro_malumotlari.ism");
                String Fuqaro_familyasi=rs.getString("fuqaro_malumotlari.familya");
                String vaksina_nomi=rs.getString("vaksina.nomi");
                String xodim_ismi=rs.getString("Xodimlar.ism");
                String Xodim_familyasi=rs.getString("Xodimlar.familya");
                Date keyingi_doza_sanasi=rs.getDate("vaksina_jarayoni.belgilangan_sana_vaqt");
                Date qabul_qilgan_sanasi=rs.getDate("vaksina_jarayoni.hozirgi_sana_vaqt");
                Information.add(new Vaksina_jarayoni( Fuqaro_seria,Fuqaro_ismi,Fuqaro_familyasi,vaksina_nomi,xodim_ismi,Xodim_familyasi,keyingi_doza_sanasi,qabul_qilgan_sanasi));
            }
            pst.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println();
        }
    }

    public ArrayList<Vaksina_jarayoni> getInformations() {
        return Information;
    }

    ArrayList<Statistika> Statistics=new ArrayList<>();

    public void getStatistic() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covid_19_pbl", "root", "humoyun_1504");
            pst = conn.prepareStatement("SELECT COUNT(passport_seriya) AS SONI FROM fuqaro_malumotlari");
            rs = pst.executeQuery();
            while (rs.next()) {
                int emlangan=0;
                int soni = rs.getInt("SONI");
                PreparedStatement pst2 = conn.prepareStatement("SELECT IF(emlanganligi=true,id,0) as id FROM fuqaro_malumotlari;");
                ResultSet rs2 = pst2.executeQuery();
                while(rs2.next()){
                    int id=rs2.getInt("id");
                    if (id==0){
                        PreparedStatement pst1 = conn.prepareStatement("SELECT  COUNT(DISTINCT fuqaro_id) AS emlanganlar FROM vaksina_jarayoni");
                        ResultSet rs1 = pst1.executeQuery();
                        while (rs1.next()) {
                            int emlanganlar = rs1.getInt("emlanganlar");
                            Statistics.add(new Statistika(soni,emlangan, emlanganlar));
                        }
                    }else{
                        emlangan++;
                        PreparedStatement pst1 = conn.prepareStatement("SELECT  COUNT(DISTINCT fuqaro_id) AS emlanganlar FROM vaksina_jarayoni where fuqaro_id!=?");
                        pst1.setInt(1,id);
                        ResultSet rs1 = pst1.executeQuery();
                        while (rs1.next()) {
                            System.out.println(emlangan);
                            int emlanganlar = rs1.getInt("emlanganlar");
                            Statistics.add(new Statistika(soni,emlangan, emlanganlar));
                        }
                    }

                }
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public ArrayList<Statistika> getStatistics(){
        return Statistics;
    }
}

