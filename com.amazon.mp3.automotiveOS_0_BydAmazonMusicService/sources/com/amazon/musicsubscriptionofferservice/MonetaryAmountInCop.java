package com.amazon.musicsubscriptionofferservice;

import java.math.BigDecimal;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class MonetaryAmountInCop implements Comparable<MonetaryAmountInCop> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.MonetaryAmountInCop");
    private String currencyCode;
    private String formattedAmount;
    private BigDecimal value;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MonetaryAmountInCop monetaryAmountInCop) {
        if (monetaryAmountInCop == null) {
            return -1;
        }
        if (monetaryAmountInCop == this) {
            return 0;
        }
        BigDecimal value = getValue();
        BigDecimal value2 = monetaryAmountInCop.getValue();
        if (value != value2) {
            if (value == null) {
                return -1;
            }
            if (value2 == null) {
                return 1;
            }
            int iCompareTo = value.compareTo(value2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String currencyCode = getCurrencyCode();
        String currencyCode2 = monetaryAmountInCop.getCurrencyCode();
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
        String formattedAmount = getFormattedAmount();
        String formattedAmount2 = monetaryAmountInCop.getFormattedAmount();
        if (formattedAmount != formattedAmount2) {
            if (formattedAmount == null) {
                return -1;
            }
            if (formattedAmount2 == null) {
                return 1;
            }
            int iCompareTo3 = formattedAmount.compareTo(formattedAmount2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MonetaryAmountInCop)) {
            return false;
        }
        MonetaryAmountInCop monetaryAmountInCop = (MonetaryAmountInCop) obj;
        return internalEqualityCheck(getValue(), monetaryAmountInCop.getValue()) && internalEqualityCheck(getCurrencyCode(), monetaryAmountInCop.getCurrencyCode()) && internalEqualityCheck(getFormattedAmount(), monetaryAmountInCop.getFormattedAmount());
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public String getFormattedAmount() {
        return this.formattedAmount;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getValue(), getCurrencyCode(), getFormattedAmount());
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public void setFormattedAmount(String str) {
        this.formattedAmount = str;
    }

    public void setValue(BigDecimal bigDecimal) {
        this.value = bigDecimal;
    }
}
