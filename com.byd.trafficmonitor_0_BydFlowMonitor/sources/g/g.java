package g;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ForwardingTimeout.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public q f5474e;

    public g(q qVar) {
        if (qVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f5474e = qVar;
    }

    @Override // g.q
    public q a() {
        return this.f5474e.a();
    }

    @Override // g.q
    public q b() {
        return this.f5474e.b();
    }

    @Override // g.q
    public long c() {
        return this.f5474e.c();
    }

    @Override // g.q
    public q d(long j) {
        return this.f5474e.d(j);
    }

    @Override // g.q
    public boolean e() {
        return this.f5474e.e();
    }

    @Override // g.q
    public void f() throws IOException {
        this.f5474e.f();
    }

    @Override // g.q
    public q g(long j, TimeUnit timeUnit) {
        return this.f5474e.g(j, timeUnit);
    }

    public final q i() {
        return this.f5474e;
    }

    public final g j(q qVar) {
        if (qVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f5474e = qVar;
        return this;
    }
}
