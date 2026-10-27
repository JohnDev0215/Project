package a.g.h;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HandlerThread f317b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f318c;
    private final int f;
    private final int g;
    private final String h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f316a = new Object();
    private Handler.Callback e = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f319d = 0;

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

    class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Callable f321b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Handler f322c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ d f323d;

        class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ Object f324b;

            a(Object obj) {
                this.f324b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f323d.a(this.f324b);
            }
        }

        b(Callable callable, Handler handler, d dVar) {
            this.f321b = callable;
            this.f322c = handler;
            this.f323d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f321b.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f322c.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: a.g.h.c$c, reason: collision with other inner class name */
    class RunnableC0008c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ AtomicReference f326b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Callable f327c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ReentrantLock f328d;
        final /* synthetic */ AtomicBoolean e;
        final /* synthetic */ Condition f;

        RunnableC0008c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f326b = atomicReference;
            this.f327c = callable;
            this.f328d = reentrantLock;
            this.e = atomicBoolean;
            this.f = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f326b.set(this.f327c.call());
            } catch (Exception unused) {
            }
            this.f328d.lock();
            try {
                this.e.set(false);
                this.f.signal();
            } finally {
                this.f328d.unlock();
            }
        }
    }

    public interface d<T> {
        void a(T t);
    }

    public c(String str, int i, int i2) {
        this.h = str;
        this.g = i;
        this.f = i2;
    }

    private void c(Runnable runnable) {
        synchronized (this.f316a) {
            if (this.f317b == null) {
                HandlerThread handlerThread = new HandlerThread(this.h, this.g);
                this.f317b = handlerThread;
                handlerThread.start();
                this.f318c = new Handler(this.f317b.getLooper(), this.e);
                this.f319d++;
            }
            this.f318c.removeMessages(0);
            Handler handler = this.f318c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    void a() {
        synchronized (this.f316a) {
            if (this.f318c.hasMessages(1)) {
                return;
            }
            this.f317b.quit();
            this.f317b = null;
            this.f318c = null;
        }
    }

    void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f316a) {
            this.f318c.removeMessages(0);
            Handler handler = this.f318c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f);
        }
    }

    public <T> void d(Callable<T> callable, d<T> dVar) {
        c(new b(callable, new Handler(), dVar));
    }

    public <T> T e(Callable<T> callable, int i) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionNewCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        c(new RunnableC0008c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
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
