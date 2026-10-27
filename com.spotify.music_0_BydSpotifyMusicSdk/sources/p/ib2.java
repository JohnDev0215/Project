package p;

import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* JADX INFO: loaded from: classes.dex */
public final class ib2 implements fv0 {
    public final hb2 c;

    public ib2(hb2 hb2Var) {
        this.c = hb2Var;
    }

    @Override // p.fv0
    public final Object b(Object obj, c00 c00Var) {
        Object objL = this.c.l(obj, c00Var);
        return objL == CoroutineSingletons.c ? objL : lq2.a;
    }
}
