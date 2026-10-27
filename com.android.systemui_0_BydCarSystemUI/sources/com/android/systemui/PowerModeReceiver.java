package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.stackdivider.DividerUtils;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class PowerModeReceiver extends BroadcastReceiver {
    public static final String ACTION = "com.byd.autoservice.ACTION_POWER";
    public static final String POWER_MODE = "power_mode";
    private static final String TAG = "PowerModeReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String str = TAG;
        BydLogHelper.logD(str, "PowerModeReceiver action = " + action);
        if (ACTION.equals(action)) {
            boolean booleanExtra = intent.getBooleanExtra(POWER_MODE, false);
            BydLogHelper.logD(str, "PowerModeReceiver powerMode = " + booleanExtra);
            DividerUtils.getInstance().setPowerMode(booleanExtra);
        }
    }
}
