package com.android.server.telecom.ui;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telecom.Log;

/* JADX INFO: loaded from: classes.dex */
public class NotificationChannelManager {
    private BroadcastReceiver mLocaleChangeReceiver = new BroadcastReceiver() { // from class: com.android.server.telecom.ui.NotificationChannelManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.i(this, "Locale change; recreating channels.", new Object[0]);
            NotificationChannelManager.this.createOrUpdateAll(context);
        }
    };

    public void createChannels(Context context) {
        context.registerReceiver(this.mLocaleChangeReceiver, new IntentFilter("android.intent.action.LOCALE_CHANGED"));
        createOrUpdateAll(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createOrUpdateAll(Context context) {
        createOrUpdateChannel(context, "TelecomMissedCalls");
        createOrUpdateChannel(context, "TelecomIncomingCalls");
        createOrUpdateChannel(context, "TelecomCallBlocking");
        createOrUpdateChannel(context, "TelecomBackgroundAudioProcessing");
        createOrUpdateChannel(context, "TelecomDisconnectedCalls");
        createOrUpdateChannel(context, "TelecomInCallServiceCrash");
    }

    private void createOrUpdateChannel(Context context, String str) {
        getNotificationManager(context).createNotificationChannel(createChannel(context, str));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.app.NotificationChannel createChannel(android.content.Context r10, java.lang.String r11) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.ui.NotificationChannelManager.createChannel(android.content.Context, java.lang.String):android.app.NotificationChannel");
    }

    private NotificationManager getNotificationManager(Context context) {
        return (NotificationManager) context.getSystemService(NotificationManager.class);
    }
}
