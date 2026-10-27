package com.ts.androidauto.aap.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class MediaMetadata implements Parcelable {
    public static final Parcelable.Creator<MediaMetadata> CREATOR = new Parcelable.Creator<MediaMetadata>() { // from class: com.ts.androidauto.aap.data.MediaMetadata.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaMetadata createFromParcel(Parcel in) {
            return new MediaMetadata(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MediaMetadata[] newArray(int size) {
            return new MediaMetadata[size];
        }
    };
    private String mAlbum;
    private byte[] mAlbumArt;
    private String mArtist;
    private String mArtworkPath;
    private String mComposer;
    private int mDurationSeconds;
    private String mGenrel;
    private int mRepeatMode;
    private int mShuffleMode;
    private String mSong;
    private int mTotalTracks;
    private int mTrackNumber;
    private String mYear;

    public MediaMetadata() {
    }

    public String getSong() {
        return this.mSong;
    }

    public void setSong(String song) {
        this.mSong = song;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public void setAlbum(String album) {
        this.mAlbum = album;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public void setArtist(String artist) {
        this.mArtist = artist;
    }

    public int getDurationSeconds() {
        return this.mDurationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.mDurationSeconds = durationSeconds;
    }

    public String getArtworkPath() {
        return this.mArtworkPath;
    }

    public void setArtworkPath(String artworkPath) {
        this.mArtworkPath = artworkPath;
    }

    public byte[] getAlbumArt() {
        return this.mAlbumArt;
    }

    public void setAlbumArt(byte[] albumArt) {
        this.mAlbumArt = albumArt;
    }

    public String getComposer() {
        return this.mComposer;
    }

    public void setComposer(String composer) {
        this.mComposer = composer;
    }

    public String getGenrel() {
        return this.mGenrel;
    }

    public void setGenrel(String genrel) {
        this.mGenrel = genrel;
    }

    public String getYear() {
        return this.mYear;
    }

    public void setYear(String year) {
        this.mYear = year;
    }

    public int getTrackNumber() {
        return this.mTrackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.mTrackNumber = trackNumber;
    }

    public int getTotalTracks() {
        return this.mTotalTracks;
    }

    public void setTotalTracks(int totalTracks) {
        this.mTotalTracks = totalTracks;
    }

    public int getShuffleMode() {
        return this.mShuffleMode;
    }

    public void setShuffleMode(int shuffleMode) {
        this.mShuffleMode = shuffleMode;
    }

    public int getRepeatMode() {
        return this.mRepeatMode;
    }

    public void setRepeatMode(int repeatMode) {
        this.mRepeatMode = repeatMode;
    }

    public MediaMetadata(Parcel in) {
        this.mSong = in.readString();
        this.mAlbum = in.readString();
        this.mArtist = in.readString();
        this.mArtworkPath = in.readString();
        this.mDurationSeconds = in.readInt();
        int len = in.readInt();
        if (len <= 0) {
            this.mAlbumArt = null;
        } else {
            byte[] bArr = new byte[len];
            this.mAlbumArt = bArr;
            in.readByteArray(bArr);
        }
        this.mComposer = in.readString();
        this.mGenrel = in.readString();
        this.mYear = in.readString();
        this.mTrackNumber = in.readInt();
        this.mTotalTracks = in.readInt();
        this.mShuffleMode = in.readInt();
        this.mRepeatMode = in.readInt();
    }

    public void clear() {
        this.mSong = null;
        this.mAlbum = null;
        this.mArtist = null;
        this.mDurationSeconds = 0;
        this.mArtworkPath = null;
        this.mAlbumArt = null;
        this.mComposer = null;
        this.mGenrel = null;
        this.mYear = null;
        this.mTrackNumber = 0;
        this.mTotalTracks = 0;
        this.mShuffleMode = 0;
        this.mRepeatMode = 0;
    }

    public String toString() {
        return this.mSong + " " + this.mAlbum + " " + this.mArtist + " " + this.mArtworkPath + " " + this.mDurationSeconds + " " + this.mComposer + " " + this.mComposer + " " + this.mGenrel + " " + this.mYear + " " + this.mTrackNumber + " " + this.mTotalTracks + " " + this.mShuffleMode + " " + this.mRepeatMode;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mSong);
        parcel.writeString(this.mAlbum);
        parcel.writeString(this.mArtist);
        parcel.writeString(this.mArtworkPath);
        parcel.writeInt(this.mDurationSeconds);
        byte[] bArr = this.mAlbumArt;
        if (bArr != null && bArr.length > 0) {
            parcel.writeInt(bArr.length);
            parcel.writeByteArray(this.mAlbumArt);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.mComposer);
        parcel.writeString(this.mGenrel);
        parcel.writeString(this.mYear);
        parcel.writeInt(this.mTrackNumber);
        parcel.writeInt(this.mTotalTracks);
        parcel.writeInt(this.mShuffleMode);
        parcel.writeInt(this.mRepeatMode);
    }
}
