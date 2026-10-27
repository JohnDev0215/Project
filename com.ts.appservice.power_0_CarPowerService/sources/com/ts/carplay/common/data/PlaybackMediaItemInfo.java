package com.ts.carplay.common.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackMediaItemInfo implements Parcelable {
    public String mAlbum;
    public String mAlbumArtist;
    public String mArtist;
    public String mComposer;
    public String mGenre;
    public int mPid;
    public String mTitle;
    private static final String TAG = PlaybackMediaItemInfo.class.getSimpleName();
    public static final Parcelable.Creator<PlaybackMediaItemInfo> CREATOR = new Parcelable.Creator<PlaybackMediaItemInfo>() { // from class: com.ts.carplay.common.data.PlaybackMediaItemInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlaybackMediaItemInfo createFromParcel(Parcel in) {
            return new PlaybackMediaItemInfo(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlaybackMediaItemInfo[] newArray(int size) {
            return new PlaybackMediaItemInfo[size];
        }
    };

    public PlaybackMediaItemInfo() {
        this.mPid = -1;
        this.mTitle = null;
        this.mAlbum = null;
        this.mArtist = null;
        this.mAlbumArtist = null;
        this.mGenre = null;
        this.mComposer = null;
    }

    public PlaybackMediaItemInfo(Parcel in) {
        this.mPid = in.readInt();
        this.mTitle = in.readString();
        this.mAlbum = in.readString();
        this.mArtist = in.readString();
        this.mAlbumArtist = in.readString();
        this.mGenre = in.readString();
        this.mComposer = in.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mPid);
        dest.writeString(this.mTitle);
        dest.writeString(this.mAlbum);
        dest.writeString(this.mArtist);
        dest.writeString(this.mAlbumArtist);
        dest.writeString(this.mGenre);
        dest.writeString(this.mComposer);
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("PlaybackMediaItemInfo{");
        sb.append("mPid = ");
        sb.append(this.mPid);
        sb.append(" ");
        sb.append("mTitle = ");
        sb.append(this.mTitle);
        sb.append(" ");
        sb.append("mAlbum = ");
        sb.append(this.mAlbum);
        sb.append(" ");
        sb.append("mArtist = ");
        sb.append(this.mArtist);
        sb.append(" ");
        sb.append("mAlbumArtist = ");
        sb.append(this.mAlbumArtist);
        sb.append(" ");
        sb.append("mGenre = ");
        sb.append(this.mGenre);
        sb.append(" ");
        sb.append("mComposer = ");
        sb.append(this.mComposer);
        sb.append(" ");
        sb.append("}");
        return sb.toString();
    }
}
