package com.byd.dilink.app;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkPlatformConfig {
    int getDeviceType(Context context);

    String getUiGeneration(Context context);

    boolean isUiRGeneration(Context context);
}
