package com.byd.music.beans;

/* JADX INFO: loaded from: classes5.dex */
public class ApiChangeBean {
    public static final int FLAG_CMIU_NO_UPDATE = 2;
    public static final int FLAG_RADIO_NO_SET = 1;
    private int extra;

    public ApiChangeBean() {
        this.extra = 0;
    }

    public ApiChangeBean(int i) {
        this.extra = i;
    }

    public boolean flagEnable(int i) {
        return (i & this.extra) > 0;
    }
}
