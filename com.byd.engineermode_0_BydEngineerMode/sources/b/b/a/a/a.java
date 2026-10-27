package b.b.a.a;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f1576a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Executor f1577b = new ExecutorC0031a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Executor f1578c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f1579d;
    private c e;

    /* JADX INFO: renamed from: b.b.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    static class ExecutorC0031a implements Executor {
        ExecutorC0031a() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.e().c(runnable);
        }
    }

    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    static class b implements Executor {
        b() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.e().a(runnable);
        }
    }

    private a() {
        b.b.a.a.b bVar = new b.b.a.a.b();
        this.e = bVar;
        this.f1579d = bVar;
    }

    public static Executor d() {
        return f1578c;
    }

    public static a e() {
        if (f1576a != null) {
            return f1576a;
        }
        synchronized (a.class) {
            if (f1576a == null) {
                f1576a = new a();
            }
        }
        return f1576a;
    }

    @Override // b.b.a.a.c
    public void a(Runnable runnable) {
        this.f1579d.a(runnable);
    }

    @Override // b.b.a.a.c
    public boolean b() {
        return this.f1579d.b();
    }

    @Override // b.b.a.a.c
    public void c(Runnable runnable) {
        this.f1579d.c(runnable);
    }
}
