package org.chromium.chrome.browser.notifications.permissions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import defpackage.kp1;
import defpackage.oj2;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationPermissionChangeReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals("android.app.action.APP_BLOCK_STATE_CHANGED") && intent.hasExtra("android.app.extra.BLOCKED_STATE")) {
            boolean booleanExtra = intent.getBooleanExtra("android.app.extra.BLOCKED_STATE", false);
            kp1.a.a.getClass();
            oj2.O("Mobile.SystemNotification.Permission.Change", !booleanExtra);
        }
    }
}
