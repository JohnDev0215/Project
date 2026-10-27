package l0;

import E0.AbstractC0156f;
import E0.F;
import java.util.Arrays;
import java.util.Comparator;
import n7.AbstractC1996k;

/* JADX INFO: renamed from: l0.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1729t implements Comparator {
    public static final C1729t l = new C1729t();

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        C1727r c1727r = (C1727r) obj;
        C1727r c1727r2 = (C1727r) obj2;
        int i7 = 0;
        if (!AbstractC1713d.s(c1727r) || !AbstractC1713d.s(c1727r2)) {
            if (AbstractC1713d.s(c1727r)) {
                return -1;
            }
            return AbstractC1713d.s(c1727r2) ? 1 : 0;
        }
        F fT = AbstractC0156f.t(c1727r);
        F fT2 = AbstractC0156f.t(c1727r2);
        if (A7.m.a(fT, fT2)) {
            return 0;
        }
        Object[] objArrCopyOf = new F[16];
        int i10 = 0;
        while (fT != null) {
            int i11 = i10 + 1;
            if (objArrCopyOf.length < i11) {
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, Math.max(i11, objArrCopyOf.length * 2));
                A7.m.e(objArrCopyOf, "copyOf(this, newSize)");
            }
            if (i10 != 0) {
                AbstractC1996k.D(0 + 1, 0, i10, objArrCopyOf, objArrCopyOf);
            }
            objArrCopyOf[0] = fT;
            i10++;
            fT = fT.G();
        }
        Object[] objArrCopyOf2 = new F[16];
        int i12 = 0;
        while (fT2 != null) {
            int i13 = i12 + 1;
            if (objArrCopyOf2.length < i13) {
                objArrCopyOf2 = Arrays.copyOf(objArrCopyOf2, Math.max(i13, objArrCopyOf2.length * 2));
                A7.m.e(objArrCopyOf2, "copyOf(this, newSize)");
            }
            if (i12 != 0) {
                AbstractC1996k.D(0 + 1, 0, i12, objArrCopyOf2, objArrCopyOf2);
            }
            objArrCopyOf2[0] = fT2;
            i12++;
            fT2 = fT2.G();
        }
        int iMin = Math.min(i10 - 1, i12 - 1);
        if (iMin >= 0) {
            while (A7.m.a(objArrCopyOf[i7], objArrCopyOf2[i7])) {
                if (i7 != iMin) {
                    i7++;
                }
            }
            return A7.m.g(((F) objArrCopyOf[i7]).H(), ((F) objArrCopyOf2[i7]).H());
        }
        throw new IllegalStateException("Could not find a common ancestor between the two FocusModifiers.");
    }
}
