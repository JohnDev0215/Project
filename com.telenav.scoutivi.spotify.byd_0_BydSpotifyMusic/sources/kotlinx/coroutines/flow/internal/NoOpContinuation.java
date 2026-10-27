package kotlinx.coroutines.flow.internal;

import k5.d;
import k5.g;
import k5.h;

/* JADX INFO: compiled from: SafeCollector.kt */
/* JADX INFO: loaded from: classes.dex */
final class NoOpContinuation implements d<Object> {
    public static final NoOpContinuation INSTANCE = new NoOpContinuation();
    private static final g context = h.f7276e;

    private NoOpContinuation() {
    }

    @Override // k5.d
    public g getContext() {
        return context;
    }

    @Override // k5.d
    public void resumeWith(Object obj) {
    }
}
