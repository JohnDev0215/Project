package kotlinx.coroutines.flow;

import g5.r;
import java.util.List;
import k5.d;
import k5.g;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.internal.FusibleFlow;

/* JADX INFO: compiled from: Share.kt */
/* JADX INFO: loaded from: classes.dex */
final class ReadonlyStateFlow<T> implements StateFlow<T>, CancellableFlow<T>, FusibleFlow<T> {
    private final /* synthetic */ StateFlow<? extends T> $$delegate_0;

    public ReadonlyStateFlow(StateFlow<? extends T> stateFlow) {
        this.$$delegate_0 = stateFlow;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, d<? super r> dVar) {
        return this.$$delegate_0.collect(flowCollector, dVar);
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow<T> fuse(g gVar, int i7, k kVar) {
        return StateFlowKt.fuseStateFlow(this, gVar, i7, kVar);
    }

    @Override // kotlinx.coroutines.flow.SharedFlow
    public List<T> getReplayCache() {
        return this.$$delegate_0.getReplayCache();
    }

    @Override // kotlinx.coroutines.flow.StateFlow
    public T getValue() {
        return this.$$delegate_0.getValue();
    }
}
