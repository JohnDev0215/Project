package com.amazon.musicensembleservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class StationSubscriptionTierDetail implements Comparable<StationSubscriptionTierDetail> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.StationSubscriptionTierDetail");
    private ParentalControls parentalControls;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated StationSubscriptionTierDetail stationSubscriptionTierDetail) {
        ParentalControls parentalControls;
        ParentalControls parentalControls2;
        if (stationSubscriptionTierDetail == null) {
            return -1;
        }
        if (stationSubscriptionTierDetail != this && (parentalControls = getParentalControls()) != (parentalControls2 = stationSubscriptionTierDetail.getParentalControls())) {
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
        if (obj instanceof StationSubscriptionTierDetail) {
            return internalEqualityCheck(getParentalControls(), ((StationSubscriptionTierDetail) obj).getParentalControls());
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
