package spi;

/* JADX INFO: loaded from: classes2.dex */
public final class u6<T> implements ll<T> {
    public static final Object c = new Object();
    public volatile ll<T> a;
    public volatile Object b = c;

    private u6(ll<T> llVar) {
        this.a = llVar;
    }

    public static <P extends ll<T>, T> ll<T> a(P p) {
        return p instanceof u6 ? p : new u6(p);
    }

    public static Object b(Object obj, Object obj2) {
        if (!(obj != c) || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    @Override // spi.ll
    public final T get() {
        T t = (T) this.b;
        Object obj = c;
        if (t == obj) {
            synchronized (this) {
                t = (T) this.b;
                if (t == obj) {
                    t = this.a.get();
                    b(this.b, t);
                    this.b = t;
                    this.a = null;
                }
            }
        }
        return t;
    }
}
