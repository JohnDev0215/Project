package kotlinx.coroutines;

/* JADX INFO: compiled from: EventLoop.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class h2 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h2 f7614b = new h2();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<y0> f7613a = new ThreadLocal<>();

    private h2() {
    }

    public final y0 a() {
        ThreadLocal<y0> threadLocal = f7613a;
        y0 y0Var = threadLocal.get();
        if (y0Var != null) {
            return y0Var;
        }
        y0 y0VarA = b1.a();
        threadLocal.set(y0VarA);
        return y0VarA;
    }

    public final void b() {
        f7613a.set(null);
    }

    public final void c(y0 y0Var) {
        f7613a.set(y0Var);
    }
}
