package g.a.b.m0;

import g.a.b.j0.e1;
import java.math.BigInteger;
import org.bouncycastle.crypto.CryptoException;

/* JADX INFO: loaded from: classes.dex */
public class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public g.a.b.n f5172a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g.a.b.a f5173b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public e1 f5174c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5175d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5176e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public byte[] f5177f;

    public o(g.a.b.a aVar, g.a.b.n nVar) {
        this(aVar, nVar, false);
    }

    public o(g.a.b.a aVar, g.a.b.n nVar, boolean z) {
        int iIntValue;
        this.f5173b = aVar;
        this.f5172a = nVar;
        if (z) {
            iIntValue = 188;
        } else {
            Integer numA = k.a(nVar);
            if (numA == null) {
                throw new IllegalArgumentException("no valid trailer for digest: " + nVar.getAlgorithmName());
            }
            iIntValue = numA.intValue();
        }
        this.f5175d = iIntValue;
    }

    public final void a(byte[] bArr) {
        for (int i2 = 0; i2 != bArr.length; i2++) {
            bArr[i2] = 0;
        }
    }

    public final void b(int i2) {
        int length;
        int digestSize = this.f5172a.getDigestSize();
        if (i2 == 188) {
            byte[] bArr = this.f5177f;
            length = (bArr.length - digestSize) - 1;
            this.f5172a.doFinal(bArr, length);
            this.f5177f[r5.length - 1] = -68;
        } else {
            byte[] bArr2 = this.f5177f;
            int length2 = (bArr2.length - digestSize) - 2;
            this.f5172a.doFinal(bArr2, length2);
            byte[] bArr3 = this.f5177f;
            bArr3[bArr3.length - 2] = (byte) (i2 >>> 8);
            bArr3[bArr3.length - 1] = (byte) i2;
            length = length2;
        }
        this.f5177f[0] = 107;
        for (int i3 = length - 2; i3 != 0; i3--) {
            this.f5177f[i3] = -69;
        }
        this.f5177f[length - 1] = -70;
    }

    public byte[] c() throws CryptoException {
        b(this.f5175d);
        g.a.b.a aVar = this.f5173b;
        byte[] bArr = this.f5177f;
        BigInteger bigInteger = new BigInteger(1, aVar.b(bArr, 0, bArr.length));
        a(this.f5177f);
        return g.a.f.b.a((this.f5174c.c().bitLength() + 7) / 8, bigInteger.min(this.f5174c.c().subtract(bigInteger)));
    }

    public void d(boolean z, g.a.b.i iVar) {
        e1 e1Var = (e1) iVar;
        this.f5174c = e1Var;
        this.f5173b.init(z, e1Var);
        int iBitLength = this.f5174c.c().bitLength();
        this.f5176e = iBitLength;
        this.f5177f = new byte[(iBitLength + 7) / 8];
        e();
    }

    public void e() {
        this.f5172a.reset();
    }

    public void f(byte b2) {
        this.f5172a.update(b2);
    }

    public void g(byte[] bArr, int i2, int i3) {
        this.f5172a.update(bArr, i2, i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x002f A[PHI: r4
  0x002f: PHI (r4v3 java.math.BigInteger) = (r4v2 java.math.BigInteger), (r4v5 java.math.BigInteger) binds: [B:5:0x001a, B:8:0x002d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean h(byte[] r4) {
        /*
            r3 = this;
            r0 = 0
            g.a.b.a r1 = r3.f5173b     // Catch: java.lang.Exception -> L5e
            int r2 = r4.length     // Catch: java.lang.Exception -> L5e
            byte[] r4 = r1.b(r4, r0, r2)     // Catch: java.lang.Exception -> L5e
            r3.f5177f = r4     // Catch: java.lang.Exception -> L5e
            java.math.BigInteger r4 = new java.math.BigInteger
            r1 = 1
            byte[] r2 = r3.f5177f
            r4.<init>(r1, r2)
            int r1 = r4.intValue()
            r1 = r1 & 15
            r2 = 12
            if (r1 != r2) goto L1d
            goto L2f
        L1d:
            g.a.b.j0.e1 r1 = r3.f5174c
            java.math.BigInteger r1 = r1.c()
            java.math.BigInteger r4 = r1.subtract(r4)
            int r1 = r4.intValue()
            r1 = r1 & 15
            if (r1 != r2) goto L5e
        L2f:
            int r0 = r3.f5175d
            r3.b(r0)
            byte[] r0 = r3.f5177f
            int r0 = r0.length
            byte[] r4 = g.a.f.b.a(r0, r4)
            byte[] r0 = r3.f5177f
            boolean r0 = g.a.f.a.q(r0, r4)
            int r1 = r3.f5175d
            r2 = 15052(0x3acc, float:2.1092E-41)
            if (r1 != r2) goto L56
            if (r0 != 0) goto L56
            byte[] r0 = r3.f5177f
            int r1 = r0.length
            int r1 = r1 + (-2)
            r2 = 64
            r0[r1] = r2
            boolean r0 = g.a.f.a.q(r0, r4)
        L56:
            byte[] r1 = r3.f5177f
            r3.a(r1)
            r3.a(r4)
        L5e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: g.a.b.m0.o.h(byte[]):boolean");
    }
}
