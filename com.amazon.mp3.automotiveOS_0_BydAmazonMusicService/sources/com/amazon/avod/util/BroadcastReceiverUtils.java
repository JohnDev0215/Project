package com.amazon.avod.util;

import android.content.BroadcastReceiver;
import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class BroadcastReceiverUtils {
    public static void unregisterReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        try {
            context.unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException e) {
            DLog.exceptionf(e, "Broadcast recevier failed to de-register: %s", broadcastReceiver.getClass().getSimpleName());
        }
    }
}
