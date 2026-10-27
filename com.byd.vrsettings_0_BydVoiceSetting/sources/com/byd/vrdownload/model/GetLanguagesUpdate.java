package com.byd.vrdownload.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GetLanguagesUpdate implements IGetLanguagesUpdate, Parcelable {
    public static final Parcelable.Creator<GetLanguagesUpdate> CREATOR = new Parcelable.Creator<GetLanguagesUpdate>() { // from class: com.byd.vrdownload.model.GetLanguagesUpdate.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GetLanguagesUpdate createFromParcel(Parcel parcel) {
            return new GetLanguagesUpdate(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GetLanguagesUpdate[] newArray(int i) {
            return new GetLanguagesUpdate[i];
        }
    };
    String apkVersion;
    String countryCode;
    List<PkgVersion> pkgVersion;
    String reqId;
    String sign;
    String vehicleType;
    String vin;
    String xPlatform;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GetLanguagesUpdate() {
    }

    protected GetLanguagesUpdate(Parcel parcel) {
        this.apkVersion = parcel.readString();
        this.reqId = parcel.readString();
        this.vin = parcel.readString();
        this.sign = parcel.readString();
        this.xPlatform = parcel.readString();
        if (this.pkgVersion == null) {
            this.pkgVersion = new ArrayList();
        }
        parcel.readTypedList(this.pkgVersion, PkgVersion.CREATOR);
        this.countryCode = parcel.readString();
        this.vehicleType = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.apkVersion);
        parcel.writeString(this.reqId);
        parcel.writeString(this.vin);
        parcel.writeString(this.sign);
        parcel.writeString(this.xPlatform);
        parcel.writeTypedList(this.pkgVersion);
        parcel.writeString(this.countryCode);
        parcel.writeString(this.vehicleType);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.pkgVersion.size(); i++) {
            sb.append(this.pkgVersion.get(i));
        }
        return "GetLanguagesUpdate{apkVersion='" + this.apkVersion + "', pkgVersion=" + sb.toString() + ", reqId='" + this.reqId + "', vin='" + this.vin + "', sign='" + this.sign + "', xPlatform='" + this.xPlatform + "', countryCode='" + this.countryCode + "', vehicleType='" + this.vehicleType + "'}";
    }

    public void setPkgVersion(List<PkgVersion> list) {
        this.pkgVersion = list;
    }

    public void setReqId(String str) {
        this.reqId = str;
    }

    public void setVin(String str) {
        this.vin = str;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    @Override // com.byd.vrdownload.model.IGetLanguagesUpdate
    public String getxPlatform() {
        return this.xPlatform;
    }

    public void setxPlatform(String str) {
        this.xPlatform = str;
    }

    public void setApkVersion(String str) {
        this.apkVersion = str;
    }

    @Override // com.byd.vrdownload.model.IGetLanguagesUpdate
    public String getApkVersion() {
        return this.apkVersion;
    }

    @Override // com.byd.vrdownload.model.IGetLanguagesUpdate
    public List<PkgVersion> getPkgVersion() {
        return this.pkgVersion;
    }

    @Override // com.byd.vrdownload.model.IGetLanguagesUpdate
    public String getReqId() {
        return this.reqId;
    }

    @Override // com.byd.vrdownload.model.IGetLanguagesUpdate
    public String getVin() {
        return this.vin;
    }

    @Override // com.byd.vrdownload.model.IGetLanguagesUpdate
    public String getSign() {
        return this.sign;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(String str) {
        this.vehicleType = str;
    }
}
