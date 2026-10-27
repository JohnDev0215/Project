package com.google.zxing.n.r.g;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.h;
import com.google.zxing.i;
import com.google.zxing.n.k;
import com.google.zxing.n.r.f;
import com.google.zxing.oned.rss.expanded.decoders.j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: RSSExpandedReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends com.google.zxing.n.r.a {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final int[] f4379g = {7, 5, 4, 3, 1};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int[] f4380h = {4, 20, 52, 104, 204};
    private static final int[] i = {0, 348, 1388, 2948, 3988};
    private static final int[][] j = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] k = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, 145, 13, 39, 117, 140, 209, 205}, new int[]{193, 157, 49, 147, 19, 57, 171, 91}, new int[]{62, 186, 136, 197, 169, 85, 44, 132}, new int[]{185, 133, 188, 142, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, 158, 52, 156}, new int[]{46, 138, 203, 187, 139, 206, 196, 166}, new int[]{76, 17, 51, 153, 37, 111, 122, 155}, new int[]{43, 129, 176, 106, 107, 110, 119, 146}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, 200, 178, 112, 125, 164}, new int[]{70, 210, 208, 202, 184, 130, 179, 115}, new int[]{134, 191, 151, 31, 93, 68, 204, 190}, new int[]{148, 22, 66, 198, 172, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{120, 149, 25, 75, 14, 42, 126, 167}, new int[]{79, 26, 78, 23, 69, 207, 199, 175}, new int[]{103, 98, 83, 38, 114, 131, 182, 124}, new int[]{161, 61, 183, 127, 170, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, 194, 160, 58, 174, 100, 89}};
    private static final int[][] l = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    private final List<b> m = new ArrayList(11);
    private final List<c> n = new ArrayList();
    private final int[] o = new int[2];
    private boolean p;

    private static boolean A(Iterable<b> iterable, Iterable<c> iterable2) {
        boolean z;
        boolean z2;
        Iterator<c> it = iterable2.iterator();
        do {
            z = false;
            if (!it.hasNext()) {
                return false;
            }
            c next = it.next();
            Iterator<b> it2 = iterable.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = true;
                    break;
                }
                b next2 = it2.next();
                Iterator<b> it3 = next.a().iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z2 = false;
                        break;
                    }
                    if (next2.equals(it3.next())) {
                        z2 = true;
                        break;
                    }
                }
                if (!z2) {
                    break;
                }
            }
        } while (!z);
        return true;
    }

    private static boolean B(List<b> list) {
        boolean z;
        for (int[] iArr : l) {
            if (list.size() <= iArr.length) {
                int i2 = 0;
                while (true) {
                    if (i2 >= list.size()) {
                        z = true;
                        break;
                    }
                    if (list.get(i2).b().c() != iArr[i2]) {
                        z = false;
                        break;
                    }
                    i2++;
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    private com.google.zxing.n.r.c C(com.google.zxing.common.a aVar, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        if (z) {
            int i6 = this.o[0] - 1;
            while (i6 >= 0 && !aVar.h(i6)) {
                i6--;
            }
            int i7 = i6 + 1;
            int[] iArr = this.o;
            i5 = iArr[0] - i7;
            i3 = iArr[1];
            i4 = i7;
        } else {
            int[] iArr2 = this.o;
            int i8 = iArr2[0];
            int iK = aVar.k(iArr2[1] + 1);
            i3 = iK;
            i4 = i8;
            i5 = iK - this.o[1];
        }
        int[] iArrI = i();
        System.arraycopy(iArrI, 0, iArrI, 1, iArrI.length - 1);
        iArrI[0] = i5;
        try {
            return new com.google.zxing.n.r.c(com.google.zxing.n.r.a.p(iArrI, j), new int[]{i4, i3}, i4, i3, i2);
        } catch (NotFoundException unused) {
            return null;
        }
    }

    private static void D(List<b> list, List<c> list2) {
        boolean z;
        Iterator<c> it = list2.iterator();
        while (it.hasNext()) {
            c next = it.next();
            if (next.a().size() != list.size()) {
                Iterator<b> it2 = next.a().iterator();
                while (true) {
                    z = false;
                    boolean z2 = true;
                    if (!it2.hasNext()) {
                        z = true;
                        break;
                    }
                    b next2 = it2.next();
                    Iterator<b> it3 = list.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            z2 = false;
                            break;
                        } else if (next2.equals(it3.next())) {
                            break;
                        }
                    }
                    if (!z2) {
                        break;
                    }
                }
                if (z) {
                    it.remove();
                }
            }
        }
    }

    private static void F(int[] iArr) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length / 2; i2++) {
            int i3 = iArr[i2];
            int i4 = (length - i2) - 1;
            iArr[i2] = iArr[i4];
            iArr[i4] = i3;
        }
    }

    private void G(int i2, boolean z) {
        boolean zC = false;
        int i3 = 0;
        boolean zC2 = false;
        while (true) {
            if (i3 >= this.n.size()) {
                break;
            }
            c cVar = this.n.get(i3);
            if (cVar.b() > i2) {
                zC = cVar.c(this.m);
                break;
            } else {
                zC2 = cVar.c(this.m);
                i3++;
            }
        }
        if (zC || zC2 || A(this.m, this.n)) {
            return;
        }
        this.n.add(i3, new c(this.m, i2, z));
        D(this.m, this.n);
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void q(int r11) throws com.google.zxing.NotFoundException {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.n.r.g.d.q(int):void");
    }

    private boolean r() {
        b bVar = this.m.get(0);
        com.google.zxing.n.r.b bVarC = bVar.c();
        com.google.zxing.n.r.b bVarD = bVar.d();
        if (bVarD == null) {
            return false;
        }
        int iA = bVarD.a();
        int i2 = 2;
        for (int i3 = 1; i3 < this.m.size(); i3++) {
            b bVar2 = this.m.get(i3);
            iA += bVar2.c().a();
            i2++;
            com.google.zxing.n.r.b bVarD2 = bVar2.d();
            if (bVarD2 != null) {
                iA += bVarD2.a();
                i2++;
            }
        }
        return ((i2 + (-4)) * 211) + (iA % 211) == bVarC.b();
    }

    private List<b> s(List<c> list, int i2) throws NotFoundException {
        while (i2 < this.n.size()) {
            c cVar = this.n.get(i2);
            this.m.clear();
            Iterator<c> it = list.iterator();
            while (it.hasNext()) {
                this.m.addAll(it.next().a());
            }
            this.m.addAll(cVar.a());
            if (B(this.m)) {
                if (r()) {
                    return this.m;
                }
                ArrayList arrayList = new ArrayList(list);
                arrayList.add(cVar);
                try {
                    return s(arrayList, i2 + 1);
                } catch (NotFoundException unused) {
                    continue;
                }
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private List<b> t(boolean z) {
        List<b> listS = null;
        if (this.n.size() > 25) {
            this.n.clear();
            return null;
        }
        this.m.clear();
        if (z) {
            Collections.reverse(this.n);
        }
        try {
            listS = s(new ArrayList(), 0);
        } catch (NotFoundException unused) {
        }
        if (z) {
            Collections.reverse(this.n);
        }
        return listS;
    }

    static h u(List<b> list) throws NotFoundException, FormatException {
        String strD = j.a(a.a(list)).d();
        i[] iVarArrA = list.get(0).b().a();
        i[] iVarArrA2 = list.get(list.size() - 1).b().a();
        return new h(strD, null, new i[]{iVarArrA[0], iVarArrA[1], iVarArrA2[0], iVarArrA2[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    private void x(com.google.zxing.common.a aVar, List<b> list, int i2) throws NotFoundException {
        int[] iArrI = i();
        iArrI[0] = 0;
        iArrI[1] = 0;
        iArrI[2] = 0;
        iArrI[3] = 0;
        int iL = aVar.l();
        if (i2 < 0) {
            i2 = list.isEmpty() ? 0 : list.get(list.size() - 1).b().b()[1];
        }
        boolean z = list.size() % 2 != 0;
        if (this.p) {
            z = !z;
        }
        boolean z2 = false;
        while (i2 < iL) {
            z2 = !aVar.h(i2);
            if (!z2) {
                break;
            } else {
                i2++;
            }
        }
        int i3 = 0;
        boolean z3 = z2;
        int i4 = i2;
        while (i2 < iL) {
            if (aVar.h(i2) != z3) {
                iArrI[i3] = iArrI[i3] + 1;
            } else {
                if (i3 == 3) {
                    if (z) {
                        F(iArrI);
                    }
                    if (com.google.zxing.n.r.a.o(iArrI)) {
                        int[] iArr = this.o;
                        iArr[0] = i4;
                        iArr[1] = i2;
                        return;
                    }
                    if (z) {
                        F(iArrI);
                    }
                    i4 += iArrI[0] + iArrI[1];
                    iArrI[0] = iArrI[2];
                    iArrI[1] = iArrI[3];
                    iArrI[2] = 0;
                    iArrI[3] = 0;
                    i3--;
                } else {
                    i3++;
                }
                iArrI[i3] = 1;
                z3 = !z3;
            }
            i2++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int y(com.google.zxing.common.a aVar, int i2) {
        return aVar.h(i2) ? aVar.j(aVar.k(i2)) : aVar.k(aVar.j(i2));
    }

    private static boolean z(com.google.zxing.n.r.c cVar, boolean z, boolean z2) {
        return (cVar.c() == 0 && z && z2) ? false : true;
    }

    b E(com.google.zxing.common.a aVar, List<b> list, int i2) throws NotFoundException {
        com.google.zxing.n.r.c cVarC;
        com.google.zxing.n.r.b bVarV;
        boolean z = list.size() % 2 == 0;
        if (this.p) {
            z = !z;
        }
        int iY = -1;
        boolean z2 = true;
        do {
            x(aVar, list, iY);
            cVarC = C(aVar, i2, z);
            if (cVarC == null) {
                iY = y(aVar, this.o[0]);
            } else {
                z2 = false;
            }
        } while (z2);
        com.google.zxing.n.r.b bVarV2 = v(aVar, cVarC, z, true);
        if (!list.isEmpty() && list.get(list.size() - 1).f()) {
            throw NotFoundException.getNotFoundInstance();
        }
        try {
            bVarV = v(aVar, cVarC, z, false);
        } catch (NotFoundException unused) {
            bVarV = null;
        }
        return new b(bVarV2, bVarV, cVarC, true);
    }

    @Override // com.google.zxing.n.k
    public h b(int i2, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        this.m.clear();
        this.p = false;
        try {
            return u(w(i2, aVar));
        } catch (NotFoundException unused) {
            this.m.clear();
            this.p = true;
            return u(w(i2, aVar));
        }
    }

    @Override // com.google.zxing.n.k, com.google.zxing.g
    public void reset() {
        this.m.clear();
        this.n.clear();
    }

    com.google.zxing.n.r.b v(com.google.zxing.common.a aVar, com.google.zxing.n.r.c cVar, boolean z, boolean z2) throws NotFoundException {
        int[] iArrH = h();
        for (int i2 = 0; i2 < iArrH.length; i2++) {
            iArrH[i2] = 0;
        }
        if (z2) {
            k.f(aVar, cVar.b()[0], iArrH);
        } else {
            k.e(aVar, cVar.b()[1], iArrH);
            int i3 = 0;
            for (int length = iArrH.length - 1; i3 < length; length--) {
                int i4 = iArrH[i3];
                iArrH[i3] = iArrH[length];
                iArrH[length] = i4;
                i3++;
            }
        }
        float fD = com.google.zxing.common.l.a.d(iArrH) / 17.0f;
        float f2 = (cVar.b()[1] - cVar.b()[0]) / 15.0f;
        if (Math.abs(fD - f2) / f2 > 0.3f) {
            throw NotFoundException.getNotFoundInstance();
        }
        int[] iArrL = l();
        int[] iArrJ = j();
        float[] fArrM = m();
        float[] fArrK = k();
        for (int i5 = 0; i5 < iArrH.length; i5++) {
            float f3 = (iArrH[i5] * 1.0f) / fD;
            int i6 = (int) (0.5f + f3);
            if (i6 <= 0) {
                if (f3 < 0.3f) {
                    throw NotFoundException.getNotFoundInstance();
                }
                i6 = 1;
            } else if (i6 > 8) {
                if (f3 > 8.7f) {
                    throw NotFoundException.getNotFoundInstance();
                }
                i6 = 8;
            }
            int i7 = i5 / 2;
            if ((i5 & 1) == 0) {
                iArrL[i7] = i6;
                fArrM[i7] = f3 - i6;
            } else {
                iArrJ[i7] = i6;
                fArrK[i7] = f3 - i6;
            }
        }
        q(17);
        int iC = (((cVar.c() * 4) + (z ? 0 : 2)) + (!z2 ? 1 : 0)) - 1;
        int i8 = 0;
        int i9 = 0;
        for (int length2 = iArrL.length - 1; length2 >= 0; length2--) {
            if (z(cVar, z, z2)) {
                i8 += iArrL[length2] * k[iC][length2 * 2];
            }
            i9 += iArrL[length2];
        }
        int i10 = 0;
        for (int length3 = iArrJ.length - 1; length3 >= 0; length3--) {
            if (z(cVar, z, z2)) {
                i10 += iArrJ[length3] * k[iC][(length3 * 2) + 1];
            }
        }
        int i11 = i8 + i10;
        if ((i9 & 1) != 0 || i9 > 13 || i9 < 4) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i12 = (13 - i9) / 2;
        int i13 = f4379g[i12];
        return new com.google.zxing.n.r.b((f.b(iArrL, i13, true) * f4380h[i12]) + f.b(iArrJ, 9 - i13, false) + i[i12], i11);
    }

    List<b> w(int i2, com.google.zxing.common.a aVar) throws NotFoundException {
        boolean z = false;
        while (!z) {
            try {
                List<b> list = this.m;
                list.add(E(aVar, list, i2));
            } catch (NotFoundException e2) {
                if (this.m.isEmpty()) {
                    throw e2;
                }
                z = true;
            }
        }
        if (r()) {
            return this.m;
        }
        boolean z2 = !this.n.isEmpty();
        G(i2, false);
        if (z2) {
            List<b> listT = t(false);
            if (listT != null) {
                return listT;
            }
            List<b> listT2 = t(true);
            if (listT2 != null) {
                return listT2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
