package d.a.p;

import d.a.j;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: Schedulers.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final j f4538a = d.a.o.a.h(new h());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final j f4539b = d.a.o.a.e(new b());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final j f4540c = d.a.o.a.f(new c());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final j f4541d = io.reactivex.internal.schedulers.h.d();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final j f4542e = d.a.o.a.g(new f());

    /* JADX INFO: renamed from: d.a.p.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Schedulers.java */
    static final class C0114a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4543a = new io.reactivex.internal.schedulers.a();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class b implements Callable<j> {
        b() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return C0114a.f4543a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class c implements Callable<j> {
        c() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return d.f4544a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4544a = new io.reactivex.internal.schedulers.c();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4545a = new io.reactivex.internal.schedulers.d();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class f implements Callable<j> {
        f() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return e.f4545a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final j f4546a = new io.reactivex.internal.schedulers.g();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class h implements Callable<j> {
        h() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return g.f4546a;
        }
    }

    public static j a() {
        return d.a.o.a.k(f4539b);
    }

    public static j b(Executor executor) {
        return new ExecutorScheduler(executor);
    }

    public static j c() {
        return d.a.o.a.m(f4540c);
    }

    public static j d() {
        return d.a.o.a.n(f4542e);
    }

    public static j e() {
        return d.a.o.a.p(f4538a);
    }
}
