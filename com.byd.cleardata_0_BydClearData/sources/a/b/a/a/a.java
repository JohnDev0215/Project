package a.b.a.a;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f97a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Executor f98b = new ExecutorC0003a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Executor f99c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f100d;
    private c e;

    /* JADX INFO: renamed from: a.b.a.a.a$a, reason: collision with other inner class name */
    static class ExecutorC0003a implements Executor {
        ExecutorC0003a() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.d().c(runnable);
        }
    }

    static class b implements Executor {
        b() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.d().a(runnable);
        }
    }

    private a() {
        a.b.a.a.b bVar = new a.b.a.a.b();
        this.e = bVar;
        this.f100d = bVar;
    }

    public static a d() {
        if (f97a != null) {
            return f97a;
        }
        synchronized (a.class) {
            if (f97a == null) {
                f97a = new a();
            }
        }
        return f97a;
    }

    @Override // a.b.a.a.c
    public void a(Runnable runnable) {
        this.f100d.a(runnable);
    }

    @Override // a.b.a.a.c
    public boolean b() {
        return this.f100d.b();
    }

    @Override // a.b.a.a.c
    public void c(Runnable runnable) {
        this.f100d.c(runnable);
    }
}
