package com.byd.systemsettings.hotspot.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.systemsettings.hotspot.service.HotspotService;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class HotspotReceiver extends BroadcastReceiver {
    private static final String TAG = "HotspotReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a.a(TAG, "--onReceive--Action = " + intent.getAction());
        HotspotService.startHotspotAction(context, (Intent) intent.clone());
    }
}
