package com.amazon.music.subscription;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class AdditionalPlanPriceInfo implements Comparable<AdditionalPlanPriceInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.AdditionalPlanPriceInfo");
    private PriceWithCurrency dueNowPlanPriceTotal;
    private PriceWithCurrency pendingPlanPriceTotal;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AdditionalPlanPriceInfo additionalPlanPriceInfo) {
        if (additionalPlanPriceInfo == null) {
            return -1;
        }
        if (additionalPlanPriceInfo == this) {
            return 0;
        }
        PriceWithCurrency dueNowPlanPriceTotal = getDueNowPlanPriceTotal();
        PriceWithCurrency dueNowPlanPriceTotal2 = additionalPlanPriceInfo.getDueNowPlanPriceTotal();
        if (dueNowPlanPriceTotal != dueNowPlanPriceTotal2) {
            if (dueNowPlanPriceTotal == null) {
                return -1;
            }
            if (dueNowPlanPriceTotal2 == null) {
                return 1;
            }
            int iCompareTo = dueNowPlanPriceTotal.compareTo(dueNowPlanPriceTotal2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        PriceWithCurrency pendingPlanPriceTotal = getPendingPlanPriceTotal();
        PriceWithCurrency pendingPlanPriceTotal2 = additionalPlanPriceInfo.getPendingPlanPriceTotal();
        if (pendingPlanPriceTotal != pendingPlanPriceTotal2) {
            if (pendingPlanPriceTotal == null) {
                return -1;
            }
            if (pendingPlanPriceTotal2 == null) {
                return 1;
            }
            int iCompareTo2 = pendingPlanPriceTotal.compareTo(pendingPlanPriceTotal2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AdditionalPlanPriceInfo)) {
            return false;
        }
        AdditionalPlanPriceInfo additionalPlanPriceInfo = (AdditionalPlanPriceInfo) obj;
        return internalEqualityCheck(getDueNowPlanPriceTotal(), additionalPlanPriceInfo.getDueNowPlanPriceTotal()) && internalEqualityCheck(getPendingPlanPriceTotal(), additionalPlanPriceInfo.getPendingPlanPriceTotal());
    }

    public PriceWithCurrency getDueNowPlanPriceTotal() {
        return this.dueNowPlanPriceTotal;
    }

    public PriceWithCurrency getPendingPlanPriceTotal() {
        return this.pendingPlanPriceTotal;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDueNowPlanPriceTotal(), getPendingPlanPriceTotal());
    }

    public void setDueNowPlanPriceTotal(PriceWithCurrency priceWithCurrency) {
        this.dueNowPlanPriceTotal = priceWithCurrency;
    }

    public void setPendingPlanPriceTotal(PriceWithCurrency priceWithCurrency) {
        this.pendingPlanPriceTotal = priceWithCurrency;
    }
}
