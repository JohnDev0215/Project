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
        public Info[] newArray(int i) {
            return new Info[i];
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

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

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

    public String getEcuId() {
        return this.ecuId;
    }

    public void setEcuId(String str) {
        this.ecuId = str;
    }

    public String getEcuDiaId() {
        return this.ecuDiaId;
    }

    public void setEcuDiaId(String str) {
        this.ecuDiaId = str;
    }

    public String getEcuDId() {
        return this.ecuDId;
    }

    public void setEcuDId(String str) {
        this.ecuDId = str;
    }

    public String getEcuConfigDid() {
        return this.ecuConfigDid;
    }

    public void setEcuConfigDid(String str) {
        this.ecuConfigDid = str;
    }

    public String getEcuUpdateStyle() {
        return this.ecuUpdateStyle;
    }

    public void setEcuUpdateStyle(String str) {
        this.ecuUpdateStyle = str;
    }

    public String getEcuReprogramType() {
        return this.ecuReprogramType;
    }

    public void setEcuReprogramType(String str) {
        this.ecuReprogramType = str;
    }

    public String getEcuReprogramWay() {
        return this.ecuReprogramWay;
    }

    public void setEcuReprogramWay(String str) {
        this.ecuReprogramWay = str;
    }

    public String getEcuIp() {
        return this.ecuIp;
    }

    public void setEcuIp(String str) {
        this.ecuIp = str;
    }

    public String getEcuPort() {
        return this.ecuPort;
    }

    public void setEcuPort(String str) {
        this.ecuPort = str;
    }

    public String getEcuHardDid() {
        return this.ecuHardDid;
    }

    public void setEcuHardDid(String str) {
        this.ecuHardDid = str;
    }

    public String toString() {
        return "EcuInfo{ecuId=" + this.ecuId + ", ecuDiaId=" + this.ecuDiaId + ", ecuDId=" + this.ecuDId + ", ecuHardDid=" + this.ecuHardDid + ", ecuConfigDid=" + this.ecuConfigDid + ", ecuReprogramType=" + this.ecuUpdateStyle + ", ecuReprogramWay=" + this.ecuReprogramType + ", ecuUpdateStyle=" + this.ecuReprogramWay + ", ecuIp=" + this.ecuIp + ", ecuPort=" + this.ecuPort + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
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

    public Info() {
    }

    protected Info(Parcel parcel) {
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
