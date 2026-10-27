package com.byd.bluetoothcall.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.byd.bluetoothcall.service.BYD_BlueToothService;

/* JADX INFO: loaded from: classes.dex */
public class BootCompleteReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Log.d("DANDAN", "BootCompleteReceiver 11111111111111>>>>>>receive broadcast" + intent.getAction());
        Intent intent2 = new Intent(context, (Class<?>) BYD_BlueToothService.class);
        intent2.putExtra("command", "BootCompleteReceiver");
        context.startService(intent2);
    }
}
