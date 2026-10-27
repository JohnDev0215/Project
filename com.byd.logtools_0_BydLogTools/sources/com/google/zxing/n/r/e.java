package com.google.zxing.n.r;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.h;
import com.google.zxing.i;
import com.google.zxing.j;
import com.google.zxing.n.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: RSS14Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int[] f4370g = {1, 10, 34, 70, 126};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int[] f4371h = {4, 20, 48, 81};
    private static final int[] i = {0, 161, 961, 2015, 2715};
    private static final int[] j = {0, 336, 1036, 1516};
    private static final int[] k = {8, 6, 4, 3, 1};
    private static final int[] l = {2, 4, 6, 8};
    private static final int[][] m = {new int[]{3, 8, 2, 1}, new int[]{3, 5, 5, 1}, new int[]{3, 3, 7, 1}, new int[]{3, 1, 9, 1}, new int[]{2, 7, 4, 1}, new int[]{2, 5, 6, 1}, new int[]{2, 3, 8, 1}, new int[]{1, 5, 7, 1}, new int[]{1, 3, 9, 1}};
    private final List<d> n = new ArrayList();
    private final List<d> o = new ArrayList();

    private static void q(Collection<d> collection, d dVar) {
        if (dVar == null) {
            return;
        }
        boolean z = false;
        Iterator<d> it = collection.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            d next = it.next();
            if (next.b() == dVar.b()) {
                next.e();
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        collection.add(dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0041 A[PHI: r6 r7
  0x0041: PHI (r6v5 boolean) = (r6v2 boolean), (r6v10 boolean) binds: [B:24:0x003f, B:12:0x0026] A[DONT_GENERATE, DONT_INLINE]
  0x0041: PHI (r7v5 boolean) = (r7v2 boolean), (r7v14 boolean) binds: [B:24:0x003f, B:12:0x0026] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0044 A[PHI: r6 r7
  0x0044: PHI (r6v7 boolean) = (r6v2 boolean), (r6v10 boolean) binds: [B:24:0x003f, B:12:0x0026] A[DONT_GENERATE, DONT_INLINE]
  0x0044: PHI (r7v11 boolean) = (r7v2 boolean), (r7v14 boolean) binds: [B:24:0x003f, B:12:0x0026] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:85:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void r(boolean r10, int r11) throws com.google.zxing.NotFoundException {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.n.r.e.r(boolean, int):void");
    }

    private static boolean s(d dVar, d dVar2) {
        int iA = (dVar.a() + (dVar2.a() * 16)) % 79;
        int iC = (dVar.d().c() * 9) + dVar2.d().c();
        if (iC > 72) {
            iC--;
        }
        if (iC > 8) {
            iC--;
        }
        return iA == iC;
    }

    private static h t(d dVar, d dVar2) {
        String strValueOf = String.valueOf((((long) dVar.b()) * 4537077) + ((long) dVar2.b()));
        StringBuilder sb = new StringBuilder(14);
        for (int length = 13 - strValueOf.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(strValueOf);
        int i2 = 0;
        for (int i3 = 0; i3 < 13; i3++) {
            int iCharAt = sb.charAt(i3) - '0';
            if ((i3 & 1) == 0) {
                iCharAt *= 3;
            }
            i2 += iCharAt;
        }
        int i4 = 10 - (i2 % 10);
        if (i4 == 10) {
            i4 = 0;
        }
        sb.append(i4);
        i[] iVarArrA = dVar.d().a();
        i[] iVarArrA2 = dVar2.d().a();
        return new h(sb.toString(), null, new i[]{iVarArrA[0], iVarArrA[1], iVarArrA2[0], iVarArrA2[1]}, BarcodeFormat.RSS_14);
    }

    private b u(com.google.zxing.common.a aVar, c cVar, boolean z) throws NotFoundException {
        int[] iArrH = h();
        for (int i2 = 0; i2 < iArrH.length; i2++) {
            iArrH[i2] = 0;
        }
        if (z) {
            k.f(aVar, cVar.b()[0], iArrH);
        } else {
            k.e(aVar, cVar.b()[1] + 1, iArrH);
            int i3 = 0;
            for (int length = iArrH.length - 1; i3 < length; length--) {
                int i4 = iArrH[i3];
                iArrH[i3] = iArrH[length];
                iArrH[length] = i4;
                i3++;
            }
        }
        int i5 = z ? 16 : 15;
        float fD = com.google.zxing.common.l.a.d(iArrH) / i5;
        int[] iArrL = l();
        int[] iArrJ = j();
        float[] fArrM = m();
        float[] fArrK = k();
        for (int i6 = 0; i6 < iArrH.length; i6++) {
            float f2 = iArrH[i6] / fD;
            int i7 = (int) (0.5f + f2);
            if (i7 <= 0) {
                i7 = 1;
            } else if (i7 > 8) {
                i7 = 8;
            }
            int i8 = i6 / 2;
            if ((i6 & 1) == 0) {
                iArrL[i8] = i7;
                fArrM[i8] = f2 - i7;
            } else {
                iArrJ[i8] = i7;
                fArrK[i8] = f2 - i7;
            }
        }
        r(z, i5);
        int i9 = 0;
        int i10 = 0;
        for (int length2 = iArrL.length - 1; length2 >= 0; length2--) {
            i9 = (i9 * 9) + iArrL[length2];
            i10 += iArrL[length2];
        }
        int i11 = 0;
        int i12 = 0;
        for (int length3 = iArrJ.length - 1; length3 >= 0; length3--) {
            i11 = (i11 * 9) + iArrJ[length3];
            i12 += iArrJ[length3];
        }
        int i13 = i9 + (i11 * 3);
        if (!z) {
            if ((i12 & 1) != 0 || i12 > 10 || i12 < 4) {
                throw NotFoundException.getNotFoundInstance();
            }
            int i14 = (10 - i12) / 2;
            int i15 = l[i14];
            return new b((f.b(iArrJ, 9 - i15, false) * f4371h[i14]) + f.b(iArrL, i15, true) + j[i14], i13);
        }
        if ((i10 & 1) != 0 || i10 > 12 || i10 < 4) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i16 = (12 - i10) / 2;
        int i17 = k[i16];
        return new b((f.b(iArrL, i17, false) * f4370g[i16]) + f.b(iArrJ, 9 - i17, true) + i[i16], i13);
    }

    private d v(com.google.zxing.common.a aVar, boolean z, int i2, Map<DecodeHintType, ?> map) {
        try {
            c cVarX = x(aVar, i2, z, w(aVar, z));
            j jVar = map == null ? null : (j) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            if (jVar != null) {
                float fL = (r1[0] + r1[1]) / 2.0f;
                if (z) {
                    fL = (aVar.l() - 1) - fL;
                }
                jVar.a(new i(fL, i2));
            }
            b bVarU = u(aVar, cVarX, true);
            b bVarU2 = u(aVar, cVarX, false);
            return new d((bVarU.b() * 1597) + bVarU2.b(), bVarU.a() + (bVarU2.a() * 4), cVarX);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    private int[] w(com.google.zxing.common.a aVar, boolean z) throws NotFoundException {
        int[] iArrI = i();
        iArrI[0] = 0;
        iArrI[1] = 0;
        iArrI[2] = 0;
        iArrI[3] = 0;
        int iL = aVar.l();
        int i2 = 0;
        boolean z2 = false;
        while (i2 < iL) {
            z2 = !aVar.h(i2);
            if (z == z2) {
                break;
            }
            i2++;
        }
        int i3 = 0;
        int i4 = i2;
        while (i2 < iL) {
            if (aVar.h(i2) != z2) {
                iArrI[i3] = iArrI[i3] + 1;
            } else {
                if (i3 != 3) {
                    i3++;
                } else {
                    if (a.o(iArrI)) {
                        return new int[]{i4, i2};
                    }
                    i4 += iArrI[0] + iArrI[1];
                    iArrI[0] = iArrI[2];
                    iArrI[1] = iArrI[3];
                    iArrI[2] = 0;
                    iArrI[3] = 0;
                    i3--;
                }
                iArrI[i3] = 1;
                z2 = !z2;
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private c x(com.google.zxing.common.a aVar, int i2, boolean z, int[] iArr) throws NotFoundException {
        int iL;
        int i3;
        boolean zH = aVar.h(iArr[0]);
        int i4 = iArr[0] - 1;
        while (i4 >= 0 && zH != aVar.h(i4)) {
            i4--;
        }
        int i5 = i4 + 1;
        int i6 = iArr[0] - i5;
        int[] iArrI = i();
        System.arraycopy(iArrI, 0, iArrI, 1, iArrI.length - 1);
        iArrI[0] = i6;
        int iP = a.p(iArrI, m);
        int i7 = iArr[1];
        if (z) {
            int iL2 = (aVar.l() - 1) - i5;
            iL = (aVar.l() - 1) - i7;
            i3 = iL2;
        } else {
            iL = i7;
            i3 = i5;
        }
        return new c(iP, new int[]{i5, iArr[1]}, i3, iL, i2);
    }

    @Override // com.google.zxing.n.k
    public h b(int i2, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        q(this.n, v(aVar, false, i2, map));
        aVar.p();
        q(this.o, v(aVar, true, i2, map));
        aVar.p();
        for (d dVar : this.n) {
            if (dVar.c() > 1) {
                for (d dVar2 : this.o) {
                    if (dVar2.c() > 1 && s(dVar, dVar2)) {
                        return t(dVar, dVar2);
                    }
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    @Override // com.google.zxing.n.k, com.google.zxing.g
    public void reset() {
        this.n.clear();
        this.o.clear();
    }
}
