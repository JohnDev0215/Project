package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Addon implements Comparable<Addon> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.Addon");
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
    public int compareTo(@Deprecated Addon addon) {
        if (addon == null) {
            return -1;
        }
        if (addon == this) {
            return 0;
        }
        Eligibility eligibility = getEligibility();
        Eligibility eligibility2 = addon.getEligibility();
        if (eligibility != eligibility2) {
            if (eligibility == null) {
                return -1;
            }
            if (eligibility2 == null) {
                return 1;
            }
            int iCompareTo = eligibility.compareTo(eligibility2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Buyability buyability = getBuyability();
        Buyability buyability2 = addon.getBuyability();
        if (buyability != buyability2) {
            if (buyability == null) {
                return -1;
            }
            if (buyability2 == null) {
                return 1;
            }
            int iCompareTo2 = buyability.compareTo(buyability2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<PartnerPromotionEligibility> partnerPromotionEligibilities = getPartnerPromotionEligibilities();
        List<PartnerPromotionEligibility> partnerPromotionEligibilities2 = addon.getPartnerPromotionEligibilities();
        if (partnerPromotionEligibilities != partnerPromotionEligibilities2) {
            if (partnerPromotionEligibilities == null) {
                return -1;
            }
            if (partnerPromotionEligibilities2 == null) {
                return 1;
            }
            if (partnerPromotionEligibilities instanceof Comparable) {
                int iCompareTo3 = ((Comparable) partnerPromotionEligibilities).compareTo(partnerPromotionEligibilities2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
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
        String transitionType2 = addon.getTransitionType();
        if (transitionType != transitionType2) {
            if (transitionType == null) {
                return -1;
            }
            if (transitionType2 == null) {
                return 1;
            }
            int iCompareTo4 = transitionType.compareTo(transitionType2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        PlanSchedule planSchedule = getPlanSchedule();
        PlanSchedule planSchedule2 = addon.getPlanSchedule();
        if (planSchedule != planSchedule2) {
            if (planSchedule == null) {
                return -1;
            }
            if (planSchedule2 == null) {
                return 1;
            }
            int iCompareTo5 = planSchedule.compareTo(planSchedule2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Addon)) {
            return false;
        }
        Addon addon = (Addon) obj;
        return internalEqualityCheck(getEligibility(), addon.getEligibility()) && internalEqualityCheck(getBuyability(), addon.getBuyability()) && internalEqualityCheck(getPartnerPromotionEligibilities(), addon.getPartnerPromotionEligibilities()) && internalEqualityCheck(getTransitionType(), addon.getTransitionType()) && internalEqualityCheck(getPlanSchedule(), addon.getPlanSchedule());
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
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getEligibility(), getBuyability(), getPartnerPromotionEligibilities(), getTransitionType(), getPlanSchedule());
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
