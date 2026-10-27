package g.a.b.m0;

import g.a.b.j0.b0;
import g.a.b.j0.c0;
import g.a.b.j0.x;
import g.a.b.j0.x0;
import g.a.b.j0.z;
import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class c implements g.a.b.k {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f5127g = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public z f5128h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public SecureRandom f5129i;

    public static BigInteger d(BigInteger bigInteger, g.a.d.b.e eVar) {
        return g(eVar.t(), bigInteger.bitLength() - 1);
    }

    public static BigInteger e(BigInteger bigInteger, SecureRandom secureRandom) {
        return new BigInteger(bigInteger.bitLength() - 1, secureRandom);
    }

    public static g.a.d.b.e f(g.a.d.b.d dVar, byte[] bArr) {
        return dVar.n(g(new BigInteger(1, g.a.f.a.H(bArr)), dVar.u()));
    }

    public static BigInteger g(BigInteger bigInteger, int i2) {
        return bigInteger.bitLength() > i2 ? bigInteger.mod(f5127g.shiftLeft(i2)) : bigInteger;
    }

    @Override // g.a.b.k
    public BigInteger[] a(byte[] bArr) {
        x xVarB = this.f5128h.b();
        g.a.d.b.d dVarA = xVarB.a();
        g.a.d.b.e eVarF = f(dVarA, bArr);
        if (eVarF.i()) {
            eVarF = dVarA.n(f5127g);
        }
        BigInteger bigIntegerE = xVarB.e();
        BigInteger bigIntegerC = ((b0) this.f5128h).c();
        g.a.d.b.g gVarC = c();
        while (true) {
            BigInteger bigIntegerE2 = e(bigIntegerE, this.f5129i);
            g.a.d.b.e eVarF2 = gVarC.a(xVarB.b(), bigIntegerE2).A().f();
            if (!eVarF2.i()) {
                BigInteger bigIntegerD = d(bigIntegerE, eVarF.j(eVarF2));
                if (bigIntegerD.signum() != 0) {
                    BigInteger bigIntegerMod = bigIntegerD.multiply(bigIntegerC).add(bigIntegerE2).mod(bigIntegerE);
                    if (bigIntegerMod.signum() != 0) {
                        return new BigInteger[]{bigIntegerD, bigIntegerMod};
                    }
                } else {
                    continue;
                }
            }
        }
    }

    @Override // g.a.b.k
    public boolean b(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        if (bigInteger.signum() <= 0 || bigInteger2.signum() <= 0) {
            return false;
        }
        x xVarB = this.f5128h.b();
        BigInteger bigIntegerE = xVarB.e();
        if (bigInteger.compareTo(bigIntegerE) >= 0 || bigInteger2.compareTo(bigIntegerE) >= 0) {
            return false;
        }
        g.a.d.b.d dVarA = xVarB.a();
        g.a.d.b.e eVarF = f(dVarA, bArr);
        if (eVarF.i()) {
            eVarF = dVarA.n(f5127g);
        }
        g.a.d.b.h hVarA = g.a.d.b.b.q(xVarB.b(), bigInteger2, ((c0) this.f5128h).c(), bigInteger).A();
        return !hVarA.u() && d(bigIntegerE, eVarF.j(hVarA.f())).compareTo(bigInteger) == 0;
    }

    public g.a.d.b.g c() {
        return new g.a.d.b.j();
    }

    @Override // g.a.b.k
    public void init(boolean z, g.a.b.i iVar) {
        z zVar;
        if (z) {
            if (iVar instanceof x0) {
                x0 x0Var = (x0) iVar;
                this.f5129i = x0Var.b();
                iVar = x0Var.a();
            } else {
                this.f5129i = g.a.b.j.b();
            }
            zVar = (b0) iVar;
        } else {
            zVar = (c0) iVar;
        }
        this.f5128h = zVar;
    }
}
