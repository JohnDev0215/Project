package p;

import com.spotify.base.java.logging.Logger;
import com.spotify.support.assertion.Assertion$Note;
import com.spotify.support.assertion.Assertion$RecoverableAssertionError;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class lc {
    public static final lc b = new lc(new qo());
    public kc a;

    private lc(kc kcVar) {
        this.a = kcVar;
    }

    public static void a(Object obj) {
        b(obj, "Object failed null check");
    }

    @Deprecated
    public static void b(Object obj, String str) {
        if (obj == null) {
            h(str);
        }
    }

    public static void c(String str) {
        k(str);
    }

    public static void d(String str, String str2) {
        j(new Assertion$RecoverableAssertionError(Logger.d("%s", str), str2));
    }

    public static void e(String str, Throwable th) {
        j(new Assertion$RecoverableAssertionError(str, th));
    }

    @Deprecated
    public static void f(String str, boolean z) {
        if (z) {
            return;
        }
        h(Logger.d("%s", str));
    }

    private static kc g() {
        return b.a;
    }

    public static void h(String str) {
        Logger.b("%s", str);
        i(new AssertionError(Logger.d("%s", str)));
    }

    private static void i(AssertionError assertionError) {
        m(assertionError);
        g().f(assertionError);
    }

    private static void j(Assertion$RecoverableAssertionError assertion$RecoverableAssertionError) {
        m(assertion$RecoverableAssertionError);
        g().h(assertion$RecoverableAssertionError);
    }

    private static void k(String str) {
        j(new Assertion$RecoverableAssertionError(Logger.d("%s", str), (String) null));
    }

    public static void l(Throwable th) {
        g().c(th);
    }

    private static void m(Throwable th) {
        ArrayList arrayList = new ArrayList();
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            if (!lc.class.getCanonicalName().equals(stackTraceElement.getClassName())) {
                arrayList.add(stackTraceElement);
            }
        }
        th.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
    }

    public static void n(String str) {
        p(new Assertion$Note(str, null));
    }

    public static void o(String str, Throwable th) {
        p(new Assertion$Note(str, th));
    }

    private static void p(Assertion$Note assertion$Note) {
        m(assertion$Note);
        g().d(assertion$Note);
    }

    public static void q(kc kcVar) {
        b.a = kcVar;
    }
}
