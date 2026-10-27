package x4;

import java.util.Comparator;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: Functions.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final v4.e<Object, Object> f9840a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Runnable f9841b = new d();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final v4.a f9842c = new C0172a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final v4.d<Object> f9843d = new b();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final v4.d<Throwable> f9844e = new e();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final v4.d<Throwable> f9845f = new k();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final v4.f f9846g = new c();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    static final v4.g<Object> f9847h = new l();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    static final v4.g<Object> f9848i = new f();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    static final Callable<Object> f9849j = new j();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    static final Comparator<Object> f9850k = new i();

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final v4.d<r6.a> f9851l = new h();

    /* JADX INFO: renamed from: x4.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Functions.java */
    static final class C0172a implements v4.a {
        C0172a() {
        }

        public String toString() {
            return "EmptyAction";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class b implements v4.d<Object> {
        b() {
        }

        @Override // v4.d
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class c implements v4.f {
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
    static final class e implements v4.d<Throwable> {
        e() {
        }

        @Override // v4.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th) {
            d5.a.k(th);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class f implements v4.g<Object> {
        f() {
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class g implements v4.e<Object, Object> {
        g() {
        }

        @Override // v4.e
        public Object a(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class h implements v4.d<r6.a> {
        h() {
        }

        @Override // v4.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(r6.a aVar) {
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
    static final class k implements v4.d<Throwable> {
        k() {
        }

        @Override // v4.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void accept(Throwable th) {
            d5.a.k(new u4.c(th));
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class l implements v4.g<Object> {
        l() {
        }
    }
}
