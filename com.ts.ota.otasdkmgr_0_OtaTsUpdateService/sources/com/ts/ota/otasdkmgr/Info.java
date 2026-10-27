package com.ts.ota.otasdkmgr;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Info implements Parcelable {
    public static final Parcelable.Creator<Info> CREATOR = new Parcelable.Creator<Info>() { // from class: com.ts.ota.otasdkmgr.Info.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Info createFromParcel(Parcel source) {
            return new Info(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
    String ecuConfigDid;
    String ecuDId;
    String ecuDiaId;
    String ecuHardDid;
    String ecuId;
    String ecuIp;
    String ecuPort;
    String ecuReprogramType;
    String ecuReprogramWay;
    String ecuUpdateStyle;

    public Info(String ecuId, String ecuDiaId, String ecuDId, String ecuHardDid, String ecuConfigDid, String ecuReprogramType, String ecuReprogramWay, String ecuUpdateStyle, String ecuIp, String ecuPort) {
        this.ecuId = ecuId;
        this.ecuDiaId = ecuDiaId;
        this.ecuDId = ecuDId;
        this.ecuConfigDid = ecuConfigDid;
        this.ecuUpdateStyle = ecuUpdateStyle;
        this.ecuReprogramType = ecuReprogramType;
        this.ecuReprogramWay = ecuReprogramWay;
        this.ecuIp = ecuIp;
        this.ecuPort = ecuPort;
        this.ecuHardDid = ecuHardDid;
    }

    public String getEcuId() {
        return this.ecuId;
    }

    public void setEcuId(String ecuId) {
        this.ecuId = ecuId;
    }

    public String getEcuDiaId() {
        return this.ecuDiaId;
    }

    public void setEcuDiaId(String ecuDiaId) {
        this.ecuDiaId = ecuDiaId;
    }

    public String getEcuDId() {
        return this.ecuDId;
    }

    public void setEcuDId(String ecuDId) {
        this.ecuDId = ecuDId;
    }

    public String getEcuConfigDid() {
        return this.ecuConfigDid;
    }

    public void setEcuConfigDid(String ecuConfigDid) {
        this.ecuConfigDid = ecuConfigDid;
    }

    public String getEcuUpdateStyle() {
        return this.ecuUpdateStyle;
    }

    public void setEcuUpdateStyle(String ecuUpdateStyle) {
        this.ecuUpdateStyle = ecuUpdateStyle;
    }

    public String getEcuReprogramType() {
        return this.ecuReprogramType;
    }

    public void setEcuReprogramType(String ecuReprogramType) {
        this.ecuReprogramType = ecuReprogramType;
    }

    public String getEcuReprogramWay() {
        return this.ecuReprogramWay;
    }

    public void setEcuReprogramWay(String ecuReprogramWay) {
        this.ecuReprogramWay = ecuReprogramWay;
    }

    public String getEcuIp() {
        return this.ecuIp;
    }

    public void setEcuIp(String ecuIp) {
        this.ecuIp = ecuIp;
    }

    public String getEcuPort() {
        return this.ecuPort;
    }

    public void setEcuPort(String ecuPort) {
        this.ecuPort = ecuPort;
    }

    public String getEcuHardDid() {
        return this.ecuHardDid;
    }

    public void setEcuHardDid(String ecuHardDid) {
        this.ecuHardDid = ecuHardDid;
    }

    public String toString() {
        return "EcuInfo{ecuId=" + this.ecuId + ", ecuDiaId=" + this.ecuDiaId + ", ecuDId=" + this.ecuDId + ", ecuHardDid=" + this.ecuHardDid + ", ecuConfigDid=" + this.ecuConfigDid + ", ecuReprogramType=" + this.ecuUpdateStyle + ", ecuReprogramWay=" + this.ecuReprogramType + ", ecuUpdateStyle=" + this.ecuReprogramWay + ", ecuIp=" + this.ecuIp + ", ecuPort=" + this.ecuPort + '}';
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ecuId);
        dest.writeString(this.ecuDiaId);
        dest.writeString(this.ecuDId);
        dest.writeString(this.ecuHardDid);
        dest.writeString(this.ecuConfigDid);
        dest.writeString(this.ecuUpdateStyle);
        dest.writeString(this.ecuReprogramType);
        dest.writeString(this.ecuReprogramWay);
        dest.writeString(this.ecuIp);
        dest.writeString(this.ecuPort);
    }

    public void readFromParcel(Parcel source) {
        this.ecuId = source.readString();
        this.ecuDiaId = source.readString();
        this.ecuDId = source.readString();
        this.ecuHardDid = source.readString();
        this.ecuConfigDid = source.readString();
        this.ecuUpdateStyle = source.readString();
        this.ecuReprogramType = source.readString();
        this.ecuReprogramWay = source.readString();
        this.ecuIp = source.readString();
        this.ecuPort = source.readString();
    }

    public Info() {
    }

    protected Info(Parcel in) {
        this.ecuId = in.readString();
        this.ecuDiaId = in.readString();
        this.ecuDId = in.readString();
        this.ecuHardDid = in.readString();
        this.ecuConfigDid = in.readString();
        this.ecuUpdateStyle = in.readString();
        this.ecuReprogramType = in.readString();
        this.ecuReprogramWay = in.readString();
        this.ecuIp = in.readString();
        this.ecuPort = in.readString();
    }
}
