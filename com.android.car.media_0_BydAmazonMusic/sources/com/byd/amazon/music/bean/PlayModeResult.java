package com.byd.amazon.music.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.byd.amazon.music.param.PlayMode;

/* JADX INFO: loaded from: classes2.dex */
public class PlayModeResult extends Result {
    public static final Parcelable.Creator<PlayModeResult> CREATOR = new a();
    public PlayMode b;

    public class a implements Parcelable.Creator<PlayModeResult> {
        @Override // android.os.Parcelable.Creator
        public final PlayModeResult createFromParcel(Parcel parcel) {
            return new PlayModeResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final PlayModeResult[] newArray(int i) {
            return new PlayModeResult[i];
        }
    }

    public PlayModeResult(int i) {
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

    public PlayModeResult(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = (PlayMode) parcel.readParcelable(PlayMode.class.getClassLoader());
    }
}
