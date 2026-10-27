package g.a.a.p2;

import g.a.a.f0;
import g.a.a.j0;
import g.a.a.x;

/* JADX INFO: loaded from: classes.dex */
public class e extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.r f3962c;

    public e(g.a.a.m mVar, g.a.a.w2.a aVar, g.a.a.e eVar) {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(mVar);
        fVar.a(aVar.c());
        fVar.a(new j0(false, 0, eVar));
        this.f3962c = new f0(fVar);
    }

    public e(g.a.a.r rVar) {
        if (((g.a.a.j) rVar.q(0)).q().intValue() != 0) {
            throw new IllegalArgumentException("sequence not version 0");
        }
        this.f3962c = g.a.a.r.n(rVar.q(1));
    }

    public static e i(Object obj) {
        if (obj instanceof e) {
            return (e) obj;
        }
        if (obj != null) {
            return new e(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(0L));
        fVar.a(this.f3962c);
        return new f0(fVar);
    }

    public g.a.a.n g() {
        if (this.f3962c.size() == 3) {
            return g.a.a.n.o(x.n(this.f3962c.q(2)), false);
        }
        return null;
    }

    public g.a.a.w2.a h() {
        return g.a.a.w2.a.h(this.f3962c.q(1));
    }
}
