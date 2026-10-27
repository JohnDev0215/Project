package com.byd.music.beans;

/* JADX INFO: loaded from: classes5.dex */
public class PlayStateDBBean {
    private long id;
    private boolean playState;
    private long timeStamp;

    public PlayStateDBBean() {
    }

    public PlayStateDBBean(boolean z) {
        this.playState = z;
        this.timeStamp = System.currentTimeMillis();
    }

    public boolean getPlayState() {
        return this.playState;
    }

    public void setPlayState(boolean z) {
        this.playState = z;
        this.timeStamp = System.currentTimeMillis();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }
}
