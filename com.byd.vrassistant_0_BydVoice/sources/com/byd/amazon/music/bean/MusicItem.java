package com.byd.amazon.music.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class MusicItem implements Parcelable {
    public static final Parcelable.Creator<MusicItem> CREATOR = new Parcelable.Creator<MusicItem>() { // from class: com.byd.amazon.music.bean.MusicItem.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicItem createFromParcel(Parcel parcel) {
            return new MusicItem(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicItem[] newArray(int i) {
            return new MusicItem[i];
        }
    };
    private final transient String mAlum;
    private final transient String mMediaId;
    private final transient String mSinger;
    private final transient String mSongClass;
    private final transient String mTitle;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MusicItem(String str, String str2, String str3, String str4, String str5) {
        this.mMediaId = str;
        this.mTitle = str2;
        this.mSinger = str3;
        this.mAlum = str4;
        this.mSongClass = str5;
    }

    protected MusicItem(Parcel parcel) {
        this.mMediaId = parcel.readString();
        this.mTitle = parcel.readString();
        this.mSinger = parcel.readString();
        this.mAlum = parcel.readString();
        this.mSongClass = parcel.readString();
    }

    public String getMediaId() {
        return this.mMediaId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSinger() {
        return this.mSinger;
    }

    public String getAlum() {
        return this.mAlum;
    }

    public String getSongClass() {
        return this.mSongClass;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mMediaId);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSinger);
        parcel.writeString(this.mAlum);
        parcel.writeString(this.mSongClass);
    }

    public String toString() {
        return "MusicItem{mMediaId='" + this.mMediaId + "', mTitle='" + this.mTitle + "', mSinger='" + this.mSinger + "', mAlum='" + this.mAlum + "', mSongClass='" + this.mSongClass + "'}";
    }
}
