package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class DisplayableInfo implements Comparable<DisplayableInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.DisplayableInfo");
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
    public int compareTo(@Deprecated DisplayableInfo displayableInfo) {
        if (displayableInfo == null) {
            return -1;
        }
        if (displayableInfo == this) {
            return 0;
        }
        String tierName = getTierName();
        String tierName2 = displayableInfo.getTierName();
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
        String productName = getProductName();
        String productName2 = displayableInfo.getProductName();
        if (productName != productName2) {
            if (productName == null) {
                return -1;
            }
            if (productName2 == null) {
                return 1;
            }
            int iCompareTo2 = productName.compareTo(productName2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DisplayableInfo)) {
            return false;
        }
        DisplayableInfo displayableInfo = (DisplayableInfo) obj;
        return internalEqualityCheck(getTierName(), displayableInfo.getTierName()) && internalEqualityCheck(getProductName(), displayableInfo.getProductName());
    }

    public String getProductName() {
        return this.productName;
    }

    public String getTierName() {
        return this.tierName;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTierName(), getProductName());
    }

    public void setProductName(String str) {
        this.productName = str;
    }

    public void setTierName(String str) {
        this.tierName = str;
    }
}
