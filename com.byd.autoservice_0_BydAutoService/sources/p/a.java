package p;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f4459a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Executor f4460b = new ExecutorC0046a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Executor f4461c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f4462d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f4463e;

    /* JADX INFO: renamed from: p.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    public static class ExecutorC0046a implements Executor {
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
        p.b bVar = new p.b();
        this.f4463e = bVar;
        this.f4462d = bVar;
    }

    public static a d() {
        if (f4459a != null) {
            return f4459a;
        }
        synchronized (a.class) {
            if (f4459a == null) {
                f4459a = new a();
            }
        }
        return f4459a;
    }

    @Override // p.c
    public void a(Runnable runnable) {
        this.f4462d.a(runnable);
    }

    @Override // p.c
    public boolean b() {
        return this.f4462d.b();
    }

    @Override // p.c
    public void c(Runnable runnable) {
        this.f4462d.c(runnable);
    }
}
