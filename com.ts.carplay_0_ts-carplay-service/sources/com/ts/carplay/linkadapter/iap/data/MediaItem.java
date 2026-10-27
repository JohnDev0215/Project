package com.ts.carplay.linkadapter.iap.data;

/* JADX INFO: loaded from: classes.dex */
public class MediaItem {
    private String mAlbumPid;
    private String mAlbumTitle;
    private String mArtist;
    private String mArtistPid;
    private String mComposer;
    private String mComposerPid;
    private String mGenre;
    private String mGenrePid;
    private String mPid;
    private String mTitle;
    private int mMediaType = -1;
    private int mAlbumTrackNum = -1;

    public String getPid() {
        return this.mPid;
    }

    public void setPid(String pid) {
        this.mPid = pid;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public int getMediaType() {
        return this.mMediaType;
    }

    public void setMediaType(int type) {
        if (type < 0 || type > 2) {
            return;
        }
        this.mMediaType = type;
    }

    public String getAlbumPid() {
        return this.mAlbumPid;
    }

    public void setAlbumPid(String pid) {
        this.mAlbumPid = pid;
    }

    public String getAlbumTitle() {
        return this.mAlbumTitle;
    }

    public void setAlbumTitle(String title) {
        this.mAlbumTitle = title;
    }

    public int getAlbumTrackNum() {
        return this.mAlbumTrackNum;
    }

    public void setAlbumTrackNum(int num) {
        if (num < 0) {
            return;
        }
        this.mAlbumTrackNum = num;
    }

    public String getArtistPid() {
        return this.mArtistPid;
    }

    public void setArtistPid(String pid) {
        this.mArtistPid = pid;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public void setArtist(String artist) {
        this.mArtist = artist;
    }

    public String getGenrePid() {
        return this.mGenrePid;
    }

    public void setGenrePid(String pid) {
        this.mGenrePid = pid;
    }

    public String getGenre() {
        return this.mGenre;
    }

    public void setGenre(String genre) {
        this.mGenre = genre;
    }

    public String getComposerPid() {
        return this.mComposerPid;
    }

    public void setComposerPid(String pid) {
        this.mComposerPid = pid;
    }

    public String getComposer() {
        return this.mComposer;
    }

    public void setComposer(String composer) {
        this.mComposer = composer;
    }

    public String toString() {
        return "MediaItem{mPid='" + this.mPid + "', mTitle='" + this.mTitle + "', mMediaType=" + this.mMediaType + ", mAlbumPid='" + this.mAlbumPid + "', mAlbumTitle='" + this.mAlbumTitle + "', mAlbumTrackNum=" + this.mAlbumTrackNum + ", mArtistPid='" + this.mArtistPid + "', mArtist='" + this.mArtist + "', mGenrePid='" + this.mGenrePid + "', mGenre='" + this.mGenre + "', mComposerPid='" + this.mComposerPid + "', mComposer='" + this.mComposer + "'}";
    }
}
