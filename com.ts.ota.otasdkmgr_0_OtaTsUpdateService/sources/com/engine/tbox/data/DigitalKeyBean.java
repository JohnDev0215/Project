package com.engine.tbox.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class DigitalKeyBean {
    private static final int DEFAULT_BYTE_FF = 255;
    private static final byte DEFAULT_PROP = -1;
    private static final int MAX_DAY = 31;
    private static final int MAX_KEY_BIND = 2;
    private static final int MAX_KEY_ENTITY = 3;
    private static final int MAX_KEY_TYPE = 10;
    private static final int MAX_MONTH = 12;
    private static final int MAX_PERMISSION = 4;
    private static final int MAX_PROP = 3;
    private static final int MAX_YEAR = 99;
    private static final int MIN_DAY = 1;
    private static final int MIN_KEY_BIND = 0;
    private static final int MIN_KEY_ENTITY = 1;
    private static final int MIN_KEY_TYPE = 0;
    private static final int MIN_MONTH = 1;
    private static final int MIN_PERMISSION = 1;
    private static final int MIN_PROP = 0;
    private static final int MIN_YEAR = 0;
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
    private String userPhone;
    private byte uwbTag;
    private byte userPro = -1;
    private byte keyType = 0;

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

    public byte[] getKeyIdArray() {
        return this.keyIdArray;
    }

    public void setKeyIdArray(byte[] keyId) {
        this.keyIdArray = keyId;
    }

    public byte getUwbTag() {
        return this.uwbTag;
    }

    public void setUwbTag(int uwbTag) {
        this.uwbTag = (byte) uwbTag;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DigitalKeyBean [userPro=");
        sb.append((int) this.userPro);
        sb.append(", userPhone=");
        sb.append(this.userPhone);
        sb.append(", keyType=");
        sb.append((int) this.keyType);
        sb.append(", keyNo=");
        sb.append(this.keyNo);
        sb.append(", keyNameLen=");
        sb.append((int) this.keyNameLen);
        sb.append(", keyNameStr=");
        sb.append(this.keyNameStr);
        sb.append(", deviceModelLen=");
        sb.append((int) this.deviceModelLen);
        sb.append(", deviceModelStr=");
        sb.append(this.deviceModelStr);
        sb.append(", keyState=");
        sb.append((int) this.keyState);
        sb.append(", expireTime=");
        sb.append(this.expireTime);
        sb.append(", keyPermission=");
        sb.append((int) this.keyPermission);
        sb.append(", keyLoadType=");
        sb.append((int) this.keyLoadType);
        sb.append(", accountTag=");
        sb.append((int) this.accountTag);
        sb.append(", keyId=");
        byte[] bArr = this.keyIdArray;
        sb.append(bArr == null ? " null" : Arrays.toString(bArr));
        sb.append(", uwbTag=");
        sb.append((int) this.uwbTag);
        sb.append("]");
        return sb.toString();
    }

    public static boolean isValidUserProp(int prop) {
        return (prop <= 3 && prop >= 0) || prop == 255;
    }

    public static boolean isValidKeyType(int type) {
        return type <= 10 && type >= 0;
    }

    public static boolean isValidAccountTag(int tag) {
        return tag == 0 || tag == 1;
    }

    public static boolean isValidDate(int year, int month, int day) {
        if (year < 0 || year > MAX_YEAR || month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }
        return true;
    }

    public static boolean isValidKeyPermission(int permission) {
        return permission <= 4 && permission >= 1;
    }

    public static boolean isValidKeyEntityType(int loadType) {
        return loadType <= 3 && loadType >= 1;
    }

    public static boolean isValidKeyBindType(int bindType) {
        return bindType <= 2 && bindType >= 0;
    }

    public static boolean isValidUwb(int uwb) {
        return uwb == 0 || uwb == 1;
    }

    public Map<String, Object> generateForOta() {
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
}
