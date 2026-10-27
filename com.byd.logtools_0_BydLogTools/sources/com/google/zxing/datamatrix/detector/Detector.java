package com.google.zxing.datamatrix.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.f;
import com.google.zxing.common.h;
import com.google.zxing.i;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class Detector {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.google.zxing.common.b f4274a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.google.zxing.common.l.b f4275b;

    private static final class ResultPointsAndTransitionsComparator implements Serializable, Comparator<b> {
        private ResultPointsAndTransitionsComparator() {
        }

        @Override // java.util.Comparator
        public int compare(b bVar, b bVar2) {
            return bVar.c() - bVar2.c();
        }
    }

    private static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final i f4276a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final i f4277b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f4278c;

        i a() {
            return this.f4276a;
        }

        i b() {
            return this.f4277b;
        }

        int c() {
            return this.f4278c;
        }

        public String toString() {
            return this.f4276a + "/" + this.f4277b + '/' + this.f4278c;
        }

        private b(i iVar, i iVar2, int i) {
            this.f4276a = iVar;
            this.f4277b = iVar2;
            this.f4278c = i;
        }
    }

    public Detector(com.google.zxing.common.b bVar) throws NotFoundException {
        this.f4274a = bVar;
        this.f4275b = new com.google.zxing.common.l.b(bVar);
    }

    private i a(i iVar, i iVar2, i iVar3, i iVar4, int i) {
        float f2 = i;
        float fD = d(iVar, iVar2) / f2;
        float fD2 = d(iVar3, iVar4);
        i iVar5 = new i(iVar4.c() + (((iVar4.c() - iVar3.c()) / fD2) * fD), iVar4.d() + (fD * ((iVar4.d() - iVar3.d()) / fD2)));
        float fD3 = d(iVar, iVar3) / f2;
        float fD4 = d(iVar2, iVar4);
        i iVar6 = new i(iVar4.c() + (((iVar4.c() - iVar2.c()) / fD4) * fD3), iVar4.d() + (fD3 * ((iVar4.d() - iVar2.d()) / fD4)));
        if (f(iVar5)) {
            return (f(iVar6) && Math.abs(h(iVar3, iVar5).c() - h(iVar2, iVar5).c()) > Math.abs(h(iVar3, iVar6).c() - h(iVar2, iVar6).c())) ? iVar6 : iVar5;
        }
        if (f(iVar6)) {
            return iVar6;
        }
        return null;
    }

    private i b(i iVar, i iVar2, i iVar3, i iVar4, int i, int i2) {
        float fD = d(iVar, iVar2) / i;
        float fD2 = d(iVar3, iVar4);
        i iVar5 = new i(iVar4.c() + (((iVar4.c() - iVar3.c()) / fD2) * fD), iVar4.d() + (fD * ((iVar4.d() - iVar3.d()) / fD2)));
        float fD3 = d(iVar, iVar3) / i2;
        float fD4 = d(iVar2, iVar4);
        i iVar6 = new i(iVar4.c() + (((iVar4.c() - iVar2.c()) / fD4) * fD3), iVar4.d() + (fD3 * ((iVar4.d() - iVar2.d()) / fD4)));
        if (f(iVar5)) {
            return (f(iVar6) && Math.abs(i - h(iVar3, iVar5).c()) + Math.abs(i2 - h(iVar2, iVar5).c()) > Math.abs(i - h(iVar3, iVar6).c()) + Math.abs(i2 - h(iVar2, iVar6).c())) ? iVar6 : iVar5;
        }
        if (f(iVar6)) {
            return iVar6;
        }
        return null;
    }

    private static int d(i iVar, i iVar2) {
        return com.google.zxing.common.l.a.c(i.b(iVar, iVar2));
    }

    private static void e(Map<i, Integer> map, i iVar) {
        Integer num = map.get(iVar);
        map.put(iVar, Integer.valueOf(num != null ? 1 + num.intValue() : 1));
    }

    private boolean f(i iVar) {
        return iVar.c() >= 0.0f && iVar.c() < ((float) this.f4274a.k()) && iVar.d() > 0.0f && iVar.d() < ((float) this.f4274a.h());
    }

    private static com.google.zxing.common.b g(com.google.zxing.common.b bVar, i iVar, i iVar2, i iVar3, i iVar4, int i, int i2) throws NotFoundException {
        float f2 = i - 0.5f;
        float f3 = i2 - 0.5f;
        return h.b().c(bVar, i, i2, 0.5f, 0.5f, f2, 0.5f, f2, f3, 0.5f, f3, iVar.c(), iVar.d(), iVar4.c(), iVar4.d(), iVar3.c(), iVar3.d(), iVar2.c(), iVar2.d());
    }

    private b h(i iVar, i iVar2) {
        int iC = (int) iVar.c();
        int iD = (int) iVar.d();
        int iC2 = (int) iVar2.c();
        int iD2 = (int) iVar2.d();
        int i = 0;
        boolean z = Math.abs(iD2 - iD) > Math.abs(iC2 - iC);
        if (z) {
            iD = iC;
            iC = iD;
            iD2 = iC2;
            iC2 = iD2;
        }
        int iAbs = Math.abs(iC2 - iC);
        int iAbs2 = Math.abs(iD2 - iD);
        int i2 = (-iAbs) / 2;
        int i3 = iD < iD2 ? 1 : -1;
        int i4 = iC >= iC2 ? -1 : 1;
        boolean zD = this.f4274a.d(z ? iD : iC, z ? iC : iD);
        while (iC != iC2) {
            boolean zD2 = this.f4274a.d(z ? iD : iC, z ? iC : iD);
            if (zD2 != zD) {
                i++;
                zD = zD2;
            }
            i2 += iAbs2;
            if (i2 > 0) {
                if (iD == iD2) {
                    break;
                }
                iD += i3;
                i2 -= iAbs;
            }
            iC += i4;
        }
        return new b(iVar, iVar2, i);
    }

    public f c() throws NotFoundException {
        i iVar;
        com.google.zxing.common.b bVarG;
        i[] iVarArrC = this.f4275b.c();
        i iVar2 = iVarArrC[0];
        i iVar3 = iVarArrC[1];
        i iVar4 = iVarArrC[2];
        i iVar5 = iVarArrC[3];
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(h(iVar2, iVar3));
        arrayList.add(h(iVar2, iVar4));
        arrayList.add(h(iVar3, iVar5));
        arrayList.add(h(iVar4, iVar5));
        i iVar6 = null;
        Collections.sort(arrayList, new ResultPointsAndTransitionsComparator());
        b bVar = (b) arrayList.get(0);
        b bVar2 = (b) arrayList.get(1);
        HashMap map = new HashMap();
        e(map, bVar.a());
        e(map, bVar.b());
        e(map, bVar2.a());
        e(map, bVar2.b());
        i iVar7 = null;
        i iVar8 = null;
        for (Map.Entry entry : map.entrySet()) {
            i iVar9 = (i) entry.getKey();
            if (((Integer) entry.getValue()).intValue() == 2) {
                iVar7 = iVar9;
            } else if (iVar6 == null) {
                iVar6 = iVar9;
            } else {
                iVar8 = iVar9;
            }
        }
        if (iVar6 == null || iVar7 == null || iVar8 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        i[] iVarArr = {iVar6, iVar7, iVar8};
        i.e(iVarArr);
        i iVar10 = iVarArr[0];
        i iVar11 = iVarArr[1];
        i iVar12 = iVarArr[2];
        i iVar13 = !map.containsKey(iVar2) ? iVar2 : !map.containsKey(iVar3) ? iVar3 : !map.containsKey(iVar4) ? iVar4 : iVar5;
        int iC = h(iVar12, iVar13).c();
        int iC2 = h(iVar10, iVar13).c();
        if ((iC & 1) == 1) {
            iC++;
        }
        int i = iC + 2;
        if ((iC2 & 1) == 1) {
            iC2++;
        }
        int i2 = iC2 + 2;
        if (i * 4 >= i2 * 7 || i2 * 4 >= i * 7) {
            iVar = iVar12;
            i iVarB = b(iVar11, iVar10, iVar12, iVar13, i, i2);
            if (iVarB != null) {
                iVar13 = iVarB;
            }
            int iC3 = h(iVar, iVar13).c();
            int iC4 = h(iVar10, iVar13).c();
            if ((iC3 & 1) == 1) {
                iC3++;
            }
            int i3 = iC3;
            if ((iC4 & 1) == 1) {
                iC4++;
            }
            bVarG = g(this.f4274a, iVar, iVar11, iVar10, iVar13, i3, iC4);
        } else {
            i iVarA = a(iVar11, iVar10, iVar12, iVar13, Math.min(i2, i));
            if (iVarA != null) {
                iVar13 = iVarA;
            }
            int iMax = Math.max(h(iVar12, iVar13).c(), h(iVar10, iVar13).c()) + 1;
            if ((iMax & 1) == 1) {
                iMax++;
            }
            int i4 = iMax;
            bVarG = g(this.f4274a, iVar12, iVar11, iVar10, iVar13, i4, i4);
            iVar = iVar12;
        }
        return new f(bVarG, new i[]{iVar, iVar11, iVar10, iVar13});
    }
}
