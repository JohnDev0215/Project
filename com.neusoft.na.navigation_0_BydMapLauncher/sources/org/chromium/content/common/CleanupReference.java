package org.chromium.content.common;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;

/* JADX INFO: loaded from: classes3.dex */
public class CleanupReference extends WeakReference<Object> {
    private static final int ADD_REF = 1;
    private static final boolean DEBUG = false;
    private static final int REMOVE_REF = 2;
    private static final String TAG = "cr.CleanupReference";
    private static final Thread sReaperThread;
    private static Set<CleanupReference> sRefs;
    private Runnable mCleanupTask;
    private static ReferenceQueue<Object> sGcQueue = new ReferenceQueue<>();
    private static Object sCleanupMonitor = new Object();

    static {
        Thread thread = new Thread(TAG) { // from class: org.chromium.content.common.CleanupReference.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                while (true) {
                    try {
                        CleanupReference cleanupReference = (CleanupReference) CleanupReference.sGcQueue.remove();
                        synchronized (CleanupReference.sCleanupMonitor) {
                            Message.obtain(LazyHolder.sHandler, 2, cleanupReference).sendToTarget();
                            CleanupReference.sCleanupMonitor.wait(500L);
                        }
                    } catch (Exception e) {
                        Log.e(CleanupReference.TAG, "Queue remove exception:", e);
                    }
                }
            }
        };
        sReaperThread = thread;
        thread.setDaemon(true);
        thread.start();
        sRefs = new HashSet();
    }

    private static class LazyHolder {
        static final Handler sHandler = new Handler(ThreadUtils.getUiThreadLooper()) { // from class: org.chromium.content.common.CleanupReference.LazyHolder.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                try {
                    TraceEvent.begin("CleanupReference.LazyHolder.handleMessage");
                    CleanupReference cleanupReference = (CleanupReference) message.obj;
                    int i = message.what;
                    if (i == 1) {
                        CleanupReference.sRefs.add(cleanupReference);
                    } else if (i != 2) {
                        Log.e(CleanupReference.TAG, "Bad message=%d", Integer.valueOf(message.what));
                    } else {
                        cleanupReference.runCleanupTaskInternal();
                    }
                } finally {
                }
                synchronized (CleanupReference.sCleanupMonitor) {
                    while (true) {
                        CleanupReference cleanupReference2 = (CleanupReference) CleanupReference.sGcQueue.poll();
                        if (cleanupReference2 != null) {
                            cleanupReference2.runCleanupTaskInternal();
                        } else {
                            CleanupReference.sCleanupMonitor.notifyAll();
                        }
                        TraceEvent.end("CleanupReference.LazyHolder.handleMessage");
                    }
                }
            }
        };

        private LazyHolder() {
        }
    }

    public CleanupReference(Object obj, Runnable runnable) {
        super(obj, sGcQueue);
        this.mCleanupTask = runnable;
        handleOnUiThread(1);
    }

    public void cleanupNow() {
        handleOnUiThread(2);
    }

    private void handleOnUiThread(int i) {
        Message messageObtain = Message.obtain(LazyHolder.sHandler, i, this);
        if (Looper.myLooper() == messageObtain.getTarget().getLooper()) {
            messageObtain.getTarget().handleMessage(messageObtain);
            messageObtain.recycle();
        } else {
            messageObtain.sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runCleanupTaskInternal() {
        sRefs.remove(this);
        Runnable runnable = this.mCleanupTask;
        if (runnable != null) {
            runnable.run();
            this.mCleanupTask = null;
        }
        clear();
    }
}
