package com.byd.systemsettings.wifi2.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import b.b.m.c.a;
import com.byd.systemsettings.wifi2.service.WifiActionService;

/* JADX INFO: loaded from: classes.dex */
public class WifiActionReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiActionReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a.a(TAG, "--onReceive--Action: " + intent.getAction());
        WifiActionService.startWifiAction(context, (Intent) intent.clone());
    }
}
