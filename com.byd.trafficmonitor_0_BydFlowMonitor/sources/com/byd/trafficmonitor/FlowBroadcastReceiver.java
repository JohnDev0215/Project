package com.byd.trafficmonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class FlowBroadcastReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f4375a = "com.byd.traffic_monitor.CHECK_FLOW";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f4376b = "com.byd.traffic_monitor.UPLOAD_SIM";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("com.byd.traffic_monitor.CHECK_FLOW".equals(intent.getAction())) {
            TrafficMonitorService.p(context);
        } else if ("com.byd.traffic_monitor.UPLOAD_SIM".equals(intent.getAction())) {
            TrafficMonitorService.q(context);
        }
    }
}
