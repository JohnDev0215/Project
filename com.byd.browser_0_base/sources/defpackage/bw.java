package defpackage;

import java.util.ArrayList;

/* JADX INFO: compiled from: ComposedBrowserControlsVisibilityDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bw extends lj {
    public final boolean j = true;
    public final ArrayList h = new ArrayList();
    public final se3 i = new se3(14, this);

    public bw(lj... ljVarArr) {
        for (lj ljVar : ljVarArr) {
            this.h.add(ljVar);
            ljVar.p(this.i);
        }
        super.r(Integer.valueOf(u()));
    }

    @Override // defpackage.lj, defpackage.aq1
    /* JADX INFO: renamed from: s */
    public final void r(Integer num) {
        if (this.j) {
            throw new IllegalStateException("Calling set on the composed delegate is not allowed.");
        }
        super.r(num);
    }

    public final int u() {
        int i = 0;
        boolean z = false;
        while (true) {
            ArrayList arrayList = this.h;
            boolean z2 = true;
            if (i >= arrayList.size()) {
                return z ? 1 : 3;
            }
            int iIntValue = ((Integer) ((lj) arrayList.get(i)).f).intValue();
            if (iIntValue == 2) {
                return 2;
            }
            if (iIntValue != 1) {
                z2 = false;
            }
            z |= z2;
            i++;
        }
    }
}
