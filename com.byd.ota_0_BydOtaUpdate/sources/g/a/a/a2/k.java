package g.a.a.a2;

import g.a.a.c1;
import g.a.a.l;
import g.a.a.m;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class k extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m f3765c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final n f3766d;

    public k(m mVar, byte[] bArr) {
        this.f3765c = mVar;
        this.f3766d = new y0(g.a.f.a.f(bArr));
    }

    public k(r rVar) {
        this.f3765c = m.s(rVar.q(0));
        this.f3766d = n.n(rVar.q(1));
    }

    public static k g(Object obj) {
        if (obj instanceof k) {
            return (k) obj;
        }
        if (obj != null) {
            return new k(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3765c);
        fVar.a(this.f3766d);
        return new c1(fVar);
    }

    public m h() {
        return this.f3765c;
    }

    public byte[] i() {
        return g.a.f.a.f(this.f3766d.p());
    }
}
