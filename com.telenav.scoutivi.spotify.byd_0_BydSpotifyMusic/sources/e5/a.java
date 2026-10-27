package e5;

import a5.l;
import a5.m;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: Schedulers.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final q4.d f6303a = d5.a.h(new h());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final q4.d f6304b = d5.a.e(new b());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final q4.d f6305c = d5.a.f(new c());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final q4.d f6306d = m.d();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final q4.d f6307e = d5.a.g(new f());

    /* JADX INFO: renamed from: e5.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Schedulers.java */
    static final class C0072a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final q4.d f6308a = new a5.b();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class b implements Callable<q4.d> {
        b() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public q4.d call() {
            return C0072a.f6308a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class c implements Callable<q4.d> {
        c() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public q4.d call() {
            return d.f6309a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final q4.d f6309a = new a5.e();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final q4.d f6310a = new a5.f();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class f implements Callable<q4.d> {
        f() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public q4.d call() {
            return e.f6310a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final q4.d f6311a = new l();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    static final class h implements Callable<q4.d> {
        h() {
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public q4.d call() {
            return g.f6311a;
        }
    }

    public static q4.d a(Executor executor) {
        return new a5.d(executor, false);
    }

    public static q4.d b() {
        return d5.a.l(f6305c);
    }

    public static q4.d c() {
        return d5.a.n(f6303a);
    }
}
