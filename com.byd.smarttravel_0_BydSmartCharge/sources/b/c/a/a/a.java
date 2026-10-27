package b.c.a.a;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f847a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Executor f848b = new ExecutorC0007a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Executor f849c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f850d;
    public c e;

    /* JADX INFO: renamed from: b.c.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    public static class ExecutorC0007a implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.d().c(runnable);
        }
    }

    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    public static class b implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.d().a(runnable);
        }
    }

    public a() {
        b.c.a.a.b bVar = new b.c.a.a.b();
        this.e = bVar;
        this.f850d = bVar;
    }

    public static a d() {
        if (f847a != null) {
            return f847a;
        }
        synchronized (a.class) {
            if (f847a == null) {
                f847a = new a();
            }
        }
        return f847a;
    }

    @Override // b.c.a.a.c
    public void a(Runnable runnable) {
        this.f850d.a(runnable);
    }

    @Override // b.c.a.a.c
    public boolean b() {
        return this.f850d.b();
    }

    @Override // b.c.a.a.c
    public void c(Runnable runnable) {
        this.f850d.c(runnable);
    }
}
