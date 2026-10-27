package kotlinx.coroutines.flow;

import g5.r;
import java.util.List;
import k5.d;
import r5.p;

/* JADX INFO: compiled from: Share.kt */
/* JADX INFO: loaded from: classes.dex */
final class SubscribedSharedFlow<T> implements SharedFlow<T> {
    private final p<FlowCollector<? super T>, d<? super r>, Object> action;
    private final SharedFlow<T> sharedFlow;

    /* JADX WARN: Multi-variable type inference failed */
    public SubscribedSharedFlow(SharedFlow<? extends T> sharedFlow, p<? super FlowCollector<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        this.sharedFlow = sharedFlow;
        this.action = pVar;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, d<? super r> dVar) {
        Object objCollect = this.sharedFlow.collect(new SubscribedFlowCollector(flowCollector, this.action), dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }

    @Override // kotlinx.coroutines.flow.SharedFlow
    public List<T> getReplayCache() {
        return this.sharedFlow.getReplayCache();
    }
}
