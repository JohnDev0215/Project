package com.amazon.nimblymusicservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class SubscriptionTierDetail implements Comparable<SubscriptionTierDetail> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.SubscriptionTierDetail");
    private ParentalControls parentalControls;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SubscriptionTierDetail subscriptionTierDetail) {
        ParentalControls parentalControls;
        ParentalControls parentalControls2;
        if (subscriptionTierDetail == null) {
            return -1;
        }
        if (subscriptionTierDetail != this && (parentalControls = getParentalControls()) != (parentalControls2 = subscriptionTierDetail.getParentalControls())) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo = parentalControls.compareTo(parentalControls2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof SubscriptionTierDetail) {
            return internalEqualityCheck(getParentalControls(), ((SubscriptionTierDetail) obj).getParentalControls());
        }
        return false;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getParentalControls());
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }
}
