package g.a.b.m0;

import g.a.b.j0.p;
import g.a.b.j0.r;
import g.a.b.j0.s;
import g.a.b.j0.t;
import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class b implements g.a.b.k {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a f5124g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public p f5125h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public SecureRandom f5126i;

    public b() {
        this.f5124g = new m();
    }

    public b(a aVar) {
        this.f5124g = aVar;
    }

    @Override // g.a.b.k
    public BigInteger[] a(byte[] bArr) {
        r rVarB = this.f5125h.b();
        BigInteger bigIntegerC = rVarB.c();
        BigInteger bigIntegerC2 = c(bigIntegerC, bArr);
        BigInteger bigIntegerC3 = ((s) this.f5125h).c();
        if (this.f5124g.c()) {
            this.f5124g.d(bigIntegerC, bigIntegerC3, bArr);
        } else {
            this.f5124g.b(bigIntegerC, this.f5126i);
        }
        BigInteger bigIntegerA = this.f5124g.a();
        BigInteger bigIntegerMod = rVarB.a().modPow(bigIntegerA.add(d(bigIntegerC, this.f5126i)), rVarB.b()).mod(bigIntegerC);
        return new BigInteger[]{bigIntegerMod, bigIntegerA.modInverse(bigIntegerC).multiply(bigIntegerC2.add(bigIntegerC3.multiply(bigIntegerMod))).mod(bigIntegerC)};
    }

    @Override // g.a.b.k
    public boolean b(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        r rVarB = this.f5125h.b();
        BigInteger bigIntegerC = rVarB.c();
        BigInteger bigIntegerC2 = c(bigIntegerC, bArr);
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        if (bigIntegerValueOf.compareTo(bigInteger) >= 0 || bigIntegerC.compareTo(bigInteger) <= 0 || bigIntegerValueOf.compareTo(bigInteger2) >= 0 || bigIntegerC.compareTo(bigInteger2) <= 0) {
            return false;
        }
        BigInteger bigIntegerModInverse = bigInteger2.modInverse(bigIntegerC);
        BigInteger bigIntegerMod = bigIntegerC2.multiply(bigIntegerModInverse).mod(bigIntegerC);
        BigInteger bigIntegerMod2 = bigInteger.multiply(bigIntegerModInverse).mod(bigIntegerC);
        BigInteger bigIntegerB = rVarB.b();
        return rVarB.a().modPow(bigIntegerMod, bigIntegerB).multiply(((t) this.f5125h).c().modPow(bigIntegerMod2, bigIntegerB)).mod(bigIntegerB).mod(bigIntegerC).equals(bigInteger);
    }

    public final BigInteger c(BigInteger bigInteger, byte[] bArr) {
        if (bigInteger.bitLength() >= bArr.length * 8) {
            return new BigInteger(1, bArr);
        }
        int iBitLength = bigInteger.bitLength() / 8;
        byte[] bArr2 = new byte[iBitLength];
        System.arraycopy(bArr, 0, bArr2, 0, iBitLength);
        return new BigInteger(1, bArr2);
    }

    public final BigInteger d(BigInteger bigInteger, SecureRandom secureRandom) {
        if (secureRandom == null) {
            secureRandom = g.a.b.j.b();
        }
        return new BigInteger(7, secureRandom).add(BigInteger.valueOf(128L)).multiply(bigInteger);
    }

    public SecureRandom e(boolean z, SecureRandom secureRandom) {
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
            g.a.b.j0.s r0 = (g.a.b.j0.s) r0
            r1.f5125h = r0
            java.security.SecureRandom r3 = r3.b()
            goto L1d
        L15:
            g.a.b.j0.s r3 = (g.a.b.j0.s) r3
            goto L1a
        L18:
            g.a.b.j0.t r3 = (g.a.b.j0.t) r3
        L1a:
            r1.f5125h = r3
            r3 = 0
        L1d:
            if (r2 == 0) goto L29
            g.a.b.m0.a r2 = r1.f5124g
            boolean r2 = r2.c()
            if (r2 != 0) goto L29
            r2 = 1
            goto L2a
        L29:
            r2 = 0
        L2a:
            java.security.SecureRandom r2 = r1.e(r2, r3)
            r1.f5126i = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: g.a.b.m0.b.init(boolean, g.a.b.i):void");
    }
}
