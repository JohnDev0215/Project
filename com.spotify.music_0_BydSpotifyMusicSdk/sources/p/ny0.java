package p;

import androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$3;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import p.bz;

/* JADX INFO: loaded from: classes.dex */
public final class ny0 implements dv0 {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ ny0(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$2] */
    @Override // p.dv0
    public final Object c(fv0 fv0Var, c00 c00Var) throws Throwable {
        lq2 lq2Var = lq2.a;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.c;
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                Object objC = ((dv0) obj).c(new com.spotify.automotive.playback.a(fv0Var), c00Var);
                return objC == coroutineSingletons ? objC : lq2Var;
            case 1:
                Object objC2 = ((dv0) obj).c(new com.spotify.automotive.playerapi.a(fv0Var), c00Var);
                return objC2 == coroutineSingletons ? objC2 : lq2Var;
            case 2:
                Object objC3 = ((dv0) obj).c(new com.spotify.automotive.playerapi.b(fv0Var), c00Var);
                return objC3 == coroutineSingletons ? objC3 : lq2Var;
            default:
                final dv0[] dv0VarArr = (dv0[]) obj;
                Object objA = kotlinx.coroutines.flow.internal.d.a(c00Var, new py0() { // from class: androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // p.py0
                    public final Object invoke() {
                        return new bz[dv0VarArr.length];
                    }
                }, new WorkConstraintsTracker$track$$inlined$combine$1$3(3, null), fv0Var, dv0VarArr);
                return objA == coroutineSingletons ? objA : lq2Var;
        }
    }
}
