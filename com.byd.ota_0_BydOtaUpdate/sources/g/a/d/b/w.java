package g.a.d.b;

import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f5539a = {13, 41, 121, 337, 897, 2305};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f5540b = new byte[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f5541c = new int[0];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final h[] f5542d = new h[0];

    public static class a implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ v f5543a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ i f5544b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ boolean f5545c;

        public a(v vVar, i iVar, boolean z) {
            this.f5543a = vVar;
            this.f5544b = iVar;
            this.f5545c = z;
        }

        @Override // g.a.d.b.o
        public p a(p pVar) {
            v vVar = new v();
            h hVarC = this.f5543a.c();
            if (hVarC != null) {
                vVar.f(this.f5544b.a(hVarC));
            }
            h[] hVarArrA = this.f5543a.a();
            int length = hVarArrA.length;
            h[] hVarArr = new h[length];
            for (int i2 = 0; i2 < hVarArrA.length; i2++) {
                hVarArr[i2] = this.f5544b.a(hVarArrA[i2]);
            }
            vVar.d(hVarArr);
            if (this.f5545c) {
                h[] hVarArr2 = new h[length];
                for (int i3 = 0; i3 < length; i3++) {
                    hVarArr2[i3] = hVarArr[i3].z();
                }
                vVar.e(hVarArr2);
            }
            return vVar;
        }
    }

    public static class b implements o {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5546a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ boolean f5547b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ h f5548c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ d f5549d;

        public b(int i2, boolean z, h hVar, d dVar) {
            this.f5546a = i2;
            this.f5547b = z;
            this.f5548c = hVar;
            this.f5549d = dVar;
        }

        /* JADX WARN: Removed duplicated region for block: B:42:0x00c2 A[PHI: r12
  0x00c2: PHI (r12v6 g.a.d.b.h) = (r12v4 g.a.d.b.h), (r12v9 g.a.d.b.h), (r12v9 g.a.d.b.h), (r12v9 g.a.d.b.h), (r12v9 g.a.d.b.h) binds: [B:25:0x0064, B:27:0x0070, B:29:0x0078, B:31:0x0082, B:37:0x0090] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00ea A[LOOP:1: B:54:0x00e8->B:55:0x00ea, LOOP_END] */
        @Override // g.a.d.b.o
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public g.a.d.b.p a(g.a.d.b.p r12) {
            /*
                Method dump skipped, instruction units count: 260
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: g.a.d.b.w.b.a(g.a.d.b.p):g.a.d.b.p");
        }

        public final boolean b(v vVar, int i2, boolean z) {
            return vVar != null && c(vVar.a(), i2) && (!z || c(vVar.b(), i2));
        }

        public final boolean c(h[] hVarArr, int i2) {
            return hVarArr != null && hVarArr.length >= i2;
        }
    }

    public static int[] c(BigInteger bigInteger) {
        if ((bigInteger.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        }
        if (bigInteger.signum() == 0) {
            return f5541c;
        }
        BigInteger bigIntegerAdd = bigInteger.shiftLeft(1).add(bigInteger);
        int iBitLength = bigIntegerAdd.bitLength();
        int i2 = iBitLength >> 1;
        int[] iArr = new int[i2];
        BigInteger bigIntegerXor = bigIntegerAdd.xor(bigInteger);
        int i3 = iBitLength - 1;
        int i4 = 0;
        int i5 = 1;
        int i6 = 0;
        while (i5 < i3) {
            if (bigIntegerXor.testBit(i5)) {
                iArr[i4] = i6 | ((bigInteger.testBit(i5) ? -1 : 1) << 16);
                i5++;
                i6 = 1;
                i4++;
            } else {
                i6++;
            }
            i5++;
        }
        int i7 = i4 + 1;
        iArr[i4] = 65536 | i6;
        return i2 > i7 ? p(iArr, i7) : iArr;
    }

    public static int[] d(int i2, BigInteger bigInteger) {
        if (i2 == 2) {
            return c(bigInteger);
        }
        if (i2 < 2 || i2 > 16) {
            throw new IllegalArgumentException("'width' must be in the range [2, 16]");
        }
        if ((bigInteger.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        }
        if (bigInteger.signum() == 0) {
            return f5541c;
        }
        int iBitLength = (bigInteger.bitLength() / i2) + 1;
        int[] iArr = new int[iBitLength];
        int i3 = 1 << i2;
        int i4 = i3 - 1;
        int i5 = i3 >>> 1;
        int i6 = 0;
        int i7 = 0;
        boolean z = false;
        while (i6 <= bigInteger.bitLength()) {
            if (bigInteger.testBit(i6) == z) {
                i6++;
            } else {
                bigInteger = bigInteger.shiftRight(i6);
                int iIntValue = bigInteger.intValue() & i4;
                if (z) {
                    iIntValue++;
                }
                z = (iIntValue & i5) != 0;
                if (z) {
                    iIntValue -= i3;
                }
                if (i7 > 0) {
                    i6--;
                }
                iArr[i7] = i6 | (iIntValue << 16);
                i6 = i2;
                i7++;
            }
        }
        return iBitLength > i7 ? p(iArr, i7) : iArr;
    }

    public static byte[] e(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return f5540b;
        }
        BigInteger bigIntegerAdd = bigInteger.shiftLeft(1).add(bigInteger);
        int iBitLength = bigIntegerAdd.bitLength() - 1;
        byte[] bArr = new byte[iBitLength];
        BigInteger bigIntegerXor = bigIntegerAdd.xor(bigInteger);
        int i2 = 1;
        while (i2 < iBitLength) {
            if (bigIntegerXor.testBit(i2)) {
                bArr[i2 - 1] = (byte) (bigInteger.testBit(i2) ? -1 : 1);
                i2++;
            }
            i2++;
        }
        bArr[iBitLength - 1] = 1;
        return bArr;
    }

    public static byte[] f(int i2, BigInteger bigInteger) {
        if (i2 == 2) {
            return e(bigInteger);
        }
        if (i2 < 2 || i2 > 8) {
            throw new IllegalArgumentException("'width' must be in the range [2, 8]");
        }
        if (bigInteger.signum() == 0) {
            return f5540b;
        }
        int iBitLength = bigInteger.bitLength() + 1;
        byte[] bArr = new byte[iBitLength];
        int i3 = 1 << i2;
        int i4 = i3 - 1;
        int i5 = i3 >>> 1;
        int i6 = 0;
        int i7 = 0;
        boolean z = false;
        while (i6 <= bigInteger.bitLength()) {
            if (bigInteger.testBit(i6) == z) {
                i6++;
            } else {
                bigInteger = bigInteger.shiftRight(i6);
                int iIntValue = bigInteger.intValue() & i4;
                if (z) {
                    iIntValue++;
                }
                z = (iIntValue & i5) != 0;
                if (z) {
                    iIntValue -= i3;
                }
                if (i7 > 0) {
                    i6--;
                }
                int i8 = i7 + i6;
                bArr[i8] = (byte) iIntValue;
                i7 = i8 + 1;
                i6 = i2;
            }
        }
        return iBitLength > i7 ? o(bArr, i7) : bArr;
    }

    public static int g(BigInteger bigInteger) {
        if (bigInteger.signum() == 0) {
            return 0;
        }
        return bigInteger.shiftLeft(1).add(bigInteger).xor(bigInteger).bitCount();
    }

    public static v h(h hVar) {
        return i(hVar.i().y(hVar, "bc_wnaf"));
    }

    public static v i(p pVar) {
        if (pVar instanceof v) {
            return (v) pVar;
        }
        return null;
    }

    public static int j(int i2) {
        return k(i2, f5539a);
    }

    public static int k(int i2, int[] iArr) {
        int i3 = 0;
        while (i3 < iArr.length && i2 >= iArr[i3]) {
            i3++;
        }
        return i3 + 2;
    }

    public static h l(h hVar, int i2, boolean z, i iVar) {
        d dVarI = hVar.i();
        v vVarM = m(hVar, i2, z);
        h hVarA = iVar.a(hVar);
        dVarI.D(hVarA, "bc_wnaf", new a(vVarM, iVar, z));
        return hVarA;
    }

    public static v m(h hVar, int i2, boolean z) {
        d dVarI = hVar.i();
        return (v) dVarI.D(hVar, "bc_wnaf", new b(i2, z, hVar, dVarI));
    }

    public static h[] n(h[] hVarArr, int i2) {
        h[] hVarArr2 = new h[i2];
        System.arraycopy(hVarArr, 0, hVarArr2, 0, hVarArr.length);
        return hVarArr2;
    }

    public static byte[] o(byte[] bArr, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }

    public static int[] p(int[] iArr, int i2) {
        int[] iArr2 = new int[i2];
        System.arraycopy(iArr, 0, iArr2, 0, i2);
        return iArr2;
    }
}
