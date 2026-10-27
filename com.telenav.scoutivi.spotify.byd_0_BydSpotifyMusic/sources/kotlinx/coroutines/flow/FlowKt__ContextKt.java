package kotlinx.coroutines.flow;

import g5.r;
import k5.d;
import k5.g;
import k5.h;
import kotlin.jvm.internal.l;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.l1;

/* JADX INFO: compiled from: Context.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class FlowKt__ContextKt {
    public static final <T> Flow<T> buffer(Flow<? extends T> flow, int i7, k kVar) {
        int i8;
        k kVar2;
        boolean z6 = true;
        if (!(i7 >= 0 || i7 == -2 || i7 == -1)) {
            throw new IllegalArgumentException(("Buffer size should be non-negative, BUFFERED, or CONFLATED, but was " + i7).toString());
        }
        if (i7 == -1 && kVar != k.SUSPEND) {
            z6 = false;
        }
        if (!z6) {
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
        }
        if (i7 == -1) {
            kVar2 = k.DROP_OLDEST;
            i8 = 0;
        } else {
            i8 = i7;
            kVar2 = kVar;
        }
        return flow instanceof FusibleFlow ? FusibleFlow.DefaultImpls.fuse$default((FusibleFlow) flow, null, i8, kVar2, 1, null) : new ChannelFlowOperatorImpl(flow, null, i8, kVar2, 2, null);
    }

    public static /* synthetic */ Flow buffer$default(Flow flow, int i7, k kVar, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i7 = -2;
        }
        if ((i8 & 2) != 0) {
            kVar = k.SUSPEND;
        }
        return FlowKt.buffer(flow, i7, kVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Flow<T> cancellable(Flow<? extends T> flow) {
        return flow instanceof CancellableFlow ? flow : new CancellableFlowImpl(flow);
    }

    private static final void checkFlowContext$FlowKt__ContextKt(g gVar) {
        if (gVar.get(l1.f7703d) == null) {
            return;
        }
        throw new IllegalArgumentException(("Flow context cannot contain job in it. Had " + gVar).toString());
    }

    public static final <T> Flow<T> conflate(Flow<? extends T> flow) {
        return buffer$default(flow, -1, null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Flow<T> flowOn(Flow<? extends T> flow, g gVar) {
        checkFlowContext$FlowKt__ContextKt(gVar);
        return l.a(gVar, h.f7276e) ? flow : flow instanceof FusibleFlow ? FusibleFlow.DefaultImpls.fuse$default((FusibleFlow) flow, gVar, 0, null, 6, null) : new ChannelFlowOperatorImpl(flow, gVar, 0, null, 12, null);
    }

    public static final <T, R> Flow<R> flowWith(final Flow<? extends T> flow, final g gVar, final int i7, final r5.l<? super Flow<? extends T>, ? extends Flow<? extends R>> lVar) {
        checkFlowContext$FlowKt__ContextKt(gVar);
        return new Flow<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ContextKt$flowWith$$inlined$unsafeFlow$1
            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(final FlowCollector flowCollector, d dVar) {
                Object objCollect = FlowKt__ContextKt.buffer$default(FlowKt.flowOn((Flow) lVar.invoke(FlowKt__ContextKt.buffer$default(FlowKt.flowOn(flow, dVar.getContext().minusKey(l1.f7703d)), i7, null, 2, null)), gVar), i7, null, 2, null).collect(new FlowCollector<R>() { // from class: kotlinx.coroutines.flow.FlowKt__ContextKt$flowWith$$inlined$unsafeFlow$1$lambda$1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public Object emit(Object obj, d dVar2) {
                        Object objEmit = flowCollector.emit(obj, dVar2);
                        return objEmit == l5.d.d() ? objEmit : r.f6563a;
                    }
                }, dVar);
                return objCollect == l5.d.d() ? objCollect : r.f6563a;
            }
        };
    }

    public static /* synthetic */ Flow flowWith$default(Flow flow, g gVar, int i7, r5.l lVar, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            i7 = -2;
        }
        return FlowKt.flowWith(flow, gVar, i7, lVar);
    }

    public static /* synthetic */ Flow buffer$default(Flow flow, int i7, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i7 = -2;
        }
        return buffer(flow, i7);
    }

    public static final /* synthetic */ <T> Flow<T> buffer(Flow<? extends T> flow, int i7) {
        return buffer$default(flow, i7, null, 2, null);
    }
}
