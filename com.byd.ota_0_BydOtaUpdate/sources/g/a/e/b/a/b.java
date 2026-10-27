package g.a.e.b.a;

import g.a.e.d.a.h;
import g.a.e.d.a.i;
import g.a.e.d.a.k;

/* JADX INFO: loaded from: classes.dex */
public class b extends a {
    public i E3;
    public h F3;
    public g.a.e.d.a.a G3;
    public i[] H3;
    public int s;
    public int x;
    public g.a.e.d.a.b y;

    public b(int i2, int i3, g.a.e.d.a.b bVar, i iVar, g.a.e.d.a.a aVar, h hVar, String str) {
        super(true, str);
        this.s = i2;
        this.x = i3;
        this.y = bVar;
        this.E3 = iVar;
        this.G3 = aVar;
        this.F3 = hVar;
        this.H3 = new k(bVar, iVar).c();
    }

    public b(int i2, int i3, g.a.e.d.a.b bVar, i iVar, h hVar, String str) {
        this(i2, i3, bVar, iVar, g.a.e.d.a.c.a(bVar, iVar), hVar, str);
    }

    public g.a.e.d.a.b c() {
        return this.y;
    }

    public i d() {
        return this.E3;
    }

    public g.a.e.d.a.a e() {
        return this.G3;
    }

    public int f() {
        return this.x;
    }

    public int g() {
        return this.s;
    }

    public h h() {
        return this.F3;
    }

    public i[] i() {
        return this.H3;
    }
}
