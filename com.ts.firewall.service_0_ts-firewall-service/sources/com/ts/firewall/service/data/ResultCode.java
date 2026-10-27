package com.ts.firewall.service.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public enum ResultCode implements Parcelable {
    SUCCESS(0),
    GENERIC_ERROR(1);

    public static final Parcelable.Creator<ResultCode> CREATOR = new Parcelable.Creator<ResultCode>() { // from class: com.ts.firewall.service.data.ResultCode.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResultCode createFromParcel(Parcel in) {
            return ResultCode.valueOf(in.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ResultCode[] newArray(int size) {
            return new ResultCode[size];
        }
    };
    private final int value;

    ResultCode(int value) {
        this.value = value;
    }

    public static ResultCode valueOf(int type) {
        if (type == 0) {
            return SUCCESS;
        }
        if (type == 1) {
            return GENERIC_ERROR;
        }
        return null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name());
    }
}
