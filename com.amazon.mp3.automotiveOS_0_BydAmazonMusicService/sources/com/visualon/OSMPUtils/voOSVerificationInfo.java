package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSVerificationInfo {
    private int DataFlag;
    private int DataSize;
    private byte[] ResponseData;
    private int ResponseDataSize;
    private int UserData;
    private String VerificationData;

    public int getUserData() {
        return this.UserData;
    }

    public void setUserData(int i) {
        this.UserData = i;
    }

    public String getVerificationData() {
        return this.VerificationData;
    }

    public void setVerificationData(String str) {
        this.VerificationData = str;
    }

    public int getDataFlag() {
        return this.DataFlag;
    }

    public void setDataFlag(int i) {
        this.DataFlag = i;
    }

    public byte[] getResponseData() {
        return this.ResponseData;
    }

    public void setResponseData(byte[] bArr) {
        this.ResponseData = bArr;
    }

    public voOSVerificationInfo(int i, String str, int i2, byte[] bArr) {
        this.UserData = i;
        this.VerificationData = str;
        this.DataFlag = i2;
        this.ResponseData = bArr;
    }

    public voOSVerificationInfo() {
        this.UserData = 0;
        this.VerificationData = null;
        this.DataSize = 0;
        this.DataFlag = 0;
        this.ResponseData = null;
        this.ResponseDataSize = 0;
    }
}
