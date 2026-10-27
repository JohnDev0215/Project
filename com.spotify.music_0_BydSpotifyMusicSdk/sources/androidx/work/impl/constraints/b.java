package androidx.work.impl.constraints;

import p.ky2;
import p.q30;
import p.r61;
import p.rp1;
import p.v03;
import p.vc1;
import p.vr2;
import p.wk0;
import p.zu;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {
    public static final /* synthetic */ int a = 0;

    static {
        v03.i(vc1.d("WorkConstraintsTracker"), "tagWithPrefix(\"WorkConstraintsTracker\")");
    }

    public static final r61 a(a aVar, ky2 ky2Var, wk0 wk0Var, rp1 rp1Var) {
        v03.j(aVar, "<this>");
        v03.j(wk0Var, "dispatcher");
        v03.j(rp1Var, "listener");
        r61 r61VarA = zu.a();
        vr2.l(q30.b(kotlin.coroutines.a.a(wk0Var, r61VarA)), null, null, new WorkConstraintsTrackerKt$listen$1(aVar, ky2Var, rp1Var, null), 3);
        return r61VarA;
    }
}
