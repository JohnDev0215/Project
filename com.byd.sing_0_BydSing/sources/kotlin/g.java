package kotlin;

import kotlin.Result;

/* JADX INFO: compiled from: Result.kt */
/* JADX INFO: loaded from: classes.dex */
public final class g {
    public static final Object a(Throwable th) {
        kotlin.jvm.internal.h.d(th, "exception");
        return new Result.Failure(th);
    }
}
