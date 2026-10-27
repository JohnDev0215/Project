package defpackage;

import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: ApplicationViewportInsetSupplier.java */
/* JADX INFO: loaded from: classes3.dex */
public final class x7 extends aq1<Integer> implements w80 {
    public final HashSet h = new HashSet();
    public final HashSet i = new HashSet();
    public final ur3 j = new ur3(4, this);

    public x7() {
        super.r(0);
    }

    @Override // defpackage.w80
    public final void destroy() {
        ur3 ur3Var;
        HashSet hashSet = this.h;
        Iterator it = hashSet.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            ur3Var = this.j;
            if (!zHasNext) {
                break;
            } else {
                ((zp1) it.next()).d(ur3Var);
            }
        }
        HashSet hashSet2 = this.i;
        Iterator it2 = hashSet2.iterator();
        while (it2.hasNext()) {
            ((zp1) it2.next()).d(ur3Var);
        }
        hashSet.clear();
        hashSet2.clear();
    }

    @Override // defpackage.aq1
    public final void r(Integer num) {
        throw new IllegalStateException("#set(...) should not be called directly on ApplicationViewportInsetSupplier.");
    }

    public final void s() {
        int iIntValue = 0;
        for (zp1 zp1Var : this.i) {
            iIntValue += zp1Var.get() == null ? 0 : ((Integer) zp1Var.get()).intValue();
        }
        int iMax = 0;
        for (zp1 zp1Var2 : this.h) {
            iMax = Math.max(iMax, zp1Var2.get() == null ? 0 : ((Integer) zp1Var2.get()).intValue());
        }
        super.r(Integer.valueOf(Math.max(iIntValue, iMax)));
    }
}
