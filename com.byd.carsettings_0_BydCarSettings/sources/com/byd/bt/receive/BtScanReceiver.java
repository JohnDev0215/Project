package com.byd.bt.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import b.b.m.c.a;
import com.byd.bt.service.BtActionService;

/* JADX INFO: loaded from: classes.dex */
public class BtScanReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a.a("BtScanReceiver", "---onReceive--action:" + intent.getAction());
        BtActionService.r(context, (Intent) intent.clone());
    }
}
