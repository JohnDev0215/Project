package com.byd.amazon.music.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class MusicItemResult extends Result {
    public static final Parcelable.Creator<MusicItemResult> CREATOR = new a();
    public MusicItem b;

    public class a implements Parcelable.Creator<MusicItemResult> {
        @Override // android.os.Parcelable.Creator
        public final MusicItemResult createFromParcel(Parcel parcel) {
            return new MusicItemResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final MusicItemResult[] newArray(int i) {
            return new MusicItemResult[i];
        }
    }

    public MusicItemResult(int i) {
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

    public MusicItemResult(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = (MusicItem) parcel.readParcelable(MusicItem.class.getClassLoader());
    }
}
