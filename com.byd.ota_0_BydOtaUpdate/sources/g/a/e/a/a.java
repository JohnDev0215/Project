package g.a.e.a;

import g.a.a.c1;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class a extends g.a.a.l {
    public g.a.a.w2.a E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5561c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5562d;
    public byte[] s;
    public byte[] x;
    public byte[] y;

    public a(int i2, int i3, g.a.e.d.a.b bVar, g.a.e.d.a.i iVar, g.a.e.d.a.h hVar, g.a.a.w2.a aVar) {
        this.f5561c = i2;
        this.f5562d = i3;
        this.s = bVar.e();
        this.x = iVar.h();
        this.y = hVar.a();
        this.E3 = aVar;
    }

    public a(r rVar) {
        this.f5561c = ((g.a.a.j) rVar.q(0)).q().intValue();
        this.f5562d = ((g.a.a.j) rVar.q(1)).q().intValue();
        this.s = ((n) rVar.q(2)).p();
        this.x = ((n) rVar.q(3)).p();
        this.y = ((n) rVar.q(4)).p();
        this.E3 = g.a.a.w2.a.h(rVar.q(5));
    }

    public static a j(Object obj) {
        if (obj instanceof a) {
            return (a) obj;
        }
        if (obj != null) {
            return new a(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new g.a.a.j(this.f5561c));
        fVar.a(new g.a.a.j(this.f5562d));
        fVar.a(new y0(this.s));
        fVar.a(new y0(this.x));
        fVar.a(new y0(this.y));
        fVar.a(this.E3);
        return new c1(fVar);
    }

    public g.a.a.w2.a g() {
        return this.E3;
    }

    public g.a.e.d.a.b h() {
        return new g.a.e.d.a.b(this.s);
    }

    public g.a.e.d.a.i i() {
        return new g.a.e.d.a.i(h(), this.x);
    }

    public int k() {
        return this.f5562d;
    }

    public int l() {
        return this.f5561c;
    }

    public g.a.e.d.a.h m() {
        return new g.a.e.d.a.h(this.y);
    }
}
