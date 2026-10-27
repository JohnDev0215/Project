package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Result implements Comparable<Result> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.Result");
    private Buyability buyability;
    private Eligibility eligibility;
    private List<PartnerPromotionEligibility> partnerPromotionEligibilities;
    private PlanSchedule planSchedule;
    private String transitionType;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Result result) {
        if (result == null) {
            return -1;
        }
        if (result == this) {
            return 0;
        }
        PlanSchedule planSchedule = getPlanSchedule();
        PlanSchedule planSchedule2 = result.getPlanSchedule();
        if (planSchedule != planSchedule2) {
            if (planSchedule == null) {
                return -1;
            }
            if (planSchedule2 == null) {
                return 1;
            }
            int iCompareTo = planSchedule.compareTo(planSchedule2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<PartnerPromotionEligibility> partnerPromotionEligibilities = getPartnerPromotionEligibilities();
        List<PartnerPromotionEligibility> partnerPromotionEligibilities2 = result.getPartnerPromotionEligibilities();
        if (partnerPromotionEligibilities != partnerPromotionEligibilities2) {
            if (partnerPromotionEligibilities == null) {
                return -1;
            }
            if (partnerPromotionEligibilities2 == null) {
                return 1;
            }
            if (partnerPromotionEligibilities instanceof Comparable) {
                int iCompareTo2 = ((Comparable) partnerPromotionEligibilities).compareTo(partnerPromotionEligibilities2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!partnerPromotionEligibilities.equals(partnerPromotionEligibilities2)) {
                int iHashCode = partnerPromotionEligibilities.hashCode();
                int iHashCode2 = partnerPromotionEligibilities2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String transitionType = getTransitionType();
        String transitionType2 = result.getTransitionType();
        if (transitionType != transitionType2) {
            if (transitionType == null) {
                return -1;
            }
            if (transitionType2 == null) {
                return 1;
            }
            int iCompareTo3 = transitionType.compareTo(transitionType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Eligibility eligibility = getEligibility();
        Eligibility eligibility2 = result.getEligibility();
        if (eligibility != eligibility2) {
            if (eligibility == null) {
                return -1;
            }
            if (eligibility2 == null) {
                return 1;
            }
            int iCompareTo4 = eligibility.compareTo(eligibility2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Buyability buyability = getBuyability();
        Buyability buyability2 = result.getBuyability();
        if (buyability != buyability2) {
            if (buyability == null) {
                return -1;
            }
            if (buyability2 == null) {
                return 1;
            }
            int iCompareTo5 = buyability.compareTo(buyability2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Result)) {
            return false;
        }
        Result result = (Result) obj;
        return internalEqualityCheck(getPlanSchedule(), result.getPlanSchedule()) && internalEqualityCheck(getPartnerPromotionEligibilities(), result.getPartnerPromotionEligibilities()) && internalEqualityCheck(getTransitionType(), result.getTransitionType()) && internalEqualityCheck(getEligibility(), result.getEligibility()) && internalEqualityCheck(getBuyability(), result.getBuyability());
    }

    public Buyability getBuyability() {
        return this.buyability;
    }

    public Eligibility getEligibility() {
        return this.eligibility;
    }

    public List<PartnerPromotionEligibility> getPartnerPromotionEligibilities() {
        return this.partnerPromotionEligibilities;
    }

    public PlanSchedule getPlanSchedule() {
        return this.planSchedule;
    }

    public String getTransitionType() {
        return this.transitionType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPlanSchedule(), getPartnerPromotionEligibilities(), getTransitionType(), getEligibility(), getBuyability());
    }

    public void setBuyability(Buyability buyability) {
        this.buyability = buyability;
    }

    public void setEligibility(Eligibility eligibility) {
        this.eligibility = eligibility;
    }

    public void setPartnerPromotionEligibilities(List<PartnerPromotionEligibility> list) {
        this.partnerPromotionEligibilities = list;
    }

    public void setPlanSchedule(PlanSchedule planSchedule) {
        this.planSchedule = planSchedule;
    }

    public void setTransitionType(String str) {
        this.transitionType = str;
    }
}
