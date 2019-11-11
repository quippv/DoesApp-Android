package com.example.doesapp;

public class MyDoes {

    String titleDoes, descDoes, dateDoes, keyDoes;

    public MyDoes() {
    }

    public MyDoes(String titleDoes, String descDoes, String dateDoes, String keyDoes) {
        this.titleDoes = titleDoes;
        this.descDoes = descDoes;
        this.dateDoes = dateDoes;
        this.keyDoes = keyDoes;
    }

    public String getKeyDoes() {
        return keyDoes;
    }

    public void setKeyDoes(String keyDoes) {
        this.keyDoes = keyDoes;
    }

    public String getTitleDoes() {
        return titleDoes;
    }

    public void setTitleDoes(String titleDoes) {
        this.titleDoes = titleDoes;
    }

    public String getDescDoes() {
        return descDoes;
    }

    public void setDescDoes(String descDoes) {
        this.descDoes = descDoes;
    }

    public String getDateDoes() {
        return dateDoes;
    }

    public void setDateDoes(String dateDoes) {
        this.dateDoes = dateDoes;
    }
}
