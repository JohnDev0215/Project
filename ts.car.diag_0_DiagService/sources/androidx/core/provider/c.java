package androidx.core.provider;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.GuardedBy;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @GuardedBy("mLock")
    private HandlerThread f527b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @GuardedBy("mLock")
    private Handler f528c;
    private final int f;
    private final int g;
    private final String h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f526a = new Object();
    private Handler.Callback e = new a();

    @GuardedBy("mLock")
    private int d = 0;

    /* JADX INFO: compiled from: caradapter-bug */
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

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Callable f530c;
        final /* synthetic */ Handler d;
        final /* synthetic */ d e;

        /* JADX INFO: compiled from: caradapter-bug */
        class a implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ Object f531c;

            a(Object obj) {
                this.f531c = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.e.a(this.f531c);
            }
        }

        b(c cVar, Callable callable, Handler handler, d dVar) {
            this.f530c = callable;
            this.d = handler;
            this.e = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f530c.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.d.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: androidx.core.provider.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    class RunnableC0014c implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ AtomicReference f532c;
        final /* synthetic */ Callable d;
        final /* synthetic */ ReentrantLock e;
        final /* synthetic */ AtomicBoolean f;
        final /* synthetic */ Condition g;

        RunnableC0014c(c cVar, AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f532c = atomicReference;
            this.d = callable;
            this.e = reentrantLock;
            this.f = atomicBoolean;
            this.g = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f532c.set(this.d.call());
            } catch (Exception unused) {
            }
            this.e.lock();
            try {
                this.f.set(false);
                this.g.signal();
            } finally {
                this.e.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface d<T> {
        void a(T t);
    }

    public c(String str, int i, int i2) {
        this.h = str;
        this.g = i;
        this.f = i2;
    }

    private void c(Runnable runnable) {
        synchronized (this.f526a) {
            if (this.f527b == null) {
                HandlerThread handlerThread = new HandlerThread(this.h, this.g);
                this.f527b = handlerThread;
                handlerThread.start();
                this.f528c = new Handler(this.f527b.getLooper(), this.e);
                this.d++;
            }
            this.f528c.removeMessages(0);
            this.f528c.sendMessage(this.f528c.obtainMessage(1, runnable));
        }
    }

    void a() {
        synchronized (this.f526a) {
            if (this.f528c.hasMessages(1)) {
                return;
            }
            this.f527b.quit();
            this.f527b = null;
            this.f528c = null;
        }
    }

    void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f526a) {
            this.f528c.removeMessages(0);
            this.f528c.sendMessageDelayed(this.f528c.obtainMessage(0), this.f);
        }
    }

    public <T> void d(Callable<T> callable, d<T> dVar) {
        c(new b(this, callable, new Handler(), dVar));
    }

    public <T> T e(Callable<T> callable, int i) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionNewCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        c(new RunnableC0014c(this, atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
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

    @VisibleForTesting
    public int getGeneration() {
        int i;
        synchronized (this.f526a) {
            i = this.d;
        }
        return i;
    }

    @VisibleForTesting
    public boolean isRunning() {
        boolean z;
        synchronized (this.f526a) {
            z = this.f527b != null;
        }
        return z;
    }
}
