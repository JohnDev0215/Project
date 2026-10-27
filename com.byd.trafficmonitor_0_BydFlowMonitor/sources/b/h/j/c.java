package b.h.j;

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
    public HandlerThread f2510b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Handler f2511c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2514f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f2515g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f2516h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2509a = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Handler.Callback f2513e = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2512d = 0;

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 0) {
                c.this.a();
                return true;
            }
            if (i2 != 1) {
                return true;
            }
            c.this.b((Runnable) message.obj);
            return true;
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Callable f2518b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Handler f2519c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ d f2520d;

        /* JADX INFO: compiled from: SelfDestructiveThread.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Object f2522b;

            public a(Object obj) {
                this.f2522b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f2520d.a(this.f2522b);
            }
        }

        public b(Callable callable, Handler handler, d dVar) {
            this.f2518b = callable;
            this.f2519c = handler;
            this.f2520d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f2518b.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f2519c.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: b.h.j.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public class RunnableC0038c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ AtomicReference f2524b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Callable f2525c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ReentrantLock f2526d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ AtomicBoolean f2527e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ Condition f2528f;

        public RunnableC0038c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f2524b = atomicReference;
            this.f2525c = callable;
            this.f2526d = reentrantLock;
            this.f2527e = atomicBoolean;
            this.f2528f = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f2524b.set(this.f2525c.call());
            } catch (Exception unused) {
            }
            this.f2526d.lock();
            try {
                this.f2527e.set(false);
                this.f2528f.signal();
            } finally {
                this.f2526d.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public interface d<T> {
        void a(T t);
    }

    public c(String str, int i2, int i3) {
        this.f2516h = str;
        this.f2515g = i2;
        this.f2514f = i3;
    }

    public void a() {
        synchronized (this.f2509a) {
            if (this.f2511c.hasMessages(1)) {
                return;
            }
            this.f2510b.quit();
            this.f2510b = null;
            this.f2511c = null;
        }
    }

    public void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f2509a) {
            this.f2511c.removeMessages(0);
            Handler handler = this.f2511c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f2514f);
        }
    }

    public final void c(Runnable runnable) {
        synchronized (this.f2509a) {
            if (this.f2510b == null) {
                HandlerThread handlerThread = new HandlerThread(this.f2516h, this.f2515g);
                this.f2510b = handlerThread;
                handlerThread.start();
                this.f2511c = new Handler(this.f2510b.getLooper(), this.f2513e);
                this.f2512d++;
            }
            this.f2511c.removeMessages(0);
            Handler handler = this.f2511c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    public <T> void d(Callable<T> callable, d<T> dVar) {
        c(new b(callable, new Handler(), dVar));
    }

    public <T> T e(Callable<T> callable, int i2) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionNewCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        c(new RunnableC0038c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                return (T) atomicReference.get();
            }
            long nanos = TimeUnit.MILLISECONDS.toNanos(i2);
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
