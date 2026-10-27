package android.hardware.bydauto.ota;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class EcuInfo implements Parcelable {
    public static final Parcelable.Creator<EcuInfo> CREATOR = new Parcelable.Creator<EcuInfo>() { // from class: android.hardware.bydauto.ota.EcuInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EcuInfo createFromParcel(Parcel parcel) {
            return new EcuInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public EcuInfo[] newArray(int i) {
            return new EcuInfo[i];
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

    public EcuInfo() {
    }

    protected EcuInfo(Parcel parcel) {
        this.ecuId = parcel.readString();
        this.ecuSoftCode = parcel.readString();
        this.ecuSoftVer = parcel.readString();
        this.ecuDiaId = parcel.readString();
        this.ecuDId = parcel.readString();
        this.ecuHardDid = parcel.readString();
        this.ecuHardVer = parcel.readString();
        this.ecuConfigDid = parcel.readString();
        this.ecuUpdateStyle = parcel.readString();
        this.ecuReprogramType = parcel.readString();
        this.ecuReprogramWay = parcel.readString();
        this.ecuSafetyAccessKey = parcel.readString();
        this.ecuPath = parcel.readString();
        this.ecuConfigVer = parcel.readString();
        this.ecuConfigCode = parcel.readString();
    }

    public EcuInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15) {
        this.ecuId = str;
        this.ecuSoftCode = str2;
        this.ecuSoftVer = str3;
        this.ecuDiaId = str4;
        this.ecuDId = str5;
        this.ecuConfigDid = str8;
        this.ecuUpdateStyle = str9;
        this.ecuReprogramType = str10;
        this.ecuReprogramWay = str11;
        this.ecuSafetyAccessKey = str12;
        this.ecuPath = str13;
        this.ecuHardDid = str6;
        this.ecuHardVer = str7;
        this.ecuConfigVer = str14;
        this.ecuConfigCode = str15;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getEcuConfigCode() {
        return this.ecuConfigCode;
    }

    public String getEcuConfigDid() {
        return this.ecuConfigDid;
    }

    public String getEcuConfigVer() {
        return this.ecuConfigVer;
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

    public String getEcuHardVer() {
        return this.ecuHardVer;
    }

    public String getEcuId() {
        return this.ecuId;
    }

    public String getEcuPath() {
        return this.ecuPath;
    }

    public String getEcuReprogramType() {
        return this.ecuReprogramType;
    }

    public String getEcuReprogramWay() {
        return this.ecuReprogramWay;
    }

    public String getEcuSafetyAccessKey() {
        return this.ecuSafetyAccessKey;
    }

    public String getEcuSoftCode() {
        return this.ecuSoftCode;
    }

    public String getEcuSoftVer() {
        return this.ecuSoftVer;
    }

    public String getEcuUpdateStyle() {
        return this.ecuUpdateStyle;
    }

    public void readFromParcel(Parcel parcel) {
        this.ecuId = parcel.readString();
        this.ecuSoftCode = parcel.readString();
        this.ecuSoftVer = parcel.readString();
        this.ecuDiaId = parcel.readString();
        this.ecuDId = parcel.readString();
        this.ecuHardDid = parcel.readString();
        this.ecuHardVer = parcel.readString();
        this.ecuConfigDid = parcel.readString();
        this.ecuUpdateStyle = parcel.readString();
        this.ecuReprogramType = parcel.readString();
        this.ecuReprogramWay = parcel.readString();
        this.ecuSafetyAccessKey = parcel.readString();
        this.ecuPath = parcel.readString();
        this.ecuConfigVer = parcel.readString();
        this.ecuConfigCode = parcel.readString();
    }

    public void setEcuConfigCode(String str) {
        this.ecuConfigCode = str;
    }

    public void setEcuConfigDid(String str) {
        this.ecuConfigDid = str;
    }

    public void setEcuConfigVer(String str) {
        this.ecuConfigVer = str;
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

    public void setEcuHardVer(String str) {
        this.ecuHardVer = str;
    }

    public void setEcuId(String str) {
        this.ecuId = str;
    }

    public void setEcuPath(String str) {
        this.ecuPath = str;
    }

    public void setEcuReprogramType(String str) {
        this.ecuReprogramType = str;
    }

    public void setEcuReprogramWay(String str) {
        this.ecuReprogramWay = str;
    }

    public void setEcuSafetyAccessKey(String str) {
        this.ecuSafetyAccessKey = str;
    }

    public void setEcuSoftCode(String str) {
        this.ecuSoftCode = str;
    }

    public void setEcuSoftVer(String str) {
        this.ecuSoftVer = str;
    }

    public void setEcuUpdateStyle(String str) {
        this.ecuUpdateStyle = str;
    }

    public String toString() {
        return "EcuInfo{ecuId=" + this.ecuId + ", ecuSoftCode=" + this.ecuSoftCode + ", ecuSoftVer=" + this.ecuSoftVer + ", ecuConfigCode=" + this.ecuDiaId + ", ecuConfigVer=" + this.ecuDId + ", ecuHardDid=" + this.ecuHardDid + ", ecuHardVer=" + this.ecuHardVer + ", ecuConfigDid=" + this.ecuConfigDid + ", ecuUpdateStyle=" + this.ecuUpdateStyle + ", ecuReprogramType=" + this.ecuReprogramType + ", ecuReprogramWay=" + this.ecuReprogramWay + ", ecuSafetyAccessKey=" + this.ecuSafetyAccessKey + ", ecuPath=" + this.ecuPath + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.ecuId);
        parcel.writeString(this.ecuSoftCode);
        parcel.writeString(this.ecuSoftVer);
        parcel.writeString(this.ecuDiaId);
        parcel.writeString(this.ecuDId);
        parcel.writeString(this.ecuHardDid);
        parcel.writeString(this.ecuHardVer);
        parcel.writeString(this.ecuConfigDid);
        parcel.writeString(this.ecuUpdateStyle);
        parcel.writeString(this.ecuReprogramType);
        parcel.writeString(this.ecuReprogramWay);
        parcel.writeString(this.ecuSafetyAccessKey);
        parcel.writeString(this.ecuPath);
        parcel.writeString(this.ecuConfigVer);
        parcel.writeString(this.ecuConfigCode);
    }
}
