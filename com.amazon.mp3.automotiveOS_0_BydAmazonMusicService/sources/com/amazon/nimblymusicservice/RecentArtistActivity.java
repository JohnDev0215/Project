package com.amazon.nimblymusicservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RecentArtistActivity extends RecentActivityData {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.RecentArtistActivity");
    private String marketplaceId;
    private String trackTitle;

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
        if (!(recentActivityData instanceof RecentArtistActivity)) {
            return 1;
        }
        RecentArtistActivity recentArtistActivity = (RecentArtistActivity) recentActivityData;
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = recentArtistActivity.getMarketplaceId();
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
        String trackTitle = getTrackTitle();
        String trackTitle2 = recentArtistActivity.getTrackTitle();
        if (trackTitle != trackTitle2) {
            if (trackTitle == null) {
                return -1;
            }
            if (trackTitle2 == null) {
                return 1;
            }
            int iCompareTo2 = trackTitle.compareTo(trackTitle2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(recentActivityData);
    }

    @Override // com.amazon.nimblymusicservice.RecentActivityData
    public boolean equals(Object obj) {
        if (!(obj instanceof RecentArtistActivity)) {
            return false;
        }
        RecentArtistActivity recentArtistActivity = (RecentArtistActivity) obj;
        return super.equals(obj) && internalEqualityCheck(getMarketplaceId(), recentArtistActivity.getMarketplaceId()) && internalEqualityCheck(getTrackTitle(), recentArtistActivity.getTrackTitle());
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getTrackTitle() {
        return this.trackTitle;
    }

    @Override // com.amazon.nimblymusicservice.RecentActivityData
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMarketplaceId(), getTrackTitle());
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setTrackTitle(String str) {
        this.trackTitle = str;
    }
}
