package com.byd.carsettings.system.model;

import com.byd.mvp.model.BydData;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class BydLanguage extends BydData {
    private int canValue;
    private boolean checked;
    private String description;
    private Locale locale;
    private String text;

    public BydLanguage(Locale locale, int i) {
        this.locale = locale;
        this.canValue = i;
    }

    public int getCanValue() {
        return this.canValue;
    }

    public String getDescription() {
        return this.description;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public String getText() {
        return this.text;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setCanValue(int i) {
        this.canValue = i;
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String toString() {
        return "BydLanguage{locale=" + this.locale + ", canValue=" + this.canValue + ", text=" + this.text + ", description=" + this.description + ", checked=" + this.checked + '}';
    }
}
