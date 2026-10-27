package com.loostone.libserver.version2.bean;

import com.android.volley.BuildConfig;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class UpgradeParams {
    private String upgradeType = BuildConfig.FLAVOR;
    private int period = 240;
    private String deviceId = BuildConfig.FLAVOR;
    private int version = -1;
    private int fileType = 3;

    public final String getDeviceId() {
        return this.deviceId;
    }

    public final int getFileType() {
        return this.fileType;
    }

    public final int getPeriod() {
        return this.period;
    }

    public final String getUpgradeType() {
        return this.upgradeType;
    }

    public final int getVersion() {
        return this.version;
    }

    public final void setDeviceId(String str) {
        h.d(str, "<set-?>");
        this.deviceId = str;
    }

    public final void setFileType(int i) {
        this.fileType = i;
    }

    public final void setPeriod(int i) {
        this.period = i;
    }

    public final void setUpgradeType(String str) {
        h.d(str, "<set-?>");
        this.upgradeType = str;
    }

    public final void setVersion(int i) {
        this.version = i;
    }
}
