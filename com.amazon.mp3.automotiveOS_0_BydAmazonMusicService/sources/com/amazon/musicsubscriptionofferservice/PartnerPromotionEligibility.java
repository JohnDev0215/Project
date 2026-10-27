package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class PartnerPromotionEligibility implements Comparable<PartnerPromotionEligibility> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.PartnerPromotionEligibility");
    private String promoGroup;
    private String promoPartner;
    private String promoTransitionType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PartnerPromotionEligibility partnerPromotionEligibility) {
        if (partnerPromotionEligibility == null) {
            return -1;
        }
        if (partnerPromotionEligibility == this) {
            return 0;
        }
        String promoPartner = getPromoPartner();
        String promoPartner2 = partnerPromotionEligibility.getPromoPartner();
        if (promoPartner != promoPartner2) {
            if (promoPartner == null) {
                return -1;
            }
            if (promoPartner2 == null) {
                return 1;
            }
            int iCompareTo = promoPartner.compareTo(promoPartner2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String promoGroup = getPromoGroup();
        String promoGroup2 = partnerPromotionEligibility.getPromoGroup();
        if (promoGroup != promoGroup2) {
            if (promoGroup == null) {
                return -1;
            }
            if (promoGroup2 == null) {
                return 1;
            }
            int iCompareTo2 = promoGroup.compareTo(promoGroup2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String promoTransitionType = getPromoTransitionType();
        String promoTransitionType2 = partnerPromotionEligibility.getPromoTransitionType();
        if (promoTransitionType != promoTransitionType2) {
            if (promoTransitionType == null) {
                return -1;
            }
            if (promoTransitionType2 == null) {
                return 1;
            }
            int iCompareTo3 = promoTransitionType.compareTo(promoTransitionType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PartnerPromotionEligibility)) {
            return false;
        }
        PartnerPromotionEligibility partnerPromotionEligibility = (PartnerPromotionEligibility) obj;
        return internalEqualityCheck(getPromoPartner(), partnerPromotionEligibility.getPromoPartner()) && internalEqualityCheck(getPromoGroup(), partnerPromotionEligibility.getPromoGroup()) && internalEqualityCheck(getPromoTransitionType(), partnerPromotionEligibility.getPromoTransitionType());
    }

    public String getPromoGroup() {
        return this.promoGroup;
    }

    public String getPromoPartner() {
        return this.promoPartner;
    }

    public String getPromoTransitionType() {
        return this.promoTransitionType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPromoPartner(), getPromoGroup(), getPromoTransitionType());
    }

    public void setPromoGroup(String str) {
        this.promoGroup = str;
    }

    public void setPromoPartner(String str) {
        this.promoPartner = str;
    }

    public void setPromoTransitionType(String str) {
        this.promoTransitionType = str;
    }
}
