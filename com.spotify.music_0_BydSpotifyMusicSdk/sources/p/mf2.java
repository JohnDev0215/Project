package p;

import kotlin.Result;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;

/* JADX INFO: loaded from: classes.dex */
public abstract class mf2 {
    public static final /* synthetic */ int a = 0;

    static {
        Object objB;
        Object objB2;
        Exception exc = new Exception();
        String simpleName = p02.class.getSimpleName();
        StackTraceElement stackTraceElement = exc.getStackTrace()[0];
        new StackTraceElement("_COROUTINE.".concat(simpleName), "_", stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
        try {
            objB = BaseContinuationImpl.class.getCanonicalName();
        } catch (Throwable th) {
            objB = kotlin.a.b(th);
        }
        if (Result.a(objB) != null) {
            objB = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        }
        try {
            objB2 = mf2.class.getCanonicalName();
        } catch (Throwable th2) {
            objB2 = kotlin.a.b(th2);
        }
        if (Result.a(objB2) != null) {
            objB2 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        }
    }
}
