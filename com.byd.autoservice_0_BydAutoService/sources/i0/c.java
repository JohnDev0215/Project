package i0;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: SelfDestructiveThread.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public HandlerThread f3252b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Handler f3253c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f3256f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3257g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f3258h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f3251a = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Handler.Callback f3255e = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3254d = 0;

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i3 = message.what;
            if (i3 == 0) {
                c.this.a();
                return true;
            }
            if (i3 != 1) {
                return true;
            }
            c.this.b((Runnable) message.obj);
            return true;
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Callable f3260b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Handler f3261c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ d f3262d;

        /* JADX INFO: compiled from: SelfDestructiveThread.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Object f3264b;

            public a(Object obj) {
                this.f3264b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f3262d.a(this.f3264b);
            }
        }

        public b(Callable callable, Handler handler, d dVar) {
            this.f3260b = callable;
            this.f3261c = handler;
            this.f3262d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f3260b.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f3261c.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: i0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public class RunnableC0019c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ AtomicReference f3266b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Callable f3267c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ReentrantLock f3268d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ AtomicBoolean f3269e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ Condition f3270f;

        public RunnableC0019c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f3266b = atomicReference;
            this.f3267c = callable;
            this.f3268d = reentrantLock;
            this.f3269e = atomicBoolean;
            this.f3270f = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f3266b.set(this.f3267c.call());
            } catch (Exception unused) {
            }
            this.f3268d.lock();
            try {
                this.f3269e.set(false);
                this.f3270f.signal();
            } finally {
                this.f3268d.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public interface d<T> {
        void a(T t3);
    }

    public c(String str, int i3, int i4) {
        this.f3258h = str;
        this.f3257g = i3;
        this.f3256f = i4;
    }

    public void a() {
        synchronized (this.f3251a) {
            if (this.f3253c.hasMessages(1)) {
                return;
            }
            this.f3252b.quit();
            this.f3252b = null;
            this.f3253c = null;
        }
    }

    public void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f3251a) {
            this.f3253c.removeMessages(0);
            Handler handler = this.f3253c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f3256f);
        }
    }

    public final void c(Runnable runnable) {
        synchronized (this.f3251a) {
            if (this.f3252b == null) {
                HandlerThread handlerThread = new HandlerThread(this.f3258h, this.f3257g);
                this.f3252b = handlerThread;
                handlerThread.start();
                this.f3253c = new Handler(this.f3252b.getLooper(), this.f3255e);
                this.f3254d++;
            }
            this.f3253c.removeMessages(0);
            Handler handler = this.f3253c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    public <T> void d(Callable<T> callable, d<T> dVar) {
        c(new b(callable, new Handler(), dVar));
    }

    public <T> T e(Callable<T> callable, int i3) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionNewCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        c(new RunnableC0019c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                return (T) atomicReference.get();
            }
            long nanos = TimeUnit.MILLISECONDS.toNanos(i3);
            do {
                try {
                    nanos = conditionNewCondition.awaitNanos(nanos);
                } catch (InterruptedException unused) {
                }
                if (!atomicBoolean.get()) {
                    return (T) atomicReference.get();
                }
            } while (nanos > 0);
            throw new InterruptedException("timeout");
        } finally {
            reentrantLock.unlock();
        }
    }
}
