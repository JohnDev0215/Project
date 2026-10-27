package kotlinx.coroutines.flow.internal;

import k5.g;
import kotlinx.coroutines.channels.l;
import kotlinx.coroutines.channels.z;

/* JADX INFO: compiled from: FlowCoroutine.kt */
/* JADX INFO: loaded from: classes.dex */
final class FlowProduceCoroutine<T> extends z<T> {
    public FlowProduceCoroutine(g gVar, l<T> lVar) {
        super(gVar, lVar);
    }

    @Override // kotlinx.coroutines.s1
    public boolean childCancelled(Throwable th) {
        if (th instanceof ChildCancelledException) {
            return true;
        }
        return cancelImpl$kotlinx_coroutines_core(th);
    }
}
