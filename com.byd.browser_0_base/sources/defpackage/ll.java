package defpackage;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;
import org.chromium.base.Callback;
import org.chromium.base.CollectionUtil;

/* JADX INFO: compiled from: CallbackController.java */
/* JADX INFO: loaded from: classes.dex */
public final class ll {
    public ArrayList<WeakReference<a>> a = new ArrayList<>();
    public final ReentrantLock b = new ReentrantLock(true);

    /* JADX INFO: compiled from: CallbackController.java */
    public interface a {
        void cancel();
    }

    /* JADX INFO: compiled from: CallbackController.java */
    public class b<T> implements a, Callback<T> {
        public Callback<T> e;

        public b(Callback callback) {
            this.e = callback;
        }

        @Override // ll.a
        public final void cancel() {
            this.e = null;
        }

        @Override // org.chromium.base.Callback
        public final void onResult(T t) {
            ReentrantLock reentrantLock = ll.this.b;
            reentrantLock.lock();
            try {
                Callback<T> callback = this.e;
                if (callback != null) {
                    callback.onResult(t);
                }
                reentrantLock.unlock();
            } catch (Throwable th) {
                try {
                    reentrantLock.unlock();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
    }

    /* JADX INFO: compiled from: CallbackController.java */
    public class c implements a, Runnable {
        public Runnable e;

        public c(Runnable runnable) {
            this.e = runnable;
        }

        @Override // ll.a
        public final void cancel() {
            this.e = null;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ReentrantLock reentrantLock = ll.this.b;
            reentrantLock.lock();
            try {
                Runnable runnable = this.e;
                if (runnable != null) {
                    runnable.run();
                }
                reentrantLock.unlock();
            } catch (Throwable th) {
                try {
                    reentrantLock.unlock();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
    }

    public final void a() {
        ReentrantLock reentrantLock = this.b;
        reentrantLock.lock();
        try {
            ArrayList<WeakReference<a>> arrayList = this.a;
            if (arrayList == null) {
                throw new IllegalStateException("This CallbackController has already been destroyed.");
            }
            Iterator it = CollectionUtil.f(arrayList).iterator();
            while (it.hasNext()) {
                ((a) it.next()).cancel();
            }
            this.a = null;
            reentrantLock.unlock();
        } catch (Throwable th) {
            try {
                reentrantLock.unlock();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final b b(Callback callback) {
        ReentrantLock reentrantLock = this.b;
        reentrantLock.lock();
        try {
            ArrayList<WeakReference<a>> arrayList = this.a;
            if (arrayList == null) {
                throw new IllegalStateException("This CallbackController has already been destroyed.");
            }
            b bVar = new b(callback);
            arrayList.add(new WeakReference<>(bVar));
            reentrantLock.unlock();
            return bVar;
        } catch (Throwable th) {
            try {
                reentrantLock.unlock();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    public final c c(Runnable runnable) {
        ReentrantLock reentrantLock = this.b;
        reentrantLock.lock();
        try {
            ArrayList<WeakReference<a>> arrayList = this.a;
            if (arrayList == null) {
                throw new IllegalStateException("This CallbackController has already been destroyed.");
            }
            c cVar = new c(runnable);
            arrayList.add(new WeakReference<>(cVar));
            reentrantLock.unlock();
            return cVar;
        } catch (Throwable th) {
            try {
                reentrantLock.unlock();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
