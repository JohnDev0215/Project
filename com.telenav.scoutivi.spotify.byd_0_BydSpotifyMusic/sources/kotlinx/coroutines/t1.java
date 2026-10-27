package kotlinx.coroutines;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public final class t1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final kotlinx.coroutines.internal.c0 f7826a = new kotlinx.coroutines.internal.c0("COMPLETING_ALREADY");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final kotlinx.coroutines.internal.c0 f7827b = new kotlinx.coroutines.internal.c0("COMPLETING_WAITING_CHILDREN");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final kotlinx.coroutines.internal.c0 f7828c = new kotlinx.coroutines.internal.c0("COMPLETING_RETRY");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final kotlinx.coroutines.internal.c0 f7829d = new kotlinx.coroutines.internal.c0("TOO_LATE_TO_CANCEL");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final kotlinx.coroutines.internal.c0 f7830e = new kotlinx.coroutines.internal.c0("SEALED");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final x0 f7831f = new x0(false);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final x0 f7832g = new x0(true);

    public static final Object g(Object obj) {
        return obj instanceof g1 ? new h1((g1) obj) : obj;
    }

    public static final Object h(Object obj) {
        g1 g1Var;
        h1 h1Var = (h1) (!(obj instanceof h1) ? null : obj);
        return (h1Var == null || (g1Var = h1Var.f7612a) == null) ? obj : g1Var;
    }
}
