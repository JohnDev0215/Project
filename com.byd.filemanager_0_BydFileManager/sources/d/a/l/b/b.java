package d.a.l.b;

import android.os.Handler;
import android.os.Message;
import d.a.j;
import io.reactivex.disposables.c;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: HandlerScheduler.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f4521b;

    /* JADX INFO: compiled from: HandlerScheduler.java */
    private static final class a extends j.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Handler f4522b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private volatile boolean f4523c;

        a(Handler handler) {
            this.f4522b = handler;
        }

        @Override // d.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            Objects.requireNonNull(runnable, "run == null");
            Objects.requireNonNull(timeUnit, "unit == null");
            if (this.f4523c) {
                return c.a();
            }
            RunnableC0112b runnableC0112b = new RunnableC0112b(this.f4522b, d.a.o.a.o(runnable));
            Message messageObtain = Message.obtain(this.f4522b, runnableC0112b);
            messageObtain.obj = this;
            this.f4522b.sendMessageDelayed(messageObtain, Math.max(0L, timeUnit.toMillis(j)));
            if (!this.f4523c) {
                return runnableC0112b;
            }
            this.f4522b.removeCallbacks(runnableC0112b);
            return c.a();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f4523c = true;
            this.f4522b.removeCallbacksAndMessages(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4523c;
        }
    }

    /* JADX INFO: renamed from: d.a.l.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HandlerScheduler.java */
    private static final class RunnableC0112b implements Runnable, io.reactivex.disposables.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Handler f4524b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Runnable f4525c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private volatile boolean f4526d;

        RunnableC0112b(Handler handler, Runnable runnable) {
            this.f4524b = handler;
            this.f4525c = runnable;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f4526d = true;
            this.f4524b.removeCallbacks(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4526d;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f4525c.run();
            } catch (Throwable th) {
                IllegalStateException illegalStateException = new IllegalStateException("Fatal Exception thrown on Scheduler.", th);
                d.a.o.a.l(illegalStateException);
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, illegalStateException);
            }
        }
    }

    b(Handler handler) {
        this.f4521b = handler;
    }

    @Override // d.a.j
    public j.b a() {
        return new a(this.f4521b);
    }

    @Override // d.a.j
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run == null");
        Objects.requireNonNull(timeUnit, "unit == null");
        RunnableC0112b runnableC0112b = new RunnableC0112b(this.f4521b, d.a.o.a.o(runnable));
        this.f4521b.postDelayed(runnableC0112b, Math.max(0L, timeUnit.toMillis(j)));
        return runnableC0112b;
    }
}
