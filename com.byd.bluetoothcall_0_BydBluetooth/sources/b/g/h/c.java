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
    private HandlerThread f2235b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f2236c;
    private final int f;
    private final int g;
    private final String h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f2234a = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Handler.Callback f2238e = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2237d = 0;

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
        final /* synthetic */ Callable f2240b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Handler f2241c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ d f2242d;

        /* JADX INFO: compiled from: SelfDestructiveThread.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Object f2244b;

            a(Object obj) {
                this.f2244b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f2242d.a(this.f2244b);
            }
        }

        b(Callable callable, Handler handler, d dVar) {
            this.f2240b = callable;
            this.f2241c = handler;
            this.f2242d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f2240b.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f2241c.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: b.g.h.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    class RunnableC0048c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ AtomicReference f2246b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Callable f2247c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ReentrantLock f2248d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ AtomicBoolean f2249e;
        final /* synthetic */ Condition f;

        RunnableC0048c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f2246b = atomicReference;
            this.f2247c = callable;
            this.f2248d = reentrantLock;
            this.f2249e = atomicBoolean;
            this.f = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f2246b.set(this.f2247c.call());
            } catch (Exception unused) {
            }
            this.f2248d.lock();
            try {
                this.f2249e.set(false);
                this.f.signal();
            } finally {
                this.f2248d.unlock();
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
        synchronized (this.f2234a) {
            if (this.f2235b == null) {
                HandlerThread handlerThread = new HandlerThread(this.h, this.g);
                this.f2235b = handlerThread;
                handlerThread.start();
                this.f2236c = new Handler(this.f2235b.getLooper(), this.f2238e);
                this.f2237d++;
            }
            this.f2236c.removeMessages(0);
            Handler handler = this.f2236c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    void a() {
        synchronized (this.f2234a) {
            if (this.f2236c.hasMessages(1)) {
                return;
            }
            this.f2235b.quit();
            this.f2235b = null;
            this.f2236c = null;
        }
    }

    void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f2234a) {
            this.f2236c.removeMessages(0);
            Handler handler = this.f2236c;
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
        c(new RunnableC0048c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
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
