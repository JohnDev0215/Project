package kotlinx.coroutines.flow;

import k5.g;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.c0;

/* JADX INFO: compiled from: StateFlow.kt */
/* JADX INFO: loaded from: classes.dex */
public final class StateFlowKt {
    private static final c0 NONE = new c0("NONE");
    private static final c0 PENDING = new c0("PENDING");

    public static final <T> MutableStateFlow<T> MutableStateFlow(T t6) {
        if (t6 == null) {
            t6 = (T) NullSurrogateKt.NULL;
        }
        return new StateFlowImpl(t6);
    }

    public static final <T> Flow<T> fuseStateFlow(StateFlow<? extends T> stateFlow, g gVar, int i7, k kVar) {
        return (((i7 < 0 || 1 < i7) && i7 != -2) || kVar != k.DROP_OLDEST) ? SharedFlowKt.fuseSharedFlow(stateFlow, gVar, i7, kVar) : stateFlow;
    }

    private static /* synthetic */ void getNONE$annotations() {
    }

    private static /* synthetic */ void getPENDING$annotations() {
    }

    public static final void increment(MutableStateFlow<Integer> mutableStateFlow, int i7) {
        int iIntValue;
        do {
            iIntValue = mutableStateFlow.getValue().intValue();
        } while (!mutableStateFlow.compareAndSet(Integer.valueOf(iIntValue), Integer.valueOf(iIntValue + i7)));
    }
}
