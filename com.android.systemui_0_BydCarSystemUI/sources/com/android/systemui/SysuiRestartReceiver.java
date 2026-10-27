package com.android.systemui;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class SysuiRestartReceiver extends BroadcastReceiver {
    public static String ACTION = "com.android.systemui.action.RESTART";
    private static final String TAG = "SysuiRestartReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        BydLogHelper.logV("SysuiRestartReceiver action = " + intent.getAction());
        if (ACTION.equals(intent.getAction())) {
            NotificationManager.from(context).cancel(intent.getData().toString().substring(10), 6);
            Process.killProcess(Process.myPid());
        }
    }
}
