package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import android.text.TextUtils;
import com.android.systemui.statusbar.phone.StatusBar;
import com.byd.profile.activity.BaseActivity;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class BarReceiver extends BroadcastReceiver {
    public static final String ACTION = "com.android.systemui.action.byd.BarReceiver";
    public static final String ACTION_STATUS_BAR_UPDATE = "com.byd.ACTION_wallpaper_update";
    private static final String TAG = "BarReceiver";
    public int mWallpaperId = -1;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String str = TAG;
        BydLogHelper.logD(str, "BarReceiver action = " + action);
        if ("com.android.systemui.action.byd.BarReceiver".equals(action)) {
            boolean booleanExtra = intent.getBooleanExtra(BaseActivity.EXTRA_VISIBLE, true);
            BydLogHelper.logD(str, "visible:" + booleanExtra);
            if (booleanExtra) {
                SystemProperties.set(Constants.SYS_BAR_SYSTEMUI_KEY, Constants.SYS_BAR_SYSTEMUI_VALUE_ENABLE);
                return;
            } else {
                SystemProperties.set(Constants.SYS_BAR_SYSTEMUI_KEY, Constants.SYS_BAR_SYSTEMUI_VALUE_DISABLE);
                return;
            }
        }
        if (TextUtils.equals(ACTION_STATUS_BAR_UPDATE, action)) {
            int intExtra = intent.getIntExtra("wallpaper_id", -1);
            BydLogHelper.logD("wallpaperId = " + intExtra + " ");
            if (intExtra == -1 || intExtra != this.mWallpaperId) {
                BydLogHelper.logD("updateStatusBarIcon mWallpaperId = " + this.mWallpaperId);
                ((StatusBar) SysUiServiceProvider.getComponent(context, StatusBar.class)).updateStatusBarIcon();
            }
            this.mWallpaperId = intExtra;
        }
    }
}
