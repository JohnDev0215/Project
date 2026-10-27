package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import p.bk0;
import p.c00;
import p.fv0;
import p.fz0;
import p.lq2;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public final class f implements fv0 {
    public final y10 c;
    public final Object e;
    public final fz0 k;

    public f(fv0 fv0Var, y10 y10Var) {
        this.c = y10Var;
        this.e = kotlinx.coroutines.internal.c.b(y10Var);
        this.k = new UndispatchedContextCollector$emitRef$1(fv0Var, null);
    }

    @Override // p.fv0
    public final Object b(Object obj, c00 c00Var) {
        Object objW = bk0.W(this.c, obj, this.e, this.k, c00Var);
        return objW == CoroutineSingletons.c ? objW : lq2.a;
    }
}
