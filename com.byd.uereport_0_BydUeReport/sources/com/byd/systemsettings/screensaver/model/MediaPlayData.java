package com.byd.systemsettings.screensaver.model;

import com.byd.mvp.model.BydData;

/* JADX INFO: loaded from: classes2.dex */
public class MediaPlayData extends BydData {
    public static final int MUSIC_SRC_AM = 18;
    public static final int MUSIC_SRC_AUX = 5;
    public static final int MUSIC_SRC_BT = 6;
    public static final int MUSIC_SRC_DAB = 17;
    public static final int MUSIC_SRC_FM = 7;
    public static final int MUSIC_SRC_HIMALAYA = 12;
    public static final int MUSIC_SRC_KARAOKE = 15;
    public static final int MUSIC_SRC_KOALA = 10;
    public static final int MUSIC_SRC_KUWO = 9;
    public static final int MUSIC_SRC_LOCAL = 1;
    public static final int MUSIC_SRC_SD = 4;
    public static final int MUSIC_SRC_SPOTIFY = 16;
    public static final int MUSIC_SRC_USB1 = 2;
    public static final int MUSIC_SRC_USB2 = 3;
    public static final int MUSIC_SRC_VIDEO = 8;
    public static final int MUSIC_SRC_YUNTING = 14;
    public static final int PLAY_STATE_PAUSE = 2;
    public static final int PLAY_STATE_PLAYING = 1;
    private String artistName;
    private String imgUrl;
    private String musicName;
    private int musicSource;
    private int playState;

    public MediaPlayData() {
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public String getMusicName() {
        return this.musicName;
    }

    public int getMusicSource() {
        return this.musicSource;
    }

    public int getPlayState() {
        return this.playState;
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public void setMusicName(String str) {
        this.musicName = str;
    }

    public void setMusicSource(int i8) {
        this.musicSource = i8;
    }

    public void setPlayState(int i8) {
        this.playState = i8;
    }

    public String toString() {
        return "MediaPlayData{musicName='" + this.musicName + "', artistName='" + this.artistName + "', playState=" + this.playState + ", musicSource=" + this.musicSource + ", imgUrl='" + this.imgUrl + "'}";
    }

    public MediaPlayData(String str, String str2, int i8, int i9, String str3) {
        this.musicName = str;
        this.artistName = str2;
        this.playState = i8;
        this.musicSource = i9;
        this.imgUrl = str3;
    }
}
