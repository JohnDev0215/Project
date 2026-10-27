package b.g.g;

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
    public HandlerThread f1036b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Handler f1037c;
    public final int f;
    public final int g;
    public final String h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f1035a = new Object();
    public Handler.Callback e = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1038d = 0;

    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public class a implements Handler.Callback {
        public a() {
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
    public class b implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Callable f1040b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Handler f1041c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ d f1042d;

        /* JADX INFO: compiled from: SelfDestructiveThread.java */
        public class a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public final /* synthetic */ Object f1043b;

            public a(Object obj) {
                this.f1043b = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.f1042d.a(this.f1043b);
            }
        }

        public b(Callable callable, Handler handler, d dVar) {
            this.f1040b = callable;
            this.f1041c = handler;
            this.f1042d = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object objCall;
            try {
                objCall = this.f1040b.call();
            } catch (Exception unused) {
                objCall = null;
            }
            this.f1041c.post(new a(objCall));
        }
    }

    /* JADX INFO: renamed from: b.g.g.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SelfDestructiveThread.java */
    public class RunnableC0018c implements Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ AtomicReference f1045b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ Callable f1046c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ ReentrantLock f1047d;
        public final /* synthetic */ AtomicBoolean e;
        public final /* synthetic */ Condition f;

        public RunnableC0018c(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.f1045b = atomicReference;
            this.f1046c = callable;
            this.f1047d = reentrantLock;
            this.e = atomicBoolean;
            this.f = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f1045b.set(this.f1046c.call());
            } catch (Exception unused) {
            }
            this.f1047d.lock();
            try {
                this.e.set(false);
                this.f.signal();
            } finally {
                this.f1047d.unlock();
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

    public void a() {
        synchronized (this.f1035a) {
            if (this.f1037c.hasMessages(1)) {
                return;
            }
            this.f1036b.quit();
            this.f1036b = null;
            this.f1037c = null;
        }
    }

    public void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f1035a) {
            this.f1037c.removeMessages(0);
            Handler handler = this.f1037c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f);
        }
    }

    public final void c(Runnable runnable) {
        synchronized (this.f1035a) {
            if (this.f1036b == null) {
                HandlerThread handlerThread = new HandlerThread(this.h, this.g);
                this.f1036b = handlerThread;
                handlerThread.start();
                this.f1037c = new Handler(this.f1036b.getLooper(), this.e);
                this.f1038d++;
            }
            this.f1037c.removeMessages(0);
            Handler handler = this.f1037c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
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
        c(new RunnableC0018c(atomicReference, callable, reentrantLock, atomicBoolean, conditionNewCondition));
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
