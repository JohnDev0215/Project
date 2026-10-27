package com.amazon.music.subscription;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class ProductInfo implements Comparable<ProductInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.ProductInfo");
    private String externalProductIdentifier;
    private String productName;
    private String tierName;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ProductInfo productInfo) {
        if (productInfo == null) {
            return -1;
        }
        if (productInfo == this) {
            return 0;
        }
        String tierName = getTierName();
        String tierName2 = productInfo.getTierName();
        if (tierName != tierName2) {
            if (tierName == null) {
                return -1;
            }
            if (tierName2 == null) {
                return 1;
            }
            int iCompareTo = tierName.compareTo(tierName2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String externalProductIdentifier = getExternalProductIdentifier();
        String externalProductIdentifier2 = productInfo.getExternalProductIdentifier();
        if (externalProductIdentifier != externalProductIdentifier2) {
            if (externalProductIdentifier == null) {
                return -1;
            }
            if (externalProductIdentifier2 == null) {
                return 1;
            }
            int iCompareTo2 = externalProductIdentifier.compareTo(externalProductIdentifier2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String productName = getProductName();
        String productName2 = productInfo.getProductName();
        if (productName != productName2) {
            if (productName == null) {
                return -1;
            }
            if (productName2 == null) {
                return 1;
            }
            int iCompareTo3 = productName.compareTo(productName2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ProductInfo)) {
            return false;
        }
        ProductInfo productInfo = (ProductInfo) obj;
        return internalEqualityCheck(getTierName(), productInfo.getTierName()) && internalEqualityCheck(getExternalProductIdentifier(), productInfo.getExternalProductIdentifier()) && internalEqualityCheck(getProductName(), productInfo.getProductName());
    }

    public String getExternalProductIdentifier() {
        return this.externalProductIdentifier;
    }

    public String getProductName() {
        return this.productName;
    }

    public String getTierName() {
        return this.tierName;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTierName(), getExternalProductIdentifier(), getProductName());
    }

    public void setExternalProductIdentifier(String str) {
        this.externalProductIdentifier = str;
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public void setTierName(String str) {
        this.tierName = str;
    }
}
