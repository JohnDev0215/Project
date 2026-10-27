package g.a.e.a;

import g.a.a.c1;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class d extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5567c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5568d;
    public final g.a.e.d.a.a s;

    public d(int i2, int i3, g.a.e.d.a.a aVar) {
        this.f5567c = i2;
        this.f5568d = i3;
        this.s = new g.a.e.d.a.a(aVar);
    }

    public d(r rVar) {
        this.f5567c = ((g.a.a.j) rVar.q(0)).q().intValue();
        this.f5568d = ((g.a.a.j) rVar.q(1)).q().intValue();
        this.s = new g.a.e.d.a.a(((n) rVar.q(2)).p());
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
        fVar.a(new g.a.a.j(this.f5567c));
        fVar.a(new g.a.a.j(this.f5568d));
        fVar.a(new y0(this.s.c()));
        return new c1(fVar);
    }

    public g.a.e.d.a.a g() {
        return new g.a.e.d.a.a(this.s);
    }

    public int i() {
        return this.f5567c;
    }

    public int j() {
        return this.f5568d;
    }
}
