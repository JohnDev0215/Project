package com.ts.ota.otasdkmgr;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class EcuInfo implements Parcelable {
    public static final Parcelable.Creator<EcuInfo> CREATOR = new Parcelable.Creator<EcuInfo>() { // from class: com.ts.ota.otasdkmgr.EcuInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EcuInfo createFromParcel(Parcel source) {
            return new EcuInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EcuInfo[] newArray(int size) {
            return new EcuInfo[size];
        }
    };
    String ecuConfigCode;
    String ecuConfigDid;
    String ecuConfigVer;
    String ecuDId;
    String ecuDiaId;
    String ecuHardDid;
    String ecuHardVer;
    String ecuId;
    String ecuPath;
    String ecuReprogramType;
    String ecuReprogramWay;
    String ecuSafetyAccessKey;
    String ecuSoftCode;
    String ecuSoftVer;
    String ecuUpdateStyle;

    public EcuInfo(String ecuId, String ecuSoftCode, String ecuSoftVer, String ecuDiaId, String ecuDId, String ecuHardDid, String ecuHardVer, String ecuConfigDid, String ecuUpdateStyle, String ecuReprogramType, String ecuReprogramWay, String ecuSafetyAccessKey, String ecuPath, String ecuConfigVer, String ecuConfigCode) {
        this.ecuId = ecuId;
        this.ecuSoftCode = ecuSoftCode;
        this.ecuSoftVer = ecuSoftVer;
        this.ecuDiaId = ecuDiaId;
        this.ecuDId = ecuDId;
        this.ecuConfigDid = ecuConfigDid;
        this.ecuUpdateStyle = ecuUpdateStyle;
        this.ecuReprogramType = ecuReprogramType;
        this.ecuReprogramWay = ecuReprogramWay;
        this.ecuSafetyAccessKey = ecuSafetyAccessKey;
        this.ecuPath = ecuPath;
        this.ecuHardDid = ecuHardDid;
        this.ecuHardVer = ecuHardVer;
        this.ecuConfigVer = ecuConfigVer;
        this.ecuConfigCode = ecuConfigCode;
    }

    public String getEcuId() {
        return this.ecuId;
    }

    public void setEcuId(String ecuId) {
        this.ecuId = ecuId;
    }

    public String getEcuSoftCode() {
        return this.ecuSoftCode;
    }

    public void setEcuSoftCode(String ecuSoftCode) {
        this.ecuSoftCode = ecuSoftCode;
    }

    public String getEcuSoftVer() {
        return this.ecuSoftVer;
    }

    public void setEcuSoftVer(String ecuSoftVer) {
        this.ecuSoftVer = ecuSoftVer;
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

    public String getEcuSafetyAccessKey() {
        return this.ecuSafetyAccessKey;
    }

    public void setEcuSafetyAccessKey(String ecuSafetyAccessKey) {
        this.ecuSafetyAccessKey = ecuSafetyAccessKey;
    }

    public String getEcuPath() {
        return this.ecuPath;
    }

    public void setEcuPath(String ecuPath) {
        this.ecuPath = ecuPath;
    }

    public String getEcuHardDid() {
        return this.ecuHardDid;
    }

    public void setEcuHardDid(String ecuHardDid) {
        this.ecuHardDid = ecuHardDid;
    }

    public String getEcuHardVer() {
        return this.ecuHardVer;
    }

    public void setEcuHardVer(String ecuHardVer) {
        this.ecuHardVer = ecuHardVer;
    }

    public String getEcuConfigVer() {
        return this.ecuConfigVer;
    }

    public void setEcuConfigVer(String ecuConfigVer) {
        this.ecuConfigVer = ecuConfigVer;
    }

    public String getEcuConfigCode() {
        return this.ecuConfigCode;
    }

    public void setEcuConfigCode(String ecuConfigCode) {
        this.ecuConfigCode = ecuConfigCode;
    }

    public String toString() {
        return "EcuInfo{ecuId=" + this.ecuId + ", ecuSoftCode=" + this.ecuSoftCode + ", ecuSoftVer=" + this.ecuSoftVer + ", ecuDiaId=" + this.ecuDiaId + ", ecuDId=" + this.ecuDId + ", ecuHardDid=" + this.ecuHardDid + ", ecuHardVer=" + this.ecuHardVer + ", ecuConfigDid=" + this.ecuConfigDid + ", ecuUpdateStyle=" + this.ecuUpdateStyle + ", ecuReprogramType=" + this.ecuReprogramType + ", ecuReprogramWay=" + this.ecuReprogramWay + ", ecuSafetyAccessKey=" + this.ecuSafetyAccessKey + ", ecuConfigCode=" + this.ecuConfigCode + ", ecuConfigVer=" + this.ecuConfigVer + ", ecuPath=" + this.ecuPath + '}';
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ecuId);
        dest.writeString(this.ecuSoftCode);
        dest.writeString(this.ecuSoftVer);
        dest.writeString(this.ecuDiaId);
        dest.writeString(this.ecuDId);
        dest.writeString(this.ecuHardDid);
        dest.writeString(this.ecuHardVer);
        dest.writeString(this.ecuConfigDid);
        dest.writeString(this.ecuUpdateStyle);
        dest.writeString(this.ecuReprogramType);
        dest.writeString(this.ecuReprogramWay);
        dest.writeString(this.ecuSafetyAccessKey);
        dest.writeString(this.ecuPath);
        dest.writeString(this.ecuConfigVer);
        dest.writeString(this.ecuConfigCode);
    }

    public void readFromParcel(Parcel source) {
        this.ecuId = source.readString();
        this.ecuSoftCode = source.readString();
        this.ecuSoftVer = source.readString();
        this.ecuDiaId = source.readString();
        this.ecuDId = source.readString();
        this.ecuHardDid = source.readString();
        this.ecuHardVer = source.readString();
        this.ecuConfigDid = source.readString();
        this.ecuUpdateStyle = source.readString();
        this.ecuReprogramType = source.readString();
        this.ecuReprogramWay = source.readString();
        this.ecuSafetyAccessKey = source.readString();
        this.ecuPath = source.readString();
        this.ecuConfigVer = source.readString();
        this.ecuConfigCode = source.readString();
    }

    public EcuInfo() {
    }

    protected EcuInfo(Parcel in) {
        this.ecuId = in.readString();
        this.ecuSoftCode = in.readString();
        this.ecuSoftVer = in.readString();
        this.ecuDiaId = in.readString();
        this.ecuDId = in.readString();
        this.ecuHardDid = in.readString();
        this.ecuHardVer = in.readString();
        this.ecuConfigDid = in.readString();
        this.ecuUpdateStyle = in.readString();
        this.ecuReprogramType = in.readString();
        this.ecuReprogramWay = in.readString();
        this.ecuSafetyAccessKey = in.readString();
        this.ecuPath = in.readString();
        this.ecuConfigVer = in.readString();
        this.ecuConfigCode = in.readString();
    }
}
