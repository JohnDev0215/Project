package g.a.a.w2;

import g.a.a.c1;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class m extends g.a.a.l {
    public g.a.a.m f4;
    public boolean g4;
    public g.a.a.n h4;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final g.a.a.m f4131c = new g.a.a.m("2.5.29.9").u();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final g.a.a.m f4132d = new g.a.a.m("2.5.29.14").u();
    public static final g.a.a.m s = new g.a.a.m("2.5.29.15").u();
    public static final g.a.a.m x = new g.a.a.m("2.5.29.16").u();
    public static final g.a.a.m y = new g.a.a.m("2.5.29.17").u();
    public static final g.a.a.m E3 = new g.a.a.m("2.5.29.18").u();
    public static final g.a.a.m F3 = new g.a.a.m("2.5.29.19").u();
    public static final g.a.a.m G3 = new g.a.a.m("2.5.29.20").u();
    public static final g.a.a.m H3 = new g.a.a.m("2.5.29.21").u();
    public static final g.a.a.m I3 = new g.a.a.m("2.5.29.23").u();
    public static final g.a.a.m J3 = new g.a.a.m("2.5.29.24").u();
    public static final g.a.a.m K3 = new g.a.a.m("2.5.29.27").u();
    public static final g.a.a.m L3 = new g.a.a.m("2.5.29.28").u();
    public static final g.a.a.m M3 = new g.a.a.m("2.5.29.29").u();
    public static final g.a.a.m N3 = new g.a.a.m("2.5.29.30").u();
    public static final g.a.a.m O3 = new g.a.a.m("2.5.29.31").u();
    public static final g.a.a.m P3 = new g.a.a.m("2.5.29.32").u();
    public static final g.a.a.m Q3 = new g.a.a.m("2.5.29.33").u();
    public static final g.a.a.m R3 = new g.a.a.m("2.5.29.35").u();
    public static final g.a.a.m S3 = new g.a.a.m("2.5.29.36").u();
    public static final g.a.a.m T3 = new g.a.a.m("2.5.29.37").u();
    public static final g.a.a.m U3 = new g.a.a.m("2.5.29.46").u();
    public static final g.a.a.m V3 = new g.a.a.m("2.5.29.54").u();
    public static final g.a.a.m W3 = new g.a.a.m("1.3.6.1.5.5.7.1.1").u();
    public static final g.a.a.m X3 = new g.a.a.m("1.3.6.1.5.5.7.1.11").u();
    public static final g.a.a.m Y3 = new g.a.a.m("1.3.6.1.5.5.7.1.12").u();
    public static final g.a.a.m Z3 = new g.a.a.m("1.3.6.1.5.5.7.1.2").u();
    public static final g.a.a.m a4 = new g.a.a.m("1.3.6.1.5.5.7.1.3").u();
    public static final g.a.a.m b4 = new g.a.a.m("1.3.6.1.5.5.7.1.4").u();
    public static final g.a.a.m c4 = new g.a.a.m("2.5.29.56").u();
    public static final g.a.a.m d4 = new g.a.a.m("2.5.29.55").u();
    public static final g.a.a.m e4 = new g.a.a.m("2.5.29.60").u();

    public m(g.a.a.r rVar) {
        g.a.a.e eVarQ;
        if (rVar.size() == 2) {
            this.f4 = g.a.a.m.s(rVar.q(0));
            this.g4 = false;
            eVarQ = rVar.q(1);
        } else {
            if (rVar.size() != 3) {
                throw new IllegalArgumentException("Bad sequence size: " + rVar.size());
            }
            this.f4 = g.a.a.m.s(rVar.q(0));
            this.g4 = g.a.a.c.o(rVar.q(1)).r();
            eVarQ = rVar.q(2);
        }
        this.h4 = g.a.a.n.n(eVarQ);
    }

    public static g.a.a.q g(m mVar) throws IllegalArgumentException {
        try {
            return g.a.a.q.j(mVar.i().p());
        } catch (IOException e2) {
            throw new IllegalArgumentException("can't convert extension: " + e2);
        }
    }

    public static m j(Object obj) {
        if (obj instanceof m) {
            return (m) obj;
        }
        if (obj != null) {
            return new m(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f4);
        if (this.g4) {
            fVar.a(g.a.a.c.q(true));
        }
        fVar.a(this.h4);
        return new c1(fVar);
    }

    @Override // g.a.a.l
    public boolean equals(Object obj) {
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return mVar.h().equals(h()) && mVar.i().equals(i()) && mVar.l() == l();
    }

    public g.a.a.m h() {
        return this.f4;
    }

    @Override // g.a.a.l
    public int hashCode() {
        return l() ? i().hashCode() ^ h().hashCode() : ~(i().hashCode() ^ h().hashCode());
    }

    public g.a.a.n i() {
        return this.h4;
    }

    public g.a.a.e k() {
        return g(this);
    }

    public boolean l() {
        return this.g4;
    }
}
