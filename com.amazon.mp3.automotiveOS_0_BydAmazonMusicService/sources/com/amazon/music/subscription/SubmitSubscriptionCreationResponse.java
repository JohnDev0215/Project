package com.amazon.music.subscription;

import com.amazon.stratus.Subscription;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class SubmitSubscriptionCreationResponse implements Comparable<SubmitSubscriptionCreationResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.SubmitSubscriptionCreationResponse");
    private Subscription subscription;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SubmitSubscriptionCreationResponse submitSubscriptionCreationResponse) {
        Subscription subscription;
        Subscription subscription2;
        if (submitSubscriptionCreationResponse == null) {
            return -1;
        }
        if (submitSubscriptionCreationResponse != this && (subscription = getSubscription()) != (subscription2 = submitSubscriptionCreationResponse.getSubscription())) {
            if (subscription == null) {
                return -1;
            }
            if (subscription2 == null) {
                return 1;
            }
            int iCompareTo = subscription.compareTo(subscription2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof SubmitSubscriptionCreationResponse) {
            return internalEqualityCheck(getSubscription(), ((SubmitSubscriptionCreationResponse) obj).getSubscription());
        }
        return false;
    }

    public Subscription getSubscription() {
        return this.subscription;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSubscription());
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
