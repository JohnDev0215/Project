package kotlinx.coroutines.flow.internal;

import g5.m;
import g5.r;
import k5.d;
import k5.e;
import k5.g;
import kotlin.coroutines.jvm.internal.f;
import kotlin.coroutines.jvm.internal.l;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import r5.p;

/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class ChannelFlowOperator<S, T> extends ChannelFlow<T> {
    protected final Flow<S> flow;

    /* JADX INFO: renamed from: kotlinx.coroutines.flow.internal.ChannelFlowOperator$collectWithContextUndispatched$2, reason: invalid class name */
    /* JADX INFO: compiled from: ChannelFlow.kt */
    @f(c = "kotlinx.coroutines.flow.internal.ChannelFlowOperator$collectWithContextUndispatched$2", f = "ChannelFlow.kt", l = {164}, m = "invokeSuspend")
    static final class AnonymousClass2 extends l implements p<FlowCollector<? super T>, d<? super r>, Object> {
        Object L$0;
        int label;
        private FlowCollector p$0;

        AnonymousClass2(d dVar) {
            super(2, dVar);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final d<r> create(Object obj, d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
            anonymousClass2.p$0 = (FlowCollector) obj;
            return anonymousClass2;
        }

        @Override // r5.p
        public final Object invoke(Object obj, d<? super r> dVar) {
            return ((AnonymousClass2) create(obj, dVar)).invokeSuspend(r.f6563a);
        }

        @Override // kotlin.coroutines.jvm.internal.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objD = l5.d.d();
            int i7 = this.label;
            if (i7 == 0) {
                m.b(obj);
                FlowCollector<? super T> flowCollector = this.p$0;
                ChannelFlowOperator channelFlowOperator = ChannelFlowOperator.this;
                this.L$0 = flowCollector;
                this.label = 1;
                if (channelFlowOperator.flowCollect(flowCollector, this) == objD) {
                    return objD;
                }
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                m.b(obj);
            }
            return r.f6563a;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ChannelFlowOperator(Flow<? extends S> flow, g gVar, int i7, k kVar) {
        super(gVar, i7, kVar);
        this.flow = flow;
    }

    static /* synthetic */ Object collect$suspendImpl(ChannelFlowOperator channelFlowOperator, FlowCollector flowCollector, d dVar) {
        if (channelFlowOperator.capacity == -3) {
            g context = dVar.getContext();
            g gVarPlus = context.plus(channelFlowOperator.context);
            if (kotlin.jvm.internal.l.a(gVarPlus, context)) {
                Object objFlowCollect = channelFlowOperator.flowCollect(flowCollector, dVar);
                return objFlowCollect == l5.d.d() ? objFlowCollect : r.f6563a;
            }
            e.b bVar = e.f7273b;
            if (kotlin.jvm.internal.l.a((e) gVarPlus.get(bVar), (e) context.get(bVar))) {
                Object objCollectWithContextUndispatched = channelFlowOperator.collectWithContextUndispatched(flowCollector, gVarPlus, dVar);
                return objCollectWithContextUndispatched == l5.d.d() ? objCollectWithContextUndispatched : r.f6563a;
            }
        }
        Object objCollect = super.collect(flowCollector, dVar);
        return objCollect == l5.d.d() ? objCollect : r.f6563a;
    }

    static /* synthetic */ Object collectTo$suspendImpl(ChannelFlowOperator channelFlowOperator, a0 a0Var, d dVar) {
        Object objFlowCollect = channelFlowOperator.flowCollect(new SendingCollector(a0Var), dVar);
        return objFlowCollect == l5.d.d() ? objFlowCollect : r.f6563a;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow, kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super T> flowCollector, d<? super r> dVar) {
        return collect$suspendImpl((ChannelFlowOperator) this, (FlowCollector) flowCollector, (d) dVar);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    protected Object collectTo(a0<? super T> a0Var, d<? super r> dVar) {
        return collectTo$suspendImpl(this, a0Var, dVar);
    }

    final /* synthetic */ Object collectWithContextUndispatched(FlowCollector<? super T> flowCollector, g gVar, d<? super r> dVar) {
        Object objWithContextUndispatched$default = ChannelFlowKt.withContextUndispatched$default(gVar, ChannelFlowKt.withUndispatchedContextCollector(flowCollector, dVar.getContext()), null, new AnonymousClass2(null), dVar, 4, null);
        return objWithContextUndispatched$default == l5.d.d() ? objWithContextUndispatched$default : r.f6563a;
    }

    protected abstract Object flowCollect(FlowCollector<? super T> flowCollector, d<? super r> dVar);

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public String toString() {
        return this.flow + " -> " + super.toString();
    }
}
