package e.a.n.a;

import io.reactivex.exceptions.OnErrorNotImplementedException;
import java.util.Comparator;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: Functions.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final e.a.m.f<Object, Object> f4926a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Runnable f4927b = new d();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e.a.m.a f4928c = new C0089a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final e.a.m.e<Object> f4929d = new b();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final e.a.m.e<Throwable> f4930e = new e();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e.a.m.e<Throwable> f4931f = new k();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e.a.m.g f4932g = new c();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static final e.a.m.h<Object> f4933h = new l();
    static final e.a.m.h<Object> i = new f();
    static final Callable<Object> j = new j();
    static final Comparator<Object> k = new i();
    public static final e.a.m.e<g.a.a> l = new h();

    /* JADX INFO: renamed from: e.a.n.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Functions.java */
    static final class C0089a implements e.a.m.a {
        C0089a() {
        }

        public String toString() {
            return "EmptyAction";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class b implements e.a.m.e<Object> {
        b() {
        }

        @Override // e.a.m.e
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class c implements e.a.m.g {
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
    static final class e implements e.a.m.e<Throwable> {
        e() {
        }

        @Override // e.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th) {
            e.a.o.a.k(th);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class f implements e.a.m.h<Object> {
        f() {
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class g implements e.a.m.f<Object, Object> {
        g() {
        }

        @Override // e.a.m.f
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class h implements e.a.m.e<g.a.a> {
        h() {
        }

        @Override // e.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(g.a.a aVar) throws Exception {
            aVar.request(Long.MAX_VALUE);
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
    static final class k implements e.a.m.e<Throwable> {
        k() {
        }

        @Override // e.a.m.e
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th) {
            e.a.o.a.k(new OnErrorNotImplementedException(th));
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class l implements e.a.m.h<Object> {
        l() {
        }
    }
}
