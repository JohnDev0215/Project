package com.amazon.identity.mobi.common.ui;

import android.content.Context;

/* JADX INFO: loaded from: classes3.dex */
public interface UITask {
    String getEntryPoint();

    long getEpochExpirationTimestamp();

    String getUITaskId();

    UIType getUIType();

    void openUI(Context context);
}
