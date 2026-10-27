package defpackage;

/* JADX INFO: compiled from: DoubleCheck.java */
/* JADX INFO: loaded from: classes.dex */
public final class sb0<T> implements c72<T>, p61<T> {
    public static final Object g = new Object();
    public volatile c72<T> e;
    public volatile Object f = g;

    public sb0(c72<T> c72Var) {
        this.e = c72Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <P extends c72<T>, T> p61<T> a(P p) {
        return p instanceof p61 ? (p61) p : new sb0(p);
    }

    public static void b(Object obj, Object obj2) {
        if (!((obj == g || (obj instanceof pe1)) ? false : true) || obj == obj2) {
            return;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    @Override // defpackage.c72
    public final T get() {
        T t = (T) this.f;
        Object obj = g;
        if (t == obj) {
            synchronized (this) {
                t = (T) this.f;
                if (t == obj) {
                    t = this.e.get();
                    b(this.f, t);
                    this.f = t;
                    this.e = null;
                }
            }
        }
        return t;
    }
}
