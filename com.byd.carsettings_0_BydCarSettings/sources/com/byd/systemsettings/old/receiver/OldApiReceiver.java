package com.byd.systemsettings.old.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.byd.systemsettings.BydSettingsApi;

/* JADX INFO: loaded from: classes.dex */
public class OldApiReceiver extends BroadcastReceiver {
    public static final String SYS_BT_DIALOG_CHANGE_ACTION = "com.byd.carsettings.bt.dialog.state.change";
    public static final String SYS_HOTSPOT_DIALOG_CHANGE_ACTION = "com.byd.carsettings.hotspot.dialog.state.change";
    public static final String SYS_WIFI_DIALOG_CHANGE_ACTION = "com.byd.carsettings.wifi.dialog.state.change";
    private static final String TAG = "OldApiReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "--onReceive--action " + action);
        action.hashCode();
        switch (action) {
            case "com.byd.carsettings.wifi.dialog.state.change":
            case "com.byd.carsettings.hotspot.dialog.state.change":
            case "com.byd.carsettings.bt.dialog.state.change":
                boolean booleanExtra = intent.getBooleanExtra("show", false);
                BydSettingsApi.getApi(context, null).setHightLevelDialogShowing(booleanExtra);
                Log.i(TAG, "--onReceive--mSettingsDialogShow " + booleanExtra);
                break;
        }
    }
}
