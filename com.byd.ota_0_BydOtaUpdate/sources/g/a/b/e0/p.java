package g.a.b.e0;

import g.a.b.j0.i0;
import g.a.b.j0.k0;
import g.a.b.j0.l0;
import g.a.b.j0.m0;
import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class p implements g.a.b.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public i0 f4718g;

    @Override // g.a.b.c
    public g.a.b.b a() {
        k0 k0VarC = this.f4718g.c();
        SecureRandom secureRandomA = this.f4718g.a();
        BigInteger bigIntegerC = k0VarC.c();
        BigInteger bigIntegerB = k0VarC.b();
        BigInteger bigIntegerA = k0VarC.a();
        while (true) {
            BigInteger bigInteger = new BigInteger(256, secureRandomA);
            if (bigInteger.signum() >= 1 && bigInteger.compareTo(bigIntegerC) < 0 && g.a.d.b.w.g(bigInteger) >= 64) {
                return new g.a.b.b(new m0(bigIntegerA.modPow(bigInteger, bigIntegerB), k0VarC), new l0(bigInteger, k0VarC));
            }
        }
    }

    public void b(g.a.b.r rVar) {
        this.f4718g = (i0) rVar;
    }
}
