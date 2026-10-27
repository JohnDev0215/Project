package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class PlanOffer implements Comparable<PlanOffer> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.PlanOffer");
    private List<String> acquisitionGateways;
    private Period baseCommitmentPeriod;
    private List<BillingPeriod> billingSchedule;
    private Period commitmentPeriod;
    private String contractType;
    private DisplayableInfo displayableInfo;
    private String externalPaymentIdentifier;
    private Integer index;
    private String planId;
    private List<String> principalOwnershipType;
    private Promotion promotion;
    private RenewalPolicy renewalPolicy;
    private List<SalesPromotionInfo> salesPromotions;
    private String serviceLevel;
    private String shortPlanId;

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
        Promotion promotion = getPromotion();
        Promotion promotion2 = planOffer.getPromotion();
        if (promotion != promotion2) {
            if (promotion == null) {
                return -1;
            }
            if (promotion2 == null) {
                return 1;
            }
            int iCompareTo = promotion.compareTo(promotion2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Integer index = getIndex();
        Integer index2 = planOffer.getIndex();
        if (index != index2) {
            if (index == null) {
                return -1;
            }
            if (index2 == null) {
                return 1;
            }
            int iCompareTo2 = index.compareTo(index2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String contractType = getContractType();
        String contractType2 = planOffer.getContractType();
        if (contractType != contractType2) {
            if (contractType == null) {
                return -1;
            }
            if (contractType2 == null) {
                return 1;
            }
            int iCompareTo3 = contractType.compareTo(contractType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<String> principalOwnershipType = getPrincipalOwnershipType();
        List<String> principalOwnershipType2 = planOffer.getPrincipalOwnershipType();
        if (principalOwnershipType != principalOwnershipType2) {
            if (principalOwnershipType == null) {
                return -1;
            }
            if (principalOwnershipType2 == null) {
                return 1;
            }
            if (principalOwnershipType instanceof Comparable) {
                int iCompareTo4 = ((Comparable) principalOwnershipType).compareTo(principalOwnershipType2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!principalOwnershipType.equals(principalOwnershipType2)) {
                int iHashCode = principalOwnershipType.hashCode();
                int iHashCode2 = principalOwnershipType2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Period baseCommitmentPeriod = getBaseCommitmentPeriod();
        Period baseCommitmentPeriod2 = planOffer.getBaseCommitmentPeriod();
        if (baseCommitmentPeriod != baseCommitmentPeriod2) {
            if (baseCommitmentPeriod == null) {
                return -1;
            }
            if (baseCommitmentPeriod2 == null) {
                return 1;
            }
            int iCompareTo5 = baseCommitmentPeriod.compareTo(baseCommitmentPeriod2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        RenewalPolicy renewalPolicy = getRenewalPolicy();
        RenewalPolicy renewalPolicy2 = planOffer.getRenewalPolicy();
        if (renewalPolicy != renewalPolicy2) {
            if (renewalPolicy == null) {
                return -1;
            }
            if (renewalPolicy2 == null) {
                return 1;
            }
            int iCompareTo6 = renewalPolicy.compareTo(renewalPolicy2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Period commitmentPeriod = getCommitmentPeriod();
        Period commitmentPeriod2 = planOffer.getCommitmentPeriod();
        if (commitmentPeriod != commitmentPeriod2) {
            if (commitmentPeriod == null) {
                return -1;
            }
            if (commitmentPeriod2 == null) {
                return 1;
            }
            int iCompareTo7 = commitmentPeriod.compareTo(commitmentPeriod2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        List<BillingPeriod> billingSchedule = getBillingSchedule();
        List<BillingPeriod> billingSchedule2 = planOffer.getBillingSchedule();
        if (billingSchedule != billingSchedule2) {
            if (billingSchedule == null) {
                return -1;
            }
            if (billingSchedule2 == null) {
                return 1;
            }
            if (billingSchedule instanceof Comparable) {
                int iCompareTo8 = ((Comparable) billingSchedule).compareTo(billingSchedule2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!billingSchedule.equals(billingSchedule2)) {
                int iHashCode3 = billingSchedule.hashCode();
                int iHashCode4 = billingSchedule2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<SalesPromotionInfo> salesPromotions = getSalesPromotions();
        List<SalesPromotionInfo> salesPromotions2 = planOffer.getSalesPromotions();
        if (salesPromotions != salesPromotions2) {
            if (salesPromotions == null) {
                return -1;
            }
            if (salesPromotions2 == null) {
                return 1;
            }
            if (salesPromotions instanceof Comparable) {
                int iCompareTo9 = ((Comparable) salesPromotions).compareTo(salesPromotions2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!salesPromotions.equals(salesPromotions2)) {
                int iHashCode5 = salesPromotions.hashCode();
                int iHashCode6 = salesPromotions2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        DisplayableInfo displayableInfo = getDisplayableInfo();
        DisplayableInfo displayableInfo2 = planOffer.getDisplayableInfo();
        if (displayableInfo != displayableInfo2) {
            if (displayableInfo == null) {
                return -1;
            }
            if (displayableInfo2 == null) {
                return 1;
            }
            int iCompareTo10 = displayableInfo.compareTo(displayableInfo2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String planId = getPlanId();
        String planId2 = planOffer.getPlanId();
        if (planId != planId2) {
            if (planId == null) {
                return -1;
            }
            if (planId2 == null) {
                return 1;
            }
            int iCompareTo11 = planId.compareTo(planId2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String serviceLevel = getServiceLevel();
        String serviceLevel2 = planOffer.getServiceLevel();
        if (serviceLevel != serviceLevel2) {
            if (serviceLevel == null) {
                return -1;
            }
            if (serviceLevel2 == null) {
                return 1;
            }
            int iCompareTo12 = serviceLevel.compareTo(serviceLevel2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String shortPlanId = getShortPlanId();
        String shortPlanId2 = planOffer.getShortPlanId();
        if (shortPlanId != shortPlanId2) {
            if (shortPlanId == null) {
                return -1;
            }
            if (shortPlanId2 == null) {
                return 1;
            }
            int iCompareTo13 = shortPlanId.compareTo(shortPlanId2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String externalPaymentIdentifier = getExternalPaymentIdentifier();
        String externalPaymentIdentifier2 = planOffer.getExternalPaymentIdentifier();
        if (externalPaymentIdentifier != externalPaymentIdentifier2) {
            if (externalPaymentIdentifier == null) {
                return -1;
            }
            if (externalPaymentIdentifier2 == null) {
                return 1;
            }
            int iCompareTo14 = externalPaymentIdentifier.compareTo(externalPaymentIdentifier2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        List<String> acquisitionGateways = getAcquisitionGateways();
        List<String> acquisitionGateways2 = planOffer.getAcquisitionGateways();
        if (acquisitionGateways != acquisitionGateways2) {
            if (acquisitionGateways == null) {
                return -1;
            }
            if (acquisitionGateways2 == null) {
                return 1;
            }
            if (acquisitionGateways instanceof Comparable) {
                int iCompareTo15 = ((Comparable) acquisitionGateways).compareTo(acquisitionGateways2);
                if (iCompareTo15 != 0) {
                    return iCompareTo15;
                }
            } else if (!acquisitionGateways.equals(acquisitionGateways2)) {
                int iHashCode7 = acquisitionGateways.hashCode();
                int iHashCode8 = acquisitionGateways2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlanOffer)) {
            return false;
        }
        PlanOffer planOffer = (PlanOffer) obj;
        return internalEqualityCheck(getPromotion(), planOffer.getPromotion()) && internalEqualityCheck(getIndex(), planOffer.getIndex()) && internalEqualityCheck(getContractType(), planOffer.getContractType()) && internalEqualityCheck(getPrincipalOwnershipType(), planOffer.getPrincipalOwnershipType()) && internalEqualityCheck(getBaseCommitmentPeriod(), planOffer.getBaseCommitmentPeriod()) && internalEqualityCheck(getRenewalPolicy(), planOffer.getRenewalPolicy()) && internalEqualityCheck(getCommitmentPeriod(), planOffer.getCommitmentPeriod()) && internalEqualityCheck(getBillingSchedule(), planOffer.getBillingSchedule()) && internalEqualityCheck(getSalesPromotions(), planOffer.getSalesPromotions()) && internalEqualityCheck(getDisplayableInfo(), planOffer.getDisplayableInfo()) && internalEqualityCheck(getPlanId(), planOffer.getPlanId()) && internalEqualityCheck(getServiceLevel(), planOffer.getServiceLevel()) && internalEqualityCheck(getShortPlanId(), planOffer.getShortPlanId()) && internalEqualityCheck(getExternalPaymentIdentifier(), planOffer.getExternalPaymentIdentifier()) && internalEqualityCheck(getAcquisitionGateways(), planOffer.getAcquisitionGateways());
    }

    public List<String> getAcquisitionGateways() {
        return this.acquisitionGateways;
    }

    public Period getBaseCommitmentPeriod() {
        return this.baseCommitmentPeriod;
    }

    public List<BillingPeriod> getBillingSchedule() {
        return this.billingSchedule;
    }

    public Period getCommitmentPeriod() {
        return this.commitmentPeriod;
    }

    public String getContractType() {
        return this.contractType;
    }

    public DisplayableInfo getDisplayableInfo() {
        return this.displayableInfo;
    }

    public String getExternalPaymentIdentifier() {
        return this.externalPaymentIdentifier;
    }

    public Integer getIndex() {
        return this.index;
    }

    public String getPlanId() {
        return this.planId;
    }

    public List<String> getPrincipalOwnershipType() {
        return this.principalOwnershipType;
    }

    public Promotion getPromotion() {
        return this.promotion;
    }

    public RenewalPolicy getRenewalPolicy() {
        return this.renewalPolicy;
    }

    public List<SalesPromotionInfo> getSalesPromotions() {
        return this.salesPromotions;
    }

    public String getServiceLevel() {
        return this.serviceLevel;
    }

    public String getShortPlanId() {
        return this.shortPlanId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPromotion(), getIndex(), getContractType(), getPrincipalOwnershipType(), getBaseCommitmentPeriod(), getRenewalPolicy(), getCommitmentPeriod(), getBillingSchedule(), getSalesPromotions(), getDisplayableInfo(), getPlanId(), getServiceLevel(), getShortPlanId(), getExternalPaymentIdentifier(), getAcquisitionGateways());
    }

    public void setAcquisitionGateways(List<String> list) {
        this.acquisitionGateways = list;
    }

    public void setBaseCommitmentPeriod(Period period) {
        this.baseCommitmentPeriod = period;
    }

    public void setBillingSchedule(List<BillingPeriod> list) {
        this.billingSchedule = list;
    }

    public void setCommitmentPeriod(Period period) {
        this.commitmentPeriod = period;
    }

    public void setContractType(String str) {
        this.contractType = str;
    }

    public void setDisplayableInfo(DisplayableInfo displayableInfo) {
        this.displayableInfo = displayableInfo;
    }

    public void setExternalPaymentIdentifier(String str) {
        this.externalPaymentIdentifier = str;
    }

    public void setIndex(Integer num) {
        this.index = num;
    }

    public void setPlanId(String str) {
        this.planId = str;
    }

    public void setPrincipalOwnershipType(List<String> list) {
        this.principalOwnershipType = list;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public void setRenewalPolicy(RenewalPolicy renewalPolicy) {
        this.renewalPolicy = renewalPolicy;
    }

    public void setSalesPromotions(List<SalesPromotionInfo> list) {
        this.salesPromotions = list;
    }

    public void setServiceLevel(String str) {
        this.serviceLevel = str;
    }

    public void setShortPlanId(String str) {
        this.shortPlanId = str;
    }
}
