package android.hardware.bydauto.digital.tsimpl;

import com.google.common.base.Ascii;
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

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void init(Map<String, Object> map) {
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            str.hashCode();
            byte b = -1;
            switch (str.hashCode()) {
                case -933314910:
                    if (str.equals("deviceModelLen")) {
                        b = 0;
                    }
                    break;
                case -933307714:
                    if (str.equals("deviceModelStr")) {
                        b = 1;
                    }
                    break;
                case -834724724:
                    if (str.equals("expireTime")) {
                        b = 2;
                    }
                    break;
                case -832454278:
                    if (str.equals("uwbTag")) {
                        b = 3;
                    }
                    break;
                case -815441351:
                    if (str.equals("keyType")) {
                        b = 4;
                    }
                    break;
                case -803343827:
                    if (str.equals("accountTag")) {
                        b = 5;
                    }
                    break;
                case -147147038:
                    if (str.equals("userPro")) {
                        b = 6;
                    }
                    break;
                case 101944282:
                    if (str.equals("keyId")) {
                        b = 7;
                    }
                    break;
                case 101944448:
                    if (str.equals("keyNo")) {
                        b = 8;
                    }
                    break;
                case 325322851:
                    if (str.equals("userPhone")) {
                        b = 9;
                    }
                    break;
                case 490035570:
                    if (str.equals("keyState")) {
                        b = 10;
                    }
                    break;
                case 583342271:
                    if (str.equals("keyLoadType")) {
                        b = 11;
                    }
                    break;
                case 1911166350:
                    if (str.equals("keyPermission")) {
                        b = 12;
                    }
                    break;
                case 1999982239:
                    if (str.equals("keyIdArray")) {
                        b = Ascii.CR;
                    }
                    break;
                case 2096857131:
                    if (str.equals("keyNameLen")) {
                        b = Ascii.SO;
                    }
                    break;
                case 2096864327:
                    if (str.equals("keyNameStr")) {
                        b = Ascii.SI;
                    }
                    break;
            }
            switch (b) {
                case 0:
                    this.deviceModelLen = ((Byte) obj).byteValue();
                    break;
                case 1:
                    this.deviceModelStr = (String) obj;
                    break;
                case 2:
                    this.expireTime = (String) obj;
                    break;
                case 3:
                    this.uwbTag = ((Byte) obj).byteValue();
                    break;
                case 4:
                    this.keyType = ((Byte) obj).byteValue();
                    break;
                case 5:
                    this.accountTag = ((Byte) obj).byteValue();
                    break;
                case 6:
                    this.userPro = ((Byte) obj).byteValue();
                    break;
                case 7:
                    this.keyId = (String) obj;
                    break;
                case 8:
                    this.keyNo = (String) obj;
                    break;
                case 9:
                    this.userPhone = (String) obj;
                    break;
                case 10:
                    this.keyState = ((Byte) obj).byteValue();
                    break;
                case 11:
                    this.keyLoadType = ((Byte) obj).byteValue();
                    break;
                case 12:
                    this.keyPermission = ((Byte) obj).byteValue();
                    break;
                case 13:
                    this.keyIdArray = (byte[]) obj;
                    break;
                case 14:
                    this.keyNameLen = ((Byte) obj).byteValue();
                    break;
                case 15:
                    this.keyNameStr = (String) obj;
                    break;
            }
        }
    }

    public byte getUserPro() {
        return this.userPro;
    }

    public void setUserPro(byte b) {
        this.userPro = b;
    }

    public String getUserPhone() {
        return this.userPhone;
    }

    public void setUserPhone(String str) {
        this.userPhone = str;
    }

    public byte getKeyType() {
        return this.keyType;
    }

    public void setKeyType(byte b) {
        this.keyType = b;
    }

    public String getKeyNo() {
        return this.keyNo;
    }

    public void setKeyNo(String str) {
        this.keyNo = str;
    }

    public byte getKeyNameLen() {
        return this.keyNameLen;
    }

    public void setKeyNameLen(byte b) {
        this.keyNameLen = b;
    }

    public String getKeyNameStr() {
        return this.keyNameStr;
    }

    public void setKeyNameStr(String str) {
        this.keyNameStr = str;
    }

    public byte getDeviceModelLen() {
        return this.deviceModelLen;
    }

    public void setDeviceModelLen(byte b) {
        this.deviceModelLen = b;
    }

    public String getDeviceModelStr() {
        return this.deviceModelStr;
    }

    public void setDeviceModelStr(String str) {
        this.deviceModelStr = str;
    }

    public byte getKeyState() {
        return this.keyState;
    }

    public void setKeyState(byte b) {
        this.keyState = b;
    }

    public String getExpireTime() {
        return this.expireTime;
    }

    public void setExpireTime(String str) {
        this.expireTime = str;
    }

    public byte getKeyPermission() {
        return this.keyPermission;
    }

    public void setKeyPermission(byte b) {
        this.keyPermission = b;
    }

    public byte getKeyLoadType() {
        return this.keyLoadType;
    }

    public void setKeyLoadType(byte b) {
        this.keyLoadType = b;
    }

    public byte getAccountTag() {
        return this.accountTag;
    }

    public void setAccountTag(byte b) {
        this.accountTag = b;
    }

    public String getKeyId() {
        return this.keyId;
    }

    public void setKeyId(String str) {
        this.keyId = str;
    }

    public byte getUwbTag() {
        return this.uwbTag;
    }

    public void setUwbTag(byte b) {
        this.uwbTag = b;
    }

    public byte[] getKeyIdArray() {
        return this.keyIdArray;
    }

    public void setKeyIdArray(byte[] bArr) {
        this.keyIdArray = bArr;
    }
}
