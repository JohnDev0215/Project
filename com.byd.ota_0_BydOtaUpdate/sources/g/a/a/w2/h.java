package g.a.a.w2;

import g.a.a.c1;
import g.a.a.o0;
import java.util.Enumeration;

/* JADX INFO: loaded from: classes.dex */
public class h extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v f4121c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f4122d;
    public o0 s;
    public boolean x = false;
    public int y;

    public h(g.a.a.r rVar) {
        if (rVar.size() != 3) {
            throw new IllegalArgumentException("sequence wrong size for CertificateList");
        }
        this.f4121c = v.h(rVar.q(0));
        this.f4122d = a.h(rVar.q(1));
        this.s = o0.t(rVar.q(2));
    }

    public static h g(Object obj) {
        if (obj instanceof h) {
            return (h) obj;
        }
        if (obj != null) {
            return new h(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f4121c);
        fVar.a(this.f4122d);
        fVar.a(this.s);
        return new c1(fVar);
    }

    public g.a.a.v2.c h() {
        return this.f4121c.i();
    }

    @Override // g.a.a.l
    public int hashCode() {
        if (!this.x) {
            this.y = super.hashCode();
            this.x = true;
        }
        return this.y;
    }

    public x i() {
        return this.f4121c.j();
    }

    public Enumeration j() {
        return this.f4121c.k();
    }

    public o0 k() {
        return this.s;
    }

    public a l() {
        return this.f4122d;
    }

    public v m() {
        return this.f4121c;
    }

    public x n() {
        return this.f4121c.m();
    }

    public int o() {
        return this.f4121c.n();
    }
}
