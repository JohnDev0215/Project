package kotlinx.coroutines.flow.internal;

import g5.r;
import k5.d;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import kotlinx.coroutines.flow.FlowCollector;
import r5.q;

/* JADX INFO: compiled from: SafeCollector.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class SafeCollectorKt$emitFun$1 extends j implements q<FlowCollector<? super Object>, Object, d<? super r>, Object> {
    SafeCollectorKt$emitFun$1() {
        super(3, FlowCollector.class, "emit", "emit(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
    }

    @Override // r5.q
    public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super Object> flowCollector, Object obj, d<? super r> dVar) {
        return invoke2((FlowCollector<Object>) flowCollector, obj, dVar);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(FlowCollector<Object> flowCollector, Object obj, d<? super r> dVar) {
        k.a(0);
        Object objEmit = flowCollector.emit(obj, dVar);
        k.a(2);
        k.a(1);
        return objEmit;
    }
}
