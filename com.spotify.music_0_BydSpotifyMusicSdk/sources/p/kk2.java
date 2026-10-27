package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class kk2 {
    public static final ThreadLocal a = new ThreadLocal();

    public static pi0 a() {
        ThreadLocal threadLocal = a;
        pi0 pi0Var = (pi0) threadLocal.get();
        if (pi0Var != null) {
            return pi0Var;
        }
        gm gmVar = new gm(Thread.currentThread());
        threadLocal.set(gmVar);
        return gmVar;
    }
}
