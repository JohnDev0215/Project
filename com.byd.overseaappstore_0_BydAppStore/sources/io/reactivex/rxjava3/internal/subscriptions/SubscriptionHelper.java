package io.reactivex.rxjava3.internal.subscriptions;

import io.reactivex.rxjava3.exceptions.ProtocolViolationException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import spi.d;
import spi.ps;
import spi.vn;
import spi.z0;

/* JADX INFO: loaded from: classes2.dex */
public enum SubscriptionHelper implements ps {
    CANCELLED;

    public static boolean cancel(AtomicReference<ps> atomicReference) {
        ps andSet;
        ps psVar = atomicReference.get();
        SubscriptionHelper subscriptionHelper = CANCELLED;
        if (psVar == subscriptionHelper || (andSet = atomicReference.getAndSet(subscriptionHelper)) == subscriptionHelper) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.cancel();
        return true;
    }

    public static void deferredRequest(AtomicReference<ps> atomicReference, AtomicLong atomicLong, long j) {
        ps psVar = atomicReference.get();
        if (psVar != null) {
            psVar.request(j);
            return;
        }
        if (validate(j)) {
            z0.a(atomicLong, j);
            ps psVar2 = atomicReference.get();
            if (psVar2 != null) {
                long andSet = atomicLong.getAndSet(0L);
                if (andSet != 0) {
                    psVar2.request(andSet);
                }
            }
        }
    }

    public static boolean deferredSetOnce(AtomicReference<ps> atomicReference, AtomicLong atomicLong, ps psVar) {
        if (!setOnce(atomicReference, psVar)) {
            return false;
        }
        long andSet = atomicLong.getAndSet(0L);
        if (andSet == 0) {
            return true;
        }
        psVar.request(andSet);
        return true;
    }

    public static boolean replace(AtomicReference<ps> atomicReference, ps psVar) {
        ps psVar2;
        do {
            psVar2 = atomicReference.get();
            if (psVar2 == CANCELLED) {
                if (psVar == null) {
                    return false;
                }
                psVar.cancel();
                return false;
            }
        } while (!atomicReference.compareAndSet(psVar2, psVar));
        return true;
    }

    public static void reportMoreProduced(long j) {
        vn.a(new ProtocolViolationException(d.d("More produced than requested: ", j)));
    }

    public static void reportSubscriptionSet() {
        vn.a(new ProtocolViolationException("Subscription already set!"));
    }

    public static boolean set(AtomicReference<ps> atomicReference, ps psVar) {
        ps psVar2;
        do {
            psVar2 = atomicReference.get();
            if (psVar2 == CANCELLED) {
                if (psVar == null) {
                    return false;
                }
                psVar.cancel();
                return false;
            }
        } while (!atomicReference.compareAndSet(psVar2, psVar));
        if (psVar2 == null) {
            return true;
        }
        psVar2.cancel();
        return true;
    }

    public static boolean setOnce(AtomicReference<ps> atomicReference, ps psVar) {
        Objects.requireNonNull(psVar, "s is null");
        if (atomicReference.compareAndSet(null, psVar)) {
            return true;
        }
        psVar.cancel();
        if (atomicReference.get() == CANCELLED) {
            return false;
        }
        reportSubscriptionSet();
        return false;
    }

    public static boolean setOnce(AtomicReference<ps> atomicReference, ps psVar, long j) {
        if (!setOnce(atomicReference, psVar)) {
            return false;
        }
        psVar.request(j);
        return true;
    }

    public static boolean validate(long j) {
        if (j > 0) {
            return true;
        }
        vn.a(new IllegalArgumentException(d.d("n > 0 required but it was ", j)));
        return false;
    }

    public static boolean validate(ps psVar, ps psVar2) {
        if (psVar2 == null) {
            vn.a(new NullPointerException("next is null"));
            return false;
        }
        if (psVar == null) {
            return true;
        }
        psVar2.cancel();
        reportSubscriptionSet();
        return false;
    }

    @Override // spi.ps
    public void cancel() {
    }

    @Override // spi.ps
    public void request(long j) {
    }
}
