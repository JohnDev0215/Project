package d.a.q;

import d.a.j;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: Schedulers.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final j f4250a = d.a.p.a.h(new h());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final j f4251b = d.a.p.a.e(new b());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final j f4252c = d.a.p.a.f(new c());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final j f4253d = io.reactivex.internal.schedulers.g.d();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final j f4254e = d.a.p.a.g(new f());

    /* JADX INFO: renamed from: d.a.q.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Schedulers.java */
    static final class C0110a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4255a = new io.reactivex.internal.schedulers.a();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class b implements Callable<j> {
        b() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return C0110a.f4255a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class c implements Callable<j> {
        c() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return d.f4256a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4256a = new io.reactivex.internal.schedulers.b();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4257a = new io.reactivex.internal.schedulers.c();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class f implements Callable<j> {
        f() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return e.f4257a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4258a = new io.reactivex.internal.schedulers.f();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class h implements Callable<j> {
        h() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return g.f4258a;
        }
    }

    public static j a() {
        return d.a.p.a.k(f4251b);
    }

    public static j b() {
        return d.a.p.a.m(f4252c);
    }
}
