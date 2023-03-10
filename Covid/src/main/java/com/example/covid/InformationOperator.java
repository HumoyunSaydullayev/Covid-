package com.example.covid;

import java.util.Date;

public class InformationOperator {
    private int id;
    private String Login;
    private String Parol;
    private String ism;
    private String familya;
    private Date tugulgan_yili;
    private String lavozimi;
    private String malakasi;

    public InformationOperator(int id, String login, String parol, String ism, String familya, Date tugulgan_yili, String lavozimi, String malakasi) {
        this.id = id;
        Login = login;
        Parol = parol;
        this.ism = ism;
        this.familya = familya;
        this.tugulgan_yili = tugulgan_yili;
        this.lavozimi = lavozimi;
        this.malakasi = malakasi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String Login) {
        this.Login=Login;
    }

    public void setParol(String Parol) {
        this.Parol=Parol;
    }

    public String getLogin() {
        return Login;
    }

    public String getParol() {
        return Parol;
    }
    public String getIsm() {
        return ism;
    }

    public void setIsm(String ism) {
        this.ism = ism;
    }

    public String getFamilya() {
        return familya;
    }

    public void setFamilya(String familya) {
        this.familya = familya;
    }

    public Date getTugulgan_yili() {
        return tugulgan_yili;
    }

    public void setTugulgan_yili(Date tugulgan_yili) {
        this.tugulgan_yili = tugulgan_yili;
    }

    public String getLavozimi() {
        return lavozimi;
    }

    public void setLavozimi(String lavozimi) {
        this.lavozimi = lavozimi;
    }

    public String getMalakasi() {
        return malakasi;
    }

    public void setMalakasi(String malakasi) {
        this.malakasi = malakasi;
    }


}