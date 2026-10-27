package com.byd.systemsettings.hotspot.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import b.b.m.c.a;
import com.byd.systemsettings.hotspot.service.HotspotService;

/* JADX INFO: loaded from: classes.dex */
public class HotspotReceiver extends BroadcastReceiver {
    private static final String TAG = "HotspotReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a.a(TAG, "--onReceive--Action = " + intent.getAction());
        HotspotService.startHotspotAction(context, (Intent) intent.clone());
    }
}
