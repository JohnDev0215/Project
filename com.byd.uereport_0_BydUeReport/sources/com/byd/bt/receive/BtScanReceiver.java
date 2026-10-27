package com.byd.bt.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import x3.a;

/* JADX INFO: loaded from: classes.dex */
public class BtScanReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a.a("BtScanReceiver", "---onReceive--action:" + intent.getAction());
        d3.a.c().a((Intent) intent.clone());
    }
}
