package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.android.systemui.volume.VolumeComponent;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class LauncherSelectReceiver extends BroadcastReceiver {
    public static final String ACTION = "com.byd.ACTION_launcher_settings";
    public static final String KEY = "launcher_settings_dialog";
    private static final String TAG = "LauncherSelectReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = TAG;
        BydLogHelper.logD(str, " action = " + intent.getAction());
        if (ACTION.equals(intent.getAction())) {
            if (!AppUtils.isSupportMapDesktop()) {
                BydLogHelper.logD("isSupportMapDesktop false");
                return;
            }
            int intExtra = intent.getIntExtra(KEY, 0);
            VolumeComponent volumeComponent = (VolumeComponent) SysUiServiceProvider.getComponent(context, VolumeComponent.class);
            BydLogHelper.logD(str, "settings_dialog:" + intExtra);
            if (volumeComponent == null) {
                return;
            }
            if (intExtra == 1) {
                volumeComponent.showLauncherDialog();
            } else if (intExtra == 2) {
                volumeComponent.dismissLauncherDialog();
            }
        }
    }
}
