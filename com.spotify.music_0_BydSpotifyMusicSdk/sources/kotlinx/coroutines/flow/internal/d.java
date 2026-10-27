package kotlinx.coroutines.flow.internal;

import androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$2;
import androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$3;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import p.c00;
import p.dv0;
import p.fv0;
import p.gv0;
import p.lq2;
import p.q30;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public static final Object a(c00 c00Var, WorkConstraintsTracker$track$$inlined$combine$1$2 workConstraintsTracker$track$$inlined$combine$1$2, WorkConstraintsTracker$track$$inlined$combine$1$3 workConstraintsTracker$track$$inlined$combine$1$3, fv0 fv0Var, dv0[] dv0VarArr) throws Throwable {
        CombineKt$combineInternal$2 combineKt$combineInternal$2 = new CombineKt$combineInternal$2(null, workConstraintsTracker$track$$inlined$combine$1$2, workConstraintsTracker$track$$inlined$combine$1$3, fv0Var, dv0VarArr);
        gv0 gv0Var = new gv0(c00Var, c00Var.getContext());
        Object objV = q30.v(gv0Var, gv0Var, combineKt$combineInternal$2);
        return objV == CoroutineSingletons.c ? objV : lq2.a;
    }
}
