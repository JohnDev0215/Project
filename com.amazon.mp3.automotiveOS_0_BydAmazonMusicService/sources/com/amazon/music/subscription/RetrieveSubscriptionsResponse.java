package com.amazon.music.subscription;

import com.amazon.stratus.Subscription;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RetrieveSubscriptionsResponse implements Comparable<RetrieveSubscriptionsResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.RetrieveSubscriptionsResponse");
    private List<Subscription> subscriptions;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RetrieveSubscriptionsResponse retrieveSubscriptionsResponse) {
        List<Subscription> subscriptions;
        List<Subscription> subscriptions2;
        if (retrieveSubscriptionsResponse == null) {
            return -1;
        }
        if (retrieveSubscriptionsResponse != this && (subscriptions = getSubscriptions()) != (subscriptions2 = retrieveSubscriptionsResponse.getSubscriptions())) {
            if (subscriptions == null) {
                return -1;
            }
            if (subscriptions2 == null) {
                return 1;
            }
            if (subscriptions instanceof Comparable) {
                int iCompareTo = ((Comparable) subscriptions).compareTo(subscriptions2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!subscriptions.equals(subscriptions2)) {
                int iHashCode = subscriptions.hashCode();
                int iHashCode2 = subscriptions2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof RetrieveSubscriptionsResponse) {
            return internalEqualityCheck(getSubscriptions(), ((RetrieveSubscriptionsResponse) obj).getSubscriptions());
        }
        return false;
    }

    public List<Subscription> getSubscriptions() {
        return this.subscriptions;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSubscriptions());
    }

    public void setSubscriptions(List<Subscription> list) {
        this.subscriptions = list;
    }
}
