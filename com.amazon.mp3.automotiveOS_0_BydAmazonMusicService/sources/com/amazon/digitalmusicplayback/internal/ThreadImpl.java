package com.amazon.digitalmusicplayback.internal;

import androidx.annotation.NonNull;
import com.amazon.digitalmusicplayback.internal.LoggingFacade;

/* JADX INFO: loaded from: classes2.dex */
public class ThreadImpl extends PlatformThread {
    private LoggingFacade.Logger mLogger;
    private Thread mThread;

    ThreadImpl(@NonNull String str, @NonNull final ThreadRunnable threadRunnable, LoggingFacade loggingFacade) {
        this.mLogger = loggingFacade.currentSourceFileLog();
        Thread thread = new Thread(new Runnable() { // from class: com.amazon.digitalmusicplayback.internal.ThreadImpl.1
            @Override // java.lang.Runnable
            public void run() {
                threadRunnable.run();
            }
        }, str);
        this.mThread = thread;
        thread.start();
    }

    @Override // com.amazon.digitalmusicplayback.internal.PlatformThread
    public void join() {
        try {
            this.mThread.join();
        } catch (InterruptedException unused) {
            this.mLogger.error("Joining thread was interrupted: %s id=%d", this.mThread.getName(), Long.valueOf(this.mThread.getId()));
        }
    }

    @Override // com.amazon.digitalmusicplayback.internal.PlatformThread
    public void elevatePriority(int i) {
        this.mThread.setPriority(10 - i);
    }
}
