package com.amazon.nimblymusicservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RecentUserPlaylistActivity extends RecentActivityData {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.RecentUserPlaylistActivity");
    private String curatedBy;
    private String durationSeconds;
    private String isCurator;
    private String isFollowing;
    private String marketplaceId;
    private String shareBlurb;
    private String sharingUrl;
    private String trackCount;
    private String version;

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
        if (!(recentActivityData instanceof RecentUserPlaylistActivity)) {
            return 1;
        }
        RecentUserPlaylistActivity recentUserPlaylistActivity = (RecentUserPlaylistActivity) recentActivityData;
        String isFollowing = getIsFollowing();
        String isFollowing2 = recentUserPlaylistActivity.getIsFollowing();
        if (isFollowing != isFollowing2) {
            if (isFollowing == null) {
                return -1;
            }
            if (isFollowing2 == null) {
                return 1;
            }
            int iCompareTo = isFollowing.compareTo(isFollowing2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = recentUserPlaylistActivity.getMarketplaceId();
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
        String version = getVersion();
        String version2 = recentUserPlaylistActivity.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            int iCompareTo3 = version.compareTo(version2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String trackCount = getTrackCount();
        String trackCount2 = recentUserPlaylistActivity.getTrackCount();
        if (trackCount != trackCount2) {
            if (trackCount == null) {
                return -1;
            }
            if (trackCount2 == null) {
                return 1;
            }
            int iCompareTo4 = trackCount.compareTo(trackCount2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String isCurator = getIsCurator();
        String isCurator2 = recentUserPlaylistActivity.getIsCurator();
        if (isCurator != isCurator2) {
            if (isCurator == null) {
                return -1;
            }
            if (isCurator2 == null) {
                return 1;
            }
            int iCompareTo5 = isCurator.compareTo(isCurator2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String durationSeconds = getDurationSeconds();
        String durationSeconds2 = recentUserPlaylistActivity.getDurationSeconds();
        if (durationSeconds != durationSeconds2) {
            if (durationSeconds == null) {
                return -1;
            }
            if (durationSeconds2 == null) {
                return 1;
            }
            int iCompareTo6 = durationSeconds.compareTo(durationSeconds2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String shareBlurb = getShareBlurb();
        String shareBlurb2 = recentUserPlaylistActivity.getShareBlurb();
        if (shareBlurb != shareBlurb2) {
            if (shareBlurb == null) {
                return -1;
            }
            if (shareBlurb2 == null) {
                return 1;
            }
            int iCompareTo7 = shareBlurb.compareTo(shareBlurb2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String curatedBy = getCuratedBy();
        String curatedBy2 = recentUserPlaylistActivity.getCuratedBy();
        if (curatedBy != curatedBy2) {
            if (curatedBy == null) {
                return -1;
            }
            if (curatedBy2 == null) {
                return 1;
            }
            int iCompareTo8 = curatedBy.compareTo(curatedBy2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String sharingUrl = getSharingUrl();
        String sharingUrl2 = recentUserPlaylistActivity.getSharingUrl();
        if (sharingUrl != sharingUrl2) {
            if (sharingUrl == null) {
                return -1;
            }
            if (sharingUrl2 == null) {
                return 1;
            }
            int iCompareTo9 = sharingUrl.compareTo(sharingUrl2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return super.compareTo(recentActivityData);
    }

    @Override // com.amazon.nimblymusicservice.RecentActivityData
    public boolean equals(Object obj) {
        if (!(obj instanceof RecentUserPlaylistActivity)) {
            return false;
        }
        RecentUserPlaylistActivity recentUserPlaylistActivity = (RecentUserPlaylistActivity) obj;
        return super.equals(obj) && internalEqualityCheck(getIsFollowing(), recentUserPlaylistActivity.getIsFollowing()) && internalEqualityCheck(getMarketplaceId(), recentUserPlaylistActivity.getMarketplaceId()) && internalEqualityCheck(getVersion(), recentUserPlaylistActivity.getVersion()) && internalEqualityCheck(getTrackCount(), recentUserPlaylistActivity.getTrackCount()) && internalEqualityCheck(getIsCurator(), recentUserPlaylistActivity.getIsCurator()) && internalEqualityCheck(getDurationSeconds(), recentUserPlaylistActivity.getDurationSeconds()) && internalEqualityCheck(getShareBlurb(), recentUserPlaylistActivity.getShareBlurb()) && internalEqualityCheck(getCuratedBy(), recentUserPlaylistActivity.getCuratedBy()) && internalEqualityCheck(getSharingUrl(), recentUserPlaylistActivity.getSharingUrl());
    }

    public String getCuratedBy() {
        return this.curatedBy;
    }

    public String getDurationSeconds() {
        return this.durationSeconds;
    }

    public String getIsCurator() {
        return this.isCurator;
    }

    public String getIsFollowing() {
        return this.isFollowing;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getShareBlurb() {
        return this.shareBlurb;
    }

    public String getSharingUrl() {
        return this.sharingUrl;
    }

    public String getTrackCount() {
        return this.trackCount;
    }

    public String getVersion() {
        return this.version;
    }

    @Override // com.amazon.nimblymusicservice.RecentActivityData
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getIsFollowing(), getMarketplaceId(), getVersion(), getTrackCount(), getIsCurator(), getDurationSeconds(), getShareBlurb(), getCuratedBy(), getSharingUrl());
    }

    public void setCuratedBy(String str) {
        this.curatedBy = str;
    }

    public void setDurationSeconds(String str) {
        this.durationSeconds = str;
    }

    public void setIsCurator(String str) {
        this.isCurator = str;
    }

    public void setIsFollowing(String str) {
        this.isFollowing = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setShareBlurb(String str) {
        this.shareBlurb = str;
    }

    public void setSharingUrl(String str) {
        this.sharingUrl = str;
    }

    public void setTrackCount(String str) {
        this.trackCount = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
