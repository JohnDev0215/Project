package com.amazon.nimblymusicservice;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class RecentStationActivity extends RecentActivityData {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.RecentStationActivity");
    private Map<String, SubscriptionTierDetail> subscriptionTierToDetails;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.nimblymusicservice.RecentActivityData, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RecentActivityData recentActivityData) {
        if (recentActivityData == null) {
            return -1;
        }
        if (recentActivityData == this) {
            return 0;
        }
        if (!(recentActivityData instanceof RecentStationActivity)) {
            return 1;
        }
        Map<String, SubscriptionTierDetail> subscriptionTierToDetails = getSubscriptionTierToDetails();
        Map<String, SubscriptionTierDetail> subscriptionTierToDetails2 = ((RecentStationActivity) recentActivityData).getSubscriptionTierToDetails();
        if (subscriptionTierToDetails != subscriptionTierToDetails2) {
            if (subscriptionTierToDetails == null) {
                return -1;
            }
            if (subscriptionTierToDetails2 == null) {
                return 1;
            }
            if (subscriptionTierToDetails instanceof Comparable) {
                int iCompareTo = ((Comparable) subscriptionTierToDetails).compareTo(subscriptionTierToDetails2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!subscriptionTierToDetails.equals(subscriptionTierToDetails2)) {
                int iHashCode = subscriptionTierToDetails.hashCode();
                int iHashCode2 = subscriptionTierToDetails2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(recentActivityData);
    }

    @Override // com.amazon.nimblymusicservice.RecentActivityData
    public boolean equals(Object obj) {
        if (obj instanceof RecentStationActivity) {
            return super.equals(obj) && internalEqualityCheck(getSubscriptionTierToDetails(), ((RecentStationActivity) obj).getSubscriptionTierToDetails());
        }
        return false;
    }

    public Map<String, SubscriptionTierDetail> getSubscriptionTierToDetails() {
        return this.subscriptionTierToDetails;
    }

    @Override // com.amazon.nimblymusicservice.RecentActivityData
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSubscriptionTierToDetails());
    }

    public void setSubscriptionTierToDetails(Map<String, SubscriptionTierDetail> map) {
        this.subscriptionTierToDetails = map;
    }
}
