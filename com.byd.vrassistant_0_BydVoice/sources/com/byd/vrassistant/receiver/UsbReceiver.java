package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.eventbus.AudioEvent;
import com.byd.vrassistant.manager.LocalMediaManager;
import com.byd.vrassistant.utils.RLog;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class UsbReceiver extends BroadcastReceiver {
    public static String TAG = "NEU_UsbReceiver";
    private boolean isNeedUpdate = false;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals("android.intent.action.MEDIA_SCANNER_SCAN_FILE")) {
            RLog.e(TAG, "onReceive: MEDIA_SCANNER_SCAN_FILE");
            this.isNeedUpdate = true;
            return;
        }
        if (action.equals("android.intent.action.MEDIA_SCANNER_STARTED")) {
            RLog.e(TAG, "onReceive: MEDIA_SCANNER_STARTED");
            this.isNeedUpdate = true;
            EventBus.getDefault().post("onReceive: MEDIA_SCANNER_STARTED, isNeedUpdate = " + this.isNeedUpdate);
            return;
        }
        if (action.equals("android.intent.action.MEDIA_SCANNER_FINISHED")) {
            RLog.e(TAG, "onReceive: MEDIA_SCANNER_FINISHED, isNeedUpdate = " + this.isNeedUpdate);
            if (this.isNeedUpdate) {
                this.isNeedUpdate = false;
                EventBus.getDefault().postSticky(new AudioEvent("", 3));
                EventBus.getDefault().post("onReceive: MEDIA_SCANNER_FINISHED, isNeedUpdate = " + this.isNeedUpdate);
                return;
            }
            return;
        }
        if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
            RLog.e(TAG, "ACTION_MEDIA_MOUNTED");
            LocalMediaManager.get().setUsbConnected(true);
            EventBus.getDefault().post(action);
        } else if (action.equals("android.intent.action.MEDIA_EJECT") || action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
            RLog.e(TAG, "ACTION_MEDIA_UNMOUNTED");
            LocalMediaManager.get().setUsbConnected(false);
            EventBus.getDefault().post(action);
            EventBus.getDefault().postSticky(new AudioEvent("", 3));
        }
    }
}
