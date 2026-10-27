package com.byd.auto_emanual.update;

/* JADX INFO: loaded from: classes.dex */
public class NewVersionInfo {
    public static final int UPDATE_FORCE = 2;
    public static final int UPDATE_SILENCE = 3;
    public static final int UPDATE_TIPS = 1;
    private String downloadUrl;
    private int packageSize;
    private String upgradeContent;
    private int upgradeStyle;
    private int versionCode;
    private String versionDescription;
    private String versionName;

    public NewVersionInfo() {
    }

    public NewVersionInfo(String versionName, String versionDescription, int versionCode, int packageSize, int upgradeStyle, String downloadUrl, String upgradeContent) {
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.versionDescription = versionDescription;
        this.upgradeStyle = upgradeStyle;
        this.upgradeContent = upgradeContent;
        this.packageSize = packageSize;
        this.downloadUrl = downloadUrl;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionDescription() {
        return this.versionDescription;
    }

    public void setVersionDescription(String versionDescription) {
        this.versionDescription = versionDescription;
    }

    public String getUpgradeContent() {
        return this.upgradeContent;
    }

    public void setUpgradeContent(String upgradeContent) {
        this.upgradeContent = upgradeContent;
    }

    public int getPackageSize() {
        return this.packageSize;
    }

    public void setPackageSize(int packageSize) {
        this.packageSize = packageSize;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getUpgradeStyle() {
        return this.upgradeStyle;
    }

    public void setUpgradeStyle(int upgradeStyle) {
        this.upgradeStyle = upgradeStyle;
    }
}
