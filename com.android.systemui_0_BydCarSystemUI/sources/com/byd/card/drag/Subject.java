package com.byd.card.drag;

/* JADX INFO: loaded from: classes.dex */
public class Subject {
    private int img;
    private String title;

    public Subject(String title, int img) {
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg() {
        return this.img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
