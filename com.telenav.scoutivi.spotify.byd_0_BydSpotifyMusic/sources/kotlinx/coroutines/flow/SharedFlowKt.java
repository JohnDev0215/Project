package kotlinx.coroutines.flow;

import k5.g;
import kotlinx.coroutines.channels.k;
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl;
import kotlinx.coroutines.internal.c0;

/* JADX INFO: compiled from: SharedFlow.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SharedFlowKt {
    public static final c0 NO_VALUE = new c0("NO_VALUE");

    public static final <T> MutableSharedFlow<T> MutableSharedFlow(int i7, int i8, k kVar) {
        boolean z6 = true;
        if (!(i7 >= 0)) {
            throw new IllegalArgumentException(("replay cannot be negative, but was " + i7).toString());
        }
        if (!(i8 >= 0)) {
            throw new IllegalArgumentException(("extraBufferCapacity cannot be negative, but was " + i8).toString());
        }
        if (i7 <= 0 && i8 <= 0 && kVar != k.SUSPEND) {
            z6 = false;
        }
        if (z6) {
            int i9 = i8 + i7;
            if (i9 < 0) {
                i9 = Integer.MAX_VALUE;
            }
            return new SharedFlowImpl(i7, i9, kVar);
        }
        throw new IllegalArgumentException(("replay or extraBufferCapacity must be positive with non-default onBufferOverflow strategy " + kVar).toString());
    }

    public static /* synthetic */ MutableSharedFlow MutableSharedFlow$default(int i7, int i8, k kVar, int i9, Object obj) {
        if ((i9 & 1) != 0) {
            i7 = 0;
        }
        if ((i9 & 2) != 0) {
            i8 = 0;
        }
        if ((i9 & 4) != 0) {
            kVar = k.SUSPEND;
        }
        return MutableSharedFlow(i7, i8, kVar);
    }

    public static final <T> Flow<T> fuseSharedFlow(SharedFlow<? extends T> sharedFlow, g gVar, int i7, k kVar) {
        return ((i7 == 0 || i7 == -3) && kVar == k.SUSPEND) ? sharedFlow : new ChannelFlowOperatorImpl(sharedFlow, gVar, i7, kVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object getBufferAt(Object[] objArr, long j7) {
        return objArr[((int) j7) & (objArr.length - 1)];
    }

    public static /* synthetic */ void getNO_VALUE$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setBufferAt(Object[] objArr, long j7, Object obj) {
        objArr[((int) j7) & (objArr.length - 1)] = obj;
    }
}
