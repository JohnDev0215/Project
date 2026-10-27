package com.android.shell;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class BugreportRequestedReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.d("BugreportRequestedReceiver", "onReceive(): " + BugreportProgressService.dumpIntent(intent));
        Intent intent2 = new Intent(context, (Class<?>) BugreportProgressService.class);
        Log.d("BugreportRequestedReceiver", "onReceive() ACTION: " + intent2.getAction());
        intent2.setAction(intent.getAction());
        intent2.putExtra("android.intent.extra.ORIGINAL_INTENT", intent);
        context.startService(intent2);
    }
}
