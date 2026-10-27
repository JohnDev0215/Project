package o1;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/* JADX INFO: compiled from: LottieTask.java */
/* JADX INFO: loaded from: classes.dex */
public class n<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Executor f6856a = Executors.newCachedThreadPool();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Set<i<T>> f6857b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Set<i<Throwable>> f6858c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Handler f6859d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile m<T> f6860e;

    /* JADX INFO: compiled from: LottieTask.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (n.this.f6860e == null) {
                return;
            }
            m mVar = n.this.f6860e;
            if (mVar.b() != null) {
                n.this.i(mVar.b());
            } else {
                n.this.g(mVar.a());
            }
        }
    }

    /* JADX INFO: compiled from: LottieTask.java */
    public class b extends FutureTask<m<T>> {
        public b(Callable<m<T>> callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask
        public void done() {
            if (isCancelled()) {
                return;
            }
            try {
                n.this.l(get());
            } catch (InterruptedException | ExecutionException e8) {
                n.this.l(new m(e8));
            }
        }
    }

    public n(Callable<m<T>> callable) {
        this(callable, false);
    }

    public synchronized n<T> e(i<Throwable> iVar) {
        if (this.f6860e != null && this.f6860e.a() != null) {
            iVar.a(this.f6860e.a());
        }
        this.f6858c.add(iVar);
        return this;
    }

    public synchronized n<T> f(i<T> iVar) {
        if (this.f6860e != null && this.f6860e.b() != null) {
            iVar.a(this.f6860e.b());
        }
        this.f6857b.add(iVar);
        return this;
    }

    public final synchronized void g(Throwable th) {
        ArrayList arrayList = new ArrayList(this.f6858c);
        if (arrayList.isEmpty()) {
            a2.d.d("Lottie encountered an error but no failure listener was added:", th);
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((i) it.next()).a(th);
        }
    }

    public final void h() {
        this.f6859d.post(new a());
    }

    public final synchronized void i(T t7) {
        Iterator it = new ArrayList(this.f6857b).iterator();
        while (it.hasNext()) {
            ((i) it.next()).a(t7);
        }
    }

    public synchronized n<T> j(i<Throwable> iVar) {
        this.f6858c.remove(iVar);
        return this;
    }

    public synchronized n<T> k(i<T> iVar) {
        this.f6857b.remove(iVar);
        return this;
    }

    public final void l(m<T> mVar) {
        if (this.f6860e != null) {
            throw new IllegalStateException("A task may only be set once.");
        }
        this.f6860e = mVar;
        h();
    }

    public n(Callable<m<T>> callable, boolean z7) {
        this.f6857b = new LinkedHashSet(1);
        this.f6858c = new LinkedHashSet(1);
        this.f6859d = new Handler(Looper.getMainLooper());
        this.f6860e = null;
        if (!z7) {
            f6856a.execute(new b(callable));
            return;
        }
        try {
            l(callable.call());
        } catch (Throwable th) {
            l(new m<>(th));
        }
    }
}
