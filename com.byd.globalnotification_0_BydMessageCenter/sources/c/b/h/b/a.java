package c.b.h.b;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Runnable f2231a = new c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c.b.g.a f2232b = new C0051a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c.b.g.b<Object> f2233c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final c.b.g.b<Throwable> f2234d = new d();

    /* JADX INFO: renamed from: c.b.h.b.a$a, reason: collision with other inner class name */
    public static final class C0051a implements c.b.g.a {
        public String toString() {
            return "EmptyAction";
        }
    }

    public static final class b implements c.b.g.b<Object> {
        @Override // c.b.g.b
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    public static final class c implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }
    }

    public static final class d implements c.b.g.b<Throwable> {
        @Override // c.b.g.b
        public void accept(Throwable th) {
            c.b.j.a.c(new c.b.f.b(th));
        }
    }
}
