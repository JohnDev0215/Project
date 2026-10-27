package com.amazon.music.subscription;

import java.math.BigDecimal;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class PriceWithCurrency implements Comparable<PriceWithCurrency> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.PriceWithCurrency");
    private BigDecimal amount;
    private String currencyCode;

    public static class Builder {
        protected BigDecimal amount;
        protected String currencyCode;

        public PriceWithCurrency build() {
            PriceWithCurrency priceWithCurrency = new PriceWithCurrency();
            populate(priceWithCurrency);
            return priceWithCurrency;
        }

        protected void populate(PriceWithCurrency priceWithCurrency) {
            priceWithCurrency.setAmount(this.amount);
            priceWithCurrency.setCurrencyCode(this.currencyCode);
        }

        public Builder withAmount(BigDecimal bigDecimal) {
            this.amount = bigDecimal;
            return this;
        }

        public Builder withCurrencyCode(String str) {
            this.currencyCode = str;
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
    public int compareTo(@Deprecated PriceWithCurrency priceWithCurrency) {
        if (priceWithCurrency == null) {
            return -1;
        }
        if (priceWithCurrency == this) {
            return 0;
        }
        BigDecimal amount = getAmount();
        BigDecimal amount2 = priceWithCurrency.getAmount();
        if (amount != amount2) {
            if (amount == null) {
                return -1;
            }
            if (amount2 == null) {
                return 1;
            }
            int iCompareTo = amount.compareTo(amount2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String currencyCode = getCurrencyCode();
        String currencyCode2 = priceWithCurrency.getCurrencyCode();
        if (currencyCode != currencyCode2) {
            if (currencyCode == null) {
                return -1;
            }
            if (currencyCode2 == null) {
                return 1;
            }
            int iCompareTo2 = currencyCode.compareTo(currencyCode2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PriceWithCurrency)) {
            return false;
        }
        PriceWithCurrency priceWithCurrency = (PriceWithCurrency) obj;
        return internalEqualityCheck(getAmount(), priceWithCurrency.getAmount()) && internalEqualityCheck(getCurrencyCode(), priceWithCurrency.getCurrencyCode());
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAmount(), getCurrencyCode());
    }

    public void setAmount(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }
}
