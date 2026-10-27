package c;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes2.dex */
public final class f extends AbstractList<d> implements RandomAccess {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final d[] f634a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int[] f635b;

    private f(d[] dVarArr, int[] iArr) {
        this.f634a = dVarArr;
        this.f635b = iArr;
    }

    private static void a(long j, a aVar, int i, List<d> list, int i2, int i3, List<Integer> list2) {
        int i4;
        int i5;
        int i6;
        a aVar2;
        int i7 = i2;
        if (i7 >= i3) {
            throw new AssertionError();
        }
        for (int i8 = i7; i8 < i3; i8++) {
            if (list.get(i8).j() < i) {
                throw new AssertionError();
            }
        }
        d dVar = list.get(i2);
        d dVar2 = list.get(i3 - 1);
        int iIntValue = -1;
        if (i == dVar.j()) {
            iIntValue = list2.get(i7).intValue();
            i7++;
            dVar = list.get(i7);
        }
        int i9 = i7;
        if (dVar.d(i) == dVar2.d(i)) {
            int i10 = 0;
            int iMin = Math.min(dVar.j(), dVar2.j());
            for (int i11 = i; i11 < iMin && dVar.d(i11) == dVar2.d(i11); i11++) {
                i10++;
            }
            long jC = 1 + j + ((long) c(aVar)) + 2 + ((long) i10);
            aVar.H(-i10);
            aVar.H(iIntValue);
            int i12 = i;
            while (true) {
                i4 = i + i10;
                if (i12 >= i4) {
                    break;
                }
                aVar.H(dVar.d(i12) & 255);
                i12++;
            }
            if (i9 + 1 == i3) {
                if (i4 != list.get(i9).j()) {
                    throw new AssertionError();
                }
                aVar.H(list2.get(i9).intValue());
                return;
            } else {
                a aVar3 = new a();
                aVar.H((int) ((((long) c(aVar3)) + jC) * (-1)));
                a(jC, aVar3, i4, list, i9, i3, list2);
                aVar.F(aVar3, aVar3.A());
                return;
            }
        }
        int i13 = 1;
        for (int i14 = i9 + 1; i14 < i3; i14++) {
            if (list.get(i14 - 1).d(i) != list.get(i14).d(i)) {
                i13++;
            }
        }
        long jC2 = j + ((long) c(aVar)) + 2 + ((long) (i13 * 2));
        aVar.H(i13);
        aVar.H(iIntValue);
        for (int i15 = i9; i15 < i3; i15++) {
            byte bD = list.get(i15).d(i);
            if (i15 == i9 || bD != list.get(i15 - 1).d(i)) {
                aVar.H(bD & 255);
            }
        }
        a aVar4 = new a();
        int i16 = i9;
        while (i16 < i3) {
            byte bD2 = list.get(i16).d(i);
            int i17 = i16 + 1;
            int i18 = i17;
            while (true) {
                if (i18 >= i3) {
                    i5 = i3;
                    break;
                } else {
                    if (bD2 != list.get(i18).d(i)) {
                        i5 = i18;
                        break;
                    }
                    i18++;
                }
            }
            if (i17 == i5 && i + 1 == list.get(i16).j()) {
                aVar.H(list2.get(i16).intValue());
                i6 = i5;
                aVar2 = aVar4;
            } else {
                aVar.H((int) ((((long) c(aVar4)) + jC2) * (-1)));
                i6 = i5;
                aVar2 = aVar4;
                a(jC2, aVar4, i + 1, list, i16, i5, list2);
            }
            aVar4 = aVar2;
            i16 = i6;
        }
        a aVar5 = aVar4;
        aVar.F(aVar5, aVar5.A());
    }

    private static int c(a aVar) {
        return (int) (aVar.A() / 4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00bc, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static c.f d(c.d... r10) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.f.d(c.d[]):c.f");
    }

    @Override // java.util.AbstractList, java.util.List
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public d get(int i) {
        return this.f634a[i];
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.f634a.length;
    }
}
