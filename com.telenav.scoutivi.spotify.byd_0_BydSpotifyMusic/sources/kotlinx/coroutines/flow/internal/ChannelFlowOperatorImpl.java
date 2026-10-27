package kotlinx.coroutines.flow.internal;

import g5.r;
import k5.d;
import k5.g;
import k5.h;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ChannelFlowOperatorImpl<T> extends ChannelFlowOperator<T, T> {
    public /* synthetic */ ChannelFlowOperatorImpl(Flow flow, g gVar, int i7, k kVar, int i8, kotlin.jvm.internal.g gVar2) {
        this(flow, (i8 & 2) != 0 ? h.f7276e : gVar, (i8 & 4) != 0 ? -3 : i7, (i8 & 8) != 0 ? k.SUSPEND : kVar);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow<T> create(g gVar, int i7, k kVar) {
        return new ChannelFlowOperatorImpl(this.flow, gVar, i7, kVar);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public Flow<T> dropChannelOperators() {
        return (Flow<T>) this.flow;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // kotlinx.coroutines.flow.internal.ChannelFlowOperator
    protected Object flowCollect(FlowCollector<? super T> flowCollector, d<? super r> dVar) {
        Object objCollect = this.flow.collect((FlowCollector<? super S>) flowCollector, dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }

    public ChannelFlowOperatorImpl(Flow<? extends T> flow, g gVar, int i7, k kVar) {
        super(flow, gVar, i7, kVar);
    }
}
