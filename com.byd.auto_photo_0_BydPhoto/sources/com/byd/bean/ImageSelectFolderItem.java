package com.byd.bean;

/* JADX INFO: loaded from: classes.dex */
public class ImageSelectFolderItem {
    private int childCount;
    private FileBean firstBean;
    private String name;
    private String path;

    public ImageSelectFolderItem(String str, String str2, int i, FileBean fileBean) {
        this.name = str;
        this.path = str2;
        this.childCount = i;
        this.firstBean = fileBean;
    }

    public int getChildCount() {
        return this.childCount;
    }

    public FileBean getFirstBean() {
        return this.firstBean;
    }

    public String getName() {
        return this.name;
    }

    public String getPath() {
        return this.path;
    }

    public void setChildCount(int i) {
        this.childCount = i;
    }

    public void setFirstBean(FileBean fileBean) {
        this.firstBean = fileBean;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPath(String str) {
        this.path = str;
    }
}
