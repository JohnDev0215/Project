package com.byd.card.drag;

import android.os.Handler;

/* JADX INFO: loaded from: classes.dex */
public class CancellableQueueTimer implements Runnable {
    private Runnable callback;
    private Handler handler;

    public CancellableQueueTimer(Handler handler, int delay, Runnable runnable) {
        this.handler = handler;
        handler.postDelayed(this, delay);
        this.callback = runnable;
    }

    public void cancel() {
        Handler handler = this.handler;
        if (handler != null) {
            Runnable runnable = this.callback;
            if (runnable != null) {
                handler.removeCallbacks(runnable);
            }
            this.handler = null;
        }
        this.callback = null;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.handler = null;
        Runnable runnable = this.callback;
        if (runnable != null) {
            runnable.run();
            this.callback = null;
        }
    }
}
