package com.byd.auto_emanual.search;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LevelMenu {
    private String isDefaultLanguage;
    private String languageCode;
    private List<LevelOne> languageMenu;

    public String getIsDefaultLanguage() {
        return this.isDefaultLanguage;
    }

    public void setIsDefaultLanguage(String isDefaultLanguage) {
        this.isDefaultLanguage = isDefaultLanguage;
    }

    public String getLanguageCode() {
        return this.languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public List<LevelOne> getLanguageMenu() {
        return this.languageMenu;
    }

    public void setLanguageMenu(List<LevelOne> languageMenu) {
        this.languageMenu = languageMenu;
    }
}
