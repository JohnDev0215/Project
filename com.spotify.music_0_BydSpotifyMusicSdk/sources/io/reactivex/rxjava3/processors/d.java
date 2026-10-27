package io.reactivex.rxjava3.processors;

import io.reactivex.rxjava3.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.operators.h;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.ci1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class d extends a {
    public volatile boolean m;
    public Throwable n;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public volatile boolean f84p;
    public boolean t;
    public final h e = new h(8);
    public final AtomicReference k = new AtomicReference(null);
    public final boolean l = true;
    public final AtomicReference o = new AtomicReference();
    public final AtomicBoolean q = new AtomicBoolean();
    public final BasicIntQueueSubscription r = new BasicIntQueueSubscription<Object>() { // from class: io.reactivex.rxjava3.processors.UnicastProcessor$UnicastQueueSubscription
        private static final long serialVersionUID = -4896760517184205454L;

        @Override // p.tg2
        public final void a(long j) {
            if (SubscriptionHelper.f(j)) {
                ci1.d(this.this$0.s, j);
                this.this$0.p();
            }
        }

        @Override // io.reactivex.rxjava3.operators.c
        public final int b(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.this$0.t = true;
            return 2;
        }

        @Override // p.tg2
        public final void cancel() {
            if (this.this$0.f84p) {
                return;
            }
            this.this$0.f84p = true;
            Runnable runnable = (Runnable) this.this$0.k.getAndSet(null);
            if (runnable != null) {
                runnable.run();
            }
            this.this$0.o.lazySet(null);
            if (this.this$0.r.getAndIncrement() == 0) {
                this.this$0.o.lazySet(null);
                d dVar = this.this$0;
                if (dVar.t) {
                    return;
                }
                dVar.e.clear();
            }
        }

        @Override // io.reactivex.rxjava3.operators.g
        public final void clear() {
            this.this$0.e.clear();
        }

        @Override // io.reactivex.rxjava3.operators.g
        public final boolean isEmpty() {
            return this.this$0.e.isEmpty();
        }

        @Override // io.reactivex.rxjava3.operators.g
        public final Object poll() {
            return this.this$0.e.poll();
        }
    };
    public final AtomicLong s = new AtomicLong();

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        if (this.q.get() || !this.q.compareAndSet(false, true)) {
            IllegalStateException illegalStateException = new IllegalStateException("This processor allows only a single Subscriber");
            rg2Var.onSubscribe(EmptySubscription.c);
            rg2Var.onError(illegalStateException);
        } else {
            rg2Var.onSubscribe(this.r);
            this.o.set(rg2Var);
            if (this.f84p) {
                this.o.lazySet(null);
            } else {
                p();
            }
        }
    }

    public final boolean o(boolean z, boolean z2, boolean z3, rg2 rg2Var, h hVar) {
        if (this.f84p) {
            hVar.clear();
            this.o.lazySet(null);
            return true;
        }
        if (!z2) {
            return false;
        }
        if (z && this.n != null) {
            hVar.clear();
            this.o.lazySet(null);
            rg2Var.onError(this.n);
            return true;
        }
        if (!z3) {
            return false;
        }
        Throwable th = this.n;
        this.o.lazySet(null);
        if (th != null) {
            rg2Var.onError(th);
        } else {
            rg2Var.onComplete();
        }
        return true;
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.m || this.f84p) {
            return;
        }
        this.m = true;
        Runnable runnable = (Runnable) this.k.getAndSet(null);
        if (runnable != null) {
            runnable.run();
        }
        p();
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        io.reactivex.rxjava3.internal.util.c.c(th, "onError called with a null Throwable.");
        if (this.m || this.f84p) {
            RxJavaPlugins.b(th);
            return;
        }
        this.n = th;
        this.m = true;
        Runnable runnable = (Runnable) this.k.getAndSet(null);
        if (runnable != null) {
            runnable.run();
        }
        p();
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        io.reactivex.rxjava3.internal.util.c.c(obj, "onNext called with a null value.");
        if (this.m || this.f84p) {
            return;
        }
        this.e.offer(obj);
        p();
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (this.m || this.f84p) {
            tg2Var.cancel();
        } else {
            tg2Var.a(Long.MAX_VALUE);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void p() {
        long j;
        if (this.r.getAndIncrement() != 0) {
            return;
        }
        int iAddAndGet = 1;
        rg2 rg2Var = (rg2) this.o.get();
        int iAddAndGet2 = 1;
        while (rg2Var == null) {
            iAddAndGet2 = this.r.addAndGet(-iAddAndGet2);
            if (iAddAndGet2 == 0) {
                return;
            }
            rg2Var = (rg2) this.o.get();
            iAddAndGet = 1;
        }
        if (this.t) {
            h hVar = this.e;
            int i = (this.l ? 1 : 0) ^ iAddAndGet;
            while (!this.f84p) {
                boolean z = this.m;
                if (i != 0 && z && this.n != null) {
                    hVar.clear();
                    this.o.lazySet(null);
                    rg2Var.onError(this.n);
                    return;
                }
                rg2Var.onNext(null);
                if (z) {
                    this.o.lazySet(null);
                    Throwable th = this.n;
                    if (th != null) {
                        rg2Var.onError(th);
                        return;
                    } else {
                        rg2Var.onComplete();
                        return;
                    }
                }
                iAddAndGet = this.r.addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
            }
            this.o.lazySet(null);
            return;
        }
        h hVar2 = this.e;
        boolean z2 = !this.l;
        int iAddAndGet3 = iAddAndGet;
        while (true) {
            long j2 = this.s.get();
            long j3 = 0;
            while (true) {
                if (j2 == j3) {
                    j = j3;
                    break;
                }
                boolean z3 = this.m;
                Object objPoll = hVar2.poll();
                int i2 = objPoll == null ? iAddAndGet : 0;
                j = j3;
                if (o(z2, z3, i2, rg2Var, hVar2)) {
                    return;
                }
                if (i2 != 0) {
                    break;
                }
                rg2Var.onNext(objPoll);
                j3 = j + 1;
                iAddAndGet = 1;
            }
            if (j2 == j3 && o(z2, this.m, hVar2.isEmpty(), rg2Var, hVar2)) {
                return;
            }
            if (j != 0 && j2 != Long.MAX_VALUE) {
                this.s.addAndGet(-j);
            }
            iAddAndGet3 = this.r.addAndGet(-iAddAndGet3);
            if (iAddAndGet3 == 0) {
                return;
            } else {
                iAddAndGet = 1;
            }
        }
    }
}
