package com.byd.datasource.feature;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Response<T> implements Parcelable {
    public static final Parcelable.Creator<Response> CREATOR = new Parcelable.Creator<Response>() { // from class: com.byd.datasource.feature.Response.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };
    public final T result;
    public final Status status;

    public Response(int statusCode) {
        this(new Status(statusCode));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.status, flags);
        dest.writeParcelable((Parcelable) this.result, flags);
    }

    public Response(Status status) {
        this.status = status;
        this.result = null;
    }

    public Response(Status status, T result) {
        this.status = status;
        this.result = result;
    }

    public Response(Parcel parcel) {
        this.status = (Status) parcel.readParcelable(Status.class.getClassLoader());
        this.result = (T) parcel.readParcelable(getClass().getClassLoader());
    }
}
