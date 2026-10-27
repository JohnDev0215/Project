package kotlinx.coroutines.flow.internal;

import g5.r;
import k5.d;
import k5.g;
import k5.h;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.channels.c0;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.h0;
import kotlinx.coroutines.l1;

/* JADX INFO: compiled from: Merge.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ChannelFlowMerge<T> extends ChannelFlow<T> {
    private final int concurrency;
    private final Flow<Flow<T>> flow;

    public /* synthetic */ ChannelFlowMerge(Flow flow, int i7, g gVar, int i8, k kVar, int i9, kotlin.jvm.internal.g gVar2) {
        this(flow, i7, (i9 & 4) != 0 ? h.f7276e : gVar, (i9 & 8) != 0 ? -2 : i8, (i9 & 16) != 0 ? k.SUSPEND : kVar);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected String additionalToStringProps() {
        return "concurrency=" + this.concurrency;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object collectTo(a0<? super T> a0Var, d<? super r> dVar) {
        Object objCollect = this.flow.collect(new ChannelFlowMerge$collectTo$$inlined$collect$1((l1) dVar.getContext().get(l1.f7703d), kotlinx.coroutines.sync.d.b(this.concurrency, 0, 2, null), a0Var, new SendingCollector(a0Var)), dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow<T> create(g gVar, int i7, k kVar) {
        return new ChannelFlowMerge(this.flow, this.concurrency, gVar, i7, kVar);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public c0<T> produceImpl(h0 h0Var) {
        return FlowCoroutineKt.flowProduce(h0Var, this.context, this.capacity, getCollectToFun$kotlinx_coroutines_core());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelFlowMerge(Flow<? extends Flow<? extends T>> flow, int i7, g gVar, int i8, k kVar) {
        super(gVar, i8, kVar);
        this.flow = flow;
        this.concurrency = i7;
    }
}
