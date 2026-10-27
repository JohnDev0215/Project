package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class PlaylistMetadata implements Comparable<PlaylistMetadata> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.PlaylistMetadata");
    private String asin;
    private String autoPlaylistType;
    private Map<String, String> bannerArt;
    private Image bannerImage;
    private boolean canEditContents;
    private boolean canRemovePlaylist;
    private boolean canRenamePlaylist;
    private boolean canReorderPlaylist;
    private String collaborativeMode;
    private Date createdDate;
    private String curatedBy;
    private String description;
    private int durationSeconds;
    private Map<String, String> fourSquareArt;
    private Image fourSquareImage;
    private boolean isCustomerFollowing;
    private boolean isDeleted;
    private boolean isMusicSubscription;
    private boolean isNightwing;
    private boolean isNightwingOnDemandPlayable;
    private boolean isPrime;
    private boolean isShareableType;
    private Date lastModifiedDate;
    private String marketplaceId;
    private int numFollowers;
    private String playlistId;
    private String profileId;
    private String shareBlurb;
    private String sharedPlaylistId;
    private String sharingUrl;
    private List<String> socialSharingErrors;
    private String title;
    private int totalTrackCount;
    private String version;
    private String visibility;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PlaylistMetadata playlistMetadata) {
        if (playlistMetadata == null) {
            return -1;
        }
        if (playlistMetadata == this) {
            return 0;
        }
        String profileId = getProfileId();
        String profileId2 = playlistMetadata.getProfileId();
        if (profileId != profileId2) {
            if (profileId == null) {
                return -1;
            }
            if (profileId2 == null) {
                return 1;
            }
            int iCompareTo = profileId.compareTo(profileId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String collaborativeMode = getCollaborativeMode();
        String collaborativeMode2 = playlistMetadata.getCollaborativeMode();
        if (collaborativeMode != collaborativeMode2) {
            if (collaborativeMode == null) {
                return -1;
            }
            if (collaborativeMode2 == null) {
                return 1;
            }
            int iCompareTo2 = collaborativeMode.compareTo(collaborativeMode2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String playlistId = getPlaylistId();
        String playlistId2 = playlistMetadata.getPlaylistId();
        if (playlistId != playlistId2) {
            if (playlistId == null) {
                return -1;
            }
            if (playlistId2 == null) {
                return 1;
            }
            int iCompareTo3 = playlistId.compareTo(playlistId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Map<String, String> bannerArt = getBannerArt();
        Map<String, String> bannerArt2 = playlistMetadata.getBannerArt();
        if (bannerArt != bannerArt2) {
            if (bannerArt == null) {
                return -1;
            }
            if (bannerArt2 == null) {
                return 1;
            }
            if (bannerArt instanceof Comparable) {
                int iCompareTo4 = ((Comparable) bannerArt).compareTo(bannerArt2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!bannerArt.equals(bannerArt2)) {
                int iHashCode = bannerArt.hashCode();
                int iHashCode2 = bannerArt2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        if (!isCanRemovePlaylist() && playlistMetadata.isCanRemovePlaylist()) {
            return -1;
        }
        if (isCanRemovePlaylist() && !playlistMetadata.isCanRemovePlaylist()) {
            return 1;
        }
        String version = getVersion();
        String version2 = playlistMetadata.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            int iCompareTo5 = version.compareTo(version2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        if (!isIsMusicSubscription() && playlistMetadata.isIsMusicSubscription()) {
            return -1;
        }
        if (isIsMusicSubscription() && !playlistMetadata.isIsMusicSubscription()) {
            return 1;
        }
        if (!isCanReorderPlaylist() && playlistMetadata.isCanReorderPlaylist()) {
            return -1;
        }
        if (isCanReorderPlaylist() && !playlistMetadata.isCanReorderPlaylist()) {
            return 1;
        }
        String description = getDescription();
        String description2 = playlistMetadata.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo6 = description.compareTo(description2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Image bannerImage = getBannerImage();
        Image bannerImage2 = playlistMetadata.getBannerImage();
        if (bannerImage != bannerImage2) {
            if (bannerImage == null) {
                return -1;
            }
            if (bannerImage2 == null) {
                return 1;
            }
            int iCompareTo7 = bannerImage.compareTo(bannerImage2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        if (!isIsCustomerFollowing() && playlistMetadata.isIsCustomerFollowing()) {
            return -1;
        }
        if (isIsCustomerFollowing() && !playlistMetadata.isIsCustomerFollowing()) {
            return 1;
        }
        String curatedBy = getCuratedBy();
        String curatedBy2 = playlistMetadata.getCuratedBy();
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
        String sharingUrl2 = playlistMetadata.getSharingUrl();
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
        if (!isIsPrime() && playlistMetadata.isIsPrime()) {
            return -1;
        }
        if (isIsPrime() && !playlistMetadata.isIsPrime()) {
            return 1;
        }
        String asin = getAsin();
        String asin2 = playlistMetadata.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo10 = asin.compareTo(asin2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        if (!isIsShareableType() && playlistMetadata.isIsShareableType()) {
            return -1;
        }
        if (isIsShareableType() && !playlistMetadata.isIsShareableType()) {
            return 1;
        }
        if (!isIsDeleted() && playlistMetadata.isIsDeleted()) {
            return -1;
        }
        if (isIsDeleted() && !playlistMetadata.isIsDeleted()) {
            return 1;
        }
        Date lastModifiedDate = getLastModifiedDate();
        Date lastModifiedDate2 = playlistMetadata.getLastModifiedDate();
        if (lastModifiedDate != lastModifiedDate2) {
            if (lastModifiedDate == null) {
                return -1;
            }
            if (lastModifiedDate2 == null) {
                return 1;
            }
            int iCompareTo11 = lastModifiedDate.compareTo(lastModifiedDate2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String visibility = getVisibility();
        String visibility2 = playlistMetadata.getVisibility();
        if (visibility != visibility2) {
            if (visibility == null) {
                return -1;
            }
            if (visibility2 == null) {
                return 1;
            }
            int iCompareTo12 = visibility.compareTo(visibility2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        if (!isCanRenamePlaylist() && playlistMetadata.isCanRenamePlaylist()) {
            return -1;
        }
        if (isCanRenamePlaylist() && !playlistMetadata.isCanRenamePlaylist()) {
            return 1;
        }
        if (getDurationSeconds() < playlistMetadata.getDurationSeconds()) {
            return -1;
        }
        if (getDurationSeconds() > playlistMetadata.getDurationSeconds()) {
            return 1;
        }
        String sharedPlaylistId = getSharedPlaylistId();
        String sharedPlaylistId2 = playlistMetadata.getSharedPlaylistId();
        if (sharedPlaylistId != sharedPlaylistId2) {
            if (sharedPlaylistId == null) {
                return -1;
            }
            if (sharedPlaylistId2 == null) {
                return 1;
            }
            int iCompareTo13 = sharedPlaylistId.compareTo(sharedPlaylistId2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = playlistMetadata.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo14 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        if (getTotalTrackCount() < playlistMetadata.getTotalTrackCount()) {
            return -1;
        }
        if (getTotalTrackCount() > playlistMetadata.getTotalTrackCount()) {
            return 1;
        }
        if (!isCanEditContents() && playlistMetadata.isCanEditContents()) {
            return -1;
        }
        if (isCanEditContents() && !playlistMetadata.isCanEditContents()) {
            return 1;
        }
        String shareBlurb = getShareBlurb();
        String shareBlurb2 = playlistMetadata.getShareBlurb();
        if (shareBlurb != shareBlurb2) {
            if (shareBlurb == null) {
                return -1;
            }
            if (shareBlurb2 == null) {
                return 1;
            }
            int iCompareTo15 = shareBlurb.compareTo(shareBlurb2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        if (!isIsNightwingOnDemandPlayable() && playlistMetadata.isIsNightwingOnDemandPlayable()) {
            return -1;
        }
        if (isIsNightwingOnDemandPlayable() && !playlistMetadata.isIsNightwingOnDemandPlayable()) {
            return 1;
        }
        List<String> socialSharingErrors = getSocialSharingErrors();
        List<String> socialSharingErrors2 = playlistMetadata.getSocialSharingErrors();
        if (socialSharingErrors != socialSharingErrors2) {
            if (socialSharingErrors == null) {
                return -1;
            }
            if (socialSharingErrors2 == null) {
                return 1;
            }
            if (socialSharingErrors instanceof Comparable) {
                int iCompareTo16 = ((Comparable) socialSharingErrors).compareTo(socialSharingErrors2);
                if (iCompareTo16 != 0) {
                    return iCompareTo16;
                }
            } else if (!socialSharingErrors.equals(socialSharingErrors2)) {
                int iHashCode3 = socialSharingErrors.hashCode();
                int iHashCode4 = socialSharingErrors2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        if (!isIsNightwing() && playlistMetadata.isIsNightwing()) {
            return -1;
        }
        if (isIsNightwing() && !playlistMetadata.isIsNightwing()) {
            return 1;
        }
        Image fourSquareImage = getFourSquareImage();
        Image fourSquareImage2 = playlistMetadata.getFourSquareImage();
        if (fourSquareImage != fourSquareImage2) {
            if (fourSquareImage == null) {
                return -1;
            }
            if (fourSquareImage2 == null) {
                return 1;
            }
            int iCompareTo17 = fourSquareImage.compareTo(fourSquareImage2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        if (getNumFollowers() < playlistMetadata.getNumFollowers()) {
            return -1;
        }
        if (getNumFollowers() > playlistMetadata.getNumFollowers()) {
            return 1;
        }
        Map<String, String> fourSquareArt = getFourSquareArt();
        Map<String, String> fourSquareArt2 = playlistMetadata.getFourSquareArt();
        if (fourSquareArt != fourSquareArt2) {
            if (fourSquareArt == null) {
                return -1;
            }
            if (fourSquareArt2 == null) {
                return 1;
            }
            if (fourSquareArt instanceof Comparable) {
                int iCompareTo18 = ((Comparable) fourSquareArt).compareTo(fourSquareArt2);
                if (iCompareTo18 != 0) {
                    return iCompareTo18;
                }
            } else if (!fourSquareArt.equals(fourSquareArt2)) {
                int iHashCode5 = fourSquareArt.hashCode();
                int iHashCode6 = fourSquareArt2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String title = getTitle();
        String title2 = playlistMetadata.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo19 = title.compareTo(title2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        String autoPlaylistType = getAutoPlaylistType();
        String autoPlaylistType2 = playlistMetadata.getAutoPlaylistType();
        if (autoPlaylistType != autoPlaylistType2) {
            if (autoPlaylistType == null) {
                return -1;
            }
            if (autoPlaylistType2 == null) {
                return 1;
            }
            int iCompareTo20 = autoPlaylistType.compareTo(autoPlaylistType2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        Date createdDate = getCreatedDate();
        Date createdDate2 = playlistMetadata.getCreatedDate();
        if (createdDate != createdDate2) {
            if (createdDate == null) {
                return -1;
            }
            if (createdDate2 == null) {
                return 1;
            }
            int iCompareTo21 = createdDate.compareTo(createdDate2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaylistMetadata)) {
            return false;
        }
        PlaylistMetadata playlistMetadata = (PlaylistMetadata) obj;
        return internalEqualityCheck(getProfileId(), playlistMetadata.getProfileId()) && internalEqualityCheck(getCollaborativeMode(), playlistMetadata.getCollaborativeMode()) && internalEqualityCheck(getPlaylistId(), playlistMetadata.getPlaylistId()) && internalEqualityCheck(getBannerArt(), playlistMetadata.getBannerArt()) && internalEqualityCheck(Boolean.valueOf(isCanRemovePlaylist()), Boolean.valueOf(playlistMetadata.isCanRemovePlaylist())) && internalEqualityCheck(getVersion(), playlistMetadata.getVersion()) && internalEqualityCheck(Boolean.valueOf(isIsMusicSubscription()), Boolean.valueOf(playlistMetadata.isIsMusicSubscription())) && internalEqualityCheck(Boolean.valueOf(isCanReorderPlaylist()), Boolean.valueOf(playlistMetadata.isCanReorderPlaylist())) && internalEqualityCheck(getDescription(), playlistMetadata.getDescription()) && internalEqualityCheck(getBannerImage(), playlistMetadata.getBannerImage()) && internalEqualityCheck(Boolean.valueOf(isIsCustomerFollowing()), Boolean.valueOf(playlistMetadata.isIsCustomerFollowing())) && internalEqualityCheck(getCuratedBy(), playlistMetadata.getCuratedBy()) && internalEqualityCheck(getSharingUrl(), playlistMetadata.getSharingUrl()) && internalEqualityCheck(Boolean.valueOf(isIsPrime()), Boolean.valueOf(playlistMetadata.isIsPrime())) && internalEqualityCheck(getAsin(), playlistMetadata.getAsin()) && internalEqualityCheck(Boolean.valueOf(isIsShareableType()), Boolean.valueOf(playlistMetadata.isIsShareableType())) && internalEqualityCheck(Boolean.valueOf(isIsDeleted()), Boolean.valueOf(playlistMetadata.isIsDeleted())) && internalEqualityCheck(getLastModifiedDate(), playlistMetadata.getLastModifiedDate()) && internalEqualityCheck(getVisibility(), playlistMetadata.getVisibility()) && internalEqualityCheck(Boolean.valueOf(isCanRenamePlaylist()), Boolean.valueOf(playlistMetadata.isCanRenamePlaylist())) && internalEqualityCheck(Integer.valueOf(getDurationSeconds()), Integer.valueOf(playlistMetadata.getDurationSeconds())) && internalEqualityCheck(getSharedPlaylistId(), playlistMetadata.getSharedPlaylistId()) && internalEqualityCheck(getMarketplaceId(), playlistMetadata.getMarketplaceId()) && internalEqualityCheck(Integer.valueOf(getTotalTrackCount()), Integer.valueOf(playlistMetadata.getTotalTrackCount())) && internalEqualityCheck(Boolean.valueOf(isCanEditContents()), Boolean.valueOf(playlistMetadata.isCanEditContents())) && internalEqualityCheck(getShareBlurb(), playlistMetadata.getShareBlurb()) && internalEqualityCheck(Boolean.valueOf(isIsNightwingOnDemandPlayable()), Boolean.valueOf(playlistMetadata.isIsNightwingOnDemandPlayable())) && internalEqualityCheck(getSocialSharingErrors(), playlistMetadata.getSocialSharingErrors()) && internalEqualityCheck(Boolean.valueOf(isIsNightwing()), Boolean.valueOf(playlistMetadata.isIsNightwing())) && internalEqualityCheck(getFourSquareImage(), playlistMetadata.getFourSquareImage()) && internalEqualityCheck(Integer.valueOf(getNumFollowers()), Integer.valueOf(playlistMetadata.getNumFollowers())) && internalEqualityCheck(getFourSquareArt(), playlistMetadata.getFourSquareArt()) && internalEqualityCheck(getTitle(), playlistMetadata.getTitle()) && internalEqualityCheck(getAutoPlaylistType(), playlistMetadata.getAutoPlaylistType()) && internalEqualityCheck(getCreatedDate(), playlistMetadata.getCreatedDate());
    }

    public String getAsin() {
        return this.asin;
    }

    public String getAutoPlaylistType() {
        return this.autoPlaylistType;
    }

    public Map<String, String> getBannerArt() {
        return this.bannerArt;
    }

    public Image getBannerImage() {
        return this.bannerImage;
    }

    public String getCollaborativeMode() {
        return this.collaborativeMode;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public String getCuratedBy() {
        return this.curatedBy;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDurationSeconds() {
        return this.durationSeconds;
    }

    public Map<String, String> getFourSquareArt() {
        return this.fourSquareArt;
    }

    public Image getFourSquareImage() {
        return this.fourSquareImage;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public int getNumFollowers() {
        return this.numFollowers;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public String getShareBlurb() {
        return this.shareBlurb;
    }

    public String getSharedPlaylistId() {
        return this.sharedPlaylistId;
    }

    public String getSharingUrl() {
        return this.sharingUrl;
    }

    public List<String> getSocialSharingErrors() {
        return this.socialSharingErrors;
    }

    public String getTitle() {
        return this.title;
    }

    public int getTotalTrackCount() {
        return this.totalTrackCount;
    }

    public String getVersion() {
        return this.version;
    }

    public String getVisibility() {
        return this.visibility;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getProfileId(), getCollaborativeMode(), getPlaylistId(), getBannerArt(), Boolean.valueOf(isCanRemovePlaylist()), getVersion(), Boolean.valueOf(isIsMusicSubscription()), Boolean.valueOf(isCanReorderPlaylist()), getDescription(), getBannerImage(), Boolean.valueOf(isIsCustomerFollowing()), getCuratedBy(), getSharingUrl(), Boolean.valueOf(isIsPrime()), getAsin(), Boolean.valueOf(isIsShareableType()), Boolean.valueOf(isIsDeleted()), getLastModifiedDate(), getVisibility(), Boolean.valueOf(isCanRenamePlaylist()), Integer.valueOf(getDurationSeconds()), getSharedPlaylistId(), getMarketplaceId(), Integer.valueOf(getTotalTrackCount()), Boolean.valueOf(isCanEditContents()), getShareBlurb(), Boolean.valueOf(isIsNightwingOnDemandPlayable()), getSocialSharingErrors(), Boolean.valueOf(isIsNightwing()), getFourSquareImage(), Integer.valueOf(getNumFollowers()), getFourSquareArt(), getTitle(), getAutoPlaylistType(), getCreatedDate());
    }

    public boolean isCanEditContents() {
        return this.canEditContents;
    }

    public boolean isCanRemovePlaylist() {
        return this.canRemovePlaylist;
    }

    public boolean isCanRenamePlaylist() {
        return this.canRenamePlaylist;
    }

    public boolean isCanReorderPlaylist() {
        return this.canReorderPlaylist;
    }

    public boolean isIsCustomerFollowing() {
        return this.isCustomerFollowing;
    }

    public boolean isIsDeleted() {
        return this.isDeleted;
    }

    public boolean isIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public boolean isIsNightwing() {
        return this.isNightwing;
    }

    public boolean isIsNightwingOnDemandPlayable() {
        return this.isNightwingOnDemandPlayable;
    }

    public boolean isIsPrime() {
        return this.isPrime;
    }

    public boolean isIsShareableType() {
        return this.isShareableType;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setAutoPlaylistType(String str) {
        this.autoPlaylistType = str;
    }

    public void setBannerArt(Map<String, String> map) {
        this.bannerArt = map;
    }

    public void setBannerImage(Image image) {
        this.bannerImage = image;
    }

    public void setCanEditContents(boolean z) {
        this.canEditContents = z;
    }

    public void setCanRemovePlaylist(boolean z) {
        this.canRemovePlaylist = z;
    }

    public void setCanRenamePlaylist(boolean z) {
        this.canRenamePlaylist = z;
    }

    public void setCanReorderPlaylist(boolean z) {
        this.canReorderPlaylist = z;
    }

    public void setCollaborativeMode(String str) {
        this.collaborativeMode = str;
    }

    public void setCreatedDate(Date date) {
        this.createdDate = date;
    }

    public void setCuratedBy(String str) {
        this.curatedBy = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDurationSeconds(int i) {
        this.durationSeconds = i;
    }

    public void setFourSquareArt(Map<String, String> map) {
        this.fourSquareArt = map;
    }

    public void setFourSquareImage(Image image) {
        this.fourSquareImage = image;
    }

    public void setIsCustomerFollowing(boolean z) {
        this.isCustomerFollowing = z;
    }

    public void setIsDeleted(boolean z) {
        this.isDeleted = z;
    }

    public void setIsMusicSubscription(boolean z) {
        this.isMusicSubscription = z;
    }

    public void setIsNightwing(boolean z) {
        this.isNightwing = z;
    }

    public void setIsNightwingOnDemandPlayable(boolean z) {
        this.isNightwingOnDemandPlayable = z;
    }

    public void setIsPrime(boolean z) {
        this.isPrime = z;
    }

    public void setIsShareableType(boolean z) {
        this.isShareableType = z;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setNumFollowers(int i) {
        this.numFollowers = i;
    }

    public void setPlaylistId(String str) {
        this.playlistId = str;
    }

    public void setProfileId(String str) {
        this.profileId = str;
    }

    public void setShareBlurb(String str) {
        this.shareBlurb = str;
    }

    public void setSharedPlaylistId(String str) {
        this.sharedPlaylistId = str;
    }

    public void setSharingUrl(String str) {
        this.sharingUrl = str;
    }

    public void setSocialSharingErrors(List<String> list) {
        this.socialSharingErrors = list;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTotalTrackCount(int i) {
        this.totalTrackCount = i;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setVisibility(String str) {
        this.visibility = str;
    }
}
