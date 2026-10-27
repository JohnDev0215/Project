package g.a.e.a;

import g.a.a.c1;
import g.a.a.q;
import g.a.a.r;

/* JADX INFO: loaded from: classes.dex */
public class i extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.a.a.j f5584c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5585d;
    public final g.a.a.w2.a s;

    public i(int i2, g.a.a.w2.a aVar) {
        this.f5584c = new g.a.a.j(0L);
        this.f5585d = i2;
        this.s = aVar;
    }

    public i(r rVar) {
        this.f5584c = g.a.a.j.n(rVar.q(0));
        this.f5585d = g.a.a.j.n(rVar.q(1)).q().intValue();
        this.s = g.a.a.w2.a.h(rVar.q(2));
    }

    public static i h(Object obj) {
        if (obj instanceof i) {
            return (i) obj;
        }
        if (obj != null) {
            return new i(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f5584c);
        fVar.a(new g.a.a.j(this.f5585d));
        fVar.a(this.s);
        return new c1(fVar);
    }

    public int g() {
        return this.f5585d;
    }

    public g.a.a.w2.a i() {
        return this.s;
    }
}
