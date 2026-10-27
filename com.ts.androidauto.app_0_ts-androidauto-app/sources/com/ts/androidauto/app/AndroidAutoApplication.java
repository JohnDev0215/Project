package com.ts.androidauto.app;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Process;
import com.ts.androidauto.app.common.LogUtil;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidAutoApplication extends Application {
    private static final String TAG = AndroidAutoApplication.class.getSimpleName();

    @Override // android.app.Application
    public void onCreate() {
        Process.setThreadPriority(-8);
        super.onCreate();
        LogUtil.info(TAG, "App AndroidAutoApplication start!");
        String packageName = getPackageName();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, packageName + ".AndroidAutoRemoteUiService"));
        startService(intent);
    }
}
