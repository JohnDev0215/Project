package kotlin;

import kotlin.Result;
import p.lt1;
import p.py0;
import p.v03;
import p.v81;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static void a(Throwable th, Throwable th2) {
        v03.j(th, "<this>");
        v03.j(th2, "exception");
        if (th != th2) {
            lt1.a.a(th, th2);
        }
    }

    public static final Result.Failure b(Throwable th) {
        v03.j(th, "exception");
        return new Result.Failure(th);
    }

    public static v81 c(py0 py0Var) {
        v03.j(py0Var, "initializer");
        return new SynchronizedLazyImpl(py0Var);
    }

    public static final void d(Object obj) {
        if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        }
    }
}
