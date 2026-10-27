package kotlinx.coroutines.flow;

import k5.d;
import kotlin.jvm.internal.k;
import kotlinx.coroutines.flow.FlowKt__EmittersKt;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Collect.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__EmittersKt$transform$1$invokeSuspend$$inlined$collect$1<T> implements FlowCollector<T> {
    final /* synthetic */ FlowCollector $this_flow$inlined;
    final /* synthetic */ FlowKt__EmittersKt.AnonymousClass1 this$0;

    public FlowKt__EmittersKt$transform$1$invokeSuspend$$inlined$collect$1(FlowKt__EmittersKt.AnonymousClass1 anonymousClass1, FlowCollector flowCollector) {
        this.this$0 = anonymousClass1;
        this.$this_flow$inlined = flowCollector;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object obj, d dVar) {
        return this.this$0.$transform.invoke(this.$this_flow$inlined, obj, dVar);
    }

    public Object emit$$forInline(Object obj, d dVar) {
        k.a(4);
        new kotlin.coroutines.jvm.internal.d(dVar) { // from class: kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1$invokeSuspend$$inlined$collect$1.1
            int label;
            /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj2) {
                this.result = obj2;
                this.label |= Integer.MIN_VALUE;
                return FlowKt__EmittersKt$transform$1$invokeSuspend$$inlined$collect$1.this.emit(null, this);
            }
        };
        k.a(5);
        return this.this$0.$transform.invoke(this.$this_flow$inlined, obj, dVar);
    }
}
