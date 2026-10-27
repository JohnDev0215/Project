package defpackage;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: Exceptions.kt */
/* JADX INFO: loaded from: classes.dex */
public final class n31 extends CancellationException {
    public final m31 e;

    public n31(String str, Throwable th, m31 m31Var) {
        super(str);
        this.e = m31Var;
        if (th != null) {
            initCause(th);
        }
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof n31) {
                n31 n31Var = (n31) obj;
                if (!i21.a(n31Var.getMessage(), getMessage()) || !i21.a(n31Var.e, this.e) || !i21.a(n31Var.getCause(), getCause())) {
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
        i21.b(message);
        int iHashCode = (this.e.hashCode() + (message.hashCode() * 31)) * 31;
        Throwable cause = getCause();
        return iHashCode + (cause == null ? 0 : cause.hashCode());
    }

    @Override // java.lang.Throwable
    public final String toString() {
        return super.toString() + "; job=" + this.e;
    }
}
