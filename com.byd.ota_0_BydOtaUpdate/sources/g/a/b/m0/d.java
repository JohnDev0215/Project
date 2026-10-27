package g.a.b.m0;

import g.a.b.j0.b0;
import g.a.b.j0.c0;
import g.a.b.j0.x;
import g.a.b.j0.z;
import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class d implements g.a.d.b.c, g.a.b.k {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a f5130g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public z f5131h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public SecureRandom f5132i;

    public d() {
        this.f5130g = new m();
    }

    public d(a aVar) {
        this.f5130g = aVar;
    }

    @Override // g.a.b.k
    public BigInteger[] a(byte[] bArr) {
        x xVarB = this.f5131h.b();
        BigInteger bigIntegerE = xVarB.e();
        BigInteger bigIntegerC = c(bigIntegerE, bArr);
        BigInteger bigIntegerC2 = ((b0) this.f5131h).c();
        if (this.f5130g.c()) {
            this.f5130g.d(bigIntegerE, bigIntegerC2, bArr);
        } else {
            this.f5130g.b(bigIntegerE, this.f5132i);
        }
        g.a.d.b.g gVarD = d();
        while (true) {
            BigInteger bigIntegerA = this.f5130g.a();
            BigInteger bigIntegerMod = gVarD.a(xVarB.b(), bigIntegerA).A().f().t().mod(bigIntegerE);
            BigInteger bigInteger = g.a.d.b.c.f5470a;
            if (!bigIntegerMod.equals(bigInteger)) {
                BigInteger bigIntegerMod2 = bigIntegerA.modInverse(bigIntegerE).multiply(bigIntegerC.add(bigIntegerC2.multiply(bigIntegerMod))).mod(bigIntegerE);
                if (!bigIntegerMod2.equals(bigInteger)) {
                    return new BigInteger[]{bigIntegerMod, bigIntegerMod2};
                }
            }
        }
    }

    @Override // g.a.b.k
    public boolean b(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        BigInteger bigIntegerQ;
        g.a.d.b.e eVarE;
        x xVarB = this.f5131h.b();
        BigInteger bigIntegerE = xVarB.e();
        BigInteger bigIntegerC = c(bigIntegerE, bArr);
        BigInteger bigInteger3 = g.a.d.b.c.f5471b;
        if (bigInteger.compareTo(bigInteger3) < 0 || bigInteger.compareTo(bigIntegerE) >= 0 || bigInteger2.compareTo(bigInteger3) < 0 || bigInteger2.compareTo(bigIntegerE) >= 0) {
            return false;
        }
        BigInteger bigIntegerModInverse = bigInteger2.modInverse(bigIntegerE);
        g.a.d.b.h hVarQ = g.a.d.b.b.q(xVarB.b(), bigIntegerC.multiply(bigIntegerModInverse).mod(bigIntegerE), ((c0) this.f5131h).c(), bigInteger.multiply(bigIntegerModInverse).mod(bigIntegerE));
        if (hVarQ.u()) {
            return false;
        }
        g.a.d.b.d dVarI = hVarQ.i();
        if (dVarI == null || (bigIntegerQ = dVarI.q()) == null || bigIntegerQ.compareTo(g.a.d.b.c.f5475f) > 0 || (eVarE = e(dVarI.r(), hVarQ)) == null || eVarE.i()) {
            return hVarQ.A().f().t().mod(bigIntegerE).equals(bigInteger);
        }
        g.a.d.b.e eVarQ = hVarQ.q();
        while (dVarI.A(bigInteger)) {
            if (dVarI.n(bigInteger).j(eVarE).equals(eVarQ)) {
                return true;
            }
            bigInteger = bigInteger.add(bigIntegerE);
        }
        return false;
    }

    public BigInteger c(BigInteger bigInteger, byte[] bArr) {
        int iBitLength = bigInteger.bitLength();
        int length = bArr.length * 8;
        BigInteger bigInteger2 = new BigInteger(1, bArr);
        return iBitLength < length ? bigInteger2.shiftRight(length - iBitLength) : bigInteger2;
    }

    public g.a.d.b.g d() {
        return new g.a.d.b.j();
    }

    public g.a.d.b.e e(int i2, g.a.d.b.h hVar) {
        if (i2 != 1) {
            if (i2 == 2 || i2 == 3 || i2 == 4) {
                return hVar.s(0).o();
            }
            if (i2 != 6 && i2 != 7) {
                return null;
            }
        }
        return hVar.s(0);
    }

    public SecureRandom f(boolean z, SecureRandom secureRandom) {
        if (z) {
            return secureRandom != null ? secureRandom : g.a.b.j.b();
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0029  */
    @Override // g.a.b.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void init(boolean r2, g.a.b.i r3) {
        /*
            r1 = this;
            if (r2 == 0) goto L18
            boolean r0 = r3 instanceof g.a.b.j0.x0
            if (r0 == 0) goto L15
            g.a.b.j0.x0 r3 = (g.a.b.j0.x0) r3
            g.a.b.i r0 = r3.a()
            g.a.b.j0.b0 r0 = (g.a.b.j0.b0) r0
            r1.f5131h = r0
            java.security.SecureRandom r3 = r3.b()
            goto L1d
        L15:
            g.a.b.j0.b0 r3 = (g.a.b.j0.b0) r3
            goto L1a
        L18:
            g.a.b.j0.c0 r3 = (g.a.b.j0.c0) r3
        L1a:
            r1.f5131h = r3
            r3 = 0
        L1d:
            if (r2 == 0) goto L29
            g.a.b.m0.a r2 = r1.f5130g
            boolean r2 = r2.c()
            if (r2 != 0) goto L29
            r2 = 1
            goto L2a
        L29:
            r2 = 0
        L2a:
            java.security.SecureRandom r2 = r1.f(r2, r3)
            r1.f5132i = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: g.a.b.m0.d.init(boolean, g.a.b.i):void");
    }
}
