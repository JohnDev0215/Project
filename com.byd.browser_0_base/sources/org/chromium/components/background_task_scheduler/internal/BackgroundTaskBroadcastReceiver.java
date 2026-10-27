package org.chromium.components.background_task_scheduler.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class BackgroundTaskBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Log.e("cr_BkgrdTaskBR", "AlarmManager based BackgroundTasks are unsupported.");
    }
}
