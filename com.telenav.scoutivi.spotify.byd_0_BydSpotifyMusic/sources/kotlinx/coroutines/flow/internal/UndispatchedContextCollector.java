package kotlinx.coroutines.flow.internal;

import g5.r;
import k5.d;
import k5.g;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.g0;
import r5.p;

/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes.dex */
final class UndispatchedContextCollector<T> implements FlowCollector<T> {
    private final Object countOrElement;
    private final g emitContext;
    private final p<T, d<? super r>, Object> emitRef;

    public UndispatchedContextCollector(FlowCollector<? super T> flowCollector, g gVar) {
        this.emitContext = gVar;
        this.countOrElement = g0.b(gVar);
        this.emitRef = new UndispatchedContextCollector$emitRef$1(flowCollector, null);
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(T t6, d<? super r> dVar) {
        Object objWithContextUndispatched = ChannelFlowKt.withContextUndispatched(this.emitContext, t6, this.countOrElement, this.emitRef, dVar);
        return objWithContextUndispatched == l5.d.d() ? objWithContextUndispatched : r.f6563a;
    }
}
