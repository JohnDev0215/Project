package g.a.a.d2;

import g.a.a.c1;
import g.a.a.j;
import g.a.a.l;
import g.a.a.q;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class e extends l {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3813c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public j f3814d;
    public j s;
    public j x;

    public e(int i2, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f3813c = i2;
        this.f3814d = new j(bigInteger);
        this.s = new j(bigInteger2);
        this.x = new j(bigInteger3);
    }

    @Override // g.a.a.l, g.a.a.e
    public q c() {
        g.a.a.f fVar = new g.a.a.f();
        fVar.a(new j(this.f3813c));
        fVar.a(this.f3814d);
        fVar.a(this.s);
        fVar.a(this.x);
        return new c1(fVar);
    }

    public BigInteger g() {
        return this.x.p();
    }

    public BigInteger h() {
        return this.f3814d.p();
    }

    public BigInteger i() {
        return this.s.p();
    }
}
