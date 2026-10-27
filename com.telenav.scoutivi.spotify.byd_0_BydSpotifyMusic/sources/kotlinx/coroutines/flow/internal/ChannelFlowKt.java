package kotlinx.coroutines.flow.internal;

import k5.d;
import k5.g;
import kotlin.coroutines.jvm.internal.h;
import kotlin.jvm.internal.v;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.g0;
import r5.p;

/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ChannelFlowKt {
    public static final <T> ChannelFlow<T> asChannelFlow(Flow<? extends T> flow) {
        ChannelFlow<T> channelFlow = (ChannelFlow) (!(flow instanceof ChannelFlow) ? null : flow);
        return channelFlow != null ? channelFlow : new ChannelFlowOperatorImpl(flow, null, 0, null, 14, null);
    }

    public static final <T, V> Object withContextUndispatched(g gVar, V v6, Object obj, p<? super V, ? super d<? super T>, ? extends Object> pVar, d<? super T> dVar) {
        Object objC = g0.c(gVar, obj);
        try {
            StackFrameContinuation stackFrameContinuation = new StackFrameContinuation(dVar, gVar);
            if (pVar == null) {
                throw new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
            }
            Object objInvoke = ((p) v.b(pVar, 2)).invoke(v6, stackFrameContinuation);
            g0.a(gVar, objC);
            if (objInvoke == l5.d.d()) {
                h.c(dVar);
            }
            return objInvoke;
        } catch (Throwable th) {
            g0.a(gVar, objC);
            throw th;
        }
    }

    public static /* synthetic */ Object withContextUndispatched$default(g gVar, Object obj, Object obj2, p pVar, d dVar, int i7, Object obj3) {
        if ((i7 & 4) != 0) {
            obj2 = g0.b(gVar);
        }
        return withContextUndispatched(gVar, obj, obj2, pVar, dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> FlowCollector<T> withUndispatchedContextCollector(FlowCollector<? super T> flowCollector, g gVar) {
        return ((flowCollector instanceof SendingCollector) || (flowCollector instanceof NopCollector)) ? flowCollector : new UndispatchedContextCollector(flowCollector, gVar);
    }
}
