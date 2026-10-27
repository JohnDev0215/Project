package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.statusbar.phone.StatusBar;

/* JADX INFO: loaded from: classes.dex */
public class AssistantReceiver extends BroadcastReceiver {
    public static final String ACTION_DISMISS_VOLUME_DIALOG = "action.byd.sysui.volume.dismiss";
    private static final String TAG = "AssistantReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals(action, ACTION_DISMISS_VOLUME_DIALOG)) {
            Log.i(TAG, "AssistantReceiver action = " + action);
            StatusBar statusBar = (StatusBar) SysUiServiceProvider.getComponent(context, StatusBar.class);
            if (statusBar == null) {
                return;
            }
            statusBar.dismissVolumeDialog();
        }
    }
}
