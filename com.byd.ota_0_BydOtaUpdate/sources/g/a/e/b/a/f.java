package g.a.e.b.a;

import g.a.e.d.a.h;
import g.a.e.d.a.i;
import g.a.e.d.a.k;

/* JADX INFO: loaded from: classes.dex */
public class f extends d {
    public g.a.e.d.a.a E3;
    public h F3;
    public h G3;
    public g.a.e.d.a.a H3;
    public i[] I3;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5595d;
    public int s;
    public g.a.e.d.a.b x;
    public i y;

    public f(int i2, int i3, g.a.e.d.a.b bVar, i iVar, h hVar, h hVar2, g.a.e.d.a.a aVar) {
        super(true, null);
        this.s = i3;
        this.f5595d = i2;
        this.x = bVar;
        this.y = iVar;
        this.E3 = aVar;
        this.F3 = hVar;
        this.G3 = hVar2;
        this.H3 = g.a.e.d.a.c.a(bVar, iVar);
        this.I3 = new k(bVar, iVar).c();
    }

    public g.a.e.d.a.b b() {
        return this.x;
    }

    public i c() {
        return this.y;
    }

    public g.a.e.d.a.a d() {
        return this.H3;
    }

    public int e() {
        return this.s;
    }

    public int f() {
        return this.f5595d;
    }

    public h g() {
        return this.F3;
    }

    public h h() {
        return this.G3;
    }

    public i[] i() {
        return this.I3;
    }

    public g.a.e.d.a.a j() {
        return this.E3;
    }
}
