package com.amazon.nimblymusicservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RecentAlbumActivity extends RecentActivityData {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.RecentAlbumActivity");
    private String artistAsin;
    private String artistName;
    private String marketplaceId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.nimblymusicservice.RecentActivityData, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RecentActivityData recentActivityData) {
        if (recentActivityData == null) {
            return -1;
        }
        if (recentActivityData == this) {
            return 0;
        }
        if (!(recentActivityData instanceof RecentAlbumActivity)) {
            return 1;
        }
        RecentAlbumActivity recentAlbumActivity = (RecentAlbumActivity) recentActivityData;
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = recentAlbumActivity.getMarketplaceId();
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
        String artistAsin = getArtistAsin();
        String artistAsin2 = recentAlbumActivity.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo2 = artistAsin.compareTo(artistAsin2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String artistName = getArtistName();
        String artistName2 = recentAlbumActivity.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo3 = artistName.compareTo(artistName2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(recentActivityData);
    }

    @Override // com.amazon.nimblymusicservice.RecentActivityData
    public boolean equals(Object obj) {
        if (!(obj instanceof RecentAlbumActivity)) {
            return false;
        }
        RecentAlbumActivity recentAlbumActivity = (RecentAlbumActivity) obj;
        return super.equals(obj) && internalEqualityCheck(getMarketplaceId(), recentAlbumActivity.getMarketplaceId()) && internalEqualityCheck(getArtistAsin(), recentAlbumActivity.getArtistAsin()) && internalEqualityCheck(getArtistName(), recentAlbumActivity.getArtistName());
    }

    public String getArtistAsin() {
        return this.artistAsin;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    @Override // com.amazon.nimblymusicservice.RecentActivityData
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMarketplaceId(), getArtistAsin(), getArtistName());
    }

    public void setArtistAsin(String str) {
        this.artistAsin = str;
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }
}
