package com.ts.ota.otasdkmgr;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class DigitalKeyBeanSdk implements Parcelable {
    public static final Parcelable.Creator<DigitalKeyBeanSdk> CREATOR = new Parcelable.Creator<DigitalKeyBeanSdk>() { // from class: com.ts.ota.otasdkmgr.DigitalKeyBeanSdk.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DigitalKeyBeanSdk createFromParcel(Parcel in) {
            return new DigitalKeyBeanSdk(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DigitalKeyBeanSdk[] newArray(int size) {
            return new DigitalKeyBeanSdk[size];
        }
    };
    private byte accountTag;
    private byte deviceModelLen;
    private String deviceModelStr;
    private String expireTime;
    private String keyId;
    private byte[] keyIdArray;
    private byte keyLoadType;
    private byte keyNameLen;
    private String keyNameStr;
    private String keyNo;
    private byte keyPermission;
    private byte keyState;
    private byte keyType;
    private String userPhone;
    private byte userPro;
    private byte uwbTag;

    public DigitalKeyBeanSdk() {
        this.userPro = (byte) -1;
        this.keyType = (byte) 0;
    }

    protected DigitalKeyBeanSdk(Parcel in) {
        this.userPro = (byte) -1;
        this.keyType = (byte) 0;
        this.userPro = in.readByte();
        this.userPhone = in.readString();
        this.keyType = in.readByte();
        this.keyNo = in.readString();
        this.keyNameLen = in.readByte();
        this.keyNameStr = in.readString();
        this.deviceModelLen = in.readByte();
        this.deviceModelStr = in.readString();
        this.keyState = in.readByte();
        this.expireTime = in.readString();
        this.keyPermission = in.readByte();
        this.keyLoadType = in.readByte();
        this.accountTag = in.readByte();
        this.keyId = in.readString();
        this.uwbTag = in.readByte();
        this.keyIdArray = in.createByteArray();
    }

    public Map<String, Object> generateForApi() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("userPro", Byte.valueOf(this.userPro));
        dataMap.put("userPhone", this.userPhone);
        dataMap.put("keyType", Byte.valueOf(this.keyType));
        dataMap.put("keyNo", this.keyNo);
        dataMap.put("keyNameLen", Byte.valueOf(this.keyNameLen));
        dataMap.put("keyNameStr", this.keyNameStr);
        dataMap.put("deviceModelLen", Byte.valueOf(this.deviceModelLen));
        dataMap.put("deviceModelStr", this.deviceModelStr);
        dataMap.put("keyState", Byte.valueOf(this.keyState));
        dataMap.put("expireTime", this.expireTime);
        dataMap.put("keyPermission", Byte.valueOf(this.keyPermission));
        dataMap.put("keyLoadType", Byte.valueOf(this.keyLoadType));
        dataMap.put("accountTag", Byte.valueOf(this.accountTag));
        dataMap.put("keyId", this.keyId);
        dataMap.put("uwbTag", Byte.valueOf(this.uwbTag));
        dataMap.put("keyIdArray", this.keyIdArray);
        return dataMap;
    }

    public void init(Map<String, Object> dataMap) {
        Object value;
        Set<String> keys = dataMap.keySet();
        for (String key : keys) {
            value = dataMap.get(key);
            switch (key) {
                case "userPro":
                    this.userPro = ((Byte) value).byteValue();
                    break;
                case "userPhone":
                    this.userPhone = (String) value;
                    break;
                case "keyType":
                    this.keyType = ((Byte) value).byteValue();
                    break;
                case "keyNo":
                    this.keyNo = (String) value;
                    break;
                case "keyNameLen":
                    this.keyNameLen = ((Byte) value).byteValue();
                    break;
                case "keyNameStr":
                    this.keyNameStr = (String) value;
                    break;
                case "deviceModelLen":
                    this.deviceModelLen = ((Byte) value).byteValue();
                    break;
                case "deviceModelStr":
                    this.deviceModelStr = (String) value;
                    break;
                case "keyState":
                    this.keyState = ((Byte) value).byteValue();
                    break;
                case "expireTime":
                    this.expireTime = (String) value;
                    break;
                case "keyPermission":
                    this.keyPermission = ((Byte) value).byteValue();
                    break;
                case "keyLoadType":
                    this.keyLoadType = ((Byte) value).byteValue();
                    break;
                case "accountTag":
                    this.accountTag = ((Byte) value).byteValue();
                    break;
                case "keyId":
                    this.keyId = (String) value;
                    break;
                case "uwbTag":
                    this.uwbTag = ((Byte) value).byteValue();
                    break;
                case "keyIdArray":
                    this.keyIdArray = (byte[]) value;
                    break;
            }
        }
    }

    public byte getUserPro() {
        return this.userPro;
    }

    public void setUserPro(int userPro) {
        this.userPro = (byte) userPro;
    }

    public String getUserPhone() {
        return this.userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public byte getKeyType() {
        return this.keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = (byte) keyType;
    }

    public String getKeyNo() {
        return this.keyNo;
    }

    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
    }

    public byte getKeyNameLen() {
        return this.keyNameLen;
    }

    public void setKeyNameLen(int keyNameLen) {
        this.keyNameLen = (byte) keyNameLen;
    }

    public String getKeyNameStr() {
        return this.keyNameStr;
    }

    public void setKeyNameStr(String keyNameStr) {
        this.keyNameStr = keyNameStr;
    }

    public byte getDeviceModelLen() {
        return this.deviceModelLen;
    }

    public void setDeviceModelLen(int deviceModelLen) {
        this.deviceModelLen = (byte) deviceModelLen;
    }

    public String getDeviceModelStr() {
        return this.deviceModelStr;
    }

    public void setDeviceModelStr(String deviceModelStr) {
        this.deviceModelStr = deviceModelStr;
    }

    public byte getKeyState() {
        return this.keyState;
    }

    public void setKeyState(int keyState) {
        this.keyState = (byte) keyState;
    }

    public String getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public byte getKeyPermission() {
        return this.keyPermission;
    }

    public void setKeyPermission(int keyPermission) {
        this.keyPermission = (byte) keyPermission;
    }

    public byte getKeyLoadType() {
        return this.keyLoadType;
    }

    public void setKeyLoadType(int keyLoadType) {
        this.keyLoadType = (byte) keyLoadType;
    }

    public byte getAccountTag() {
        return this.accountTag;
    }

    public void setAccountTag(int accountTag) {
        this.accountTag = (byte) accountTag;
    }

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public byte getUwbTag() {
        return this.uwbTag;
    }

    public void setUwbTag(int uwbTag) {
        this.uwbTag = (byte) uwbTag;
    }

    public String toString() {
        return "DigitalKeyBean [userPro=" + ((int) this.userPro) + ", userPhone=" + this.userPhone + ", keyType=" + ((int) this.keyType) + ", keyNo=" + this.keyNo + ", keyNameLen=" + ((int) this.keyNameLen) + ", keyNameStr=" + this.keyNameStr + ", deviceModelLen=" + ((int) this.deviceModelLen) + ", deviceModelStr=" + this.deviceModelStr + ", keyState=" + ((int) this.keyState) + ", expireTime=" + this.expireTime + ", keyPermission=" + ((int) this.keyPermission) + ", keyLoadType=" + ((int) this.keyLoadType) + ", accountTag=" + ((int) this.accountTag) + ", keyId=" + this.keyId + ", uwbTag=" + ((int) this.uwbTag) + ", keyIdArray=" + Arrays.toString(this.keyIdArray) + "]";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.userPro);
        parcel.writeString(this.userPhone);
        parcel.writeByte(this.keyType);
        parcel.writeString(this.keyNo);
        parcel.writeByte(this.keyNameLen);
        parcel.writeString(this.keyNameStr);
        parcel.writeByte(this.deviceModelLen);
        parcel.writeString(this.deviceModelStr);
        parcel.writeByte(this.keyState);
        parcel.writeString(this.expireTime);
        parcel.writeByte(this.keyPermission);
        parcel.writeByte(this.keyLoadType);
        parcel.writeByte(this.accountTag);
        parcel.writeString(this.keyId);
        parcel.writeByte(this.uwbTag);
        parcel.writeByteArray(this.keyIdArray);
    }
}
