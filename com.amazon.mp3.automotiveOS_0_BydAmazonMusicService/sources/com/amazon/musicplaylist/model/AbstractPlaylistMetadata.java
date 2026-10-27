package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractPlaylistMetadata implements Comparable<AbstractPlaylistMetadata> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.AbstractPlaylistMetadata");
    private Image bannerArt;
    private boolean canEditContents;
    private boolean canRemovePlaylist;
    private boolean canRenamePlaylist;
    private boolean canReorderPlaylist;
    private Date createdDate;
    private String description;
    private int durationSeconds;
    private Image fourSquareArt;
    private boolean generatedForCustomer;
    private Date goLiveDate;
    private boolean isShareableType;
    private Date lastModifiedDate;
    private String playlistId;
    private String sharedPlaylistId;
    private String sharingUrl;
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
    public int compareTo(@Deprecated AbstractPlaylistMetadata abstractPlaylistMetadata) {
        if (abstractPlaylistMetadata == null) {
            return -1;
        }
        if (abstractPlaylistMetadata == this) {
            return 0;
        }
        String sharedPlaylistId = getSharedPlaylistId();
        String sharedPlaylistId2 = abstractPlaylistMetadata.getSharedPlaylistId();
        if (sharedPlaylistId != sharedPlaylistId2) {
            if (sharedPlaylistId == null) {
                return -1;
            }
            if (sharedPlaylistId2 == null) {
                return 1;
            }
            int iCompareTo = sharedPlaylistId.compareTo(sharedPlaylistId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Image bannerArt = getBannerArt();
        Image bannerArt2 = abstractPlaylistMetadata.getBannerArt();
        if (bannerArt != bannerArt2) {
            if (bannerArt == null) {
                return -1;
            }
            if (bannerArt2 == null) {
                return 1;
            }
            int iCompareTo2 = bannerArt.compareTo(bannerArt2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        if (!isCanEditContents() && abstractPlaylistMetadata.isCanEditContents()) {
            return -1;
        }
        if (isCanEditContents() && !abstractPlaylistMetadata.isCanEditContents()) {
            return 1;
        }
        String sharingUrl = getSharingUrl();
        String sharingUrl2 = abstractPlaylistMetadata.getSharingUrl();
        if (sharingUrl != sharingUrl2) {
            if (sharingUrl == null) {
                return -1;
            }
            if (sharingUrl2 == null) {
                return 1;
            }
            int iCompareTo3 = sharingUrl.compareTo(sharingUrl2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        if (!isCanRenamePlaylist() && abstractPlaylistMetadata.isCanRenamePlaylist()) {
            return -1;
        }
        if (isCanRenamePlaylist() && !abstractPlaylistMetadata.isCanRenamePlaylist()) {
            return 1;
        }
        String version = getVersion();
        String version2 = abstractPlaylistMetadata.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            int iCompareTo4 = version.compareTo(version2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        if (!isCanRemovePlaylist() && abstractPlaylistMetadata.isCanRemovePlaylist()) {
            return -1;
        }
        if (isCanRemovePlaylist() && !abstractPlaylistMetadata.isCanRemovePlaylist()) {
            return 1;
        }
        if (getTotalTrackCount() < abstractPlaylistMetadata.getTotalTrackCount()) {
            return -1;
        }
        if (getTotalTrackCount() > abstractPlaylistMetadata.getTotalTrackCount()) {
            return 1;
        }
        if (getDurationSeconds() < abstractPlaylistMetadata.getDurationSeconds()) {
            return -1;
        }
        if (getDurationSeconds() > abstractPlaylistMetadata.getDurationSeconds()) {
            return 1;
        }
        if (!isGeneratedForCustomer() && abstractPlaylistMetadata.isGeneratedForCustomer()) {
            return -1;
        }
        if (isGeneratedForCustomer() && !abstractPlaylistMetadata.isGeneratedForCustomer()) {
            return 1;
        }
        Image fourSquareArt = getFourSquareArt();
        Image fourSquareArt2 = abstractPlaylistMetadata.getFourSquareArt();
        if (fourSquareArt != fourSquareArt2) {
            if (fourSquareArt == null) {
                return -1;
            }
            if (fourSquareArt2 == null) {
                return 1;
            }
            int iCompareTo5 = fourSquareArt.compareTo(fourSquareArt2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String visibility = getVisibility();
        String visibility2 = abstractPlaylistMetadata.getVisibility();
        if (visibility != visibility2) {
            if (visibility == null) {
                return -1;
            }
            if (visibility2 == null) {
                return 1;
            }
            int iCompareTo6 = visibility.compareTo(visibility2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String playlistId = getPlaylistId();
        String playlistId2 = abstractPlaylistMetadata.getPlaylistId();
        if (playlistId != playlistId2) {
            if (playlistId == null) {
                return -1;
            }
            if (playlistId2 == null) {
                return 1;
            }
            int iCompareTo7 = playlistId.compareTo(playlistId2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String description = getDescription();
        String description2 = abstractPlaylistMetadata.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo8 = description.compareTo(description2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        if (!isCanReorderPlaylist() && abstractPlaylistMetadata.isCanReorderPlaylist()) {
            return -1;
        }
        if (isCanReorderPlaylist() && !abstractPlaylistMetadata.isCanReorderPlaylist()) {
            return 1;
        }
        Date createdDate = getCreatedDate();
        Date createdDate2 = abstractPlaylistMetadata.getCreatedDate();
        if (createdDate != createdDate2) {
            if (createdDate == null) {
                return -1;
            }
            if (createdDate2 == null) {
                return 1;
            }
            int iCompareTo9 = createdDate.compareTo(createdDate2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String title = getTitle();
        String title2 = abstractPlaylistMetadata.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo10 = title.compareTo(title2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        if (!isIsShareableType() && abstractPlaylistMetadata.isIsShareableType()) {
            return -1;
        }
        if (isIsShareableType() && !abstractPlaylistMetadata.isIsShareableType()) {
            return 1;
        }
        Date lastModifiedDate = getLastModifiedDate();
        Date lastModifiedDate2 = abstractPlaylistMetadata.getLastModifiedDate();
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
        Date goLiveDate = getGoLiveDate();
        Date goLiveDate2 = abstractPlaylistMetadata.getGoLiveDate();
        if (goLiveDate != goLiveDate2) {
            if (goLiveDate == null) {
                return -1;
            }
            if (goLiveDate2 == null) {
                return 1;
            }
            int iCompareTo12 = goLiveDate.compareTo(goLiveDate2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractPlaylistMetadata)) {
            return false;
        }
        AbstractPlaylistMetadata abstractPlaylistMetadata = (AbstractPlaylistMetadata) obj;
        return internalEqualityCheck(getSharedPlaylistId(), abstractPlaylistMetadata.getSharedPlaylistId()) && internalEqualityCheck(getBannerArt(), abstractPlaylistMetadata.getBannerArt()) && internalEqualityCheck(Boolean.valueOf(isCanEditContents()), Boolean.valueOf(abstractPlaylistMetadata.isCanEditContents())) && internalEqualityCheck(getSharingUrl(), abstractPlaylistMetadata.getSharingUrl()) && internalEqualityCheck(Boolean.valueOf(isCanRenamePlaylist()), Boolean.valueOf(abstractPlaylistMetadata.isCanRenamePlaylist())) && internalEqualityCheck(getVersion(), abstractPlaylistMetadata.getVersion()) && internalEqualityCheck(Boolean.valueOf(isCanRemovePlaylist()), Boolean.valueOf(abstractPlaylistMetadata.isCanRemovePlaylist())) && internalEqualityCheck(Integer.valueOf(getTotalTrackCount()), Integer.valueOf(abstractPlaylistMetadata.getTotalTrackCount())) && internalEqualityCheck(Integer.valueOf(getDurationSeconds()), Integer.valueOf(abstractPlaylistMetadata.getDurationSeconds())) && internalEqualityCheck(Boolean.valueOf(isGeneratedForCustomer()), Boolean.valueOf(abstractPlaylistMetadata.isGeneratedForCustomer())) && internalEqualityCheck(getFourSquareArt(), abstractPlaylistMetadata.getFourSquareArt()) && internalEqualityCheck(getVisibility(), abstractPlaylistMetadata.getVisibility()) && internalEqualityCheck(getPlaylistId(), abstractPlaylistMetadata.getPlaylistId()) && internalEqualityCheck(getDescription(), abstractPlaylistMetadata.getDescription()) && internalEqualityCheck(Boolean.valueOf(isCanReorderPlaylist()), Boolean.valueOf(abstractPlaylistMetadata.isCanReorderPlaylist())) && internalEqualityCheck(getCreatedDate(), abstractPlaylistMetadata.getCreatedDate()) && internalEqualityCheck(getTitle(), abstractPlaylistMetadata.getTitle()) && internalEqualityCheck(Boolean.valueOf(isIsShareableType()), Boolean.valueOf(abstractPlaylistMetadata.isIsShareableType())) && internalEqualityCheck(getLastModifiedDate(), abstractPlaylistMetadata.getLastModifiedDate()) && internalEqualityCheck(getGoLiveDate(), abstractPlaylistMetadata.getGoLiveDate());
    }

    public Image getBannerArt() {
        return this.bannerArt;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDurationSeconds() {
        return this.durationSeconds;
    }

    public Image getFourSquareArt() {
        return this.fourSquareArt;
    }

    public Date getGoLiveDate() {
        return this.goLiveDate;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public String getSharedPlaylistId() {
        return this.sharedPlaylistId;
    }

    public String getSharingUrl() {
        return this.sharingUrl;
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
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSharedPlaylistId(), getBannerArt(), Boolean.valueOf(isCanEditContents()), getSharingUrl(), Boolean.valueOf(isCanRenamePlaylist()), getVersion(), Boolean.valueOf(isCanRemovePlaylist()), Integer.valueOf(getTotalTrackCount()), Integer.valueOf(getDurationSeconds()), Boolean.valueOf(isGeneratedForCustomer()), getFourSquareArt(), getVisibility(), getPlaylistId(), getDescription(), Boolean.valueOf(isCanReorderPlaylist()), getCreatedDate(), getTitle(), Boolean.valueOf(isIsShareableType()), getLastModifiedDate(), getGoLiveDate());
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

    public boolean isGeneratedForCustomer() {
        return this.generatedForCustomer;
    }

    public boolean isIsShareableType() {
        return this.isShareableType;
    }

    public void setBannerArt(Image image) {
        this.bannerArt = image;
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

    public void setCreatedDate(Date date) {
        this.createdDate = date;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDurationSeconds(int i) {
        this.durationSeconds = i;
    }

    public void setFourSquareArt(Image image) {
        this.fourSquareArt = image;
    }

    public void setGeneratedForCustomer(boolean z) {
        this.generatedForCustomer = z;
    }

    public void setGoLiveDate(Date date) {
        this.goLiveDate = date;
    }

    public void setIsShareableType(boolean z) {
        this.isShareableType = z;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public void setPlaylistId(String str) {
        this.playlistId = str;
    }

    public void setSharedPlaylistId(String str) {
        this.sharedPlaylistId = str;
    }

    public void setSharingUrl(String str) {
        this.sharingUrl = str;
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
