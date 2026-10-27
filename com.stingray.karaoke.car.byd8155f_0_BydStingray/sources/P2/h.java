package P2;

import B2.C0074n;
import B2.N;
import H7.H;
import d5.C1077F;
import d5.I;
import d5.d0;
import e2.K;
import e2.L;
import h2.AbstractC1343A;
import h2.t;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class h extends l9.d {
    public static final C0074n c = new C0074n(14);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0074n f5316b;

    public h(C0074n c0074n) {
        this.f5316b = c0074n;
    }

    public static Charset A0(int i7) {
        return i7 != 1 ? i7 != 2 ? i7 != 3 ? StandardCharsets.ISO_8859_1 : StandardCharsets.UTF_8 : StandardCharsets.UTF_16BE : StandardCharsets.UTF_16;
    }

    public static String B0(int i7, int i10, int i11, int i12, int i13) {
        return i7 == 2 ? String.format(Locale.US, "%c%c%c", Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12)) : String.format(Locale.US, "%c%c%c%c", Integer.valueOf(i10), Integer.valueOf(i11), Integer.valueOf(i12), Integer.valueOf(i13));
    }

    public static int C0(int i7, int i10, byte[] bArr) {
        int iD0 = D0(i7, bArr);
        if (i10 == 0 || i10 == 3) {
            return iD0;
        }
        while (iD0 < bArr.length - 1) {
            if ((iD0 - i7) % 2 == 0 && bArr[iD0 + 1] == 0) {
                return iD0;
            }
            iD0 = D0(iD0 + 1, bArr);
        }
        return bArr.length;
    }

    public static int D0(int i7, byte[] bArr) {
        while (i7 < bArr.length) {
            if (bArr[i7] == 0) {
                return i7;
            }
            i7++;
        }
        return bArr.length;
    }

    public static int E0(int i7, t tVar) {
        byte[] bArr = tVar.f15241a;
        int i10 = tVar.f15242b;
        int i11 = i10;
        while (true) {
            int i12 = i11 + 1;
            if (i12 >= i10 + i7) {
                return i7;
            }
            if ((bArr[i11] & 255) == 255 && bArr[i12] == 0) {
                System.arraycopy(bArr, i11 + 2, bArr, i12, (i7 - (i11 - i10)) - 2);
                i7--;
            }
            i11 = i12;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x007a A[PHI: r3
  0x007a: PHI (r3v16 int) = (r3v5 int), (r3v19 int) binds: [B:42:0x0087, B:33:0x0077] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean F0(h2.t r18, int r19, int r20, boolean r21) {
        /*
            r1 = r18
            r0 = r19
            int r2 = r1.f15242b
        L6:
            int r3 = r18.a()     // Catch: java.lang.Throwable -> L20
            r4 = 1
            r5 = r20
            if (r3 < r5) goto Lac
            r3 = 3
            r6 = 0
            if (r0 < r3) goto L23
            int r7 = r18.h()     // Catch: java.lang.Throwable -> L20
            long r8 = r18.x()     // Catch: java.lang.Throwable -> L20
            int r10 = r18.B()     // Catch: java.lang.Throwable -> L20
            goto L2d
        L20:
            r0 = move-exception
            goto Lb0
        L23:
            int r7 = r18.y()     // Catch: java.lang.Throwable -> L20
            int r8 = r18.y()     // Catch: java.lang.Throwable -> L20
            long r8 = (long) r8
            r10 = r6
        L2d:
            r11 = 0
            if (r7 != 0) goto L3b
            int r7 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r7 != 0) goto L3b
            if (r10 != 0) goto L3b
            r1.H(r2)
            return r4
        L3b:
            r7 = 4
            if (r0 != r7) goto L6c
            if (r21 != 0) goto L6c
            r13 = 8421504(0x808080, double:4.160776E-317)
            long r13 = r13 & r8
            int r11 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r11 == 0) goto L4c
            r1.H(r2)
            return r6
        L4c:
            r11 = 255(0xff, double:1.26E-321)
            long r13 = r8 & r11
            r15 = 8
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 7
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 16
            long r15 = r8 >> r15
            long r15 = r15 & r11
            r17 = 14
            long r15 = r15 << r17
            long r13 = r13 | r15
            r15 = 24
            long r8 = r8 >> r15
            long r8 = r8 & r11
            r11 = 21
            long r8 = r8 << r11
            long r8 = r8 | r13
        L6c:
            if (r0 != r7) goto L7c
            r3 = r10 & 64
            if (r3 == 0) goto L74
            r3 = r4
            goto L75
        L74:
            r3 = r6
        L75:
            r7 = r10 & 1
            if (r7 == 0) goto L7a
            goto L8c
        L7a:
            r4 = r6
            goto L8c
        L7c:
            if (r0 != r3) goto L8a
            r3 = r10 & 32
            if (r3 == 0) goto L84
            r3 = r4
            goto L85
        L84:
            r3 = r6
        L85:
            r7 = r10 & 128(0x80, float:1.8E-43)
            if (r7 == 0) goto L7a
            goto L8c
        L8a:
            r3 = r6
            r4 = r3
        L8c:
            if (r4 == 0) goto L90
            int r3 = r3 + 4
        L90:
            long r3 = (long) r3
            int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r3 >= 0) goto L99
            r1.H(r2)
            return r6
        L99:
            int r3 = r18.a()     // Catch: java.lang.Throwable -> L20
            long r3 = (long) r3
            int r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r3 >= 0) goto La6
            r1.H(r2)
            return r6
        La6:
            int r3 = (int) r8
            r1.I(r3)     // Catch: java.lang.Throwable -> L20
            goto L6
        Lac:
            r1.H(r2)
            return r4
        Lb0:
            r1.H(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: P2.h.F0(h2.t, int, int, boolean):boolean");
    }

    public static a l0(t tVar, int i7, int i10) {
        int iD0;
        String strConcat;
        int iV = tVar.v();
        Charset charsetA0 = A0(iV);
        int i11 = i7 - 1;
        byte[] bArr = new byte[i11];
        tVar.f(0, i11, bArr);
        if (i10 == 2) {
            strConcat = "image/" + H.R(new String(bArr, 0, 3, StandardCharsets.ISO_8859_1));
            if ("image/jpg".equals(strConcat)) {
                strConcat = "image/jpeg";
            }
            iD0 = 2;
        } else {
            iD0 = D0(0, bArr);
            String strR = H.R(new String(bArr, 0, iD0, StandardCharsets.ISO_8859_1));
            strConcat = strR.indexOf(47) == -1 ? "image/".concat(strR) : strR;
        }
        int i12 = bArr[iD0 + 1] & 255;
        int i13 = iD0 + 2;
        int iC0 = C0(i13, iV, bArr);
        String str = new String(bArr, i13, iC0 - i13, charsetA0);
        int iZ0 = z0(iV) + iC0;
        return new a(strConcat, str, i12, i11 <= iZ0 ? AbstractC1343A.f15198f : Arrays.copyOfRange(bArr, iZ0, i11));
    }

    public static c m0(t tVar, int i7, int i10, boolean z6, int i11, C0074n c0074n) {
        int i12 = tVar.f15242b;
        int iD0 = D0(i12, tVar.f15241a);
        String str = new String(tVar.f15241a, i12, iD0 - i12, StandardCharsets.ISO_8859_1);
        tVar.H(iD0 + 1);
        int iH = tVar.h();
        int iH2 = tVar.h();
        long jX = tVar.x();
        long j3 = jX == 4294967295L ? -1L : jX;
        long jX2 = tVar.x();
        long j10 = jX2 == 4294967295L ? -1L : jX2;
        ArrayList arrayList = new ArrayList();
        int i13 = i12 + i7;
        while (tVar.f15242b < i13) {
            i iVarP0 = p0(i10, tVar, z6, i11, c0074n);
            if (iVarP0 != null) {
                arrayList.add(iVarP0);
            }
        }
        return new c(str, iH, iH2, j3, j10, (i[]) arrayList.toArray(new i[0]));
    }

    public static d n0(t tVar, int i7, int i10, boolean z6, int i11, C0074n c0074n) {
        int i12 = tVar.f15242b;
        int iD0 = D0(i12, tVar.f15241a);
        String str = new String(tVar.f15241a, i12, iD0 - i12, StandardCharsets.ISO_8859_1);
        tVar.H(iD0 + 1);
        int iV = tVar.v();
        boolean z10 = (iV & 2) != 0;
        boolean z11 = (iV & 1) != 0;
        int iV2 = tVar.v();
        String[] strArr = new String[iV2];
        for (int i13 = 0; i13 < iV2; i13++) {
            int i14 = tVar.f15242b;
            int iD02 = D0(i14, tVar.f15241a);
            strArr[i13] = new String(tVar.f15241a, i14, iD02 - i14, StandardCharsets.ISO_8859_1);
            tVar.H(iD02 + 1);
        }
        ArrayList arrayList = new ArrayList();
        int i15 = i12 + i7;
        while (tVar.f15242b < i15) {
            i iVarP0 = p0(i10, tVar, z6, i11, c0074n);
            if (iVarP0 != null) {
                arrayList.add(iVarP0);
            }
        }
        return new d(str, z10, z11, strArr, (i[]) arrayList.toArray(new i[0]));
    }

    public static e o0(int i7, t tVar) {
        if (i7 < 4) {
            return null;
        }
        int iV = tVar.v();
        Charset charsetA0 = A0(iV);
        byte[] bArr = new byte[3];
        tVar.f(0, 3, bArr);
        String str = new String(bArr, 0, 3);
        int i10 = i7 - 4;
        byte[] bArr2 = new byte[i10];
        tVar.f(0, i10, bArr2);
        int iC0 = C0(0, iV, bArr2);
        String str2 = new String(bArr2, 0, iC0, charsetA0);
        int iZ0 = z0(iV) + iC0;
        return new e(str, str2, t0(bArr2, iZ0, C0(iZ0, iV, bArr2), charsetA0));
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x01fa A[Catch: all -> 0x012a, Exception | OutOfMemoryError -> 0x01cb, TRY_LEAVE, TryCatch #1 {Exception | OutOfMemoryError -> 0x01cb, blocks: (B:165:0x01c6, B:173:0x01d7, B:181:0x01f5, B:182:0x01fa), top: B:196:0x01b0 }] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x021a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static P2.i p0(int r18, h2.t r19, boolean r20, int r21, B2.C0074n r22) {
        /*
            Method dump skipped, instruction units count: 584
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P2.h.p0(int, h2.t, boolean, int, B2.n):P2.i");
    }

    public static f q0(int i7, t tVar) {
        int iV = tVar.v();
        Charset charsetA0 = A0(iV);
        int i10 = i7 - 1;
        byte[] bArr = new byte[i10];
        tVar.f(0, i10, bArr);
        int iD0 = D0(0, bArr);
        String strL = L.l(new String(bArr, 0, iD0, StandardCharsets.ISO_8859_1));
        int i11 = iD0 + 1;
        int iC0 = C0(i11, iV, bArr);
        String strT0 = t0(bArr, i11, iC0, charsetA0);
        int iZ0 = z0(iV) + iC0;
        int iC02 = C0(iZ0, iV, bArr);
        String strT02 = t0(bArr, iZ0, iC02, charsetA0);
        int iZ02 = z0(iV) + iC02;
        return new f(strL, strT0, strT02, i10 <= iZ02 ? AbstractC1343A.f15198f : Arrays.copyOfRange(bArr, iZ02, i10));
    }

    public static l r0(int i7, t tVar) {
        int iB = tVar.B();
        int iY = tVar.y();
        int iY2 = tVar.y();
        int iV = tVar.v();
        int iV2 = tVar.v();
        N n10 = new N();
        n10.q(tVar);
        int i10 = ((i7 - 10) * 8) / (iV + iV2);
        int[] iArr = new int[i10];
        int[] iArr2 = new int[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            int i12 = n10.i(iV);
            int i13 = n10.i(iV2);
            iArr[i11] = i12;
            iArr2[i11] = i13;
        }
        return new l(iB, iY, iY2, iArr, iArr2);
    }

    public static m s0(int i7, t tVar) {
        byte[] bArr = new byte[i7];
        tVar.f(0, i7, bArr);
        int iD0 = D0(0, bArr);
        String str = new String(bArr, 0, iD0, StandardCharsets.ISO_8859_1);
        int i10 = iD0 + 1;
        return new m(str, i7 <= i10 ? AbstractC1343A.f15198f : Arrays.copyOfRange(bArr, i10, i7));
    }

    public static String t0(byte[] bArr, int i7, int i10, Charset charset) {
        return (i10 <= i7 || i10 > bArr.length) ? "" : new String(bArr, i7, i10 - i7, charset);
    }

    public static n u0(int i7, t tVar, String str) {
        if (i7 < 1) {
            return null;
        }
        int iV = tVar.v();
        int i10 = i7 - 1;
        byte[] bArr = new byte[i10];
        tVar.f(0, i10, bArr);
        return new n(str, null, v0(iV, 0, bArr));
    }

    public static d0 v0(int i7, int i10, byte[] bArr) {
        if (i10 >= bArr.length) {
            return I.B("");
        }
        C1077F c1077fU = I.u();
        int iC0 = C0(i10, i7, bArr);
        while (i10 < iC0) {
            c1077fU.a(new String(bArr, i10, iC0 - i10, A0(i7)));
            i10 = z0(i7) + iC0;
            iC0 = C0(i10, i7, bArr);
        }
        d0 d0VarH = c1077fU.h();
        return d0VarH.isEmpty() ? I.B("") : d0VarH;
    }

    public static n w0(int i7, t tVar) {
        if (i7 < 1) {
            return null;
        }
        int iV = tVar.v();
        int i10 = i7 - 1;
        byte[] bArr = new byte[i10];
        tVar.f(0, i10, bArr);
        int iC0 = C0(0, iV, bArr);
        return new n("TXXX", new String(bArr, 0, iC0, A0(iV)), v0(iV, z0(iV) + iC0, bArr));
    }

    public static o x0(int i7, t tVar, String str) {
        byte[] bArr = new byte[i7];
        tVar.f(0, i7, bArr);
        return new o(str, null, new String(bArr, 0, D0(0, bArr), StandardCharsets.ISO_8859_1));
    }

    public static o y0(int i7, t tVar) {
        if (i7 < 1) {
            return null;
        }
        int iV = tVar.v();
        int i10 = i7 - 1;
        byte[] bArr = new byte[i10];
        tVar.f(0, i10, bArr);
        int iC0 = C0(0, iV, bArr);
        String str = new String(bArr, 0, iC0, A0(iV));
        int iZ0 = z0(iV) + iC0;
        return new o("WXXX", str, t0(bArr, iZ0, D0(iZ0, bArr), StandardCharsets.ISO_8859_1));
    }

    public static int z0(int i7) {
        return (i7 == 0 || i7 == 3) ? 1 : 2;
    }

    @Override // l9.d
    public final K O(K2.a aVar, ByteBuffer byteBuffer) {
        return k0(byteBuffer.limit(), byteBuffer.array());
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final e2.K k0(int r13, byte[] r14) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: P2.h.k0(int, byte[]):e2.K");
    }
}
