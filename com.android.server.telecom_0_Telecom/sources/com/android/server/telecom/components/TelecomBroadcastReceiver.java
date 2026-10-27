package com.android.server.telecom.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.server.telecom.TelecomSystem;

/* JADX INFO: loaded from: classes.dex */
public final class TelecomBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        synchronized (getTelecomSystem().getLock()) {
            getTelecomSystem().getTelecomBroadcastIntentProcessor().processIntent(intent);
        }
    }

    public TelecomSystem getTelecomSystem() {
        return TelecomSystem.getInstance();
    }
}
