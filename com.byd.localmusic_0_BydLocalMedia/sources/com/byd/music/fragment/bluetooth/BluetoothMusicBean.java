package com.byd.music.fragment.bluetooth;

import android.graphics.Bitmap;

/* JADX INFO: loaded from: classes5.dex */
public class BluetoothMusicBean {
    private String album;
    private String artist;
    private boolean bluetoothIsConnect;
    private long duration;
    private Bitmap iconBtimap;
    private String iconUrl;
    private String imgUrl;
    private boolean playOrPause;
    private long positon;
    private String title;

    public BluetoothMusicBean(boolean z, long j, String str, String str2, String str3, boolean z2) {
        this.bluetoothIsConnect = z;
        this.duration = j;
        this.artist = str;
        this.title = str2;
        this.playOrPause = z2;
        this.imgUrl = str3;
    }

    public boolean isBluetoothIsConnect() {
        return this.bluetoothIsConnect;
    }

    public void setBluetoothIsConnect(boolean z) {
        this.bluetoothIsConnect = z;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String str) {
        this.imgUrl = str;
    }

    public boolean getPlayOrPause() {
        return this.playOrPause;
    }

    public void setPlayOrPause(boolean z) {
        this.playOrPause = z;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setAlbum(String str) {
        this.album = str;
    }

    public long getPositon() {
        return this.positon;
    }

    public void setPositon(long j) {
        this.positon = j;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public Bitmap getIconBtimap() {
        return this.iconBtimap;
    }

    public void setIconBtimap(Bitmap bitmap) {
        this.iconBtimap = bitmap;
    }
}
