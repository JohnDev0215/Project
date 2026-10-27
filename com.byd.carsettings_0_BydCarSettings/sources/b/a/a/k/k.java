package b.a.a.k;

/* JADX INFO: compiled from: BeforeFilter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class k implements b1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<i0> f373a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadLocal<Character> f374b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Character f375c = ',';

    public final char f(i0 i0Var, Object obj, char c2) {
        ThreadLocal<i0> threadLocal = f373a;
        threadLocal.set(i0Var);
        ThreadLocal<Character> threadLocal2 = f374b;
        threadLocal2.set(Character.valueOf(c2));
        g(obj);
        threadLocal.set(null);
        return threadLocal2.get().charValue();
    }

    public abstract void g(Object obj);
}
