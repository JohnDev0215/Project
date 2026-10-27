package com.byd.systemsettings.wifi2.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.systemsettings.wifi2.service.WifiActionDispatcher;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class WifiScanReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiScanReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a.a(TAG, "--onReceive--Action: " + intent.getAction());
        WifiActionDispatcher.getInstance().dispatchWifiAction(intent);
    }
}
