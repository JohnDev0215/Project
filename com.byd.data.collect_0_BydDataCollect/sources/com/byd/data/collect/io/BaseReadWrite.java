package com.byd.data.collect.io;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseReadWrite {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected com.byd.data.common.server.e.a f3148a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Handler f3149b;

    public BaseReadWrite(com.byd.data.common.server.e.a aVar, ReentrantReadWriteLock reentrantReadWriteLock) {
        this.f3148a = aVar;
        c(reentrantReadWriteLock);
        HandlerThread handlerThread = new HandlerThread(a());
        handlerThread.start();
        this.f3149b = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: com.byd.data.collect.io.a
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f3171b.b(message);
            }
        });
    }

    abstract String a();

    abstract boolean b(Message message);

    abstract void c(ReentrantReadWriteLock reentrantReadWriteLock);

    public void d() {
        Handler handler = this.f3149b;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            Looper looper = this.f3149b.getLooper();
            if (looper != null) {
                looper.quit();
            }
            this.f3148a = null;
            this.f3149b = null;
        }
    }
}
