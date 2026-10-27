package e.a.l.b;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import e.a.j;
import io.reactivex.disposables.c;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: HandlerScheduler.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Handler f4918b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f4919c;

    /* JADX INFO: compiled from: HandlerScheduler.java */
    private static final class a extends j.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Handler f4920b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f4921c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private volatile boolean f4922d;

        a(Handler handler, boolean z) {
            this.f4920b = handler;
            this.f4921c = z;
        }

        @Override // e.a.j.b
        @SuppressLint({"NewApi"})
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            Objects.requireNonNull(runnable, "run == null");
            Objects.requireNonNull(timeUnit, "unit == null");
            if (this.f4922d) {
                return c.a();
            }
            RunnableC0088b runnableC0088b = new RunnableC0088b(this.f4920b, e.a.o.a.m(runnable));
            Message messageObtain = Message.obtain(this.f4920b, runnableC0088b);
            messageObtain.obj = this;
            if (this.f4921c) {
                messageObtain.setAsynchronous(true);
            }
            this.f4920b.sendMessageDelayed(messageObtain, timeUnit.toMillis(j));
            if (!this.f4922d) {
                return runnableC0088b;
            }
            this.f4920b.removeCallbacks(runnableC0088b);
            return c.a();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f4922d = true;
            this.f4920b.removeCallbacksAndMessages(this);
        }
    }

    /* JADX INFO: renamed from: e.a.l.b.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HandlerScheduler.java */
    private static final class RunnableC0088b implements Runnable, io.reactivex.disposables.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Handler f4923b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Runnable f4924c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private volatile boolean f4925d;

        RunnableC0088b(Handler handler, Runnable runnable) {
            this.f4923b = handler;
            this.f4924c = runnable;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f4923b.removeCallbacks(this);
            this.f4925d = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f4924c.run();
            } catch (Throwable th) {
                e.a.o.a.k(th);
            }
        }
    }

    b(Handler handler, boolean z) {
        this.f4918b = handler;
        this.f4919c = z;
    }

    @Override // e.a.j
    public j.b a() {
        return new a(this.f4918b, this.f4919c);
    }

    @Override // e.a.j
    @SuppressLint({"NewApi"})
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run == null");
        Objects.requireNonNull(timeUnit, "unit == null");
        RunnableC0088b runnableC0088b = new RunnableC0088b(this.f4918b, e.a.o.a.m(runnable));
        Message messageObtain = Message.obtain(this.f4918b, runnableC0088b);
        if (this.f4919c) {
            messageObtain.setAsynchronous(true);
        }
        this.f4918b.sendMessageDelayed(messageObtain, timeUnit.toMillis(j));
        return runnableC0088b;
    }
}
