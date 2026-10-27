package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import kotlinx.coroutines.p1;

/* JADX INFO: compiled from: Context.kt */
/* JADX INFO: loaded from: classes.dex */
final class CancellableFlowImpl<T> implements CancellableFlow<T> {
    private final Flow<T> flow;

    /* JADX WARN: Multi-variable type inference failed */
    public CancellableFlowImpl(Flow<? extends T> flow) {
        this.flow = flow;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(final FlowCollector<? super T> flowCollector, d<? super r> dVar) {
        Object objCollect = this.flow.collect((FlowCollector<? super T>) new FlowCollector<T>() { // from class: kotlinx.coroutines.flow.CancellableFlowImpl$collect$$inlined$collect$1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public Object emit(Object obj, d dVar2) {
                p1.e(dVar2.getContext());
                Object objEmit = flowCollector.emit(obj, dVar2);
                return objEmit == l5.d.d() ? objEmit : r.f6563a;
            }
        }, dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }
}
