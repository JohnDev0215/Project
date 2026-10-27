package com.airbnb.lottie;

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
public class m<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static Executor f2887a = Executors.newCachedThreadPool();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Set<h<T>> f2888b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Set<h<Throwable>> f2889c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Handler f2890d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile l<T> f2891e;

    /* JADX INFO: compiled from: LottieTask.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (m.this.f2891e == null) {
                return;
            }
            l lVar = m.this.f2891e;
            if (lVar.b() != null) {
                m.this.i(lVar.b());
            } else {
                m.this.g(lVar.a());
            }
        }
    }

    /* JADX INFO: compiled from: LottieTask.java */
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
            } catch (InterruptedException | ExecutionException e2) {
                m.this.l(new l(e2));
            }
        }
    }

    public m(Callable<l<T>> callable) {
        this(callable, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void g(Throwable th) {
        ArrayList arrayList = new ArrayList(this.f2889c);
        if (arrayList.isEmpty()) {
            com.airbnb.lottie.u.d.c("Lottie encountered an error but no failure listener was added:", th);
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((h) it.next()).a(th);
        }
    }

    private void h() {
        this.f2890d.post(new a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void i(T t) {
        Iterator it = new ArrayList(this.f2888b).iterator();
        while (it.hasNext()) {
            ((h) it.next()).a(t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l(l<T> lVar) {
        if (this.f2891e != null) {
            throw new IllegalStateException("A task may only be set once.");
        }
        this.f2891e = lVar;
        h();
    }

    public synchronized m<T> e(h<Throwable> hVar) {
        if (this.f2891e != null && this.f2891e.a() != null) {
            hVar.a(this.f2891e.a());
        }
        this.f2889c.add(hVar);
        return this;
    }

    public synchronized m<T> f(h<T> hVar) {
        if (this.f2891e != null && this.f2891e.b() != null) {
            hVar.a(this.f2891e.b());
        }
        this.f2888b.add(hVar);
        return this;
    }

    public synchronized m<T> j(h<Throwable> hVar) {
        this.f2889c.remove(hVar);
        return this;
    }

    public synchronized m<T> k(h<T> hVar) {
        this.f2888b.remove(hVar);
        return this;
    }

    m(Callable<l<T>> callable, boolean z) {
        this.f2888b = new LinkedHashSet(1);
        this.f2889c = new LinkedHashSet(1);
        this.f2890d = new Handler(Looper.getMainLooper());
        this.f2891e = null;
        if (!z) {
            f2887a.execute(new b(callable));
            return;
        }
        try {
            l(callable.call());
        } catch (Throwable th) {
            l(new l<>(th));
        }
    }
}
