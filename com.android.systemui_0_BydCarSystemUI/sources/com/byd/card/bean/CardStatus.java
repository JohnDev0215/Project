package com.byd.card.bean;

/* JADX INFO: loaded from: classes.dex */
public class CardStatus {
    private String pkg;
    private boolean show;

    public String getPkg() {
        return this.pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public boolean isShow() {
        return this.show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String toString() {
        return "CardStatus{pkg='" + this.pkg + "', show=" + this.show + '}';
    }
}
