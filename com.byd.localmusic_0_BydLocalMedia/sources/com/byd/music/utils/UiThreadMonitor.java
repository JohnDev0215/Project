package com.byd.music.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Printer;
import com.bydux.api.common.tools.cmd.BydAdbHelper;

/* JADX INFO: loaded from: classes5.dex */
public class UiThreadMonitor {
    private static final int MAX_BLOCK_TIME = 500;
    private static final String TAG = "UiThreadMonitor";
    private static HandlerThread monitorThread;

    public static void start() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        monitorThread = handlerThread;
        handlerThread.start();
        final Handler handler = new Handler(monitorThread.getLooper());
        final MonitorTask monitorTask = new MonitorTask();
        Looper.getMainLooper().setMessageLogging(new Printer() { // from class: com.byd.music.utils.UiThreadMonitor.1
            private boolean isStart = true;

            @Override // android.util.Printer
            public void println(String str) {
                if (this.isStart) {
                    handler.postDelayed(monitorTask, 500L);
                    this.isStart = false;
                } else {
                    handler.removeCallbacks(monitorTask);
                    this.isStart = true;
                }
            }
        });
    }

    public static void stop() {
        HandlerThread handlerThread = monitorThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        monitorThread = null;
    }

    private static class MonitorTask implements Runnable {
        private MonitorTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append("has blocked: ");
            sb.append(BydAdbHelper.COMMAND_LINE_END);
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append(stackTraceElement.toString());
                sb.append(BydAdbHelper.COMMAND_LINE_END);
            }
            LogUtil.e(" MonitorTask --> run:" + sb.toString());
        }
    }
}
