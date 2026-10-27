package b.b.a.a;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f1696a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Executor f1697b = new ExecutorC0027a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Executor f1698c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f1699d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private c f1700e;

    /* JADX INFO: renamed from: b.b.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    static class ExecutorC0027a implements Executor {
        ExecutorC0027a() {
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
        this.f1700e = bVar;
        this.f1699d = bVar;
    }

    public static a d() {
        if (f1696a != null) {
            return f1696a;
        }
        synchronized (a.class) {
            if (f1696a == null) {
                f1696a = new a();
            }
        }
        return f1696a;
    }

    @Override // b.b.a.a.c
    public void a(Runnable runnable) {
        this.f1699d.a(runnable);
    }

    @Override // b.b.a.a.c
    public boolean b() {
        return this.f1699d.b();
    }

    @Override // b.b.a.a.c
    public void c(Runnable runnable) {
        this.f1699d.c(runnable);
    }
}
