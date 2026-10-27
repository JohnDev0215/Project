package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class CatalogTrackId implements Comparable<CatalogTrackId> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.CatalogTrackId");
    private String asin;
    private String marketplaceId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CatalogTrackId catalogTrackId) {
        if (catalogTrackId == null) {
            return -1;
        }
        if (catalogTrackId == this) {
            return 0;
        }
        String asin = getAsin();
        String asin2 = catalogTrackId.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo = asin.compareTo(asin2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = catalogTrackId.getMarketplaceId();
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
        if (!(obj instanceof CatalogTrackId)) {
            return false;
        }
        CatalogTrackId catalogTrackId = (CatalogTrackId) obj;
        return internalEqualityCheck(getAsin(), catalogTrackId.getAsin()) && internalEqualityCheck(getMarketplaceId(), catalogTrackId.getMarketplaceId());
    }

    public String getAsin() {
        return this.asin;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAsin(), getMarketplaceId());
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }
}
