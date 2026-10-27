package com.google.protobuf;

import com.google.protobuf.c0;
import com.google.protobuf.s;
import com.google.protobuf.z;
import defpackage.lk;
import defpackage.sk3;

/* JADX INFO: compiled from: ArrayDecoders.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: compiled from: ArrayDecoders.java */
    public static final class a {
        public int a;
        public long b;
        public Object c;
        public final s d;

        public a(s sVar) {
            sVar.getClass();
            this.d = sVar;
        }
    }

    public static int A(int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) {
        i0 i0Var = (i0) eVar;
        int iJ = J(bArr, i2, aVar);
        i0Var.b(g.d(aVar.b));
        while (iJ < i3) {
            int iH = H(bArr, iJ, aVar);
            if (i != aVar.a) {
                break;
            }
            iJ = J(bArr, iH, aVar);
            i0Var.b(g.d(aVar.b));
        }
        return iJ;
    }

    public static int B(byte[] bArr, int i, a aVar) throws d0 {
        int iH = H(bArr, i, aVar);
        int i2 = aVar.a;
        if (i2 < 0) {
            throw d0.f();
        }
        if (i2 == 0) {
            aVar.c = "";
            return iH;
        }
        aVar.c = new String(bArr, iH, i2, c0.a);
        return iH + i2;
    }

    public static int C(int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) throws d0 {
        int iH = H(bArr, i2, aVar);
        int i4 = aVar.a;
        if (i4 < 0) {
            throw d0.f();
        }
        if (i4 == 0) {
            eVar.add("");
        } else {
            eVar.add(new String(bArr, iH, i4, c0.a));
            iH += i4;
        }
        while (iH < i3) {
            int iH2 = H(bArr, iH, aVar);
            if (i != aVar.a) {
                break;
            }
            iH = H(bArr, iH2, aVar);
            int i5 = aVar.a;
            if (i5 < 0) {
                throw d0.f();
            }
            if (i5 == 0) {
                eVar.add("");
            } else {
                eVar.add(new String(bArr, iH, i5, c0.a));
                iH += i5;
            }
        }
        return iH;
    }

    public static int D(int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) throws d0 {
        int iH = H(bArr, i2, aVar);
        int i4 = aVar.a;
        if (i4 < 0) {
            throw d0.f();
        }
        if (i4 == 0) {
            eVar.add("");
        } else {
            int i5 = iH + i4;
            if (!sk3.g(bArr, iH, i5)) {
                throw d0.c();
            }
            eVar.add(new String(bArr, iH, i4, c0.a));
            iH = i5;
        }
        while (iH < i3) {
            int iH2 = H(bArr, iH, aVar);
            if (i != aVar.a) {
                break;
            }
            iH = H(bArr, iH2, aVar);
            int i6 = aVar.a;
            if (i6 < 0) {
                throw d0.f();
            }
            if (i6 == 0) {
                eVar.add("");
            } else {
                int i7 = iH + i6;
                if (!sk3.g(bArr, iH, i7)) {
                    throw d0.c();
                }
                eVar.add(new String(bArr, iH, i6, c0.a));
                iH = i7;
            }
        }
        return iH;
    }

    public static int E(byte[] bArr, int i, a aVar) throws d0 {
        int iH = H(bArr, i, aVar);
        int i2 = aVar.a;
        if (i2 < 0) {
            throw d0.f();
        }
        if (i2 == 0) {
            aVar.c = "";
            return iH;
        }
        aVar.c = sk3.a.b(bArr, iH, i2);
        return iH + i2;
    }

    public static int F(int i, byte[] bArr, int i2, int i3, k1 k1Var, a aVar) throws d0 {
        if ((i >>> 3) == 0) {
            throw d0.b();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int iJ = J(bArr, i2, aVar);
            k1Var.b(i, Long.valueOf(aVar.b));
            return iJ;
        }
        if (i4 == 1) {
            k1Var.b(i, Long.valueOf(i(i2, bArr)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int iH = H(bArr, i2, aVar);
            int i5 = aVar.a;
            if (i5 < 0) {
                throw d0.f();
            }
            if (i5 > bArr.length - iH) {
                throw d0.k();
            }
            if (i5 == 0) {
                k1Var.b(i, lk.f);
            } else {
                k1Var.b(i, lk.d(bArr, iH, i5));
            }
            return iH + i5;
        }
        if (i4 != 3) {
            if (i4 != 5) {
                throw d0.b();
            }
            k1Var.b(i, Integer.valueOf(g(i2, bArr)));
            return i2 + 4;
        }
        k1 k1Var2 = new k1();
        int i6 = (i & (-8)) | 4;
        int i7 = 0;
        while (true) {
            if (i2 >= i3) {
                break;
            }
            int iH2 = H(bArr, i2, aVar);
            int i8 = aVar.a;
            if (i8 == i6) {
                i7 = i8;
                i2 = iH2;
                break;
            }
            i7 = i8;
            i2 = F(i8, bArr, iH2, i3, k1Var2, aVar);
        }
        if (i2 > i3 || i7 != i6) {
            throw d0.h();
        }
        k1Var.b(i, k1Var2);
        return i2;
    }

    public static int G(int i, byte[] bArr, int i2, a aVar) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            aVar.a = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & 127) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            aVar.a = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & 127) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            aVar.a = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & 127) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            aVar.a = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & 127) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                aVar.a = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    public static int H(byte[] bArr, int i, a aVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return G(b, bArr, i2, aVar);
        }
        aVar.a = b;
        return i2;
    }

    public static int I(int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) {
        b0 b0Var = (b0) eVar;
        int iH = H(bArr, i2, aVar);
        b0Var.b(aVar.a);
        while (iH < i3) {
            int iH2 = H(bArr, iH, aVar);
            if (i != aVar.a) {
                break;
            }
            iH = H(bArr, iH2, aVar);
            b0Var.b(aVar.a);
        }
        return iH;
    }

    public static int J(byte[] bArr, int i, a aVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            aVar.b = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & 127)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & 127)) << i4;
            b = b2;
            i3 = i5;
        }
        aVar.b = j2;
        return i3;
    }

    public static int K(int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) {
        i0 i0Var = (i0) eVar;
        int iJ = J(bArr, i2, aVar);
        i0Var.b(aVar.b);
        while (iJ < i3) {
            int iH = H(bArr, iJ, aVar);
            if (i != aVar.a) {
                break;
            }
            iJ = J(bArr, iH, aVar);
            i0Var.b(aVar.b);
        }
        return iJ;
    }

    public static int L(int i, byte[] bArr, int i2, int i3, a aVar) throws d0 {
        if ((i >>> 3) == 0) {
            throw d0.b();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            return J(bArr, i2, aVar);
        }
        if (i4 == 1) {
            return i2 + 8;
        }
        if (i4 == 2) {
            return H(bArr, i2, aVar) + aVar.a;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                return i2 + 4;
            }
            throw d0.b();
        }
        int i5 = (i & (-8)) | 4;
        int i6 = 0;
        while (i2 < i3) {
            i2 = H(bArr, i2, aVar);
            i6 = aVar.a;
            if (i6 == i5) {
                break;
            }
            i2 = L(i6, bArr, i2, i3, aVar);
        }
        if (i2 > i3 || i6 != i5) {
            throw d0.h();
        }
        return i2;
    }

    public static int a(int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) {
        f fVar = (f) eVar;
        int iJ = J(bArr, i2, aVar);
        fVar.b(aVar.b != 0);
        while (iJ < i3) {
            int iH = H(bArr, iJ, aVar);
            if (i != aVar.a) {
                break;
            }
            iJ = J(bArr, iH, aVar);
            fVar.b(aVar.b != 0);
        }
        return iJ;
    }

    public static int b(byte[] bArr, int i, a aVar) throws d0 {
        int iH = H(bArr, i, aVar);
        int i2 = aVar.a;
        if (i2 < 0) {
            throw d0.f();
        }
        if (i2 > bArr.length - iH) {
            throw d0.k();
        }
        if (i2 == 0) {
            aVar.c = lk.f;
            return iH;
        }
        aVar.c = lk.d(bArr, iH, i2);
        return iH + i2;
    }

    public static int c(int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) throws d0 {
        int iH = H(bArr, i2, aVar);
        int i4 = aVar.a;
        if (i4 < 0) {
            throw d0.f();
        }
        if (i4 > bArr.length - iH) {
            throw d0.k();
        }
        if (i4 == 0) {
            eVar.add(lk.f);
        } else {
            eVar.add(lk.d(bArr, iH, i4));
            iH += i4;
        }
        while (iH < i3) {
            int iH2 = H(bArr, iH, aVar);
            if (i != aVar.a) {
                break;
            }
            iH = H(bArr, iH2, aVar);
            int i5 = aVar.a;
            if (i5 < 0) {
                throw d0.f();
            }
            if (i5 > bArr.length - iH) {
                throw d0.k();
            }
            if (i5 == 0) {
                eVar.add(lk.f);
            } else {
                eVar.add(lk.d(bArr, iH, i5));
                iH += i5;
            }
        }
        return iH;
    }

    public static double d(int i, byte[] bArr) {
        return Double.longBitsToDouble(i(i, bArr));
    }

    public static int e(int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) {
        o oVar = (o) eVar;
        oVar.b(Double.longBitsToDouble(i(i2, bArr)));
        int i4 = i2 + 8;
        while (i4 < i3) {
            int iH = H(bArr, i4, aVar);
            if (i != aVar.a) {
                break;
            }
            oVar.b(Double.longBitsToDouble(i(iH, bArr)));
            i4 = iH + 8;
        }
        return i4;
    }

    public static int f(int i, byte[] bArr, int i2, int i3, Object obj, r0 r0Var, a aVar) {
        s sVar = aVar.d;
        sVar.getClass();
        if (sVar.a.get(new s.a(i >>> 3, r0Var)) == null) {
            return F(i, bArr, i2, i3, v0.q(obj), aVar);
        }
        z.c cVar = (z.c) obj;
        x<z.d> xVar = cVar.extensions;
        if (xVar.b) {
            cVar.extensions = xVar.clone();
        }
        throw null;
    }

    public static int g(int i, byte[] bArr) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static int h(int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) {
        b0 b0Var = (b0) eVar;
        b0Var.b(g(i2, bArr));
        int i4 = i2 + 4;
        while (i4 < i3) {
            int iH = H(bArr, i4, aVar);
            if (i != aVar.a) {
                break;
            }
            b0Var.b(g(iH, bArr));
            i4 = iH + 4;
        }
        return i4;
    }

    public static long i(int i, byte[] bArr) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }

    public static int j(int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) {
        i0 i0Var = (i0) eVar;
        i0Var.b(i(i2, bArr));
        int i4 = i2 + 8;
        while (i4 < i3) {
            int iH = H(bArr, i4, aVar);
            if (i != aVar.a) {
                break;
            }
            i0Var.b(i(iH, bArr));
            i4 = iH + 8;
        }
        return i4;
    }

    public static float k(int i, byte[] bArr) {
        return Float.intBitsToFloat(g(i, bArr));
    }

    public static int l(int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) {
        y yVar = (y) eVar;
        yVar.b(Float.intBitsToFloat(g(i2, bArr)));
        int i4 = i2 + 4;
        while (i4 < i3) {
            int iH = H(bArr, i4, aVar);
            if (i != aVar.a) {
                break;
            }
            yVar.b(Float.intBitsToFloat(g(iH, bArr)));
            i4 = iH + 4;
        }
        return i4;
    }

    public static int m(b1 b1Var, byte[] bArr, int i, int i2, int i3, a aVar) {
        v0 v0Var = (v0) b1Var;
        Object objI = v0Var.i();
        int iG = v0Var.G(objI, bArr, i, i2, i3, aVar);
        v0Var.c(objI);
        aVar.c = objI;
        return iG;
    }

    public static int n(b1 b1Var, int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) {
        int i4 = (i & (-8)) | 4;
        int iM = m(b1Var, bArr, i2, i3, i4, aVar);
        eVar.add(aVar.c);
        while (iM < i3) {
            int iH = H(bArr, iM, aVar);
            if (i != aVar.a) {
                break;
            }
            iM = m(b1Var, bArr, iH, i3, i4, aVar);
            eVar.add(aVar.c);
        }
        return iM;
    }

    public static int o(b1 b1Var, byte[] bArr, int i, int i2, a aVar) throws d0 {
        int iG = i + 1;
        int i3 = bArr[i];
        if (i3 < 0) {
            iG = G(i3, bArr, iG, aVar);
            i3 = aVar.a;
        }
        int i4 = iG;
        if (i3 < 0 || i3 > i2 - i4) {
            throw d0.k();
        }
        Object objI = b1Var.i();
        int i5 = i3 + i4;
        b1Var.f(objI, bArr, i4, i5, aVar);
        b1Var.c(objI);
        aVar.c = objI;
        return i5;
    }

    public static int p(b1<?> b1Var, int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) throws d0 {
        int iO = o(b1Var, bArr, i2, i3, aVar);
        eVar.add(aVar.c);
        while (iO < i3) {
            int iH = H(bArr, iO, aVar);
            if (i != aVar.a) {
                break;
            }
            iO = o(b1Var, bArr, iH, i3, aVar);
            eVar.add(aVar.c);
        }
        return iO;
    }

    public static int q(byte[] bArr, int i, c0.e<?> eVar, a aVar) throws d0 {
        f fVar = (f) eVar;
        int iH = H(bArr, i, aVar);
        int i2 = aVar.a + iH;
        while (iH < i2) {
            iH = J(bArr, iH, aVar);
            fVar.b(aVar.b != 0);
        }
        if (iH == i2) {
            return iH;
        }
        throw d0.k();
    }

    public static int r(byte[] bArr, int i, c0.e<?> eVar, a aVar) throws d0 {
        o oVar = (o) eVar;
        int iH = H(bArr, i, aVar);
        int i2 = aVar.a + iH;
        while (iH < i2) {
            oVar.b(Double.longBitsToDouble(i(iH, bArr)));
            iH += 8;
        }
        if (iH == i2) {
            return iH;
        }
        throw d0.k();
    }

    public static int s(byte[] bArr, int i, c0.e<?> eVar, a aVar) throws d0 {
        b0 b0Var = (b0) eVar;
        int iH = H(bArr, i, aVar);
        int i2 = aVar.a + iH;
        while (iH < i2) {
            b0Var.b(g(iH, bArr));
            iH += 4;
        }
        if (iH == i2) {
            return iH;
        }
        throw d0.k();
    }

    public static int t(byte[] bArr, int i, c0.e<?> eVar, a aVar) throws d0 {
        i0 i0Var = (i0) eVar;
        int iH = H(bArr, i, aVar);
        int i2 = aVar.a + iH;
        while (iH < i2) {
            i0Var.b(i(iH, bArr));
            iH += 8;
        }
        if (iH == i2) {
            return iH;
        }
        throw d0.k();
    }

    public static int u(byte[] bArr, int i, c0.e<?> eVar, a aVar) throws d0 {
        y yVar = (y) eVar;
        int iH = H(bArr, i, aVar);
        int i2 = aVar.a + iH;
        while (iH < i2) {
            yVar.b(Float.intBitsToFloat(g(iH, bArr)));
            iH += 4;
        }
        if (iH == i2) {
            return iH;
        }
        throw d0.k();
    }

    public static int v(byte[] bArr, int i, c0.e<?> eVar, a aVar) throws d0 {
        b0 b0Var = (b0) eVar;
        int iH = H(bArr, i, aVar);
        int i2 = aVar.a + iH;
        while (iH < i2) {
            iH = H(bArr, iH, aVar);
            b0Var.b(g.c(aVar.a));
        }
        if (iH == i2) {
            return iH;
        }
        throw d0.k();
    }

    public static int w(byte[] bArr, int i, c0.e<?> eVar, a aVar) throws d0 {
        i0 i0Var = (i0) eVar;
        int iH = H(bArr, i, aVar);
        int i2 = aVar.a + iH;
        while (iH < i2) {
            iH = J(bArr, iH, aVar);
            i0Var.b(g.d(aVar.b));
        }
        if (iH == i2) {
            return iH;
        }
        throw d0.k();
    }

    public static int x(byte[] bArr, int i, c0.e<?> eVar, a aVar) throws d0 {
        b0 b0Var = (b0) eVar;
        int iH = H(bArr, i, aVar);
        int i2 = aVar.a + iH;
        while (iH < i2) {
            iH = H(bArr, iH, aVar);
            b0Var.b(aVar.a);
        }
        if (iH == i2) {
            return iH;
        }
        throw d0.k();
    }

    public static int y(byte[] bArr, int i, c0.e<?> eVar, a aVar) throws d0 {
        i0 i0Var = (i0) eVar;
        int iH = H(bArr, i, aVar);
        int i2 = aVar.a + iH;
        while (iH < i2) {
            iH = J(bArr, iH, aVar);
            i0Var.b(aVar.b);
        }
        if (iH == i2) {
            return iH;
        }
        throw d0.k();
    }

    public static int z(int i, byte[] bArr, int i2, int i3, c0.e<?> eVar, a aVar) {
        b0 b0Var = (b0) eVar;
        int iH = H(bArr, i2, aVar);
        b0Var.b(g.c(aVar.a));
        while (iH < i3) {
            int iH2 = H(bArr, iH, aVar);
            if (i != aVar.a) {
                break;
            }
            iH = H(bArr, iH2, aVar);
            b0Var.b(g.c(aVar.a));
        }
        return iH;
    }
}
