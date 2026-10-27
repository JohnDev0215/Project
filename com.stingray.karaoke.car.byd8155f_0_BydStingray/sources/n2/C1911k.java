package n2;

import io.sentry.android.core.C1438n;

/* JADX INFO: renamed from: n2.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1911k implements H {
    public final C1438n l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final D f19681m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public AbstractC1905e f19682n;
    public H o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f19683p = true;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f19684q;

    public C1911k(D d, h2.v vVar) {
        this.f19681m = d;
        this.l = new C1438n(vVar);
    }

    @Override // n2.H
    public final boolean a() {
        if (this.f19683p) {
            this.l.getClass();
            return false;
        }
        H h10 = this.o;
        h10.getClass();
        return h10.a();
    }

    @Override // n2.H
    public final long c() {
        if (this.f19683p) {
            return this.l.c();
        }
        H h10 = this.o;
        h10.getClass();
        return h10.c();
    }

    @Override // n2.H
    public final void f(e2.P p10) {
        H h10 = this.o;
        if (h10 != null) {
            h10.f(p10);
            p10 = this.o.j();
        }
        this.l.f(p10);
    }

    @Override // n2.H
    public final e2.P j() {
        H h10 = this.o;
        return h10 != null ? h10.j() : (e2.P) this.l.f16247p;
    }
}
