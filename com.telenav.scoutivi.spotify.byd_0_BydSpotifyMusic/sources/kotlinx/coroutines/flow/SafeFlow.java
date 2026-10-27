package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import r5.p;

/* JADX INFO: compiled from: Builders.kt */
/* JADX INFO: loaded from: classes.dex */
final class SafeFlow<T> extends AbstractFlow<T> {
    private final p<FlowCollector<? super T>, d<? super r>, Object> block;

    /* JADX WARN: Multi-variable type inference failed */
    public SafeFlow(p<? super FlowCollector<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        this.block = pVar;
    }

    @Override // kotlinx.coroutines.flow.AbstractFlow
    public Object collectSafely(FlowCollector<? super T> flowCollector, d<? super r> dVar) {
        Object objInvoke = this.block.invoke(flowCollector, dVar);
        return objInvoke == l5.d.d() ? objInvoke : r.f6563a;
    }
}
