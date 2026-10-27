package com.amazon.musicensembleservice.stations;

import com.amazon.musicensembleservice.StationSubscriptionTierDetail;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class Station implements Comparable<Station> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.stations.Station");
    private String cleanExplicit;
    private boolean isFree;
    private boolean isMusicSubscription;
    private boolean isPrime;
    private boolean isSonicRush;
    private String seedId;
    private String seedType;
    private String stationImageUrl;
    private String stationKey;
    private String stationTitle;
    private Map<String, StationSubscriptionTierDetail> subscriptionTierToDetails;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Station station) {
        if (station == null) {
            return -1;
        }
        if (station == this) {
            return 0;
        }
        String seedType = getSeedType();
        String seedType2 = station.getSeedType();
        if (seedType != seedType2) {
            if (seedType == null) {
                return -1;
            }
            if (seedType2 == null) {
                return 1;
            }
            int iCompareTo = seedType.compareTo(seedType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String cleanExplicit = getCleanExplicit();
        String cleanExplicit2 = station.getCleanExplicit();
        if (cleanExplicit != cleanExplicit2) {
            if (cleanExplicit == null) {
                return -1;
            }
            if (cleanExplicit2 == null) {
                return 1;
            }
            int iCompareTo2 = cleanExplicit.compareTo(cleanExplicit2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String stationKey = getStationKey();
        String stationKey2 = station.getStationKey();
        if (stationKey != stationKey2) {
            if (stationKey == null) {
                return -1;
            }
            if (stationKey2 == null) {
                return 1;
            }
            int iCompareTo3 = stationKey.compareTo(stationKey2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Map<String, StationSubscriptionTierDetail> subscriptionTierToDetails = getSubscriptionTierToDetails();
        Map<String, StationSubscriptionTierDetail> subscriptionTierToDetails2 = station.getSubscriptionTierToDetails();
        if (subscriptionTierToDetails != subscriptionTierToDetails2) {
            if (subscriptionTierToDetails == null) {
                return -1;
            }
            if (subscriptionTierToDetails2 == null) {
                return 1;
            }
            if (subscriptionTierToDetails instanceof Comparable) {
                int iCompareTo4 = ((Comparable) subscriptionTierToDetails).compareTo(subscriptionTierToDetails2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
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
        if (!isIsMusicSubscription() && station.isIsMusicSubscription()) {
            return -1;
        }
        if (isIsMusicSubscription() && !station.isIsMusicSubscription()) {
            return 1;
        }
        if (!isIsPrime() && station.isIsPrime()) {
            return -1;
        }
        if (isIsPrime() && !station.isIsPrime()) {
            return 1;
        }
        if (!isIsFree() && station.isIsFree()) {
            return -1;
        }
        if (isIsFree() && !station.isIsFree()) {
            return 1;
        }
        String seedId = getSeedId();
        String seedId2 = station.getSeedId();
        if (seedId != seedId2) {
            if (seedId == null) {
                return -1;
            }
            if (seedId2 == null) {
                return 1;
            }
            int iCompareTo5 = seedId.compareTo(seedId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String stationTitle = getStationTitle();
        String stationTitle2 = station.getStationTitle();
        if (stationTitle != stationTitle2) {
            if (stationTitle == null) {
                return -1;
            }
            if (stationTitle2 == null) {
                return 1;
            }
            int iCompareTo6 = stationTitle.compareTo(stationTitle2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String stationImageUrl = getStationImageUrl();
        String stationImageUrl2 = station.getStationImageUrl();
        if (stationImageUrl != stationImageUrl2) {
            if (stationImageUrl == null) {
                return -1;
            }
            if (stationImageUrl2 == null) {
                return 1;
            }
            int iCompareTo7 = stationImageUrl.compareTo(stationImageUrl2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        if (isIsSonicRush() || !station.isIsSonicRush()) {
            return (!isIsSonicRush() || station.isIsSonicRush()) ? 0 : 1;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Station)) {
            return false;
        }
        Station station = (Station) obj;
        return internalEqualityCheck(getSeedType(), station.getSeedType()) && internalEqualityCheck(getCleanExplicit(), station.getCleanExplicit()) && internalEqualityCheck(getStationKey(), station.getStationKey()) && internalEqualityCheck(getSubscriptionTierToDetails(), station.getSubscriptionTierToDetails()) && internalEqualityCheck(Boolean.valueOf(isIsMusicSubscription()), Boolean.valueOf(station.isIsMusicSubscription())) && internalEqualityCheck(Boolean.valueOf(isIsPrime()), Boolean.valueOf(station.isIsPrime())) && internalEqualityCheck(Boolean.valueOf(isIsFree()), Boolean.valueOf(station.isIsFree())) && internalEqualityCheck(getSeedId(), station.getSeedId()) && internalEqualityCheck(getStationTitle(), station.getStationTitle()) && internalEqualityCheck(getStationImageUrl(), station.getStationImageUrl()) && internalEqualityCheck(Boolean.valueOf(isIsSonicRush()), Boolean.valueOf(station.isIsSonicRush()));
    }

    public String getCleanExplicit() {
        return this.cleanExplicit;
    }

    public String getSeedId() {
        return this.seedId;
    }

    public String getSeedType() {
        return this.seedType;
    }

    public String getStationImageUrl() {
        return this.stationImageUrl;
    }

    public String getStationKey() {
        return this.stationKey;
    }

    public String getStationTitle() {
        return this.stationTitle;
    }

    public Map<String, StationSubscriptionTierDetail> getSubscriptionTierToDetails() {
        return this.subscriptionTierToDetails;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSeedType(), getCleanExplicit(), getStationKey(), getSubscriptionTierToDetails(), Boolean.valueOf(isIsMusicSubscription()), Boolean.valueOf(isIsPrime()), Boolean.valueOf(isIsFree()), getSeedId(), getStationTitle(), getStationImageUrl(), Boolean.valueOf(isIsSonicRush()));
    }

    public boolean isIsFree() {
        return this.isFree;
    }

    public boolean isIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public boolean isIsPrime() {
        return this.isPrime;
    }

    public boolean isIsSonicRush() {
        return this.isSonicRush;
    }

    public void setCleanExplicit(String str) {
        this.cleanExplicit = str;
    }

    public void setIsFree(boolean z) {
        this.isFree = z;
    }

    public void setIsMusicSubscription(boolean z) {
        this.isMusicSubscription = z;
    }

    public void setIsPrime(boolean z) {
        this.isPrime = z;
    }

    public void setIsSonicRush(boolean z) {
        this.isSonicRush = z;
    }

    public void setSeedId(String str) {
        this.seedId = str;
    }

    public void setSeedType(String str) {
        this.seedType = str;
    }

    public void setStationImageUrl(String str) {
        this.stationImageUrl = str;
    }

    public void setStationKey(String str) {
        this.stationKey = str;
    }

    public void setStationTitle(String str) {
        this.stationTitle = str;
    }

    public void setSubscriptionTierToDetails(Map<String, StationSubscriptionTierDetail> map) {
        this.subscriptionTierToDetails = map;
    }
}
