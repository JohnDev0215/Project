package kotlinx.coroutines.flow;

import k5.d;
import kotlin.jvm.internal.k;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: Collect.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7$lambda$1<T> implements FlowCollector<T> {
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7 this$0;

    public FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7$lambda$1(FlowCollector flowCollector, FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7 flowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7) {
        this.$this_unsafeFlow$inlined = flowCollector;
        this.this$0 = flowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object obj, d dVar) {
        return this.this$0.$transform$inlined.invoke(this.$this_unsafeFlow$inlined, obj, dVar);
    }

    public Object emit$$forInline(Object obj, d dVar) {
        k.a(4);
        new kotlin.coroutines.jvm.internal.d(dVar) { // from class: kotlinx.coroutines.flow.FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7$lambda$1.1
            int label;
            /* synthetic */ Object result;

            @Override // kotlin.coroutines.jvm.internal.a
            public final Object invokeSuspend(Object obj2) {
                this.result = obj2;
                this.label |= Integer.MIN_VALUE;
                return FlowKt__TransformKt$unsafeTransform$$inlined$unsafeFlow$7$lambda$1.this.emit(null, this);
            }
        };
        k.a(5);
        return this.this$0.$transform$inlined.invoke(this.$this_unsafeFlow$inlined, obj, dVar);
    }
}
