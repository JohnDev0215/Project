package com.amazon.mp3.library.service.sync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.visualon.OSMPUtils.voOSType;

/* JADX INFO: loaded from: classes3.dex */
public class MediaReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        SyncService.startSync(context, voOSType.VOOSMP_PID_ANALYTICS_AGENT);
    }
}
