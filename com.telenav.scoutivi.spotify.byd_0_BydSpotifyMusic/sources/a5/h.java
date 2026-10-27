package a5;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: RxThreadFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends AtomicLong implements ThreadFactory {
    private static final long serialVersionUID = -7789753024099756196L;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final String f94e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final int f95g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    final boolean f96h;

    /* JADX INFO: compiled from: RxThreadFactory.java */
    static final class a extends Thread {
        a(Runnable runnable, String str) {
            super(runnable, str);
        }
    }

    public h(String str) {
        this(str, 5, false);
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        String str = this.f94e + '-' + incrementAndGet();
        Thread aVar = this.f96h ? new a(runnable, str) : new Thread(runnable, str);
        aVar.setPriority(this.f95g);
        aVar.setDaemon(true);
        return aVar;
    }

    @Override // java.util.concurrent.atomic.AtomicLong
    public String toString() {
        return "RxThreadFactory[" + this.f94e + "]";
    }

    public h(String str, int i7) {
        this(str, i7, false);
    }

    public h(String str, int i7, boolean z6) {
        this.f94e = str;
        this.f95g = i7;
        this.f96h = z6;
    }
}
