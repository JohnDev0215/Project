package b.b.a.a;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f1496a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Executor f1497b = new ExecutorC0029a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Executor f1498c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f1499d;
    private c e;

    /* JADX INFO: renamed from: b.b.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    static class ExecutorC0029a implements Executor {
        ExecutorC0029a() {
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
        b.b.a.a.b bVar = new b.b.a.a.b();
        this.e = bVar;
        this.f1499d = bVar;
    }

    public static a d() {
        if (f1496a != null) {
            return f1496a;
        }
        synchronized (a.class) {
            if (f1496a == null) {
                f1496a = new a();
            }
        }
        return f1496a;
    }

    @Override // b.b.a.a.c
    public void a(Runnable runnable) {
        this.f1499d.a(runnable);
    }

    @Override // b.b.a.a.c
    public boolean b() {
        return this.f1499d.b();
    }

    @Override // b.b.a.a.c
    public void c(Runnable runnable) {
        this.f1499d.c(runnable);
    }
}
