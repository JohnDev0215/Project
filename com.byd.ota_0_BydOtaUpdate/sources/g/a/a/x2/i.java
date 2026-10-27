package g.a.a.x2;

import g.a.a.n;
import g.a.a.q;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class i extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final n f4189c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.d.b.d f4190d;
    public g.a.d.b.h s;

    public i(g.a.d.b.d dVar, n nVar) {
        this(dVar, nVar.p());
    }

    public i(g.a.d.b.d dVar, byte[] bArr) {
        this.f4190d = dVar;
        this.f4189c = new y0(g.a.f.a.f(bArr));
    }

    public i(g.a.d.b.h hVar) {
        this(hVar, false);
    }

    public i(g.a.d.b.h hVar, boolean z) {
        this.s = hVar.A();
        this.f4189c = new y0(hVar.l(z));
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        return this.f4189c;
    }

    public synchronized g.a.d.b.h g() {
        if (this.s == null) {
            this.s = this.f4190d.k(this.f4189c.p()).A();
        }
        return this.s;
    }
}
