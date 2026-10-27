package p4;

/* JADX INFO: compiled from: DoubleCheck.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<T> implements f5.a<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f8843c = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile f5.a<T> f8844a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile Object f8845b = f8843c;

    private b(f5.a<T> aVar) {
        this.f8844a = aVar;
    }

    public static <P extends f5.a<T>, T> f5.a<T> a(P p7) {
        d.b(p7);
        return p7 instanceof b ? p7 : new b(p7);
    }

    public static Object b(Object obj, Object obj2) {
        if (!(obj != f8843c) || obj == obj2) {
            return obj2;
        }
        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj + " & " + obj2 + ". This is likely due to a circular dependency.");
    }

    @Override // f5.a
    public T get() {
        T t6 = (T) this.f8845b;
        Object obj = f8843c;
        if (t6 == obj) {
            synchronized (this) {
                t6 = (T) this.f8845b;
                if (t6 == obj) {
                    t6 = this.f8844a.get();
                    this.f8845b = b(this.f8845b, t6);
                    this.f8844a = null;
                }
            }
        }
        return t6;
    }
}
