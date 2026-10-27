package com.airbnb.lottie;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes.dex */
public class m<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Executor f714a = Executors.newCachedThreadPool();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Set<h<T>> f715b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Set<h<Throwable>> f716c;
    private final Handler d;

    @Nullable
    private volatile l<T> e;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (m.this.e == null) {
                return;
            }
            l lVar = m.this.e;
            if (lVar.b() != null) {
                m.this.i(lVar.b());
            } else {
                m.this.g(lVar.a());
            }
        }
    }

    private class b extends FutureTask<l<T>> {
        b(Callable<l<T>> callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask
        protected void done() {
            if (isCancelled()) {
                return;
            }
            try {
                m.this.l(get());
            } catch (InterruptedException | ExecutionException e) {
                m.this.l(new l(e));
            }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public m(Callable<l<T>> callable) {
        this(callable, false);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    m(Callable<l<T>> callable, boolean z) {
        this.f715b = new LinkedHashSet(1);
        this.f716c = new LinkedHashSet(1);
        this.d = new Handler(Looper.getMainLooper());
        this.e = null;
        if (!z) {
            f714a.execute(new b(callable));
            return;
        }
        try {
            l(callable.call());
        } catch (Throwable th) {
            l(new l<>(th));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void g(Throwable th) {
        ArrayList arrayList = new ArrayList(this.f716c);
        if (arrayList.isEmpty()) {
            com.airbnb.lottie.w.d.c("Lottie encountered an error but no failure listener was added:", th);
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((h) it.next()).onResult(th);
        }
    }

    private void h() {
        this.d.post(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void i(T t) {
        Iterator it = new ArrayList(this.f715b).iterator();
        while (it.hasNext()) {
            ((h) it.next()).onResult(t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(@Nullable l<T> lVar) {
        if (this.e != null) {
            throw new IllegalStateException("A task may only be set once.");
        }
        this.e = lVar;
        h();
    }

    public synchronized m<T> e(h<Throwable> hVar) {
        if (this.e != null && this.e.a() != null) {
            hVar.onResult(this.e.a());
        }
        this.f716c.add(hVar);
        return this;
    }

    public synchronized m<T> f(h<T> hVar) {
        if (this.e != null && this.e.b() != null) {
            hVar.onResult(this.e.b());
        }
        this.f715b.add(hVar);
        return this;
    }

    public synchronized m<T> j(h<Throwable> hVar) {
        this.f716c.remove(hVar);
        return this;
    }

    public synchronized m<T> k(h<T> hVar) {
        this.f715b.remove(hVar);
        return this;
    }
}
