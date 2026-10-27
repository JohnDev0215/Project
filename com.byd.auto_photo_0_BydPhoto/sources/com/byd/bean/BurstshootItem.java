package com.byd.bean;

/* JADX INFO: loaded from: classes.dex */
public class BurstshootItem {
    private String path;
    private boolean select;

    public BurstshootItem(String str) {
        this.path = str;
    }

    public boolean equals(Object obj) {
        return obj instanceof BurstshootItem ? this.path.equals(((BurstshootItem) obj).path) : super.equals(obj);
    }

    public String getPath() {
        return this.path;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setSelect(boolean z) {
        this.select = z;
    }
}
