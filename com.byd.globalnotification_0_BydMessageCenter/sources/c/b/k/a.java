package c.b.k;

import c.b.h.e.i;
import c.b.h.e.j;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c.b.c f2337a;

    /* JADX INFO: renamed from: c.b.k.a$a, reason: collision with other inner class name */
    public static final class C0054a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c.b.c f2338a = new c.b.h.e.a();
    }

    public static final class b implements Callable<c.b.c> {
        @Override // java.util.concurrent.Callable
        public c.b.c call() {
            return C0054a.f2338a;
        }
    }

    public static final class c implements Callable<c.b.c> {
        @Override // java.util.concurrent.Callable
        public c.b.c call() {
            return d.f2339a;
        }
    }

    public static final class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c.b.c f2339a = new c.b.h.e.b();
    }

    public static final class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c.b.c f2340a = new c.b.h.e.c();
    }

    public static final class f implements Callable<c.b.c> {
        @Override // java.util.concurrent.Callable
        public c.b.c call() {
            return e.f2340a;
        }
    }

    public static final class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c.b.c f2341a = new i();
    }

    public static final class h implements Callable<c.b.c> {
        @Override // java.util.concurrent.Callable
        public c.b.c call() {
            return g.f2341a;
        }
    }

    static {
        h hVar = new h();
        c.b.h.b.b.a(hVar, "Scheduler Callable can't be null");
        c.b.j.a.a(hVar);
        b bVar = new b();
        c.b.h.b.b.a(bVar, "Scheduler Callable can't be null");
        f2337a = c.b.j.a.a(bVar);
        c cVar = new c();
        c.b.h.b.b.a(cVar, "Scheduler Callable can't be null");
        c.b.j.a.a(cVar);
        j jVar = j.f2304b;
        f fVar = new f();
        c.b.h.b.b.a(fVar, "Scheduler Callable can't be null");
        c.b.j.a.a(fVar);
    }
}
