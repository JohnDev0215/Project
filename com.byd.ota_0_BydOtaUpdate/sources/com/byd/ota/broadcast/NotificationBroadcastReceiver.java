package com.byd.ota.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import c.a.b.d.e;

/* JADX INFO: loaded from: classes.dex */
public class NotificationBroadcastReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3053a = NotificationBroadcastReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        String action = intent.getAction();
        e.c(f3053a, "action = " + action);
        int iHashCode = action.hashCode();
        if (iHashCode == 575478056) {
            str = "vendor.intent.action.NOTIFICATION_DELETE_INTENT";
        } else if (iHashCode != 1371578058) {
            return;
        } else {
            str = "vendor.intent.action.NOTIFICATION_CONTENT_INTENT";
        }
        action.equals(str);
    }
}
