package kotlinx.coroutines.flow.internal;

import g5.r;
import k5.d;

/* JADX INFO: compiled from: AbstractSharedFlow.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractSharedFlowSlot<F> {
    public abstract boolean allocateLocked(F f7);

    public abstract d<r>[] freeLocked(F f7);
}
