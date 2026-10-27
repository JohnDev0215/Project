package com.ts.dm.service;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import com.ts.dm.service.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class DeviceManagerServiceApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        LogUtil.info("DeviceManagerServiceApplication", "Application onCreate");
        String packageName = getPackageName();
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, packageName + ".DeviceManagerService"));
        startService(intent);
    }
}
