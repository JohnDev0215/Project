package com.byd.carsettings.model.dilink;

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import com.byd.mvp.model.BydData;

/* JADX INFO: loaded from: classes2.dex */
public class BydAppInfo extends BydData {
    private String appName;
    private long cacheBytes;
    private long codeBytes;
    private long dataBytes;
    private boolean disable;
    private Drawable icon;
    private PackageInfo info;
    private long installTime;
    private boolean systemApp;
    private String version;

    public BydAppInfo() {
    }

    public String getAppName() {
        return this.appName;
    }

    public long getCacheBytes() {
        return this.cacheBytes;
    }

    public long getCodeBytes() {
        return this.codeBytes;
    }

    public long getDataBytes() {
        return this.dataBytes;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public PackageInfo getInfo() {
        return this.info;
    }

    public long getInstallTime() {
        return this.installTime;
    }

    public String getVersion() {
        return this.version;
    }

    public boolean isDisable() {
        return this.disable;
    }

    public boolean isSystemApp() {
        return this.systemApp;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setCacheBytes(long j) {
        this.cacheBytes = j;
    }

    public void setCodeBytes(long j) {
        this.codeBytes = j;
    }

    public void setDataBytes(long j) {
        this.dataBytes = j;
    }

    public void setDisable(boolean z) {
        this.disable = z;
    }

    public void setIcon(Drawable drawable) {
        this.icon = drawable;
    }

    public void setInfo(PackageInfo packageInfo) {
        this.info = packageInfo;
    }

    public void setInstallTime(long j) {
        this.installTime = j;
    }

    public void setSystemApp(boolean z) {
        this.systemApp = z;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String toString() {
        return "BydAppInfo{info=" + this.info + ", icon=" + this.icon + ", appName='" + this.appName + "', systemApp=" + this.systemApp + ", disable=" + this.disable + ", version='" + this.version + "', codeBytes=" + this.codeBytes + ", cacheBytes=" + this.cacheBytes + ", dataBytes=" + this.dataBytes + ", installTime=" + this.installTime + '}';
    }

    public BydAppInfo(PackageInfo packageInfo, Drawable drawable, String str, boolean z) {
        this.info = packageInfo;
        this.icon = drawable;
        this.appName = str;
        this.systemApp = z;
    }
}
