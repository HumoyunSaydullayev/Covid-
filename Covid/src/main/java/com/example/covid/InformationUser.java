package com.example.covid;

import java.util.Date;

public class InformationUser {
    private int id;
    private String seria;
    private String ism;
    private String familya;
    private String sharifi;
    private String jinsi;
    private Date tugulgan_yili;
    private String yashash_manzili;
    private Boolean Emlanganligi;
    private int vaksina_id;
    private int takrorlanganligi;

    public InformationUser(int id, String seria, String ism, String familya, String sharifi, String jinsi, Date tugulgan_yili, String yashash_manzili, Boolean emlanganligi,int takrorlanganligi,int vaksina_id) {
        this.id = id;
        this.seria = seria;
        this.ism = ism;
        this.familya = familya;
        this.sharifi = sharifi;
        this.jinsi = jinsi;
        this.tugulgan_yili = tugulgan_yili;
        this.yashash_manzili = yashash_manzili;
        this.Emlanganligi = emlanganligi;
        this.takrorlanganligi=takrorlanganligi;
        this.vaksina_id=vaksina_id;
    }

    public int getTakrorlanganligi() {
        return takrorlanganligi;
    }

    public void setTakrorlanganligi(int takrorlanganligi) {
        this.takrorlanganligi = takrorlanganligi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
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

    public String getSharifi() {
        return sharifi;
    }

    public void setSharifi(String sharifi) {
        this.sharifi = sharifi;
    }

    public String getJinsi() {
        return jinsi;
    }

    public void setJinsi(String jinsi) {
        this.jinsi = jinsi;
    }

    public Date getTugulgan_yili() {
        return tugulgan_yili;
    }

    public void setTugulgan_yili(Date tugulgan_yili) {
        this.tugulgan_yili = tugulgan_yili;
    }

    public String getYashash_manzili() {
        return yashash_manzili;
    }

    public void setYashash_manzili(String yashash_manzili) {
        this.yashash_manzili = yashash_manzili;
    }

    public Boolean getEmlanganligi() {
        return Emlanganligi;
    }

    public void setEmlanganligi(Boolean emlanganligi) {
        Emlanganligi = emlanganligi;
    }

    public int getVaksina_id() {
        return vaksina_id;
    }

    public void setVaksina_id(int valsina_id) {
        this.vaksina_id = valsina_id;
    }

}