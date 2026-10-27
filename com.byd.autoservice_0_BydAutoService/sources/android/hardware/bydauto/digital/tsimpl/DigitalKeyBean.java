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
            str.hashCode();
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

    public void setAccountTag(byte b3) {
        this.accountTag = b3;
    }

    public void setDeviceModelLen(byte b3) {
        this.deviceModelLen = b3;
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

    public void setKeyLoadType(byte b3) {
        this.keyLoadType = b3;
    }

    public void setKeyNameLen(byte b3) {
        this.keyNameLen = b3;
    }

    public void setKeyNameStr(String str) {
        this.keyNameStr = str;
    }

    public void setKeyNo(String str) {
        this.keyNo = str;
    }

    public void setKeyPermission(byte b3) {
        this.keyPermission = b3;
    }

    public void setKeyState(byte b3) {
        this.keyState = b3;
    }

    public void setKeyType(byte b3) {
        this.keyType = b3;
    }

    public void setUserPhone(String str) {
        this.userPhone = str;
    }

    public void setUserPro(byte b3) {
        this.userPro = b3;
    }

    public void setUwbTag(byte b3) {
        this.uwbTag = b3;
    }

    public DigitalKeyBean(byte b3, String str, byte b4, String str2, byte b5, String str3, byte b6, String str4, byte b7, String str5, byte b8, byte b9, byte b10, byte[] bArr, byte b11) {
        this.userPro = b3;
        this.userPhone = str;
        this.keyType = b4;
        this.keyNo = str2;
        this.keyNameLen = b5;
        this.keyNameStr = str3;
        this.deviceModelLen = b6;
        this.deviceModelStr = str4;
        this.keyState = b7;
        this.expireTime = str5;
        this.keyPermission = b8;
        this.keyLoadType = b9;
        this.accountTag = b10;
        this.keyIdArray = bArr;
        this.uwbTag = b11;
    }
}
