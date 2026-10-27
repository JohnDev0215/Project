package b.f.j;

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
    public HandlerThread f1702b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Handler f1703c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f1706f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1707g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f1708h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1701a = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Handler.Callback f1705e = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1704d = 0;

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

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Callable f1710c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Handler f1711d;
        public final /* synthetic */ d s;

        /* JADX INFO: compiled from: SelfDestructiveThread.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public final /* synthetic */ Object f1712c;

            public a(Object obj) {
                this.f1712c = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.s.a(this.f1712c);
            }
        }

        public b(Callable callable, Handler handler, d dVar) {
            this.f1710c = callable;
            this.f1711d = handler;
            this.s = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f1710c.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f1711d.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: b.f.j.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public class RunnableC0025c implements Runnable {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ AtomicReference f1714c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Callable f1715d;
        public final /* synthetic */ ReentrantLock s;
        public final /* synthetic */ AtomicBoolean x;
        public final /* synthetic */ Condition y;

        public RunnableC0025c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f1714c = atomicReference;
            this.f1715d = callable;
            this.s = reentrantLock;
            this.x = atomicBoolean;
            this.y = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f1714c.set(this.f1715d.call());
            } catch (Exception unused) {
            }
            this.s.lock();
            try {
                this.x.set(false);
                this.y.signal();
            } finally {
                this.s.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public interface d<T> {
        void a(T t);
    }

    public c(String str, int i2, int i3) {
        this.f1708h = str;
        this.f1707g = i2;
        this.f1706f = i3;
    }

    public void a() {
        synchronized (this.f1701a) {
            if (this.f1703c.hasMessages(1)) {
                return;
            }
            this.f1702b.quit();
            this.f1702b = null;
            this.f1703c = null;
        }
    }

    public void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f1701a) {
            this.f1703c.removeMessages(0);
            Handler handler = this.f1703c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f1706f);
        }
    }

    public final void c(Runnable runnable) {
        synchronized (this.f1701a) {
            if (this.f1702b == null) {
                HandlerThread handlerThread = new HandlerThread(this.f1708h, this.f1707g);
                this.f1702b = handlerThread;
                handlerThread.start();
                this.f1703c = new Handler(this.f1702b.getLooper(), this.f1705e);
                this.f1704d++;
            }
            this.f1703c.removeMessages(0);
            Handler handler = this.f1703c;
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
        c(new RunnableC0025c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
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
