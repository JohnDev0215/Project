package com.byd.ota.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class GlobalBroadcastReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3052a = GlobalBroadcastReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        String action = intent.getAction();
        int iHashCode = action.hashCode();
        if (iHashCode == -1273480071) {
            str = "byd.intent.action.pano";
        } else if (iHashCode != 1132505917) {
            return;
        } else {
            str = "com.byd.xcall.ecall";
        }
        action.equals(str);
    }
}
