package g.a.a.p2;

import g.a.a.c1;
import g.a.a.y0;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class i extends g.a.a.l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final BigInteger f3967c = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.a.w2.j f3968d;
    public byte[] s;
    public BigInteger x;

    public i(g.a.a.r rVar) {
        this.f3968d = g.a.a.w2.j.i(rVar.q(0));
        this.s = g.a.f.a.f(((g.a.a.n) rVar.q(1)).p());
        this.x = rVar.size() == 3 ? ((g.a.a.j) rVar.q(2)).q() : f3967c;
    }

    public i(g.a.a.w2.j jVar, byte[] bArr, int i2) {
        this.f3968d = jVar;
        this.s = g.a.f.a.f(bArr);
        this.x = BigInteger.valueOf(i2);
    }

    public static i g(Object obj) {
        if (obj instanceof i) {
            return (i) obj;
        }
        if (obj != null) {
            return new i(g.a.a.r.n(obj));
        }
        return null;
    }

    @Override // g.a.a.l, g.a.a.e
    public g.a.a.q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(this.f3968d);
        fVar.a(new y0(this.s));
        if (!this.x.equals(f3967c)) {
            fVar.a(new g.a.a.j(this.x));
        }
        return new c1(fVar);
    }

    public BigInteger h() {
        return this.x;
    }

    public g.a.a.w2.j i() {
        return this.f3968d;
    }

    public byte[] j() {
        return g.a.f.a.f(this.s);
    }
}
