package com.amazon.mp3.store.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.amazon.mp3.service.ComponentCheckService;

/* JADX INFO: loaded from: classes4.dex */
public class FirstBootReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ComponentCheckService.start(context, intent.getAction());
    }
}
