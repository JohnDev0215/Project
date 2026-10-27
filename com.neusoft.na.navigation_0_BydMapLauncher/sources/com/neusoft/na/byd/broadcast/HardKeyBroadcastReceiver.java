package com.neusoft.na.byd.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class HardKeyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "XJHardKeyReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
        Log.i(TAG, "onReceive: " + action);
        if ("CUSTOM_NAVI_STANDARD_BROADCAST_RECV".equals(action)) {
            int intExtra = intent.getIntExtra("DEST_TYPE", -1);
            Log.i(TAG, "onReceive: " + intExtra);
            if (intExtra == 0) {
                Intent intent2 = new Intent();
                intent2.putExtra("type", 1);
                intent2.addFlags(268435456);
                intent2.setClassName(context, "com.neusoft.na.mots.MainActivity");
                context.startActivity(intent2);
                Log.i(TAG, "onReceive: done");
            }
        }
    }
}
