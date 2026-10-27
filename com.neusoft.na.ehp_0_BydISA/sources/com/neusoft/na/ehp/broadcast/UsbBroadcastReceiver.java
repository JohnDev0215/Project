package com.neusoft.na.ehp.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.neusoft.na.ehp.manager.NativeManager;

/* JADX INFO: loaded from: classes.dex */
public class UsbBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "NeuEhpUsbReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        Log.d(TAG, "onReceive: " + intent.getAction());
        String action = intent.getAction();
        if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
            String path = intent.getData().getPath();
            Log.d(TAG, "onReceive: path = " + path);
            if (path.contains("emulated") || path.contains("sdcard") || path.contains("/data") || path.contains("/dev")) {
                return;
            }
            NativeManager.getManager(context.getApplicationContext()).setUsbPath(path);
            return;
        }
        if ("android.intent.action.MEDIA_EJECT".equals(action)) {
            NativeManager.getManager(context.getApplicationContext()).onUnmount();
        }
    }
}
