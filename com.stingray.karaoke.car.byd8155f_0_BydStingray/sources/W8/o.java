package W8;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class o extends CancellationException {
    public o() {
        super("Child of the scoped flow was cancelled");
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
