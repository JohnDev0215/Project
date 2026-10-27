package g.a.e.a;

import g.a.a.c1;
import g.a.a.n;
import g.a.a.q;
import g.a.a.r;
import g.a.a.y0;

/* JADX INFO: loaded from: classes.dex */
public class g extends g.a.a.l {
    public byte[] E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.a.j f5580c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.m f5581d;
    public g.a.a.j s;
    public byte[][] x;
    public byte[][] y;

    public g(int i2, short[][] sArr, short[][] sArr2, short[] sArr3) {
        this.f5580c = new g.a.a.j(0L);
        this.s = new g.a.a.j(i2);
        this.x = g.a.e.b.c.f.a.c(sArr);
        this.y = g.a.e.b.c.f.a.c(sArr2);
        this.E3 = g.a.e.b.c.f.a.a(sArr3);
    }

    public g(r rVar) {
        if (rVar.q(0) instanceof g.a.a.j) {
            this.f5580c = g.a.a.j.n(rVar.q(0));
        } else {
            this.f5581d = g.a.a.m.s(rVar.q(0));
        }
        this.s = g.a.a.j.n(rVar.q(1));
        r rVarN = r.n(rVar.q(2));
        this.x = new byte[rVarN.size()][];
        for (int i2 = 0; i2 < rVarN.size(); i2++) {
            this.x[i2] = n.n(rVarN.q(i2)).p();
        }
        r rVar2 = (r) rVar.q(3);
        this.y = new byte[rVar2.size()][];
        for (int i3 = 0; i3 < rVar2.size(); i3++) {
            this.y[i3] = n.n(rVar2.q(i3)).p();
        }
        this.E3 = n.n(((r) rVar.q(4)).q(0)).p();
    }

    public static g k(Object obj) {
        if (obj instanceof g) {
            return (g) obj;
        }
        if (obj != null) {
            return new g(r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        g.a.a.e eVar = this.f5580c;
        if (eVar == null) {
            eVar = this.f5581d;
        }
        fVar.a(eVar);
        fVar.a(this.s);
        g.a.a.f fVar2 = new g.a.a.f();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[][] bArr = this.x;
            if (i3 >= bArr.length) {
                break;
            }
            fVar2.a(new y0(bArr[i3]));
            i3++;
        }
        fVar.a(new c1(fVar2));
        g.a.a.f fVar3 = new g.a.a.f();
        while (true) {
            byte[][] bArr2 = this.y;
            if (i2 >= bArr2.length) {
                fVar.a(new c1(fVar3));
                g.a.a.f fVar4 = new g.a.a.f();
                fVar4.a(new y0(this.E3));
                fVar.a(new c1(fVar4));
                return new c1(fVar);
            }
            fVar3.a(new y0(bArr2[i2]));
            i2++;
        }
    }

    public short[][] g() {
        return g.a.e.b.c.f.a.d(this.x);
    }

    public short[] h() {
        return g.a.e.b.c.f.a.b(this.E3);
    }

    public short[][] i() {
        return g.a.e.b.c.f.a.d(this.y);
    }

    public int j() {
        return this.s.q().intValue();
    }
}
