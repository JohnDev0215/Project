package c.g.h;

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
    private HandlerThread f2220b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f2221c;
    private final int f;
    private final int g;
    private final String h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f2219a = new Object();
    private Handler.Callback e = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2222d = 0;

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
        final /* synthetic */ Callable f2224b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Handler f2225c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ d f2226d;

        /* JADX INFO: compiled from: SelfDestructiveThread.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Object f2227b;

            a(Object obj) {
                this.f2227b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f2226d.a(this.f2227b);
            }
        }

        b(Callable callable, Handler handler, d dVar) {
            this.f2224b = callable;
            this.f2225c = handler;
            this.f2226d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f2224b.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f2225c.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: c.g.h.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    class RunnableC0061c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ AtomicReference f2229b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Callable f2230c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ReentrantLock f2231d;
        final /* synthetic */ AtomicBoolean e;
        final /* synthetic */ Condition f;

        RunnableC0061c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f2229b = atomicReference;
            this.f2230c = callable;
            this.f2231d = reentrantLock;
            this.e = atomicBoolean;
            this.f = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f2229b.set(this.f2230c.call());
            } catch (Exception unused) {
            }
            this.f2231d.lock();
            try {
                this.e.set(false);
                this.f.signal();
            } finally {
                this.f2231d.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public interface d<T> {
        void a(T t);
    }

    public c(String str, int i, int i2) {
        this.h = str;
        this.g = i;
        this.f = i2;
    }

    private void c(Runnable runnable) {
        synchronized (this.f2219a) {
            if (this.f2220b == null) {
                HandlerThread handlerThread = new HandlerThread(this.h, this.g);
                this.f2220b = handlerThread;
                handlerThread.start();
                this.f2221c = new Handler(this.f2220b.getLooper(), this.e);
                this.f2222d++;
            }
            this.f2221c.removeMessages(0);
            Handler handler = this.f2221c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    void a() {
        synchronized (this.f2219a) {
            if (this.f2221c.hasMessages(1)) {
                return;
            }
            this.f2220b.quit();
            this.f2220b = null;
            this.f2221c = null;
        }
    }

    void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f2219a) {
            this.f2221c.removeMessages(0);
            Handler handler = this.f2221c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f);
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
        c(new RunnableC0061c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
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
