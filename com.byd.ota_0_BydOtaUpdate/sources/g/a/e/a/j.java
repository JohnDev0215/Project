package g.a.e.a;

import g.a.a.c1;
import g.a.a.q;
import g.a.a.r;

/* JADX INFO: loaded from: classes.dex */
public class j extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g.a.a.j f5586c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5587d;
    public final int s;
    public final g.a.a.w2.a x;

    public j(int i2, int i3, g.a.a.w2.a aVar) {
        this.f5586c = new g.a.a.j(0L);
        this.f5587d = i2;
        this.s = i3;
        this.x = aVar;
    }

    public j(r rVar) {
        this.f5586c = g.a.a.j.n(rVar.q(0));
        this.f5587d = g.a.a.j.n(rVar.q(1)).q().intValue();
        this.s = g.a.a.j.n(rVar.q(2)).q().intValue();
        this.x = g.a.a.w2.a.h(rVar.q(3));
    }

    public static j h(Object obj) {
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
        fVar.a(this.f5586c);
        fVar.a(new g.a.a.j(this.f5587d));
        fVar.a(new g.a.a.j(this.s));
        fVar.a(this.x);
        return new c1(fVar);
    }

    public int g() {
        return this.f5587d;
    }

    public int i() {
        return this.s;
    }

    public g.a.a.w2.a j() {
        return this.x;
    }
}
