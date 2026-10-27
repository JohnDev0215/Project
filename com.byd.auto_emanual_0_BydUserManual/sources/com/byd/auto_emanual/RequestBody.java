package com.byd.auto_emanual;

/* JADX INFO: loaded from: classes.dex */
public class RequestBody {
    private String enCodeData;
    private String sign;
    private String userName;
    private String uuid;

    public RequestBody(String userName, String uuid, String sign, String enCodeData) {
        this.userName = userName;
        this.uuid = uuid;
        this.sign = sign;
        this.enCodeData = enCodeData;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getEnCodeData() {
        return this.enCodeData;
    }

    public void setEnCodeData(String enCodeData) {
        this.enCodeData = enCodeData;
    }

    public String toString() {
        return "{\"userName\":\"" + this.userName + "\",\"uuid\":\"" + this.uuid + "\",\"sign\":\"" + this.sign + "\",\"enCodeData\":\"" + this.enCodeData + "\"}";
    }
}
