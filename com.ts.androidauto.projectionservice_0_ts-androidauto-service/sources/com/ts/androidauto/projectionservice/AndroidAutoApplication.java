package com.ts.androidauto.projectionservice;

import android.app.Application;
import android.os.Process;
import com.ts.androidauto.fsdk.common.LogUtil;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidAutoApplication extends Application {
    private static final String TAG = "AndroidAutoApplication";

    @Override // android.app.Application
    public void onCreate() {
        Process.setThreadPriority(-8);
        super.onCreate();
        LogUtil.init();
        LogUtil.info(TAG, "AndroidAutoApplication start!");
    }

    @Override // android.app.Application
    public void onTerminate() {
        super.onTerminate();
        LogUtil.info(TAG, "AndroidAutoApplication terminate!");
    }
}
