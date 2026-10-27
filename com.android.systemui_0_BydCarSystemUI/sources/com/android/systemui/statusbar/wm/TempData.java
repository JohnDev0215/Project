package com.android.systemui.statusbar.wm;

/* JADX INFO: loaded from: classes.dex */
public class TempData {
    private String key;
    private int value;

    public TempData(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
