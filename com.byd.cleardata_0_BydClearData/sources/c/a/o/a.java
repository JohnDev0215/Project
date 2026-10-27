package c.a.o;

import c.a.m.g.j;
import c.a.m.g.k;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final c.a.h f1916a = c.a.n.a.h(new h());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final c.a.h f1917b = c.a.n.a.e(new b());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final c.a.h f1918c = c.a.n.a.f(new c());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final c.a.h f1919d = k.d();
    static final c.a.h e = c.a.n.a.g(new f());

    /* JADX INFO: renamed from: c.a.o.a$a, reason: collision with other inner class name */
    static final class C0063a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final c.a.h f1920a = new c.a.m.g.b();
    }

    static final class b implements Callable<c.a.h> {
        b() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c.a.h call() {
            return C0063a.f1920a;
        }
    }

    static final class c implements Callable<c.a.h> {
        c() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c.a.h call() {
            return d.f1921a;
        }
    }

    static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final c.a.h f1921a = new c.a.m.g.c();
    }

    static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final c.a.h f1922a = new c.a.m.g.d();
    }

    static final class f implements Callable<c.a.h> {
        f() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c.a.h call() {
            return e.f1922a;
        }
    }

    static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final c.a.h f1923a = new j();
    }

    static final class h implements Callable<c.a.h> {
        h() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c.a.h call() {
            return g.f1923a;
        }
    }

    public static c.a.h a() {
        return c.a.n.a.k(f1917b);
    }

    public static c.a.h b() {
        return c.a.n.a.m(f1918c);
    }

    public static c.a.h c() {
        return c.a.n.a.n(e);
    }
}
