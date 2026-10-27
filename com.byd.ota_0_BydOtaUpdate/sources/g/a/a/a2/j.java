package g.a.a.a2;

import g.a.a.c1;
import g.a.a.l;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class j extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.a.a.w2.a f3763c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g.a.a.p2.h f3764d;
    public final n s;

    public j(r rVar) {
        this.f3763c = g.a.a.w2.a.h(rVar.q(0));
        this.f3764d = g.a.a.p2.h.h(rVar.q(1));
        this.s = n.n(rVar.q(2));
    }

    public j(g.a.a.w2.a aVar, g.a.a.p2.h hVar, byte[] bArr) {
        this.f3763c = aVar;
        this.f3764d = hVar;
        this.s = new y0(g.a.f.a.f(bArr));
    }

    public static j g(Object obj) {
        if (obj instanceof j) {
            return (j) obj;
        }
        if (obj != null) {
            return new j(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3763c);
        fVar.a(this.f3764d);
        fVar.a(this.s);
        return new c1(fVar);
    }

    public byte[] h() {
        return g.a.f.a.f(this.s.p());
    }

    public g.a.a.w2.a i() {
        return this.f3763c;
    }

    public g.a.a.p2.h j() {
        return this.f3764d;
    }
}
