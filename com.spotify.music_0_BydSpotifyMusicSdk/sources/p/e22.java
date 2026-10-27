package p;

import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public final class e22 {
    public SparseArray a;
    public int b;

    public final d22 a(int i) {
        SparseArray sparseArray = this.a;
        d22 d22Var = (d22) sparseArray.get(i);
        if (d22Var != null) {
            return d22Var;
        }
        d22 d22Var2 = new d22();
        sparseArray.put(i, d22Var2);
        return d22Var2;
    }
}
