package g.a.e.a;

import g.a.a.c1;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class c extends g.a.a.l {
    public byte[] E3;
    public byte[] F3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5565c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5566d;
    public byte[] s;
    public byte[] x;
    public byte[] y;

    public c(int i2, int i3, g.a.e.d.a.b bVar, g.a.e.d.a.i iVar, g.a.e.d.a.h hVar, g.a.e.d.a.h hVar2, g.a.e.d.a.a aVar) {
        this.f5565c = i2;
        this.f5566d = i3;
        this.s = bVar.e();
        this.x = iVar.h();
        this.y = aVar.c();
        this.E3 = hVar.a();
        this.F3 = hVar2.a();
    }

    public c(r rVar) {
        this.f5565c = ((g.a.a.j) rVar.q(0)).q().intValue();
        this.f5566d = ((g.a.a.j) rVar.q(1)).q().intValue();
        this.s = ((n) rVar.q(2)).p();
        this.x = ((n) rVar.q(3)).p();
        this.E3 = ((n) rVar.q(4)).p();
        this.F3 = ((n) rVar.q(5)).p();
        this.y = ((n) rVar.q(6)).p();
    }

    public static c i(Object obj) {
        if (obj instanceof c) {
            return (c) obj;
        }
        if (obj != null) {
            return new c(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(this.f5565c));
        fVar.a(new g.a.a.j(this.f5566d));
        fVar.a(new y0(this.s));
        fVar.a(new y0(this.x));
        fVar.a(new y0(this.E3));
        fVar.a(new y0(this.F3));
        fVar.a(new y0(this.y));
        return new c1(fVar);
    }

    public g.a.e.d.a.b g() {
        return new g.a.e.d.a.b(this.s);
    }

    public g.a.e.d.a.i h() {
        return new g.a.e.d.a.i(g(), this.x);
    }

    public int j() {
        return this.f5566d;
    }

    public int k() {
        return this.f5565c;
    }

    public g.a.e.d.a.h l() {
        return new g.a.e.d.a.h(this.E3);
    }

    public g.a.e.d.a.h m() {
        return new g.a.e.d.a.h(this.F3);
    }

    public g.a.e.d.a.a n() {
        return new g.a.e.d.a.a(this.y);
    }
}
