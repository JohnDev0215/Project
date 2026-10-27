package com.android.launcher3.home.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.launcher3.home.listener.OnBroadcastReceiverCallBack;

/* JADX INFO: loaded from: classes4.dex */
public class AppStateBroadcastReceiver extends BroadcastReceiver {
    private OnBroadcastReceiverCallBack onBroadcastReceiverCallBack;

    public AppStateBroadcastReceiver(OnBroadcastReceiverCallBack onBroadcastReceiverCallBack) {
        this.onBroadcastReceiverCallBack = onBroadcastReceiverCallBack;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED) || intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
            this.onBroadcastReceiverCallBack.OnBroadcastReceiverCallBack(intent.getData().getSchemeSpecificPart());
        }
    }
}
