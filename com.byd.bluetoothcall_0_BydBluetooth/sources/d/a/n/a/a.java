package d.a.n.a;

import io.reactivex.exceptions.OnErrorNotImplementedException;
import java.util.Comparator;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: Functions.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a */
    static final d.a.m.f<Object, Object> f4235a = new g();

    /* JADX INFO: renamed from: b */
    public static final Runnable f4236b = new d();

    /* JADX INFO: renamed from: c */
    public static final d.a.m.a f4237c = new C0109a();

    /* JADX INFO: renamed from: d */
    static final d.a.m.e<Object> f4238d = new b();

    /* JADX INFO: renamed from: e */
    public static final d.a.m.e<Throwable> f4239e = new e();
    public static final d.a.m.e<Throwable> f = new k();
    public static final d.a.m.g g = new c();
    static final d.a.m.h<Object> h = new l();
    static final d.a.m.h<Object> i = new f();
    static final Callable<Object> j = new j();
    static final Comparator<Object> k = new i();
    public static final d.a.m.e<e.a.b> l = new h();

    /* JADX INFO: renamed from: d.a.n.a.a$a */
    /* JADX INFO: compiled from: Functions.java */
    static final class C0109a implements d.a.m.a {
        C0109a() {
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
        /* JADX INFO: renamed from: a */
        public void accept(Throwable th) {
            d.a.p.a.l(th);
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
    static final class h implements d.a.m.e<e.a.b> {
        h() {
        }

        @Override // d.a.m.e
        /* JADX INFO: renamed from: a */
        public void accept(e.a.b bVar) throws Exception {
            bVar.a(Long.MAX_VALUE);
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
        /* JADX INFO: renamed from: a */
        public void accept(Throwable th) {
            d.a.p.a.l(new OnErrorNotImplementedException(th));
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class l implements d.a.m.h<Object> {
        l() {
        }
    }
}
