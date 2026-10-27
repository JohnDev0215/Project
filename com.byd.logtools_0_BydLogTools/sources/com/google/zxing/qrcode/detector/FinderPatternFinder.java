package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.i;
import com.google.zxing.j;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class FinderPatternFinder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.zxing.common.b f4509a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4511c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final j f4513e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<d> f4510b = new ArrayList();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int[] f4512d = new int[5];

    private static final class CenterComparator implements Serializable, Comparator<d> {
        private final float average;

        private CenterComparator(float f2) {
            this.average = f2;
        }

        @Override // java.util.Comparator
        public int compare(d dVar, d dVar2) {
            int iCompare = Integer.compare(dVar2.h(), dVar.h());
            return iCompare == 0 ? Float.compare(Math.abs(dVar.i() - this.average), Math.abs(dVar2.i() - this.average)) : iCompare;
        }
    }

    private static final class FurthestFromAverageComparator implements Serializable, Comparator<d> {
        private final float average;

        private FurthestFromAverageComparator(float f2) {
            this.average = f2;
        }

        @Override // java.util.Comparator
        public int compare(d dVar, d dVar2) {
            return Float.compare(Math.abs(dVar2.i() - this.average), Math.abs(dVar.i() - this.average));
        }
    }

    public FinderPatternFinder(com.google.zxing.common.b bVar, j jVar) {
        this.f4509a = bVar;
        this.f4513e = jVar;
    }

    private static float a(int[] iArr, int i) {
        return ((i - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    private boolean c(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int[] iArrJ = j();
        int i6 = 0;
        while (i >= i6 && i2 >= i6 && this.f4509a.d(i2 - i6, i - i6)) {
            iArrJ[2] = iArrJ[2] + 1;
            i6++;
        }
        if (iArrJ[2] == 0) {
            return false;
        }
        while (i >= i6 && i2 >= i6 && !this.f4509a.d(i2 - i6, i - i6)) {
            iArrJ[1] = iArrJ[1] + 1;
            i6++;
        }
        if (iArrJ[1] == 0) {
            return false;
        }
        while (i >= i6 && i2 >= i6 && this.f4509a.d(i2 - i6, i - i6)) {
            iArrJ[0] = iArrJ[0] + 1;
            i6++;
        }
        if (iArrJ[0] == 0) {
            return false;
        }
        int iH = this.f4509a.h();
        int iK = this.f4509a.k();
        int i7 = 1;
        while (true) {
            int i8 = i + i7;
            if (i8 >= iH || (i5 = i2 + i7) >= iK || !this.f4509a.d(i5, i8)) {
                break;
            }
            iArrJ[2] = iArrJ[2] + 1;
            i7++;
        }
        while (true) {
            int i9 = i + i7;
            if (i9 >= iH || (i4 = i2 + i7) >= iK || this.f4509a.d(i4, i9)) {
                break;
            }
            iArrJ[3] = iArrJ[3] + 1;
            i7++;
        }
        if (iArrJ[3] == 0) {
            return false;
        }
        while (true) {
            int i10 = i + i7;
            if (i10 >= iH || (i3 = i2 + i7) >= iK || !this.f4509a.d(i3, i10)) {
                break;
            }
            iArrJ[4] = iArrJ[4] + 1;
            i7++;
        }
        if (iArrJ[4] == 0) {
            return false;
        }
        return i(iArrJ);
    }

    private float d(int i, int i2, int i3, int i4) {
        com.google.zxing.common.b bVar = this.f4509a;
        int iK = bVar.k();
        int[] iArrJ = j();
        int i5 = i;
        while (i5 >= 0 && bVar.d(i5, i2)) {
            iArrJ[2] = iArrJ[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bVar.d(i5, i2) && iArrJ[1] <= i3) {
            iArrJ[1] = iArrJ[1] + 1;
            i5--;
        }
        if (i5 >= 0 && iArrJ[1] <= i3) {
            while (i5 >= 0 && bVar.d(i5, i2) && iArrJ[0] <= i3) {
                iArrJ[0] = iArrJ[0] + 1;
                i5--;
            }
            if (iArrJ[0] > i3) {
                return Float.NaN;
            }
            int i6 = i + 1;
            while (i6 < iK && bVar.d(i6, i2)) {
                iArrJ[2] = iArrJ[2] + 1;
                i6++;
            }
            if (i6 == iK) {
                return Float.NaN;
            }
            while (i6 < iK && !bVar.d(i6, i2) && iArrJ[3] < i3) {
                iArrJ[3] = iArrJ[3] + 1;
                i6++;
            }
            if (i6 != iK && iArrJ[3] < i3) {
                while (i6 < iK && bVar.d(i6, i2) && iArrJ[4] < i3) {
                    iArrJ[4] = iArrJ[4] + 1;
                    i6++;
                }
                if (iArrJ[4] < i3 && Math.abs(((((iArrJ[0] + iArrJ[1]) + iArrJ[2]) + iArrJ[3]) + iArrJ[4]) - i4) * 5 < i4 && h(iArrJ)) {
                    return a(iArrJ, i6);
                }
            }
        }
        return Float.NaN;
    }

    private float e(int i, int i2, int i3, int i4) {
        com.google.zxing.common.b bVar = this.f4509a;
        int iH = bVar.h();
        int[] iArrJ = j();
        int i5 = i;
        while (i5 >= 0 && bVar.d(i2, i5)) {
            iArrJ[2] = iArrJ[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bVar.d(i2, i5) && iArrJ[1] <= i3) {
            iArrJ[1] = iArrJ[1] + 1;
            i5--;
        }
        if (i5 >= 0 && iArrJ[1] <= i3) {
            while (i5 >= 0 && bVar.d(i2, i5) && iArrJ[0] <= i3) {
                iArrJ[0] = iArrJ[0] + 1;
                i5--;
            }
            if (iArrJ[0] > i3) {
                return Float.NaN;
            }
            int i6 = i + 1;
            while (i6 < iH && bVar.d(i2, i6)) {
                iArrJ[2] = iArrJ[2] + 1;
                i6++;
            }
            if (i6 == iH) {
                return Float.NaN;
            }
            while (i6 < iH && !bVar.d(i2, i6) && iArrJ[3] < i3) {
                iArrJ[3] = iArrJ[3] + 1;
                i6++;
            }
            if (i6 != iH && iArrJ[3] < i3) {
                while (i6 < iH && bVar.d(i2, i6) && iArrJ[4] < i3) {
                    iArrJ[4] = iArrJ[4] + 1;
                    i6++;
                }
                if (iArrJ[4] < i3 && Math.abs(((((iArrJ[0] + iArrJ[1]) + iArrJ[2]) + iArrJ[3]) + iArrJ[4]) - i4) * 5 < i4 * 2 && h(iArrJ)) {
                    return a(iArrJ, i6);
                }
            }
        }
        return Float.NaN;
    }

    private int g() {
        if (this.f4510b.size() <= 1) {
            return 0;
        }
        d dVar = null;
        for (d dVar2 : this.f4510b) {
            if (dVar2.h() >= 2) {
                if (dVar != null) {
                    this.f4511c = true;
                    return ((int) (Math.abs(dVar.c() - dVar2.c()) - Math.abs(dVar.d() - dVar2.d()))) / 2;
                }
                dVar = dVar2;
            }
        }
        return 0;
    }

    protected static boolean h(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f2 = i / 7.0f;
        float f3 = f2 / 2.0f;
        return Math.abs(f2 - ((float) iArr[0])) < f3 && Math.abs(f2 - ((float) iArr[1])) < f3 && Math.abs((f2 * 3.0f) - ((float) iArr[2])) < 3.0f * f3 && Math.abs(f2 - ((float) iArr[3])) < f3 && Math.abs(f2 - ((float) iArr[4])) < f3;
    }

    protected static boolean i(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 7) {
            return false;
        }
        float f2 = i / 7.0f;
        float f3 = f2 / 1.333f;
        return Math.abs(f2 - ((float) iArr[0])) < f3 && Math.abs(f2 - ((float) iArr[1])) < f3 && Math.abs((f2 * 3.0f) - ((float) iArr[2])) < 3.0f * f3 && Math.abs(f2 - ((float) iArr[3])) < f3 && Math.abs(f2 - ((float) iArr[4])) < f3;
    }

    private int[] j() {
        b(this.f4512d);
        return this.f4512d;
    }

    private boolean l() {
        int size = this.f4510b.size();
        float fAbs = 0.0f;
        float fI = 0.0f;
        int i = 0;
        for (d dVar : this.f4510b) {
            if (dVar.h() >= 2) {
                i++;
                fI += dVar.i();
            }
        }
        if (i < 3) {
            return false;
        }
        float f2 = fI / size;
        Iterator<d> it = this.f4510b.iterator();
        while (it.hasNext()) {
            fAbs += Math.abs(it.next().i() - f2);
        }
        return fAbs <= fI * 0.05f;
    }

    private d[] m() throws NotFoundException {
        int size = this.f4510b.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        float fI = 0.0f;
        if (size > 3) {
            Iterator<d> it = this.f4510b.iterator();
            float f2 = 0.0f;
            float f3 = 0.0f;
            while (it.hasNext()) {
                float fI2 = it.next().i();
                f2 += fI2;
                f3 += fI2 * fI2;
            }
            float f4 = f2 / size;
            float fSqrt = (float) Math.sqrt((f3 / r0) - (f4 * f4));
            Collections.sort(this.f4510b, new FurthestFromAverageComparator(f4));
            float fMax = Math.max(0.2f * f4, fSqrt);
            int i = 0;
            while (i < this.f4510b.size() && this.f4510b.size() > 3) {
                if (Math.abs(this.f4510b.get(i).i() - f4) > fMax) {
                    this.f4510b.remove(i);
                    i--;
                }
                i++;
            }
        }
        if (this.f4510b.size() > 3) {
            Iterator<d> it2 = this.f4510b.iterator();
            while (it2.hasNext()) {
                fI += it2.next().i();
            }
            Collections.sort(this.f4510b, new CenterComparator(fI / this.f4510b.size()));
            List<d> list = this.f4510b;
            list.subList(3, list.size()).clear();
        }
        return new d[]{this.f4510b.get(0), this.f4510b.get(1), this.f4510b.get(2)};
    }

    protected final void b(int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 0;
        }
    }

    final e f(Map<DecodeHintType, ?> map) throws NotFoundException {
        boolean z = map != null && map.containsKey(DecodeHintType.TRY_HARDER);
        int iH = this.f4509a.h();
        int iK = this.f4509a.k();
        int i = (iH * 3) / 388;
        if (i < 3 || z) {
            i = 3;
        }
        int[] iArr = new int[5];
        int i2 = i - 1;
        boolean zL = false;
        while (i2 < iH && !zL) {
            b(iArr);
            int i3 = 0;
            int i4 = 0;
            while (i3 < iK) {
                if (this.f4509a.d(i3, i2)) {
                    if ((i4 & 1) == 1) {
                        i4++;
                    }
                    iArr[i4] = iArr[i4] + 1;
                } else if ((i4 & 1) != 0) {
                    iArr[i4] = iArr[i4] + 1;
                } else if (i4 != 4) {
                    i4++;
                    iArr[i4] = iArr[i4] + 1;
                } else if (h(iArr) && k(iArr, i2, i3)) {
                    if (this.f4511c) {
                        zL = l();
                    } else {
                        int iG = g();
                        if (iG > iArr[2]) {
                            i2 += (iG - iArr[2]) - 2;
                            i3 = iK - 1;
                        }
                    }
                    b(iArr);
                    i4 = 0;
                    i = 2;
                } else {
                    n(iArr);
                    i4 = 3;
                }
                i3++;
            }
            if (h(iArr) && k(iArr, i2, iK)) {
                i = iArr[0];
                if (this.f4511c) {
                    zL = l();
                }
            }
            i2 += i;
        }
        d[] dVarArrM = m();
        i.e(dVarArrM);
        return new e(dVarArrM);
    }

    protected final boolean k(int[] iArr, int i, int i2) {
        boolean z = false;
        int i3 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int iA = (int) a(iArr, i2);
        float fE = e(i, iA, iArr[2], i3);
        if (!Float.isNaN(fE)) {
            int i4 = (int) fE;
            float fD = d(iA, i4, iArr[2], i3);
            if (!Float.isNaN(fD) && c(i4, (int) fD)) {
                float f2 = i3 / 7.0f;
                int i5 = 0;
                while (true) {
                    if (i5 >= this.f4510b.size()) {
                        break;
                    }
                    d dVar = this.f4510b.get(i5);
                    if (dVar.f(f2, fE, fD)) {
                        this.f4510b.set(i5, dVar.g(fE, fD, f2));
                        z = true;
                        break;
                    }
                    i5++;
                }
                if (!z) {
                    d dVar2 = new d(fD, fE, f2);
                    this.f4510b.add(dVar2);
                    j jVar = this.f4513e;
                    if (jVar != null) {
                        jVar.a(dVar2);
                    }
                }
                return true;
            }
        }
        return false;
    }

    protected final void n(int[] iArr) {
        iArr[0] = iArr[2];
        iArr[1] = iArr[3];
        iArr[2] = iArr[4];
        iArr[3] = 1;
        iArr[4] = 0;
    }
}
