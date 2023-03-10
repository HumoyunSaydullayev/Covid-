package com.example.covid;

import java.util.Date;

public class Vaksina_jarayoni {
    private String Fuqaro_seria;
    private String Fuqaro_ismi;
    private String Fuqaro_familyasi;
    private String vaksina_nomi;
    private String xodim_ismi;
    private String Xodim_familyasi;
    private Date keyingi_doza_sanasi;
    private Date qabul_qilgan_sanasi;

    public Vaksina_jarayoni(String fuqaro_seria, String fuqaro_ismi, String fuqaro_familyasi, String vaksina_nomi, String xodim_ismi, String xodim_familyasi,Date keyingi_doza_sanasi, Date qabul_qilgan_sana_vaqt) {
        this.Fuqaro_seria = fuqaro_seria;
        this.Fuqaro_ismi = fuqaro_ismi;
        this.Fuqaro_familyasi = fuqaro_familyasi;
        this.vaksina_nomi = vaksina_nomi;
        this.xodim_ismi = xodim_ismi;
        this.Xodim_familyasi = xodim_familyasi;
        this.keyingi_doza_sanasi=keyingi_doza_sanasi;
        this.qabul_qilgan_sanasi = qabul_qilgan_sana_vaqt;
    }

    public String getFuqaro_seria() {
        return Fuqaro_seria;
    }

    public void setFuqaro_seria(String fuqaro_seria) {
        Fuqaro_seria = fuqaro_seria;
    }

    public String getFuqaro_ismi() {
        return Fuqaro_ismi;
    }

    public void setFuqaro_ismi(String fuqaro_ismi) {
        Fuqaro_ismi = fuqaro_ismi;
    }

    public String getFuqaro_familyasi() {
        return Fuqaro_familyasi;
    }

    public void setFuqaro_familyasi(String fuqaro_familyasi) {
        Fuqaro_familyasi = fuqaro_familyasi;
    }

    public String getVaksina_nomi() {
        return vaksina_nomi;
    }

    public void setVaksina_nomi(String vaksina_nomi) {
        this.vaksina_nomi = vaksina_nomi;
    }

    public String getXodim_ismi() {
        return xodim_ismi;
    }

    public void setXodim_ismi(String xodim_ismi) {
        this.xodim_ismi = xodim_ismi;
    }

    public String getXodim_familyasi() {
        return Xodim_familyasi;
    }

    public void setXodim_familyasi(String xodim_familyasi) {
        Xodim_familyasi = xodim_familyasi;
    }

    public Date getKeyingi_doza_sanasi() {
        return keyingi_doza_sanasi;
    }

    public void setKeyingi_doza_sanasi(Date keyingi_doza_sanasi) {
        this.keyingi_doza_sanasi = keyingi_doza_sanasi;
    }

    public Date getQabul_qilgan_sanasi() {
        return qabul_qilgan_sanasi;
    }

    public void setQabul_qilgan_sanasi(Date qabul_qilgan_sanasi) {
        this.qabul_qilgan_sanasi = qabul_qilgan_sanasi;
    }

}
