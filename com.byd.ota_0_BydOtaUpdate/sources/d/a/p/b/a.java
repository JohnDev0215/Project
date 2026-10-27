package d.a.p.b;

import java.util.Comparator;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: Functions.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d.a.o.e<Object, Object> f3170a = new k();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Runnable f3171b = new RunnableC0072a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d.a.o.a f3172c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final d.a.o.d<Object> f3173d = new c();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d.a.o.d<Throwable> f3174e = new d();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final d.a.o.f f3175f = new e();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final d.a.o.g<Object> f3176g = new f();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final d.a.o.g<Object> f3177h = new g();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Callable<Object> f3178i = new h();
    public static final Comparator<Object> j = new i();
    public static final d.a.o.d<g.b.c> k = new j();

    /* JADX INFO: renamed from: d.a.p.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Functions.java */
    public static class RunnableC0072a implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    public static class b implements d.a.o.a {
        public String toString() {
            return "EmptyAction";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    public static class c implements d.a.o.d<Object> {
        @Override // d.a.o.d
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    public static class d implements d.a.o.d<Throwable> {
        @Override // d.a.o.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th) {
            d.a.r.a.o(th);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    public static class e implements d.a.o.f {
    }

    /* JADX INFO: compiled from: Functions.java */
    public static class f implements d.a.o.g<Object> {
        @Override // d.a.o.g
        public boolean test(Object obj) {
            return true;
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    public static class g implements d.a.o.g<Object> {
        @Override // d.a.o.g
        public boolean test(Object obj) {
            return false;
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    public static class h implements Callable<Object> {
        @Override // java.util.concurrent.Callable
        public Object call() {
            return null;
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    public static class i implements Comparator<Object> {
        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    public static class j implements d.a.o.d<g.b.c> {
        @Override // d.a.o.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(g.b.c cVar) throws Exception {
            cVar.b(Long.MAX_VALUE);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    public static class k implements d.a.o.e<Object, Object> {
        @Override // d.a.o.e
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    public static final class l<T, U> implements Callable<U>, d.a.o.e<T, U> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final U f3179c;

        public l(U u) {
            this.f3179c = u;
        }

        @Override // d.a.o.e
        public U apply(T t) throws Exception {
            return this.f3179c;
        }

        @Override // java.util.concurrent.Callable
        public U call() throws Exception {
            return this.f3179c;
        }
    }

    public static <T> Callable<T> a(T t) {
        return new l(t);
    }
}
