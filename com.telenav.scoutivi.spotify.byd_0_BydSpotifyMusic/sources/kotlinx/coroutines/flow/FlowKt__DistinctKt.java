package kotlinx.coroutines.flow;

import java.util.Objects;
import kotlin.jvm.internal.v;
import r5.l;
import r5.p;

/* JADX INFO: compiled from: Distinct.kt */
/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class FlowKt__DistinctKt {
    private static final l<Object, Object> defaultKeySelector = FlowKt__DistinctKt$defaultKeySelector$1.INSTANCE;
    private static final p<Object, Object, Boolean> defaultAreEquivalent = FlowKt__DistinctKt$defaultAreEquivalent$1.INSTANCE;

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> Flow<T> distinctUntilChanged(Flow<? extends T> flow) {
        return flow instanceof StateFlow ? flow : distinctUntilChangedBy$FlowKt__DistinctKt(flow, defaultKeySelector, defaultAreEquivalent);
    }

    public static final <T, K> Flow<T> distinctUntilChangedBy(Flow<? extends T> flow, l<? super T, ? extends K> lVar) {
        return distinctUntilChangedBy$FlowKt__DistinctKt(flow, lVar, defaultAreEquivalent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <T> Flow<T> distinctUntilChangedBy$FlowKt__DistinctKt(Flow<? extends T> flow, l<? super T, ? extends Object> lVar, p<Object, Object, Boolean> pVar) {
        if (flow instanceof DistinctFlowImpl) {
            DistinctFlowImpl distinctFlowImpl = (DistinctFlowImpl) flow;
            if (distinctFlowImpl.keySelector == lVar && distinctFlowImpl.areEquivalent == pVar) {
                return flow;
            }
        }
        return new DistinctFlowImpl(flow, lVar, pVar);
    }

    private static /* synthetic */ void getDefaultAreEquivalent$FlowKt__DistinctKt$annotations() {
    }

    private static /* synthetic */ void getDefaultKeySelector$FlowKt__DistinctKt$annotations() {
    }

    public static final <T> Flow<T> distinctUntilChanged(Flow<? extends T> flow, p<? super T, ? super T, Boolean> pVar) {
        l<Object, Object> lVar = defaultKeySelector;
        Objects.requireNonNull(pVar, "null cannot be cast to non-null type (kotlin.Any?, kotlin.Any?) -> kotlin.Boolean");
        return distinctUntilChangedBy$FlowKt__DistinctKt(flow, lVar, (p) v.b(pVar, 2));
    }
}
