package kotlinx.coroutines.flow;

import g5.l;
import g5.r;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import k5.d;
import kotlin.coroutines.jvm.internal.h;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.j;
import kotlinx.coroutines.k;
import l5.c;

/* JADX INFO: compiled from: StateFlow.kt */
/* JADX INFO: loaded from: classes.dex */
final class StateFlowSlot extends AbstractSharedFlowSlot<StateFlowImpl<?>> {
    static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(StateFlowSlot.class, Object.class, "_state");
    volatile Object _state = null;

    public final Object awaitPending(d<? super r> dVar) throws Throwable {
        k kVar = new k(c.c(dVar), 1);
        kVar.A();
        if (!j.a(_state$FU, this, StateFlowKt.NONE, kVar)) {
            r rVar = r.f6563a;
            l.a aVar = l.f6557e;
            kVar.resumeWith(l.a(rVar));
        }
        Object objY = kVar.y();
        if (objY == l5.d.d()) {
            h.c(dVar);
        }
        return objY;
    }

    public final void makePending() {
        while (true) {
            Object obj = this._state;
            if (obj == null || obj == StateFlowKt.PENDING) {
                return;
            }
            if (obj == StateFlowKt.NONE) {
                if (j.a(_state$FU, this, obj, StateFlowKt.PENDING)) {
                    return;
                }
            } else if (j.a(_state$FU, this, obj, StateFlowKt.NONE)) {
                r rVar = r.f6563a;
                l.a aVar = l.f6557e;
                ((k) obj).resumeWith(l.a(rVar));
                return;
            }
        }
    }

    public final boolean takePending() {
        Object andSet = _state$FU.getAndSet(this, StateFlowKt.NONE);
        kotlin.jvm.internal.l.c(andSet);
        return andSet == StateFlowKt.PENDING;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public boolean allocateLocked(StateFlowImpl<?> stateFlowImpl) {
        if (this._state != null) {
            return false;
        }
        this._state = StateFlowKt.NONE;
        return true;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public d<r>[] freeLocked(StateFlowImpl<?> stateFlowImpl) {
        this._state = null;
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }
}
