package com.byd.music.beans;

/* JADX INFO: loaded from: classes5.dex */
public class SearchHistoryBean {
    public static final int TYPE_ALBUM = 3;
    public static final int TYPE_ARTIST = 2;
    public static final int TYPE_SONG = 1;
    private long id;
    private String title;
    private int type;

    public SearchHistoryBean() {
    }

    public SearchHistoryBean(String str, int i) {
        this.title = str;
        this.type = i;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
