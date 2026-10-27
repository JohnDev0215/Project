package com.android.car.developeroptions.sim;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class SimSelectNotification extends BroadcastReceiver {
    public static void cancelNotification(Context context) {
        ((NotificationManager) context.getSystemService("notification")).cancel(1);
    }
}
