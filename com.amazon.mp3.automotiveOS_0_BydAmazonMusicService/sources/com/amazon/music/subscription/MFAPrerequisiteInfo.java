package com.amazon.music.subscription;

import com.amazon.stratus.Period;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class MFAPrerequisiteInfo implements Comparable<MFAPrerequisiteInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.MFAPrerequisiteInfo");
    private AdditionalPlanPriceInfo additionalPlanPriceInfo;
    private PriceWithCurrency authenticationPrice;
    private Period authenticationRecurringPeriod;
    private String authenticationSourceId;
    private String mfaType;
    private Boolean redirectRequired;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MFAPrerequisiteInfo mFAPrerequisiteInfo) {
        if (mFAPrerequisiteInfo == null) {
            return -1;
        }
        if (mFAPrerequisiteInfo == this) {
            return 0;
        }
        String mfaType = getMfaType();
        String mfaType2 = mFAPrerequisiteInfo.getMfaType();
        if (mfaType != mfaType2) {
            if (mfaType == null) {
                return -1;
            }
            if (mfaType2 == null) {
                return 1;
            }
            int iCompareTo = mfaType.compareTo(mfaType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Period authenticationRecurringPeriod = getAuthenticationRecurringPeriod();
        Period authenticationRecurringPeriod2 = mFAPrerequisiteInfo.getAuthenticationRecurringPeriod();
        if (authenticationRecurringPeriod != authenticationRecurringPeriod2) {
            if (authenticationRecurringPeriod == null) {
                return -1;
            }
            if (authenticationRecurringPeriod2 == null) {
                return 1;
            }
            int iCompareTo2 = authenticationRecurringPeriod.compareTo(authenticationRecurringPeriod2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String authenticationSourceId = getAuthenticationSourceId();
        String authenticationSourceId2 = mFAPrerequisiteInfo.getAuthenticationSourceId();
        if (authenticationSourceId != authenticationSourceId2) {
            if (authenticationSourceId == null) {
                return -1;
            }
            if (authenticationSourceId2 == null) {
                return 1;
            }
            int iCompareTo3 = authenticationSourceId.compareTo(authenticationSourceId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsRedirectRequired = isRedirectRequired();
        Boolean boolIsRedirectRequired2 = mFAPrerequisiteInfo.isRedirectRequired();
        if (boolIsRedirectRequired != boolIsRedirectRequired2) {
            if (boolIsRedirectRequired == null) {
                return -1;
            }
            if (boolIsRedirectRequired2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsRedirectRequired.compareTo(boolIsRedirectRequired2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        PriceWithCurrency authenticationPrice = getAuthenticationPrice();
        PriceWithCurrency authenticationPrice2 = mFAPrerequisiteInfo.getAuthenticationPrice();
        if (authenticationPrice != authenticationPrice2) {
            if (authenticationPrice == null) {
                return -1;
            }
            if (authenticationPrice2 == null) {
                return 1;
            }
            int iCompareTo5 = authenticationPrice.compareTo(authenticationPrice2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        AdditionalPlanPriceInfo additionalPlanPriceInfo = getAdditionalPlanPriceInfo();
        AdditionalPlanPriceInfo additionalPlanPriceInfo2 = mFAPrerequisiteInfo.getAdditionalPlanPriceInfo();
        if (additionalPlanPriceInfo != additionalPlanPriceInfo2) {
            if (additionalPlanPriceInfo == null) {
                return -1;
            }
            if (additionalPlanPriceInfo2 == null) {
                return 1;
            }
            int iCompareTo6 = additionalPlanPriceInfo.compareTo(additionalPlanPriceInfo2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MFAPrerequisiteInfo)) {
            return false;
        }
        MFAPrerequisiteInfo mFAPrerequisiteInfo = (MFAPrerequisiteInfo) obj;
        return internalEqualityCheck(getMfaType(), mFAPrerequisiteInfo.getMfaType()) && internalEqualityCheck(getAuthenticationRecurringPeriod(), mFAPrerequisiteInfo.getAuthenticationRecurringPeriod()) && internalEqualityCheck(getAuthenticationSourceId(), mFAPrerequisiteInfo.getAuthenticationSourceId()) && internalEqualityCheck(isRedirectRequired(), mFAPrerequisiteInfo.isRedirectRequired()) && internalEqualityCheck(getAuthenticationPrice(), mFAPrerequisiteInfo.getAuthenticationPrice()) && internalEqualityCheck(getAdditionalPlanPriceInfo(), mFAPrerequisiteInfo.getAdditionalPlanPriceInfo());
    }

    public AdditionalPlanPriceInfo getAdditionalPlanPriceInfo() {
        return this.additionalPlanPriceInfo;
    }

    public PriceWithCurrency getAuthenticationPrice() {
        return this.authenticationPrice;
    }

    public Period getAuthenticationRecurringPeriod() {
        return this.authenticationRecurringPeriod;
    }

    public String getAuthenticationSourceId() {
        return this.authenticationSourceId;
    }

    public String getMfaType() {
        return this.mfaType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMfaType(), getAuthenticationRecurringPeriod(), getAuthenticationSourceId(), isRedirectRequired(), getAuthenticationPrice(), getAdditionalPlanPriceInfo());
    }

    public Boolean isRedirectRequired() {
        return this.redirectRequired;
    }

    public void setAdditionalPlanPriceInfo(AdditionalPlanPriceInfo additionalPlanPriceInfo) {
        this.additionalPlanPriceInfo = additionalPlanPriceInfo;
    }

    public void setAuthenticationPrice(PriceWithCurrency priceWithCurrency) {
        this.authenticationPrice = priceWithCurrency;
    }

    public void setAuthenticationRecurringPeriod(Period period) {
        this.authenticationRecurringPeriod = period;
    }

    public void setAuthenticationSourceId(String str) {
        this.authenticationSourceId = str;
    }

    public void setMfaType(String str) {
        this.mfaType = str;
    }

    public void setRedirectRequired(Boolean bool) {
        this.redirectRequired = bool;
    }
}
