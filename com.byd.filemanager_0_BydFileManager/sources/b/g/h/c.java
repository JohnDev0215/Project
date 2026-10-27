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
    private HandlerThread f1879b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f1880c;
    private final int f;
    private final int g;
    private final String h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1878a = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Handler.Callback f1882e = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1881d = 0;

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
        final /* synthetic */ Callable f1884b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Handler f1885c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ d f1886d;

        /* JADX INFO: compiled from: SelfDestructiveThread.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Object f1888b;

            a(Object obj) {
                this.f1888b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f1886d.a(this.f1888b);
            }
        }

        b(Callable callable, Handler handler, d dVar) {
            this.f1884b = callable;
            this.f1885c = handler;
            this.f1886d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f1884b.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f1885c.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: b.g.h.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    class RunnableC0032c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ AtomicReference f1890b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Callable f1891c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ReentrantLock f1892d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ AtomicBoolean f1893e;
        final /* synthetic */ Condition f;

        RunnableC0032c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f1890b = atomicReference;
            this.f1891c = callable;
            this.f1892d = reentrantLock;
            this.f1893e = atomicBoolean;
            this.f = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f1890b.set(this.f1891c.call());
            } catch (Exception unused) {
            }
            this.f1892d.lock();
            try {
                this.f1893e.set(false);
                this.f.signal();
            } finally {
                this.f1892d.unlock();
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
        synchronized (this.f1878a) {
            if (this.f1879b == null) {
                HandlerThread handlerThread = new HandlerThread(this.h, this.g);
                this.f1879b = handlerThread;
                handlerThread.start();
                this.f1880c = new Handler(this.f1879b.getLooper(), this.f1882e);
                this.f1881d++;
            }
            this.f1880c.removeMessages(0);
            Handler handler = this.f1880c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    void a() {
        synchronized (this.f1878a) {
            if (this.f1880c.hasMessages(1)) {
                return;
            }
            this.f1879b.quit();
            this.f1879b = null;
            this.f1880c = null;
        }
    }

    void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f1878a) {
            this.f1880c.removeMessages(0);
            Handler handler = this.f1880c;
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
        c(new RunnableC0032c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
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
