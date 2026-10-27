package com.amazon.layout.music.model;

import com.amazon.musicensembleservice.StationSubscriptionTierDetail;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class StationHint extends Hint {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.StationHint");
    private Boolean isFree;
    private Boolean isMusicSubscription;
    private Boolean isPrime;
    private Boolean isRecommended;
    private Boolean isSonicRush;
    private String recentlyPlayedEntityType;
    private String seedId;
    private String stationImageUrl;
    private String stationKey;
    private Map<String, StationSubscriptionTierDetail> subscriptionTierToDetails;
    private Long timeStamp;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Hint, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Hint hint) {
        if (hint == null) {
            return -1;
        }
        if (hint == this) {
            return 0;
        }
        if (!(hint instanceof StationHint)) {
            return 1;
        }
        StationHint stationHint = (StationHint) hint;
        Long timeStamp = getTimeStamp();
        Long timeStamp2 = stationHint.getTimeStamp();
        if (timeStamp != timeStamp2) {
            if (timeStamp == null) {
                return -1;
            }
            if (timeStamp2 == null) {
                return 1;
            }
            int iCompareTo = timeStamp.compareTo(timeStamp2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = stationHint.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Map<String, StationSubscriptionTierDetail> subscriptionTierToDetails = getSubscriptionTierToDetails();
        Map<String, StationSubscriptionTierDetail> subscriptionTierToDetails2 = stationHint.getSubscriptionTierToDetails();
        if (subscriptionTierToDetails != subscriptionTierToDetails2) {
            if (subscriptionTierToDetails == null) {
                return -1;
            }
            if (subscriptionTierToDetails2 == null) {
                return 1;
            }
            if (subscriptionTierToDetails instanceof Comparable) {
                int iCompareTo3 = ((Comparable) subscriptionTierToDetails).compareTo(subscriptionTierToDetails2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!subscriptionTierToDetails.equals(subscriptionTierToDetails2)) {
                int iHashCode = subscriptionTierToDetails.hashCode();
                int iHashCode2 = subscriptionTierToDetails2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Boolean boolIsIsSonicRush = isIsSonicRush();
        Boolean boolIsIsSonicRush2 = stationHint.isIsSonicRush();
        if (boolIsIsSonicRush != boolIsIsSonicRush2) {
            if (boolIsIsSonicRush == null) {
                return -1;
            }
            if (boolIsIsSonicRush2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsIsSonicRush.compareTo(boolIsIsSonicRush2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsIsRecommended = isIsRecommended();
        Boolean boolIsIsRecommended2 = stationHint.isIsRecommended();
        if (boolIsIsRecommended != boolIsIsRecommended2) {
            if (boolIsIsRecommended == null) {
                return -1;
            }
            if (boolIsIsRecommended2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsIsRecommended.compareTo(boolIsIsRecommended2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String recentlyPlayedEntityType = getRecentlyPlayedEntityType();
        String recentlyPlayedEntityType2 = stationHint.getRecentlyPlayedEntityType();
        if (recentlyPlayedEntityType != recentlyPlayedEntityType2) {
            if (recentlyPlayedEntityType == null) {
                return -1;
            }
            if (recentlyPlayedEntityType2 == null) {
                return 1;
            }
            int iCompareTo6 = recentlyPlayedEntityType.compareTo(recentlyPlayedEntityType2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String title = getTitle();
        String title2 = stationHint.getTitle();
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
        String stationKey = getStationKey();
        String stationKey2 = stationHint.getStationKey();
        if (stationKey != stationKey2) {
            if (stationKey == null) {
                return -1;
            }
            if (stationKey2 == null) {
                return 1;
            }
            int iCompareTo8 = stationKey.compareTo(stationKey2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String stationImageUrl = getStationImageUrl();
        String stationImageUrl2 = stationHint.getStationImageUrl();
        if (stationImageUrl != stationImageUrl2) {
            if (stationImageUrl == null) {
                return -1;
            }
            if (stationImageUrl2 == null) {
                return 1;
            }
            int iCompareTo9 = stationImageUrl.compareTo(stationImageUrl2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Boolean boolIsIsFree = isIsFree();
        Boolean boolIsIsFree2 = stationHint.isIsFree();
        if (boolIsIsFree != boolIsIsFree2) {
            if (boolIsIsFree == null) {
                return -1;
            }
            if (boolIsIsFree2 == null) {
                return 1;
            }
            int iCompareTo10 = boolIsIsFree.compareTo(boolIsIsFree2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = stationHint.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo11 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String seedId = getSeedId();
        String seedId2 = stationHint.getSeedId();
        if (seedId != seedId2) {
            if (seedId == null) {
                return -1;
            }
            if (seedId2 == null) {
                return 1;
            }
            int iCompareTo12 = seedId.compareTo(seedId2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        return super.compareTo(hint);
    }

    @Override // com.amazon.layout.music.model.Hint
    public boolean equals(Object obj) {
        if (!(obj instanceof StationHint)) {
            return false;
        }
        StationHint stationHint = (StationHint) obj;
        return super.equals(obj) && internalEqualityCheck(getTimeStamp(), stationHint.getTimeStamp()) && internalEqualityCheck(isIsMusicSubscription(), stationHint.isIsMusicSubscription()) && internalEqualityCheck(getSubscriptionTierToDetails(), stationHint.getSubscriptionTierToDetails()) && internalEqualityCheck(isIsSonicRush(), stationHint.isIsSonicRush()) && internalEqualityCheck(isIsRecommended(), stationHint.isIsRecommended()) && internalEqualityCheck(getRecentlyPlayedEntityType(), stationHint.getRecentlyPlayedEntityType()) && internalEqualityCheck(getTitle(), stationHint.getTitle()) && internalEqualityCheck(getStationKey(), stationHint.getStationKey()) && internalEqualityCheck(getStationImageUrl(), stationHint.getStationImageUrl()) && internalEqualityCheck(isIsFree(), stationHint.isIsFree()) && internalEqualityCheck(isIsPrime(), stationHint.isIsPrime()) && internalEqualityCheck(getSeedId(), stationHint.getSeedId());
    }

    public String getRecentlyPlayedEntityType() {
        return this.recentlyPlayedEntityType;
    }

    public String getSeedId() {
        return this.seedId;
    }

    public String getStationImageUrl() {
        return this.stationImageUrl;
    }

    public String getStationKey() {
        return this.stationKey;
    }

    public Map<String, StationSubscriptionTierDetail> getSubscriptionTierToDetails() {
        return this.subscriptionTierToDetails;
    }

    public Long getTimeStamp() {
        return this.timeStamp;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.layout.music.model.Hint
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTimeStamp(), isIsMusicSubscription(), getSubscriptionTierToDetails(), isIsSonicRush(), isIsRecommended(), getRecentlyPlayedEntityType(), getTitle(), getStationKey(), getStationImageUrl(), isIsFree(), isIsPrime(), getSeedId());
    }

    public Boolean isIsFree() {
        return this.isFree;
    }

    public Boolean isIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public Boolean isIsPrime() {
        return this.isPrime;
    }

    public Boolean isIsRecommended() {
        return this.isRecommended;
    }

    public Boolean isIsSonicRush() {
        return this.isSonicRush;
    }

    public void setIsFree(Boolean bool) {
        this.isFree = bool;
    }

    public void setIsMusicSubscription(Boolean bool) {
        this.isMusicSubscription = bool;
    }

    public void setIsPrime(Boolean bool) {
        this.isPrime = bool;
    }

    public void setIsRecommended(Boolean bool) {
        this.isRecommended = bool;
    }

    public void setIsSonicRush(Boolean bool) {
        this.isSonicRush = bool;
    }

    public void setRecentlyPlayedEntityType(String str) {
        this.recentlyPlayedEntityType = str;
    }

    public void setSeedId(String str) {
        this.seedId = str;
    }

    public void setStationImageUrl(String str) {
        this.stationImageUrl = str;
    }

    public void setStationKey(String str) {
        this.stationKey = str;
    }

    public void setSubscriptionTierToDetails(Map<String, StationSubscriptionTierDetail> map) {
        this.subscriptionTierToDetails = map;
    }

    public void setTimeStamp(Long l) {
        this.timeStamp = l;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
