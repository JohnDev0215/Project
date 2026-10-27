package com.amazon.music.subscription;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.stratus.PaymentInfo;
import com.amazon.stratus.Period;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CreateSubscriptionRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.CreateSubscriptionRequest");
    private List<String> addonScheduleIds;
    private Boolean autoRenew;
    private Boolean enableOptimisticSignUp;
    private String ipAddress;
    private String ipv6Address;
    private String marketplaceId;
    private MFAPrerequisiteInfo mfaPrerequisiteInfo;
    private String musicTerritory;
    private PaymentInfo paymentInfo;
    private Period periodOverride;
    private String planType;
    private RequestConfig requestConfig;
    private String rolloverPlanType;
    private String scheduleId;
    private String sessionId;
    private Boolean vetRequired;

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
        if (!(cirrusBaseRequestV2 instanceof CreateSubscriptionRequest)) {
            return 1;
        }
        CreateSubscriptionRequest createSubscriptionRequest = (CreateSubscriptionRequest) cirrusBaseRequestV2;
        Boolean boolIsVetRequired = isVetRequired();
        Boolean boolIsVetRequired2 = createSubscriptionRequest.isVetRequired();
        if (boolIsVetRequired != boolIsVetRequired2) {
            if (boolIsVetRequired == null) {
                return -1;
            }
            if (boolIsVetRequired2 == null) {
                return 1;
            }
            int iCompareTo = boolIsVetRequired.compareTo(boolIsVetRequired2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String rolloverPlanType = getRolloverPlanType();
        String rolloverPlanType2 = createSubscriptionRequest.getRolloverPlanType();
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
        List<String> addonScheduleIds = getAddonScheduleIds();
        List<String> addonScheduleIds2 = createSubscriptionRequest.getAddonScheduleIds();
        if (addonScheduleIds != addonScheduleIds2) {
            if (addonScheduleIds == null) {
                return -1;
            }
            if (addonScheduleIds2 == null) {
                return 1;
            }
            if (addonScheduleIds instanceof Comparable) {
                int iCompareTo3 = ((Comparable) addonScheduleIds).compareTo(addonScheduleIds2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
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
        String planType = getPlanType();
        String planType2 = createSubscriptionRequest.getPlanType();
        if (planType != planType2) {
            if (planType == null) {
                return -1;
            }
            if (planType2 == null) {
                return 1;
            }
            int iCompareTo4 = planType.compareTo(planType2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String ipv6Address = getIpv6Address();
        String ipv6Address2 = createSubscriptionRequest.getIpv6Address();
        if (ipv6Address != ipv6Address2) {
            if (ipv6Address == null) {
                return -1;
            }
            if (ipv6Address2 == null) {
                return 1;
            }
            int iCompareTo5 = ipv6Address.compareTo(ipv6Address2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String sessionId = getSessionId();
        String sessionId2 = createSubscriptionRequest.getSessionId();
        if (sessionId != sessionId2) {
            if (sessionId == null) {
                return -1;
            }
            if (sessionId2 == null) {
                return 1;
            }
            int iCompareTo6 = sessionId.compareTo(sessionId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Period periodOverride = getPeriodOverride();
        Period periodOverride2 = createSubscriptionRequest.getPeriodOverride();
        if (periodOverride != periodOverride2) {
            if (periodOverride == null) {
                return -1;
            }
            if (periodOverride2 == null) {
                return 1;
            }
            int iCompareTo7 = periodOverride.compareTo(periodOverride2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = createSubscriptionRequest.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo8 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        MFAPrerequisiteInfo mfaPrerequisiteInfo = getMfaPrerequisiteInfo();
        MFAPrerequisiteInfo mfaPrerequisiteInfo2 = createSubscriptionRequest.getMfaPrerequisiteInfo();
        if (mfaPrerequisiteInfo != mfaPrerequisiteInfo2) {
            if (mfaPrerequisiteInfo == null) {
                return -1;
            }
            if (mfaPrerequisiteInfo2 == null) {
                return 1;
            }
            int iCompareTo9 = mfaPrerequisiteInfo.compareTo(mfaPrerequisiteInfo2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Boolean boolIsAutoRenew = isAutoRenew();
        Boolean boolIsAutoRenew2 = createSubscriptionRequest.isAutoRenew();
        if (boolIsAutoRenew != boolIsAutoRenew2) {
            if (boolIsAutoRenew == null) {
                return -1;
            }
            if (boolIsAutoRenew2 == null) {
                return 1;
            }
            int iCompareTo10 = boolIsAutoRenew.compareTo(boolIsAutoRenew2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        PaymentInfo paymentInfo = getPaymentInfo();
        PaymentInfo paymentInfo2 = createSubscriptionRequest.getPaymentInfo();
        if (paymentInfo != paymentInfo2) {
            if (paymentInfo == null) {
                return -1;
            }
            if (paymentInfo2 == null) {
                return 1;
            }
            int iCompareTo11 = paymentInfo.compareTo(paymentInfo2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String scheduleId = getScheduleId();
        String scheduleId2 = createSubscriptionRequest.getScheduleId();
        if (scheduleId != scheduleId2) {
            if (scheduleId == null) {
                return -1;
            }
            if (scheduleId2 == null) {
                return 1;
            }
            int iCompareTo12 = scheduleId.compareTo(scheduleId2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Boolean boolIsEnableOptimisticSignUp = isEnableOptimisticSignUp();
        Boolean boolIsEnableOptimisticSignUp2 = createSubscriptionRequest.isEnableOptimisticSignUp();
        if (boolIsEnableOptimisticSignUp != boolIsEnableOptimisticSignUp2) {
            if (boolIsEnableOptimisticSignUp == null) {
                return -1;
            }
            if (boolIsEnableOptimisticSignUp2 == null) {
                return 1;
            }
            int iCompareTo13 = boolIsEnableOptimisticSignUp.compareTo(boolIsEnableOptimisticSignUp2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        RequestConfig requestConfig = getRequestConfig();
        RequestConfig requestConfig2 = createSubscriptionRequest.getRequestConfig();
        if (requestConfig != requestConfig2) {
            if (requestConfig == null) {
                return -1;
            }
            if (requestConfig2 == null) {
                return 1;
            }
            int iCompareTo14 = requestConfig.compareTo(requestConfig2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String ipAddress = getIpAddress();
        String ipAddress2 = createSubscriptionRequest.getIpAddress();
        if (ipAddress != ipAddress2) {
            if (ipAddress == null) {
                return -1;
            }
            if (ipAddress2 == null) {
                return 1;
            }
            int iCompareTo15 = ipAddress.compareTo(ipAddress2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = createSubscriptionRequest.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo16 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof CreateSubscriptionRequest)) {
            return false;
        }
        CreateSubscriptionRequest createSubscriptionRequest = (CreateSubscriptionRequest) obj;
        return super.equals(obj) && internalEqualityCheck(isVetRequired(), createSubscriptionRequest.isVetRequired()) && internalEqualityCheck(getRolloverPlanType(), createSubscriptionRequest.getRolloverPlanType()) && internalEqualityCheck(getAddonScheduleIds(), createSubscriptionRequest.getAddonScheduleIds()) && internalEqualityCheck(getPlanType(), createSubscriptionRequest.getPlanType()) && internalEqualityCheck(getIpv6Address(), createSubscriptionRequest.getIpv6Address()) && internalEqualityCheck(getSessionId(), createSubscriptionRequest.getSessionId()) && internalEqualityCheck(getPeriodOverride(), createSubscriptionRequest.getPeriodOverride()) && internalEqualityCheck(getMarketplaceId(), createSubscriptionRequest.getMarketplaceId()) && internalEqualityCheck(getMfaPrerequisiteInfo(), createSubscriptionRequest.getMfaPrerequisiteInfo()) && internalEqualityCheck(isAutoRenew(), createSubscriptionRequest.isAutoRenew()) && internalEqualityCheck(getPaymentInfo(), createSubscriptionRequest.getPaymentInfo()) && internalEqualityCheck(getScheduleId(), createSubscriptionRequest.getScheduleId()) && internalEqualityCheck(isEnableOptimisticSignUp(), createSubscriptionRequest.isEnableOptimisticSignUp()) && internalEqualityCheck(getRequestConfig(), createSubscriptionRequest.getRequestConfig()) && internalEqualityCheck(getIpAddress(), createSubscriptionRequest.getIpAddress()) && internalEqualityCheck(getMusicTerritory(), createSubscriptionRequest.getMusicTerritory());
    }

    public List<String> getAddonScheduleIds() {
        return this.addonScheduleIds;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getIpv6Address() {
        return this.ipv6Address;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public MFAPrerequisiteInfo getMfaPrerequisiteInfo() {
        return this.mfaPrerequisiteInfo;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    public Period getPeriodOverride() {
        return this.periodOverride;
    }

    public String getPlanType() {
        return this.planType;
    }

    public RequestConfig getRequestConfig() {
        return this.requestConfig;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isVetRequired(), getRolloverPlanType(), getAddonScheduleIds(), getPlanType(), getIpv6Address(), getSessionId(), getPeriodOverride(), getMarketplaceId(), getMfaPrerequisiteInfo(), isAutoRenew(), getPaymentInfo(), getScheduleId(), isEnableOptimisticSignUp(), getRequestConfig(), getIpAddress(), getMusicTerritory());
    }

    public Boolean isAutoRenew() {
        return this.autoRenew;
    }

    public Boolean isEnableOptimisticSignUp() {
        return this.enableOptimisticSignUp;
    }

    public Boolean isVetRequired() {
        return this.vetRequired;
    }
}
