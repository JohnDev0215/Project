package g.a.b.e0;

import g.a.b.j0.b0;
import g.a.b.j0.c0;
import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class l implements g.a.b.c, g.a.d.b.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public g.a.b.j0.x f4710g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public SecureRandom f4711h;

    @Override // g.a.b.c
    public g.a.b.b a() {
        BigInteger bigIntegerE = this.f4710g.e();
        int iBitLength = bigIntegerE.bitLength();
        int i2 = iBitLength >>> 2;
        while (true) {
            BigInteger bigInteger = new BigInteger(iBitLength, this.f4711h);
            if (bigInteger.compareTo(g.a.d.b.c.f5472c) >= 0 && bigInteger.compareTo(bigIntegerE) < 0 && g.a.d.b.w.g(bigInteger) >= i2) {
                return new g.a.b.b(new c0(b().a(this.f4710g.b(), bigInteger), this.f4710g), new b0(bigInteger, this.f4710g));
            }
        }
    }

    public g.a.d.b.g b() {
        return new g.a.d.b.j();
    }

    public void c(g.a.b.r rVar) {
        g.a.b.j0.y yVar = (g.a.b.j0.y) rVar;
        this.f4711h = yVar.a();
        this.f4710g = yVar.c();
        if (this.f4711h == null) {
            this.f4711h = g.a.b.j.b();
        }
    }
}
