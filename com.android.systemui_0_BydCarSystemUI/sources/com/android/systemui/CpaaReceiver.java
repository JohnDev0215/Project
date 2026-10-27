package com.android.systemui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.statusbar.policy.CarController;

/* JADX INFO: loaded from: classes.dex */
public class CpaaReceiver extends BroadcastReceiver {
    public static final String ACTION_ANDROID_AUTO_START_FINISH = "com.ts.androidauto.start.finish";
    public static final String ACTION_CARPLAY_START_FINISH = "com.ts.carplay.start.finish";
    private static final String TAG = "CpaaReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i(TAG, "onReceive action:" + action);
        if (TextUtils.equals(action, ACTION_CARPLAY_START_FINISH)) {
            SystemProperties.set("sys.sysui.cpaa.connect", "1");
            ((CarController) Dependency.get(CarController.class)).initCarplay(true);
        } else if (TextUtils.equals(action, ACTION_ANDROID_AUTO_START_FINISH)) {
            SystemProperties.set("sys.sysui.cpaa.connect", "1");
            ((CarController) Dependency.get(CarController.class)).initAndroidAuto(true);
        }
    }
}
