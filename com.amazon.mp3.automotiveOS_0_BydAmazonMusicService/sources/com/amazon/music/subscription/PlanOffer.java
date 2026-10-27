package com.amazon.music.subscription;

import com.amazon.stratus.Plan;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class PlanOffer implements Comparable<PlanOffer> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.PlanOffer");
    private Buyability buyability;
    private Plan plan;
    private ProductInfo productInfo;
    private String transitionType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PlanOffer planOffer) {
        if (planOffer == null) {
            return -1;
        }
        if (planOffer == this) {
            return 0;
        }
        Plan plan = getPlan();
        Plan plan2 = planOffer.getPlan();
        if (plan != plan2) {
            if (plan == null) {
                return -1;
            }
            if (plan2 == null) {
                return 1;
            }
            int iCompareTo = plan.compareTo(plan2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String transitionType = getTransitionType();
        String transitionType2 = planOffer.getTransitionType();
        if (transitionType != transitionType2) {
            if (transitionType == null) {
                return -1;
            }
            if (transitionType2 == null) {
                return 1;
            }
            int iCompareTo2 = transitionType.compareTo(transitionType2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        ProductInfo productInfo = getProductInfo();
        ProductInfo productInfo2 = planOffer.getProductInfo();
        if (productInfo != productInfo2) {
            if (productInfo == null) {
                return -1;
            }
            if (productInfo2 == null) {
                return 1;
            }
            int iCompareTo3 = productInfo.compareTo(productInfo2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Buyability buyability = getBuyability();
        Buyability buyability2 = planOffer.getBuyability();
        if (buyability != buyability2) {
            if (buyability == null) {
                return -1;
            }
            if (buyability2 == null) {
                return 1;
            }
            int iCompareTo4 = buyability.compareTo(buyability2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlanOffer)) {
            return false;
        }
        PlanOffer planOffer = (PlanOffer) obj;
        return internalEqualityCheck(getPlan(), planOffer.getPlan()) && internalEqualityCheck(getTransitionType(), planOffer.getTransitionType()) && internalEqualityCheck(getProductInfo(), planOffer.getProductInfo()) && internalEqualityCheck(getBuyability(), planOffer.getBuyability());
    }

    public Buyability getBuyability() {
        return this.buyability;
    }

    public Plan getPlan() {
        return this.plan;
    }

    public ProductInfo getProductInfo() {
        return this.productInfo;
    }

    public String getTransitionType() {
        return this.transitionType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPlan(), getTransitionType(), getProductInfo(), getBuyability());
    }

    public void setBuyability(Buyability buyability) {
        this.buyability = buyability;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setProductInfo(ProductInfo productInfo) {
        this.productInfo = productInfo;
    }

    public void setTransitionType(String str) {
        this.transitionType = str;
    }
}
