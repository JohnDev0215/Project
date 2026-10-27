package com.byd.blockingqueue;

import java.util.concurrent.BlockingQueue;

/* JADX INFO: compiled from: TaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends Thread {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BlockingQueue<b> f3221b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3222c = true;

    public e(BlockingQueue<b> blockingQueue) {
        this.f3221b = blockingQueue;
    }

    public void a() {
        this.f3222c = false;
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (this.f3222c) {
            try {
                this.f3221b.take().run();
            } catch (InterruptedException unused) {
                if (!this.f3222c) {
                    interrupt();
                    return;
                }
            }
        }
    }
}
