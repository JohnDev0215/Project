package r9;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class r extends L {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public L f21940e;

    public r(L l) {
        A7.m.f(l, "delegate");
        this.f21940e = l;
    }

    @Override // r9.L
    public final L a() {
        return this.f21940e.a();
    }

    @Override // r9.L
    public final L b() {
        return this.f21940e.b();
    }

    @Override // r9.L
    public final long c() {
        return this.f21940e.c();
    }

    @Override // r9.L
    public final L d(long j3) {
        return this.f21940e.d(j3);
    }

    @Override // r9.L
    public final boolean e() {
        return this.f21940e.e();
    }

    @Override // r9.L
    public final void f() throws InterruptedIOException {
        this.f21940e.f();
    }

    @Override // r9.L
    public final L g(long j3, TimeUnit timeUnit) {
        A7.m.f(timeUnit, "unit");
        return this.f21940e.g(j3, timeUnit);
    }

    @Override // r9.L
    public final long h() {
        return this.f21940e.h();
    }
}
