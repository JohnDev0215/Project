package com.byd.music.beans;

/* JADX INFO: loaded from: classes5.dex */
public class SearchTagBean {
    public static final int TYPE_HISTORY = 0;
    public static final int TYPE_HOT = 1;
    private String keyword;
    private int type;

    public SearchTagBean(int i, String str) {
        this.type = i;
        this.keyword = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }
}
