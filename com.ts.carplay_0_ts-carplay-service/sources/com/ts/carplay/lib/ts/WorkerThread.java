package com.ts.carplay.lib.ts;

import android.os.Looper;

/* JADX INFO: loaded from: classes.dex */
class WorkerThread implements Runnable {
    private final Object mLock = new Object();
    private Looper mLooper;

    WorkerThread(String name) {
        Thread threadTmp = new Thread(null, this, name);
        threadTmp.start();
        synchronized (this.mLock) {
            while (this.mLooper == null) {
                try {
                    this.mLock.wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    Looper getLooper() {
        return this.mLooper;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.mLock) {
            Looper.prepare();
            this.mLooper = Looper.myLooper();
            this.mLock.notifyAll();
        }
        Looper.loop();
    }

    void quit() {
        this.mLooper.quit();
    }
}
