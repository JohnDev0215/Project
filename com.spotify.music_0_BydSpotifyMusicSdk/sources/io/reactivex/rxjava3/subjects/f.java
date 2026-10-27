package io.reactivex.rxjava3.subjects;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.internal.observers.BasicIntQueueDisposable;
import io.reactivex.rxjava3.operators.h;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class f extends e {
    public final h c;
    public final AtomicReference k;
    public volatile boolean m;
    public volatile boolean n;
    public Throwable o;
    public boolean r;
    public final boolean l = true;
    public final AtomicReference e = new AtomicReference();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final AtomicBoolean f87p = new AtomicBoolean();
    public final BasicIntQueueDisposable q = new BasicIntQueueDisposable<Object>() { // from class: io.reactivex.rxjava3.subjects.UnicastSubject$UnicastQueueDisposable
        private static final long serialVersionUID = 7926949470189395511L;

        @Override // io.reactivex.rxjava3.operators.c
        public final int b(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.this$0.r = true;
            return 2;
        }

        @Override // io.reactivex.rxjava3.operators.g
        public final void clear() {
            this.this$0.c.clear();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.this$0.m) {
                return;
            }
            this.this$0.m = true;
            this.this$0.b();
            this.this$0.e.lazySet(null);
            if (this.this$0.q.getAndIncrement() == 0) {
                this.this$0.e.lazySet(null);
                f fVar = this.this$0;
                if (fVar.r) {
                    return;
                }
                fVar.c.clear();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.this$0.m;
        }

        @Override // io.reactivex.rxjava3.operators.g
        public final boolean isEmpty() {
            return this.this$0.c.isEmpty();
        }

        @Override // io.reactivex.rxjava3.operators.g
        public final Object poll() {
            return this.this$0.c.poll();
        }
    };

    public f(int i, Runnable runnable) {
        this.c = new h(i);
        this.k = new AtomicReference(runnable);
    }

    public static f a(int i, Runnable runnable) {
        k.c(i, "capacityHint");
        Objects.requireNonNull(runnable, "onTerminate");
        return new f(i, runnable);
    }

    public final void b() {
        AtomicReference atomicReference = this.k;
        Runnable runnable = (Runnable) atomicReference.get();
        if (runnable != null) {
            while (!atomicReference.compareAndSet(runnable, null)) {
                if (atomicReference.get() != runnable) {
                    return;
                }
            }
            runnable.run();
        }
    }

    public final void c() {
        Throwable th;
        if (this.q.getAndIncrement() != 0) {
            return;
        }
        Observer observer = (Observer) this.e.get();
        int iAddAndGet = 1;
        int iAddAndGet2 = 1;
        while (observer == null) {
            iAddAndGet2 = this.q.addAndGet(-iAddAndGet2);
            if (iAddAndGet2 == 0) {
                return;
            } else {
                observer = (Observer) this.e.get();
            }
        }
        if (this.r) {
            h hVar = this.c;
            boolean z = !this.l;
            while (!this.m) {
                boolean z2 = this.n;
                if (z && z2 && (th = this.o) != null) {
                    this.e.lazySet(null);
                    hVar.clear();
                    observer.onError(th);
                    return;
                }
                observer.onNext(null);
                if (z2) {
                    this.e.lazySet(null);
                    Throwable th2 = this.o;
                    if (th2 != null) {
                        observer.onError(th2);
                        return;
                    } else {
                        observer.onComplete();
                        return;
                    }
                }
                iAddAndGet = this.q.addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
            this.e.lazySet(null);
            return;
        }
        h hVar2 = this.c;
        boolean z3 = !this.l;
        boolean z4 = true;
        int iAddAndGet3 = 1;
        while (!this.m) {
            boolean z5 = this.n;
            Object objPoll = this.c.poll();
            boolean z6 = objPoll == null;
            if (z5) {
                if (z3 && z4) {
                    Throwable th3 = this.o;
                    if (th3 != null) {
                        this.e.lazySet(null);
                        hVar2.clear();
                        observer.onError(th3);
                        return;
                    }
                    z4 = false;
                }
                if (z6) {
                    this.e.lazySet(null);
                    Throwable th4 = this.o;
                    if (th4 != null) {
                        observer.onError(th4);
                        return;
                    } else {
                        observer.onComplete();
                        return;
                    }
                }
            }
            if (z6) {
                iAddAndGet3 = this.q.addAndGet(-iAddAndGet3);
                if (iAddAndGet3 == 0) {
                    return;
                }
            } else {
                observer.onNext(objPoll);
            }
        }
        this.e.lazySet(null);
        hVar2.clear();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.n || this.m) {
            return;
        }
        this.n = true;
        b();
        c();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        io.reactivex.rxjava3.internal.util.c.c(th, "onError called with a null Throwable.");
        if (this.n || this.m) {
            RxJavaPlugins.b(th);
            return;
        }
        this.o = th;
        this.n = true;
        b();
        c();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        io.reactivex.rxjava3.internal.util.c.c(obj, "onNext called with a null value.");
        if (this.n || this.m) {
            return;
        }
        this.c.offer(obj);
        c();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (this.n || this.m) {
            disposable.dispose();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        if (this.f87p.get() || !this.f87p.compareAndSet(false, true)) {
            IllegalStateException illegalStateException = new IllegalStateException("Only a single observer allowed.");
            observer.onSubscribe(EmptyDisposable.c);
            observer.onError(illegalStateException);
        } else {
            observer.onSubscribe(this.q);
            this.e.lazySet(observer);
            if (this.m) {
                this.e.lazySet(null);
            } else {
                c();
            }
        }
    }
}
