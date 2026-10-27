package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/* JADX INFO: compiled from: PDF417ScanningDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final com.google.zxing.pdf417.decoder.j.a f4472a = new com.google.zxing.pdf417.decoder.j.a();

    private static c a(g gVar) throws NotFoundException {
        int[] iArrJ;
        if (gVar == null || (iArrJ = gVar.j()) == null) {
            return null;
        }
        int iP = p(iArrJ);
        int i = 0;
        int i2 = 0;
        for (int i3 : iArrJ) {
            i2 += iP - i3;
            if (i3 > 0) {
                break;
            }
        }
        d[] dVarArrD = gVar.d();
        for (int i4 = 0; i2 > 0 && dVarArrD[i4] == null; i4++) {
            i2--;
        }
        for (int length = iArrJ.length - 1; length >= 0; length--) {
            i += iP - iArrJ[length];
            if (iArrJ[length] > 0) {
                break;
            }
        }
        for (int length2 = dVarArrD.length - 1; i > 0 && dVarArrD[length2] == null; length2--) {
            i--;
        }
        return gVar.a().a(i2, i, gVar.k());
    }

    private static void b(e eVar, b[][] bVarArr) throws NotFoundException {
        b bVar = bVarArr[0][1];
        int[] iArrA = bVar.a();
        int iJ = (eVar.j() * eVar.l()) - r(eVar.k());
        if (iArrA.length != 0) {
            if (iArrA[0] != iJ) {
                bVar.b(iJ);
            }
        } else {
            if (iJ <= 0 || iJ > 928) {
                throw NotFoundException.getNotFoundInstance();
            }
            bVar.b(iJ);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0022, code lost:
    
        r0 = -r0;
        r8 = !r8;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0022, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0022, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0022, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int c(com.google.zxing.common.b r5, int r6, int r7, boolean r8, int r9, int r10) {
        /*
            if (r8 == 0) goto L4
            r0 = -1
            goto L5
        L4:
            r0 = 1
        L5:
            r1 = 0
            r2 = r9
        L7:
            r3 = 2
            if (r1 >= r3) goto L28
        La:
            if (r8 == 0) goto Lf
            if (r2 < r6) goto L22
            goto L11
        Lf:
            if (r2 >= r7) goto L22
        L11:
            boolean r4 = r5.d(r2, r10)
            if (r8 != r4) goto L22
            int r4 = r9 - r2
            int r4 = java.lang.Math.abs(r4)
            if (r4 <= r3) goto L20
            return r9
        L20:
            int r2 = r2 + r0
            goto La
        L22:
            int r0 = -r0
            r8 = r8 ^ 1
            int r1 = r1 + 1
            goto L7
        L28:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.i.c(com.google.zxing.common.b, int, int, boolean, int, int):int");
    }

    private static boolean d(int i, int i2, int i3) {
        return i2 + (-2) <= i && i <= i3 + 2;
    }

    private static int e(int[] iArr, int[] iArr2, int i) throws ChecksumException {
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            return f4472a.a(iArr, i, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static b[][] f(e eVar) {
        int iC;
        b[][] bVarArr = (b[][]) Array.newInstance((Class<?>) b.class, eVar.l(), eVar.j() + 2);
        for (int i = 0; i < bVarArr.length; i++) {
            for (int i2 = 0; i2 < bVarArr[i].length; i2++) {
                bVarArr[i][i2] = new b();
            }
        }
        int i3 = 0;
        for (f fVar : eVar.o()) {
            if (fVar != null) {
                for (d dVar : fVar.d()) {
                    if (dVar != null && (iC = dVar.c()) >= 0 && iC < bVarArr.length) {
                        bVarArr[iC][i3].b(dVar.e());
                    }
                }
            }
            i3++;
        }
        return bVarArr;
    }

    private static com.google.zxing.common.d g(e eVar) throws NotFoundException, ChecksumException, FormatException {
        b[][] bVarArrF = f(eVar);
        b(eVar, bVarArrF);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[eVar.l() * eVar.j()];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < eVar.l(); i++) {
            int i2 = 0;
            while (i2 < eVar.j()) {
                int i3 = i2 + 1;
                int[] iArrA = bVarArrF[i][i3].a();
                int iJ = (eVar.j() * i) + i2;
                if (iArrA.length == 0) {
                    arrayList.add(Integer.valueOf(iJ));
                } else if (iArrA.length == 1) {
                    iArr[iJ] = iArrA[0];
                } else {
                    arrayList3.add(Integer.valueOf(iJ));
                    arrayList2.add(iArrA);
                }
                i2 = i3;
            }
        }
        int size = arrayList2.size();
        int[][] iArr2 = new int[size][];
        for (int i4 = 0; i4 < size; i4++) {
            iArr2[i4] = (int[]) arrayList2.get(i4);
        }
        return h(eVar.k(), iArr, com.google.zxing.o.a.b(arrayList), com.google.zxing.o.a.b(arrayList3), iArr2);
    }

    private static com.google.zxing.common.d h(int i, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) throws ChecksumException, FormatException {
        int length = iArr3.length;
        int[] iArr5 = new int[length];
        int i2 = 100;
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                throw ChecksumException.getChecksumInstance();
            }
            for (int i4 = 0; i4 < length; i4++) {
                iArr[iArr3[i4]] = iArr4[i4][iArr5[i4]];
            }
            try {
                return j(iArr, i, iArr2);
            } catch (ChecksumException unused) {
                if (length == 0) {
                    throw ChecksumException.getChecksumInstance();
                }
                int i5 = 0;
                while (true) {
                    if (i5 >= length) {
                        break;
                    }
                    if (iArr5[i5] < iArr4[i5].length - 1) {
                        iArr5[i5] = iArr5[i5] + 1;
                        break;
                    }
                    iArr5[i5] = 0;
                    if (i5 == length - 1) {
                        throw ChecksumException.getChecksumInstance();
                    }
                    i5++;
                }
                i2 = i3;
            }
        }
    }

    public static com.google.zxing.common.d i(com.google.zxing.common.b bVar, com.google.zxing.i iVar, com.google.zxing.i iVar2, com.google.zxing.i iVar3, com.google.zxing.i iVar4, int i, int i2) throws NotFoundException, ChecksumException, FormatException {
        int i3;
        int i4;
        int i5;
        int i6;
        g gVarS = null;
        g gVarS2 = null;
        e eVarV = null;
        c cVar = new c(bVar, iVar, iVar2, iVar3, iVar4);
        for (int i7 = 0; i7 < 2; i7++) {
            if (iVar != null) {
                gVarS = s(bVar, cVar, iVar, true, i, i2);
            }
            if (iVar3 != null) {
                gVarS2 = s(bVar, cVar, iVar3, false, i, i2);
            }
            eVarV = v(gVarS, gVarS2);
            if (eVarV == null) {
                throw NotFoundException.getNotFoundInstance();
            }
            if (i7 != 0 || eVarV.m() == null || (eVarV.m().g() >= cVar.g() && eVarV.m().e() <= cVar.e())) {
                eVarV.p(cVar);
                break;
            }
            cVar = eVarV.m();
        }
        int iJ = eVarV.j() + 1;
        eVarV.q(0, gVarS);
        eVarV.q(iJ, gVarS2);
        boolean z = gVarS != null;
        int iMin = i;
        int iMax = i2;
        for (int i8 = 1; i8 <= iJ; i8++) {
            int i9 = z ? i8 : iJ - i8;
            if (eVarV.n(i9) == null) {
                f gVar = (i9 == 0 || i9 == iJ) ? new g(cVar, i9 == 0) : new f(cVar);
                eVarV.q(i9, gVar);
                int i10 = -1;
                int iG = cVar.g();
                int i11 = -1;
                while (iG <= cVar.e()) {
                    int iT = t(eVarV, i9, iG, z);
                    if (iT >= 0 && iT <= cVar.d()) {
                        i6 = iT;
                    } else if (i11 != i10) {
                        i6 = i11;
                    } else {
                        i3 = i11;
                        i4 = iG;
                        i5 = i10;
                        i11 = i3;
                        iG = i4 + 1;
                        i10 = i5;
                    }
                    i3 = i11;
                    int i12 = iG;
                    i5 = i10;
                    d dVarK = k(bVar, cVar.f(), cVar.d(), z, i6, i12, iMin, iMax);
                    i4 = i12;
                    if (dVarK != null) {
                        gVar.f(i4, dVarK);
                        iMin = Math.min(iMin, dVarK.f());
                        iMax = Math.max(iMax, dVarK.f());
                        i11 = i6;
                    } else {
                        i11 = i3;
                    }
                    iG = i4 + 1;
                    i10 = i5;
                }
            }
        }
        return g(eVarV);
    }

    private static com.google.zxing.common.d j(int[] iArr, int i, int[] iArr2) throws ChecksumException, FormatException {
        if (iArr.length == 0) {
            throw FormatException.getFormatInstance();
        }
        int i2 = 1 << (i + 1);
        int iE = e(iArr, iArr2, i2);
        w(iArr, i2);
        com.google.zxing.common.d dVarB = DecodedBitStreamParser.b(iArr, String.valueOf(i));
        dVarB.k(Integer.valueOf(iE));
        dVarB.j(Integer.valueOf(iArr2.length));
        return dVarB;
    }

    private static d k(com.google.zxing.common.b bVar, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        int i7;
        int iD;
        int iA;
        int iC = c(bVar, i, i2, z, i3, i4);
        int[] iArrQ = q(bVar, i, i2, z, iC, i4);
        if (iArrQ == null) {
            return null;
        }
        int iD2 = com.google.zxing.common.l.a.d(iArrQ);
        if (z) {
            i7 = iC + iD2;
        } else {
            for (int i8 = 0; i8 < iArrQ.length / 2; i8++) {
                int i9 = iArrQ[i8];
                iArrQ[i8] = iArrQ[(iArrQ.length - 1) - i8];
                iArrQ[(iArrQ.length - 1) - i8] = i9;
            }
            iC -= iD2;
            i7 = iC;
        }
        if (d(iD2, i5, i6) && (iA = com.google.zxing.o.a.a((iD = h.d(iArrQ)))) != -1) {
            return new d(iC, i7, n(iD), iA);
        }
        return null;
    }

    private static a l(g gVar, g gVar2) {
        a aVarI;
        a aVarI2;
        if (gVar == null || (aVarI = gVar.i()) == null) {
            if (gVar2 == null) {
                return null;
            }
            return gVar2.i();
        }
        if (gVar2 == null || (aVarI2 = gVar2.i()) == null || aVarI.a() == aVarI2.a() || aVarI.b() == aVarI2.b() || aVarI.c() == aVarI2.c()) {
            return aVarI;
        }
        return null;
    }

    private static int[] m(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = 7;
        while (true) {
            int i4 = i & 1;
            if (i4 != i2) {
                i3--;
                if (i3 < 0) {
                    return iArr;
                }
                i2 = i4;
            }
            iArr[i3] = iArr[i3] + 1;
            i >>= 1;
        }
    }

    private static int n(int i) {
        return o(m(i));
    }

    private static int o(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    private static int p(int[] iArr) {
        int iMax = -1;
        for (int i : iArr) {
            iMax = Math.max(iMax, i);
        }
        return iMax;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int[] q(com.google.zxing.common.b r7, int r8, int r9, boolean r10, int r11, int r12) {
        /*
            r0 = 8
            int[] r1 = new int[r0]
            r2 = 1
            if (r10 == 0) goto L9
            r3 = r2
            goto La
        L9:
            r3 = -1
        La:
            r4 = 0
            r5 = r10
        Lc:
            if (r10 == 0) goto L11
            if (r11 >= r9) goto L27
            goto L13
        L11:
            if (r11 < r8) goto L27
        L13:
            if (r4 >= r0) goto L27
            boolean r6 = r7.d(r11, r12)
            if (r6 != r5) goto L22
            r6 = r1[r4]
            int r6 = r6 + r2
            r1[r4] = r6
            int r11 = r11 + r3
            goto Lc
        L22:
            int r4 = r4 + 1
            r5 = r5 ^ 1
            goto Lc
        L27:
            if (r4 == r0) goto L34
            if (r10 == 0) goto L2c
            r8 = r9
        L2c:
            if (r11 != r8) goto L32
            r7 = 7
            if (r4 != r7) goto L32
            goto L34
        L32:
            r7 = 0
            return r7
        L34:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.i.q(com.google.zxing.common.b, int, int, boolean, int, int):int[]");
    }

    private static int r(int i) {
        return 2 << i;
    }

    private static g s(com.google.zxing.common.b bVar, c cVar, com.google.zxing.i iVar, boolean z, int i, int i2) {
        g gVar = new g(cVar, z);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = i3 == 0 ? 1 : -1;
            int iC = (int) iVar.c();
            for (int iD = (int) iVar.d(); iD <= cVar.e() && iD >= cVar.g(); iD += i4) {
                d dVarK = k(bVar, 0, bVar.k(), z, iC, iD, i, i2);
                if (dVarK != null) {
                    gVar.f(iD, dVarK);
                    iC = z ? dVarK.d() : dVarK.b();
                }
            }
            i3++;
        }
        return gVar;
    }

    private static int t(e eVar, int i, int i2, boolean z) {
        int i3 = z ? 1 : -1;
        int i4 = i - i3;
        d dVarB = u(eVar, i4) ? eVar.n(i4).b(i2) : null;
        if (dVarB != null) {
            return z ? dVarB.b() : dVarB.d();
        }
        d dVarC = eVar.n(i).c(i2);
        if (dVarC != null) {
            return z ? dVarC.d() : dVarC.b();
        }
        if (u(eVar, i4)) {
            dVarC = eVar.n(i4).c(i2);
        }
        if (dVarC != null) {
            return z ? dVarC.b() : dVarC.d();
        }
        int i5 = 0;
        while (true) {
            i -= i3;
            if (!u(eVar, i)) {
                c cVarM = eVar.m();
                return z ? cVarM.f() : cVarM.d();
            }
            for (d dVar : eVar.n(i).d()) {
                if (dVar != null) {
                    return (z ? dVar.b() : dVar.d()) + (i3 * i5 * (dVar.b() - dVar.d()));
                }
            }
            i5++;
        }
    }

    private static boolean u(e eVar, int i) {
        return i >= 0 && i <= eVar.j() + 1;
    }

    private static e v(g gVar, g gVar2) throws NotFoundException {
        a aVarL;
        if ((gVar == null && gVar2 == null) || (aVarL = l(gVar, gVar2)) == null) {
            return null;
        }
        return new e(aVarL, c.j(a(gVar), a(gVar2)));
    }

    private static void w(int[] iArr, int i) throws FormatException {
        if (iArr.length < 4) {
            throw FormatException.getFormatInstance();
        }
        int i2 = iArr[0];
        if (i2 > iArr.length) {
            throw FormatException.getFormatInstance();
        }
        if (i2 == 0) {
            if (i >= iArr.length) {
                throw FormatException.getFormatInstance();
            }
            iArr[0] = iArr.length - i;
        }
    }
}
