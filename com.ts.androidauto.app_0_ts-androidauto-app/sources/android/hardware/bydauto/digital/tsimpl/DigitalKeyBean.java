package android.hardware.bydauto.digital.tsimpl;

import java.util.Map;
import java.util.Set;

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

    public DigitalKeyBean(byte userPro, String userPhone, byte keyType, String keyNo, byte keyNameLen, String keyNameStr, byte deviceModelLen, String deviceModelStr, byte keyState, String expireTime, byte keyPermission, byte keyLoadType, byte accountTag, byte[] keyIdArray, byte uwbTag) {
        this.userPro = userPro;
        this.userPhone = userPhone;
        this.keyType = keyType;
        this.keyNo = keyNo;
        this.keyNameLen = keyNameLen;
        this.keyNameStr = keyNameStr;
        this.deviceModelLen = deviceModelLen;
        this.deviceModelStr = deviceModelStr;
        this.keyState = keyState;
        this.expireTime = expireTime;
        this.keyPermission = keyPermission;
        this.keyLoadType = keyLoadType;
        this.accountTag = accountTag;
        this.keyIdArray = keyIdArray;
        this.uwbTag = uwbTag;
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

    public void setUserPro(byte userPro) {
        this.userPro = userPro;
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

    public void setKeyType(byte keyType) {
        this.keyType = keyType;
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

    public void setKeyNameLen(byte keyNameLen) {
        this.keyNameLen = keyNameLen;
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

    public void setDeviceModelLen(byte deviceModelLen) {
        this.deviceModelLen = deviceModelLen;
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

    public void setKeyState(byte keyState) {
        this.keyState = keyState;
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

    public void setKeyPermission(byte keyPermission) {
        this.keyPermission = keyPermission;
    }

    public byte getKeyLoadType() {
        return this.keyLoadType;
    }

    public void setKeyLoadType(byte keyLoadType) {
        this.keyLoadType = keyLoadType;
    }

    public byte getAccountTag() {
        return this.accountTag;
    }

    public void setAccountTag(byte accountTag) {
        this.accountTag = accountTag;
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

    public void setUwbTag(byte uwbTag) {
        this.uwbTag = uwbTag;
    }

    public byte[] getKeyIdArray() {
        return this.keyIdArray;
    }

    public void setKeyIdArray(byte[] keyIdArray) {
        this.keyIdArray = keyIdArray;
    }
}
