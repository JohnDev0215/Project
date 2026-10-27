package com.amazon.musicsubscriptionofferservice;

import java.math.BigDecimal;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class MonetaryAmount implements Comparable<MonetaryAmount> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.MonetaryAmount");
    private MonetaryAmountInCop copPrice;
    private String currencyCode;
    private BigDecimal value;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MonetaryAmount monetaryAmount) {
        if (monetaryAmount == null) {
            return -1;
        }
        if (monetaryAmount == this) {
            return 0;
        }
        MonetaryAmountInCop copPrice = getCopPrice();
        MonetaryAmountInCop copPrice2 = monetaryAmount.getCopPrice();
        if (copPrice != copPrice2) {
            if (copPrice == null) {
                return -1;
            }
            if (copPrice2 == null) {
                return 1;
            }
            int iCompareTo = copPrice.compareTo(copPrice2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        BigDecimal value = getValue();
        BigDecimal value2 = monetaryAmount.getValue();
        if (value != value2) {
            if (value == null) {
                return -1;
            }
            if (value2 == null) {
                return 1;
            }
            int iCompareTo2 = value.compareTo(value2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String currencyCode = getCurrencyCode();
        String currencyCode2 = monetaryAmount.getCurrencyCode();
        if (currencyCode != currencyCode2) {
            if (currencyCode == null) {
                return -1;
            }
            if (currencyCode2 == null) {
                return 1;
            }
            int iCompareTo3 = currencyCode.compareTo(currencyCode2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MonetaryAmount)) {
            return false;
        }
        MonetaryAmount monetaryAmount = (MonetaryAmount) obj;
        return internalEqualityCheck(getCopPrice(), monetaryAmount.getCopPrice()) && internalEqualityCheck(getValue(), monetaryAmount.getValue()) && internalEqualityCheck(getCurrencyCode(), monetaryAmount.getCurrencyCode());
    }

    public MonetaryAmountInCop getCopPrice() {
        return this.copPrice;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public BigDecimal getValue() {
        return this.value;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCopPrice(), getValue(), getCurrencyCode());
    }

    public void setCopPrice(MonetaryAmountInCop monetaryAmountInCop) {
        this.copPrice = monetaryAmountInCop;
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public void setValue(BigDecimal bigDecimal) {
        this.value = bigDecimal;
    }
}
