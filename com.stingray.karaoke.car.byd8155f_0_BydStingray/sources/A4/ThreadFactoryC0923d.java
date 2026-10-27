package a4;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: a4.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ThreadFactoryC0923d implements ThreadFactory {
    public static final AtomicInteger d = new AtomicInteger(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ThreadGroup f11095a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AtomicInteger f11096b = new AtomicInteger(1);
    public final String c;

    public ThreadFactoryC0923d() {
        SecurityManager securityManager = System.getSecurityManager();
        this.f11095a = securityManager == null ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
        this.c = "lottie-" + d.getAndIncrement() + "-thread-";
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(this.f11095a, runnable, this.c + this.f11096b.getAndIncrement(), 0L);
        thread.setDaemon(false);
        thread.setPriority(10);
        return thread;
    }
}
