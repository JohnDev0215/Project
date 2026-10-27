package c.b.d.b;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import c.b.c;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class b extends c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f2210b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f2211c;

    public static final class a extends c.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Handler f2212a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f2213b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public volatile boolean f2214c;

        public a(Handler handler, boolean z) {
            this.f2212a = handler;
            this.f2213b = z;
        }

        @Override // c.b.c.b
        @SuppressLint({"NewApi"})
        public c.b.e.b b(Runnable runnable, long j, TimeUnit timeUnit) {
            c.b.h.a.c cVar = c.b.h.a.c.INSTANCE;
            if (timeUnit == null) {
                throw new NullPointerException("unit == null");
            }
            if (this.f2214c) {
                return cVar;
            }
            c.b.h.b.b.a(runnable, "run is null");
            RunnableC0049b runnableC0049b = new RunnableC0049b(this.f2212a, runnable);
            Message messageObtain = Message.obtain(this.f2212a, runnableC0049b);
            messageObtain.obj = this;
            if (this.f2213b) {
                messageObtain.setAsynchronous(true);
            }
            this.f2212a.sendMessageDelayed(messageObtain, timeUnit.toMillis(j));
            if (!this.f2214c) {
                return runnableC0049b;
            }
            this.f2212a.removeCallbacks(runnableC0049b);
            return cVar;
        }

        @Override // c.b.e.b
        public void d() {
            this.f2214c = true;
            this.f2212a.removeCallbacksAndMessages(this);
        }
    }

    /* JADX INFO: renamed from: c.b.d.b.b$b, reason: collision with other inner class name */
    public static final class RunnableC0049b implements Runnable, c.b.e.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Handler f2215a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Runnable f2216b;

        public RunnableC0049b(Handler handler, Runnable runnable) {
            this.f2215a = handler;
            this.f2216b = runnable;
        }

        @Override // c.b.e.b
        public void d() {
            this.f2215a.removeCallbacks(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f2216b.run();
            } catch (Throwable th) {
                c.b.j.a.c(th);
            }
        }
    }

    public b(Handler handler, boolean z) {
        this.f2210b = handler;
        this.f2211c = z;
    }

    @Override // c.b.c
    public c.b a() {
        return new a(this.f2210b, this.f2211c);
    }

    @Override // c.b.c
    @SuppressLint({"NewApi"})
    public c.b.e.b c(Runnable runnable, long j, TimeUnit timeUnit) {
        if (runnable == null) {
            throw new NullPointerException("run == null");
        }
        if (timeUnit == null) {
            throw new NullPointerException("unit == null");
        }
        c.b.h.b.b.a(runnable, "run is null");
        RunnableC0049b runnableC0049b = new RunnableC0049b(this.f2210b, runnable);
        Message messageObtain = Message.obtain(this.f2210b, runnableC0049b);
        if (this.f2211c) {
            messageObtain.setAsynchronous(true);
        }
        this.f2210b.sendMessageDelayed(messageObtain, timeUnit.toMillis(j));
        return runnableC0049b;
    }
}
