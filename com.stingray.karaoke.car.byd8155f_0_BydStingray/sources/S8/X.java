package S8;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes.dex */
public final class X extends CancellationException {
    public final transient d0 l;

    public X(String str, Throwable th, d0 d0Var) {
        super(str);
        this.l = d0Var;
        if (th != null) {
            initCause(th);
        }
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof X) {
                X x8 = (X) obj;
                if (!A7.m.a(x8.getMessage(), getMessage()) || !A7.m.a(x8.l, this.l) || !A7.m.a(x8.getCause(), getCause())) {
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
        A7.m.c(message);
        int iHashCode = (this.l.hashCode() + (message.hashCode() * 31)) * 31;
        Throwable cause = getCause();
        return iHashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public final String toString() {
        return super.toString() + "; job=" + this.l;
    }
}
