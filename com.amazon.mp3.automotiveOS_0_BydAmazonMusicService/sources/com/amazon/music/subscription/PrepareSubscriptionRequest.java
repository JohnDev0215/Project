package com.amazon.music.subscription;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.stratus.PaymentInfo;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PrepareSubscriptionRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.PrepareSubscriptionRequest");
    private List<String> addonScheduleIds;
    private Boolean forceAuthenticationRequired;
    private String ipAddress;
    private String musicTerritory;
    private PaymentInfo paymentInfo;
    private String planType;
    private String rolloverPlanType;
    private String scheduleId;
    private String sessionId;
    private String subscriptionId;

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
        if (!(cirrusBaseRequestV2 instanceof PrepareSubscriptionRequest)) {
            return 1;
        }
        PrepareSubscriptionRequest prepareSubscriptionRequest = (PrepareSubscriptionRequest) cirrusBaseRequestV2;
        String sessionId = getSessionId();
        String sessionId2 = prepareSubscriptionRequest.getSessionId();
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
        String planType = getPlanType();
        String planType2 = prepareSubscriptionRequest.getPlanType();
        if (planType != planType2) {
            if (planType == null) {
                return -1;
            }
            if (planType2 == null) {
                return 1;
            }
            int iCompareTo2 = planType.compareTo(planType2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        PaymentInfo paymentInfo = getPaymentInfo();
        PaymentInfo paymentInfo2 = prepareSubscriptionRequest.getPaymentInfo();
        if (paymentInfo != paymentInfo2) {
            if (paymentInfo == null) {
                return -1;
            }
            if (paymentInfo2 == null) {
                return 1;
            }
            int iCompareTo3 = paymentInfo.compareTo(paymentInfo2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String ipAddress = getIpAddress();
        String ipAddress2 = prepareSubscriptionRequest.getIpAddress();
        if (ipAddress != ipAddress2) {
            if (ipAddress == null) {
                return -1;
            }
            if (ipAddress2 == null) {
                return 1;
            }
            int iCompareTo4 = ipAddress.compareTo(ipAddress2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String subscriptionId = getSubscriptionId();
        String subscriptionId2 = prepareSubscriptionRequest.getSubscriptionId();
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
        List<String> addonScheduleIds = getAddonScheduleIds();
        List<String> addonScheduleIds2 = prepareSubscriptionRequest.getAddonScheduleIds();
        if (addonScheduleIds != addonScheduleIds2) {
            if (addonScheduleIds == null) {
                return -1;
            }
            if (addonScheduleIds2 == null) {
                return 1;
            }
            if (addonScheduleIds instanceof Comparable) {
                int iCompareTo6 = ((Comparable) addonScheduleIds).compareTo(addonScheduleIds2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!addonScheduleIds.equals(addonScheduleIds2)) {
                int iHashCode = addonScheduleIds.hashCode();
                int iHashCode2 = addonScheduleIds2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = prepareSubscriptionRequest.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo7 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String rolloverPlanType = getRolloverPlanType();
        String rolloverPlanType2 = prepareSubscriptionRequest.getRolloverPlanType();
        if (rolloverPlanType != rolloverPlanType2) {
            if (rolloverPlanType == null) {
                return -1;
            }
            if (rolloverPlanType2 == null) {
                return 1;
            }
            int iCompareTo8 = rolloverPlanType.compareTo(rolloverPlanType2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String scheduleId = getScheduleId();
        String scheduleId2 = prepareSubscriptionRequest.getScheduleId();
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
        Boolean boolIsForceAuthenticationRequired = isForceAuthenticationRequired();
        Boolean boolIsForceAuthenticationRequired2 = prepareSubscriptionRequest.isForceAuthenticationRequired();
        if (boolIsForceAuthenticationRequired != boolIsForceAuthenticationRequired2) {
            if (boolIsForceAuthenticationRequired == null) {
                return -1;
            }
            if (boolIsForceAuthenticationRequired2 == null) {
                return 1;
            }
            int iCompareTo10 = boolIsForceAuthenticationRequired.compareTo(boolIsForceAuthenticationRequired2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof PrepareSubscriptionRequest)) {
            return false;
        }
        PrepareSubscriptionRequest prepareSubscriptionRequest = (PrepareSubscriptionRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getSessionId(), prepareSubscriptionRequest.getSessionId()) && internalEqualityCheck(getPlanType(), prepareSubscriptionRequest.getPlanType()) && internalEqualityCheck(getPaymentInfo(), prepareSubscriptionRequest.getPaymentInfo()) && internalEqualityCheck(getIpAddress(), prepareSubscriptionRequest.getIpAddress()) && internalEqualityCheck(getSubscriptionId(), prepareSubscriptionRequest.getSubscriptionId()) && internalEqualityCheck(getAddonScheduleIds(), prepareSubscriptionRequest.getAddonScheduleIds()) && internalEqualityCheck(getMusicTerritory(), prepareSubscriptionRequest.getMusicTerritory()) && internalEqualityCheck(getRolloverPlanType(), prepareSubscriptionRequest.getRolloverPlanType()) && internalEqualityCheck(getScheduleId(), prepareSubscriptionRequest.getScheduleId()) && internalEqualityCheck(isForceAuthenticationRequired(), prepareSubscriptionRequest.isForceAuthenticationRequired());
    }

    public List<String> getAddonScheduleIds() {
        return this.addonScheduleIds;
    }

    public String getIpAddress() {
        return this.ipAddress;
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

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSessionId(), getPlanType(), getPaymentInfo(), getIpAddress(), getSubscriptionId(), getAddonScheduleIds(), getMusicTerritory(), getRolloverPlanType(), getScheduleId(), isForceAuthenticationRequired());
    }

    public Boolean isForceAuthenticationRequired() {
        return this.forceAuthenticationRequired;
    }
}
