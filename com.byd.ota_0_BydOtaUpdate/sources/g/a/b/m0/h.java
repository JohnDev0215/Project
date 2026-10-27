package g.a.b.m0;

import g.a.b.j0.j0;
import g.a.b.j0.k0;
import g.a.b.j0.l0;
import g.a.b.j0.m0;
import g.a.b.j0.x0;
import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class h implements g.a.b.k {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public j0 f5140g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public SecureRandom f5141h;

    @Override // g.a.b.k
    public BigInteger[] a(byte[] bArr) {
        BigInteger bigInteger;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr2[i2] = bArr[(length - 1) - i2];
        }
        BigInteger bigInteger2 = new BigInteger(1, bArr2);
        k0 k0VarB = this.f5140g.b();
        do {
            bigInteger = new BigInteger(k0VarB.c().bitLength(), this.f5141h);
        } while (bigInteger.compareTo(k0VarB.c()) >= 0);
        BigInteger bigIntegerMod = k0VarB.a().modPow(bigInteger, k0VarB.b()).mod(k0VarB.c());
        return new BigInteger[]{bigIntegerMod, bigInteger.multiply(bigInteger2).add(((l0) this.f5140g).c().multiply(bigIntegerMod)).mod(k0VarB.c())};
    }

    @Override // g.a.b.k
    public boolean b(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr2[i2] = bArr[(length - 1) - i2];
        }
        BigInteger bigInteger3 = new BigInteger(1, bArr2);
        k0 k0VarB = this.f5140g.b();
        BigInteger bigIntegerValueOf = BigInteger.valueOf(0L);
        if (bigIntegerValueOf.compareTo(bigInteger) >= 0 || k0VarB.c().compareTo(bigInteger) <= 0 || bigIntegerValueOf.compareTo(bigInteger2) >= 0 || k0VarB.c().compareTo(bigInteger2) <= 0) {
            return false;
        }
        BigInteger bigIntegerModPow = bigInteger3.modPow(k0VarB.c().subtract(new BigInteger("2")), k0VarB.c());
        return k0VarB.a().modPow(bigInteger2.multiply(bigIntegerModPow).mod(k0VarB.c()), k0VarB.b()).multiply(((m0) this.f5140g).c().modPow(k0VarB.c().subtract(bigInteger).multiply(bigIntegerModPow).mod(k0VarB.c()), k0VarB.b())).mod(k0VarB.b()).mod(k0VarB.c()).equals(bigInteger);
    }

    @Override // g.a.b.k
    public void init(boolean z, g.a.b.i iVar) {
        j0 j0Var;
        if (!z) {
            j0Var = (m0) iVar;
        } else {
            if (iVar instanceof x0) {
                x0 x0Var = (x0) iVar;
                this.f5141h = x0Var.b();
                this.f5140g = (l0) x0Var.a();
                return;
            }
            this.f5141h = g.a.b.j.b();
            j0Var = (l0) iVar;
        }
        this.f5140g = j0Var;
    }
}
