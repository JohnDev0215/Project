package com.byd.music.crashHandle;

import android.os.Process;
import com.byd.music.utils.LogUtil;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes5.dex */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    private ExecutorService mExecutorService = Executors.newSingleThreadExecutor();

    public static void setCrashHandler(boolean z) {
        if (z) {
            Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
        } else {
            Thread.setDefaultUncaughtExceptionHandler(null);
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        th.printStackTrace();
        handleException(thread.getName(), th);
        try {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            this.mDefaultHandler.uncaughtException(thread, th);
            exit();
        }
    }

    private void handleException(String str, final Throwable th) {
        if (th == null) {
            return;
        }
        this.mExecutorService.execute(new Runnable() { // from class: com.byd.music.crashHandle.CrashHandler.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.e("媒体中心发生崩溃，详细信息如下：" + CrashHandler.getExceptionString(th));
            }
        });
    }

    private void exit() {
        Process.killProcess(Process.myPid());
        System.exit(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getExceptionString(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        sb.append("======================================Exception Info======================================\n");
        sb.append(th.toString());
        sb.append(BydAdbHelper.COMMAND_LINE_END);
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            sb.append(stackTraceElement.toString());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            sb.append("【Caused by】: ");
            sb.append(cause.toString());
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            for (StackTraceElement stackTraceElement2 : cause.getStackTrace()) {
                sb.append(stackTraceElement2.toString());
                sb.append(BydAdbHelper.COMMAND_LINE_END);
            }
        }
        sb.append("==============================================================================");
        return sb.toString();
    }
}
