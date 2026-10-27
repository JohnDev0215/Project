package com.byd.sdkupdate.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class OtaVersionInfo implements Parcelable {
    public static final Parcelable.Creator<OtaVersionInfo> CREATOR = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3143c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3144d;
    public String s;
    public String x;

    public class a implements Parcelable.Creator<OtaVersionInfo> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public OtaVersionInfo createFromParcel(Parcel parcel) {
            return new OtaVersionInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public OtaVersionInfo[] newArray(int i2) {
            return new OtaVersionInfo[i2];
        }
    }

    public OtaVersionInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f3143c);
        parcel.writeString(this.f3144d);
        parcel.writeString(this.s);
        parcel.writeString(this.x);
    }

    public OtaVersionInfo(Parcel parcel) {
        this.f3143c = parcel.readInt();
        this.f3144d = parcel.readString();
        this.s = parcel.readString();
        this.x = parcel.readString();
    }
}
