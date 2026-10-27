package p;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class z11 extends ty implements y11 {
    public ty[] p0;
    public int q0;

    public final void N(int i, iw2 iw2Var, ArrayList arrayList) {
        for (int i2 = 0; i2 < this.q0; i2++) {
            ty tyVar = this.p0[i2];
            ArrayList arrayList2 = iw2Var.a;
            if (!arrayList2.contains(tyVar)) {
                arrayList2.add(tyVar);
            }
        }
        for (int i3 = 0; i3 < this.q0; i3++) {
            he1.q(this.p0[i3], i, arrayList, iw2Var);
        }
    }
}
