package android.hardware.bydauto.ota;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Info implements Parcelable {
    public static final Parcelable.Creator<Info> CREATOR = new Parcelable.Creator<Info>() { // from class: android.hardware.bydauto.ota.Info.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Info createFromParcel(Parcel parcel) {
            return new Info(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Info[] newArray(int i2) {
            return new Info[i2];
        }
    };
    public String ecuConfigDid;
    public String ecuDId;
    public String ecuDiaId;
    public String ecuHardDid;
    public String ecuId;
    public String ecuIp;
    public String ecuPort;
    public String ecuReprogramType;
    public String ecuReprogramWay;
    public String ecuUpdateStyle;

    public Info(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        this.ecuId = str;
        this.ecuDiaId = str2;
        this.ecuDId = str3;
        this.ecuConfigDid = str5;
        this.ecuUpdateStyle = str8;
        this.ecuReprogramType = str6;
        this.ecuReprogramWay = str7;
        this.ecuIp = str9;
        this.ecuPort = str10;
        this.ecuHardDid = str4;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getEcuConfigDid() {
        return this.ecuConfigDid;
    }

    public String getEcuDId() {
        return this.ecuDId;
    }

    public String getEcuDiaId() {
        return this.ecuDiaId;
    }

    public String getEcuHardDid() {
        return this.ecuHardDid;
    }

    public String getEcuId() {
        return this.ecuId;
    }

    public String getEcuIp() {
        return this.ecuIp;
    }

    public String getEcuPort() {
        return this.ecuPort;
    }

    public String getEcuReprogramType() {
        return this.ecuReprogramType;
    }

    public String getEcuReprogramWay() {
        return this.ecuReprogramWay;
    }

    public String getEcuUpdateStyle() {
        return this.ecuUpdateStyle;
    }

    public void readFromParcel(Parcel parcel) {
        this.ecuId = parcel.readString();
        this.ecuDiaId = parcel.readString();
        this.ecuDId = parcel.readString();
        this.ecuHardDid = parcel.readString();
        this.ecuConfigDid = parcel.readString();
        this.ecuUpdateStyle = parcel.readString();
        this.ecuReprogramType = parcel.readString();
        this.ecuReprogramWay = parcel.readString();
        this.ecuIp = parcel.readString();
        this.ecuPort = parcel.readString();
    }

    public void setEcuConfigDid(String str) {
        this.ecuConfigDid = str;
    }

    public void setEcuDId(String str) {
        this.ecuDId = str;
    }

    public void setEcuDiaId(String str) {
        this.ecuDiaId = str;
    }

    public void setEcuHardDid(String str) {
        this.ecuHardDid = str;
    }

    public void setEcuId(String str) {
        this.ecuId = str;
    }

    public void setEcuIp(String str) {
        this.ecuIp = str;
    }

    public void setEcuPort(String str) {
        this.ecuPort = str;
    }

    public void setEcuReprogramType(String str) {
        this.ecuReprogramType = str;
    }

    public void setEcuReprogramWay(String str) {
        this.ecuReprogramWay = str;
    }

    public void setEcuUpdateStyle(String str) {
        this.ecuUpdateStyle = str;
    }

    public String toString() {
        return "EcuInfo{ecuId=" + this.ecuId + ", ecuDiaId=" + this.ecuDiaId + ", ecuDId=" + this.ecuDId + ", ecuHardDid=" + this.ecuHardDid + ", ecuConfigDid=" + this.ecuConfigDid + ", ecuReprogramType=" + this.ecuUpdateStyle + ", ecuReprogramWay=" + this.ecuReprogramType + ", ecuUpdateStyle=" + this.ecuReprogramWay + ", ecuIp=" + this.ecuIp + ", ecuPort=" + this.ecuPort + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.ecuId);
        parcel.writeString(this.ecuDiaId);
        parcel.writeString(this.ecuDId);
        parcel.writeString(this.ecuHardDid);
        parcel.writeString(this.ecuConfigDid);
        parcel.writeString(this.ecuUpdateStyle);
        parcel.writeString(this.ecuReprogramType);
        parcel.writeString(this.ecuReprogramWay);
        parcel.writeString(this.ecuIp);
        parcel.writeString(this.ecuPort);
    }

    public Info() {
    }

    public Info(Parcel parcel) {
        this.ecuId = parcel.readString();
        this.ecuDiaId = parcel.readString();
        this.ecuDId = parcel.readString();
        this.ecuHardDid = parcel.readString();
        this.ecuConfigDid = parcel.readString();
        this.ecuUpdateStyle = parcel.readString();
        this.ecuReprogramType = parcel.readString();
        this.ecuReprogramWay = parcel.readString();
        this.ecuIp = parcel.readString();
        this.ecuPort = parcel.readString();
    }
}
