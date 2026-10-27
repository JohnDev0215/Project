package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CatalogTrackMetadata implements Comparable<CatalogTrackMetadata> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.CatalogTrackMetadata");
    private Image albumArt;
    private List<Artist> albumArtists;
    private String albumAsin;
    private Integer albumDiscNum;
    private String albumName;
    private Integer albumTrackNum;
    private List<Artist> artists;
    private String asin;
    private List<String> contentEncoding;
    private Integer durationSeconds;
    private Integer fileSizeKilobytes;
    private boolean free;
    private boolean freeOnDemandPlayable;
    private List<String> internalTags;
    private boolean musicSubscription;
    private boolean newSinceLastMajorVersion;
    private String objectId;
    private Date originalReleaseDate;
    private ParentalControls parentalControls;
    private String primaryGenre;
    private boolean prime;
    private boolean purchased;
    private String title;
    private Integer trackNum;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CatalogTrackMetadata catalogTrackMetadata) {
        if (catalogTrackMetadata == null) {
            return -1;
        }
        if (catalogTrackMetadata == this) {
            return 0;
        }
        if (!isFreeOnDemandPlayable() && catalogTrackMetadata.isFreeOnDemandPlayable()) {
            return -1;
        }
        if (isFreeOnDemandPlayable() && !catalogTrackMetadata.isFreeOnDemandPlayable()) {
            return 1;
        }
        String albumAsin = getAlbumAsin();
        String albumAsin2 = catalogTrackMetadata.getAlbumAsin();
        if (albumAsin != albumAsin2) {
            if (albumAsin == null) {
                return -1;
            }
            if (albumAsin2 == null) {
                return 1;
            }
            int iCompareTo = albumAsin.compareTo(albumAsin2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Image albumArt = getAlbumArt();
        Image albumArt2 = catalogTrackMetadata.getAlbumArt();
        if (albumArt != albumArt2) {
            if (albumArt == null) {
                return -1;
            }
            if (albumArt2 == null) {
                return 1;
            }
            int iCompareTo2 = albumArt.compareTo(albumArt2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        if (!isPrime() && catalogTrackMetadata.isPrime()) {
            return -1;
        }
        if (isPrime() && !catalogTrackMetadata.isPrime()) {
            return 1;
        }
        if (!isMusicSubscription() && catalogTrackMetadata.isMusicSubscription()) {
            return -1;
        }
        if (isMusicSubscription() && !catalogTrackMetadata.isMusicSubscription()) {
            return 1;
        }
        String albumName = getAlbumName();
        String albumName2 = catalogTrackMetadata.getAlbumName();
        if (albumName != albumName2) {
            if (albumName == null) {
                return -1;
            }
            if (albumName2 == null) {
                return 1;
            }
            int iCompareTo3 = albumName.compareTo(albumName2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String objectId = getObjectId();
        String objectId2 = catalogTrackMetadata.getObjectId();
        if (objectId != objectId2) {
            if (objectId == null) {
                return -1;
            }
            if (objectId2 == null) {
                return 1;
            }
            int iCompareTo4 = objectId.compareTo(objectId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<Artist> artists = getArtists();
        List<Artist> artists2 = catalogTrackMetadata.getArtists();
        if (artists != artists2) {
            if (artists == null) {
                return -1;
            }
            if (artists2 == null) {
                return 1;
            }
            if (artists instanceof Comparable) {
                int iCompareTo5 = ((Comparable) artists).compareTo(artists2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!artists.equals(artists2)) {
                int iHashCode = artists.hashCode();
                int iHashCode2 = artists2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = catalogTrackMetadata.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo6 = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!contentEncoding.equals(contentEncoding2)) {
                int iHashCode3 = contentEncoding.hashCode();
                int iHashCode4 = contentEncoding2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String title = getTitle();
        String title2 = catalogTrackMetadata.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo7 = title.compareTo(title2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Date originalReleaseDate = getOriginalReleaseDate();
        Date originalReleaseDate2 = catalogTrackMetadata.getOriginalReleaseDate();
        if (originalReleaseDate != originalReleaseDate2) {
            if (originalReleaseDate == null) {
                return -1;
            }
            if (originalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo8 = originalReleaseDate.compareTo(originalReleaseDate2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String asin = getAsin();
        String asin2 = catalogTrackMetadata.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo9 = asin.compareTo(asin2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Integer albumDiscNum = getAlbumDiscNum();
        Integer albumDiscNum2 = catalogTrackMetadata.getAlbumDiscNum();
        if (albumDiscNum != albumDiscNum2) {
            if (albumDiscNum == null) {
                return -1;
            }
            if (albumDiscNum2 == null) {
                return 1;
            }
            int iCompareTo10 = albumDiscNum.compareTo(albumDiscNum2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Integer albumTrackNum = getAlbumTrackNum();
        Integer albumTrackNum2 = catalogTrackMetadata.getAlbumTrackNum();
        if (albumTrackNum != albumTrackNum2) {
            if (albumTrackNum == null) {
                return -1;
            }
            if (albumTrackNum2 == null) {
                return 1;
            }
            int iCompareTo11 = albumTrackNum.compareTo(albumTrackNum2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Integer durationSeconds = getDurationSeconds();
        Integer durationSeconds2 = catalogTrackMetadata.getDurationSeconds();
        if (durationSeconds != durationSeconds2) {
            if (durationSeconds == null) {
                return -1;
            }
            if (durationSeconds2 == null) {
                return 1;
            }
            int iCompareTo12 = durationSeconds.compareTo(durationSeconds2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        List<String> internalTags = getInternalTags();
        List<String> internalTags2 = catalogTrackMetadata.getInternalTags();
        if (internalTags != internalTags2) {
            if (internalTags == null) {
                return -1;
            }
            if (internalTags2 == null) {
                return 1;
            }
            if (internalTags instanceof Comparable) {
                int iCompareTo13 = ((Comparable) internalTags).compareTo(internalTags2);
                if (iCompareTo13 != 0) {
                    return iCompareTo13;
                }
            } else if (!internalTags.equals(internalTags2)) {
                int iHashCode5 = internalTags.hashCode();
                int iHashCode6 = internalTags2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        List<Artist> albumArtists = getAlbumArtists();
        List<Artist> albumArtists2 = catalogTrackMetadata.getAlbumArtists();
        if (albumArtists != albumArtists2) {
            if (albumArtists == null) {
                return -1;
            }
            if (albumArtists2 == null) {
                return 1;
            }
            if (albumArtists instanceof Comparable) {
                int iCompareTo14 = ((Comparable) albumArtists).compareTo(albumArtists2);
                if (iCompareTo14 != 0) {
                    return iCompareTo14;
                }
            } else if (!albumArtists.equals(albumArtists2)) {
                int iHashCode7 = albumArtists.hashCode();
                int iHashCode8 = albumArtists2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        String primaryGenre = getPrimaryGenre();
        String primaryGenre2 = catalogTrackMetadata.getPrimaryGenre();
        if (primaryGenre != primaryGenre2) {
            if (primaryGenre == null) {
                return -1;
            }
            if (primaryGenre2 == null) {
                return 1;
            }
            int iCompareTo15 = primaryGenre.compareTo(primaryGenre2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        if (!isFree() && catalogTrackMetadata.isFree()) {
            return -1;
        }
        if (isFree() && !catalogTrackMetadata.isFree()) {
            return 1;
        }
        Integer trackNum = getTrackNum();
        Integer trackNum2 = catalogTrackMetadata.getTrackNum();
        if (trackNum != trackNum2) {
            if (trackNum == null) {
                return -1;
            }
            if (trackNum2 == null) {
                return 1;
            }
            int iCompareTo16 = trackNum.compareTo(trackNum2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        Integer fileSizeKilobytes = getFileSizeKilobytes();
        Integer fileSizeKilobytes2 = catalogTrackMetadata.getFileSizeKilobytes();
        if (fileSizeKilobytes != fileSizeKilobytes2) {
            if (fileSizeKilobytes == null) {
                return -1;
            }
            if (fileSizeKilobytes2 == null) {
                return 1;
            }
            int iCompareTo17 = fileSizeKilobytes.compareTo(fileSizeKilobytes2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = catalogTrackMetadata.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo18 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        if (!isPurchased() && catalogTrackMetadata.isPurchased()) {
            return -1;
        }
        if (isPurchased() && !catalogTrackMetadata.isPurchased()) {
            return 1;
        }
        if (isNewSinceLastMajorVersion() || !catalogTrackMetadata.isNewSinceLastMajorVersion()) {
            return (!isNewSinceLastMajorVersion() || catalogTrackMetadata.isNewSinceLastMajorVersion()) ? 0 : 1;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogTrackMetadata)) {
            return false;
        }
        CatalogTrackMetadata catalogTrackMetadata = (CatalogTrackMetadata) obj;
        return internalEqualityCheck(Boolean.valueOf(isFreeOnDemandPlayable()), Boolean.valueOf(catalogTrackMetadata.isFreeOnDemandPlayable())) && internalEqualityCheck(getAlbumAsin(), catalogTrackMetadata.getAlbumAsin()) && internalEqualityCheck(getAlbumArt(), catalogTrackMetadata.getAlbumArt()) && internalEqualityCheck(Boolean.valueOf(isPrime()), Boolean.valueOf(catalogTrackMetadata.isPrime())) && internalEqualityCheck(Boolean.valueOf(isMusicSubscription()), Boolean.valueOf(catalogTrackMetadata.isMusicSubscription())) && internalEqualityCheck(getAlbumName(), catalogTrackMetadata.getAlbumName()) && internalEqualityCheck(getObjectId(), catalogTrackMetadata.getObjectId()) && internalEqualityCheck(getArtists(), catalogTrackMetadata.getArtists()) && internalEqualityCheck(getContentEncoding(), catalogTrackMetadata.getContentEncoding()) && internalEqualityCheck(getTitle(), catalogTrackMetadata.getTitle()) && internalEqualityCheck(getOriginalReleaseDate(), catalogTrackMetadata.getOriginalReleaseDate()) && internalEqualityCheck(getAsin(), catalogTrackMetadata.getAsin()) && internalEqualityCheck(getAlbumDiscNum(), catalogTrackMetadata.getAlbumDiscNum()) && internalEqualityCheck(getAlbumTrackNum(), catalogTrackMetadata.getAlbumTrackNum()) && internalEqualityCheck(getDurationSeconds(), catalogTrackMetadata.getDurationSeconds()) && internalEqualityCheck(getInternalTags(), catalogTrackMetadata.getInternalTags()) && internalEqualityCheck(getAlbumArtists(), catalogTrackMetadata.getAlbumArtists()) && internalEqualityCheck(getPrimaryGenre(), catalogTrackMetadata.getPrimaryGenre()) && internalEqualityCheck(Boolean.valueOf(isFree()), Boolean.valueOf(catalogTrackMetadata.isFree())) && internalEqualityCheck(getTrackNum(), catalogTrackMetadata.getTrackNum()) && internalEqualityCheck(getFileSizeKilobytes(), catalogTrackMetadata.getFileSizeKilobytes()) && internalEqualityCheck(getParentalControls(), catalogTrackMetadata.getParentalControls()) && internalEqualityCheck(Boolean.valueOf(isPurchased()), Boolean.valueOf(catalogTrackMetadata.isPurchased())) && internalEqualityCheck(Boolean.valueOf(isNewSinceLastMajorVersion()), Boolean.valueOf(catalogTrackMetadata.isNewSinceLastMajorVersion()));
    }

    public Image getAlbumArt() {
        return this.albumArt;
    }

    public List<Artist> getAlbumArtists() {
        return this.albumArtists;
    }

    public String getAlbumAsin() {
        return this.albumAsin;
    }

    public Integer getAlbumDiscNum() {
        return this.albumDiscNum;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public Integer getAlbumTrackNum() {
        return this.albumTrackNum;
    }

    public List<Artist> getArtists() {
        return this.artists;
    }

    public String getAsin() {
        return this.asin;
    }

    public List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public Integer getFileSizeKilobytes() {
        return this.fileSizeKilobytes;
    }

    public List<String> getInternalTags() {
        return this.internalTags;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public Date getOriginalReleaseDate() {
        return this.originalReleaseDate;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getPrimaryGenre() {
        return this.primaryGenre;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getTrackNum() {
        return this.trackNum;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Boolean.valueOf(isFreeOnDemandPlayable()), getAlbumAsin(), getAlbumArt(), Boolean.valueOf(isPrime()), Boolean.valueOf(isMusicSubscription()), getAlbumName(), getObjectId(), getArtists(), getContentEncoding(), getTitle(), getOriginalReleaseDate(), getAsin(), getAlbumDiscNum(), getAlbumTrackNum(), getDurationSeconds(), getInternalTags(), getAlbumArtists(), getPrimaryGenre(), Boolean.valueOf(isFree()), getTrackNum(), getFileSizeKilobytes(), getParentalControls(), Boolean.valueOf(isPurchased()), Boolean.valueOf(isNewSinceLastMajorVersion()));
    }

    public boolean isFree() {
        return this.free;
    }

    public boolean isFreeOnDemandPlayable() {
        return this.freeOnDemandPlayable;
    }

    public boolean isMusicSubscription() {
        return this.musicSubscription;
    }

    public boolean isNewSinceLastMajorVersion() {
        return this.newSinceLastMajorVersion;
    }

    public boolean isPrime() {
        return this.prime;
    }

    public boolean isPurchased() {
        return this.purchased;
    }

    public void setAlbumArt(Image image) {
        this.albumArt = image;
    }

    public void setAlbumArtists(List<Artist> list) {
        this.albumArtists = list;
    }

    public void setAlbumAsin(String str) {
        this.albumAsin = str;
    }

    public void setAlbumDiscNum(Integer num) {
        this.albumDiscNum = num;
    }

    public void setAlbumName(String str) {
        this.albumName = str;
    }

    public void setAlbumTrackNum(Integer num) {
        this.albumTrackNum = num;
    }

    public void setArtists(List<Artist> list) {
        this.artists = list;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setContentEncoding(List<String> list) {
        this.contentEncoding = list;
    }

    public void setDurationSeconds(Integer num) {
        this.durationSeconds = num;
    }

    public void setFileSizeKilobytes(Integer num) {
        this.fileSizeKilobytes = num;
    }

    public void setFree(boolean z) {
        this.free = z;
    }

    public void setFreeOnDemandPlayable(boolean z) {
        this.freeOnDemandPlayable = z;
    }

    public void setInternalTags(List<String> list) {
        this.internalTags = list;
    }

    public void setMusicSubscription(boolean z) {
        this.musicSubscription = z;
    }

    public void setNewSinceLastMajorVersion(boolean z) {
        this.newSinceLastMajorVersion = z;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public void setOriginalReleaseDate(Date date) {
        this.originalReleaseDate = date;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setPrimaryGenre(String str) {
        this.primaryGenre = str;
    }

    public void setPrime(boolean z) {
        this.prime = z;
    }

    public void setPurchased(boolean z) {
        this.purchased = z;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTrackNum(Integer num) {
        this.trackNum = num;
    }
}
