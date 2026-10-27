package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import p.p61;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class JobCancellationException extends CancellationException {
    public final transient p61 c;

    public JobCancellationException(String str, Throwable th, p61 p61Var) {
        super(str);
        this.c = p61Var;
        if (th != null) {
            initCause(th);
        }
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof JobCancellationException) {
                JobCancellationException jobCancellationException = (JobCancellationException) obj;
                if (!v03.b(jobCancellationException.getMessage(), getMessage()) || !v03.b(jobCancellationException.c, this.c) || !v03.b(jobCancellationException.getCause(), getCause())) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public final int hashCode() {
        String message = getMessage();
        v03.h(message);
        int iHashCode = (this.c.hashCode() + (message.hashCode() * 31)) * 31;
        Throwable cause = getCause();
        return iHashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public final String toString() {
        return super.toString() + "; job=" + this.c;
    }
}
