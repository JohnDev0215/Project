package com.ts.avm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class AvmCallInfoBean implements Parcelable {
    public static final Parcelable.Creator<AvmCallInfoBean> CREATOR = new Parcelable.Creator<AvmCallInfoBean>() { // from class: com.ts.avm.bean.AvmCallInfoBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AvmCallInfoBean createFromParcel(Parcel in) {
            return new AvmCallInfoBean(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AvmCallInfoBean[] newArray(int size) {
            return new AvmCallInfoBean[size];
        }
    };
    private static final int NUM_DEFAULT = -1;
    private static final int PHONE_NUM_MIN_LENGTH = 4;
    private String address;
    private int audioSource;
    private int callState;
    private int callTime;
    private int callType;
    private String contactName;
    private boolean isMute;
    private String phoneNum;

    protected AvmCallInfoBean(Parcel in) {
        this.callTime = -1;
        this.callType = -1;
        this.callState = -1;
        this.audioSource = -1;
        this.phoneNum = in.readString();
        this.contactName = in.readString();
        this.address = in.readString();
        this.callTime = in.readInt();
        this.callType = in.readInt();
        this.callState = in.readInt();
        this.isMute = in.readByte() != 0;
        this.audioSource = in.readInt();
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
        parcel.writeInt(this.audioSource);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCallTime() {
        return this.callTime;
    }

    public void setCallTime(int callTime) {
        this.callTime = callTime;
    }

    public int getCallType() {
        return this.callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }

    public int getCallState() {
        return this.callState;
    }

    public void setCallState(int callState) {
        this.callState = callState;
    }

    public boolean isMute() {
        return this.isMute;
    }

    public void setMute(boolean mute) {
        this.isMute = mute;
    }

    public int getAudioSource() {
        return this.audioSource;
    }

    public void setAudioSource(int audioSource) {
        this.audioSource = audioSource;
    }

    public AvmCallInfoBean() {
        this.callTime = -1;
        this.callType = -1;
        this.callState = -1;
        this.audioSource = -1;
    }

    public String toString() {
        String phoneNumStr;
        if (this.phoneNum.length() > 4) {
            String str = this.phoneNum;
            phoneNumStr = str.substring(str.length() - 4, this.phoneNum.length());
        } else {
            phoneNumStr = this.phoneNum;
        }
        return "AvmCallInfoBean{phoneNum='" + phoneNumStr + "', callTime=" + this.callTime + ", callType=" + this.callType + ", callState=" + this.callState + ", isMute=" + this.isMute + ", audioSource=" + this.audioSource + '}';
    }
}
