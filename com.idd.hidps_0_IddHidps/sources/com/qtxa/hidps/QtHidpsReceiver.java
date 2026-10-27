package com.qtxa.hidps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qtxa.qtxadefend.w;

/* JADX INFO: loaded from: classes.dex */
public class QtHidpsReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        w.d("RECEIVE_BOOT_COMPLETED");
        a.h(context.getApplicationContext());
    }
}
