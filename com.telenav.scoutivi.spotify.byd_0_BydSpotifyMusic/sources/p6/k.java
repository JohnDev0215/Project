package p6;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ForwardingTimeout.kt */
/* JADX INFO: loaded from: classes.dex */
public class k extends b0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b0 f8876f;

    public k(b0 delegate) {
        kotlin.jvm.internal.l.e(delegate, "delegate");
        this.f8876f = delegate;
    }

    @Override // p6.b0
    public b0 a() {
        return this.f8876f.a();
    }

    @Override // p6.b0
    public b0 b() {
        return this.f8876f.b();
    }

    @Override // p6.b0
    public long c() {
        return this.f8876f.c();
    }

    @Override // p6.b0
    public b0 d(long j7) {
        return this.f8876f.d(j7);
    }

    @Override // p6.b0
    public boolean e() {
        return this.f8876f.e();
    }

    @Override // p6.b0
    public void f() throws InterruptedIOException {
        this.f8876f.f();
    }

    @Override // p6.b0
    public b0 g(long j7, TimeUnit unit) {
        kotlin.jvm.internal.l.e(unit, "unit");
        return this.f8876f.g(j7, unit);
    }

    public final b0 i() {
        return this.f8876f;
    }

    public final k j(b0 delegate) {
        kotlin.jvm.internal.l.e(delegate, "delegate");
        this.f8876f = delegate;
        return this;
    }
}
