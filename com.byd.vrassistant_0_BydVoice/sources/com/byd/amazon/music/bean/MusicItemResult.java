package com.byd.amazon.music.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class MusicItemResult extends Result {
    public static final Parcelable.Creator<MusicItemResult> CREATOR = new Parcelable.Creator<MusicItemResult>() { // from class: com.byd.amazon.music.bean.MusicItemResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicItemResult createFromParcel(Parcel parcel) {
            return new MusicItemResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MusicItemResult[] newArray(int i) {
            return new MusicItemResult[i];
        }
    };
    private MusicItem mMusicItem;

    @Override // com.byd.amazon.music.bean.Result, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MusicItemResult(int i) {
        super(i);
    }

    public MusicItemResult(Parcel parcel) {
        this.mResult = parcel.readInt();
        this.mMusicItem = (MusicItem) parcel.readParcelable(MusicItem.class.getClassLoader());
    }

    public MusicItem getMusicItem() {
        return this.mMusicItem;
    }

    public void setMusicItem(MusicItem musicItem) {
        this.mMusicItem = musicItem;
    }

    @Override // com.byd.amazon.music.bean.Result, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mResult);
        parcel.writeParcelable(this.mMusicItem, i);
    }
}
