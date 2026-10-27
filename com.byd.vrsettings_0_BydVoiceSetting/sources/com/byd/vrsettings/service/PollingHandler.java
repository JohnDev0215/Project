package com.byd.vrsettings.service;

import android.os.Handler;
import android.os.Looper;
import com.byd.vrsettings.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class PollingHandler {
    private Consumer mConsumer;
    private long mInterval;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable runnable = new Runnable() { // from class: com.byd.vrsettings.service.PollingHandler.1
        @Override // java.lang.Runnable
        public void run() {
            RLog.i("PollingHandler", "run...");
            PollingHandler.this.mConsumer.next(0);
            PollingHandler.this.handler.postDelayed(this, PollingHandler.this.mInterval);
        }
    };

    public interface Consumer<T> {
        void next(T t);
    }

    public static PollingHandler newTask(long j, Consumer consumer) {
        return new PollingHandler().startPollingTask(j, consumer);
    }

    public static PollingHandler newTaskDefault(Consumer consumer) {
        return new PollingHandler().startPollingTask(2000L, consumer);
    }

    private PollingHandler startPollingTask(long j, Consumer consumer) {
        this.mInterval = j;
        this.mConsumer = consumer;
        this.handler.postDelayed(this.runnable, 0L);
        return this;
    }

    public void stopTask() {
        Runnable runnable;
        Handler handler = this.handler;
        if (handler == null || (runnable = this.runnable) == null) {
            return;
        }
        handler.removeCallbacks(runnable);
    }
}
