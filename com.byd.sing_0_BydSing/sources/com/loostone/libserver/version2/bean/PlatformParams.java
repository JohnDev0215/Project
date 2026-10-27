package com.loostone.libserver.version2.bean;

import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class PlatformParams extends OTAParams {
    private String modelName;
    private String sdkVersion;

    public PlatformParams(String str, String str2) {
        h.d(str, "modelName");
        h.d(str2, "sdkVersion");
        this.modelName = str;
        this.sdkVersion = str2;
    }

    public final String getModelName() {
        return this.modelName;
    }

    public final String getSdkVersion() {
        return this.sdkVersion;
    }

    public final void setModelName(String str) {
        h.d(str, "<set-?>");
        this.modelName = str;
    }

    public final void setSdkVersion(String str) {
        h.d(str, "<set-?>");
        this.sdkVersion = str;
    }
}
