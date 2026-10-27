package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.eventbus.SystemStatusEvent;
import com.byd.vrassistant.utils.RLog;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class OtaMode8155Receiver extends BroadcastReceiver {
    public static String ACTION_ENTER_OTA_MODEL = "com.byd.ota.broadcast.ENTER_OTA_MODEL";
    public static String TAG = "NEU_OtaMode8155Receiver";
    String ACTION_EXIT_OTA_MODEL = "com.byd.ota.broadcast.EXIT_OTA_MODEL";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ACTION_ENTER_OTA_MODEL)) {
            RLog.e(TAG, "onReceive: " + action);
            EventBus.getDefault().postSticky(new SystemStatusEvent("", 1));
        }
    }
}
