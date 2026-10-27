package p;

import com.spotify.base.java.logging.Logger;
import com.spotify.support.assertion.Assertion$Note;
import com.spotify.support.assertion.Assertion$RecoverableAssertionError;

/* JADX INFO: loaded from: classes.dex */
public final class e91 implements kc {
    public final jk c;

    public e91(jk jkVar) {
        this.c = jkVar;
    }

    @Override // p.kc
    public final void c(Throwable th) {
        th.getMessage();
        this.c.a.recordException(th);
    }

    @Override // p.kc
    public final void d(Assertion$Note assertion$Note) {
        h(assertion$Note);
    }

    @Override // p.kc
    public final void f(AssertionError assertionError) {
        Logger.c(assertionError, "", new Object[0]);
        throw assertionError;
    }

    @Override // p.kc
    public final void h(Assertion$RecoverableAssertionError assertion$RecoverableAssertionError) {
        Throwable cause = assertion$RecoverableAssertionError.getCause();
        if (cause != null) {
            assertion$RecoverableAssertionError.getMessage();
            cause.getMessage();
        } else {
            assertion$RecoverableAssertionError.getMessage();
        }
        this.c.a.recordException(assertion$RecoverableAssertionError);
    }
}
