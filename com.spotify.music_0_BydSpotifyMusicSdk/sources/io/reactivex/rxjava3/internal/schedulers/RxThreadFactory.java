package io.reactivex.rxjava3.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public final class RxThreadFactory extends AtomicLong implements ThreadFactory {
    private static final long serialVersionUID = -7789753024099756196L;
    final boolean nonBlocking;
    final String prefix;
    final int priority;

    public RxThreadFactory(String str) {
        this(5, str, false);
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        String str = this.prefix + '-' + incrementAndGet();
        Thread qVar = this.nonBlocking ? new q(runnable, str) : new Thread(runnable, str);
        qVar.setPriority(this.priority);
        qVar.setDaemon(true);
        return qVar;
    }

    @Override // java.util.concurrent.atomic.AtomicLong
    public final String toString() {
        return u4.m(new StringBuilder("RxThreadFactory["), this.prefix, "]");
    }

    public RxThreadFactory(int i, String str, boolean z) {
        this.prefix = str;
        this.priority = i;
        this.nonBlocking = z;
    }
}
