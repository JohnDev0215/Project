package com.byd.uereport;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.byd.cpaa.service.CpaaActionService;
import g5.a;

/* JADX INFO: loaded from: classes2.dex */
public class CPAAReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || TextUtils.isEmpty(intent.getAction())) {
            return;
        }
        a.i("CPAAReceiver, onReceive, action: " + intent.getAction());
        CpaaActionService.o(context, intent);
    }
}
