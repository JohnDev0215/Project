package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import kotlin.jvm.internal.o;
import r5.p;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ p $predicate$inlined;
    final /* synthetic */ Flow $this_dropWhile$inlined;

    public FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1(Flow flow, p pVar) {
        this.$this_dropWhile$inlined = flow;
        this.$predicate$inlined = pVar;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, d dVar) {
        o oVar = new o();
        oVar.f7505e = false;
        Object objCollect = this.$this_dropWhile$inlined.collect(new FlowKt__LimitKt$dropWhile$$inlined$unsafeFlow$1$lambda$1(flowCollector, oVar, this), dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }
}
