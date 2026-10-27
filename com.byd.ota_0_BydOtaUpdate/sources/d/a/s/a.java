package d.a.s;

import d.a.j;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: Schedulers.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f3274a = d.a.r.a.h(new CallableC0078a());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final j f3275b = d.a.r.a.e(new b());

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final j f3276c = d.a.r.a.f(new c());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final j f3277d = d.a.p.g.g.d();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final j f3278e = d.a.r.a.g(new d());

    /* JADX INFO: renamed from: d.a.s.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Schedulers.java */
    public static class CallableC0078a implements Callable<j> {
        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return h.f3282a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    public static class b implements Callable<j> {
        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return e.f3279a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    public static class c implements Callable<j> {
        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return f.f3280a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    public static class d implements Callable<j> {
        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public j call() throws Exception {
            return g.f3281a;
        }
    }

    /* JADX INFO: compiled from: Schedulers.java */
    public static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final j f3279a = new d.a.p.g.a();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    public static final class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final j f3280a = new d.a.p.g.b();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    public static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final j f3281a = d.a.p.g.c.d();
    }

    /* JADX INFO: compiled from: Schedulers.java */
    public static final class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final j f3282a = new d.a.p.g.f();
    }

    public static j a() {
        return d.a.r.a.n(f3275b);
    }

    public static j b() {
        return d.a.r.a.p(f3276c);
    }
}
