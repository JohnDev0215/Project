package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryTrack extends Track {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.LibraryTrack");
    private String albumContributors;
    private String assetListKey;
    private String assetType;
    private Integer bitrate;
    private String contributors;
    private Date creationDate;
    private String errorCode;
    private String fileExtension;
    private String fileName;
    private Long fileSize;
    private Boolean instantImport;
    private Boolean isKatana;
    private Date lastUpdatedDate;
    private String localFilePath;
    private String matchType;
    private String md5;
    private String objectId;
    private String orderId;
    private String physicalOrderId;
    private Date purchaseDate;
    private Boolean purchased;
    private Float rating;
    private Date rogueBackfillDate;
    private String status;
    private Boolean uploaded;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Track, com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof LibraryTrack)) {
            return 1;
        }
        LibraryTrack libraryTrack = (LibraryTrack) document;
        String localFilePath = getLocalFilePath();
        String localFilePath2 = libraryTrack.getLocalFilePath();
        if (localFilePath != localFilePath2) {
            if (localFilePath == null) {
                return -1;
            }
            if (localFilePath2 == null) {
                return 1;
            }
            int iCompareTo = localFilePath.compareTo(localFilePath2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsInstantImport = isInstantImport();
        Boolean boolIsInstantImport2 = libraryTrack.isInstantImport();
        if (boolIsInstantImport != boolIsInstantImport2) {
            if (boolIsInstantImport == null) {
                return -1;
            }
            if (boolIsInstantImport2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsInstantImport.compareTo(boolIsInstantImport2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String physicalOrderId = getPhysicalOrderId();
        String physicalOrderId2 = libraryTrack.getPhysicalOrderId();
        if (physicalOrderId != physicalOrderId2) {
            if (physicalOrderId == null) {
                return -1;
            }
            if (physicalOrderId2 == null) {
                return 1;
            }
            int iCompareTo3 = physicalOrderId.compareTo(physicalOrderId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String errorCode = getErrorCode();
        String errorCode2 = libraryTrack.getErrorCode();
        if (errorCode != errorCode2) {
            if (errorCode == null) {
                return -1;
            }
            if (errorCode2 == null) {
                return 1;
            }
            int iCompareTo4 = errorCode.compareTo(errorCode2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsIsKatana = isIsKatana();
        Boolean boolIsIsKatana2 = libraryTrack.isIsKatana();
        if (boolIsIsKatana != boolIsIsKatana2) {
            if (boolIsIsKatana == null) {
                return -1;
            }
            if (boolIsIsKatana2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsIsKatana.compareTo(boolIsIsKatana2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Date creationDate = getCreationDate();
        Date creationDate2 = libraryTrack.getCreationDate();
        if (creationDate != creationDate2) {
            if (creationDate == null) {
                return -1;
            }
            if (creationDate2 == null) {
                return 1;
            }
            int iCompareTo6 = creationDate.compareTo(creationDate2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String objectId = getObjectId();
        String objectId2 = libraryTrack.getObjectId();
        if (objectId != objectId2) {
            if (objectId == null) {
                return -1;
            }
            if (objectId2 == null) {
                return 1;
            }
            int iCompareTo7 = objectId.compareTo(objectId2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Boolean boolIsPurchased = isPurchased();
        Boolean boolIsPurchased2 = libraryTrack.isPurchased();
        if (boolIsPurchased != boolIsPurchased2) {
            if (boolIsPurchased == null) {
                return -1;
            }
            if (boolIsPurchased2 == null) {
                return 1;
            }
            int iCompareTo8 = boolIsPurchased.compareTo(boolIsPurchased2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String fileExtension = getFileExtension();
        String fileExtension2 = libraryTrack.getFileExtension();
        if (fileExtension != fileExtension2) {
            if (fileExtension == null) {
                return -1;
            }
            if (fileExtension2 == null) {
                return 1;
            }
            int iCompareTo9 = fileExtension.compareTo(fileExtension2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Integer bitrate = getBitrate();
        Integer bitrate2 = libraryTrack.getBitrate();
        if (bitrate != bitrate2) {
            if (bitrate == null) {
                return -1;
            }
            if (bitrate2 == null) {
                return 1;
            }
            int iCompareTo10 = bitrate.compareTo(bitrate2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Float rating = getRating();
        Float rating2 = libraryTrack.getRating();
        if (rating != rating2) {
            if (rating == null) {
                return -1;
            }
            if (rating2 == null) {
                return 1;
            }
            int iCompareTo11 = rating.compareTo(rating2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Long fileSize = getFileSize();
        Long fileSize2 = libraryTrack.getFileSize();
        if (fileSize != fileSize2) {
            if (fileSize == null) {
                return -1;
            }
            if (fileSize2 == null) {
                return 1;
            }
            int iCompareTo12 = fileSize.compareTo(fileSize2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Date purchaseDate = getPurchaseDate();
        Date purchaseDate2 = libraryTrack.getPurchaseDate();
        if (purchaseDate != purchaseDate2) {
            if (purchaseDate == null) {
                return -1;
            }
            if (purchaseDate2 == null) {
                return 1;
            }
            int iCompareTo13 = purchaseDate.compareTo(purchaseDate2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String fileName = getFileName();
        String fileName2 = libraryTrack.getFileName();
        if (fileName != fileName2) {
            if (fileName == null) {
                return -1;
            }
            if (fileName2 == null) {
                return 1;
            }
            int iCompareTo14 = fileName.compareTo(fileName2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String assetListKey = getAssetListKey();
        String assetListKey2 = libraryTrack.getAssetListKey();
        if (assetListKey != assetListKey2) {
            if (assetListKey == null) {
                return -1;
            }
            if (assetListKey2 == null) {
                return 1;
            }
            int iCompareTo15 = assetListKey.compareTo(assetListKey2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Boolean boolIsUploaded = isUploaded();
        Boolean boolIsUploaded2 = libraryTrack.isUploaded();
        if (boolIsUploaded != boolIsUploaded2) {
            if (boolIsUploaded == null) {
                return -1;
            }
            if (boolIsUploaded2 == null) {
                return 1;
            }
            int iCompareTo16 = boolIsUploaded.compareTo(boolIsUploaded2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        Date rogueBackfillDate = getRogueBackfillDate();
        Date rogueBackfillDate2 = libraryTrack.getRogueBackfillDate();
        if (rogueBackfillDate != rogueBackfillDate2) {
            if (rogueBackfillDate == null) {
                return -1;
            }
            if (rogueBackfillDate2 == null) {
                return 1;
            }
            int iCompareTo17 = rogueBackfillDate.compareTo(rogueBackfillDate2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String albumContributors = getAlbumContributors();
        String albumContributors2 = libraryTrack.getAlbumContributors();
        if (albumContributors != albumContributors2) {
            if (albumContributors == null) {
                return -1;
            }
            if (albumContributors2 == null) {
                return 1;
            }
            int iCompareTo18 = albumContributors.compareTo(albumContributors2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        String assetType = getAssetType();
        String assetType2 = libraryTrack.getAssetType();
        if (assetType != assetType2) {
            if (assetType == null) {
                return -1;
            }
            if (assetType2 == null) {
                return 1;
            }
            int iCompareTo19 = assetType.compareTo(assetType2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        String status = getStatus();
        String status2 = libraryTrack.getStatus();
        if (status != status2) {
            if (status == null) {
                return -1;
            }
            if (status2 == null) {
                return 1;
            }
            int iCompareTo20 = status.compareTo(status2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        String matchType = getMatchType();
        String matchType2 = libraryTrack.getMatchType();
        if (matchType != matchType2) {
            if (matchType == null) {
                return -1;
            }
            if (matchType2 == null) {
                return 1;
            }
            int iCompareTo21 = matchType.compareTo(matchType2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        Date lastUpdatedDate = getLastUpdatedDate();
        Date lastUpdatedDate2 = libraryTrack.getLastUpdatedDate();
        if (lastUpdatedDate != lastUpdatedDate2) {
            if (lastUpdatedDate == null) {
                return -1;
            }
            if (lastUpdatedDate2 == null) {
                return 1;
            }
            int iCompareTo22 = lastUpdatedDate.compareTo(lastUpdatedDate2);
            if (iCompareTo22 != 0) {
                return iCompareTo22;
            }
        }
        String orderId = getOrderId();
        String orderId2 = libraryTrack.getOrderId();
        if (orderId != orderId2) {
            if (orderId == null) {
                return -1;
            }
            if (orderId2 == null) {
                return 1;
            }
            int iCompareTo23 = orderId.compareTo(orderId2);
            if (iCompareTo23 != 0) {
                return iCompareTo23;
            }
        }
        String md5 = getMd5();
        String md52 = libraryTrack.getMd5();
        if (md5 != md52) {
            if (md5 == null) {
                return -1;
            }
            if (md52 == null) {
                return 1;
            }
            int iCompareTo24 = md5.compareTo(md52);
            if (iCompareTo24 != 0) {
                return iCompareTo24;
            }
        }
        String contributors = getContributors();
        String contributors2 = libraryTrack.getContributors();
        if (contributors != contributors2) {
            if (contributors == null) {
                return -1;
            }
            if (contributors2 == null) {
                return 1;
            }
            int iCompareTo25 = contributors.compareTo(contributors2);
            if (iCompareTo25 != 0) {
                return iCompareTo25;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Track, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof LibraryTrack)) {
            return false;
        }
        LibraryTrack libraryTrack = (LibraryTrack) obj;
        return super.equals(obj) && internalEqualityCheck(getLocalFilePath(), libraryTrack.getLocalFilePath()) && internalEqualityCheck(isInstantImport(), libraryTrack.isInstantImport()) && internalEqualityCheck(getPhysicalOrderId(), libraryTrack.getPhysicalOrderId()) && internalEqualityCheck(getErrorCode(), libraryTrack.getErrorCode()) && internalEqualityCheck(isIsKatana(), libraryTrack.isIsKatana()) && internalEqualityCheck(getCreationDate(), libraryTrack.getCreationDate()) && internalEqualityCheck(getObjectId(), libraryTrack.getObjectId()) && internalEqualityCheck(isPurchased(), libraryTrack.isPurchased()) && internalEqualityCheck(getFileExtension(), libraryTrack.getFileExtension()) && internalEqualityCheck(getBitrate(), libraryTrack.getBitrate()) && internalEqualityCheck(getRating(), libraryTrack.getRating()) && internalEqualityCheck(getFileSize(), libraryTrack.getFileSize()) && internalEqualityCheck(getPurchaseDate(), libraryTrack.getPurchaseDate()) && internalEqualityCheck(getFileName(), libraryTrack.getFileName()) && internalEqualityCheck(getAssetListKey(), libraryTrack.getAssetListKey()) && internalEqualityCheck(isUploaded(), libraryTrack.isUploaded()) && internalEqualityCheck(getRogueBackfillDate(), libraryTrack.getRogueBackfillDate()) && internalEqualityCheck(getAlbumContributors(), libraryTrack.getAlbumContributors()) && internalEqualityCheck(getAssetType(), libraryTrack.getAssetType()) && internalEqualityCheck(getStatus(), libraryTrack.getStatus()) && internalEqualityCheck(getMatchType(), libraryTrack.getMatchType()) && internalEqualityCheck(getLastUpdatedDate(), libraryTrack.getLastUpdatedDate()) && internalEqualityCheck(getOrderId(), libraryTrack.getOrderId()) && internalEqualityCheck(getMd5(), libraryTrack.getMd5()) && internalEqualityCheck(getContributors(), libraryTrack.getContributors());
    }

    public String getAlbumContributors() {
        return this.albumContributors;
    }

    public String getAssetListKey() {
        return this.assetListKey;
    }

    public String getAssetType() {
        return this.assetType;
    }

    public Integer getBitrate() {
        return this.bitrate;
    }

    public String getContributors() {
        return this.contributors;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public String getFileExtension() {
        return this.fileExtension;
    }

    public String getFileName() {
        return this.fileName;
    }

    public Long getFileSize() {
        return this.fileSize;
    }

    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public String getLocalFilePath() {
        return this.localFilePath;
    }

    public String getMatchType() {
        return this.matchType;
    }

    public String getMd5() {
        return this.md5;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public String getPhysicalOrderId() {
        return this.physicalOrderId;
    }

    public Date getPurchaseDate() {
        return this.purchaseDate;
    }

    public Float getRating() {
        return this.rating;
    }

    public Date getRogueBackfillDate() {
        return this.rogueBackfillDate;
    }

    public String getStatus() {
        return this.status;
    }

    @Override // com.amazon.music.platform.model.Track, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getLocalFilePath(), isInstantImport(), getPhysicalOrderId(), getErrorCode(), isIsKatana(), getCreationDate(), getObjectId(), isPurchased(), getFileExtension(), getBitrate(), getRating(), getFileSize(), getPurchaseDate(), getFileName(), getAssetListKey(), isUploaded(), getRogueBackfillDate(), getAlbumContributors(), getAssetType(), getStatus(), getMatchType(), getLastUpdatedDate(), getOrderId(), getMd5(), getContributors());
    }

    public Boolean isInstantImport() {
        return this.instantImport;
    }

    public Boolean isIsKatana() {
        return this.isKatana;
    }

    public Boolean isPurchased() {
        return this.purchased;
    }

    public Boolean isUploaded() {
        return this.uploaded;
    }

    public void setAlbumContributors(String str) {
        this.albumContributors = str;
    }

    public void setAssetListKey(String str) {
        this.assetListKey = str;
    }

    public void setAssetType(String str) {
        this.assetType = str;
    }

    public void setBitrate(Integer num) {
        this.bitrate = num;
    }

    public void setContributors(String str) {
        this.contributors = str;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public void setFileExtension(String str) {
        this.fileExtension = str;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setFileSize(Long l) {
        this.fileSize = l;
    }

    public void setInstantImport(Boolean bool) {
        this.instantImport = bool;
    }

    public void setIsKatana(Boolean bool) {
        this.isKatana = bool;
    }

    public void setLastUpdatedDate(Date date) {
        this.lastUpdatedDate = date;
    }

    public void setLocalFilePath(String str) {
        this.localFilePath = str;
    }

    public void setMatchType(String str) {
        this.matchType = str;
    }

    public void setMd5(String str) {
        this.md5 = str;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public void setPhysicalOrderId(String str) {
        this.physicalOrderId = str;
    }

    public void setPurchaseDate(Date date) {
        this.purchaseDate = date;
    }

    public void setPurchased(Boolean bool) {
        this.purchased = bool;
    }

    public void setRating(Float f) {
        this.rating = f;
    }

    public void setRogueBackfillDate(Date date) {
        this.rogueBackfillDate = date;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setUploaded(Boolean bool) {
        this.uploaded = bool;
    }
}
