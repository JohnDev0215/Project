package com.byd.auto_emanual.fragment;

import java.util.StringJoiner;
import org.apache.shiro.config.Ini;

/* JADX INFO: loaded from: classes.dex */
public class ZipInfo {
    public static final String NO_UPLOAD_TIME = "0";
    private String multiResourceLanguage;
    private String resourceLanguage;
    private String serverFilepath;
    private String targetFile;
    private String zipSize;
    private String zipUploadTime;
    private String zipUrl;

    public String getZipSize() {
        return this.zipSize;
    }

    public void setZipSize(String zipSize) {
        this.zipSize = zipSize;
    }

    public String getZipUrl() {
        return this.zipUrl;
    }

    public void setZipUrl(String zipUrl) {
        this.zipUrl = zipUrl;
    }

    public String getZipUploadTime() {
        return this.zipUploadTime;
    }

    public void setZipUploadTime(String zipUploadTime) {
        this.zipUploadTime = zipUploadTime;
    }

    public String getTargetFile() {
        return this.targetFile;
    }

    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }

    public String getServerFilepath() {
        return this.serverFilepath;
    }

    public void setServerFilepath(String serverFilepath) {
        this.serverFilepath = serverFilepath;
    }

    public String getResourceLanguage() {
        return this.resourceLanguage;
    }

    public void setResourceLanguage(String resourceLanguage) {
        this.resourceLanguage = resourceLanguage;
    }

    public String getMultiResourceLanguage() {
        return this.multiResourceLanguage;
    }

    public void setMultiResourceLanguage(String multiResourceLanguage) {
        this.multiResourceLanguage = multiResourceLanguage;
    }

    public String toString() {
        return new StringJoiner(", ", ZipInfo.class.getSimpleName() + Ini.SECTION_PREFIX, Ini.SECTION_SUFFIX).add("zipUploadTime='" + this.zipUploadTime + "'").add("zipUrl='" + this.zipUrl + "'").add("zipSize='" + this.zipSize + "'").add("targetFile='" + this.targetFile + "'").add("serverFilepath='" + this.serverFilepath + "'").add("resourceLanguage='" + this.resourceLanguage + "'").add("multiResourceLanguage='" + this.multiResourceLanguage + "'").toString();
    }
}
