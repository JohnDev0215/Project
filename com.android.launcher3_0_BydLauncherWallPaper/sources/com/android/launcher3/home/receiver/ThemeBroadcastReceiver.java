package com.android.launcher3.home.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.launcher3.home.Constants;
import com.android.launcher3.home.listener.OnBroadcastReceiverCallBack;
import com.android.launcher3.util.Logger;

/* JADX INFO: loaded from: classes4.dex */
public class ThemeBroadcastReceiver extends BroadcastReceiver {
    private OnBroadcastReceiverCallBack onBroadcastReceiverCallBack;

    public ThemeBroadcastReceiver(OnBroadcastReceiverCallBack onBroadcastReceiverCallBack) {
        this.onBroadcastReceiverCallBack = onBroadcastReceiverCallBack;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Constants.THEME_GET_WALLPAPER_STATE)) {
            Logger.e("pengbin", "ThemeBroadcastReceiver...onReceive");
            this.onBroadcastReceiverCallBack.OnBroadcastReceiverCallBack(24);
        }
    }
}
