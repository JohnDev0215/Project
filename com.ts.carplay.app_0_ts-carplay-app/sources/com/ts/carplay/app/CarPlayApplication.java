package com.ts.carplay.app;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Process;
import com.ts.carplay.utils.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayApplication extends Application {
    private static final String TAG = CarPlayApplication.class.getSimpleName();

    @Override // android.app.Application
    public void onCreate() {
        LogUtil.info(TAG, "App CarPlayApplication start!");
        Process.setThreadPriority(-8);
        super.onCreate();
        String packageName = getPackageName();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, packageName + ".service.CarPlayRemoteService"));
        startService(intent);
    }
}
