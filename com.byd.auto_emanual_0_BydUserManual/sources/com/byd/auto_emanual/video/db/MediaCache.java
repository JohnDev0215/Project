package com.byd.auto_emanual.video.db;

/* JADX INFO: loaded from: classes.dex */
public class MediaCache {
    public static final int STATE_DONE = 1;
    public static final int STATE_LOADING = 0;
    private int current;
    private int id;
    private String localPath;
    private int state;
    private long time;
    private String url;

    public MediaCache(int id, String localPath, String url, int state, int current, long time) {
        this.id = id;
        this.localPath = localPath;
        this.url = url;
        this.state = state;
        this.current = current;
        this.time = time;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCurrent() {
        return this.current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
