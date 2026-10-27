package com.amazon.device.crashmanager.ndk;

/* JADX INFO: loaded from: classes2.dex */
public class SystemProps {
    private String bootLoader;
    private String buildFingerPrint;
    private String hardware;
    private String osBuildNumber;
    private String revision;
    private String version;

    public String getBuildFingerPrint() {
        return this.buildFingerPrint;
    }

    public String getOsBuildNumber() {
        return this.osBuildNumber;
    }

    public String getHardware() {
        return this.hardware;
    }

    public String getRevision() {
        return this.revision;
    }

    public String getBootLoader() {
        return this.bootLoader;
    }

    public String getVersion() {
        return this.version;
    }

    public void setBuildFingerPrint(String str) {
        this.buildFingerPrint = str;
    }

    public void setOsBuildNumber(String str) {
        this.osBuildNumber = str;
    }

    public void setHardware(String str) {
        this.hardware = str;
    }

    public void setRevision(String str) {
        this.revision = str;
    }

    public void setBootLoader(String str) {
        this.bootLoader = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
