package kotlinx.coroutines.flow;

import g5.r;
import java.util.List;
import k5.d;
import k5.g;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.internal.FusibleFlow;

/* JADX INFO: compiled from: Share.kt */
/* JADX INFO: loaded from: classes.dex */
final class ReadonlySharedFlow<T> implements SharedFlow<T>, CancellableFlow<T>, FusibleFlow<T> {
    private final /* synthetic */ SharedFlow<? extends T> $$delegate_0;

    public ReadonlySharedFlow(SharedFlow<? extends T> sharedFlow) {
        this.$$delegate_0 = sharedFlow;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, d<? super r> dVar) {
        return this.$$delegate_0.collect(flowCollector, dVar);
    }

    @Override // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow<T> fuse(g gVar, int i7, k kVar) {
        return SharedFlowKt.fuseSharedFlow(this, gVar, i7, kVar);
    }

    @Override // kotlinx.coroutines.flow.SharedFlow
    public List<T> getReplayCache() {
        return this.$$delegate_0.getReplayCache();
    }
}
