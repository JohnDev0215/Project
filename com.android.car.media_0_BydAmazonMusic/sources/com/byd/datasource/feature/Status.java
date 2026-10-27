package com.byd.datasource.feature;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
public class Status implements Parcelable {
    public static final Parcelable.Creator<Status> CREATOR = new Parcelable.Creator<Status>() { // from class: com.byd.datasource.feature.Status.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Status createFromParcel(Parcel parcel) {
            return new Status(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Status[] newArray(int i) {
            return new Status[i];
        }
    };
    public static final int STATUS_BLOCKING = -2147482647;
    public static final int STATUS_FAILED = -2147482648;
    public static final int STATUS_INVALID_ARG = -2147482645;
    public static final int STATUS_NONE = -1;
    public static final int STATUS_SUCCESS = 0;
    public static final int STATUS_TIMEOUT = -2147482646;
    public static final int STATUS_UNAVAILABLE = -10011;
    public static final int STATUS_UNKNOWN_ERROR = Integer.MIN_VALUE;
    public final int code;
    public final String description;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Code {
    }

    public Status(int i) {
        this.code = i;
        this.description = "";
    }

    public Status(int i, String str) {
        this.code = i;
        this.description = str;
    }

    public Status(Parcel parcel) {
        this.code = parcel.readInt();
        this.description = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.code);
        parcel.writeString(this.description);
    }
}
