package com.byd.car.iot.converter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

/* JADX INFO: loaded from: classes.dex */
public class AutoiotSetting implements Parcelable {
    public static final Parcelable.Creator<AutoiotSetting> CREATOR = new Parcelable.Creator<AutoiotSetting>() { // from class: com.byd.car.iot.converter.AutoiotSetting.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutoiotSetting createFromParcel(Parcel parcel) {
            return new AutoiotSetting(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AutoiotSetting[] newArray(int i) {
            return new AutoiotSetting[i];
        }
    };
    private String attr;
    private String deviceId;
    private String deviceIdMac;
    private int length;
    private int priority;
    private long timestamp;
    private byte[] values;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    public AutoiotSetting() {
    }

    public AutoiotSetting(Parcel parcel) {
        int i = parcel.readInt();
        this.length = i;
        byte[] bArr = new byte[i];
        this.values = bArr;
        parcel.readByteArray(bArr);
    }

    public void setAutoiotValues(byte[] bArr) {
        this.length = bArr.length;
        this.values = bArr;
    }

    public void setAutoiotDeviceId(String str) {
        this.deviceId = str;
    }

    public byte[] getAutoiotValues() {
        return this.values;
    }

    public String getAutoiotDeviceId() {
        return this.deviceId;
    }

    public String getAttr() {
        return this.attr;
    }

    public void setAttr(String str) {
        this.attr = str;
    }

    public void setAttr(String str, String str2) {
        this.attr = AutoiotSettingConstants.assembleAttr(str, str2);
    }

    public Pair<String, String> parserAttr() {
        return AutoiotSettingConstants.parserAttr(this.attr);
    }

    public DeviceTypeEnum deviceType() {
        return AutoiotSettingConstants.deviceType(this.deviceId);
    }
}
