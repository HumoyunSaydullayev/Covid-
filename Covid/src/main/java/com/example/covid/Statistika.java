package com.example.covid;

public class Statistika {
    private int umumiy;
    private int emlanganlar;
    private int emlanayotganlar;

    public Statistika(int umumiy, int emlanganlar, int emlanayotganlar) {
        this.umumiy = umumiy;
        this.emlanganlar = emlanganlar;
        this.emlanayotganlar = emlanayotganlar;
    }

    public int getUmumiy() {
        return umumiy;
    }

    public void setUmumiy(int umumiy) {
        this.umumiy = umumiy;
    }

    public int getEmlanganlar() {
        return emlanganlar;
    }

    public void setEmlanganlar(int emlanganlar) {
        this.emlanganlar = emlanganlar;
    }

    public int getEmlanayotganlar() {
        return emlanayotganlar;
    }

    public void setEmlanayotganlar(int emlanayotganlar) {
        this.emlanayotganlar = emlanayotganlar;
    }
    public int getEmlanmaganlar(){
        return umumiy-emlanganlar-emlanayotganlar;
    }

}