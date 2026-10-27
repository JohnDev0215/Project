package s4;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import q4.d;
import t4.c;

/* JADX INFO: compiled from: HandlerScheduler.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f9512b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f9513c;

    /* JADX INFO: compiled from: HandlerScheduler.java */
    private static final class a extends d.b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Handler f9514e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final boolean f9515g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private volatile boolean f9516h;

        a(Handler handler, boolean z6) {
            this.f9514e = handler;
            this.f9515g = z6;
        }

        @Override // q4.d.b
        @SuppressLint({"NewApi"})
        public t4.b b(Runnable runnable, long j7, TimeUnit timeUnit) {
            Objects.requireNonNull(runnable, "run == null");
            Objects.requireNonNull(timeUnit, "unit == null");
            if (this.f9516h) {
                return c.a();
            }
            RunnableC0149b runnableC0149b = new RunnableC0149b(this.f9514e, d5.a.m(runnable));
            Message messageObtain = Message.obtain(this.f9514e, runnableC0149b);
            messageObtain.obj = this;
            if (this.f9515g) {
                messageObtain.setAsynchronous(true);
            }
            this.f9514e.sendMessageDelayed(messageObtain, timeUnit.toMillis(j7));
            if (!this.f9516h) {
                return runnableC0149b;
            }
            this.f9514e.removeCallbacks(runnableC0149b);
            return c.a();
        }

        @Override // t4.b
        public void dispose() {
            this.f9516h = true;
            this.f9514e.removeCallbacksAndMessages(this);
        }

        @Override // t4.b
        public boolean isDisposed() {
            return this.f9516h;
        }
    }

    /* JADX INFO: renamed from: s4.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HandlerScheduler.java */
    private static final class RunnableC0149b implements Runnable, t4.b {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final Handler f9517e;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final Runnable f9518g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private volatile boolean f9519h;

        RunnableC0149b(Handler handler, Runnable runnable) {
            this.f9517e = handler;
            this.f9518g = runnable;
        }

        @Override // t4.b
        public void dispose() {
            this.f9517e.removeCallbacks(this);
            this.f9519h = true;
        }

        @Override // t4.b
        public boolean isDisposed() {
            return this.f9519h;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f9518g.run();
            } catch (Throwable th) {
                d5.a.k(th);
            }
        }
    }

    b(Handler handler, boolean z6) {
        this.f9512b = handler;
        this.f9513c = z6;
    }

    @Override // q4.d
    public d.b a() {
        return new a(this.f9512b, this.f9513c);
    }

    @Override // q4.d
    @SuppressLint({"NewApi"})
    public t4.b c(Runnable runnable, long j7, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run == null");
        Objects.requireNonNull(timeUnit, "unit == null");
        RunnableC0149b runnableC0149b = new RunnableC0149b(this.f9512b, d5.a.m(runnable));
        Message messageObtain = Message.obtain(this.f9512b, runnableC0149b);
        if (this.f9513c) {
            messageObtain.setAsynchronous(true);
        }
        this.f9512b.sendMessageDelayed(messageObtain, timeUnit.toMillis(j7));
        return runnableC0149b;
    }
}
