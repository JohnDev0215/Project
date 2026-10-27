package c.b.a.a;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f2063a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Executor f2064b = new ExecutorC0056a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Executor f2065c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f2066d;
    private c e;

    /* JADX INFO: renamed from: c.b.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    static class ExecutorC0056a implements Executor {
        ExecutorC0056a() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.d().c(runnable);
        }
    }

    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    static class b implements Executor {
        b() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.d().a(runnable);
        }
    }

    private a() {
        c.b.a.a.b bVar = new c.b.a.a.b();
        this.e = bVar;
        this.f2066d = bVar;
    }

    public static a d() {
        if (f2063a != null) {
            return f2063a;
        }
        synchronized (a.class) {
            if (f2063a == null) {
                f2063a = new a();
            }
        }
        return f2063a;
    }

    @Override // c.b.a.a.c
    public void a(Runnable runnable) {
        this.f2066d.a(runnable);
    }

    @Override // c.b.a.a.c
    public boolean b() {
        return this.f2066d.b();
    }

    @Override // c.b.a.a.c
    public void c(Runnable runnable) {
        this.f2066d.c(runnable);
    }
}
