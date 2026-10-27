package kotlinx.coroutines.flow.internal;

import g5.r;
import java.util.Iterator;
import k5.d;
import k5.g;
import k5.h;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.channels.c0;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.f;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.h0;

/* JADX INFO: compiled from: Merge.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ChannelLimitedFlowMerge<T> extends ChannelFlow<T> {
    private final Iterable<Flow<T>> flows;

    public /* synthetic */ ChannelLimitedFlowMerge(Iterable iterable, g gVar, int i7, k kVar, int i8, kotlin.jvm.internal.g gVar2) {
        this(iterable, (i8 & 2) != 0 ? h.f7276e : gVar, (i8 & 4) != 0 ? -2 : i7, (i8 & 8) != 0 ? k.SUSPEND : kVar);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object collectTo(a0<? super T> a0Var, d<? super r> dVar) {
        SendingCollector sendingCollector = new SendingCollector(a0Var);
        Iterator<Flow<T>> it = this.flows.iterator();
        while (it.hasNext()) {
            f.b(a0Var, null, null, new ChannelLimitedFlowMerge$collectTo$$inlined$forEach$lambda$1(it.next(), null, a0Var, sendingCollector), 3, null);
        }
        return r.f6563a;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow<T> create(g gVar, int i7, k kVar) {
        return new ChannelLimitedFlowMerge(this.flows, gVar, i7, kVar);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public c0<T> produceImpl(h0 h0Var) {
        return FlowCoroutineKt.flowProduce(h0Var, this.context, this.capacity, getCollectToFun$kotlinx_coroutines_core());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelLimitedFlowMerge(Iterable<? extends Flow<? extends T>> iterable, g gVar, int i7, k kVar) {
        super(gVar, i7, kVar);
        this.flows = iterable;
    }
}
