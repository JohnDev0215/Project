package g.a.d.b;

import g.a.d.b.d;
import g.a.d.b.h;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final BigInteger f5526a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final BigInteger f5527b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final BigInteger f5528c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final z[] f5529d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[][] f5530e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final z[] f5531f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final byte[][] f5532g;

    static {
        BigInteger bigInteger = c.f5471b;
        BigInteger bigIntegerNegate = bigInteger.negate();
        f5526a = bigIntegerNegate;
        f5527b = c.f5472c.negate();
        BigInteger bigIntegerNegate2 = c.f5473d.negate();
        f5528c = bigIntegerNegate2;
        BigInteger bigInteger2 = c.f5470a;
        f5529d = new z[]{null, new z(bigInteger, bigInteger2), null, new z(bigIntegerNegate2, bigIntegerNegate), null, new z(bigIntegerNegate, bigIntegerNegate), null, new z(bigInteger, bigIntegerNegate), null};
        f5530e = new byte[][]{null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, 1}};
        f5531f = new z[]{null, new z(bigInteger, bigInteger2), null, new z(bigIntegerNegate2, bigInteger), null, new z(bigIntegerNegate, bigInteger), null, new z(bigInteger, bigInteger), null};
        f5532g = new byte[][]{null, new byte[]{1}, null, new byte[]{-1, 0, 1}, null, new byte[]{1, 0, 1}, null, new byte[]{-1, 0, 0, -1}};
    }

    public static r a(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, byte b2, int i2, int i3) {
        BigInteger bigIntegerMultiply = bigInteger2.multiply(bigInteger.shiftRight(((i2 - r0) - 2) + b2));
        BigInteger bigIntegerAdd = bigIntegerMultiply.add(bigInteger3.multiply(bigIntegerMultiply.shiftRight(i2)));
        int i4 = (((i2 + 5) / 2) + i3) - i3;
        BigInteger bigIntegerShiftRight = bigIntegerAdd.shiftRight(i4);
        if (bigIntegerAdd.testBit(i4 - 1)) {
            bigIntegerShiftRight = bigIntegerShiftRight.add(c.f5471b);
        }
        return new r(bigIntegerShiftRight, i3);
    }

    public static BigInteger[] b(byte b2, int i2, boolean z) {
        BigInteger bigInteger;
        BigInteger bigIntegerSubtract;
        if (b2 != 1 && b2 != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        if (z) {
            bigInteger = c.f5472c;
            bigIntegerSubtract = BigInteger.valueOf(b2);
        } else {
            bigInteger = c.f5470a;
            bigIntegerSubtract = c.f5471b;
        }
        int i3 = 1;
        while (i3 < i2) {
            i3++;
            BigInteger bigInteger2 = bigIntegerSubtract;
            bigIntegerSubtract = (b2 == 1 ? bigIntegerSubtract : bigIntegerSubtract.negate()).subtract(bigInteger.shiftLeft(1));
            bigInteger = bigInteger2;
        }
        return new BigInteger[]{bigInteger, bigIntegerSubtract};
    }

    public static byte c(int i2) {
        return (byte) (i2 == 0 ? -1 : 1);
    }

    public static h.b[] d(h.b bVar, byte b2) {
        byte[][] bArr = b2 == 0 ? f5530e : f5532g;
        h.b[] bVarArr = new h.b[(bArr.length + 1) >>> 1];
        bVarArr[0] = bVar;
        int length = bArr.length;
        for (int i2 = 3; i2 < length; i2 += 2) {
            bVarArr[i2 >>> 1] = h(bVar, bArr[i2]);
        }
        bVar.i().B(bVarArr);
        return bVarArr;
    }

    public static int e(BigInteger bigInteger) {
        if (bigInteger != null) {
            if (bigInteger.equals(c.f5472c)) {
                return 1;
            }
            if (bigInteger.equals(c.f5474e)) {
                return 2;
            }
        }
        throw new IllegalArgumentException("h (Cofactor) must be 2 or 4");
    }

    public static BigInteger[] f(d.b bVar) {
        if (!bVar.I()) {
            throw new IllegalArgumentException("si is defined for Koblitz curves only");
        }
        int iU = bVar.u();
        int iIntValue = bVar.o().t().intValue();
        byte bC = c(iIntValue);
        int iE = e(bVar.q());
        BigInteger[] bigIntegerArrB = b(bC, (iU + 3) - iIntValue, false);
        if (bC == 1) {
            bigIntegerArrB[0] = bigIntegerArrB[0].negate();
            bigIntegerArrB[1] = bigIntegerArrB[1].negate();
        }
        BigInteger bigInteger = c.f5471b;
        return new BigInteger[]{bigInteger.add(bigIntegerArrB[1]).shiftRight(iE), bigInteger.add(bigIntegerArrB[0]).shiftRight(iE).negate()};
    }

    public static BigInteger g(byte b2, int i2) {
        if (i2 == 4) {
            return b2 == 1 ? BigInteger.valueOf(6L) : BigInteger.valueOf(10L);
        }
        BigInteger[] bigIntegerArrB = b(b2, i2, false);
        BigInteger bit = c.f5470a.setBit(i2);
        return c.f5472c.multiply(bigIntegerArrB[0]).multiply(bigIntegerArrB[1].modInverse(bit)).mod(bit);
    }

    public static h.b h(h.b bVar, byte[] bArr) {
        h.b bVar2 = (h.b) bVar.i().v();
        h.b bVar3 = (h.b) bVar.z();
        int i2 = 0;
        for (int length = bArr.length - 1; length >= 0; length--) {
            i2++;
            byte b2 = bArr[length];
            if (b2 != 0) {
                bVar2 = (h.b) bVar2.L(i2).a(b2 > 0 ? bVar : bVar3);
                i2 = 0;
            }
        }
        return i2 > 0 ? bVar2.L(i2) : bVar2;
    }

    public static BigInteger i(byte b2, z zVar) {
        BigInteger bigIntegerSubtract;
        BigInteger bigInteger = zVar.f5553a;
        BigInteger bigIntegerMultiply = bigInteger.multiply(bigInteger);
        BigInteger bigIntegerMultiply2 = zVar.f5553a.multiply(zVar.f5554b);
        BigInteger bigInteger2 = zVar.f5554b;
        BigInteger bigIntegerShiftLeft = bigInteger2.multiply(bigInteger2).shiftLeft(1);
        if (b2 == 1) {
            bigIntegerSubtract = bigIntegerMultiply.add(bigIntegerMultiply2);
        } else {
            if (b2 != -1) {
                throw new IllegalArgumentException("mu must be 1 or -1");
            }
            bigIntegerSubtract = bigIntegerMultiply.subtract(bigIntegerMultiply2);
        }
        return bigIntegerSubtract.add(bigIntegerShiftLeft);
    }

    public static z j(BigInteger bigInteger, int i2, byte b2, BigInteger[] bigIntegerArr, byte b3, byte b4) {
        BigInteger bigIntegerAdd = b3 == 1 ? bigIntegerArr[0].add(bigIntegerArr[1]) : bigIntegerArr[0].subtract(bigIntegerArr[1]);
        BigInteger bigInteger2 = b(b3, i2, true)[1];
        z zVarK = k(a(bigInteger, bigIntegerArr[0], bigInteger2, b2, i2, b4), a(bigInteger, bigIntegerArr[1], bigInteger2, b2, i2, b4), b3);
        return new z(bigInteger.subtract(bigIntegerAdd.multiply(zVarK.f5553a)).subtract(BigInteger.valueOf(2L).multiply(bigIntegerArr[1]).multiply(zVarK.f5554b)), bigIntegerArr[1].multiply(zVarK.f5553a).subtract(bigIntegerArr[0].multiply(zVarK.f5554b)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0081, code lost:
    
        if (r5.d(r9) >= 0) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static g.a.d.b.z k(g.a.d.b.r r8, g.a.d.b.r r9, byte r10) {
        /*
            int r0 = r8.f()
            int r1 = r9.f()
            if (r1 != r0) goto La7
            r0 = -1
            r1 = 1
            if (r10 == r1) goto L19
            if (r10 != r0) goto L11
            goto L19
        L11:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "mu must be 1 or -1"
            r8.<init>(r9)
            throw r8
        L19:
            java.math.BigInteger r2 = r8.h()
            java.math.BigInteger r3 = r9.h()
            g.a.d.b.r r8 = r8.i(r2)
            g.a.d.b.r r9 = r9.i(r3)
            g.a.d.b.r r4 = r8.a(r8)
            if (r10 != r1) goto L34
            g.a.d.b.r r4 = r4.a(r9)
            goto L38
        L34:
            g.a.d.b.r r4 = r4.j(r9)
        L38:
            g.a.d.b.r r5 = r9.a(r9)
            g.a.d.b.r r5 = r5.a(r9)
            g.a.d.b.r r9 = r5.a(r9)
            if (r10 != r1) goto L4f
            g.a.d.b.r r5 = r8.j(r5)
            g.a.d.b.r r8 = r8.a(r9)
            goto L57
        L4f:
            g.a.d.b.r r5 = r8.a(r5)
            g.a.d.b.r r8 = r8.j(r9)
        L57:
            java.math.BigInteger r9 = g.a.d.b.c.f5471b
            int r6 = r4.d(r9)
            r7 = 0
            if (r6 < 0) goto L69
            java.math.BigInteger r6 = g.a.d.b.s.f5526a
            int r6 = r5.d(r6)
            if (r6 >= 0) goto L75
            goto L71
        L69:
            java.math.BigInteger r1 = g.a.d.b.c.f5472c
            int r1 = r8.d(r1)
            if (r1 < 0) goto L74
        L71:
            r1 = r7
            r7 = r10
            goto L75
        L74:
            r1 = r7
        L75:
            java.math.BigInteger r6 = g.a.d.b.s.f5526a
            int r4 = r4.d(r6)
            if (r4 >= 0) goto L84
            int r8 = r5.d(r9)
            if (r8 < 0) goto L8f
            goto L8c
        L84:
            java.math.BigInteger r9 = g.a.d.b.s.f5527b
            int r8 = r8.d(r9)
            if (r8 >= 0) goto L8e
        L8c:
            int r8 = -r10
            byte r7 = (byte) r8
        L8e:
            r0 = r1
        L8f:
            long r8 = (long) r0
            java.math.BigInteger r8 = java.math.BigInteger.valueOf(r8)
            java.math.BigInteger r8 = r2.add(r8)
            long r9 = (long) r7
            java.math.BigInteger r9 = java.math.BigInteger.valueOf(r9)
            java.math.BigInteger r9 = r3.add(r9)
            g.a.d.b.z r10 = new g.a.d.b.z
            r10.<init>(r8, r9)
            return r10
        La7:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "lambda0 and lambda1 do not have same scale"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: g.a.d.b.s.k(g.a.d.b.r, g.a.d.b.r, byte):g.a.d.b.z");
    }

    public static byte[] l(byte b2, z zVar, byte b3, BigInteger bigInteger, BigInteger bigInteger2, z[] zVarArr) {
        boolean z;
        if (b2 != 1 && b2 != -1) {
            throw new IllegalArgumentException("mu must be 1 or -1");
        }
        int iBitLength = i(b2, zVar).bitLength();
        byte[] bArr = new byte[iBitLength > 30 ? iBitLength + 4 + b3 : b3 + 34];
        BigInteger bigIntegerShiftRight = bigInteger.shiftRight(1);
        BigInteger bigIntegerAdd = zVar.f5553a;
        BigInteger bigIntegerAdd2 = zVar.f5554b;
        int i2 = 0;
        while (true) {
            BigInteger bigInteger3 = c.f5470a;
            if (bigIntegerAdd.equals(bigInteger3) && bigIntegerAdd2.equals(bigInteger3)) {
                return bArr;
            }
            if (bigIntegerAdd.testBit(0)) {
                BigInteger bigIntegerMod = bigIntegerAdd.add(bigIntegerAdd2.multiply(bigInteger2)).mod(bigInteger);
                if (bigIntegerMod.compareTo(bigIntegerShiftRight) >= 0) {
                    bigIntegerMod = bigIntegerMod.subtract(bigInteger);
                }
                byte bIntValue = (byte) bigIntegerMod.intValue();
                bArr[i2] = bIntValue;
                if (bIntValue < 0) {
                    bIntValue = (byte) (-bIntValue);
                    z = false;
                } else {
                    z = true;
                }
                if (z) {
                    bigIntegerAdd = bigIntegerAdd.subtract(zVarArr[bIntValue].f5553a);
                    bigIntegerAdd2 = bigIntegerAdd2.subtract(zVarArr[bIntValue].f5554b);
                } else {
                    bigIntegerAdd = bigIntegerAdd.add(zVarArr[bIntValue].f5553a);
                    bigIntegerAdd2 = bigIntegerAdd2.add(zVarArr[bIntValue].f5554b);
                }
            } else {
                bArr[i2] = 0;
            }
            BigInteger bigIntegerShiftRight2 = bigIntegerAdd.shiftRight(1);
            BigInteger bigIntegerAdd3 = b2 == 1 ? bigIntegerAdd2.add(bigIntegerShiftRight2) : bigIntegerAdd2.subtract(bigIntegerShiftRight2);
            BigInteger bigIntegerNegate = bigIntegerAdd.shiftRight(1).negate();
            i2++;
            bigIntegerAdd = bigIntegerAdd3;
            bigIntegerAdd2 = bigIntegerNegate;
        }
    }
}
