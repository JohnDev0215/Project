package p;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class qw0 extends tl2 {
    public tl2 e;

    public qw0(tl2 tl2Var) {
        v03.j(tl2Var, "delegate");
        this.e = tl2Var;
    }

    @Override // p.tl2
    public final tl2 a() {
        return this.e.a();
    }

    @Override // p.tl2
    public final tl2 b() {
        return this.e.b();
    }

    @Override // p.tl2
    public final long c() {
        return this.e.c();
    }

    @Override // p.tl2
    public final tl2 d(long j) {
        return this.e.d(j);
    }

    @Override // p.tl2
    public final boolean e() {
        return this.e.e();
    }

    @Override // p.tl2
    public final void f() throws InterruptedIOException {
        this.e.f();
    }

    @Override // p.tl2
    public final tl2 g(long j, TimeUnit timeUnit) {
        v03.j(timeUnit, "unit");
        return this.e.g(j, timeUnit);
    }

    @Override // p.tl2
    public final long h() {
        return this.e.h();
    }
}
