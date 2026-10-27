package d.a.l.c;

import android.os.Handler;
import android.os.Message;
import d.a.j;
import io.reactivex.disposables.c;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: HandlerScheduler.java */
/* JADX INFO: loaded from: classes.dex */
final class b extends j {

    /* JADX INFO: renamed from: b */
    private final Handler f4229b;

    /* JADX INFO: compiled from: HandlerScheduler.java */
    private static final class a extends j.b {

        /* JADX INFO: renamed from: b */
        private final Handler f4230b;

        /* JADX INFO: renamed from: c */
        private volatile boolean f4231c;

        a(Handler handler) {
            this.f4230b = handler;
        }

        @Override // d.a.j.b
        public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
            Objects.requireNonNull(runnable, "run == null");
            Objects.requireNonNull(timeUnit, "unit == null");
            if (this.f4231c) {
                return c.a();
            }
            RunnableC0108b runnableC0108b = new RunnableC0108b(this.f4230b, d.a.p.a.n(runnable));
            Message messageObtain = Message.obtain(this.f4230b, runnableC0108b);
            messageObtain.obj = this;
            this.f4230b.sendMessageDelayed(messageObtain, timeUnit.toMillis(j));
            if (!this.f4231c) {
                return runnableC0108b;
            }
            this.f4230b.removeCallbacks(runnableC0108b);
            return c.a();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f4231c = true;
            this.f4230b.removeCallbacksAndMessages(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4231c;
        }
    }

    /* JADX INFO: renamed from: d.a.l.c.b$b */
    /* JADX INFO: compiled from: HandlerScheduler.java */
    private static final class RunnableC0108b implements Runnable, io.reactivex.disposables.b {

        /* JADX INFO: renamed from: b */
        private final Handler f4232b;

        /* JADX INFO: renamed from: c */
        private final Runnable f4233c;

        /* JADX INFO: renamed from: d */
        private volatile boolean f4234d;

        RunnableC0108b(Handler handler, Runnable runnable) {
            this.f4232b = handler;
            this.f4233c = runnable;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f4234d = true;
            this.f4232b.removeCallbacks(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f4234d;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f4233c.run();
            } catch (Throwable th) {
                d.a.p.a.l(th);
            }
        }
    }

    b(Handler handler) {
        this.f4229b = handler;
    }

    @Override // d.a.j
    public j.b a() {
        return new a(this.f4229b);
    }

    @Override // d.a.j
    public io.reactivex.disposables.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        Objects.requireNonNull(runnable, "run == null");
        Objects.requireNonNull(timeUnit, "unit == null");
        RunnableC0108b runnableC0108b = new RunnableC0108b(this.f4229b, d.a.p.a.n(runnable));
        this.f4229b.postDelayed(runnableC0108b, timeUnit.toMillis(j));
        return runnableC0108b;
    }
}
