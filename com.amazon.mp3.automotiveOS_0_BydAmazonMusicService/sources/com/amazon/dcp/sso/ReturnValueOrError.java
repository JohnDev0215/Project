package com.amazon.dcp.sso;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes2.dex */
public class ReturnValueOrError implements Parcelable {
    public static final Parcelable.Creator<ReturnValueOrError> CREATOR = new a();
    private final Bundle mResponse;
    private final int mResponseCode;
    private final String mResponseMessage;

    /* JADX INFO: compiled from: DCP */
    public static class a implements Parcelable.Creator<ReturnValueOrError> {
        @Override // android.os.Parcelable.Creator
        public ReturnValueOrError createFromParcel(Parcel parcel) {
            return new ReturnValueOrError(parcel, (a) null);
        }

        @Override // android.os.Parcelable.Creator
        public ReturnValueOrError[] newArray(int i) {
            return new ReturnValueOrError[i];
        }
    }

    public /* synthetic */ ReturnValueOrError(Parcel parcel, a aVar) {
        this(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getResponse() {
        return this.mResponse;
    }

    public int getResponseCode() {
        return this.mResponseCode;
    }

    public String getResponseMessage() {
        return this.mResponseMessage;
    }

    public boolean isError() {
        return this.mResponseCode != 1;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.mResponse);
        parcel.writeInt(this.mResponseCode);
        parcel.writeString(this.mResponseMessage);
    }

    public ReturnValueOrError(int i, String str) {
        this.mResponse = null;
        this.mResponseCode = i;
        this.mResponseMessage = str;
    }

    private ReturnValueOrError(Parcel parcel) {
        this.mResponse = parcel.readBundle();
        this.mResponseCode = parcel.readInt();
        this.mResponseMessage = parcel.readString();
    }
}
