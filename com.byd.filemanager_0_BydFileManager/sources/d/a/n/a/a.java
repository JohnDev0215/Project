package d.a.n.a;

import io.reactivex.exceptions.OnErrorNotImplementedException;
import java.util.Comparator;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: Functions.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final d.a.m.f<Object, Object> f4527a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Runnable f4528b = new d();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d.a.m.a f4529c = new C0113a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final d.a.m.e<Object> f4530d = new b();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d.a.m.e<Throwable> f4531e = new e();
    public static final d.a.m.e<Throwable> f = new k();
    public static final d.a.m.g g = new c();
    static final d.a.m.h<Object> h = new l();
    static final d.a.m.h<Object> i = new f();
    static final Callable<Object> j = new j();
    static final Comparator<Object> k = new i();
    public static final d.a.m.e<e.a.a> l = new h();

    /* JADX INFO: renamed from: d.a.n.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Functions.java */
    static final class C0113a implements d.a.m.a {
        C0113a() {
        }

        @Override // d.a.m.a
        public void run() {
        }

        public String toString() {
            return "EmptyAction";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class b implements d.a.m.e<Object> {
        b() {
        }

        @Override // d.a.m.e
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class c implements d.a.m.g {
        c() {
        }
    }

    /* JADX INFO: compiled from: Functions.java */
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

    /* JADX INFO: compiled from: Functions.java */
    static final class e implements d.a.m.e<Throwable> {
        e() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th) {
            d.a.o.a.l(th);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class f implements d.a.m.h<Object> {
        f() {
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class g implements d.a.m.f<Object, Object> {
        g() {
        }

        @Override // d.a.m.f
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class h implements d.a.m.e<e.a.a> {
        h() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(e.a.a aVar) throws Exception {
            aVar.a(Long.MAX_VALUE);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class i implements Comparator<Object> {
        i() {
        }

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class j implements Callable<Object> {
        j() {
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            return null;
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class k implements d.a.m.e<Throwable> {
        k() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th) {
            d.a.o.a.l(new OnErrorNotImplementedException(th));
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class l implements d.a.m.h<Object> {
        l() {
        }
    }

    public static <T> d.a.m.e<T> a() {
        return (d.a.m.e<T>) f4530d;
    }
}
