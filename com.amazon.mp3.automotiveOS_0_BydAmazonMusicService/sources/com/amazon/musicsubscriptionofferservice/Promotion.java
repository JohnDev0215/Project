package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class Promotion implements Comparable<Promotion> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.Promotion");
    private Period baseCommitmentPeriod;
    private MonetaryAmount basePrice;
    private DateRange effective;
    private String promotionId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Promotion promotion) {
        if (promotion == null) {
            return -1;
        }
        if (promotion == this) {
            return 0;
        }
        MonetaryAmount basePrice = getBasePrice();
        MonetaryAmount basePrice2 = promotion.getBasePrice();
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
        Period baseCommitmentPeriod = getBaseCommitmentPeriod();
        Period baseCommitmentPeriod2 = promotion.getBaseCommitmentPeriod();
        if (baseCommitmentPeriod != baseCommitmentPeriod2) {
            if (baseCommitmentPeriod == null) {
                return -1;
            }
            if (baseCommitmentPeriod2 == null) {
                return 1;
            }
            int iCompareTo2 = baseCommitmentPeriod.compareTo(baseCommitmentPeriod2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String promotionId = getPromotionId();
        String promotionId2 = promotion.getPromotionId();
        if (promotionId != promotionId2) {
            if (promotionId == null) {
                return -1;
            }
            if (promotionId2 == null) {
                return 1;
            }
            int iCompareTo3 = promotionId.compareTo(promotionId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        DateRange effective = getEffective();
        DateRange effective2 = promotion.getEffective();
        if (effective != effective2) {
            if (effective == null) {
                return -1;
            }
            if (effective2 == null) {
                return 1;
            }
            int iCompareTo4 = effective.compareTo(effective2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Promotion)) {
            return false;
        }
        Promotion promotion = (Promotion) obj;
        return internalEqualityCheck(getBasePrice(), promotion.getBasePrice()) && internalEqualityCheck(getBaseCommitmentPeriod(), promotion.getBaseCommitmentPeriod()) && internalEqualityCheck(getPromotionId(), promotion.getPromotionId()) && internalEqualityCheck(getEffective(), promotion.getEffective());
    }

    public Period getBaseCommitmentPeriod() {
        return this.baseCommitmentPeriod;
    }

    public MonetaryAmount getBasePrice() {
        return this.basePrice;
    }

    public DateRange getEffective() {
        return this.effective;
    }

    public String getPromotionId() {
        return this.promotionId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getBasePrice(), getBaseCommitmentPeriod(), getPromotionId(), getEffective());
    }

    public void setBaseCommitmentPeriod(Period period) {
        this.baseCommitmentPeriod = period;
    }

    public void setBasePrice(MonetaryAmount monetaryAmount) {
        this.basePrice = monetaryAmount;
    }

    public void setEffective(DateRange dateRange) {
        this.effective = dateRange;
    }

    public void setPromotionId(String str) {
        this.promotionId = str;
    }
}
