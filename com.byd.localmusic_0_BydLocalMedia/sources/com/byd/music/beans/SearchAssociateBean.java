package com.byd.music.beans;

/* JADX INFO: loaded from: classes5.dex */
public class SearchAssociateBean {
    public static final int TYPE_ASSOCIATE = 1;
    public static final int TYPE_HISTORY = 0;
    private String content;
    private int type;

    public SearchAssociateBean(int i, String str) {
        this.type = i;
        this.content = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }
}
