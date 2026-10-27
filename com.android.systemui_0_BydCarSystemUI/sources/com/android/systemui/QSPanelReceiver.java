package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.statusbar.phone.StatusBar;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class QSPanelReceiver extends BroadcastReceiver {
    public static final String ACTION = "com.byd.action.qsPanel.auto";
    private static final String TAG = "QSPanelReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        StatusBar statusBar;
        BydLogHelper.logD(TAG, "QSPanelReceiver action = " + intent.getAction());
        if (!ACTION.equals(intent.getAction()) || (statusBar = (StatusBar) SysUiServiceProvider.getComponent(context, StatusBar.class)) == null) {
            return;
        }
        statusBar.closePanelsIfNeed();
    }
}
