package b.a.a.k;

/* JADX INFO: compiled from: AfterFilter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c implements b1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal<i0> f338a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadLocal<Character> f339b = new ThreadLocal<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Character f340c = ',';

    public final char f(i0 i0Var, Object obj, char c2) {
        ThreadLocal<i0> threadLocal = f338a;
        i0 i0Var2 = threadLocal.get();
        threadLocal.set(i0Var);
        ThreadLocal<Character> threadLocal2 = f339b;
        threadLocal2.set(Character.valueOf(c2));
        g(obj);
        threadLocal.set(i0Var2);
        return threadLocal2.get().charValue();
    }

    public abstract void g(Object obj);
}
