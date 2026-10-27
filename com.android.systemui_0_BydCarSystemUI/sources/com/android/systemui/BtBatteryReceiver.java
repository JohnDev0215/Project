package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class BtBatteryReceiver extends BroadcastReceiver {
    public static final String ACTION = "android.bluetooth.headsetclient.profile.action.AG_EVENT";
    public static final String EXTRA_BATTERY_LEVEL = "android.bluetooth.headsetclient.extra.BATTERY_LEVEL";
    private static final String TAG = "BtBatteryReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BydLogHelper.logD(TAG, "BarReceiver action = " + action);
        if (ACTION.equals(action)) {
            int intExtra = intent.getIntExtra(EXTRA_BATTERY_LEVEL, -1);
            BydLogHelper.logD("BatLevel ================================ " + intExtra);
            if (intExtra != -1) {
                Settings.Global.putInt(context.getContentResolver(), "bat_level", intExtra);
            }
        }
    }
}
