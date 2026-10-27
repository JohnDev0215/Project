package S8;

/* JADX INFO: loaded from: classes.dex */
public abstract class n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f8122a = new ThreadLocal();

    public static N a() {
        ThreadLocal threadLocal = f8122a;
        N n10 = (N) threadLocal.get();
        if (n10 != null) {
            return n10;
        }
        C0707d c0707d = new C0707d(Thread.currentThread());
        threadLocal.set(c0707d);
        return c0707d;
    }
}
