package s;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile a f7859a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Executor f7860b = new ExecutorC0115a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Executor f7861c = new b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f7862d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f7863e;

    /* JADX INFO: renamed from: s.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    public static class ExecutorC0115a implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.e().c(runnable);
        }
    }

    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    public static class b implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            a.e().a(runnable);
        }
    }

    public a() {
        s.b bVar = new s.b();
        this.f7863e = bVar;
        this.f7862d = bVar;
    }

    public static Executor d() {
        return f7861c;
    }

    public static a e() {
        if (f7859a != null) {
            return f7859a;
        }
        synchronized (a.class) {
            if (f7859a == null) {
                f7859a = new a();
            }
        }
        return f7859a;
    }

    @Override // s.c
    public void a(Runnable runnable) {
        this.f7862d.a(runnable);
    }

    @Override // s.c
    public boolean b() {
        return this.f7862d.b();
    }

    @Override // s.c
    public void c(Runnable runnable) {
        this.f7862d.c(runnable);
    }
}
