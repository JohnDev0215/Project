package com.android.permissioncontroller.incident;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes8.dex */
public class ConfirmationReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        PendingList.getInstance().updateState(context, 0);
    }
}
