package c.a.i.b;

import android.os.Handler;
import android.os.Message;
import c.a.h;
import c.a.j.c;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
final class b extends h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f1803b;

    private static final class a extends h.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Handler f1804b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private volatile boolean f1805c;

        a(Handler handler) {
            this.f1804b = handler;
        }

        @Override // c.a.h.b
        public c.a.j.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            Objects.requireNonNull(runnable, "run == null");
            Objects.requireNonNull(timeUnit, "unit == null");
            if (this.f1805c) {
                return c.a();
            }
            RunnableC0057b runnableC0057b = new RunnableC0057b(this.f1804b, c.a.n.a.o(runnable));
            Message messageObtain = Message.obtain(this.f1804b, runnableC0057b);
            messageObtain.obj = this;
            this.f1804b.sendMessageDelayed(messageObtain, Math.max(0L, timeUnit.toMillis(j)));
            if (!this.f1805c) {
                return runnableC0057b;
            }
            this.f1804b.removeCallbacks(runnableC0057b);
            return c.a();
        }

        @Override // c.a.j.b
        public void e() {
            this.f1805c = true;
            this.f1804b.removeCallbacksAndMessages(this);
        }

        @Override // c.a.j.b
        public boolean g() {
            return this.f1805c;
        }
    }

    /* JADX INFO: renamed from: c.a.i.b.b$b, reason: collision with other inner class name */
    private static final class RunnableC0057b implements Runnable, c.a.j.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Handler f1806b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Runnable f1807c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private volatile boolean f1808d;

        RunnableC0057b(Handler handler, Runnable runnable) {
            this.f1806b = handler;
            this.f1807c = runnable;
        }

        @Override // c.a.j.b
        public void e() {
            this.f1808d = true;
            this.f1806b.removeCallbacks(this);
        }

        @Override // c.a.j.b
        public boolean g() {
            return this.f1808d;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f1807c.run();
            } catch (Throwable th) {
                IllegalStateException illegalStateException = new IllegalStateException("Fatal Exception thrown on Scheduler.", th);
                c.a.n.a.l(illegalStateException);
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, illegalStateException);
            }
        }
    }

    b(Handler handler) {
        this.f1803b = handler;
    }

    @Override // c.a.h
    public h.b a() {
        return new a(this.f1803b);
    }

    @Override // c.a.h
    public c.a.j.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run == null");
        Objects.requireNonNull(timeUnit, "unit == null");
        RunnableC0057b runnableC0057b = new RunnableC0057b(this.f1803b, c.a.n.a.o(runnable));
        this.f1803b.postDelayed(runnableC0057b, Math.max(0L, timeUnit.toMillis(j)));
        return runnableC0057b;
    }
}
