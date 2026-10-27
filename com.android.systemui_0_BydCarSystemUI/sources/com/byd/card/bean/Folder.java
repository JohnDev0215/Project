package com.byd.card.bean;

/* JADX INFO: loaded from: classes.dex */
public class Folder {
    public int folder_id;
    public String title;

    public Folder(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toString() {
        return "Folder{folder_id=" + this.folder_id + ", title='" + this.title + "'}";
    }
}
