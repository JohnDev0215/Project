package com.ts.qnxapp;

import android.app.Application;
import android.content.Intent;
import com.ts.qnxapp.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class QnxMessageApp extends Application {
    private static final String TAG = "QnxMessageApp";

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        LogUtil.logI(TAG, "App onCreate called");
        Intent intent = new Intent(this, (Class<?>) QnxMessageService.class);
        startService(intent);
    }
}
