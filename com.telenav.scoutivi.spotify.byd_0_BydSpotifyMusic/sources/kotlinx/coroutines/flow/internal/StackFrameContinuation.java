package kotlinx.coroutines.flow.internal;

import k5.d;
import k5.g;
import kotlin.coroutines.jvm.internal.e;

/* JADX INFO: compiled from: ChannelFlow.kt */
/* JADX INFO: loaded from: classes.dex */
final class StackFrameContinuation<T> implements d<T>, e {
    private final g context;
    private final d<T> uCont;

    /* JADX WARN: Multi-variable type inference failed */
    public StackFrameContinuation(d<? super T> dVar, g gVar) {
        this.uCont = dVar;
        this.context = gVar;
    }

    public e getCallerFrame() {
        d<T> dVar = this.uCont;
        if (!(dVar instanceof e)) {
            dVar = null;
        }
        return (e) dVar;
    }

    @Override // k5.d
    public g getContext() {
        return this.context;
    }

    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // k5.d
    public void resumeWith(Object obj) {
        this.uCont.resumeWith(obj);
    }
}
