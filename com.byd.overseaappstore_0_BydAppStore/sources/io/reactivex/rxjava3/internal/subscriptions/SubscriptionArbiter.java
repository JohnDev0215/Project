package io.reactivex.rxjava3.internal.subscriptions;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import spi.ps;
import spi.z0;

/* JADX INFO: loaded from: classes2.dex */
public class SubscriptionArbiter extends AtomicInteger implements ps {
    private static final long serialVersionUID = -2189523197179400958L;
    public ps actual;
    public final boolean cancelOnReplace;
    public volatile boolean cancelled;
    public long requested;
    public boolean unbounded;
    public final AtomicReference<ps> missedSubscription = new AtomicReference<>();
    public final AtomicLong missedRequested = new AtomicLong();
    public final AtomicLong missedProduced = new AtomicLong();

    public SubscriptionArbiter(boolean z) {
        this.cancelOnReplace = z;
    }

    @Override // spi.ps
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        drain();
    }

    public final void drain() {
        if (getAndIncrement() != 0) {
            return;
        }
        drainLoop();
    }

    public final void drainLoop() {
        int iAddAndGet = 1;
        ps psVar = null;
        long jB = 0;
        do {
            ps andSet = this.missedSubscription.get();
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
            ps psVar2 = this.actual;
            if (this.cancelled) {
                if (psVar2 != null) {
                    psVar2.cancel();
                    this.actual = null;
                }
                if (andSet != null) {
                    andSet.cancel();
                }
            } else {
                long jB2 = this.requested;
                if (jB2 != Long.MAX_VALUE) {
                    jB2 = z0.b(jB2, andSet2);
                    if (jB2 != Long.MAX_VALUE) {
                        jB2 -= andSet3;
                        if (jB2 < 0) {
                            SubscriptionHelper.reportMoreProduced(jB2);
                            jB2 = 0;
                        }
                    }
                    this.requested = jB2;
                }
                if (andSet != null) {
                    if (psVar2 != null && this.cancelOnReplace) {
                        psVar2.cancel();
                    }
                    this.actual = andSet;
                    if (jB2 != 0) {
                        jB = z0.b(jB, jB2);
                        psVar = andSet;
                    }
                } else if (psVar2 != null && andSet2 != 0) {
                    jB = z0.b(jB, andSet2);
                    psVar = psVar2;
                }
            }
            iAddAndGet = addAndGet(-iAddAndGet);
        } while (iAddAndGet != 0);
        if (jB != 0) {
            psVar.request(jB);
        }
    }

    public final boolean isCancelled() {
        return this.cancelled;
    }

    public final boolean isUnbounded() {
        return this.unbounded;
    }

    public final void produced(long j) {
        if (this.unbounded) {
            return;
        }
        if (get() != 0 || !compareAndSet(0, 1)) {
            z0.a(this.missedProduced, j);
            drain();
            return;
        }
        long j2 = this.requested;
        if (j2 != Long.MAX_VALUE) {
            long j3 = j2 - j;
            if (j3 < 0) {
                SubscriptionHelper.reportMoreProduced(j3);
                j3 = 0;
            }
            this.requested = j3;
        }
        if (decrementAndGet() == 0) {
            return;
        }
        drainLoop();
    }

    @Override // spi.ps
    public final void request(long j) {
        if (!SubscriptionHelper.validate(j) || this.unbounded) {
            return;
        }
        if (get() != 0 || !compareAndSet(0, 1)) {
            z0.a(this.missedRequested, j);
            drain();
            return;
        }
        long j2 = this.requested;
        if (j2 != Long.MAX_VALUE) {
            long jB = z0.b(j2, j);
            this.requested = jB;
            if (jB == Long.MAX_VALUE) {
                this.unbounded = true;
            }
        }
        ps psVar = this.actual;
        if (decrementAndGet() != 0) {
            drainLoop();
        }
        if (psVar != null) {
            psVar.request(j);
        }
    }

    public final void setSubscription(ps psVar) {
        if (this.cancelled) {
            psVar.cancel();
            return;
        }
        Objects.requireNonNull(psVar, "s is null");
        if (get() != 0 || !compareAndSet(0, 1)) {
            ps andSet = this.missedSubscription.getAndSet(psVar);
            if (andSet != null && this.cancelOnReplace) {
                andSet.cancel();
            }
            drain();
            return;
        }
        ps psVar2 = this.actual;
        if (psVar2 != null && this.cancelOnReplace) {
            psVar2.cancel();
        }
        this.actual = psVar;
        long j = this.requested;
        if (decrementAndGet() != 0) {
            drainLoop();
        }
        if (j != 0) {
            psVar.request(j);
        }
    }
}
