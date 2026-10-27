package com.byd.vrassistant.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.Constant;
import java.lang.Thread;

/* JADX INFO: loaded from: classes2.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler INSTANCE = new CrashHandler();
    public static final String TAG = "CrashHandler";

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        RLog.e("YZS_ERR", "=============uncaughtException");
        Intent intent = new Intent();
        intent.setAction(Constant.ACTION_RELEASE_MIC);
        intent.putExtra("who", "bydvoice");
        intent.putExtra("show", false);
        BydVoiceApplication.getContext().sendBroadcast(intent);
        Process.killProcess(Process.myPid());
    }
}
