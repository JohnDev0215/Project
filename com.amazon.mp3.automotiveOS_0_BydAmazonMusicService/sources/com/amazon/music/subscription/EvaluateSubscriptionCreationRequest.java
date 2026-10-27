package com.amazon.music.subscription;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.stratus.PaymentInfo;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class EvaluateSubscriptionCreationRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.EvaluateSubscriptionCreationRequest");
    private String associateTagId;
    private Boolean autoRenew;
    private PriceWithCurrency displayPrice;
    private String empSku;
    private String ipAddress;
    private String ipv6Address;
    private String musicTerritory;
    private PaymentInfo paymentInfo;
    private String planType;
    private String rolloverPlanType;
    private String scheduleId;
    private String sessionId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof EvaluateSubscriptionCreationRequest)) {
            return 1;
        }
        EvaluateSubscriptionCreationRequest evaluateSubscriptionCreationRequest = (EvaluateSubscriptionCreationRequest) cirrusBaseRequestV2;
        String sessionId = getSessionId();
        String sessionId2 = evaluateSubscriptionCreationRequest.getSessionId();
        if (sessionId != sessionId2) {
            if (sessionId == null) {
                return -1;
            }
            if (sessionId2 == null) {
                return 1;
            }
            int iCompareTo = sessionId.compareTo(sessionId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String rolloverPlanType = getRolloverPlanType();
        String rolloverPlanType2 = evaluateSubscriptionCreationRequest.getRolloverPlanType();
        if (rolloverPlanType != rolloverPlanType2) {
            if (rolloverPlanType == null) {
                return -1;
            }
            if (rolloverPlanType2 == null) {
                return 1;
            }
            int iCompareTo2 = rolloverPlanType.compareTo(rolloverPlanType2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String ipAddress = getIpAddress();
        String ipAddress2 = evaluateSubscriptionCreationRequest.getIpAddress();
        if (ipAddress != ipAddress2) {
            if (ipAddress == null) {
                return -1;
            }
            if (ipAddress2 == null) {
                return 1;
            }
            int iCompareTo3 = ipAddress.compareTo(ipAddress2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsAutoRenew = isAutoRenew();
        Boolean boolIsAutoRenew2 = evaluateSubscriptionCreationRequest.isAutoRenew();
        if (boolIsAutoRenew != boolIsAutoRenew2) {
            if (boolIsAutoRenew == null) {
                return -1;
            }
            if (boolIsAutoRenew2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsAutoRenew.compareTo(boolIsAutoRenew2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        PriceWithCurrency displayPrice = getDisplayPrice();
        PriceWithCurrency displayPrice2 = evaluateSubscriptionCreationRequest.getDisplayPrice();
        if (displayPrice != displayPrice2) {
            if (displayPrice == null) {
                return -1;
            }
            if (displayPrice2 == null) {
                return 1;
            }
            int iCompareTo5 = displayPrice.compareTo(displayPrice2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String associateTagId = getAssociateTagId();
        String associateTagId2 = evaluateSubscriptionCreationRequest.getAssociateTagId();
        if (associateTagId != associateTagId2) {
            if (associateTagId == null) {
                return -1;
            }
            if (associateTagId2 == null) {
                return 1;
            }
            int iCompareTo6 = associateTagId.compareTo(associateTagId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        PaymentInfo paymentInfo = getPaymentInfo();
        PaymentInfo paymentInfo2 = evaluateSubscriptionCreationRequest.getPaymentInfo();
        if (paymentInfo != paymentInfo2) {
            if (paymentInfo == null) {
                return -1;
            }
            if (paymentInfo2 == null) {
                return 1;
            }
            int iCompareTo7 = paymentInfo.compareTo(paymentInfo2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String empSku = getEmpSku();
        String empSku2 = evaluateSubscriptionCreationRequest.getEmpSku();
        if (empSku != empSku2) {
            if (empSku == null) {
                return -1;
            }
            if (empSku2 == null) {
                return 1;
            }
            int iCompareTo8 = empSku.compareTo(empSku2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String scheduleId = getScheduleId();
        String scheduleId2 = evaluateSubscriptionCreationRequest.getScheduleId();
        if (scheduleId != scheduleId2) {
            if (scheduleId == null) {
                return -1;
            }
            if (scheduleId2 == null) {
                return 1;
            }
            int iCompareTo9 = scheduleId.compareTo(scheduleId2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String planType = getPlanType();
        String planType2 = evaluateSubscriptionCreationRequest.getPlanType();
        if (planType != planType2) {
            if (planType == null) {
                return -1;
            }
            if (planType2 == null) {
                return 1;
            }
            int iCompareTo10 = planType.compareTo(planType2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String ipv6Address = getIpv6Address();
        String ipv6Address2 = evaluateSubscriptionCreationRequest.getIpv6Address();
        if (ipv6Address != ipv6Address2) {
            if (ipv6Address == null) {
                return -1;
            }
            if (ipv6Address2 == null) {
                return 1;
            }
            int iCompareTo11 = ipv6Address.compareTo(ipv6Address2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = evaluateSubscriptionCreationRequest.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo12 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof EvaluateSubscriptionCreationRequest)) {
            return false;
        }
        EvaluateSubscriptionCreationRequest evaluateSubscriptionCreationRequest = (EvaluateSubscriptionCreationRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getSessionId(), evaluateSubscriptionCreationRequest.getSessionId()) && internalEqualityCheck(getRolloverPlanType(), evaluateSubscriptionCreationRequest.getRolloverPlanType()) && internalEqualityCheck(getIpAddress(), evaluateSubscriptionCreationRequest.getIpAddress()) && internalEqualityCheck(isAutoRenew(), evaluateSubscriptionCreationRequest.isAutoRenew()) && internalEqualityCheck(getDisplayPrice(), evaluateSubscriptionCreationRequest.getDisplayPrice()) && internalEqualityCheck(getAssociateTagId(), evaluateSubscriptionCreationRequest.getAssociateTagId()) && internalEqualityCheck(getPaymentInfo(), evaluateSubscriptionCreationRequest.getPaymentInfo()) && internalEqualityCheck(getEmpSku(), evaluateSubscriptionCreationRequest.getEmpSku()) && internalEqualityCheck(getScheduleId(), evaluateSubscriptionCreationRequest.getScheduleId()) && internalEqualityCheck(getPlanType(), evaluateSubscriptionCreationRequest.getPlanType()) && internalEqualityCheck(getIpv6Address(), evaluateSubscriptionCreationRequest.getIpv6Address()) && internalEqualityCheck(getMusicTerritory(), evaluateSubscriptionCreationRequest.getMusicTerritory());
    }

    public String getAssociateTagId() {
        return this.associateTagId;
    }

    public PriceWithCurrency getDisplayPrice() {
        return this.displayPrice;
    }

    public String getEmpSku() {
        return this.empSku;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getIpv6Address() {
        return this.ipv6Address;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    public String getPlanType() {
        return this.planType;
    }

    public String getRolloverPlanType() {
        return this.rolloverPlanType;
    }

    public String getScheduleId() {
        return this.scheduleId;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSessionId(), getRolloverPlanType(), getIpAddress(), isAutoRenew(), getDisplayPrice(), getAssociateTagId(), getPaymentInfo(), getEmpSku(), getScheduleId(), getPlanType(), getIpv6Address(), getMusicTerritory());
    }

    public Boolean isAutoRenew() {
        return this.autoRenew;
    }

    public void setAssociateTagId(String str) {
        this.associateTagId = str;
    }

    public void setAutoRenew(Boolean bool) {
        this.autoRenew = bool;
    }

    public void setDisplayPrice(PriceWithCurrency priceWithCurrency) {
        this.displayPrice = priceWithCurrency;
    }

    public void setEmpSku(String str) {
        this.empSku = str;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public void setScheduleId(String str) {
        this.scheduleId = str;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }
}
