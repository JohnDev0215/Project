package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class PaymentsPreferenceKey implements Comparable<PaymentsPreferenceKey> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.PaymentsPreferenceKey");
    private String customerId;
    private String externalReferenceId;
    private String marketplaceId;
    private String paymentMethodId;
    private String paymentPreferenceId;
    private String preferenceType;

    public static class Builder {
        protected String customerId;
        protected String externalReferenceId;
        protected String marketplaceId;
        protected String paymentMethodId;
        protected String paymentPreferenceId;
        protected String preferenceType;

        public PaymentsPreferenceKey build() {
            PaymentsPreferenceKey paymentsPreferenceKey = new PaymentsPreferenceKey();
            populate(paymentsPreferenceKey);
            return paymentsPreferenceKey;
        }

        protected void populate(PaymentsPreferenceKey paymentsPreferenceKey) {
            paymentsPreferenceKey.setMarketplaceId(this.marketplaceId);
            paymentsPreferenceKey.setExternalReferenceId(this.externalReferenceId);
            paymentsPreferenceKey.setPaymentMethodId(this.paymentMethodId);
            paymentsPreferenceKey.setCustomerId(this.customerId);
            paymentsPreferenceKey.setPaymentPreferenceId(this.paymentPreferenceId);
            paymentsPreferenceKey.setPreferenceType(this.preferenceType);
        }

        public Builder withCustomerId(String str) {
            this.customerId = str;
            return this;
        }

        public Builder withExternalReferenceId(String str) {
            this.externalReferenceId = str;
            return this;
        }

        public Builder withMarketplaceId(String str) {
            this.marketplaceId = str;
            return this;
        }

        public Builder withPaymentMethodId(String str) {
            this.paymentMethodId = str;
            return this;
        }

        public Builder withPaymentPreferenceId(String str) {
            this.paymentPreferenceId = str;
            return this;
        }

        public Builder withPreferenceType(String str) {
            this.preferenceType = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PaymentsPreferenceKey paymentsPreferenceKey) {
        if (paymentsPreferenceKey == null) {
            return -1;
        }
        if (paymentsPreferenceKey == this) {
            return 0;
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = paymentsPreferenceKey.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String externalReferenceId = getExternalReferenceId();
        String externalReferenceId2 = paymentsPreferenceKey.getExternalReferenceId();
        if (externalReferenceId != externalReferenceId2) {
            if (externalReferenceId == null) {
                return -1;
            }
            if (externalReferenceId2 == null) {
                return 1;
            }
            int iCompareTo2 = externalReferenceId.compareTo(externalReferenceId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String paymentMethodId = getPaymentMethodId();
        String paymentMethodId2 = paymentsPreferenceKey.getPaymentMethodId();
        if (paymentMethodId != paymentMethodId2) {
            if (paymentMethodId == null) {
                return -1;
            }
            if (paymentMethodId2 == null) {
                return 1;
            }
            int iCompareTo3 = paymentMethodId.compareTo(paymentMethodId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String customerId = getCustomerId();
        String customerId2 = paymentsPreferenceKey.getCustomerId();
        if (customerId != customerId2) {
            if (customerId == null) {
                return -1;
            }
            if (customerId2 == null) {
                return 1;
            }
            int iCompareTo4 = customerId.compareTo(customerId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String paymentPreferenceId = getPaymentPreferenceId();
        String paymentPreferenceId2 = paymentsPreferenceKey.getPaymentPreferenceId();
        if (paymentPreferenceId != paymentPreferenceId2) {
            if (paymentPreferenceId == null) {
                return -1;
            }
            if (paymentPreferenceId2 == null) {
                return 1;
            }
            int iCompareTo5 = paymentPreferenceId.compareTo(paymentPreferenceId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String preferenceType = getPreferenceType();
        String preferenceType2 = paymentsPreferenceKey.getPreferenceType();
        if (preferenceType != preferenceType2) {
            if (preferenceType == null) {
                return -1;
            }
            if (preferenceType2 == null) {
                return 1;
            }
            int iCompareTo6 = preferenceType.compareTo(preferenceType2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PaymentsPreferenceKey)) {
            return false;
        }
        PaymentsPreferenceKey paymentsPreferenceKey = (PaymentsPreferenceKey) obj;
        return internalEqualityCheck(getMarketplaceId(), paymentsPreferenceKey.getMarketplaceId()) && internalEqualityCheck(getExternalReferenceId(), paymentsPreferenceKey.getExternalReferenceId()) && internalEqualityCheck(getPaymentMethodId(), paymentsPreferenceKey.getPaymentMethodId()) && internalEqualityCheck(getCustomerId(), paymentsPreferenceKey.getCustomerId()) && internalEqualityCheck(getPaymentPreferenceId(), paymentsPreferenceKey.getPaymentPreferenceId()) && internalEqualityCheck(getPreferenceType(), paymentsPreferenceKey.getPreferenceType());
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getExternalReferenceId() {
        return this.externalReferenceId;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getPaymentMethodId() {
        return this.paymentMethodId;
    }

    public String getPaymentPreferenceId() {
        return this.paymentPreferenceId;
    }

    public String getPreferenceType() {
        return this.preferenceType;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMarketplaceId(), getExternalReferenceId(), getPaymentMethodId(), getCustomerId(), getPaymentPreferenceId(), getPreferenceType());
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setExternalReferenceId(String str) {
        this.externalReferenceId = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setPaymentMethodId(String str) {
        this.paymentMethodId = str;
    }

    public void setPaymentPreferenceId(String str) {
        this.paymentPreferenceId = str;
    }

    public void setPreferenceType(String str) {
        this.preferenceType = str;
    }
}
