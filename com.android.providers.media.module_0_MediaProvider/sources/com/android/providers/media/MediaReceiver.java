package com.android.providers.media;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public class MediaReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            IdleService.scheduleIdlePass(context);
        } else {
            intent.setComponent(new ComponentName(context, (Class<?>) MediaService.class));
            MediaService.enqueueWork(context, intent);
        }
    }
}
