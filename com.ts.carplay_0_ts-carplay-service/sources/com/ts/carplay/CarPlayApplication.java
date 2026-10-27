package com.ts.carplay;

import android.app.Application;
import android.os.Process;
import com.ts.carplay.common.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public final class CarPlayApplication extends Application {
    private static final String TAG = "CarPlayApplication";

    @Override // android.app.Application
    public void onCreate() {
        Process.setThreadPriority(-8);
        super.onCreate();
        LogUtil.info(TAG, "onCreate start!");
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        LogUtil.info(TAG, "onTerminate start!");
    }
}
