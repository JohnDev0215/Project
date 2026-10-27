package f;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ForwardingTimeout.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public r f3706e;

    public h(r rVar) {
        if (rVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f3706e = rVar;
    }

    @Override // f.r
    public r a() {
        return this.f3706e.a();
    }

    @Override // f.r
    public r b() {
        return this.f3706e.b();
    }

    @Override // f.r
    public long c() {
        return this.f3706e.c();
    }

    @Override // f.r
    public r d(long j) {
        return this.f3706e.d(j);
    }

    @Override // f.r
    public boolean e() {
        return this.f3706e.e();
    }

    @Override // f.r
    public void f() throws IOException {
        this.f3706e.f();
    }

    @Override // f.r
    public r g(long j, TimeUnit timeUnit) {
        return this.f3706e.g(j, timeUnit);
    }

    public final r i() {
        return this.f3706e;
    }

    public final h j(r rVar) {
        if (rVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f3706e = rVar;
        return this;
    }
}
