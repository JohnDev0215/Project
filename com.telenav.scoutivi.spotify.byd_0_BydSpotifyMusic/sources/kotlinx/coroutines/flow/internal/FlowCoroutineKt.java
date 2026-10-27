package kotlinx.coroutines.flow.internal;

import b6.b;
import g5.r;
import k5.d;
import k5.g;
import kotlin.coroutines.jvm.internal.h;
import kotlinx.coroutines.channels.a0;
import kotlinx.coroutines.channels.c0;
import kotlinx.coroutines.channels.o;
import kotlinx.coroutines.d0;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.h0;
import kotlinx.coroutines.k0;
import r5.p;
import r5.q;

/* JADX INFO: compiled from: FlowCoroutine.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FlowCoroutineKt {
    public static final <T> c0<T> flowProduce(h0 h0Var, g gVar, int i7, p<? super a0<? super T>, ? super d<? super r>, ? extends Object> pVar) {
        FlowProduceCoroutine flowProduceCoroutine = new FlowProduceCoroutine(d0.c(h0Var, gVar), o.b(i7, null, null, 6, null));
        flowProduceCoroutine.start(k0.ATOMIC, flowProduceCoroutine, pVar);
        return flowProduceCoroutine;
    }

    public static /* synthetic */ c0 flowProduce$default(h0 h0Var, g gVar, int i7, p pVar, int i8, Object obj) {
        if ((i8 & 2) != 0) {
            i7 = 0;
        }
        return flowProduce(h0Var, gVar, i7, pVar);
    }

    public static final <R> Object flowScope(p<? super h0, ? super d<? super R>, ? extends Object> pVar, d<? super R> dVar) {
        FlowCoroutine flowCoroutine = new FlowCoroutine(dVar.getContext(), dVar);
        Object objE = b.e(flowCoroutine, flowCoroutine, pVar);
        if (objE == l5.d.d()) {
            h.c(dVar);
        }
        return objE;
    }

    public static final <R> Flow<R> scopedFlow(q<? super h0, ? super FlowCollector<? super R>, ? super d<? super r>, ? extends Object> qVar) {
        return new FlowCoroutineKt$scopedFlow$$inlined$unsafeFlow$1(qVar);
    }
}
