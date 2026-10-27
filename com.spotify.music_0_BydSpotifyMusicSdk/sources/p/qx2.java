package p;

import androidx.work.impl.WorkDatabase;

/* JADX INFO: loaded from: classes.dex */
public final class qx2 implements kw0 {
    public final yx2 a;
    public final gw0 b;
    public final my2 c;

    static {
        vc1.d("WMFgUpdater");
    }

    public qx2(WorkDatabase workDatabase, gw0 gw0Var, yx2 yx2Var) {
        this.b = gw0Var;
        this.a = yx2Var;
        this.c = workDatabase.v();
    }
}
