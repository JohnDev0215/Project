package g.a.b.e0;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class i implements g.a.b.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f4699g = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public g.a.b.j0.o f4700h;

    public static BigInteger b(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        return bigInteger2.modPow(bigInteger3, bigInteger);
    }

    public static BigInteger c(BigInteger bigInteger, SecureRandom secureRandom) {
        BigInteger bigIntegerC;
        int iBitLength = bigInteger.bitLength() >>> 2;
        do {
            BigInteger bigInteger2 = f4699g;
            bigIntegerC = g.a.f.b.c(bigInteger2, bigInteger.subtract(bigInteger2), secureRandom);
        } while (g.a.d.b.w.g(bigIntegerC) < iBitLength);
        return bigIntegerC;
    }

    @Override // g.a.b.c
    public g.a.b.b a() {
        g.a.b.j0.r rVarC = this.f4700h.c();
        BigInteger bigIntegerC = c(rVarC.c(), this.f4700h.a());
        return new g.a.b.b(new g.a.b.j0.t(b(rVarC.b(), rVarC.a(), bigIntegerC), rVarC), new g.a.b.j0.s(bigIntegerC, rVarC));
    }

    public void d(g.a.b.r rVar) {
        this.f4700h = (g.a.b.j0.o) rVar;
    }
}
