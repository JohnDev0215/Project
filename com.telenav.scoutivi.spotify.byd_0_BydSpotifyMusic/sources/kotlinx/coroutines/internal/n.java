package kotlinx.coroutines.internal;

import java.util.Objects;

/* JADX INFO: compiled from: LockFreeLinkedList.kt */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Object f7654a = new c0("CONDITION_FALSE");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Object f7655b = new c0("LIST_EMPTY");

    public static final Object a() {
        return f7654a;
    }

    public static final Object b() {
        return f7655b;
    }

    public static final o c(Object obj) {
        o oVar;
        x xVar = (x) (!(obj instanceof x) ? null : obj);
        if (xVar != null && (oVar = xVar.f7687a) != null) {
            return oVar;
        }
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.Node /* = kotlinx.coroutines.internal.LockFreeLinkedListNode */");
        return (o) obj;
    }
}
