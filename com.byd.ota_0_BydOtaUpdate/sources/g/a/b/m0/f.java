package g.a.b.m0;

import g.a.b.j0.b0;
import g.a.b.j0.c0;
import g.a.b.j0.x;
import g.a.b.j0.x0;
import g.a.b.j0.z;
import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class f implements g.a.b.k {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public z f5135g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public SecureRandom f5136h;

    @Override // g.a.b.k
    public BigInteger[] a(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr2[i2] = bArr[(length - 1) - i2];
        }
        BigInteger bigInteger = new BigInteger(1, bArr2);
        x xVarB = this.f5135g.b();
        BigInteger bigIntegerE = xVarB.e();
        BigInteger bigIntegerC = ((b0) this.f5135g).c();
        g.a.d.b.g gVarC = c();
        while (true) {
            BigInteger bigInteger2 = new BigInteger(bigIntegerE.bitLength(), this.f5136h);
            BigInteger bigInteger3 = g.a.d.b.c.f5470a;
            if (!bigInteger2.equals(bigInteger3)) {
                BigInteger bigIntegerMod = gVarC.a(xVarB.b(), bigInteger2).A().f().t().mod(bigIntegerE);
                if (bigIntegerMod.equals(bigInteger3)) {
                    continue;
                } else {
                    BigInteger bigIntegerMod2 = bigInteger2.multiply(bigInteger).add(bigIntegerC.multiply(bigIntegerMod)).mod(bigIntegerE);
                    if (!bigIntegerMod2.equals(bigInteger3)) {
                        return new BigInteger[]{bigIntegerMod, bigIntegerMod2};
                    }
                }
            }
        }
    }

    @Override // g.a.b.k
    public boolean b(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr2[i2] = bArr[(length - 1) - i2];
        }
        BigInteger bigInteger3 = new BigInteger(1, bArr2);
        BigInteger bigIntegerE = this.f5135g.b().e();
        BigInteger bigInteger4 = g.a.d.b.c.f5471b;
        if (bigInteger.compareTo(bigInteger4) < 0 || bigInteger.compareTo(bigIntegerE) >= 0 || bigInteger2.compareTo(bigInteger4) < 0 || bigInteger2.compareTo(bigIntegerE) >= 0) {
            return false;
        }
        BigInteger bigIntegerModInverse = bigInteger3.modInverse(bigIntegerE);
        g.a.d.b.h hVarA = g.a.d.b.b.q(this.f5135g.b().b(), bigInteger2.multiply(bigIntegerModInverse).mod(bigIntegerE), ((c0) this.f5135g).c(), bigIntegerE.subtract(bigInteger).multiply(bigIntegerModInverse).mod(bigIntegerE)).A();
        if (hVarA.u()) {
            return false;
        }
        return hVarA.f().t().mod(bigIntegerE).equals(bigInteger);
    }

    public g.a.d.b.g c() {
        return new g.a.d.b.j();
    }

    @Override // g.a.b.k
    public void init(boolean z, g.a.b.i iVar) {
        z zVar;
        if (!z) {
            zVar = (c0) iVar;
        } else {
            if (iVar instanceof x0) {
                x0 x0Var = (x0) iVar;
                this.f5136h = x0Var.b();
                this.f5135g = (b0) x0Var.a();
                return;
            }
            this.f5136h = g.a.b.j.b();
            zVar = (b0) iVar;
        }
        this.f5135g = zVar;
    }
}
