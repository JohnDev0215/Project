package j4;

import io.sentry.android.core.M;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: j4.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ThreadFactoryC1542c implements ThreadFactory {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ThreadFactoryC1541b f17008a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f17009b;
    public final d c;
    public final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AtomicInteger f17010e;

    public ThreadFactoryC1542c(ThreadFactoryC1541b threadFactoryC1541b, String str, boolean z6) {
        d dVar = d.f17011a;
        this.f17010e = new AtomicInteger();
        this.f17008a = threadFactoryC1541b;
        this.f17009b = str;
        this.c = dVar;
        this.d = z6;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        M m10 = new M(this, 1, runnable);
        this.f17008a.getClass();
        C1540a c1540a = new C1540a(m10);
        c1540a.setName("glide-" + this.f17009b + "-thread-" + this.f17010e.getAndIncrement());
        return c1540a;
    }
}
