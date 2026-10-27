package X8;

import a.AbstractC0904a;
import h8.AbstractC1386c;
import s7.AbstractC2408a;

/* JADX INFO: loaded from: classes.dex */
public abstract class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f10578a = 0;

    static {
        Object objF;
        Object objF2;
        Exception exc = new Exception();
        String simpleName = AbstractC0904a.class.getSimpleName();
        StackTraceElement stackTraceElement = exc.getStackTrace()[0];
        new StackTraceElement("_COROUTINE.".concat(simpleName), "_", stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
        try {
            objF = AbstractC2408a.class.getCanonicalName();
        } catch (Throwable th) {
            objF = AbstractC1386c.f(th);
        }
        if (m7.l.a(objF) != null) {
            objF = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        try {
            objF2 = s.class.getCanonicalName();
        } catch (Throwable th2) {
            objF2 = AbstractC1386c.f(th2);
        }
        if (m7.l.a(objF2) != null) {
            objF2 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
    }
}
