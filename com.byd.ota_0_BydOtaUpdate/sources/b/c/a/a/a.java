package b.c.a.a;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f1135a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Executor f1136b = new ExecutorC0011a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Executor f1137c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f1138d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f1139e;

    /* JADX INFO: renamed from: b.c.a.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    public static class ExecutorC0011a implements Executor {
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
        this.f1139e = bVar;
        this.f1138d = bVar;
    }

    public static a d() {
        if (f1135a != null) {
            return f1135a;
        }
        synchronized (a.class) {
            if (f1135a == null) {
                f1135a = new a();
            }
        }
        return f1135a;
    }

    @Override // b.c.a.a.c
    public void a(Runnable runnable) {
        this.f1138d.a(runnable);
    }

    @Override // b.c.a.a.c
    public boolean b() {
        return this.f1138d.b();
    }

    @Override // b.c.a.a.c
    public void c(Runnable runnable) {
        this.f1138d.c(runnable);
    }
}
