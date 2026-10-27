package com.android.providers.contacts;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class ContactsTaskScheduler {
    public static final boolean VERBOSE_LOGGING = AbstractContactsProvider.VERBOSE_LOGGING;
    private MyHandler mHandler;
    private final Object mLock;
    private final String mName;
    private final Runnable mQuitter;
    private final int mShutdownTimeoutSeconds;
    private HandlerThread mThread;
    private final AtomicInteger mThreadSequenceNumber;

    public abstract void onPerformTask(int i, Object obj);

    public ContactsTaskScheduler(String str) {
        this(str, 60);
    }

    protected ContactsTaskScheduler(String str, int i) {
        this.mThreadSequenceNumber = new AtomicInteger();
        this.mLock = new Object();
        this.mQuitter = new Runnable() { // from class: com.android.providers.contacts.-$$Lambda$ContactsTaskScheduler$JvWut3Vtf5yiF7GIjcOt_48UpuQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$ContactsTaskScheduler();
            }
        };
        this.mName = str;
        this.mShutdownTimeoutSeconds = i;
    }

    private class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (ContactsTaskScheduler.VERBOSE_LOGGING) {
                Log.v("ContactsTaskScheduler", "[" + ContactsTaskScheduler.this.mName + "] " + ContactsTaskScheduler.this.mThread + " dispatching " + message.what);
            }
            ContactsTaskScheduler.this.onPerformTask(message.what, message.obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$ContactsTaskScheduler() {
        synchronized (this.mLock) {
            stopThread(false);
        }
    }

    private boolean isRunning() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mThread != null;
        }
        return z;
    }

    @VisibleForTesting
    public void scheduleTask(int i) {
        scheduleTask(i, null);
    }

    @VisibleForTesting
    public void scheduleTask(int i, Object obj) {
        synchronized (this.mLock) {
            if (!isRunning()) {
                HandlerThread handlerThread = new HandlerThread("Worker-" + this.mThreadSequenceNumber.incrementAndGet());
                this.mThread = handlerThread;
                handlerThread.start();
                this.mHandler = new MyHandler(this.mThread.getLooper());
                if (VERBOSE_LOGGING) {
                    Log.v("ContactsTaskScheduler", "[" + this.mName + "] " + this.mThread + " started.");
                }
            }
            if (obj == null) {
                this.mHandler.sendEmptyMessage(i);
            } else {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(i, obj));
            }
            this.mHandler.removeCallbacks(this.mQuitter);
            this.mHandler.postDelayed(this.mQuitter, this.mShutdownTimeoutSeconds * 1000);
        }
    }

    @VisibleForTesting
    public void shutdownForTest() {
        stopThread(true);
    }

    private void stopThread(boolean z) {
        synchronized (this.mLock) {
            if (VERBOSE_LOGGING) {
                Log.v("ContactsTaskScheduler", "[" + this.mName + "] " + this.mThread + " stopping...");
            }
            if (this.mThread != null) {
                this.mThread.quit();
                if (z) {
                    try {
                        this.mThread.join();
                    } catch (InterruptedException unused) {
                    }
                }
            }
            this.mThread = null;
            this.mHandler = null;
        }
    }

    @VisibleForTesting
    public int getThreadSequenceNumber() {
        return this.mThreadSequenceNumber.get();
    }

    @VisibleForTesting
    public boolean isRunningForTest() {
        return isRunning();
    }
}
