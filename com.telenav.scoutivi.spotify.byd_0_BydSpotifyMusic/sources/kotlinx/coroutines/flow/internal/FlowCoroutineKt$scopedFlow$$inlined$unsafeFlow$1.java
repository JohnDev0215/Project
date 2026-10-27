package kotlinx.coroutines.flow.internal;

import g5.r;
import k5.d;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import r5.q;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowCoroutineKt$scopedFlow$$inlined$unsafeFlow$1<R> implements Flow<R> {
    final /* synthetic */ q $block$inlined;

    public FlowCoroutineKt$scopedFlow$$inlined$unsafeFlow$1(q qVar) {
        this.$block$inlined = qVar;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, d dVar) {
        Object objFlowScope = FlowCoroutineKt.flowScope(new FlowCoroutineKt$scopedFlow$$inlined$unsafeFlow$1$lambda$1(flowCollector, null, this), dVar);
        return objFlowScope == l5.d.d() ? objFlowScope : r.f6563a;
    }
}
