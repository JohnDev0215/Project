package com.byd.music.beans;

/* JADX INFO: loaded from: classes5.dex */
public class SpinnerBean {
    private int bitmapRes;
    private boolean isSelect;
    private String name;

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public SpinnerBean(String str, int i, boolean z) {
        this.name = str;
        this.bitmapRes = i;
        this.isSelect = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getBitmapRes() {
        return this.bitmapRes;
    }

    public void setBitmapRes(int i) {
        this.bitmapRes = i;
    }
}
