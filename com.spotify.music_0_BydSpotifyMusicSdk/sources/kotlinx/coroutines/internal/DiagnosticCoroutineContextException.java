package kotlinx.coroutines.internal;

import p.y10;

/* JADX INFO: loaded from: classes.dex */
public final class DiagnosticCoroutineContextException extends RuntimeException {
    public final transient y10 c;

    public DiagnosticCoroutineContextException(y10 y10Var) {
        this.c = y10Var;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    @Override // java.lang.Throwable
    public final String getLocalizedMessage() {
        return this.c.toString();
    }
}
