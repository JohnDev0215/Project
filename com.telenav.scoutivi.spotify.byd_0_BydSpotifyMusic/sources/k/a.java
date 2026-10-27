package k;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile a f7180c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Executor f7181d = new ExecutorC0094a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final Executor f7182e = new b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f7183a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f7184b;

    /* JADX INFO: renamed from: k.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    static class ExecutorC0094a implements Executor {
        ExecutorC0094a() {
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
        k.b bVar = new k.b();
        this.f7184b = bVar;
        this.f7183a = bVar;
    }

    public static Executor d() {
        return f7182e;
    }

    public static a e() {
        if (f7180c != null) {
            return f7180c;
        }
        synchronized (a.class) {
            if (f7180c == null) {
                f7180c = new a();
            }
        }
        return f7180c;
    }

    @Override // k.c
    public void a(Runnable runnable) {
        this.f7183a.a(runnable);
    }

    @Override // k.c
    public boolean b() {
        return this.f7183a.b();
    }

    @Override // k.c
    public void c(Runnable runnable) {
        this.f7183a.c(runnable);
    }
}
