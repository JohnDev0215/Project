package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import r5.l;
import r5.p;

/* JADX INFO: compiled from: Distinct.kt */
/* JADX INFO: loaded from: classes.dex */
final class DistinctFlowImpl<T> implements Flow<T> {
    public final p<Object, Object, Boolean> areEquivalent;
    public final l<T, Object> keySelector;
    private final Flow<T> upstream;

    /* JADX WARN: Multi-variable type inference failed */
    public DistinctFlowImpl(Flow<? extends T> flow, l<? super T, ? extends Object> lVar, p<Object, Object, Boolean> pVar) {
        this.upstream = flow;
        this.keySelector = lVar;
        this.areEquivalent = pVar;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, d<? super r> dVar) {
        kotlin.jvm.internal.r rVar = new kotlin.jvm.internal.r();
        rVar.f7508e = (T) NullSurrogateKt.NULL;
        Object objCollect = this.upstream.collect(new DistinctFlowImpl$collect$$inlined$collect$1(this, rVar, flowCollector), dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }
}
