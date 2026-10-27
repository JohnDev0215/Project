package com.byd.mvcadapter;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class EmptyResult implements Parcelable {
    public static final Parcelable.Creator<EmptyResult> CREATOR = new a();

    public static class a implements Parcelable.Creator<EmptyResult> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public EmptyResult createFromParcel(Parcel parcel) {
            return new EmptyResult();
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public EmptyResult[] newArray(int i) {
            return new EmptyResult[i];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }
}
