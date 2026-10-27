package e.a.p;

import e.a.j;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: Schedulers.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final j f4943a = e.a.o.a.h(new h());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final j f4944b = e.a.o.a.e(new b());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final j f4945c = e.a.o.a.f(new c());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final j f4946d = io.reactivex.internal.schedulers.g.d();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final j f4947e = e.a.o.a.g(new f());

    /* JADX INFO: renamed from: e.a.p.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Schedulers.java */
    static final class C0090a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4948a = new io.reactivex.internal.schedulers.a();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class b implements Callable<j> {
        b() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return C0090a.f4948a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class c implements Callable<j> {
        c() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return d.f4949a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4949a = new io.reactivex.internal.schedulers.b();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4950a = new io.reactivex.internal.schedulers.c();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class f implements Callable<j> {
        f() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return e.f4950a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4951a = new io.reactivex.internal.schedulers.f();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class h implements Callable<j> {
        h() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return g.f4951a;
        }
    }

    public static j a() {
        return e.a.o.a.l(f4945c);
    }
}
