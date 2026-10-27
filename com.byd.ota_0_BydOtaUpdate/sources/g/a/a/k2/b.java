package g.a.a.k2;

import g.a.a.c1;
import g.a.a.l;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class b extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public n f3890c;

    public b(r rVar) {
        this.f3890c = rVar.size() == 1 ? (n) rVar.q(0) : null;
    }

    public b(byte[] bArr) {
        this.f3890c = new y0(bArr);
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        n nVar = this.f3890c;
        if (nVar != null) {
            fVar.a(nVar);
        }
        return new c1(fVar);
    }

    public byte[] g() {
        n nVar = this.f3890c;
        if (nVar != null) {
            return g.a.f.a.f(nVar.p());
        }
        return null;
    }
}
