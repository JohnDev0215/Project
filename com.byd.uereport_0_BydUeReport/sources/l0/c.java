package l0;

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
    public HandlerThread f6046b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Handler f6047c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f6050f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f6051g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f6052h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f6045a = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Handler.Callback f6049e = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f6048d = 0;

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i8 = message.what;
            if (i8 == 0) {
                c.this.a();
                return true;
            }
            if (i8 != 1) {
                return true;
            }
            c.this.b((Runnable) message.obj);
            return true;
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ Callable f6054d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Handler f6055e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ d f6056f;

        /* JADX INFO: compiled from: SelfDestructiveThread.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            public final /* synthetic */ Object f6058d;

            public a(Object obj) {
                this.f6058d = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f6056f.a(this.f6058d);
            }
        }

        public b(Callable callable, Handler handler, d dVar) {
            this.f6054d = callable;
            this.f6055e = handler;
            this.f6056f = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f6054d.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f6055e.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: l0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public class RunnableC0078c implements Runnable {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ AtomicReference f6060d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final /* synthetic */ Callable f6061e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final /* synthetic */ ReentrantLock f6062f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final /* synthetic */ AtomicBoolean f6063g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public final /* synthetic */ Condition f6064h;

        public RunnableC0078c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f6060d = atomicReference;
            this.f6061e = callable;
            this.f6062f = reentrantLock;
            this.f6063g = atomicBoolean;
            this.f6064h = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f6060d.set(this.f6061e.call());
            } catch (Exception unused) {
            }
            this.f6062f.lock();
            try {
                this.f6063g.set(false);
                this.f6064h.signal();
            } finally {
                this.f6062f.unlock();
            }
        }
    }

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public interface d<T> {
        void a(T t7);
    }

    public c(String str, int i8, int i9) {
        this.f6052h = str;
        this.f6051g = i8;
        this.f6050f = i9;
    }

    public void a() {
        synchronized (this.f6045a) {
            if (this.f6047c.hasMessages(1)) {
                return;
            }
            this.f6046b.quit();
            this.f6046b = null;
            this.f6047c = null;
        }
    }

    public void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f6045a) {
            this.f6047c.removeMessages(0);
            Handler handler = this.f6047c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f6050f);
        }
    }

    public final void c(Runnable runnable) {
        synchronized (this.f6045a) {
            if (this.f6046b == null) {
                HandlerThread handlerThread = new HandlerThread(this.f6052h, this.f6051g);
                this.f6046b = handlerThread;
                handlerThread.start();
                this.f6047c = new Handler(this.f6046b.getLooper(), this.f6049e);
                this.f6048d++;
            }
            this.f6047c.removeMessages(0);
            Handler handler = this.f6047c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    public <T> void d(Callable<T> callable, d<T> dVar) {
        c(new b(callable, new Handler(), dVar));
    }

    public <T> T e(Callable<T> callable, int i8) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition conditionNewCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        c(new RunnableC0078c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                return (T) atomicReference.get();
            }
            long nanos = TimeUnit.MILLISECONDS.toNanos(i8);
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
