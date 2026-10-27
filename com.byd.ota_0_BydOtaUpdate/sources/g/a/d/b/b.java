package g.a.d.b;

import g.a.d.b.d;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public class b {
    public static h a(d dVar, h hVar) {
        if (dVar.m(hVar.i())) {
            return dVar.k(hVar.l(false));
        }
        throw new IllegalArgumentException("Point must be on the same curve");
    }

    public static h b(h hVar) {
        if (hVar.x()) {
            return hVar;
        }
        throw new IllegalStateException("Invalid result");
    }

    public static h c(h hVar, BigInteger bigInteger, h hVar2, BigInteger bigInteger2) {
        boolean z = bigInteger.signum() < 0;
        boolean z2 = bigInteger2.signum() < 0;
        BigInteger bigIntegerAbs = bigInteger.abs();
        BigInteger bigIntegerAbs2 = bigInteger2.abs();
        int iMax = Math.max(2, Math.min(16, w.j(bigIntegerAbs.bitLength())));
        int iMax2 = Math.max(2, Math.min(16, w.j(bigIntegerAbs2.bitLength())));
        v vVarM = w.m(hVar, iMax, true);
        v vVarM2 = w.m(hVar2, iMax2, true);
        return e(z ? vVarM.b() : vVarM.a(), z ? vVarM.a() : vVarM.b(), w.f(iMax, bigIntegerAbs), z2 ? vVarM2.b() : vVarM2.a(), z2 ? vVarM2.a() : vVarM2.b(), w.f(iMax2, bigIntegerAbs2));
    }

    public static h d(h hVar, BigInteger bigInteger, i iVar, BigInteger bigInteger2) {
        boolean z = bigInteger.signum() < 0;
        boolean z2 = bigInteger2.signum() < 0;
        BigInteger bigIntegerAbs = bigInteger.abs();
        BigInteger bigIntegerAbs2 = bigInteger2.abs();
        int iMax = Math.max(2, Math.min(16, w.j(Math.max(bigIntegerAbs.bitLength(), bigIntegerAbs2.bitLength()))));
        h hVarL = w.l(hVar, iMax, true, iVar);
        v vVarH = w.h(hVar);
        v vVarH2 = w.h(hVarL);
        return e(z ? vVarH.b() : vVarH.a(), z ? vVarH.a() : vVarH.b(), w.f(iMax, bigIntegerAbs), z2 ? vVarH2.b() : vVarH2.a(), z2 ? vVarH2.a() : vVarH2.b(), w.f(iMax, bigIntegerAbs2));
    }

    public static h e(h[] hVarArr, h[] hVarArr2, byte[] bArr, h[] hVarArr3, h[] hVarArr4, byte[] bArr2) {
        h hVarA;
        int iMax = Math.max(bArr.length, bArr2.length);
        h hVarV = hVarArr[0].i().v();
        int i2 = iMax - 1;
        int i3 = 0;
        h hVarK = hVarV;
        while (i2 >= 0) {
            byte b2 = i2 < bArr.length ? bArr[i2] : (byte) 0;
            byte b3 = i2 < bArr2.length ? bArr2[i2] : (byte) 0;
            if ((b2 | b3) == 0) {
                i3++;
            } else {
                if (b2 != 0) {
                    hVarA = hVarV.a((b2 < 0 ? hVarArr2 : hVarArr)[Math.abs((int) b2) >>> 1]);
                } else {
                    hVarA = hVarV;
                }
                if (b3 != 0) {
                    hVarA = hVarA.a((b3 < 0 ? hVarArr4 : hVarArr3)[Math.abs((int) b3) >>> 1]);
                }
                if (i3 > 0) {
                    hVarK = hVarK.I(i3);
                    i3 = 0;
                }
                hVarK = hVarK.K(hVarA);
            }
            i2--;
        }
        return i3 > 0 ? hVarK.I(i3) : hVarK;
    }

    public static h f(h[] hVarArr, i iVar, BigInteger[] bigIntegerArr) {
        int length = hVarArr.length;
        int i2 = length << 1;
        boolean[] zArr = new boolean[i2];
        v[] vVarArr = new v[i2];
        byte[][] bArr = new byte[i2][];
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = i3 << 1;
            int i5 = i4 + 1;
            BigInteger bigInteger = bigIntegerArr[i4];
            zArr[i4] = bigInteger.signum() < 0;
            BigInteger bigIntegerAbs = bigInteger.abs();
            BigInteger bigInteger2 = bigIntegerArr[i5];
            zArr[i5] = bigInteger2.signum() < 0;
            BigInteger bigIntegerAbs2 = bigInteger2.abs();
            int iMax = Math.max(2, Math.min(16, w.j(Math.max(bigIntegerAbs.bitLength(), bigIntegerAbs2.bitLength()))));
            h hVar = hVarArr[i3];
            h hVarL = w.l(hVar, iMax, true, iVar);
            vVarArr[i4] = w.h(hVar);
            vVarArr[i5] = w.h(hVarL);
            bArr[i4] = w.f(iMax, bigIntegerAbs);
            bArr[i5] = w.f(iMax, bigIntegerAbs2);
        }
        return h(zArr, vVarArr, bArr);
    }

    public static h g(h[] hVarArr, BigInteger[] bigIntegerArr) {
        int length = hVarArr.length;
        boolean[] zArr = new boolean[length];
        v[] vVarArr = new v[length];
        byte[][] bArr = new byte[length][];
        for (int i2 = 0; i2 < length; i2++) {
            BigInteger bigInteger = bigIntegerArr[i2];
            zArr[i2] = bigInteger.signum() < 0;
            BigInteger bigIntegerAbs = bigInteger.abs();
            int iMax = Math.max(2, Math.min(16, w.j(bigIntegerAbs.bitLength())));
            vVarArr[i2] = w.m(hVarArr[i2], iMax, true);
            bArr[i2] = w.f(iMax, bigIntegerAbs);
        }
        return h(zArr, vVarArr, bArr);
    }

    public static h h(boolean[] zArr, v[] vVarArr, byte[][] bArr) {
        int length = bArr.length;
        int iMax = 0;
        for (byte[] bArr2 : bArr) {
            iMax = Math.max(iMax, bArr2.length);
        }
        h hVarV = vVarArr[0].a()[0].i().v();
        int i2 = iMax - 1;
        int i3 = 0;
        h hVarK = hVarV;
        while (i2 >= 0) {
            h hVarA = hVarV;
            for (int i4 = 0; i4 < length; i4++) {
                byte[] bArr3 = bArr[i4];
                byte b2 = i2 < bArr3.length ? bArr3[i2] : (byte) 0;
                if (b2 != 0) {
                    int iAbs = Math.abs((int) b2);
                    v vVar = vVarArr[i4];
                    hVarA = hVarA.a(((b2 < 0) == zArr[i4] ? vVar.a() : vVar.b())[iAbs >>> 1]);
                }
            }
            if (hVarA == hVarV) {
                i3++;
            } else {
                if (i3 > 0) {
                    hVarK = hVarK.I(i3);
                    i3 = 0;
                }
                hVarK = hVarK.K(hVarA);
            }
            i2--;
        }
        return i3 > 0 ? hVarK.I(i3) : hVarK;
    }

    public static h i(h[] hVarArr, BigInteger[] bigIntegerArr, g.a.d.b.b0.b bVar) {
        BigInteger bigIntegerX = hVarArr[0].i().x();
        int length = hVarArr.length;
        int i2 = length << 1;
        BigInteger[] bigIntegerArr2 = new BigInteger[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            BigInteger[] bigIntegerArrB = bVar.b(bigIntegerArr[i4].mod(bigIntegerX));
            int i5 = i3 + 1;
            bigIntegerArr2[i3] = bigIntegerArrB[0];
            i3 = i5 + 1;
            bigIntegerArr2[i5] = bigIntegerArrB[1];
        }
        i iVarC = bVar.c();
        if (bVar.a()) {
            return f(hVarArr, iVarC, bigIntegerArr2);
        }
        h[] hVarArr2 = new h[i2];
        int i6 = 0;
        for (h hVar : hVarArr) {
            h hVarA = iVarC.a(hVar);
            int i7 = i6 + 1;
            hVarArr2[i6] = hVar;
            i6 = i7 + 1;
            hVarArr2[i7] = hVarA;
        }
        return g(hVarArr2, bigIntegerArr2);
    }

    public static h j(d dVar, h hVar) {
        if (dVar.m(hVar.i())) {
            return dVar.z(hVar);
        }
        throw new IllegalArgumentException("Point must be on the same curve");
    }

    public static boolean k(d dVar) {
        return l(dVar.t());
    }

    public static boolean l(g.a.d.c.a aVar) {
        return aVar.b() > 1 && aVar.c().equals(c.f5472c) && (aVar instanceof g.a.d.c.f);
    }

    public static boolean m(d dVar) {
        return n(dVar.t());
    }

    public static boolean n(g.a.d.c.a aVar) {
        return aVar.b() == 1;
    }

    public static void o(e[] eVarArr, int i2, int i3, e eVar) {
        e[] eVarArr2 = new e[i3];
        int i4 = 0;
        eVarArr2[0] = eVarArr[i2];
        while (true) {
            i4++;
            if (i4 >= i3) {
                break;
            } else {
                eVarArr2[i4] = eVarArr2[i4 - 1].j(eVarArr[i2 + i4]);
            }
        }
        int i5 = i4 - 1;
        if (eVar != null) {
            eVarArr2[i5] = eVarArr2[i5].j(eVar);
        }
        e eVarG = eVarArr2[i5].g();
        while (i5 > 0) {
            int i6 = i5 - 1;
            int i7 = i5 + i2;
            e eVar2 = eVarArr[i7];
            eVarArr[i7] = eVarArr2[i6].j(eVarG);
            eVarG = eVarG.j(eVar2);
            i5 = i6;
        }
        eVarArr[i2] = eVarG;
    }

    public static h p(h hVar, BigInteger bigInteger) {
        BigInteger bigIntegerAbs = bigInteger.abs();
        h hVarV = hVar.i().v();
        int iBitLength = bigIntegerAbs.bitLength();
        if (iBitLength > 0) {
            if (bigIntegerAbs.testBit(0)) {
                hVarV = hVar;
            }
            for (int i2 = 1; i2 < iBitLength; i2++) {
                hVar = hVar.J();
                if (bigIntegerAbs.testBit(i2)) {
                    hVarV = hVarV.a(hVar);
                }
            }
        }
        return bigInteger.signum() < 0 ? hVarV.z() : hVarV;
    }

    public static h q(h hVar, BigInteger bigInteger, h hVar2, BigInteger bigInteger2) {
        h hVarI;
        d dVarI = hVar.i();
        h hVarJ = j(dVarI, hVar2);
        if ((dVarI instanceof d.b) && ((d.b) dVarI).I()) {
            hVarI = hVar.y(bigInteger).a(hVarJ.y(bigInteger2));
        } else {
            g.a.d.b.b0.a aVarS = dVarI.s();
            hVarI = aVarS instanceof g.a.d.b.b0.b ? i(new h[]{hVar, hVarJ}, new BigInteger[]{bigInteger, bigInteger2}, (g.a.d.b.b0.b) aVarS) : c(hVar, bigInteger, hVarJ, bigInteger2);
        }
        return b(hVarI);
    }
}
