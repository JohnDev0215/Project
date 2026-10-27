package c6;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ConnectionPool.kt */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h6.h f3792a;

    public k(h6.h delegate) {
        kotlin.jvm.internal.l.e(delegate, "delegate");
        this.f3792a = delegate;
    }

    public final h6.h a() {
        return this.f3792a;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public k(int i7, long j7, TimeUnit timeUnit) {
        this(new h6.h(g6.e.f6577h, i7, j7, timeUnit));
        kotlin.jvm.internal.l.e(timeUnit, "timeUnit");
    }

    public k() {
        this(5, 5L, TimeUnit.MINUTES);
    }
}
