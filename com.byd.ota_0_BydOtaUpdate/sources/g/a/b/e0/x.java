package g.a.b.e0;

import g.a.b.j0.d1;
import g.a.b.j0.e1;
import g.a.b.j0.f1;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class x implements g.a.b.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final BigInteger f4731g = BigInteger.valueOf(1);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public d1 f4732h;

    public static int c(int i2, int i3) {
        if (i2 >= 1536) {
            if (i3 <= 100) {
                return 3;
            }
            if (i3 <= 128) {
                return 4;
            }
            return 4 + (((i3 - 128) + 1) / 2);
        }
        if (i2 >= 1024) {
            if (i3 <= 100) {
                return 4;
            }
            if (i3 <= 112) {
                return 5;
            }
            return (((i3 - 112) + 1) / 2) + 5;
        }
        if (i2 < 512) {
            if (i3 <= 80) {
                return 40;
            }
            return 40 + (((i3 - 80) + 1) / 2);
        }
        if (i3 <= 80) {
            return 5;
        }
        if (i3 <= 100) {
            return 7;
        }
        return (((i3 - 100) + 1) / 2) + 7;
    }

    @Override // g.a.b.c
    public g.a.b.b a() {
        BigInteger bigIntegerB;
        BigInteger bigIntegerB2;
        BigInteger bigIntegerMultiply;
        BigInteger bigInteger;
        x xVar = this;
        int iB = xVar.f4732h.b();
        int i2 = (iB + 1) / 2;
        int i3 = iB - i2;
        int i4 = iB / 2;
        int i5 = i4 - 100;
        int i6 = iB / 3;
        if (i5 < i6) {
            i5 = i6;
        }
        int i7 = iB >> 2;
        BigInteger bigIntegerPow = BigInteger.valueOf(2L).pow(i4);
        BigInteger bigInteger2 = f4731g;
        BigInteger bigIntegerShiftLeft = bigInteger2.shiftLeft(iB - 1);
        BigInteger bigIntegerShiftLeft2 = bigInteger2.shiftLeft(i5);
        g.a.b.b bVar = null;
        boolean z = false;
        while (!z) {
            BigInteger bigIntegerD = xVar.f4732h.d();
            do {
                bigIntegerB = xVar.b(i2, bigIntegerD, bigIntegerShiftLeft);
                while (true) {
                    bigIntegerB2 = xVar.b(i3, bigIntegerD, bigIntegerShiftLeft);
                    BigInteger bigIntegerAbs = bigIntegerB2.subtract(bigIntegerB).abs();
                    if (bigIntegerAbs.bitLength() >= i5 && bigIntegerAbs.compareTo(bigIntegerShiftLeft2) > 0) {
                        bigIntegerMultiply = bigIntegerB.multiply(bigIntegerB2);
                        if (bigIntegerMultiply.bitLength() == iB) {
                            break;
                        }
                        bigIntegerB = bigIntegerB.max(bigIntegerB2);
                    } else {
                        xVar = this;
                        iB = iB;
                    }
                }
            } while (g.a.d.b.w.g(bigIntegerMultiply) < i7);
            if (bigIntegerB.compareTo(bigIntegerB2) < 0) {
                bigInteger = bigIntegerB;
                bigIntegerB = bigIntegerB2;
            } else {
                bigInteger = bigIntegerB2;
            }
            BigInteger bigInteger3 = f4731g;
            BigInteger bigIntegerSubtract = bigIntegerB.subtract(bigInteger3);
            BigInteger bigIntegerSubtract2 = bigInteger.subtract(bigInteger3);
            int i8 = iB;
            BigInteger bigIntegerModInverse = bigIntegerD.modInverse(bigIntegerSubtract.divide(bigIntegerSubtract.gcd(bigIntegerSubtract2)).multiply(bigIntegerSubtract2));
            if (bigIntegerModInverse.compareTo(bigIntegerPow) <= 0) {
                xVar = this;
                iB = i8;
            } else {
                bVar = new g.a.b.b(new e1(false, bigIntegerMultiply, bigIntegerD), new f1(bigIntegerMultiply, bigIntegerD, bigIntegerModInverse, bigIntegerB, bigInteger, bigIntegerModInverse.remainder(bigIntegerSubtract), bigIntegerModInverse.remainder(bigIntegerSubtract2), bigInteger.modInverse(bigIntegerB)));
                z = true;
                iB = i8;
                xVar = this;
            }
        }
        return bVar;
    }

    public BigInteger b(int i2, BigInteger bigInteger, BigInteger bigInteger2) {
        for (int i3 = 0; i3 != i2 * 5; i3++) {
            BigInteger bigInteger3 = new BigInteger(i2, 1, this.f4732h.a());
            BigInteger bigIntegerMod = bigInteger3.mod(bigInteger);
            BigInteger bigInteger4 = f4731g;
            if (!bigIntegerMod.equals(bigInteger4) && bigInteger3.multiply(bigInteger3).compareTo(bigInteger2) >= 0 && e(bigInteger3) && bigInteger.gcd(bigInteger3.subtract(bigInteger4)).equals(bigInteger4)) {
                return bigInteger3;
            }
        }
        throw new IllegalStateException("unable to generate prime number for RSA key");
    }

    public void d(g.a.b.r rVar) {
        this.f4732h = (d1) rVar;
    }

    public boolean e(BigInteger bigInteger) {
        return !g.a.d.a.b(bigInteger) && g.a.d.a.e(bigInteger, this.f4732h.a(), c(bigInteger.bitLength(), this.f4732h.c()));
    }
}
