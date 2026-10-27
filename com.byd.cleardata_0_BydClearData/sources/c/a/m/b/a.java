package c.a.m.b;

import java.util.Comparator;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final c.a.l.e<Object, Object> f1823a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Runnable f1824b = new d();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c.a.l.a f1825c = new C0059a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final c.a.l.d<Object> f1826d = new b();
    public static final c.a.l.d<Throwable> e = new e();
    public static final c.a.l.d<Throwable> f = new k();
    public static final c.a.l.f g = new c();
    static final c.a.l.g<Object> h = new l();
    static final c.a.l.g<Object> i = new f();
    static final Callable<Object> j = new j();
    static final Comparator<Object> k = new i();
    public static final c.a.l.d<d.a.a> l = new h();

    /* JADX INFO: renamed from: c.a.m.b.a$a, reason: collision with other inner class name */
    static final class C0059a implements c.a.l.a {
        C0059a() {
        }

        @Override // c.a.l.a
        public void run() {
        }

        public String toString() {
            return "EmptyAction";
        }
    }

    static final class b implements c.a.l.d<Object> {
        b() {
        }

        @Override // c.a.l.d
        public void a(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    static final class c implements c.a.l.f {
        c() {
        }
    }

    static final class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
        }

        public String toString() {
            return "EmptyRunnable";
        }
    }

    static final class e implements c.a.l.d<Throwable> {
        e() {
        }

        @Override // c.a.l.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Throwable th) {
            c.a.n.a.l(th);
        }
    }

    static final class f implements c.a.l.g<Object> {
        f() {
        }
    }

    static final class g implements c.a.l.e<Object, Object> {
        g() {
        }

        @Override // c.a.l.e
        public Object a(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    static final class h implements c.a.l.d<d.a.a> {
        h() {
        }

        @Override // c.a.l.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(d.a.a aVar) {
            aVar.a(Long.MAX_VALUE);
        }
    }

    static final class i implements Comparator<Object> {
        i() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    static final class j implements Callable<Object> {
        j() {
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            return null;
        }
    }

    static final class k implements c.a.l.d<Throwable> {
        k() {
        }

        @Override // c.a.l.d
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(Throwable th) {
            c.a.n.a.l(new c.a.k.c(th));
        }
    }

    static final class l implements c.a.l.g<Object> {
        l() {
        }
    }

    public static <T> c.a.l.d<T> a() {
        return (c.a.l.d<T>) f1826d;
    }
}
