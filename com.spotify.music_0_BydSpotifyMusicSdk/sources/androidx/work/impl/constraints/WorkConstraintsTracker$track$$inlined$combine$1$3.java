package androidx.work.impl.constraints;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import p.bz;
import p.c00;
import p.e60;
import p.fv0;
import p.hz0;
import p.lq2;
import p.v03;
import p.zy;

/* JADX INFO: loaded from: classes.dex */
@e60(c = "androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$3", f = "WorkConstraintsTracker.kt", l = {292}, m = "invokeSuspend")
public final class WorkConstraintsTracker$track$$inlined$combine$1$3 extends SuspendLambda implements hz0 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // p.hz0
    public final Object e(Object obj, Object obj2, Object obj3) {
        WorkConstraintsTracker$track$$inlined$combine$1$3 workConstraintsTracker$track$$inlined$combine$1$3 = new WorkConstraintsTracker$track$$inlined$combine$1$3(3, (c00) obj3);
        workConstraintsTracker$track$$inlined$combine$1$3.L$0 = (fv0) obj;
        workConstraintsTracker$track$$inlined$combine$1$3.L$1 = (Object[]) obj2;
        return workConstraintsTracker$track$$inlined$combine$1$3.i(lq2.a);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object i(Object obj) {
        bz bzVar;
        bz bzVar2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.label;
        if (i == 0) {
            kotlin.a.d(obj);
            fv0 fv0Var = (fv0) this.L$0;
            bz[] bzVarArr = (bz[]) ((Object[]) this.L$1);
            int length = bzVarArr.length;
            int i2 = 0;
            while (true) {
                bzVar = zy.a;
                if (i2 >= length) {
                    bzVar2 = null;
                    break;
                }
                bzVar2 = bzVarArr[i2];
                if (!v03.b(bzVar2, bzVar)) {
                    break;
                }
                i2++;
            }
            if (bzVar2 != null) {
                bzVar = bzVar2;
            }
            this.label = 1;
            if (fv0Var.b(bzVar, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kotlin.a.d(obj);
        }
        return lq2.a;
    }
}
