package com.byd.amazon.music.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.byd.amazon.music.param.PlayMode;

/* JADX INFO: loaded from: classes.dex */
public class PlayModeResult extends Result {
    public static final Parcelable.Creator<PlayModeResult> CREATOR = new Parcelable.Creator<PlayModeResult>() { // from class: com.byd.amazon.music.bean.PlayModeResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlayModeResult createFromParcel(Parcel parcel) {
            return new PlayModeResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlayModeResult[] newArray(int i) {
            return new PlayModeResult[i];
        }
    };
    private PlayMode mPlayMode;

    @Override // com.byd.amazon.music.bean.Result, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlayModeResult(int i) {
        super(i);
    }

    public PlayModeResult(Parcel parcel) {
        this.mResult = parcel.readInt();
        this.mPlayMode = (PlayMode) parcel.readParcelable(PlayMode.class.getClassLoader());
    }

    public PlayMode getPlayMode() {
        return this.mPlayMode;
    }

    public void setPlayMode(PlayMode playMode) {
        this.mPlayMode = playMode;
    }

    @Override // com.byd.amazon.music.bean.Result, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mResult);
        parcel.writeParcelable(this.mPlayMode, i);
    }
}
