package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class NameInfo implements Parcelable {
    public static final Parcelable.Creator<NameInfo> CREATOR = new Parcelable.Creator<NameInfo>() { // from class: com.neusoft.na.ocservice.NameInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NameInfo createFromParcel(Parcel parcel) {
            return new NameInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NameInfo[] newArray(int i) {
            return new NameInfo[i];
        }
    };
    String code;
    String name;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public NameInfo() {
        this.code = "";
        this.name = "";
    }

    protected NameInfo(Parcel parcel) {
        this.code = "";
        this.name = "";
        this.code = parcel.readString();
        this.name = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.code);
        parcel.writeString(this.name);
    }
}
