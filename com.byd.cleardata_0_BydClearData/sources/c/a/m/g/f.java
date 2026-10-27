package c.a.m.g;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class f extends AtomicLong implements ThreadFactory {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final String f1879b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f1880c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final boolean f1881d;

    static final class a extends Thread {
        a(Runnable runnable, String str) {
            super(runnable, str);
        }
    }

    public f(String str) {
        this(str, 5, false);
    }

    public f(String str, int i) {
        this(str, i, false);
    }

    public f(String str, int i, boolean z) {
        this.f1879b = str;
        this.f1880c = i;
        this.f1881d = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        String str = this.f1879b + '-' + incrementAndGet();
        Thread aVar = this.f1881d ? new a(runnable, str) : new Thread(runnable, str);
        aVar.setPriority(this.f1880c);
        aVar.setDaemon(true);
        return aVar;
    }

    @Override // java.util.concurrent.atomic.AtomicLong
    public String toString() {
        return "RxThreadFactory[" + this.f1879b + "]";
    }
}
