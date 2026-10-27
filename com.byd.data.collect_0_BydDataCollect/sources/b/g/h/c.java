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
    private HandlerThread f2592b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f2593c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f2596f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f2597g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f2598h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f2591a = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Handler.Callback f2595e = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2594d = 0;

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    class a implements Handler.Callback {
        a() {
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
    class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Callable f2600b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Handler f2601c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ d f2602d;

        /* JADX INFO: compiled from: SelfDestructiveThread.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Object f2604b;

            a(Object obj) {
                this.f2604b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f2602d.a(this.f2604b);
            }
        }

        b(Callable callable, Handler handler, d dVar) {
            this.f2600b = callable;
            this.f2601c = handler;
            this.f2602d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f2600b.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f2601c.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: b.g.h.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    class RunnableC0041c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ AtomicReference f2606b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Callable f2607c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ReentrantLock f2608d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ AtomicBoolean f2609e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ Condition f2610f;

        RunnableC0041c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f2606b = atomicReference;
            this.f2607c = callable;
            this.f2608d = reentrantLock;
            this.f2609e = atomicBoolean;
            this.f2610f = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f2606b.set(this.f2607c.call());
            } catch (Exception unused) {
            }
            this.f2608d.lock();
            try {
                this.f2609e.set(false);
                this.f2610f.signal();
            } finally {
                this.f2608d.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public interface d<T> {
        void a(T t);
    }

    public c(String str, int i2, int i3) {
        this.f2598h = str;
        this.f2597g = i2;
        this.f2596f = i3;
    }

    private void c(Runnable runnable) {
        synchronized (this.f2591a) {
            if (this.f2592b == null) {
                HandlerThread handlerThread = new HandlerThread(this.f2598h, this.f2597g);
                this.f2592b = handlerThread;
                handlerThread.start();
                this.f2593c = new Handler(this.f2592b.getLooper(), this.f2595e);
                this.f2594d++;
            }
            this.f2593c.removeMessages(0);
            Handler handler = this.f2593c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    void a() {
        synchronized (this.f2591a) {
            if (this.f2593c.hasMessages(1)) {
                return;
            }
            this.f2592b.quit();
            this.f2592b = null;
            this.f2593c = null;
        }
    }

    void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f2591a) {
            this.f2593c.removeMessages(0);
            Handler handler = this.f2593c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f2596f);
        }
    }

    public <T> void d(Callable<T> callable, d<T> dVar) {
        c(new b(callable, new Handler(), dVar));
    }

    public <T> T e(Callable<T> callable, int i2) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionNewCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        c(new RunnableC0041c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
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
