package c.b.h.e;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class e extends AtomicLong implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2282a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2283b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f2284c;

    public static final class a extends Thread {
        public a(Runnable runnable, String str) {
            super(runnable, str);
        }
    }

    public e(String str) {
        this.f2282a = str;
        this.f2283b = 5;
        this.f2284c = false;
    }

    public e(String str, int i, boolean z) {
        this.f2282a = str;
        this.f2283b = i;
        this.f2284c = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        String str = this.f2282a + '-' + incrementAndGet();
        Thread aVar = this.f2284c ? new a(runnable, str) : new Thread(runnable, str);
        aVar.setPriority(this.f2283b);
        aVar.setDaemon(true);
        return aVar;
    }

    @Override // java.util.concurrent.atomic.AtomicLong
    public String toString() {
        StringBuilder sbD = b.a.a.a.a.d("RxThreadFactory[");
        sbD.append(this.f2282a);
        sbD.append("]");
        return sbD.toString();
    }

    public e(String str, int i) {
        this.f2282a = str;
        this.f2283b = i;
        this.f2284c = false;
    }
}
