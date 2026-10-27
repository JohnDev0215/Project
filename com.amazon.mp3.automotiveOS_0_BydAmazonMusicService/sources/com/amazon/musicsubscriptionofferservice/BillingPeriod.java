package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class BillingPeriod implements Comparable<BillingPeriod> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.BillingPeriod");
    private MonetaryAmount basePrice;
    private Period duration;
    private MonetaryAmount price;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BillingPeriod billingPeriod) {
        if (billingPeriod == null) {
            return -1;
        }
        if (billingPeriod == this) {
            return 0;
        }
        MonetaryAmount basePrice = getBasePrice();
        MonetaryAmount basePrice2 = billingPeriod.getBasePrice();
        if (basePrice != basePrice2) {
            if (basePrice == null) {
                return -1;
            }
            if (basePrice2 == null) {
                return 1;
            }
            int iCompareTo = basePrice.compareTo(basePrice2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Period duration = getDuration();
        Period duration2 = billingPeriod.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo2 = duration.compareTo(duration2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        MonetaryAmount price = getPrice();
        MonetaryAmount price2 = billingPeriod.getPrice();
        if (price != price2) {
            if (price == null) {
                return -1;
            }
            if (price2 == null) {
                return 1;
            }
            int iCompareTo3 = price.compareTo(price2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BillingPeriod)) {
            return false;
        }
        BillingPeriod billingPeriod = (BillingPeriod) obj;
        return internalEqualityCheck(getBasePrice(), billingPeriod.getBasePrice()) && internalEqualityCheck(getDuration(), billingPeriod.getDuration()) && internalEqualityCheck(getPrice(), billingPeriod.getPrice());
    }

    public MonetaryAmount getBasePrice() {
        return this.basePrice;
    }

    public Period getDuration() {
        return this.duration;
    }

    public MonetaryAmount getPrice() {
        return this.price;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getBasePrice(), getDuration(), getPrice());
    }

    public void setBasePrice(MonetaryAmount monetaryAmount) {
        this.basePrice = monetaryAmount;
    }

    public void setDuration(Period period) {
        this.duration = period;
    }

    public void setPrice(MonetaryAmount monetaryAmount) {
        this.price = monetaryAmount;
    }
}
