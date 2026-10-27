package io.reactivex.rxjava3.internal.subscriptions;

import java.util.concurrent.atomic.AtomicReferenceArray;
import spi.ps;
import spi.q6;

/* JADX INFO: loaded from: classes2.dex */
public final class ArrayCompositeSubscription extends AtomicReferenceArray<ps> implements q6 {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeSubscription(int i) {
        super(i);
    }

    @Override // spi.q6
    public void dispose() {
        ps andSet;
        if (get(0) != SubscriptionHelper.CANCELLED) {
            int length = length();
            for (int i = 0; i < length; i++) {
                ps psVar = get(i);
                SubscriptionHelper subscriptionHelper = SubscriptionHelper.CANCELLED;
                if (psVar != subscriptionHelper && (andSet = getAndSet(i, subscriptionHelper)) != subscriptionHelper && andSet != null) {
                    andSet.cancel();
                }
            }
        }
    }

    public boolean isDisposed() {
        return get(0) == SubscriptionHelper.CANCELLED;
    }

    public ps replaceResource(int i, ps psVar) {
        ps psVar2;
        do {
            psVar2 = get(i);
            if (psVar2 == SubscriptionHelper.CANCELLED) {
                if (psVar == null) {
                    return null;
                }
                psVar.cancel();
                return null;
            }
        } while (!compareAndSet(i, psVar2, psVar));
        return psVar2;
    }

    public boolean setResource(int i, ps psVar) {
        ps psVar2;
        do {
            psVar2 = get(i);
            if (psVar2 == SubscriptionHelper.CANCELLED) {
                if (psVar == null) {
                    return false;
                }
                psVar.cancel();
                return false;
            }
        } while (!compareAndSet(i, psVar2, psVar));
        if (psVar2 == null) {
            return true;
        }
        psVar2.cancel();
        return true;
    }
}
