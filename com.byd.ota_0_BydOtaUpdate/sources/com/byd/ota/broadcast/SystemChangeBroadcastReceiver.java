package com.byd.ota.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import c.a.b.d.e;
import c.a.g.c;

/* JADX INFO: loaded from: classes.dex */
public class SystemChangeBroadcastReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3055a = SystemChangeBroadcastReceiver.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public c f3056b;

    public void a(c cVar) {
        this.f3056b = cVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = f3055a;
        e.a(str, "SystemChange onReceive");
        if (intent.getAction().equals("android.intent.action.TIMEZONE_CHANGED")) {
            String stringExtra = intent.getStringExtra("time-zone");
            e.a(str, "时区变化：" + stringExtra);
            c cVar = this.f3056b;
            if (cVar != null) {
                cVar.c().g(stringExtra);
            }
        }
    }
}
