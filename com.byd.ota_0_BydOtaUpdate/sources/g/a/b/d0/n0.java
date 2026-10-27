package g.a.b.d0;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class n0 implements g.a.b.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f4532a = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public o0 f4533b = new o0();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public g.a.b.j0.e1 f4534c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SecureRandom f4535d;

    @Override // g.a.b.a
    public int a() {
        return this.f4533b.d();
    }

    @Override // g.a.b.a
    public byte[] b(byte[] bArr, int i2, int i3) {
        BigInteger bigIntegerF;
        g.a.b.j0.f1 f1Var;
        BigInteger bigIntegerH;
        if (this.f4534c == null) {
            throw new IllegalStateException("RSA engine not initialised");
        }
        BigInteger bigIntegerA = this.f4533b.a(bArr, i2, i3);
        g.a.b.j0.e1 e1Var = this.f4534c;
        if (!(e1Var instanceof g.a.b.j0.f1) || (bigIntegerH = (f1Var = (g.a.b.j0.f1) e1Var).h()) == null) {
            bigIntegerF = this.f4533b.f(bigIntegerA);
        } else {
            BigInteger bigIntegerC = f1Var.c();
            BigInteger bigInteger = f4532a;
            BigInteger bigIntegerC2 = g.a.f.b.c(bigInteger, bigIntegerC.subtract(bigInteger), this.f4535d);
            bigIntegerF = this.f4533b.f(bigIntegerC2.modPow(bigIntegerH, bigIntegerC).multiply(bigIntegerA).mod(bigIntegerC)).multiply(bigIntegerC2.modInverse(bigIntegerC)).mod(bigIntegerC);
            if (!bigIntegerA.equals(bigIntegerF.modPow(bigIntegerH, bigIntegerC))) {
                throw new IllegalStateException("RSA engine faulty decryption/signing detected");
            }
        }
        return this.f4533b.b(bigIntegerF);
    }

    @Override // g.a.b.a
    public int c() {
        return this.f4533b.c();
    }

    @Override // g.a.b.a
    public void init(boolean z, g.a.b.i iVar) {
        SecureRandom secureRandomB;
        this.f4533b.e(z, iVar);
        if (iVar instanceof g.a.b.j0.x0) {
            g.a.b.j0.x0 x0Var = (g.a.b.j0.x0) iVar;
            this.f4534c = (g.a.b.j0.e1) x0Var.a();
            secureRandomB = x0Var.b();
        } else {
            this.f4534c = (g.a.b.j0.e1) iVar;
            secureRandomB = g.a.b.j.b();
        }
        this.f4535d = secureRandomB;
    }
}
