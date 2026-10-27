package com.amazon.nimblymusicservice;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class RecentPlayedTrackActivity implements Comparable<RecentPlayedTrackActivity> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.RecentPlayedTrackActivity");
    private String albumAsin;
    private String albumName;
    private String artistAsin;
    private String artistName;
    private String asin;
    private ContentInfo contentInfo;
    private String deviceId;
    private String deviceType;
    private String displayName;
    private String durationSeconds;
    private String hasLyrics;
    private String imageFull;
    private String isAtmosAvailable;
    private String isFree;
    private String isFreeOnDemandPlayable;
    private String isHdAvailable;
    private String isHiResAvailable;
    private String isInLibrary;
    private String isInstantImport;
    private String isMerlockExcluded;
    private String isMusicSubscription;
    private String isPrime;
    private String isPurchased;
    private String isRA360Available;
    private String isSonicRush;
    private String isSonicRushOnDemandPlayable;
    private String isThreeDAvailable;
    private String isUploaded;
    private String libraryStatus;
    private String marketplaceId;
    private String musicTerritory;
    private String objectId;
    private ParentalControls parentalControls;
    private Date timestamp;
    private String uniqueIdentifier;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RecentPlayedTrackActivity recentPlayedTrackActivity) {
        if (recentPlayedTrackActivity == null) {
            return -1;
        }
        if (recentPlayedTrackActivity == this) {
            return 0;
        }
        String displayName = getDisplayName();
        String displayName2 = recentPlayedTrackActivity.getDisplayName();
        if (displayName != displayName2) {
            if (displayName == null) {
                return -1;
            }
            if (displayName2 == null) {
                return 1;
            }
            int iCompareTo = displayName.compareTo(displayName2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String isUploaded = getIsUploaded();
        String isUploaded2 = recentPlayedTrackActivity.getIsUploaded();
        if (isUploaded != isUploaded2) {
            if (isUploaded == null) {
                return -1;
            }
            if (isUploaded2 == null) {
                return 1;
            }
            int iCompareTo2 = isUploaded.compareTo(isUploaded2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = recentPlayedTrackActivity.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo3 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String isInLibrary = getIsInLibrary();
        String isInLibrary2 = recentPlayedTrackActivity.getIsInLibrary();
        if (isInLibrary != isInLibrary2) {
            if (isInLibrary == null) {
                return -1;
            }
            if (isInLibrary2 == null) {
                return 1;
            }
            int iCompareTo4 = isInLibrary.compareTo(isInLibrary2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String isAtmosAvailable = getIsAtmosAvailable();
        String isAtmosAvailable2 = recentPlayedTrackActivity.getIsAtmosAvailable();
        if (isAtmosAvailable != isAtmosAvailable2) {
            if (isAtmosAvailable == null) {
                return -1;
            }
            if (isAtmosAvailable2 == null) {
                return 1;
            }
            int iCompareTo5 = isAtmosAvailable.compareTo(isAtmosAvailable2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String albumAsin = getAlbumAsin();
        String albumAsin2 = recentPlayedTrackActivity.getAlbumAsin();
        if (albumAsin != albumAsin2) {
            if (albumAsin == null) {
                return -1;
            }
            if (albumAsin2 == null) {
                return 1;
            }
            int iCompareTo6 = albumAsin.compareTo(albumAsin2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String isRA360Available = getIsRA360Available();
        String isRA360Available2 = recentPlayedTrackActivity.getIsRA360Available();
        if (isRA360Available != isRA360Available2) {
            if (isRA360Available == null) {
                return -1;
            }
            if (isRA360Available2 == null) {
                return 1;
            }
            int iCompareTo7 = isRA360Available.compareTo(isRA360Available2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String isSonicRushOnDemandPlayable = getIsSonicRushOnDemandPlayable();
        String isSonicRushOnDemandPlayable2 = recentPlayedTrackActivity.getIsSonicRushOnDemandPlayable();
        if (isSonicRushOnDemandPlayable != isSonicRushOnDemandPlayable2) {
            if (isSonicRushOnDemandPlayable == null) {
                return -1;
            }
            if (isSonicRushOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo8 = isSonicRushOnDemandPlayable.compareTo(isSonicRushOnDemandPlayable2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String hasLyrics = getHasLyrics();
        String hasLyrics2 = recentPlayedTrackActivity.getHasLyrics();
        if (hasLyrics != hasLyrics2) {
            if (hasLyrics == null) {
                return -1;
            }
            if (hasLyrics2 == null) {
                return 1;
            }
            int iCompareTo9 = hasLyrics.compareTo(hasLyrics2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Date timestamp = getTimestamp();
        Date timestamp2 = recentPlayedTrackActivity.getTimestamp();
        if (timestamp != timestamp2) {
            if (timestamp == null) {
                return -1;
            }
            if (timestamp2 == null) {
                return 1;
            }
            int iCompareTo10 = timestamp.compareTo(timestamp2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String uniqueIdentifier = getUniqueIdentifier();
        String uniqueIdentifier2 = recentPlayedTrackActivity.getUniqueIdentifier();
        if (uniqueIdentifier != uniqueIdentifier2) {
            if (uniqueIdentifier == null) {
                return -1;
            }
            if (uniqueIdentifier2 == null) {
                return 1;
            }
            int iCompareTo11 = uniqueIdentifier.compareTo(uniqueIdentifier2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        ContentInfo contentInfo = getContentInfo();
        ContentInfo contentInfo2 = recentPlayedTrackActivity.getContentInfo();
        if (contentInfo != contentInfo2) {
            if (contentInfo == null) {
                return -1;
            }
            if (contentInfo2 == null) {
                return 1;
            }
            int iCompareTo12 = contentInfo.compareTo(contentInfo2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String isFree = getIsFree();
        String isFree2 = recentPlayedTrackActivity.getIsFree();
        if (isFree != isFree2) {
            if (isFree == null) {
                return -1;
            }
            if (isFree2 == null) {
                return 1;
            }
            int iCompareTo13 = isFree.compareTo(isFree2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String imageFull = getImageFull();
        String imageFull2 = recentPlayedTrackActivity.getImageFull();
        if (imageFull != imageFull2) {
            if (imageFull == null) {
                return -1;
            }
            if (imageFull2 == null) {
                return 1;
            }
            int iCompareTo14 = imageFull.compareTo(imageFull2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String isSonicRush = getIsSonicRush();
        String isSonicRush2 = recentPlayedTrackActivity.getIsSonicRush();
        if (isSonicRush != isSonicRush2) {
            if (isSonicRush == null) {
                return -1;
            }
            if (isSonicRush2 == null) {
                return 1;
            }
            int iCompareTo15 = isSonicRush.compareTo(isSonicRush2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String isHiResAvailable = getIsHiResAvailable();
        String isHiResAvailable2 = recentPlayedTrackActivity.getIsHiResAvailable();
        if (isHiResAvailable != isHiResAvailable2) {
            if (isHiResAvailable == null) {
                return -1;
            }
            if (isHiResAvailable2 == null) {
                return 1;
            }
            int iCompareTo16 = isHiResAvailable.compareTo(isHiResAvailable2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String artistName = getArtistName();
        String artistName2 = recentPlayedTrackActivity.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo17 = artistName.compareTo(artistName2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String isInstantImport = getIsInstantImport();
        String isInstantImport2 = recentPlayedTrackActivity.getIsInstantImport();
        if (isInstantImport != isInstantImport2) {
            if (isInstantImport == null) {
                return -1;
            }
            if (isInstantImport2 == null) {
                return 1;
            }
            int iCompareTo18 = isInstantImport.compareTo(isInstantImport2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        String isMerlockExcluded = getIsMerlockExcluded();
        String isMerlockExcluded2 = recentPlayedTrackActivity.getIsMerlockExcluded();
        if (isMerlockExcluded != isMerlockExcluded2) {
            if (isMerlockExcluded == null) {
                return -1;
            }
            if (isMerlockExcluded2 == null) {
                return 1;
            }
            int iCompareTo19 = isMerlockExcluded.compareTo(isMerlockExcluded2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        String isThreeDAvailable = getIsThreeDAvailable();
        String isThreeDAvailable2 = recentPlayedTrackActivity.getIsThreeDAvailable();
        if (isThreeDAvailable != isThreeDAvailable2) {
            if (isThreeDAvailable == null) {
                return -1;
            }
            if (isThreeDAvailable2 == null) {
                return 1;
            }
            int iCompareTo20 = isThreeDAvailable.compareTo(isThreeDAvailable2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        String isHdAvailable = getIsHdAvailable();
        String isHdAvailable2 = recentPlayedTrackActivity.getIsHdAvailable();
        if (isHdAvailable != isHdAvailable2) {
            if (isHdAvailable == null) {
                return -1;
            }
            if (isHdAvailable2 == null) {
                return 1;
            }
            int iCompareTo21 = isHdAvailable.compareTo(isHdAvailable2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        String deviceId = getDeviceId();
        String deviceId2 = recentPlayedTrackActivity.getDeviceId();
        if (deviceId != deviceId2) {
            if (deviceId == null) {
                return -1;
            }
            if (deviceId2 == null) {
                return 1;
            }
            int iCompareTo22 = deviceId.compareTo(deviceId2);
            if (iCompareTo22 != 0) {
                return iCompareTo22;
            }
        }
        String isFreeOnDemandPlayable = getIsFreeOnDemandPlayable();
        String isFreeOnDemandPlayable2 = recentPlayedTrackActivity.getIsFreeOnDemandPlayable();
        if (isFreeOnDemandPlayable != isFreeOnDemandPlayable2) {
            if (isFreeOnDemandPlayable == null) {
                return -1;
            }
            if (isFreeOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo23 = isFreeOnDemandPlayable.compareTo(isFreeOnDemandPlayable2);
            if (iCompareTo23 != 0) {
                return iCompareTo23;
            }
        }
        String asin = getAsin();
        String asin2 = recentPlayedTrackActivity.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo24 = asin.compareTo(asin2);
            if (iCompareTo24 != 0) {
                return iCompareTo24;
            }
        }
        String libraryStatus = getLibraryStatus();
        String libraryStatus2 = recentPlayedTrackActivity.getLibraryStatus();
        if (libraryStatus != libraryStatus2) {
            if (libraryStatus == null) {
                return -1;
            }
            if (libraryStatus2 == null) {
                return 1;
            }
            int iCompareTo25 = libraryStatus.compareTo(libraryStatus2);
            if (iCompareTo25 != 0) {
                return iCompareTo25;
            }
        }
        String durationSeconds = getDurationSeconds();
        String durationSeconds2 = recentPlayedTrackActivity.getDurationSeconds();
        if (durationSeconds != durationSeconds2) {
            if (durationSeconds == null) {
                return -1;
            }
            if (durationSeconds2 == null) {
                return 1;
            }
            int iCompareTo26 = durationSeconds.compareTo(durationSeconds2);
            if (iCompareTo26 != 0) {
                return iCompareTo26;
            }
        }
        String artistAsin = getArtistAsin();
        String artistAsin2 = recentPlayedTrackActivity.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo27 = artistAsin.compareTo(artistAsin2);
            if (iCompareTo27 != 0) {
                return iCompareTo27;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = recentPlayedTrackActivity.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo28 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo28 != 0) {
                return iCompareTo28;
            }
        }
        String isPrime = getIsPrime();
        String isPrime2 = recentPlayedTrackActivity.getIsPrime();
        if (isPrime != isPrime2) {
            if (isPrime == null) {
                return -1;
            }
            if (isPrime2 == null) {
                return 1;
            }
            int iCompareTo29 = isPrime.compareTo(isPrime2);
            if (iCompareTo29 != 0) {
                return iCompareTo29;
            }
        }
        String albumName = getAlbumName();
        String albumName2 = recentPlayedTrackActivity.getAlbumName();
        if (albumName != albumName2) {
            if (albumName == null) {
                return -1;
            }
            if (albumName2 == null) {
                return 1;
            }
            int iCompareTo30 = albumName.compareTo(albumName2);
            if (iCompareTo30 != 0) {
                return iCompareTo30;
            }
        }
        String objectId = getObjectId();
        String objectId2 = recentPlayedTrackActivity.getObjectId();
        if (objectId != objectId2) {
            if (objectId == null) {
                return -1;
            }
            if (objectId2 == null) {
                return 1;
            }
            int iCompareTo31 = objectId.compareTo(objectId2);
            if (iCompareTo31 != 0) {
                return iCompareTo31;
            }
        }
        String isPurchased = getIsPurchased();
        String isPurchased2 = recentPlayedTrackActivity.getIsPurchased();
        if (isPurchased != isPurchased2) {
            if (isPurchased == null) {
                return -1;
            }
            if (isPurchased2 == null) {
                return 1;
            }
            int iCompareTo32 = isPurchased.compareTo(isPurchased2);
            if (iCompareTo32 != 0) {
                return iCompareTo32;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = recentPlayedTrackActivity.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo33 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo33 != 0) {
                return iCompareTo33;
            }
        }
        String deviceType = getDeviceType();
        String deviceType2 = recentPlayedTrackActivity.getDeviceType();
        if (deviceType != deviceType2) {
            if (deviceType == null) {
                return -1;
            }
            if (deviceType2 == null) {
                return 1;
            }
            int iCompareTo34 = deviceType.compareTo(deviceType2);
            if (iCompareTo34 != 0) {
                return iCompareTo34;
            }
        }
        String isMusicSubscription = getIsMusicSubscription();
        String isMusicSubscription2 = recentPlayedTrackActivity.getIsMusicSubscription();
        if (isMusicSubscription != isMusicSubscription2) {
            if (isMusicSubscription == null) {
                return -1;
            }
            if (isMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo35 = isMusicSubscription.compareTo(isMusicSubscription2);
            if (iCompareTo35 != 0) {
                return iCompareTo35;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RecentPlayedTrackActivity)) {
            return false;
        }
        RecentPlayedTrackActivity recentPlayedTrackActivity = (RecentPlayedTrackActivity) obj;
        return internalEqualityCheck(getDisplayName(), recentPlayedTrackActivity.getDisplayName()) && internalEqualityCheck(getIsUploaded(), recentPlayedTrackActivity.getIsUploaded()) && internalEqualityCheck(getParentalControls(), recentPlayedTrackActivity.getParentalControls()) && internalEqualityCheck(getIsInLibrary(), recentPlayedTrackActivity.getIsInLibrary()) && internalEqualityCheck(getIsAtmosAvailable(), recentPlayedTrackActivity.getIsAtmosAvailable()) && internalEqualityCheck(getAlbumAsin(), recentPlayedTrackActivity.getAlbumAsin()) && internalEqualityCheck(getIsRA360Available(), recentPlayedTrackActivity.getIsRA360Available()) && internalEqualityCheck(getIsSonicRushOnDemandPlayable(), recentPlayedTrackActivity.getIsSonicRushOnDemandPlayable()) && internalEqualityCheck(getHasLyrics(), recentPlayedTrackActivity.getHasLyrics()) && internalEqualityCheck(getTimestamp(), recentPlayedTrackActivity.getTimestamp()) && internalEqualityCheck(getUniqueIdentifier(), recentPlayedTrackActivity.getUniqueIdentifier()) && internalEqualityCheck(getContentInfo(), recentPlayedTrackActivity.getContentInfo()) && internalEqualityCheck(getIsFree(), recentPlayedTrackActivity.getIsFree()) && internalEqualityCheck(getImageFull(), recentPlayedTrackActivity.getImageFull()) && internalEqualityCheck(getIsSonicRush(), recentPlayedTrackActivity.getIsSonicRush()) && internalEqualityCheck(getIsHiResAvailable(), recentPlayedTrackActivity.getIsHiResAvailable()) && internalEqualityCheck(getArtistName(), recentPlayedTrackActivity.getArtistName()) && internalEqualityCheck(getIsInstantImport(), recentPlayedTrackActivity.getIsInstantImport()) && internalEqualityCheck(getIsMerlockExcluded(), recentPlayedTrackActivity.getIsMerlockExcluded()) && internalEqualityCheck(getIsThreeDAvailable(), recentPlayedTrackActivity.getIsThreeDAvailable()) && internalEqualityCheck(getIsHdAvailable(), recentPlayedTrackActivity.getIsHdAvailable()) && internalEqualityCheck(getDeviceId(), recentPlayedTrackActivity.getDeviceId()) && internalEqualityCheck(getIsFreeOnDemandPlayable(), recentPlayedTrackActivity.getIsFreeOnDemandPlayable()) && internalEqualityCheck(getAsin(), recentPlayedTrackActivity.getAsin()) && internalEqualityCheck(getLibraryStatus(), recentPlayedTrackActivity.getLibraryStatus()) && internalEqualityCheck(getDurationSeconds(), recentPlayedTrackActivity.getDurationSeconds()) && internalEqualityCheck(getArtistAsin(), recentPlayedTrackActivity.getArtistAsin()) && internalEqualityCheck(getMusicTerritory(), recentPlayedTrackActivity.getMusicTerritory()) && internalEqualityCheck(getIsPrime(), recentPlayedTrackActivity.getIsPrime()) && internalEqualityCheck(getAlbumName(), recentPlayedTrackActivity.getAlbumName()) && internalEqualityCheck(getObjectId(), recentPlayedTrackActivity.getObjectId()) && internalEqualityCheck(getIsPurchased(), recentPlayedTrackActivity.getIsPurchased()) && internalEqualityCheck(getMarketplaceId(), recentPlayedTrackActivity.getMarketplaceId()) && internalEqualityCheck(getDeviceType(), recentPlayedTrackActivity.getDeviceType()) && internalEqualityCheck(getIsMusicSubscription(), recentPlayedTrackActivity.getIsMusicSubscription());
    }

    public String getAlbumAsin() {
        return this.albumAsin;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public String getArtistAsin() {
        return this.artistAsin;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getAsin() {
        return this.asin;
    }

    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getDurationSeconds() {
        return this.durationSeconds;
    }

    public String getHasLyrics() {
        return this.hasLyrics;
    }

    public String getImageFull() {
        return this.imageFull;
    }

    public String getIsAtmosAvailable() {
        return this.isAtmosAvailable;
    }

    public String getIsFree() {
        return this.isFree;
    }

    public String getIsFreeOnDemandPlayable() {
        return this.isFreeOnDemandPlayable;
    }

    public String getIsHdAvailable() {
        return this.isHdAvailable;
    }

    public String getIsHiResAvailable() {
        return this.isHiResAvailable;
    }

    public String getIsInLibrary() {
        return this.isInLibrary;
    }

    public String getIsInstantImport() {
        return this.isInstantImport;
    }

    public String getIsMerlockExcluded() {
        return this.isMerlockExcluded;
    }

    public String getIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public String getIsPrime() {
        return this.isPrime;
    }

    public String getIsPurchased() {
        return this.isPurchased;
    }

    public String getIsRA360Available() {
        return this.isRA360Available;
    }

    public String getIsSonicRush() {
        return this.isSonicRush;
    }

    public String getIsSonicRushOnDemandPlayable() {
        return this.isSonicRushOnDemandPlayable;
    }

    public String getIsThreeDAvailable() {
        return this.isThreeDAvailable;
    }

    public String getIsUploaded() {
        return this.isUploaded;
    }

    public String getLibraryStatus() {
        return this.libraryStatus;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getUniqueIdentifier() {
        return this.uniqueIdentifier;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDisplayName(), getIsUploaded(), getParentalControls(), getIsInLibrary(), getIsAtmosAvailable(), getAlbumAsin(), getIsRA360Available(), getIsSonicRushOnDemandPlayable(), getHasLyrics(), getTimestamp(), getUniqueIdentifier(), getContentInfo(), getIsFree(), getImageFull(), getIsSonicRush(), getIsHiResAvailable(), getArtistName(), getIsInstantImport(), getIsMerlockExcluded(), getIsThreeDAvailable(), getIsHdAvailable(), getDeviceId(), getIsFreeOnDemandPlayable(), getAsin(), getLibraryStatus(), getDurationSeconds(), getArtistAsin(), getMusicTerritory(), getIsPrime(), getAlbumName(), getObjectId(), getIsPurchased(), getMarketplaceId(), getDeviceType(), getIsMusicSubscription());
    }

    public void setAlbumAsin(String str) {
        this.albumAsin = str;
    }

    public void setAlbumName(String str) {
        this.albumName = str;
    }

    public void setArtistAsin(String str) {
        this.artistAsin = str;
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setContentInfo(ContentInfo contentInfo) {
        this.contentInfo = contentInfo;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public void setDurationSeconds(String str) {
        this.durationSeconds = str;
    }

    public void setHasLyrics(String str) {
        this.hasLyrics = str;
    }

    public void setImageFull(String str) {
        this.imageFull = str;
    }

    public void setIsAtmosAvailable(String str) {
        this.isAtmosAvailable = str;
    }

    public void setIsFree(String str) {
        this.isFree = str;
    }

    public void setIsFreeOnDemandPlayable(String str) {
        this.isFreeOnDemandPlayable = str;
    }

    public void setIsHdAvailable(String str) {
        this.isHdAvailable = str;
    }

    public void setIsHiResAvailable(String str) {
        this.isHiResAvailable = str;
    }

    public void setIsInLibrary(String str) {
        this.isInLibrary = str;
    }

    public void setIsInstantImport(String str) {
        this.isInstantImport = str;
    }

    public void setIsMerlockExcluded(String str) {
        this.isMerlockExcluded = str;
    }

    public void setIsMusicSubscription(String str) {
        this.isMusicSubscription = str;
    }

    public void setIsPrime(String str) {
        this.isPrime = str;
    }

    public void setIsPurchased(String str) {
        this.isPurchased = str;
    }

    public void setIsRA360Available(String str) {
        this.isRA360Available = str;
    }

    public void setIsSonicRush(String str) {
        this.isSonicRush = str;
    }

    public void setIsSonicRushOnDemandPlayable(String str) {
        this.isSonicRushOnDemandPlayable = str;
    }

    public void setIsThreeDAvailable(String str) {
        this.isThreeDAvailable = str;
    }

    public void setIsUploaded(String str) {
        this.isUploaded = str;
    }

    public void setLibraryStatus(String str) {
        this.libraryStatus = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }

    public void setUniqueIdentifier(String str) {
        this.uniqueIdentifier = str;
    }
}
