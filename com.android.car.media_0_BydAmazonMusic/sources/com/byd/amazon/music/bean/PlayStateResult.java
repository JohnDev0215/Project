package com.byd.amazon.music.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.byd.amazon.music.param.PlayState;

/* JADX INFO: loaded from: classes2.dex */
public class PlayStateResult extends Result {
    public static final Parcelable.Creator<PlayStateResult> CREATOR = new a();
    public PlayState b;

    public class a implements Parcelable.Creator<PlayStateResult> {
        @Override // android.os.Parcelable.Creator
        public final PlayStateResult createFromParcel(Parcel parcel) {
            return new PlayStateResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final PlayStateResult[] newArray(int i) {
            return new PlayStateResult[i];
        }
    }

    public PlayStateResult(int i) {
        super(i);
    }

    @Override // com.byd.amazon.music.bean.Result, android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // com.byd.amazon.music.bean.Result, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeParcelable(this.b, i);
    }

    public PlayStateResult(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = (PlayState) parcel.readParcelable(PlayState.class.getClassLoader());
    }
}
