package com.byd.vrdownload.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class SuccessResult implements Parcelable {
    public static final Parcelable.Creator<SuccessResult> CREATOR = new Parcelable.Creator<SuccessResult>() { // from class: com.byd.vrdownload.model.SuccessResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SuccessResult createFromParcel(Parcel parcel) {
            return new SuccessResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SuccessResult[] newArray(int i) {
            return new SuccessResult[i];
        }
    };
    String msg;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SuccessResult(String str) {
        this.msg = str;
    }

    protected SuccessResult(Parcel parcel) {
        this.msg = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.msg);
    }
}
