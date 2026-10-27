package kotlinx.coroutines;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: Exceptions.kt */
/* JADX INFO: loaded from: classes.dex */
public final class m1 extends CancellationException {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l1 f7707e;

    public m1(String str, Throwable th, l1 l1Var) {
        super(str);
        this.f7707e = l1Var;
        if (th != null) {
            initCause(th);
        }
    }

    public boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof m1) {
                m1 m1Var = (m1) obj;
                if (!kotlin.jvm.internal.l.a(m1Var.getMessage(), getMessage()) || !kotlin.jvm.internal.l.a(m1Var.f7707e, this.f7707e) || !kotlin.jvm.internal.l.a(m1Var.getCause(), getCause())) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // java.lang.Throwable
    public Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public int hashCode() {
        String message = getMessage();
        kotlin.jvm.internal.l.c(message);
        int iHashCode = ((message.hashCode() * 31) + this.f7707e.hashCode()) * 31;
        Throwable cause = getCause();
        return iHashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public String toString() {
        return super.toString() + "; job=" + this.f7707e;
    }
}
