package com.byd.vrdownload.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class Failure implements Parcelable {
    public static final Parcelable.Creator<Failure> CREATOR = new Parcelable.Creator<Failure>() { // from class: com.byd.vrdownload.model.Failure.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Failure createFromParcel(Parcel parcel) {
            return new Failure(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Failure[] newArray(int i) {
            return new Failure[i];
        }
    };
    Exception exception;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected Failure(Parcel parcel) {
        this.exception = (Exception) parcel.readSerializable();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.exception);
    }

    public Failure(Exception exc) {
        if (exc == null) {
            this.exception = new Exception("未知错误");
        } else {
            this.exception = exc;
        }
    }

    public void setException(Exception exc) {
        this.exception = exc;
    }

    public Exception getException() {
        return this.exception;
    }

    public String toString() {
        return "Failure{exception=" + this.exception + '}';
    }
}
