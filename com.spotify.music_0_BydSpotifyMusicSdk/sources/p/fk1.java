package p;

import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public final class fk1 {
    public final SparseArray a;
    public uc0 b;

    public fk1(int i) {
        this.a = new SparseArray(i);
    }

    public final void a(uc0 uc0Var, int i, int i2) {
        int iA = uc0Var.a(i);
        SparseArray sparseArray = this.a;
        fk1 fk1Var = sparseArray == null ? null : (fk1) sparseArray.get(iA);
        if (fk1Var == null) {
            fk1Var = new fk1(1);
            sparseArray.put(uc0Var.a(i), fk1Var);
        }
        if (i2 > i) {
            fk1Var.a(uc0Var, i + 1, i2);
        } else {
            fk1Var.b = uc0Var;
        }
    }
}
