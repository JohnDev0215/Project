package com.amazon.stratus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Subscription implements Comparable<Subscription> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.Subscription");
    private List<Addon> addons;
    private Boolean autoRenew;
    private String baseCurrencyCode;
    private String cancelReason;
    private Date contractPeriodStartDate;
    private EmpDetails empDetails;
    private Date endDate;
    private Long graceDaysBeforeCancellation;
    private Boolean hasOpenProblems;
    private Boolean isMFARequired;
    private BigDecimal nextBillAmount;
    private Date nextBillDate;
    private PaymentInfo paymentInfo;
    private String paymentInstrumentId;
    private Plan plan;
    private String planCategory;
    private Plan rolloverPlan;
    private Date startDate;
    private String status;
    private String subscriptionId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Subscription subscription) {
        if (subscription == null) {
            return -1;
        }
        if (subscription == this) {
            return 0;
        }
        Boolean boolIsHasOpenProblems = isHasOpenProblems();
        Boolean boolIsHasOpenProblems2 = subscription.isHasOpenProblems();
        if (boolIsHasOpenProblems != boolIsHasOpenProblems2) {
            if (boolIsHasOpenProblems == null) {
                return -1;
            }
            if (boolIsHasOpenProblems2 == null) {
                return 1;
            }
            int iCompareTo = boolIsHasOpenProblems.compareTo(boolIsHasOpenProblems2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Date nextBillDate = getNextBillDate();
        Date nextBillDate2 = subscription.getNextBillDate();
        if (nextBillDate != nextBillDate2) {
            if (nextBillDate == null) {
                return -1;
            }
            if (nextBillDate2 == null) {
                return 1;
            }
            int iCompareTo2 = nextBillDate.compareTo(nextBillDate2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Date endDate = getEndDate();
        Date endDate2 = subscription.getEndDate();
        if (endDate != endDate2) {
            if (endDate == null) {
                return -1;
            }
            if (endDate2 == null) {
                return 1;
            }
            int iCompareTo3 = endDate.compareTo(endDate2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String cancelReason = getCancelReason();
        String cancelReason2 = subscription.getCancelReason();
        if (cancelReason != cancelReason2) {
            if (cancelReason == null) {
                return -1;
            }
            if (cancelReason2 == null) {
                return 1;
            }
            int iCompareTo4 = cancelReason.compareTo(cancelReason2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String subscriptionId = getSubscriptionId();
        String subscriptionId2 = subscription.getSubscriptionId();
        if (subscriptionId != subscriptionId2) {
            if (subscriptionId == null) {
                return -1;
            }
            if (subscriptionId2 == null) {
                return 1;
            }
            int iCompareTo5 = subscriptionId.compareTo(subscriptionId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Date startDate = getStartDate();
        Date startDate2 = subscription.getStartDate();
        if (startDate != startDate2) {
            if (startDate == null) {
                return -1;
            }
            if (startDate2 == null) {
                return 1;
            }
            int iCompareTo6 = startDate.compareTo(startDate2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Boolean boolIsAutoRenew = isAutoRenew();
        Boolean boolIsAutoRenew2 = subscription.isAutoRenew();
        if (boolIsAutoRenew != boolIsAutoRenew2) {
            if (boolIsAutoRenew == null) {
                return -1;
            }
            if (boolIsAutoRenew2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsAutoRenew.compareTo(boolIsAutoRenew2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Boolean boolIsIsMFARequired = isIsMFARequired();
        Boolean boolIsIsMFARequired2 = subscription.isIsMFARequired();
        if (boolIsIsMFARequired != boolIsIsMFARequired2) {
            if (boolIsIsMFARequired == null) {
                return -1;
            }
            if (boolIsIsMFARequired2 == null) {
                return 1;
            }
            int iCompareTo8 = boolIsIsMFARequired.compareTo(boolIsIsMFARequired2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        PaymentInfo paymentInfo = getPaymentInfo();
        PaymentInfo paymentInfo2 = subscription.getPaymentInfo();
        if (paymentInfo != paymentInfo2) {
            if (paymentInfo == null) {
                return -1;
            }
            if (paymentInfo2 == null) {
                return 1;
            }
            int iCompareTo9 = paymentInfo.compareTo(paymentInfo2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String planCategory = getPlanCategory();
        String planCategory2 = subscription.getPlanCategory();
        if (planCategory != planCategory2) {
            if (planCategory == null) {
                return -1;
            }
            if (planCategory2 == null) {
                return 1;
            }
            int iCompareTo10 = planCategory.compareTo(planCategory2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        BigDecimal nextBillAmount = getNextBillAmount();
        BigDecimal nextBillAmount2 = subscription.getNextBillAmount();
        if (nextBillAmount != nextBillAmount2) {
            if (nextBillAmount == null) {
                return -1;
            }
            if (nextBillAmount2 == null) {
                return 1;
            }
            int iCompareTo11 = nextBillAmount.compareTo(nextBillAmount2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String baseCurrencyCode = getBaseCurrencyCode();
        String baseCurrencyCode2 = subscription.getBaseCurrencyCode();
        if (baseCurrencyCode != baseCurrencyCode2) {
            if (baseCurrencyCode == null) {
                return -1;
            }
            if (baseCurrencyCode2 == null) {
                return 1;
            }
            int iCompareTo12 = baseCurrencyCode.compareTo(baseCurrencyCode2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        EmpDetails empDetails = getEmpDetails();
        EmpDetails empDetails2 = subscription.getEmpDetails();
        if (empDetails != empDetails2) {
            if (empDetails == null) {
                return -1;
            }
            if (empDetails2 == null) {
                return 1;
            }
            int iCompareTo13 = empDetails.compareTo(empDetails2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        List<Addon> addons = getAddons();
        List<Addon> addons2 = subscription.getAddons();
        if (addons != addons2) {
            if (addons == null) {
                return -1;
            }
            if (addons2 == null) {
                return 1;
            }
            if (addons instanceof Comparable) {
                int iCompareTo14 = ((Comparable) addons).compareTo(addons2);
                if (iCompareTo14 != 0) {
                    return iCompareTo14;
                }
            } else if (!addons.equals(addons2)) {
                int iHashCode = addons.hashCode();
                int iHashCode2 = addons2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String status = getStatus();
        String status2 = subscription.getStatus();
        if (status != status2) {
            if (status == null) {
                return -1;
            }
            if (status2 == null) {
                return 1;
            }
            int iCompareTo15 = status.compareTo(status2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Plan rolloverPlan = getRolloverPlan();
        Plan rolloverPlan2 = subscription.getRolloverPlan();
        if (rolloverPlan != rolloverPlan2) {
            if (rolloverPlan == null) {
                return -1;
            }
            if (rolloverPlan2 == null) {
                return 1;
            }
            int iCompareTo16 = rolloverPlan.compareTo(rolloverPlan2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        Long graceDaysBeforeCancellation = getGraceDaysBeforeCancellation();
        Long graceDaysBeforeCancellation2 = subscription.getGraceDaysBeforeCancellation();
        if (graceDaysBeforeCancellation != graceDaysBeforeCancellation2) {
            if (graceDaysBeforeCancellation == null) {
                return -1;
            }
            if (graceDaysBeforeCancellation2 == null) {
                return 1;
            }
            int iCompareTo17 = graceDaysBeforeCancellation.compareTo(graceDaysBeforeCancellation2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        Date contractPeriodStartDate = getContractPeriodStartDate();
        Date contractPeriodStartDate2 = subscription.getContractPeriodStartDate();
        if (contractPeriodStartDate != contractPeriodStartDate2) {
            if (contractPeriodStartDate == null) {
                return -1;
            }
            if (contractPeriodStartDate2 == null) {
                return 1;
            }
            int iCompareTo18 = contractPeriodStartDate.compareTo(contractPeriodStartDate2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        String paymentInstrumentId = getPaymentInstrumentId();
        String paymentInstrumentId2 = subscription.getPaymentInstrumentId();
        if (paymentInstrumentId != paymentInstrumentId2) {
            if (paymentInstrumentId == null) {
                return -1;
            }
            if (paymentInstrumentId2 == null) {
                return 1;
            }
            int iCompareTo19 = paymentInstrumentId.compareTo(paymentInstrumentId2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        Plan plan = getPlan();
        Plan plan2 = subscription.getPlan();
        if (plan != plan2) {
            if (plan == null) {
                return -1;
            }
            if (plan2 == null) {
                return 1;
            }
            int iCompareTo20 = plan.compareTo(plan2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Subscription)) {
            return false;
        }
        Subscription subscription = (Subscription) obj;
        return internalEqualityCheck(isHasOpenProblems(), subscription.isHasOpenProblems()) && internalEqualityCheck(getNextBillDate(), subscription.getNextBillDate()) && internalEqualityCheck(getEndDate(), subscription.getEndDate()) && internalEqualityCheck(getCancelReason(), subscription.getCancelReason()) && internalEqualityCheck(getSubscriptionId(), subscription.getSubscriptionId()) && internalEqualityCheck(getStartDate(), subscription.getStartDate()) && internalEqualityCheck(isAutoRenew(), subscription.isAutoRenew()) && internalEqualityCheck(isIsMFARequired(), subscription.isIsMFARequired()) && internalEqualityCheck(getPaymentInfo(), subscription.getPaymentInfo()) && internalEqualityCheck(getPlanCategory(), subscription.getPlanCategory()) && internalEqualityCheck(getNextBillAmount(), subscription.getNextBillAmount()) && internalEqualityCheck(getBaseCurrencyCode(), subscription.getBaseCurrencyCode()) && internalEqualityCheck(getEmpDetails(), subscription.getEmpDetails()) && internalEqualityCheck(getAddons(), subscription.getAddons()) && internalEqualityCheck(getStatus(), subscription.getStatus()) && internalEqualityCheck(getRolloverPlan(), subscription.getRolloverPlan()) && internalEqualityCheck(getGraceDaysBeforeCancellation(), subscription.getGraceDaysBeforeCancellation()) && internalEqualityCheck(getContractPeriodStartDate(), subscription.getContractPeriodStartDate()) && internalEqualityCheck(getPaymentInstrumentId(), subscription.getPaymentInstrumentId()) && internalEqualityCheck(getPlan(), subscription.getPlan());
    }

    public List<Addon> getAddons() {
        return this.addons;
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

    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    public String getPaymentInstrumentId() {
        return this.paymentInstrumentId;
    }

    public Plan getPlan() {
        return this.plan;
    }

    public String getPlanCategory() {
        return this.planCategory;
    }

    public Plan getRolloverPlan() {
        return this.rolloverPlan;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public String getStatus() {
        return this.status;
    }

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isHasOpenProblems(), getNextBillDate(), getEndDate(), getCancelReason(), getSubscriptionId(), getStartDate(), isAutoRenew(), isIsMFARequired(), getPaymentInfo(), getPlanCategory(), getNextBillAmount(), getBaseCurrencyCode(), getEmpDetails(), getAddons(), getStatus(), getRolloverPlan(), getGraceDaysBeforeCancellation(), getContractPeriodStartDate(), getPaymentInstrumentId(), getPlan());
    }

    public Boolean isAutoRenew() {
        return this.autoRenew;
    }

    public Boolean isHasOpenProblems() {
        return this.hasOpenProblems;
    }

    public Boolean isIsMFARequired() {
        return this.isMFARequired;
    }

    public void setAddons(List<Addon> list) {
        this.addons = list;
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

    public void setIsMFARequired(Boolean bool) {
        this.isMFARequired = bool;
    }

    public void setNextBillAmount(BigDecimal bigDecimal) {
        this.nextBillAmount = bigDecimal;
    }

    public void setNextBillDate(Date date) {
        this.nextBillDate = date;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public void setPaymentInstrumentId(String str) {
        this.paymentInstrumentId = str;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public void setPlanCategory(String str) {
        this.planCategory = str;
    }

    public void setRolloverPlan(Plan plan) {
        this.rolloverPlan = plan;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setSubscriptionId(String str) {
        this.subscriptionId = str;
    }
}
