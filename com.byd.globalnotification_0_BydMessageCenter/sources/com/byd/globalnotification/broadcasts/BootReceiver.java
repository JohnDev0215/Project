package com.byd.globalnotification.broadcasts;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import b.a.a.a.a;
import b.c.a.f.u;
import b.c.a.h.b;

/* JADX INFO: loaded from: classes.dex */
public class BootReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        b.b("BootReceiver:is in!!!");
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            b.b("BootReceiver:is start!!!");
            if (u.f1756d == null) {
                try {
                    u uVar = new u();
                    uVar.registerAsSystemService(context, new ComponentName(context.getPackageName(), getClass().getCanonicalName()), -1);
                    b.b("BootReceiver service.registerAsSystemService success in BootReceiver service = " + uVar);
                } catch (Throwable th) {
                    StringBuilder sbD = a.d("BootReceiver service.registerAsSystemService failed : ");
                    sbD.append(th.getMessage());
                    b.a(sbD.toString());
                }
            }
            b.b("BootReceiver NotificationApplication --> onCreate:");
        }
    }
}
