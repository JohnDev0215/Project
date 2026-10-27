package com.byd.music.beans;

/* JADX INFO: loaded from: classes5.dex */
public class SampleMusicBean {
    private String album;
    private String artist;
    private long duration;
    private String title;
    public int type;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String str) {
        this.album = str;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public SampleMusicBean(String str, String str2, String str3, long j) {
        this.title = str;
        this.artist = str2;
        this.album = str3;
        this.duration = j;
    }
}
