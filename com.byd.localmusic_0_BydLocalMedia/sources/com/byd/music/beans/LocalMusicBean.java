package com.byd.music.beans;

import com.blankj.utilcode.util.StringUtils;
import com.byd.music.R;
import com.byd.music.pinyin.PinyinItem;
import com.byd.music.utils.Pinyin;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public class LocalMusicBean implements Serializable, PinyinItem {
    private String album;
    private long albumId;
    private String artist;
    private long artistId;
    private long createTime;
    private int duration;
    private long id;
    private int musicId;
    private String pinyin;
    private long playTime;
    private int progress;
    private int state;
    private int storageType;
    private String title;
    private String uri;
    private boolean playing = false;
    private boolean isCollect = false;
    private boolean isLocal = false;

    public LocalMusicBean() {
    }

    public LocalMusicBean(int i, int i2, int i3) {
        this.progress = i;
        this.duration = i2;
        this.state = i3;
    }

    public LocalMusicBean(int i, int i2, int i3, int i4) {
        this.progress = i;
        this.duration = i2;
        this.state = i3;
        this.storageType = i4;
    }

    public boolean isCollect() {
        return this.isCollect;
    }

    public void setCollect(boolean z) {
        this.isCollect = z;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
        setPinyin(str);
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String str) {
        this.uri = str;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
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

    public boolean isPlaying() {
        return this.playing;
    }

    public long getPlayTime() {
        return this.playTime;
    }

    public void setPlayTime(long j) {
        this.playTime = j;
    }

    public boolean isLocal() {
        return this.isLocal;
    }

    public void setLocal(boolean z) {
        this.isLocal = z;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public void setPlaying(boolean z) {
        this.playing = z;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public int getMusicId() {
        return this.musicId;
    }

    public void setMusicId(int i) {
        this.musicId = i;
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public long getArtistId() {
        return this.artistId;
    }

    public void setArtistId(long j) {
        this.artistId = j;
    }

    public long getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(long j) {
        this.albumId = j;
    }

    public int getStorageType() {
        return this.storageType;
    }

    public void setStorageType(int i) {
        this.storageType = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LocalMusicBean localMusicBean = (LocalMusicBean) obj;
        return this.musicId == localMusicBean.musicId && this.storageType == localMusicBean.getStorageType() && this.uri.equals(localMusicBean.getUri());
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.id), Integer.valueOf(this.storageType));
    }

    public String getStorageString() {
        int i = this.storageType;
        if (i == 0) {
            return StringUtils.getString(R.string.local_storage);
        }
        if (i == 1) {
            return StringUtils.getString(R.string.local_storage_sdcard);
        }
        return StringUtils.getString(R.string.local_storage_usb);
    }

    @Override // com.byd.music.pinyin.PinyinItem
    public String getItemPinyin() {
        return this.pinyin;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String str) {
        this.pinyin = Pinyin.getPrefix(str).toUpperCase();
    }
}
