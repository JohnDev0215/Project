package kotlin.coroutines;

import java.io.Serializable;
import p.fz0;
import p.v03;
import p.w10;
import p.x10;
import p.y10;

/* JADX INFO: loaded from: classes.dex */
public final class EmptyCoroutineContext implements y10, Serializable {
    public static final EmptyCoroutineContext c = new EmptyCoroutineContext();
    private static final long serialVersionUID = 0;

    private EmptyCoroutineContext() {
    }

    private final Object readResolve() {
        return c;
    }

    @Override // p.y10
    public final y10 E(x10 x10Var) {
        v03.j(x10Var, "key");
        return this;
    }

    @Override // p.y10
    public final w10 E0(x10 x10Var) {
        v03.j(x10Var, "key");
        return null;
    }

    @Override // p.y10
    public final Object c(Object obj, fz0 fz0Var) {
        v03.j(fz0Var, "operation");
        return obj;
    }

    public final int hashCode() {
        return 0;
    }

    @Override // p.y10
    public final y10 o0(y10 y10Var) {
        v03.j(y10Var, "context");
        return y10Var;
    }

    public final String toString() {
        return "EmptyCoroutineContext";
    }
}
