package com.amazon.nimblymusicservice;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class RecentActivityData implements Comparable<RecentActivityData> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.RecentActivityData");
    private ContentInfo contentInfo;
    private String deviceTypeName;
    private String displayName;
    private String entityId;
    private String entityIdType;
    private String entityType;
    private String imageFull;
    private String instanceId;
    private String isAtmosAvailable;
    private String isFree;
    private String isFreeOnDemandPlayable;
    private String isHdAvailable;
    private String isHiResAvailable;
    private String isMerlockExcluded;
    private String isMusicSubscription;
    private String isPrime;
    private String isRA360Available;
    private String isSonicRush;
    private String isSonicRushGolden;
    private String isSonicRushOnDemandPlayable;
    private String isThreeDAvailable;
    private String musicTerritory;
    private Date timestamp;
    private String trackId;
    private String trackIdType;
    private String uniqueIdentifier;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RecentActivityData recentActivityData) {
        if (recentActivityData == null) {
            return -1;
        }
        if (recentActivityData == this) {
            return 0;
        }
        String imageFull = getImageFull();
        String imageFull2 = recentActivityData.getImageFull();
        if (imageFull != imageFull2) {
            if (imageFull == null) {
                return -1;
            }
            if (imageFull2 == null) {
                return 1;
            }
            int iCompareTo = imageFull.compareTo(imageFull2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Date timestamp = getTimestamp();
        Date timestamp2 = recentActivityData.getTimestamp();
        if (timestamp != timestamp2) {
            if (timestamp == null) {
                return -1;
            }
            if (timestamp2 == null) {
                return 1;
            }
            int iCompareTo2 = timestamp.compareTo(timestamp2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        ContentInfo contentInfo = getContentInfo();
        ContentInfo contentInfo2 = recentActivityData.getContentInfo();
        if (contentInfo != contentInfo2) {
            if (contentInfo == null) {
                return -1;
            }
            if (contentInfo2 == null) {
                return 1;
            }
            int iCompareTo3 = contentInfo.compareTo(contentInfo2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String entityIdType = getEntityIdType();
        String entityIdType2 = recentActivityData.getEntityIdType();
        if (entityIdType != entityIdType2) {
            if (entityIdType == null) {
                return -1;
            }
            if (entityIdType2 == null) {
                return 1;
            }
            int iCompareTo4 = entityIdType.compareTo(entityIdType2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String uniqueIdentifier = getUniqueIdentifier();
        String uniqueIdentifier2 = recentActivityData.getUniqueIdentifier();
        if (uniqueIdentifier != uniqueIdentifier2) {
            if (uniqueIdentifier == null) {
                return -1;
            }
            if (uniqueIdentifier2 == null) {
                return 1;
            }
            int iCompareTo5 = uniqueIdentifier.compareTo(uniqueIdentifier2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String isPrime = getIsPrime();
        String isPrime2 = recentActivityData.getIsPrime();
        if (isPrime != isPrime2) {
            if (isPrime == null) {
                return -1;
            }
            if (isPrime2 == null) {
                return 1;
            }
            int iCompareTo6 = isPrime.compareTo(isPrime2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = recentActivityData.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo7 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String isSonicRushOnDemandPlayable = getIsSonicRushOnDemandPlayable();
        String isSonicRushOnDemandPlayable2 = recentActivityData.getIsSonicRushOnDemandPlayable();
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
        String isMusicSubscription = getIsMusicSubscription();
        String isMusicSubscription2 = recentActivityData.getIsMusicSubscription();
        if (isMusicSubscription != isMusicSubscription2) {
            if (isMusicSubscription == null) {
                return -1;
            }
            if (isMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo9 = isMusicSubscription.compareTo(isMusicSubscription2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String trackIdType = getTrackIdType();
        String trackIdType2 = recentActivityData.getTrackIdType();
        if (trackIdType != trackIdType2) {
            if (trackIdType == null) {
                return -1;
            }
            if (trackIdType2 == null) {
                return 1;
            }
            int iCompareTo10 = trackIdType.compareTo(trackIdType2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String isFree = getIsFree();
        String isFree2 = recentActivityData.getIsFree();
        if (isFree != isFree2) {
            if (isFree == null) {
                return -1;
            }
            if (isFree2 == null) {
                return 1;
            }
            int iCompareTo11 = isFree.compareTo(isFree2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String entityId = getEntityId();
        String entityId2 = recentActivityData.getEntityId();
        if (entityId != entityId2) {
            if (entityId == null) {
                return -1;
            }
            if (entityId2 == null) {
                return 1;
            }
            int iCompareTo12 = entityId.compareTo(entityId2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String isMerlockExcluded = getIsMerlockExcluded();
        String isMerlockExcluded2 = recentActivityData.getIsMerlockExcluded();
        if (isMerlockExcluded != isMerlockExcluded2) {
            if (isMerlockExcluded == null) {
                return -1;
            }
            if (isMerlockExcluded2 == null) {
                return 1;
            }
            int iCompareTo13 = isMerlockExcluded.compareTo(isMerlockExcluded2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String isSonicRush = getIsSonicRush();
        String isSonicRush2 = recentActivityData.getIsSonicRush();
        if (isSonicRush != isSonicRush2) {
            if (isSonicRush == null) {
                return -1;
            }
            if (isSonicRush2 == null) {
                return 1;
            }
            int iCompareTo14 = isSonicRush.compareTo(isSonicRush2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String entityType = getEntityType();
        String entityType2 = recentActivityData.getEntityType();
        if (entityType != entityType2) {
            if (entityType == null) {
                return -1;
            }
            if (entityType2 == null) {
                return 1;
            }
            int iCompareTo15 = entityType.compareTo(entityType2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String isRA360Available = getIsRA360Available();
        String isRA360Available2 = recentActivityData.getIsRA360Available();
        if (isRA360Available != isRA360Available2) {
            if (isRA360Available == null) {
                return -1;
            }
            if (isRA360Available2 == null) {
                return 1;
            }
            int iCompareTo16 = isRA360Available.compareTo(isRA360Available2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String isAtmosAvailable = getIsAtmosAvailable();
        String isAtmosAvailable2 = recentActivityData.getIsAtmosAvailable();
        if (isAtmosAvailable != isAtmosAvailable2) {
            if (isAtmosAvailable == null) {
                return -1;
            }
            if (isAtmosAvailable2 == null) {
                return 1;
            }
            int iCompareTo17 = isAtmosAvailable.compareTo(isAtmosAvailable2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String instanceId = getInstanceId();
        String instanceId2 = recentActivityData.getInstanceId();
        if (instanceId != instanceId2) {
            if (instanceId == null) {
                return -1;
            }
            if (instanceId2 == null) {
                return 1;
            }
            int iCompareTo18 = instanceId.compareTo(instanceId2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        String trackId = getTrackId();
        String trackId2 = recentActivityData.getTrackId();
        if (trackId != trackId2) {
            if (trackId == null) {
                return -1;
            }
            if (trackId2 == null) {
                return 1;
            }
            int iCompareTo19 = trackId.compareTo(trackId2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        String deviceTypeName = getDeviceTypeName();
        String deviceTypeName2 = recentActivityData.getDeviceTypeName();
        if (deviceTypeName != deviceTypeName2) {
            if (deviceTypeName == null) {
                return -1;
            }
            if (deviceTypeName2 == null) {
                return 1;
            }
            int iCompareTo20 = deviceTypeName.compareTo(deviceTypeName2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        String isFreeOnDemandPlayable = getIsFreeOnDemandPlayable();
        String isFreeOnDemandPlayable2 = recentActivityData.getIsFreeOnDemandPlayable();
        if (isFreeOnDemandPlayable != isFreeOnDemandPlayable2) {
            if (isFreeOnDemandPlayable == null) {
                return -1;
            }
            if (isFreeOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo21 = isFreeOnDemandPlayable.compareTo(isFreeOnDemandPlayable2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        String isHiResAvailable = getIsHiResAvailable();
        String isHiResAvailable2 = recentActivityData.getIsHiResAvailable();
        if (isHiResAvailable != isHiResAvailable2) {
            if (isHiResAvailable == null) {
                return -1;
            }
            if (isHiResAvailable2 == null) {
                return 1;
            }
            int iCompareTo22 = isHiResAvailable.compareTo(isHiResAvailable2);
            if (iCompareTo22 != 0) {
                return iCompareTo22;
            }
        }
        String isSonicRushGolden = getIsSonicRushGolden();
        String isSonicRushGolden2 = recentActivityData.getIsSonicRushGolden();
        if (isSonicRushGolden != isSonicRushGolden2) {
            if (isSonicRushGolden == null) {
                return -1;
            }
            if (isSonicRushGolden2 == null) {
                return 1;
            }
            int iCompareTo23 = isSonicRushGolden.compareTo(isSonicRushGolden2);
            if (iCompareTo23 != 0) {
                return iCompareTo23;
            }
        }
        String isThreeDAvailable = getIsThreeDAvailable();
        String isThreeDAvailable2 = recentActivityData.getIsThreeDAvailable();
        if (isThreeDAvailable != isThreeDAvailable2) {
            if (isThreeDAvailable == null) {
                return -1;
            }
            if (isThreeDAvailable2 == null) {
                return 1;
            }
            int iCompareTo24 = isThreeDAvailable.compareTo(isThreeDAvailable2);
            if (iCompareTo24 != 0) {
                return iCompareTo24;
            }
        }
        String displayName = getDisplayName();
        String displayName2 = recentActivityData.getDisplayName();
        if (displayName != displayName2) {
            if (displayName == null) {
                return -1;
            }
            if (displayName2 == null) {
                return 1;
            }
            int iCompareTo25 = displayName.compareTo(displayName2);
            if (iCompareTo25 != 0) {
                return iCompareTo25;
            }
        }
        String isHdAvailable = getIsHdAvailable();
        String isHdAvailable2 = recentActivityData.getIsHdAvailable();
        if (isHdAvailable != isHdAvailable2) {
            if (isHdAvailable == null) {
                return -1;
            }
            if (isHdAvailable2 == null) {
                return 1;
            }
            int iCompareTo26 = isHdAvailable.compareTo(isHdAvailable2);
            if (iCompareTo26 != 0) {
                return iCompareTo26;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RecentActivityData)) {
            return false;
        }
        RecentActivityData recentActivityData = (RecentActivityData) obj;
        return internalEqualityCheck(getImageFull(), recentActivityData.getImageFull()) && internalEqualityCheck(getTimestamp(), recentActivityData.getTimestamp()) && internalEqualityCheck(getContentInfo(), recentActivityData.getContentInfo()) && internalEqualityCheck(getEntityIdType(), recentActivityData.getEntityIdType()) && internalEqualityCheck(getUniqueIdentifier(), recentActivityData.getUniqueIdentifier()) && internalEqualityCheck(getIsPrime(), recentActivityData.getIsPrime()) && internalEqualityCheck(getMusicTerritory(), recentActivityData.getMusicTerritory()) && internalEqualityCheck(getIsSonicRushOnDemandPlayable(), recentActivityData.getIsSonicRushOnDemandPlayable()) && internalEqualityCheck(getIsMusicSubscription(), recentActivityData.getIsMusicSubscription()) && internalEqualityCheck(getTrackIdType(), recentActivityData.getTrackIdType()) && internalEqualityCheck(getIsFree(), recentActivityData.getIsFree()) && internalEqualityCheck(getEntityId(), recentActivityData.getEntityId()) && internalEqualityCheck(getIsMerlockExcluded(), recentActivityData.getIsMerlockExcluded()) && internalEqualityCheck(getIsSonicRush(), recentActivityData.getIsSonicRush()) && internalEqualityCheck(getEntityType(), recentActivityData.getEntityType()) && internalEqualityCheck(getIsRA360Available(), recentActivityData.getIsRA360Available()) && internalEqualityCheck(getIsAtmosAvailable(), recentActivityData.getIsAtmosAvailable()) && internalEqualityCheck(getInstanceId(), recentActivityData.getInstanceId()) && internalEqualityCheck(getTrackId(), recentActivityData.getTrackId()) && internalEqualityCheck(getDeviceTypeName(), recentActivityData.getDeviceTypeName()) && internalEqualityCheck(getIsFreeOnDemandPlayable(), recentActivityData.getIsFreeOnDemandPlayable()) && internalEqualityCheck(getIsHiResAvailable(), recentActivityData.getIsHiResAvailable()) && internalEqualityCheck(getIsSonicRushGolden(), recentActivityData.getIsSonicRushGolden()) && internalEqualityCheck(getIsThreeDAvailable(), recentActivityData.getIsThreeDAvailable()) && internalEqualityCheck(getDisplayName(), recentActivityData.getDisplayName()) && internalEqualityCheck(getIsHdAvailable(), recentActivityData.getIsHdAvailable());
    }

    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    public String getDeviceTypeName() {
        return this.deviceTypeName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public String getEntityIdType() {
        return this.entityIdType;
    }

    public String getEntityType() {
        return this.entityType;
    }

    public String getImageFull() {
        return this.imageFull;
    }

    public String getInstanceId() {
        return this.instanceId;
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

    public String getIsMerlockExcluded() {
        return this.isMerlockExcluded;
    }

    public String getIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public String getIsPrime() {
        return this.isPrime;
    }

    public String getIsRA360Available() {
        return this.isRA360Available;
    }

    public String getIsSonicRush() {
        return this.isSonicRush;
    }

    public String getIsSonicRushGolden() {
        return this.isSonicRushGolden;
    }

    public String getIsSonicRushOnDemandPlayable() {
        return this.isSonicRushOnDemandPlayable;
    }

    public String getIsThreeDAvailable() {
        return this.isThreeDAvailable;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public String getTrackId() {
        return this.trackId;
    }

    public String getTrackIdType() {
        return this.trackIdType;
    }

    public String getUniqueIdentifier() {
        return this.uniqueIdentifier;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getImageFull(), getTimestamp(), getContentInfo(), getEntityIdType(), getUniqueIdentifier(), getIsPrime(), getMusicTerritory(), getIsSonicRushOnDemandPlayable(), getIsMusicSubscription(), getTrackIdType(), getIsFree(), getEntityId(), getIsMerlockExcluded(), getIsSonicRush(), getEntityType(), getIsRA360Available(), getIsAtmosAvailable(), getInstanceId(), getTrackId(), getDeviceTypeName(), getIsFreeOnDemandPlayable(), getIsHiResAvailable(), getIsSonicRushGolden(), getIsThreeDAvailable(), getDisplayName(), getIsHdAvailable());
    }

    public void setContentInfo(ContentInfo contentInfo) {
        this.contentInfo = contentInfo;
    }

    public void setDeviceTypeName(String str) {
        this.deviceTypeName = str;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public void setEntityId(String str) {
        this.entityId = str;
    }

    public void setEntityIdType(String str) {
        this.entityIdType = str;
    }

    public void setEntityType(String str) {
        this.entityType = str;
    }

    public void setImageFull(String str) {
        this.imageFull = str;
    }

    public void setInstanceId(String str) {
        this.instanceId = str;
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

    public void setIsMerlockExcluded(String str) {
        this.isMerlockExcluded = str;
    }

    public void setIsMusicSubscription(String str) {
        this.isMusicSubscription = str;
    }

    public void setIsPrime(String str) {
        this.isPrime = str;
    }

    public void setIsRA360Available(String str) {
        this.isRA360Available = str;
    }

    public void setIsSonicRush(String str) {
        this.isSonicRush = str;
    }

    public void setIsSonicRushGolden(String str) {
        this.isSonicRushGolden = str;
    }

    public void setIsSonicRushOnDemandPlayable(String str) {
        this.isSonicRushOnDemandPlayable = str;
    }

    public void setIsThreeDAvailable(String str) {
        this.isThreeDAvailable = str;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setTimestamp(Date date) {
        this.timestamp = date;
    }

    public void setTrackId(String str) {
        this.trackId = str;
    }

    public void setTrackIdType(String str) {
        this.trackIdType = str;
    }

    public void setUniqueIdentifier(String str) {
        this.uniqueIdentifier = str;
    }
}
