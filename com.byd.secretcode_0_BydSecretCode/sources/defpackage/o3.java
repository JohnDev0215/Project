package defpackage;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public class o3 extends q3 {
    public static volatile o3 a;
    public static final Executor b = new a();
    public static final Executor c = new b();
    public q3 d;
    public q3 e;

    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    public static class a implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            o3.d().c(runnable);
        }
    }

    /* JADX INFO: compiled from: ArchTaskExecutor.java */
    public static class b implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            o3.d().a(runnable);
        }
    }

    public o3() {
        p3 p3Var = new p3();
        this.e = p3Var;
        this.d = p3Var;
    }

    public static o3 d() {
        if (a != null) {
            return a;
        }
        synchronized (o3.class) {
            if (a == null) {
                a = new o3();
            }
        }
        return a;
    }

    @Override // defpackage.q3
    public void a(Runnable runnable) {
        this.d.a(runnable);
    }

    @Override // defpackage.q3
    public boolean b() {
        return this.d.b();
    }

    @Override // defpackage.q3
    public void c(Runnable runnable) {
        this.d.c(runnable);
    }
}
