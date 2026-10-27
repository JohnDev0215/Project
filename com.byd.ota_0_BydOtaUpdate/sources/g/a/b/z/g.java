package g.a.b.z;

import g.a.b.i;
import g.a.b.j0.b0;
import g.a.b.j0.c0;
import g.a.b.j0.x;
import g.a.b.j0.z0;
import g.a.b.n;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f5207a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b0 f5208b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BigInteger f5209c;

    public g(n nVar) {
        this.f5207a = nVar;
    }

    public static BigInteger d(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            bArr2[i2] = bArr[(bArr.length - i2) - 1];
        }
        return new BigInteger(1, bArr2);
    }

    public byte[] a(i iVar) {
        c0 c0Var = (c0) iVar;
        x xVarB = this.f5208b.b();
        if (!xVarB.equals(c0Var.b())) {
            throw new IllegalStateException("ECVKO public key has wrong domain parameters");
        }
        BigInteger bigIntegerMod = xVarB.c().multiply(this.f5209c).multiply(this.f5208b.c()).mod(xVarB.e());
        g.a.d.b.h hVarA = g.a.d.b.b.a(xVarB.a(), c0Var.c());
        if (hVarA.u()) {
            throw new IllegalStateException("Infinity is not a valid public key for ECDHC");
        }
        g.a.d.b.h hVarA2 = hVarA.y(bigIntegerMod).A();
        if (hVarA2.u()) {
            throw new IllegalStateException("Infinity is not a valid agreement value for ECVKO");
        }
        return b(hVarA2);
    }

    public final byte[] b(g.a.d.b.h hVar) {
        BigInteger bigIntegerT = hVar.f().t();
        BigInteger bigIntegerT2 = hVar.g().t();
        int i2 = bigIntegerT.toByteArray().length > 33 ? 64 : 32;
        int i3 = i2 * 2;
        byte[] bArr = new byte[i3];
        byte[] bArrA = g.a.f.b.a(i2, bigIntegerT);
        byte[] bArrA2 = g.a.f.b.a(i2, bigIntegerT2);
        for (int i4 = 0; i4 != i2; i4++) {
            bArr[i4] = bArrA[(i2 - i4) - 1];
        }
        for (int i5 = 0; i5 != i2; i5++) {
            bArr[i2 + i5] = bArrA2[(i2 - i5) - 1];
        }
        this.f5207a.update(bArr, 0, i3);
        byte[] bArr2 = new byte[this.f5207a.getDigestSize()];
        this.f5207a.doFinal(bArr2, 0);
        return bArr2;
    }

    public void c(i iVar) {
        z0 z0Var = (z0) iVar;
        this.f5208b = (b0) z0Var.a();
        this.f5209c = d(z0Var.b());
    }
}
