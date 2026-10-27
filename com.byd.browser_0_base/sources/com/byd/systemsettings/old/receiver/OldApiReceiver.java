package com.byd.systemsettings.old.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import defpackage.kk;

/* JADX INFO: loaded from: classes.dex */
public class OldApiReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i("OldApiReceiver", "--onReceive--action " + action);
        action.getClass();
        switch (action) {
            case "com.byd.carsettings.wifi.dialog.state.change":
            case "com.byd.carsettings.hotspot.dialog.state.change":
            case "com.byd.carsettings.bt.dialog.state.change":
                boolean booleanExtra = intent.getBooleanExtra("show", false);
                kk.b(context).getClass();
                Log.i("OldApiReceiver", "--onReceive--mSettingsDialogShow " + booleanExtra);
                break;
        }
    }
}
