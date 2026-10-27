package k5;

import java.io.Serializable;
import k5.g;
import kotlin.jvm.internal.l;
import r5.p;

/* JADX INFO: compiled from: CoroutineContextImpl.kt */
/* JADX INFO: loaded from: classes.dex */
public final class h implements g, Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final h f7276e = new h();
    private static final long serialVersionUID = 0;

    private h() {
    }

    private final Object readResolve() {
        return f7276e;
    }

    @Override // k5.g
    public <R> R fold(R r7, p<? super R, ? super g.b, ? extends R> operation) {
        l.e(operation, "operation");
        return r7;
    }

    @Override // k5.g
    public <E extends g.b> E get(g.c<E> key) {
        l.e(key, "key");
        return null;
    }

    public int hashCode() {
        return 0;
    }

    @Override // k5.g
    public g minusKey(g.c<?> key) {
        l.e(key, "key");
        return this;
    }

    @Override // k5.g
    public g plus(g context) {
        l.e(context, "context");
        return context;
    }

    public String toString() {
        return "EmptyCoroutineContext";
    }
}
