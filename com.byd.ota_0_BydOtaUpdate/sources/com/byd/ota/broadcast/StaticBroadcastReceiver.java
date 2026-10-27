package com.byd.ota.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import c.a.b.d.e;
import com.byd.ota.view.OtaUiService;

/* JADX INFO: loaded from: classes.dex */
public class StaticBroadcastReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3054a = StaticBroadcastReceiver.class.getSimpleName();

    public final void a(Context context, Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putInt("otaBroadcastType", 2);
        Intent intent2 = new Intent(context, (Class<?>) OtaUiService.class);
        intent2.putExtras(bundle);
        context.startService(intent2);
    }

    public final void b(Context context, Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putInt("otaBroadcastType", 1);
        bundle.putString("method_name", intent.getStringExtra("method_name"));
        Intent intent2 = new Intent(context, (Class<?>) OtaUiService.class);
        intent2.putExtras(bundle);
        context.startService(intent2);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        e.c(f3054a, "action = " + action);
        action.hashCode();
        if (action.equals("android.intent.action.MASTER_CLEAR_NOTIFICATION")) {
            a(context, intent);
        } else if (action.equals("vendor.intent.action.OTA_TEST_BROADCAST")) {
            b(context, intent);
        }
    }
}
