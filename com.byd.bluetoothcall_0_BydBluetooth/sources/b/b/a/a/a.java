package b.b.a.a;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f2078a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Executor f2079b = new ExecutorC0040a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Executor f2080c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private c f2081d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private c f2082e;

    /* JADX INFO: renamed from: b.b.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    static class ExecutorC0040a implements Executor {
        ExecutorC0040a() {
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
        this.f2082e = bVar;
        this.f2081d = bVar;
    }

    public static a d() {
        if (f2078a != null) {
            return f2078a;
        }
        synchronized (a.class) {
            if (f2078a == null) {
                f2078a = new a();
            }
        }
        return f2078a;
    }

    @Override // b.b.a.a.c
    public void a(Runnable runnable) {
        this.f2081d.a(runnable);
    }

    @Override // b.b.a.a.c
    public boolean b() {
        return this.f2081d.b();
    }

    @Override // b.b.a.a.c
    public void c(Runnable runnable) {
        this.f2081d.c(runnable);
    }
}
