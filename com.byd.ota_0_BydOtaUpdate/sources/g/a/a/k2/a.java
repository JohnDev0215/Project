package g.a.a.k2;

import g.a.a.c1;
import g.a.a.j;
import g.a.a.l;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class a extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public j f3888c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public n f3889d;

    public a(r rVar) {
        this.f3889d = (n) rVar.q(0);
        this.f3888c = (j) rVar.q(1);
    }

    public a(byte[] bArr, int i2) {
        this.f3889d = new y0(g.a.f.a.f(bArr));
        this.f3888c = new j(i2);
    }

    public static a h(Object obj) {
        if (obj instanceof a) {
            return (a) obj;
        }
        if (obj != null) {
            return new a(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3889d);
        fVar.a(this.f3888c);
        return new c1(fVar);
    }

    public byte[] g() {
        return g.a.f.a.f(this.f3889d.p());
    }

    public int i() {
        return this.f3888c.q().intValue();
    }
}
