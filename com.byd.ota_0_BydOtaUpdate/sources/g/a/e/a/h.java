package g.a.e.a;

import g.a.a.c1;
import g.a.a.q;
import g.a.a.r;

/* JADX INFO: loaded from: classes.dex */
public class h extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.a.a.j f5582c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g.a.a.w2.a f5583d;

    public h(r rVar) {
        this.f5582c = g.a.a.j.n(rVar.q(0));
        this.f5583d = g.a.a.w2.a.h(rVar.q(1));
    }

    public h(g.a.a.w2.a aVar) {
        this.f5582c = new g.a.a.j(0L);
        this.f5583d = aVar;
    }

    public static final h g(Object obj) {
        if (obj instanceof h) {
            return (h) obj;
        }
        if (obj != null) {
            return new h(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f5582c);
        fVar.a(this.f5583d);
        return new c1(fVar);
    }

    public g.a.a.w2.a h() {
        return this.f5583d;
    }
}
