package com.android.systemui.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import android.util.Printer;
import com.bydux.api.common.tools.cmd.BydAdbHelper;

/* JADX INFO: loaded from: classes.dex */
public class BlockDetectByPrinter {
    public static void start() {
        Looper.getMainLooper().setMessageLogging(new Printer() { // from class: com.android.systemui.util.BlockDetectByPrinter.1
            private static final String END = "<<<<< Finished";
            private static final String START = ">>>>> Dispatching";

            @Override // android.util.Printer
            public void println(String x) {
                if (x.startsWith(START)) {
                    LogMonitor.getInstance().startMonitor();
                }
                if (x.startsWith(END)) {
                    LogMonitor.getInstance().removeMonitor();
                }
            }
        });
    }

    public static final class LogMonitor {
        private static final String TAG = "BlockDetectByPrinter";
        private static final long TIME_BLOCK = 300;
        private static long start;
        private final Handler mIoHandler;
        private static final LogMonitor sInstance = new LogMonitor();
        private static final Runnable mLogRunnable = new Runnable() { // from class: com.android.systemui.util.BlockDetectByPrinter.LogMonitor.1
            @Override // java.lang.Runnable
            public void run() {
                StringBuilder sb = new StringBuilder();
                for (StackTraceElement stackTraceElement : Looper.getMainLooper().getThread().getStackTrace()) {
                    sb.append(stackTraceElement.toString());
                    sb.append(BydAdbHelper.COMMAND_LINE_END);
                }
                Log.w(LogMonitor.TAG, "--------------------------------");
                Log.w(LogMonitor.TAG, "task " + (System.currentTimeMillis() - LogMonitor.start) + " ms");
                Log.w(LogMonitor.TAG, sb.toString());
            }
        };

        private LogMonitor() {
            HandlerThread handlerThread = new HandlerThread("log");
            handlerThread.start();
            this.mIoHandler = new Handler(handlerThread.getLooper());
        }

        public static LogMonitor getInstance() {
            return sInstance;
        }

        public void startMonitor() {
            start = System.currentTimeMillis();
            this.mIoHandler.postDelayed(mLogRunnable, 300L);
        }

        public void removeMonitor() {
            this.mIoHandler.removeCallbacks(mLogRunnable);
        }
    }
}
