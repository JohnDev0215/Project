package com.amazon.music.subscription;

import com.amazon.stratus.PaymentInfo;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class PrepareSubscriptionResponse implements Comparable<PrepareSubscriptionResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.PrepareSubscriptionResponse");
    private GeocheckInfo geocheckInfo;
    private MFAPrerequisiteInfo mfaPrerequisiteInfo;
    private PaymentInfo paymentInfo;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PrepareSubscriptionResponse prepareSubscriptionResponse) {
        if (prepareSubscriptionResponse == null) {
            return -1;
        }
        if (prepareSubscriptionResponse == this) {
            return 0;
        }
        PaymentInfo paymentInfo = getPaymentInfo();
        PaymentInfo paymentInfo2 = prepareSubscriptionResponse.getPaymentInfo();
        if (paymentInfo != paymentInfo2) {
            if (paymentInfo == null) {
                return -1;
            }
            if (paymentInfo2 == null) {
                return 1;
            }
            int iCompareTo = paymentInfo.compareTo(paymentInfo2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        MFAPrerequisiteInfo mfaPrerequisiteInfo = getMfaPrerequisiteInfo();
        MFAPrerequisiteInfo mfaPrerequisiteInfo2 = prepareSubscriptionResponse.getMfaPrerequisiteInfo();
        if (mfaPrerequisiteInfo != mfaPrerequisiteInfo2) {
            if (mfaPrerequisiteInfo == null) {
                return -1;
            }
            if (mfaPrerequisiteInfo2 == null) {
                return 1;
            }
            int iCompareTo2 = mfaPrerequisiteInfo.compareTo(mfaPrerequisiteInfo2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        GeocheckInfo geocheckInfo = getGeocheckInfo();
        GeocheckInfo geocheckInfo2 = prepareSubscriptionResponse.getGeocheckInfo();
        if (geocheckInfo != geocheckInfo2) {
            if (geocheckInfo == null) {
                return -1;
            }
            if (geocheckInfo2 == null) {
                return 1;
            }
            int iCompareTo3 = geocheckInfo.compareTo(geocheckInfo2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PrepareSubscriptionResponse)) {
            return false;
        }
        PrepareSubscriptionResponse prepareSubscriptionResponse = (PrepareSubscriptionResponse) obj;
        return internalEqualityCheck(getPaymentInfo(), prepareSubscriptionResponse.getPaymentInfo()) && internalEqualityCheck(getMfaPrerequisiteInfo(), prepareSubscriptionResponse.getMfaPrerequisiteInfo()) && internalEqualityCheck(getGeocheckInfo(), prepareSubscriptionResponse.getGeocheckInfo());
    }

    public GeocheckInfo getGeocheckInfo() {
        return this.geocheckInfo;
    }

    public MFAPrerequisiteInfo getMfaPrerequisiteInfo() {
        return this.mfaPrerequisiteInfo;
    }

    public PaymentInfo getPaymentInfo() {
        return this.paymentInfo;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPaymentInfo(), getMfaPrerequisiteInfo(), getGeocheckInfo());
    }

    public void setGeocheckInfo(GeocheckInfo geocheckInfo) {
        this.geocheckInfo = geocheckInfo;
    }

    public void setMfaPrerequisiteInfo(MFAPrerequisiteInfo mFAPrerequisiteInfo) {
        this.mfaPrerequisiteInfo = mFAPrerequisiteInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }
}
