package B2;

import e2.C1207o;
import e2.C1208p;
import h2.AbstractC1346c;
import u2.T;

/* JADX INFO: loaded from: classes.dex */
public final class H implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f512a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f513b;
    public final String c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f514e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public T f515f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public L f516g;

    public H(String str, int i7, int i10) {
        this.f512a = i7;
        this.f513b = i10;
        this.c = str;
    }

    @Override // B2.r
    public final void a() {
    }

    @Override // B2.r
    public final void b(t tVar) {
        T t10 = (T) tVar;
        this.f515f = t10;
        L lU = t10.u(1024, 4);
        this.f516g = lU;
        C1207o c1207o = new C1207o();
        c1207o.f14246m = e2.L.l(this.c);
        lU.d(new C1208p(c1207o));
        this.f515f.e();
        this.f515f.w(new I());
        this.f514e = 1;
    }

    @Override // B2.r
    public final int c(s sVar, v vVar) {
        int i7 = this.f514e;
        if (i7 != 1) {
            if (i7 == 2) {
                return -1;
            }
            throw new IllegalStateException();
        }
        L l = this.f516g;
        l.getClass();
        int iC = l.c(sVar, 1024, true);
        if (iC == -1) {
            this.f514e = 2;
            this.f516g.b(0L, 1, this.d, 0, null);
            this.d = 0;
        } else {
            this.d += iC;
        }
        return 0;
    }

    @Override // B2.r
    public final void e(long j3, long j10) {
        if (j3 == 0 || this.f514e == 1) {
            this.f514e = 1;
            this.d = 0;
        }
    }

    @Override // B2.r
    public final boolean f(s sVar) {
        int i7 = this.f513b;
        int i10 = this.f512a;
        AbstractC1346c.j((i10 == -1 || i7 == -1) ? false : true);
        h2.t tVar = new h2.t(i7);
        ((C0073m) sVar).k(tVar.f15241a, 0, i7, false);
        return tVar.B() == i10;
    }
}
