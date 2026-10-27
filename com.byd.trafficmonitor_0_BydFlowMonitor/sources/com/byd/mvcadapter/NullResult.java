package com.byd.mvcadapter;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class NullResult implements Parcelable {
    public static final Parcelable.Creator<NullResult> CREATOR = new a();

    public static class a implements Parcelable.Creator<NullResult> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public NullResult createFromParcel(Parcel parcel) {
            return new NullResult();
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public NullResult[] newArray(int i2) {
            return new NullResult[i2];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
    }
}
