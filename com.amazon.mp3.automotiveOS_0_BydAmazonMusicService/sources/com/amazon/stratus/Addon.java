package com.amazon.stratus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Addon implements Comparable<Addon> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.Addon");
    private String addonId;
    private Boolean autoRenew;
    private String baseCurrencyCode;
    private String cancelReason;
    private Date contractPeriodStartDate;
    private EmpDetails empDetails;
    private Date endDate;
    private Long graceDaysBeforeCancellation;
    private Boolean hasOpenProblems;
    private Boolean isInSyncWithBaseSubscription;
    private Boolean isMFARequired;
    private BigDecimal nextBillAmount;
    private Date nextBillDate;
    private String planCategory;
    private List<Plan> plans;
    private Date startDate;
    private String status;
    private Date tentativeEndDate;

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
        Boolean boolIsAutoRenew = isAutoRenew();
        Boolean boolIsAutoRenew2 = addon.isAutoRenew();
        if (boolIsAutoRenew != boolIsAutoRenew2) {
            if (boolIsAutoRenew == null) {
                return -1;
            }
            if (boolIsAutoRenew2 == null) {
                return 1;
            }
            int iCompareTo = boolIsAutoRenew.compareTo(boolIsAutoRenew2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Plan> plans = getPlans();
        List<Plan> plans2 = addon.getPlans();
        if (plans != plans2) {
            if (plans == null) {
                return -1;
            }
            if (plans2 == null) {
                return 1;
            }
            if (plans instanceof Comparable) {
                int iCompareTo2 = ((Comparable) plans).compareTo(plans2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!plans.equals(plans2)) {
                int iHashCode = plans.hashCode();
                int iHashCode2 = plans2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Boolean boolIsIsInSyncWithBaseSubscription = isIsInSyncWithBaseSubscription();
        Boolean boolIsIsInSyncWithBaseSubscription2 = addon.isIsInSyncWithBaseSubscription();
        if (boolIsIsInSyncWithBaseSubscription != boolIsIsInSyncWithBaseSubscription2) {
            if (boolIsIsInSyncWithBaseSubscription == null) {
                return -1;
            }
            if (boolIsIsInSyncWithBaseSubscription2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsIsInSyncWithBaseSubscription.compareTo(boolIsIsInSyncWithBaseSubscription2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Date startDate = getStartDate();
        Date startDate2 = addon.getStartDate();
        if (startDate != startDate2) {
            if (startDate == null) {
                return -1;
            }
            if (startDate2 == null) {
                return 1;
            }
            int iCompareTo4 = startDate.compareTo(startDate2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        BigDecimal nextBillAmount = getNextBillAmount();
        BigDecimal nextBillAmount2 = addon.getNextBillAmount();
        if (nextBillAmount != nextBillAmount2) {
            if (nextBillAmount == null) {
                return -1;
            }
            if (nextBillAmount2 == null) {
                return 1;
            }
            int iCompareTo5 = nextBillAmount.compareTo(nextBillAmount2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String baseCurrencyCode = getBaseCurrencyCode();
        String baseCurrencyCode2 = addon.getBaseCurrencyCode();
        if (baseCurrencyCode != baseCurrencyCode2) {
            if (baseCurrencyCode == null) {
                return -1;
            }
            if (baseCurrencyCode2 == null) {
                return 1;
            }
            int iCompareTo6 = baseCurrencyCode.compareTo(baseCurrencyCode2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String cancelReason = getCancelReason();
        String cancelReason2 = addon.getCancelReason();
        if (cancelReason != cancelReason2) {
            if (cancelReason == null) {
                return -1;
            }
            if (cancelReason2 == null) {
                return 1;
            }
            int iCompareTo7 = cancelReason.compareTo(cancelReason2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String planCategory = getPlanCategory();
        String planCategory2 = addon.getPlanCategory();
        if (planCategory != planCategory2) {
            if (planCategory == null) {
                return -1;
            }
            if (planCategory2 == null) {
                return 1;
            }
            int iCompareTo8 = planCategory.compareTo(planCategory2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        EmpDetails empDetails = getEmpDetails();
        EmpDetails empDetails2 = addon.getEmpDetails();
        if (empDetails != empDetails2) {
            if (empDetails == null) {
                return -1;
            }
            if (empDetails2 == null) {
                return 1;
            }
            int iCompareTo9 = empDetails.compareTo(empDetails2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String addonId = getAddonId();
        String addonId2 = addon.getAddonId();
        if (addonId != addonId2) {
            if (addonId == null) {
                return -1;
            }
            if (addonId2 == null) {
                return 1;
            }
            int iCompareTo10 = addonId.compareTo(addonId2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Boolean boolIsHasOpenProblems = isHasOpenProblems();
        Boolean boolIsHasOpenProblems2 = addon.isHasOpenProblems();
        if (boolIsHasOpenProblems != boolIsHasOpenProblems2) {
            if (boolIsHasOpenProblems == null) {
                return -1;
            }
            if (boolIsHasOpenProblems2 == null) {
                return 1;
            }
            int iCompareTo11 = boolIsHasOpenProblems.compareTo(boolIsHasOpenProblems2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Date nextBillDate = getNextBillDate();
        Date nextBillDate2 = addon.getNextBillDate();
        if (nextBillDate != nextBillDate2) {
            if (nextBillDate == null) {
                return -1;
            }
            if (nextBillDate2 == null) {
                return 1;
            }
            int iCompareTo12 = nextBillDate.compareTo(nextBillDate2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Date endDate = getEndDate();
        Date endDate2 = addon.getEndDate();
        if (endDate != endDate2) {
            if (endDate == null) {
                return -1;
            }
            if (endDate2 == null) {
                return 1;
            }
            int iCompareTo13 = endDate.compareTo(endDate2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Long graceDaysBeforeCancellation = getGraceDaysBeforeCancellation();
        Long graceDaysBeforeCancellation2 = addon.getGraceDaysBeforeCancellation();
        if (graceDaysBeforeCancellation != graceDaysBeforeCancellation2) {
            if (graceDaysBeforeCancellation == null) {
                return -1;
            }
            if (graceDaysBeforeCancellation2 == null) {
                return 1;
            }
            int iCompareTo14 = graceDaysBeforeCancellation.compareTo(graceDaysBeforeCancellation2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        Date tentativeEndDate = getTentativeEndDate();
        Date tentativeEndDate2 = addon.getTentativeEndDate();
        if (tentativeEndDate != tentativeEndDate2) {
            if (tentativeEndDate == null) {
                return -1;
            }
            if (tentativeEndDate2 == null) {
                return 1;
            }
            int iCompareTo15 = tentativeEndDate.compareTo(tentativeEndDate2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Date contractPeriodStartDate = getContractPeriodStartDate();
        Date contractPeriodStartDate2 = addon.getContractPeriodStartDate();
        if (contractPeriodStartDate != contractPeriodStartDate2) {
            if (contractPeriodStartDate == null) {
                return -1;
            }
            if (contractPeriodStartDate2 == null) {
                return 1;
            }
            int iCompareTo16 = contractPeriodStartDate.compareTo(contractPeriodStartDate2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String status = getStatus();
        String status2 = addon.getStatus();
        if (status != status2) {
            if (status == null) {
                return -1;
            }
            if (status2 == null) {
                return 1;
            }
            int iCompareTo17 = status.compareTo(status2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        Boolean boolIsIsMFARequired = isIsMFARequired();
        Boolean boolIsIsMFARequired2 = addon.isIsMFARequired();
        if (boolIsIsMFARequired != boolIsIsMFARequired2) {
            if (boolIsIsMFARequired == null) {
                return -1;
            }
            if (boolIsIsMFARequired2 == null) {
                return 1;
            }
            int iCompareTo18 = boolIsIsMFARequired.compareTo(boolIsIsMFARequired2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Addon)) {
            return false;
        }
        Addon addon = (Addon) obj;
        return internalEqualityCheck(isAutoRenew(), addon.isAutoRenew()) && internalEqualityCheck(getPlans(), addon.getPlans()) && internalEqualityCheck(isIsInSyncWithBaseSubscription(), addon.isIsInSyncWithBaseSubscription()) && internalEqualityCheck(getStartDate(), addon.getStartDate()) && internalEqualityCheck(getNextBillAmount(), addon.getNextBillAmount()) && internalEqualityCheck(getBaseCurrencyCode(), addon.getBaseCurrencyCode()) && internalEqualityCheck(getCancelReason(), addon.getCancelReason()) && internalEqualityCheck(getPlanCategory(), addon.getPlanCategory()) && internalEqualityCheck(getEmpDetails(), addon.getEmpDetails()) && internalEqualityCheck(getAddonId(), addon.getAddonId()) && internalEqualityCheck(isHasOpenProblems(), addon.isHasOpenProblems()) && internalEqualityCheck(getNextBillDate(), addon.getNextBillDate()) && internalEqualityCheck(getEndDate(), addon.getEndDate()) && internalEqualityCheck(getGraceDaysBeforeCancellation(), addon.getGraceDaysBeforeCancellation()) && internalEqualityCheck(getTentativeEndDate(), addon.getTentativeEndDate()) && internalEqualityCheck(getContractPeriodStartDate(), addon.getContractPeriodStartDate()) && internalEqualityCheck(getStatus(), addon.getStatus()) && internalEqualityCheck(isIsMFARequired(), addon.isIsMFARequired());
    }

    public String getAddonId() {
        return this.addonId;
    }

    public String getBaseCurrencyCode() {
        return this.baseCurrencyCode;
    }

    public String getCancelReason() {
        return this.cancelReason;
    }

    public Date getContractPeriodStartDate() {
        return this.contractPeriodStartDate;
    }

    public EmpDetails getEmpDetails() {
        return this.empDetails;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public Long getGraceDaysBeforeCancellation() {
        return this.graceDaysBeforeCancellation;
    }

    public BigDecimal getNextBillAmount() {
        return this.nextBillAmount;
    }

    public Date getNextBillDate() {
        return this.nextBillDate;
    }

    public String getPlanCategory() {
        return this.planCategory;
    }

    public List<Plan> getPlans() {
        return this.plans;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public String getStatus() {
        return this.status;
    }

    public Date getTentativeEndDate() {
        return this.tentativeEndDate;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isAutoRenew(), getPlans(), isIsInSyncWithBaseSubscription(), getStartDate(), getNextBillAmount(), getBaseCurrencyCode(), getCancelReason(), getPlanCategory(), getEmpDetails(), getAddonId(), isHasOpenProblems(), getNextBillDate(), getEndDate(), getGraceDaysBeforeCancellation(), getTentativeEndDate(), getContractPeriodStartDate(), getStatus(), isIsMFARequired());
    }

    public Boolean isAutoRenew() {
        return this.autoRenew;
    }

    public Boolean isHasOpenProblems() {
        return this.hasOpenProblems;
    }

    public Boolean isIsInSyncWithBaseSubscription() {
        return this.isInSyncWithBaseSubscription;
    }

    public Boolean isIsMFARequired() {
        return this.isMFARequired;
    }

    public void setAddonId(String str) {
        this.addonId = str;
    }

    public void setAutoRenew(Boolean bool) {
        this.autoRenew = bool;
    }

    public void setBaseCurrencyCode(String str) {
        this.baseCurrencyCode = str;
    }

    public void setCancelReason(String str) {
        this.cancelReason = str;
    }

    public void setContractPeriodStartDate(Date date) {
        this.contractPeriodStartDate = date;
    }

    public void setEmpDetails(EmpDetails empDetails) {
        this.empDetails = empDetails;
    }

    public void setEndDate(Date date) {
        this.endDate = date;
    }

    public void setGraceDaysBeforeCancellation(Long l) {
        this.graceDaysBeforeCancellation = l;
    }

    public void setHasOpenProblems(Boolean bool) {
        this.hasOpenProblems = bool;
    }

    public void setIsInSyncWithBaseSubscription(Boolean bool) {
        this.isInSyncWithBaseSubscription = bool;
    }

    public void setIsMFARequired(Boolean bool) {
        this.isMFARequired = bool;
    }

    public void setNextBillAmount(BigDecimal bigDecimal) {
        this.nextBillAmount = bigDecimal;
    }

    public void setNextBillDate(Date date) {
        this.nextBillDate = date;
    }

    public void setPlanCategory(String str) {
        this.planCategory = str;
    }

    public void setPlans(List<Plan> list) {
        this.plans = list;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTentativeEndDate(Date date) {
        this.tentativeEndDate = date;
    }
}
