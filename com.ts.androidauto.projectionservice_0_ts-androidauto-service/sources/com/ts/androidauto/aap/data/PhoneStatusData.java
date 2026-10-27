package com.ts.androidauto.aap.data;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class PhoneStatusData implements Parcelable {
    public static final Parcelable.Creator<PhoneStatusData> CREATOR = new Parcelable.Creator<PhoneStatusData>() { // from class: com.ts.androidauto.aap.data.PhoneStatusData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PhoneStatusData createFromParcel(Parcel in) {
            return new PhoneStatusData(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PhoneStatusData[] newArray(int size) {
            return new PhoneStatusData[size];
        }
    };
    private int mCallerDurationSeconds;
    private String mCallerId;
    private String mCallerNumber;
    private String mCallerNumberType;
    private byte[] mCallerThumbnail;
    private int mPhoneStatus;

    public PhoneStatusData() {
    }

    public int getPhoneStatus() {
        return this.mPhoneStatus;
    }

    public int getCallerDurationSeconds() {
        return this.mCallerDurationSeconds;
    }

    public String getCallerNumber() {
        return this.mCallerNumber;
    }

    public String getCallerId() {
        return this.mCallerId;
    }

    public String getCallerNumberType() {
        return this.mCallerNumberType;
    }

    public byte[] getCallerThumbnail() {
        return this.mCallerThumbnail;
    }

    public void setPhoneStatus(int phoneStatus) {
        this.mPhoneStatus = phoneStatus;
    }

    public void setCallerDurationSeconds(int callerDurationSeconds) {
        this.mCallerDurationSeconds = callerDurationSeconds;
    }

    public void setCallerNumber(String callerNumber) {
        this.mCallerNumber = callerNumber;
    }

    public void setCallerId(String callerId) {
        this.mCallerId = callerId;
    }

    public void setCallerNumberType(String callerNumberType) {
        this.mCallerNumberType = callerNumberType;
    }

    public void setCallerThumbnail(byte[] callerThumbnail) {
        this.mCallerThumbnail = callerThumbnail;
    }

    public PhoneStatusData(Parcel in) {
        this.mPhoneStatus = in.readInt();
        this.mCallerDurationSeconds = in.readInt();
        this.mCallerNumber = in.readString();
        this.mCallerId = in.readString();
        this.mCallerNumberType = in.readString();
        int len = in.readInt();
        if (len <= 0) {
            this.mCallerThumbnail = null;
            return;
        }
        byte[] bArr = new byte[len];
        this.mCallerThumbnail = bArr;
        in.readByteArray(bArr);
    }

    public String toString() {
        return this.mPhoneStatus + " " + this.mCallerDurationSeconds + " " + this.mCallerNumber + " " + this.mCallerId + " " + this.mCallerNumberType;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mPhoneStatus);
        parcel.writeInt(this.mCallerDurationSeconds);
        parcel.writeString(this.mCallerNumber);
        parcel.writeString(this.mCallerId);
        parcel.writeString(this.mCallerNumberType);
        byte[] bArr = this.mCallerThumbnail;
        if (bArr != null && bArr.length > 0) {
            parcel.writeInt(bArr.length);
            parcel.writeByteArray(this.mCallerThumbnail);
        } else {
            parcel.writeInt(0);
        }
    }
}
