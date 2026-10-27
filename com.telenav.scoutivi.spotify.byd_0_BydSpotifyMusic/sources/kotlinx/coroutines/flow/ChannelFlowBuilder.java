package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import k5.g;
import k5.h;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import r5.p;

/* JADX INFO: compiled from: Builders.kt */
/* JADX INFO: loaded from: classes.dex */
class ChannelFlowBuilder<T> extends ChannelFlow<T> {
    private final p<a0<? super T>, d<? super r>, Object> block;

    public /* synthetic */ ChannelFlowBuilder(p pVar, g gVar, int i7, k kVar, int i8, kotlin.jvm.internal.g gVar2) {
        this(pVar, (i8 & 2) != 0 ? h.f7276e : gVar, (i8 & 4) != 0 ? -2 : i7, (i8 & 8) != 0 ? k.SUSPEND : kVar);
    }

    static /* synthetic */ Object collectTo$suspendImpl(ChannelFlowBuilder channelFlowBuilder, a0 a0Var, d dVar) {
        Object objInvoke = channelFlowBuilder.block.invoke(a0Var, dVar);
        return objInvoke == l5.d.d() ? objInvoke : r.f6563a;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object collectTo(a0<? super T> a0Var, d<? super r> dVar) {
        return collectTo$suspendImpl(this, a0Var, dVar);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow<T> create(g gVar, int i7, k kVar) {
        return new ChannelFlowBuilder(this.block, gVar, i7, kVar);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public String toString() {
        return "block[" + this.block + "] -> " + super.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelFlowBuilder(p<? super a0<? super T>, ? super d<? super r>, ? extends Object> pVar, g gVar, int i7, k kVar) {
        super(gVar, i7, kVar);
        this.block = pVar;
    }
}
