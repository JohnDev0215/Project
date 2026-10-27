package com.engine.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public class TboxStaticInfo {
    private String tboxSoftVersion = "N/A";
    private String tboxHardVersion = "N/A";
    private String imsi = "N/A";
    private String tboxSerial = "N/A";
    private String softCode = "N/A";

    public String getTboxSoftVersion() {
        return this.tboxSoftVersion;
    }

    public void setTboxSoftVersion(String tboxSoftVersion) {
        this.tboxSoftVersion = tboxSoftVersion;
    }

    public String getTboxHardVersion() {
        return this.tboxHardVersion;
    }

    public void setTboxHardVersion(String tboxHardVersion) {
        this.tboxHardVersion = tboxHardVersion;
    }

    public String getImsi() {
        return this.imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getTboxSerial() {
        return this.tboxSerial;
    }

    public void setTboxSerial(String tboxSerial) {
        this.tboxSerial = tboxSerial;
    }

    public String getSoftCode() {
        return this.softCode;
    }

    public void setSoftCode(String softCode) {
        this.softCode = softCode;
    }

    public String toString() {
        return "TboxStaticInfo{tboxSoftVersion='" + this.tboxSoftVersion + "', tboxHardVersion='" + this.tboxHardVersion + "', imsi length:'" + this.imsi.length() + "', tboxSerial length:'" + this.tboxSerial.length() + "', softCode='" + this.softCode + "'}";
    }
}
