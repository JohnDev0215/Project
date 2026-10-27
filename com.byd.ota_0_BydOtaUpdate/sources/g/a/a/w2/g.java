package g.a.a.w2;

import g.a.a.o0;

/* JADX INFO: loaded from: classes.dex */
public class g extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.r f4119c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public w f4120d;
    public a s;
    public o0 x;

    public g(g.a.a.r rVar) {
        this.f4119c = rVar;
        if (rVar.size() != 3) {
            throw new IllegalArgumentException("sequence wrong size for a certificate");
        }
        this.f4120d = w.i(rVar.q(0));
        this.s = a.h(rVar.q(1));
        this.x = o0.t(rVar.q(2));
    }

    public static g h(Object obj) {
        if (obj instanceof g) {
            return (g) obj;
        }
        if (obj != null) {
            return new g(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        return this.f4119c;
    }

    public x g() {
        return this.f4120d.g();
    }

    public g.a.a.v2.c i() {
        return this.f4120d.j();
    }

    public g.a.a.j j() {
        return this.f4120d.l();
    }

    public o0 k() {
        return this.x;
    }

    public a l() {
        return this.s;
    }

    public x m() {
        return this.f4120d.n();
    }

    public g.a.a.v2.c n() {
        return this.f4120d.o();
    }

    public u o() {
        return this.f4120d.p();
    }

    public w p() {
        return this.f4120d;
    }

    public int q() {
        return this.f4120d.r();
    }
}
