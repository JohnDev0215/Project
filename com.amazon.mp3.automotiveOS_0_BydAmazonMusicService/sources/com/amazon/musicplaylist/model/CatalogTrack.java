package com.amazon.musicplaylist.model;

import com.amazon.musicplaylist.model.TrackId;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class CatalogTrack extends TrackId {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.CatalogTrack");
    private String asin;
    private String marketplaceId;

    public static class Builder extends TrackId.Builder {
        protected String asin;
        protected String marketplaceId;

        public CatalogTrack build() {
            CatalogTrack catalogTrack = new CatalogTrack();
            populate(catalogTrack);
            return catalogTrack;
        }

        protected void populate(CatalogTrack catalogTrack) {
            super.populate((TrackId) catalogTrack);
            catalogTrack.setMarketplaceId(this.marketplaceId);
            catalogTrack.setAsin(this.asin);
        }

        public Builder withAsin(String str) {
            this.asin = str;
            return this;
        }

        public Builder withMarketplaceId(String str) {
            this.marketplaceId = str;
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

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplaylist.model.TrackId, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TrackId trackId) {
        if (trackId == null) {
            return -1;
        }
        if (trackId == this) {
            return 0;
        }
        if (!(trackId instanceof CatalogTrack)) {
            return 1;
        }
        CatalogTrack catalogTrack = (CatalogTrack) trackId;
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = catalogTrack.getMarketplaceId();
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
        String asin = getAsin();
        String asin2 = catalogTrack.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo2 = asin.compareTo(asin2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(trackId);
    }

    @Override // com.amazon.musicplaylist.model.TrackId
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogTrack)) {
            return false;
        }
        CatalogTrack catalogTrack = (CatalogTrack) obj;
        return super.equals(obj) && internalEqualityCheck(getMarketplaceId(), catalogTrack.getMarketplaceId()) && internalEqualityCheck(getAsin(), catalogTrack.getAsin());
    }

    public String getAsin() {
        return this.asin;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    @Override // com.amazon.musicplaylist.model.TrackId
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMarketplaceId(), getAsin());
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }
}
