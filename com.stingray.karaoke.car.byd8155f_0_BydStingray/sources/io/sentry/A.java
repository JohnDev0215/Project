package io.sentry;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: loaded from: classes.dex */
public final class A implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f15810a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f15811b;

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        switch (this.f15810a) {
            case 0:
                StringBuilder sb = new StringBuilder("SentryHostnameCache-");
                int i7 = this.f15811b;
                this.f15811b = i7 + 1;
                sb.append(i7);
                Thread thread = new Thread(runnable, sb.toString());
                thread.setDaemon(true);
                return thread;
            case 1:
                StringBuilder sb2 = new StringBuilder("SentryExecutorServiceThreadFactory-");
                int i10 = this.f15811b;
                this.f15811b = i10 + 1;
                sb2.append(i10);
                Thread thread2 = new Thread(runnable, sb2.toString());
                thread2.setDaemon(true);
                return thread2;
            case 2:
                A7.m.f(runnable, "r");
                StringBuilder sb3 = new StringBuilder("SentryReplayRecorder-");
                int i11 = this.f15811b;
                this.f15811b = i11 + 1;
                sb3.append(i11);
                Thread thread3 = new Thread(runnable, sb3.toString());
                thread3.setDaemon(true);
                return thread3;
            case 3:
                A7.m.f(runnable, "r");
                StringBuilder sb4 = new StringBuilder("SentryWindowRecorder-");
                int i12 = this.f15811b;
                this.f15811b = i12 + 1;
                sb4.append(i12);
                Thread thread4 = new Thread(runnable, sb4.toString());
                thread4.setDaemon(true);
                return thread4;
            case 4:
                A7.m.f(runnable, "r");
                StringBuilder sb5 = new StringBuilder("SentryReplayIntegration-");
                int i13 = this.f15811b;
                this.f15811b = i13 + 1;
                sb5.append(i13);
                Thread thread5 = new Thread(runnable, sb5.toString());
                thread5.setDaemon(true);
                return thread5;
            case 5:
                A7.m.f(runnable, "r");
                StringBuilder sb6 = new StringBuilder("SentryReplayPersister-");
                int i14 = this.f15811b;
                this.f15811b = i14 + 1;
                sb6.append(i14);
                Thread thread6 = new Thread(runnable, sb6.toString());
                thread6.setDaemon(true);
                return thread6;
            default:
                StringBuilder sb7 = new StringBuilder("SentryAsyncConnection-");
                int i15 = this.f15811b;
                this.f15811b = i15 + 1;
                sb7.append(i15);
                Thread thread7 = new Thread(runnable, sb7.toString());
                thread7.setDaemon(true);
                return thread7;
        }
    }
}
