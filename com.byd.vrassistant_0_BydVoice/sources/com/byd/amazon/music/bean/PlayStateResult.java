package com.byd.amazon.music.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.byd.amazon.music.param.PlayState;

/* JADX INFO: loaded from: classes.dex */
public class PlayStateResult extends Result {
    public static final Parcelable.Creator<PlayStateResult> CREATOR = new Parcelable.Creator<PlayStateResult>() { // from class: com.byd.amazon.music.bean.PlayStateResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlayStateResult createFromParcel(Parcel parcel) {
            return new PlayStateResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlayStateResult[] newArray(int i) {
            return new PlayStateResult[i];
        }
    };
    private PlayState mState;

    @Override // com.byd.amazon.music.bean.Result, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlayStateResult(int i) {
        super(i);
    }

    public PlayStateResult(Parcel parcel) {
        this.mResult = parcel.readInt();
        this.mState = (PlayState) parcel.readParcelable(PlayState.class.getClassLoader());
    }

    public PlayState getState() {
        return this.mState;
    }

    public void setState(PlayState playState) {
        this.mState = playState;
    }

    @Override // com.byd.amazon.music.bean.Result, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mResult);
        parcel.writeParcelable(this.mState, i);
    }
}
