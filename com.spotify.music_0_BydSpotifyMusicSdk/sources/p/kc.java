package p;

import com.spotify.support.assertion.Assertion$Note;
import com.spotify.support.assertion.Assertion$RecoverableAssertionError;

/* JADX INFO: loaded from: classes.dex */
public interface kc {
    void c(Throwable th);

    void d(Assertion$Note assertion$Note);

    void f(AssertionError assertionError);

    void h(Assertion$RecoverableAssertionError assertion$RecoverableAssertionError);
}
