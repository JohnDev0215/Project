package com.byd.sdkupdate.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class OtaVersionInfo implements Parcelable {
    public static final Parcelable.Creator<OtaVersionInfo> CREATOR = new Parcelable.Creator<OtaVersionInfo>() { // from class: com.byd.sdkupdate.aidl.OtaVersionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OtaVersionInfo createFromParcel(Parcel parcel) {
            return new OtaVersionInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public OtaVersionInfo[] newArray(int i) {
            return new OtaVersionInfo[i];
        }
    };
    public String updateTime;
    public int versionCode;
    public String versionDetail;
    public String versionName;

    public OtaVersionInfo() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.versionCode);
        parcel.writeString(this.versionName);
        parcel.writeString(this.updateTime);
        parcel.writeString(this.versionDetail);
    }

    public OtaVersionInfo(Parcel parcel) {
        this.versionCode = parcel.readInt();
        this.versionName = parcel.readString();
        this.updateTime = parcel.readString();
        this.versionDetail = parcel.readString();
    }
}
