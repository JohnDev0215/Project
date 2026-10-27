package com.ts.avm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class AvmCallInfoBean implements Parcelable {
    public static final Parcelable.Creator<AvmCallInfoBean> CREATOR = new Parcelable.Creator<AvmCallInfoBean>() { // from class: com.ts.avm.bean.AvmCallInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AvmCallInfoBean createFromParcel(Parcel parcel) {
            return new AvmCallInfoBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AvmCallInfoBean[] newArray(int i) {
            return new AvmCallInfoBean[i];
        }
    };
    private static final int PHONE_NUM_MIN_LENGTH = 4;
    private String address;
    private int callState;
    private int callTime;
    private int callType;
    private String contactName;
    private boolean isMute;
    private String phoneNum;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected AvmCallInfoBean(Parcel parcel) {
        this.phoneNum = parcel.readString();
        this.contactName = parcel.readString();
        this.address = parcel.readString();
        this.callTime = parcel.readInt();
        this.callType = parcel.readInt();
        this.callState = parcel.readInt();
        this.isMute = parcel.readByte() != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.phoneNum);
        parcel.writeString(this.contactName);
        parcel.writeString(this.address);
        parcel.writeInt(this.callTime);
        parcel.writeInt(this.callType);
        parcel.writeInt(this.callState);
        parcel.writeByte(this.isMute ? (byte) 1 : (byte) 0);
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(String str) {
        this.phoneNum = str;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String str) {
        this.contactName = str;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public int getCallTime() {
        return this.callTime;
    }

    public void setCallTime(int i) {
        this.callTime = i;
    }

    public int getCallType() {
        return this.callType;
    }

    public void setCallType(int i) {
        this.callType = i;
    }

    public int getCallState() {
        return this.callState;
    }

    public void setCallState(int i) {
        this.callState = i;
    }

    public boolean isMute() {
        return this.isMute;
    }

    public void setMute(boolean z) {
        this.isMute = z;
    }

    public AvmCallInfoBean() {
    }

    public String toString() {
        String strSubstring;
        if (this.phoneNum.length() > 4) {
            String str = this.phoneNum;
            strSubstring = str.substring(str.length() - 4, this.phoneNum.length());
        } else {
            strSubstring = this.phoneNum;
        }
        return "AvmCallInfoBean{phoneNum='" + strSubstring + "', callTime=" + this.callTime + ", callType=" + this.callType + ", callState=" + this.callState + ", isMute=" + this.isMute + '}';
    }
}
