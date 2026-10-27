package com.byd.music.common;

import java.lang.ref.SoftReference;

/* JADX INFO: loaded from: classes5.dex */
public class WeakRunnable implements Runnable {
    private SoftReference<RunnableCallBack> callBack;

    public interface RunnableCallBack {
        void run();
    }

    public WeakRunnable(RunnableCallBack runnableCallBack) {
        this.callBack = new SoftReference<>(runnableCallBack);
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.callBack.get() != null) {
            this.callBack.get().run();
            this.callBack.clear();
        }
    }
}
