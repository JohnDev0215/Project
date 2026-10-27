package kotlinx.coroutines.flow;

import g5.r;
import k5.d;

/* JADX INFO: compiled from: Emitters.kt */
/* JADX INFO: loaded from: classes.dex */
final class ThrowingCollector implements FlowCollector<Object> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Throwable f7610e;

    public ThrowingCollector(Throwable th) {
        this.f7610e = th;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object obj, d<? super r> dVar) throws Throwable {
        throw this.f7610e;
    }
}
