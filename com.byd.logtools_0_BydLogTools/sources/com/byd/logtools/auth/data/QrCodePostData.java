package com.byd.logtools.auth.data;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class QrCodePostData implements Serializable {
    private String factor;
    private String iv;
    private long qrTime;
    private String sign;
    private String taskId;
    private int type;
    private String vid;

    public QrCodePostData(String str, long j, String str2, String str3, String str4, String str5, int i) {
        this.vid = str;
        this.qrTime = j;
        this.taskId = str2;
        this.factor = str3;
        this.iv = str4;
        this.sign = str5;
        this.type = i;
    }

    public String getFactor() {
        return this.factor;
    }

    public String getIv() {
        return this.iv;
    }

    public long getQrTime() {
        return this.qrTime;
    }

    public String getSign() {
        return this.sign;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public int getType() {
        return this.type;
    }

    public String getVid() {
        return this.vid;
    }

    public void setFactor(String str) {
        this.factor = str;
    }

    public void setIv(String str) {
        this.iv = str;
    }

    public void setQrTime(long j) {
        this.qrTime = j;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setVid(String str) {
        this.vid = str;
    }

    public String toString() {
        return "QrCodePostData{vid='" + this.vid + "', qrTime=" + this.qrTime + ", taskId='" + this.taskId + "', factor='" + this.factor + "', iv='" + this.iv + "', sign='" + this.sign + "', type=" + this.type + '}';
    }
}
