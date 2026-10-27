package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import kotlin.jvm.internal.k;
import r5.q;

/* JADX INFO: Add missing generic type declarations: [R] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$5<R> implements Flow<R> {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;
    final /* synthetic */ q $transform$inlined;

    public FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$5(Flow flow, q qVar) {
        this.$this_unsafeTransform$inlined = flow;
        this.$transform$inlined = qVar;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, d dVar) {
        Object objCollect = this.$this_unsafeTransform$inlined.collect(new FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$5$lambda$1(flowCollector, this), dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }

    public Object collect$$forInline(FlowCollector flowCollector, d dVar) {
        k.a(4);
        new kotlin.coroutines.jvm.internal.d(dVar) { // from class: kotlinx.coroutines.flow.FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$5.1
            int label;
            /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$5.this.collect(null, this);
            }
        };
        k.a(5);
        Flow flow = this.$this_unsafeTransform$inlined;
        FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$5$lambda$1 flowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$5$lambda$1 = new FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$5$lambda$1(flowCollector, this);
        k.a(0);
        flow.collect(flowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$5$lambda$1, dVar);
        k.a(2);
        k.a(1);
        return r.f6563a;
    }
}
