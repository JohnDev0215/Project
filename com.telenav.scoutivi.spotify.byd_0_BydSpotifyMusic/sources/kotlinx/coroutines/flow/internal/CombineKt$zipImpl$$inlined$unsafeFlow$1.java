package kotlinx.coroutines.flow.internal;

import g5.r;
import k5.d;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.i0;
import r5.q;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class CombineKt$zipImpl$$inlined$unsafeFlow$1<R> implements Flow<R> {
    final /* synthetic */ Flow $flow$inlined;
    final /* synthetic */ Flow $flow2$inlined;
    final /* synthetic */ q $transform$inlined;

    public CombineKt$zipImpl$$inlined$unsafeFlow$1(Flow flow, Flow flow2, q qVar) {
        this.$flow2$inlined = flow;
        this.$flow$inlined = flow2;
        this.$transform$inlined = qVar;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, d dVar) {
        Object objA = i0.a(new CombineKt$zipImpl$$inlined$unsafeFlow$1$lambda$1(flowCollector, null, this), dVar);
        return objA == l5.d.d() ? objA : r.f6563a;
    }
}
