package kotlinx.coroutines.flow.internal;

import g5.l;
import g5.r;
import java.util.Arrays;
import k5.d;
import kotlin.jvm.internal.l;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

/* JADX INFO: compiled from: AbstractSharedFlow.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractSharedFlow<S extends AbstractSharedFlowSlot<?>> {
    private MutableStateFlow<Integer> _subscriptionCount;
    private int nCollectors;
    private int nextIndex;
    private S[] slots;

    protected static /* synthetic */ void getSlots$annotations() {
    }

    protected final S allocateSlot() {
        S s6;
        MutableStateFlow<Integer> mutableStateFlow;
        synchronized (this) {
            S[] sArr = this.slots;
            if (sArr == null) {
                sArr = (S[]) createSlotArray(2);
                this.slots = sArr;
            } else if (this.nCollectors >= sArr.length) {
                Object[] objArrCopyOf = Arrays.copyOf(sArr, sArr.length * 2);
                l.d(objArrCopyOf, "java.util.Arrays.copyOf(this, newSize)");
                this.slots = (S[]) ((AbstractSharedFlowSlot[]) objArrCopyOf);
                sArr = (S[]) ((AbstractSharedFlowSlot[]) objArrCopyOf);
            }
            int i7 = this.nextIndex;
            do {
                s6 = sArr[i7];
                if (s6 == null) {
                    s6 = (S) createSlot();
                    sArr[i7] = s6;
                }
                i7++;
                if (i7 >= sArr.length) {
                    i7 = 0;
                }
                if (s6 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
                }
            } while (!s6.allocateLocked(this));
            this.nextIndex = i7;
            this.nCollectors++;
            mutableStateFlow = this._subscriptionCount;
        }
        if (mutableStateFlow != null) {
            StateFlowKt.increment(mutableStateFlow, 1);
        }
        return s6;
    }

    protected abstract S createSlot();

    protected abstract S[] createSlotArray(int i7);

    protected final void forEachSlotLocked(r5.l<? super S, r> lVar) {
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        if (this.nCollectors == 0 || (abstractSharedFlowSlotArr = this.slots) == null) {
            return;
        }
        for (AbstractSharedFlowSlot abstractSharedFlowSlot : abstractSharedFlowSlotArr) {
            if (abstractSharedFlowSlot != null) {
                lVar.invoke(abstractSharedFlowSlot);
            }
        }
    }

    protected final void freeSlot(S s6) {
        MutableStateFlow<Integer> mutableStateFlow;
        int i7;
        d<r>[] dVarArrFreeLocked;
        synchronized (this) {
            int i8 = this.nCollectors - 1;
            this.nCollectors = i8;
            mutableStateFlow = this._subscriptionCount;
            if (i8 == 0) {
                this.nextIndex = 0;
            }
            if (s6 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
            }
            dVarArrFreeLocked = s6.freeLocked(this);
        }
        for (d<r> dVar : dVarArrFreeLocked) {
            if (dVar != null) {
                r rVar = r.f6563a;
                l.a aVar = g5.l.f6557e;
                dVar.resumeWith(g5.l.a(rVar));
            }
        }
        if (mutableStateFlow != null) {
            StateFlowKt.increment(mutableStateFlow, -1);
        }
    }

    protected final int getNCollectors() {
        return this.nCollectors;
    }

    protected final S[] getSlots() {
        return this.slots;
    }

    public final StateFlow<Integer> getSubscriptionCount() {
        MutableStateFlow<Integer> MutableStateFlow;
        synchronized (this) {
            MutableStateFlow = this._subscriptionCount;
            if (MutableStateFlow == null) {
                MutableStateFlow = StateFlowKt.MutableStateFlow(Integer.valueOf(this.nCollectors));
                this._subscriptionCount = MutableStateFlow;
            }
        }
        return MutableStateFlow;
    }
}
