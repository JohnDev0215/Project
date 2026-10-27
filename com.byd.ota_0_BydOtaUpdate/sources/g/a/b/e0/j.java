package g.a.b.e0;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f4701a = BigInteger.valueOf(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BigInteger f4702b = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final BigInteger f4703c = BigInteger.valueOf(2);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public g.a.b.n f4704d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4705e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4706f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4707g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4708h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public SecureRandom f4709i;
    public boolean j;
    public int k;

    public j() {
        this(g.a.b.n0.a.b());
    }

    public j(g.a.b.n nVar) {
        this.f4704d = nVar;
    }

    public static BigInteger a(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        BigInteger bigIntegerModPow;
        BigInteger bigIntegerDivide = bigInteger.subtract(f4702b).divide(bigInteger2);
        BigInteger bigIntegerSubtract = bigInteger.subtract(f4703c);
        do {
            bigIntegerModPow = g.a.f.b.c(f4703c, bigIntegerSubtract, secureRandom).modPow(bigIntegerDivide, bigInteger);
        } while (bigIntegerModPow.bitLength() <= 1);
        return bigIntegerModPow;
    }

    public static BigInteger b(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        return a(bigInteger, bigInteger2, secureRandom);
    }

    public static BigInteger c(g.a.b.n nVar, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr, int i2) {
        BigInteger bigIntegerDivide = bigInteger.subtract(f4702b).divide(bigInteger2);
        byte[] bArrA = g.a.f.j.d.a("6767656E");
        int length = bArr.length + bArrA.length + 1 + 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        System.arraycopy(bArrA, 0, bArr2, bArr.length, bArrA.length);
        bArr2[length - 3] = (byte) i2;
        byte[] bArr3 = new byte[nVar.getDigestSize()];
        for (int i3 = 1; i3 < 65536; i3++) {
            j(bArr2);
            i(nVar, bArr2, bArr3, 0);
            BigInteger bigIntegerModPow = new BigInteger(1, bArr3).modPow(bigIntegerDivide, bigInteger);
            if (bigIntegerModPow.compareTo(f4703c) >= 0) {
                return bigIntegerModPow;
            }
        }
        return null;
    }

    public static int g(int i2) {
        return i2 > 1024 ? 256 : 160;
    }

    public static int h(int i2) {
        if (i2 <= 1024) {
            return 40;
        }
        return (((i2 - 1) / 1024) * 8) + 48;
    }

    public static void i(g.a.b.n nVar, byte[] bArr, byte[] bArr2, int i2) {
        nVar.update(bArr, 0, bArr.length);
        nVar.doFinal(bArr2, i2);
    }

    public static void j(byte[] bArr) {
        for (int length = bArr.length - 1; length >= 0; length--) {
            byte b2 = (byte) ((bArr[length] + 1) & 255);
            bArr[length] = b2;
            if (b2 != 0) {
                return;
            }
        }
    }

    public g.a.b.j0.r d() {
        return this.j ? f() : e();
    }

    public final g.a.b.j0.r e() {
        int i2 = 20;
        byte[] bArr = new byte[20];
        byte[] bArr2 = new byte[20];
        byte[] bArr3 = new byte[20];
        byte[] bArr4 = new byte[20];
        int i3 = this.f4705e;
        int i4 = (i3 - 1) / 160;
        int i5 = i3 / 8;
        byte[] bArr5 = new byte[i5];
        if (!(this.f4704d instanceof g.a.b.a0.s)) {
            throw new IllegalStateException("can only use SHA-1 for generating FIPS 186-2 parameters");
        }
        while (true) {
            this.f4709i.nextBytes(bArr);
            i(this.f4704d, bArr, bArr2, 0);
            System.arraycopy(bArr, 0, bArr3, 0, i2);
            j(bArr3);
            i(this.f4704d, bArr3, bArr3, 0);
            for (int i6 = 0; i6 != i2; i6++) {
                bArr4[i6] = (byte) (bArr2[i6] ^ bArr3[i6]);
            }
            bArr4[0] = (byte) (bArr4[0] | (-128));
            bArr4[19] = (byte) (bArr4[19] | 1);
            BigInteger bigInteger = new BigInteger(1, bArr4);
            if (m(bigInteger)) {
                byte[] bArrF = g.a.f.a.f(bArr);
                j(bArrF);
                int i7 = 0;
                while (i7 < 4096) {
                    for (int i8 = 1; i8 <= i4; i8++) {
                        j(bArrF);
                        i(this.f4704d, bArrF, bArr5, i5 - (i8 * 20));
                    }
                    int i9 = i5 - (i4 * 20);
                    j(bArrF);
                    i(this.f4704d, bArrF, bArr2, 0);
                    System.arraycopy(bArr2, 20 - i9, bArr5, 0, i9);
                    bArr5[0] = (byte) (bArr5[0] | (-128));
                    BigInteger bigInteger2 = new BigInteger(1, bArr5);
                    BigInteger bigIntegerSubtract = bigInteger2.subtract(bigInteger2.mod(bigInteger.shiftLeft(1)).subtract(f4702b));
                    if (bigIntegerSubtract.bitLength() == this.f4705e && m(bigIntegerSubtract)) {
                        return new g.a.b.j0.r(bigIntegerSubtract, bigInteger, a(bigIntegerSubtract, bigInteger, this.f4709i), new g.a.b.j0.u(bArr, i7));
                    }
                    i7++;
                    i2 = 20;
                }
            }
        }
    }

    public final g.a.b.j0.r f() {
        BigInteger bit;
        int i2;
        BigInteger bigIntegerSubtract;
        BigInteger bigIntegerC;
        g.a.b.n nVar = this.f4704d;
        int digestSize = nVar.getDigestSize() * 8;
        byte[] bArr = new byte[this.f4706f / 8];
        int i3 = this.f4705e;
        int i4 = (i3 - 1) / digestSize;
        int i5 = (i3 - 1) % digestSize;
        int i6 = i3 / 8;
        byte[] bArr2 = new byte[i6];
        int digestSize2 = nVar.getDigestSize();
        byte[] bArr3 = new byte[digestSize2];
        loop0: while (true) {
            this.f4709i.nextBytes(bArr);
            int i7 = 0;
            i(nVar, bArr, bArr3, 0);
            bit = new BigInteger(1, bArr3).mod(f4702b.shiftLeft(this.f4706f - 1)).setBit(0).setBit(this.f4706f - 1);
            if (m(bit)) {
                byte[] bArrF = g.a.f.a.f(bArr);
                int i8 = this.f4705e * 4;
                i2 = 0;
                while (i2 < i8) {
                    for (int i9 = 1; i9 <= i4; i9++) {
                        j(bArrF);
                        i(nVar, bArrF, bArr2, i6 - (i9 * digestSize2));
                    }
                    int i10 = i6 - (i4 * digestSize2);
                    j(bArrF);
                    i(nVar, bArrF, bArr3, i7);
                    System.arraycopy(bArr3, digestSize2 - i10, bArr2, i7, i10);
                    bArr2[i7] = (byte) (bArr2[i7] | (-128));
                    BigInteger bigInteger = new BigInteger(1, bArr2);
                    bigIntegerSubtract = bigInteger.subtract(bigInteger.mod(bit.shiftLeft(1)).subtract(f4702b));
                    if (bigIntegerSubtract.bitLength() == this.f4705e && m(bigIntegerSubtract)) {
                        break loop0;
                    }
                    i2++;
                    i7 = 0;
                }
            }
        }
        int i11 = this.k;
        return (i11 < 0 || (bigIntegerC = c(nVar, bigIntegerSubtract, bit, bArr, i11)) == null) ? new g.a.b.j0.r(bigIntegerSubtract, bit, b(bigIntegerSubtract, bit, this.f4709i), new g.a.b.j0.u(bArr, i2)) : new g.a.b.j0.r(bigIntegerSubtract, bit, bigIntegerC, new g.a.b.j0.u(bArr, i2, this.k));
    }

    public void k(int i2, int i3, SecureRandom secureRandom) {
        this.f4705e = i2;
        this.f4706f = g(i2);
        this.f4707g = i3;
        this.f4708h = Math.max(h(this.f4705e), (i3 + 1) / 2);
        this.f4709i = secureRandom;
        this.j = false;
        this.k = -1;
    }

    public void l(g.a.b.j0.q qVar) {
        int iB = qVar.b();
        int iC = qVar.c();
        if (iB < 1024 || iB > 3072 || iB % 1024 != 0) {
            throw new IllegalArgumentException("L values must be between 1024 and 3072 and a multiple of 1024");
        }
        if (iB == 1024 && iC != 160) {
            throw new IllegalArgumentException("N must be 160 for L = 1024");
        }
        if (iB == 2048 && iC != 224 && iC != 256) {
            throw new IllegalArgumentException("N must be 224 or 256 for L = 2048");
        }
        if (iB == 3072 && iC != 256) {
            throw new IllegalArgumentException("N must be 256 for L = 3072");
        }
        if (this.f4704d.getDigestSize() * 8 < iC) {
            throw new IllegalStateException("Digest output size too small for value of N");
        }
        this.f4705e = iB;
        this.f4706f = iC;
        this.f4707g = qVar.a();
        this.f4708h = Math.max(h(iB), (this.f4707g + 1) / 2);
        this.f4709i = qVar.d();
        this.j = true;
        this.k = qVar.e();
    }

    public final boolean m(BigInteger bigInteger) {
        return bigInteger.isProbablePrime(this.f4707g);
    }
}
