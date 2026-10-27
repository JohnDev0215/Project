package kotlinx.coroutines.flow;

import g5.r;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import k5.d;
import k5.g;
import k5.h;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.channels.c0;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import kotlinx.coroutines.flow.internal.SendingCollector;
import kotlinx.coroutines.h0;
import kotlinx.coroutines.k0;

/* JADX INFO: compiled from: Channels.kt */
/* JADX INFO: loaded from: classes.dex */
final class ChannelAsFlow<T> extends ChannelFlow<T> {
    private static final AtomicIntegerFieldUpdater consumed$FU = AtomicIntegerFieldUpdater.newUpdater(ChannelAsFlow.class, "consumed");
    private final c0<T> channel;
    private final boolean consume;
    private volatile int consumed;

    public /* synthetic */ ChannelAsFlow(c0 c0Var, boolean z6, g gVar, int i7, k kVar, int i8, kotlin.jvm.internal.g gVar2) {
        this(c0Var, z6, (i8 & 4) != 0 ? h.f7276e : gVar, (i8 & 8) != 0 ? -3 : i7, (i8 & 16) != 0 ? k.SUSPEND : kVar);
    }

    private final void markConsumed() {
        if (this.consume) {
            if (!(consumed$FU.getAndSet(this, 1) == 0)) {
                throw new IllegalStateException("ReceiveChannel.consumeAsFlow can be collected just once".toString());
            }
        }
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected String additionalToStringProps() {
        return "channel=" + this.channel;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public kotlinx.coroutines.channels.g<T> broadcastImpl(h0 h0Var, k0 k0Var) {
        markConsumed();
        return super.broadcastImpl(h0Var, k0Var);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow, kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, d<? super r> dVar) throws Throwable {
        if (this.capacity == -3) {
            markConsumed();
            Object objEmitAllImpl$FlowKt__ChannelsKt = FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt(flowCollector, this.channel, this.consume, dVar);
            if (objEmitAllImpl$FlowKt__ChannelsKt == l5.d.d()) {
                return objEmitAllImpl$FlowKt__ChannelsKt;
            }
        } else {
            Object objCollect = super.collect(flowCollector, dVar);
            if (objCollect == l5.d.d()) {
                return objCollect;
            }
        }
        return r.f6563a;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object collectTo(a0<? super T> a0Var, d<? super r> dVar) throws Throwable {
        Object objEmitAllImpl$FlowKt__ChannelsKt = FlowKt__ChannelsKt.emitAllImpl$FlowKt__ChannelsKt(new SendingCollector(a0Var), this.channel, this.consume, dVar);
        return objEmitAllImpl$FlowKt__ChannelsKt == l5.d.d() ? objEmitAllImpl$FlowKt__ChannelsKt : r.f6563a;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected ChannelFlow<T> create(g gVar, int i7, k kVar) {
        return new ChannelAsFlow(this.channel, this.consume, gVar, i7, kVar);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public Flow<T> dropChannelOperators() {
        return new ChannelAsFlow(this.channel, this.consume, null, 0, null, 28, null);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public c0<T> produceImpl(h0 h0Var) {
        markConsumed();
        return this.capacity == -3 ? this.channel : super.produceImpl(h0Var);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelAsFlow(c0<? extends T> c0Var, boolean z6, g gVar, int i7, k kVar) {
        super(gVar, i7, kVar);
        this.channel = c0Var;
        this.consume = z6;
        this.consumed = 0;
    }
}
