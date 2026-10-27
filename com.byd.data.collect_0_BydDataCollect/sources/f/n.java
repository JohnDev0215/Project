package f;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ConnectionPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final f.k0.h.g f5469a;

    public n() {
        this(5, 5L, TimeUnit.MINUTES);
    }

    public n(int i2, long j, TimeUnit timeUnit) {
        this.f5469a = new f.k0.h.g(i2, j, timeUnit);
    }
}
