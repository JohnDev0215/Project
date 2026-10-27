package e.a.n.b;

import java.util.Comparator;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: Functions.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a */
    static final e.a.m.e<Object, Object> f4869a = new g();

    /* JADX INFO: renamed from: b */
    public static final Runnable f4870b = new d();

    /* JADX INFO: renamed from: c */
    public static final e.a.m.a f4871c = new C0094a();

    /* JADX INFO: renamed from: d */
    static final e.a.m.d<Object> f4872d = new b();

    /* JADX INFO: renamed from: e */
    public static final e.a.m.d<Throwable> f4873e = new e();

    /* JADX INFO: renamed from: f */
    public static final e.a.m.d<Throwable> f4874f = new k();

    /* JADX INFO: renamed from: g */
    public static final e.a.m.f f4875g = new c();

    /* JADX INFO: renamed from: h */
    static final e.a.m.g<Object> f4876h = new l();

    /* JADX INFO: renamed from: i */
    static final e.a.m.g<Object> f4877i = new f();
    static final Callable<Object> j = new j();
    static final Comparator<Object> k = new i();
    public static final e.a.m.d<g.a.a> l = new h();

    /* JADX INFO: renamed from: e.a.n.b.a$a */
    /* JADX INFO: compiled from: Functions.java */
    static final class C0094a implements e.a.m.a {
        C0094a() {
        }

        public String toString() {
            return "EmptyAction";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class b implements e.a.m.d<Object> {
        b() {
        }

        @Override // e.a.m.d
        public void accept(Object obj) {
        }

        public String toString() {
            return "EmptyConsumer";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class c implements e.a.m.f {
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
    static final class e implements e.a.m.d<Throwable> {
        e() {
        }

        @Override // e.a.m.d
        /* JADX INFO: renamed from: a */
        public void accept(Throwable th) {
            e.a.p.a.o(th);
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class f implements e.a.m.g<Object> {
        f() {
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class g implements e.a.m.e<Object, Object> {
        g() {
        }

        @Override // e.a.m.e
        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return "IdentityFunction";
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class h implements e.a.m.d<g.a.a> {
        h() {
        }

        @Override // e.a.m.d
        /* JADX INFO: renamed from: a */
        public void accept(g.a.a aVar) {
            aVar.c(Long.MAX_VALUE);
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
    static final class k implements e.a.m.d<Throwable> {
        k() {
        }

        @Override // e.a.m.d
        /* JADX INFO: renamed from: a */
        public void accept(Throwable th) {
            e.a.p.a.o(new e.a.l.c(th));
        }
    }

    /* JADX INFO: compiled from: Functions.java */
    static final class l implements e.a.m.g<Object> {
        l() {
        }
    }
}
