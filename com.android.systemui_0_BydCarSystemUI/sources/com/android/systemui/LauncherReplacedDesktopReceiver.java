package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.stackdivider.DividerUtils;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class LauncherReplacedDesktopReceiver extends BroadcastReceiver {
    public static final String ACTION = "com.byd.ACTION_screen_split";
    private static final String TAG = "LauncherReplacedDesktopReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BydLogHelper.logD(TAG, "LauncherReplacedDesktopReceiver action = " + action);
        if (ACTION.equals(action)) {
            DividerUtils.getInstance().setLauncherReplacedDesktop(true, true);
        }
    }
}
