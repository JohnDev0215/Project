package com.byd.amazon.music.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class BooleanResult extends Result {
    public static final Parcelable.Creator<BooleanResult> CREATOR = new a();
    public boolean b;

    public class a implements Parcelable.Creator<BooleanResult> {
        @Override // android.os.Parcelable.Creator
        public final BooleanResult createFromParcel(Parcel parcel) {
            return new BooleanResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final BooleanResult[] newArray(int i) {
            return new BooleanResult[i];
        }
    }

    public BooleanResult(int i) {
        super(i);
    }

    @Override // com.byd.amazon.music.bean.Result, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeBoolean(this.b);
    }

    public BooleanResult(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readBoolean();
    }
}
