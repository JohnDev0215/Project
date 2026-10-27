package kotlin;

import kotlin.Result;

/* JADX INFO: compiled from: Result.kt */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final Object a(Throwable th) {
        kotlin.jvm.internal.i.c(th, "exception");
        return new Result.Failure(th);
    }

    public static final void b(Object obj) throws Throwable {
        if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        }
    }
}
