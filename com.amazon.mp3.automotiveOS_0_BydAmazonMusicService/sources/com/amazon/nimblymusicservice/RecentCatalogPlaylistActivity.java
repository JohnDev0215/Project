package com.amazon.nimblymusicservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RecentCatalogPlaylistActivity extends RecentActivityData {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.RecentCatalogPlaylistActivity");
    private String curatedBy;
    private String description;
    private String durationSeconds;
    private String genre;
    private String isFollowing;
    private String marketplaceId;
    private String parentPlaylist;
    private String updatedDate;

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
        if (!(recentActivityData instanceof RecentCatalogPlaylistActivity)) {
            return 1;
        }
        RecentCatalogPlaylistActivity recentCatalogPlaylistActivity = (RecentCatalogPlaylistActivity) recentActivityData;
        String curatedBy = getCuratedBy();
        String curatedBy2 = recentCatalogPlaylistActivity.getCuratedBy();
        if (curatedBy != curatedBy2) {
            if (curatedBy == null) {
                return -1;
            }
            if (curatedBy2 == null) {
                return 1;
            }
            int iCompareTo = curatedBy.compareTo(curatedBy2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String parentPlaylist = getParentPlaylist();
        String parentPlaylist2 = recentCatalogPlaylistActivity.getParentPlaylist();
        if (parentPlaylist != parentPlaylist2) {
            if (parentPlaylist == null) {
                return -1;
            }
            if (parentPlaylist2 == null) {
                return 1;
            }
            int iCompareTo2 = parentPlaylist.compareTo(parentPlaylist2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = recentCatalogPlaylistActivity.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo3 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String genre = getGenre();
        String genre2 = recentCatalogPlaylistActivity.getGenre();
        if (genre != genre2) {
            if (genre == null) {
                return -1;
            }
            if (genre2 == null) {
                return 1;
            }
            int iCompareTo4 = genre.compareTo(genre2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String description = getDescription();
        String description2 = recentCatalogPlaylistActivity.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo5 = description.compareTo(description2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String durationSeconds = getDurationSeconds();
        String durationSeconds2 = recentCatalogPlaylistActivity.getDurationSeconds();
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
        String isFollowing = getIsFollowing();
        String isFollowing2 = recentCatalogPlaylistActivity.getIsFollowing();
        if (isFollowing != isFollowing2) {
            if (isFollowing == null) {
                return -1;
            }
            if (isFollowing2 == null) {
                return 1;
            }
            int iCompareTo7 = isFollowing.compareTo(isFollowing2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String updatedDate = getUpdatedDate();
        String updatedDate2 = recentCatalogPlaylistActivity.getUpdatedDate();
        if (updatedDate != updatedDate2) {
            if (updatedDate == null) {
                return -1;
            }
            if (updatedDate2 == null) {
                return 1;
            }
            int iCompareTo8 = updatedDate.compareTo(updatedDate2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        return super.compareTo(recentActivityData);
    }

    @Override // com.amazon.nimblymusicservice.RecentActivityData
    public boolean equals(Object obj) {
        if (!(obj instanceof RecentCatalogPlaylistActivity)) {
            return false;
        }
        RecentCatalogPlaylistActivity recentCatalogPlaylistActivity = (RecentCatalogPlaylistActivity) obj;
        return super.equals(obj) && internalEqualityCheck(getCuratedBy(), recentCatalogPlaylistActivity.getCuratedBy()) && internalEqualityCheck(getParentPlaylist(), recentCatalogPlaylistActivity.getParentPlaylist()) && internalEqualityCheck(getMarketplaceId(), recentCatalogPlaylistActivity.getMarketplaceId()) && internalEqualityCheck(getGenre(), recentCatalogPlaylistActivity.getGenre()) && internalEqualityCheck(getDescription(), recentCatalogPlaylistActivity.getDescription()) && internalEqualityCheck(getDurationSeconds(), recentCatalogPlaylistActivity.getDurationSeconds()) && internalEqualityCheck(getIsFollowing(), recentCatalogPlaylistActivity.getIsFollowing()) && internalEqualityCheck(getUpdatedDate(), recentCatalogPlaylistActivity.getUpdatedDate());
    }

    public String getCuratedBy() {
        return this.curatedBy;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDurationSeconds() {
        return this.durationSeconds;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getIsFollowing() {
        return this.isFollowing;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getParentPlaylist() {
        return this.parentPlaylist;
    }

    public String getUpdatedDate() {
        return this.updatedDate;
    }

    @Override // com.amazon.nimblymusicservice.RecentActivityData
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getCuratedBy(), getParentPlaylist(), getMarketplaceId(), getGenre(), getDescription(), getDurationSeconds(), getIsFollowing(), getUpdatedDate());
    }

    public void setCuratedBy(String str) {
        this.curatedBy = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDurationSeconds(String str) {
        this.durationSeconds = str;
    }

    public void setGenre(String str) {
        this.genre = str;
    }

    public void setIsFollowing(String str) {
        this.isFollowing = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setParentPlaylist(String str) {
        this.parentPlaylist = str;
    }

    public void setUpdatedDate(String str) {
        this.updatedDate = str;
    }
}
