package e.a.j.b;

import android.os.Handler;
import android.os.Message;
import e.a.h;
import e.a.k.c;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: HandlerScheduler.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f4852b;

    /* JADX INFO: compiled from: HandlerScheduler.java */
    private static final class a extends h.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Handler f4853b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private volatile boolean f4854c;

        a(Handler handler) {
            this.f4853b = handler;
        }

        @Override // e.a.h.b
        public e.a.k.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            Objects.requireNonNull(runnable, "run == null");
            Objects.requireNonNull(timeUnit, "unit == null");
            if (this.f4854c) {
                return c.a();
            }
            RunnableC0092b runnableC0092b = new RunnableC0092b(this.f4853b, e.a.p.a.q(runnable));
            Message messageObtain = Message.obtain(this.f4853b, runnableC0092b);
            messageObtain.obj = this;
            this.f4853b.sendMessageDelayed(messageObtain, Math.max(0L, timeUnit.toMillis(j)));
            if (!this.f4854c) {
                return runnableC0092b;
            }
            this.f4853b.removeCallbacks(runnableC0092b);
            return c.a();
        }

        @Override // e.a.k.b
        public void d() {
            this.f4854c = true;
            this.f4853b.removeCallbacksAndMessages(this);
        }
    }

    /* JADX INFO: renamed from: e.a.j.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HandlerScheduler.java */
    private static final class RunnableC0092b implements Runnable, e.a.k.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Handler f4855b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Runnable f4856c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private volatile boolean f4857d;

        RunnableC0092b(Handler handler, Runnable runnable) {
            this.f4855b = handler;
            this.f4856c = runnable;
        }

        @Override // e.a.k.b
        public void d() {
            this.f4857d = true;
            this.f4855b.removeCallbacks(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f4856c.run();
            } catch (Throwable th) {
                IllegalStateException illegalStateException = new IllegalStateException("Fatal Exception thrown on Scheduler.", th);
                e.a.p.a.o(illegalStateException);
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, illegalStateException);
            }
        }
    }

    b(Handler handler) {
        this.f4852b = handler;
    }

    @Override // e.a.h
    public h.b a() {
        return new a(this.f4852b);
    }

    @Override // e.a.h
    public e.a.k.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run == null");
        Objects.requireNonNull(timeUnit, "unit == null");
        RunnableC0092b runnableC0092b = new RunnableC0092b(this.f4852b, e.a.p.a.q(runnable));
        this.f4852b.postDelayed(runnableC0092b, Math.max(0L, timeUnit.toMillis(j)));
        return runnableC0092b;
    }
}
