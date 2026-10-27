package com.byd.vrassistant.bean;

/* JADX INFO: loaded from: classes.dex */
public class LanguageBean {
    String country;
    String language;

    public LanguageBean(String str) {
        this.language = str;
        this.country = "";
    }

    public LanguageBean(String str, String str2) {
        this.language = str;
        this.country = str2;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }
}
