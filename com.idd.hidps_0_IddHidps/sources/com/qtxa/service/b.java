package com.qtxa.service;

import android.os.Handler;
import android.os.HandlerThread;

/* JADX INFO: loaded from: classes.dex */
final class b extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static b f167a;

    public static abstract class a<T1, T2> implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected T1 f168a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected T2 f169b;

        public a(T1 t1, T2 t2) {
            this.f168a = t1;
            this.f169b = t2;
        }
    }

    /* JADX INFO: renamed from: com.qtxa.service.b$b, reason: collision with other inner class name */
    public static abstract class AbstractRunnableC0021b<T1, T2, T3> implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected T1 f170a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected T2 f171b;
        protected T3 c;

        public AbstractRunnableC0021b(T1 t1, T2 t2, T3 t3) {
            this.f170a = t1;
            this.f171b = t2;
            this.c = t3;
        }
    }

    private b(HandlerThread handlerThread) {
        super(handlerThread.getLooper());
    }

    public static b a() {
        if (f167a == null) {
            synchronized (b.class) {
                HandlerThread handlerThread = new HandlerThread("idps-request");
                handlerThread.start();
                f167a = new b(handlerThread);
            }
        }
        return f167a;
    }
}
