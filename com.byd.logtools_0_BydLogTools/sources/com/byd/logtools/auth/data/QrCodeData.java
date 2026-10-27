package com.byd.logtools.auth.data;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class QrCodeData implements Serializable {
    private long qrTime;
    private String qrValue;
    private String taskId;
    private int type;
    private String version;
    private String vid;

    public QrCodeData() {
    }

    public long getQrTime() {
        return this.qrTime;
    }

    public String getQrValue() {
        return this.qrValue;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public int getType() {
        return this.type;
    }

    public String getVersion() {
        return this.version;
    }

    public String getVid() {
        return this.vid;
    }

    public void setQrTime(long j) {
        this.qrTime = j;
    }

    public void setQrValue(String str) {
        this.qrValue = str;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    public void setType(int i) {
        this.type = i;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setVid(String str) {
        this.vid = str;
    }

    public String toString() {
        return "QrCodeData{qrValue='" + this.qrValue + "', vid='" + this.vid + "', qrTime=" + this.qrTime + ", taskId='" + this.taskId + "', type=" + this.type + ", version='" + this.version + "'}";
    }

    public QrCodeData(String str, String str2, long j, String str3, int i, String str4) {
        this.qrValue = str;
        this.vid = str2;
        this.qrTime = j;
        this.taskId = str3;
        this.type = i;
        this.version = str4;
    }
}
