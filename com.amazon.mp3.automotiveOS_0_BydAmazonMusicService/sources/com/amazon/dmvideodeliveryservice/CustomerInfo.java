package com.amazon.dmvideodeliveryservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class CustomerInfo implements Comparable<CustomerInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.dmvideodeliveryservice.CustomerInfo");
    private String marketplaceId;
    private String territoryId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CustomerInfo customerInfo) {
        if (customerInfo == null) {
            return -1;
        }
        if (customerInfo == this) {
            return 0;
        }
        String territoryId = getTerritoryId();
        String territoryId2 = customerInfo.getTerritoryId();
        if (territoryId != territoryId2) {
            if (territoryId == null) {
                return -1;
            }
            if (territoryId2 == null) {
                return 1;
            }
            int iCompareTo = territoryId.compareTo(territoryId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = customerInfo.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo2 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CustomerInfo)) {
            return false;
        }
        CustomerInfo customerInfo = (CustomerInfo) obj;
        return internalEqualityCheck(getTerritoryId(), customerInfo.getTerritoryId()) && internalEqualityCheck(getMarketplaceId(), customerInfo.getMarketplaceId());
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getTerritoryId() {
        return this.territoryId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTerritoryId(), getMarketplaceId());
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setTerritoryId(String str) {
        this.territoryId = str;
    }
}
