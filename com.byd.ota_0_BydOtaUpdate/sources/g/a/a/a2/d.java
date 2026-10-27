package g.a.a.a2;

import g.a.a.c1;
import g.a.a.l;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class d extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.a.a.w2.a f3752c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final n f3753d;

    public d(r rVar) {
        this.f3752c = g.a.a.w2.a.h(rVar.q(0));
        this.f3753d = n.n(rVar.q(1));
    }

    public d(g.a.a.w2.a aVar, byte[] bArr) {
        this.f3752c = aVar;
        this.f3753d = new y0(g.a.f.a.f(bArr));
    }

    public static d h(Object obj) {
        if (obj instanceof d) {
            return (d) obj;
        }
        if (obj != null) {
            return new d(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3752c);
        fVar.a(this.f3753d);
        return new c1(fVar);
    }

    public byte[] g() {
        return g.a.f.a.f(this.f3753d.p());
    }

    public g.a.a.w2.a i() {
        return this.f3752c;
    }
}
