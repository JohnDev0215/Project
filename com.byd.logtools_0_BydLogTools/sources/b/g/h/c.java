package b.g.h;

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
    private HandlerThread f2437b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f2438c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f2441f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f2442g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f2443h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f2436a = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Handler.Callback f2440e = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2439d = 0;

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    class a implements Handler.Callback {
        a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                c.this.a();
                return true;
            }
            if (i != 1) {
                return true;
            }
            c.this.b((Runnable) message.obj);
            return true;
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Callable f2445b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Handler f2446c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ d f2447d;

        /* JADX INFO: compiled from: SelfDestructiveThread.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Object f2449b;

            a(Object obj) {
                this.f2449b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f2447d.a(this.f2449b);
            }
        }

        b(Callable callable, Handler handler, d dVar) {
            this.f2445b = callable;
            this.f2446c = handler;
            this.f2447d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f2445b.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f2446c.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: b.g.h.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    class RunnableC0035c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ AtomicReference f2451b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Callable f2452c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ReentrantLock f2453d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ AtomicBoolean f2454e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ Condition f2455f;

        RunnableC0035c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f2451b = atomicReference;
            this.f2452c = callable;
            this.f2453d = reentrantLock;
            this.f2454e = atomicBoolean;
            this.f2455f = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f2451b.set(this.f2452c.call());
            } catch (Exception unused) {
            }
            this.f2453d.lock();
            try {
                this.f2454e.set(false);
                this.f2455f.signal();
            } finally {
                this.f2453d.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public interface d<T> {
        void a(T t);
    }

    public c(String str, int i, int i2) {
        this.f2443h = str;
        this.f2442g = i;
        this.f2441f = i2;
    }

    private void c(Runnable runnable) {
        synchronized (this.f2436a) {
            if (this.f2437b == null) {
                HandlerThread handlerThread = new HandlerThread(this.f2443h, this.f2442g);
                this.f2437b = handlerThread;
                handlerThread.start();
                this.f2438c = new Handler(this.f2437b.getLooper(), this.f2440e);
                this.f2439d++;
            }
            this.f2438c.removeMessages(0);
            Handler handler = this.f2438c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    void a() {
        synchronized (this.f2436a) {
            if (this.f2438c.hasMessages(1)) {
                return;
            }
            this.f2437b.quit();
            this.f2437b = null;
            this.f2438c = null;
        }
    }

    void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f2436a) {
            this.f2438c.removeMessages(0);
            Handler handler = this.f2438c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f2441f);
        }
    }

    public <T> void d(Callable<T> callable, d<T> dVar) {
        c(new b(callable, new Handler(), dVar));
    }

    public <T> T e(Callable<T> callable, int i) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionNewCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        c(new RunnableC0035c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                return (T) atomicReference.get();
            }
            long nanos = TimeUnit.MILLISECONDS.toNanos(i);
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
