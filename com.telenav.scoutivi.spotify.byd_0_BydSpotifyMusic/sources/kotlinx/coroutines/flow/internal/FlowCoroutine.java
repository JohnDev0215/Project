package kotlinx.coroutines.flow.internal;

import k5.d;
import k5.g;
import kotlinx.coroutines.internal.y;

/* JADX INFO: compiled from: FlowCoroutine.kt */
/* JADX INFO: loaded from: classes.dex */
final class FlowCoroutine<T> extends y<T> {
    public FlowCoroutine(g gVar, d<? super T> dVar) {
        super(gVar, dVar);
    }

    @Override // kotlinx.coroutines.s1
    public boolean childCancelled(Throwable th) {
        if (th instanceof ChildCancelledException) {
            return true;
        }
        return cancelImpl$kotlinx_coroutines_core(th);
    }
}
