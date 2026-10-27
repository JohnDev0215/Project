package com.byd.amazon.music.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class BooleanResult extends Result {
    public static final Parcelable.Creator<BooleanResult> CREATOR = new Parcelable.Creator<BooleanResult>() { // from class: com.byd.amazon.music.bean.BooleanResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BooleanResult createFromParcel(Parcel parcel) {
            return new BooleanResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BooleanResult[] newArray(int i) {
            return new BooleanResult[i];
        }
    };
    private boolean mBoolean;

    public BooleanResult(int i) {
        super(i);
    }

    public BooleanResult(Parcel parcel) {
        this.mResult = parcel.readInt();
        this.mBoolean = parcel.readBoolean();
    }

    public void setBoolean(boolean z) {
        this.mBoolean = z;
    }

    public boolean getBoolean() {
        return this.mBoolean;
    }

    @Override // com.byd.amazon.music.bean.Result, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mResult);
        parcel.writeBoolean(this.mBoolean);
    }
}
