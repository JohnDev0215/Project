package com.byd.vrassistant.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.byd.vrassistant.utils.RLog;

/* JADX INFO: loaded from: classes2.dex */
public class BootCompleteReceiver extends BroadcastReceiver {
    private static final String TAG = "NEU_BOOT";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        RLog.i(TAG, "BootCompleteReceiver");
    }
}
