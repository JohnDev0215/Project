package android.hardware.bydauto.digital.tsimpl;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class DigitalKeyBean {
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

    public DigitalKeyBean() {
    }

    public byte getAccountTag() {
        return this.accountTag;
    }

    public byte getDeviceModelLen() {
        return this.deviceModelLen;
    }

    public String getDeviceModelStr() {
        return this.deviceModelStr;
    }

    public String getExpireTime() {
        return this.expireTime;
    }

    public String getKeyId() {
        return this.keyId;
    }

    public byte[] getKeyIdArray() {
        return this.keyIdArray;
    }

    public byte getKeyLoadType() {
        return this.keyLoadType;
    }

    public byte getKeyNameLen() {
        return this.keyNameLen;
    }

    public String getKeyNameStr() {
        return this.keyNameStr;
    }

    public String getKeyNo() {
        return this.keyNo;
    }

    public byte getKeyPermission() {
        return this.keyPermission;
    }

    public byte getKeyState() {
        return this.keyState;
    }

    public byte getKeyType() {
        return this.keyType;
    }

    public String getUserPhone() {
        return this.userPhone;
    }

    public byte getUserPro() {
        return this.userPro;
    }

    public byte getUwbTag() {
        return this.uwbTag;
    }

    public void init(Map<String, Object> map) {
        Object obj;
        for (String str : map.keySet()) {
            obj = map.get(str);
            str.getClass();
            switch (str) {
                case "deviceModelLen":
                    this.deviceModelLen = ((Byte) obj).byteValue();
                    break;
                case "deviceModelStr":
                    this.deviceModelStr = (String) obj;
                    break;
                case "expireTime":
                    this.expireTime = (String) obj;
                    break;
                case "uwbTag":
                    this.uwbTag = ((Byte) obj).byteValue();
                    break;
                case "keyType":
                    this.keyType = ((Byte) obj).byteValue();
                    break;
                case "accountTag":
                    this.accountTag = ((Byte) obj).byteValue();
                    break;
                case "userPro":
                    this.userPro = ((Byte) obj).byteValue();
                    break;
                case "keyId":
                    this.keyId = (String) obj;
                    break;
                case "keyNo":
                    this.keyNo = (String) obj;
                    break;
                case "userPhone":
                    this.userPhone = (String) obj;
                    break;
                case "keyState":
                    this.keyState = ((Byte) obj).byteValue();
                    break;
                case "keyLoadType":
                    this.keyLoadType = ((Byte) obj).byteValue();
                    break;
                case "keyPermission":
                    this.keyPermission = ((Byte) obj).byteValue();
                    break;
                case "keyIdArray":
                    this.keyIdArray = (byte[]) obj;
                    break;
                case "keyNameLen":
                    this.keyNameLen = ((Byte) obj).byteValue();
                    break;
                case "keyNameStr":
                    this.keyNameStr = (String) obj;
                    break;
            }
        }
    }

    public void setAccountTag(byte b) {
        this.accountTag = b;
    }

    public void setDeviceModelLen(byte b) {
        this.deviceModelLen = b;
    }

    public void setDeviceModelStr(String str) {
        this.deviceModelStr = str;
    }

    public void setExpireTime(String str) {
        this.expireTime = str;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public void setKeyIdArray(byte[] bArr) {
        this.keyIdArray = bArr;
    }

    public void setKeyLoadType(byte b) {
        this.keyLoadType = b;
    }

    public void setKeyNameLen(byte b) {
        this.keyNameLen = b;
    }

    public void setKeyNameStr(String str) {
        this.keyNameStr = str;
    }

    public void setKeyNo(String str) {
        this.keyNo = str;
    }

    public void setKeyPermission(byte b) {
        this.keyPermission = b;
    }

    public void setKeyState(byte b) {
        this.keyState = b;
    }

    public void setKeyType(byte b) {
        this.keyType = b;
    }

    public void setUserPhone(String str) {
        this.userPhone = str;
    }

    public void setUserPro(byte b) {
        this.userPro = b;
    }

    public void setUwbTag(byte b) {
        this.uwbTag = b;
    }

    public DigitalKeyBean(byte b, String str, byte b2, String str2, byte b3, String str3, byte b4, String str4, byte b5, String str5, byte b6, byte b7, byte b8, byte[] bArr, byte b9) {
        this.userPro = b;
        this.userPhone = str;
        this.keyType = b2;
        this.keyNo = str2;
        this.keyNameLen = b3;
        this.keyNameStr = str3;
        this.deviceModelLen = b4;
        this.deviceModelStr = str4;
        this.keyState = b5;
        this.expireTime = str5;
        this.keyPermission = b6;
        this.keyLoadType = b7;
        this.accountTag = b8;
        this.keyIdArray = bArr;
        this.uwbTag = b9;
    }
}
