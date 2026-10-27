package kotlinx.coroutines.flow;

import k5.d;
import kotlin.jvm.internal.r;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import r5.q;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: SafeCollector.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ q $operation$inlined;
    final /* synthetic */ Flow $this_runningReduce$inlined;

    public FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1(Flow flow, q qVar) {
        this.$this_runningReduce$inlined = flow;
        this.$operation$inlined = qVar;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector, d dVar) {
        r rVar = new r();
        rVar.f7508e = (T) NullSurrogateKt.NULL;
        Object objCollect = this.$this_runningReduce$inlined.collect(new FlowKt__TransformKt$runningReduce$$inlined$unsafeFlow$1$lambda$1(flowCollector, rVar, this), dVar);
        return objCollect == l5.d.d() ? objCollect : g5.r.f6563a;
    }
}
