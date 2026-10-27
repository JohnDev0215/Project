package io.reactivex.rxjava3.internal.subscriptions;

import io.reactivex.rxjava3.exceptions.ProtocolViolationException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p.ci1;
import p.tg2;
import p.u4;

/* JADX INFO: loaded from: classes.dex */
public class SubscriptionArbiter extends AtomicInteger implements tg2 {
    private static final long serialVersionUID = -2189523197179400958L;
    tg2 actual;
    volatile boolean cancelled;
    long requested;
    protected boolean unbounded;
    final boolean cancelOnReplace = false;
    final AtomicReference<tg2> missedSubscription = new AtomicReference<>();
    final AtomicLong missedRequested = new AtomicLong();
    final AtomicLong missedProduced = new AtomicLong();

    @Override // p.tg2
    public final void a(long j) {
        if (!SubscriptionHelper.f(j) || this.unbounded) {
            return;
        }
        if (get() != 0 || !compareAndSet(0, 1)) {
            ci1.d(this.missedRequested, j);
            if (getAndIncrement() != 0) {
                return;
            }
            b();
            return;
        }
        long j2 = this.requested;
        if (j2 != Long.MAX_VALUE) {
            long jE = ci1.e(j2, j);
            this.requested = jE;
            if (jE == Long.MAX_VALUE) {
                this.unbounded = true;
            }
        }
        tg2 tg2Var = this.actual;
        if (decrementAndGet() != 0) {
            b();
        }
        if (tg2Var != null) {
            tg2Var.a(j);
        }
    }

    public final void b() {
        int iAddAndGet = 1;
        long jE = 0;
        tg2 tg2Var = null;
        do {
            tg2 andSet = this.missedSubscription.get();
            if (andSet != null) {
                andSet = this.missedSubscription.getAndSet(null);
            }
            long andSet2 = this.missedRequested.get();
            if (andSet2 != 0) {
                andSet2 = this.missedRequested.getAndSet(0L);
            }
            long andSet3 = this.missedProduced.get();
            if (andSet3 != 0) {
                andSet3 = this.missedProduced.getAndSet(0L);
            }
            tg2 tg2Var2 = this.actual;
            if (this.cancelled) {
                if (tg2Var2 != null) {
                    tg2Var2.cancel();
                    this.actual = null;
                }
                if (andSet != null) {
                    andSet.cancel();
                }
            } else {
                long jE2 = this.requested;
                if (jE2 != Long.MAX_VALUE) {
                    jE2 = ci1.e(jE2, andSet2);
                    if (jE2 != Long.MAX_VALUE) {
                        jE2 -= andSet3;
                        if (jE2 < 0) {
                            RxJavaPlugins.b(new ProtocolViolationException(u4.g("More produced than requested: ", jE2)));
                            jE2 = 0;
                        }
                    }
                    this.requested = jE2;
                }
                if (andSet != null) {
                    if (tg2Var2 != null && this.cancelOnReplace) {
                        tg2Var2.cancel();
                    }
                    this.actual = andSet;
                    if (jE2 != 0) {
                        jE = ci1.e(jE, jE2);
                        tg2Var = andSet;
                    }
                } else if (tg2Var2 != null && andSet2 != 0) {
                    jE = ci1.e(jE, andSet2);
                    tg2Var = tg2Var2;
                }
            }
            iAddAndGet = addAndGet(-iAddAndGet);
        } while (iAddAndGet != 0);
        if (jE != 0) {
            tg2Var.a(jE);
        }
    }

    public final boolean c() {
        return this.cancelled;
    }

    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        if (getAndIncrement() != 0) {
            return;
        }
        b();
    }

    public final boolean e() {
        return this.unbounded;
    }

    public final void f(long j) {
        if (this.unbounded) {
            return;
        }
        if (get() != 0 || !compareAndSet(0, 1)) {
            ci1.d(this.missedProduced, j);
            if (getAndIncrement() != 0) {
                return;
            }
            b();
            return;
        }
        long j2 = this.requested;
        if (j2 != Long.MAX_VALUE) {
            long j3 = j2 - j;
            if (j3 < 0) {
                RxJavaPlugins.b(new ProtocolViolationException(u4.g("More produced than requested: ", j3)));
                j3 = 0;
            }
            this.requested = j3;
        }
        if (decrementAndGet() == 0) {
            return;
        }
        b();
    }

    public final void g(tg2 tg2Var) {
        if (this.cancelled) {
            tg2Var.cancel();
            return;
        }
        Objects.requireNonNull(tg2Var, "s is null");
        if (get() != 0 || !compareAndSet(0, 1)) {
            tg2 andSet = this.missedSubscription.getAndSet(tg2Var);
            if (andSet != null && this.cancelOnReplace) {
                andSet.cancel();
            }
            if (getAndIncrement() != 0) {
                return;
            }
            b();
            return;
        }
        tg2 tg2Var2 = this.actual;
        if (tg2Var2 != null && this.cancelOnReplace) {
            tg2Var2.cancel();
        }
        this.actual = tg2Var;
        long j = this.requested;
        if (decrementAndGet() != 0) {
            b();
        }
        if (j != 0) {
            tg2Var.a(j);
        }
    }

    public void onSubscribe(tg2 tg2Var) {
        g(tg2Var);
    }
}
