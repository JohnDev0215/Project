package h2;

/* JADX INFO: compiled from: BeforeFilter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class k implements b1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<i0> f5259a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadLocal<Character> f5260b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Character f5261c = ',';

    public final char f(i0 i0Var, Object obj, char c8) {
        ThreadLocal<i0> threadLocal = f5259a;
        threadLocal.set(i0Var);
        ThreadLocal<Character> threadLocal2 = f5260b;
        threadLocal2.set(Character.valueOf(c8));
        g(obj);
        threadLocal.set(null);
        return threadLocal2.get().charValue();
    }

    public abstract void g(Object obj);
}
