package g.a.e.a;

import g.a.a.c1;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class b extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f5563c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f5564d;
    public final g.a.e.d.a.a s;
    public final g.a.a.w2.a x;

    public b(int i2, int i3, g.a.e.d.a.a aVar, g.a.a.w2.a aVar2) {
        this.f5563c = i2;
        this.f5564d = i3;
        this.s = new g.a.e.d.a.a(aVar.c());
        this.x = aVar2;
    }

    public b(r rVar) {
        this.f5563c = ((g.a.a.j) rVar.q(0)).q().intValue();
        this.f5564d = ((g.a.a.j) rVar.q(1)).q().intValue();
        this.s = new g.a.e.d.a.a(((n) rVar.q(2)).p());
        this.x = g.a.a.w2.a.h(rVar.q(3));
    }

    public static b i(Object obj) {
        if (obj instanceof b) {
            return (b) obj;
        }
        if (obj != null) {
            return new b(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(this.f5563c));
        fVar.a(new g.a.a.j(this.f5564d));
        fVar.a(new y0(this.s.c()));
        fVar.a(this.x);
        return new c1(fVar);
    }

    public g.a.a.w2.a g() {
        return this.x;
    }

    public g.a.e.d.a.a h() {
        return this.s;
    }

    public int j() {
        return this.f5563c;
    }

    public int k() {
        return this.f5564d;
    }
}
