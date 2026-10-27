package com.byd.datasource.feature;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Response<T> implements Parcelable {
    public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() { // from class: com.byd.datasource.feature.Response.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Response createFromParcel(Parcel parcel) {
            return new Response(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Response[] newArray(int i) {
            return new Response[i];
        }
    };
    public final T result;
    public final Status status;

    public Response(int i) {
        this(new Status(i));
    }

    public Response(Parcel parcel) {
        this.status = (Status) parcel.readParcelable(Status.class.getClassLoader());
        this.result = (T) parcel.readParcelable(getClass().getClassLoader());
    }

    public Response(Status status) {
        this.status = status;
        this.result = null;
    }

    public Response(Status status, T t) {
        this.status = status;
        this.result = t;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.status, i);
        parcel.writeParcelable((Parcelable) this.result, i);
    }
}
