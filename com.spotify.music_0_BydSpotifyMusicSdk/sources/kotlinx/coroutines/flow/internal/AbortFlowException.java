package kotlinx.coroutines.flow.internal;

import java.util.concurrent.CancellationException;
import p.fv0;

/* JADX INFO: loaded from: classes.dex */
public final class AbortFlowException extends CancellationException {
    public final transient fv0 c;

    public AbortFlowException(fv0 fv0Var) {
        super("Flow was aborted, no more elements needed");
        this.c = fv0Var;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
