package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class PaymentInfo implements Comparable<PaymentInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.PaymentInfo");
    private String paymentPortalClientId;
    private PaymentsPreferenceKey paymentsPreferenceKey;

    public static class Builder {
        protected String paymentPortalClientId;
        protected PaymentsPreferenceKey paymentsPreferenceKey;

        public PaymentInfo build() {
            PaymentInfo paymentInfo = new PaymentInfo();
            populate(paymentInfo);
            return paymentInfo;
        }

        protected void populate(PaymentInfo paymentInfo) {
            paymentInfo.setPaymentPortalClientId(this.paymentPortalClientId);
            paymentInfo.setPaymentsPreferenceKey(this.paymentsPreferenceKey);
        }

        public Builder withPaymentPortalClientId(String str) {
            this.paymentPortalClientId = str;
            return this;
        }

        public Builder withPaymentsPreferenceKey(PaymentsPreferenceKey paymentsPreferenceKey) {
            this.paymentsPreferenceKey = paymentsPreferenceKey;
            return this;
        }
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PaymentInfo paymentInfo) {
        if (paymentInfo == null) {
            return -1;
        }
        if (paymentInfo == this) {
            return 0;
        }
        String paymentPortalClientId = getPaymentPortalClientId();
        String paymentPortalClientId2 = paymentInfo.getPaymentPortalClientId();
        if (paymentPortalClientId != paymentPortalClientId2) {
            if (paymentPortalClientId == null) {
                return -1;
            }
            if (paymentPortalClientId2 == null) {
                return 1;
            }
            int iCompareTo = paymentPortalClientId.compareTo(paymentPortalClientId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        PaymentsPreferenceKey paymentsPreferenceKey = getPaymentsPreferenceKey();
        PaymentsPreferenceKey paymentsPreferenceKey2 = paymentInfo.getPaymentsPreferenceKey();
        if (paymentsPreferenceKey != paymentsPreferenceKey2) {
            if (paymentsPreferenceKey == null) {
                return -1;
            }
            if (paymentsPreferenceKey2 == null) {
                return 1;
            }
            int iCompareTo2 = paymentsPreferenceKey.compareTo(paymentsPreferenceKey2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PaymentInfo)) {
            return false;
        }
        PaymentInfo paymentInfo = (PaymentInfo) obj;
        return internalEqualityCheck(getPaymentPortalClientId(), paymentInfo.getPaymentPortalClientId()) && internalEqualityCheck(getPaymentsPreferenceKey(), paymentInfo.getPaymentsPreferenceKey());
    }

    public String getPaymentPortalClientId() {
        return this.paymentPortalClientId;
    }

    public PaymentsPreferenceKey getPaymentsPreferenceKey() {
        return this.paymentsPreferenceKey;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPaymentPortalClientId(), getPaymentsPreferenceKey());
    }

    public void setPaymentPortalClientId(String str) {
        this.paymentPortalClientId = str;
    }

    public void setPaymentsPreferenceKey(PaymentsPreferenceKey paymentsPreferenceKey) {
        this.paymentsPreferenceKey = paymentsPreferenceKey;
    }
}
