package com.android.launcher3.home.listener;

import android.content.res.Configuration;

/* JADX INFO: loaded from: classes4.dex */
public interface OnApplicationCallBack {
    void onBroadcastReceiverCallBack(int type);

    void onCancelStartAnimate();

    void onConfigurationChanged(Configuration newConfig);
}
