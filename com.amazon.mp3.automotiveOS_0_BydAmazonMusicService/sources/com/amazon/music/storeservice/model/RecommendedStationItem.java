package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.StationSubscriptionTierDetail;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendedStationItem implements Comparable<RecommendedStationItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.RecommendedStationItem");
    private Boolean isFree;
    private Boolean isMusicSubscription;
    private Boolean isPrime;
    private Boolean isSonicRush;
    private Justification justification;
    private Seed seed;
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
    public int compareTo(@Deprecated RecommendedStationItem recommendedStationItem) {
        if (recommendedStationItem == null) {
            return -1;
        }
        if (recommendedStationItem == this) {
            return 0;
        }
        Boolean boolIsIsFree = isIsFree();
        Boolean boolIsIsFree2 = recommendedStationItem.isIsFree();
        if (boolIsIsFree != boolIsIsFree2) {
            if (boolIsIsFree == null) {
                return -1;
            }
            if (boolIsIsFree2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIsFree.compareTo(boolIsIsFree2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String stationTitle = getStationTitle();
        String stationTitle2 = recommendedStationItem.getStationTitle();
        if (stationTitle != stationTitle2) {
            if (stationTitle == null) {
                return -1;
            }
            if (stationTitle2 == null) {
                return 1;
            }
            int iCompareTo2 = stationTitle.compareTo(stationTitle2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String stationImageUrl = getStationImageUrl();
        String stationImageUrl2 = recommendedStationItem.getStationImageUrl();
        if (stationImageUrl != stationImageUrl2) {
            if (stationImageUrl == null) {
                return -1;
            }
            if (stationImageUrl2 == null) {
                return 1;
            }
            int iCompareTo3 = stationImageUrl.compareTo(stationImageUrl2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsIsSonicRush = isIsSonicRush();
        Boolean boolIsIsSonicRush2 = recommendedStationItem.isIsSonicRush();
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
        String stationKey = getStationKey();
        String stationKey2 = recommendedStationItem.getStationKey();
        if (stationKey != stationKey2) {
            if (stationKey == null) {
                return -1;
            }
            if (stationKey2 == null) {
                return 1;
            }
            int iCompareTo5 = stationKey.compareTo(stationKey2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Map<String, StationSubscriptionTierDetail> subscriptionTierToDetails = getSubscriptionTierToDetails();
        Map<String, StationSubscriptionTierDetail> subscriptionTierToDetails2 = recommendedStationItem.getSubscriptionTierToDetails();
        if (subscriptionTierToDetails != subscriptionTierToDetails2) {
            if (subscriptionTierToDetails == null) {
                return -1;
            }
            if (subscriptionTierToDetails2 == null) {
                return 1;
            }
            if (subscriptionTierToDetails instanceof Comparable) {
                int iCompareTo6 = ((Comparable) subscriptionTierToDetails).compareTo(subscriptionTierToDetails2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
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
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = recommendedStationItem.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = recommendedStationItem.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo8 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Justification justification = getJustification();
        Justification justification2 = recommendedStationItem.getJustification();
        if (justification != justification2) {
            if (justification == null) {
                return -1;
            }
            if (justification2 == null) {
                return 1;
            }
            int iCompareTo9 = justification.compareTo(justification2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Seed seed = getSeed();
        Seed seed2 = recommendedStationItem.getSeed();
        if (seed != seed2) {
            if (seed == null) {
                return -1;
            }
            if (seed2 == null) {
                return 1;
            }
            int iCompareTo10 = seed.compareTo(seed2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RecommendedStationItem)) {
            return false;
        }
        RecommendedStationItem recommendedStationItem = (RecommendedStationItem) obj;
        return internalEqualityCheck(isIsFree(), recommendedStationItem.isIsFree()) && internalEqualityCheck(getStationTitle(), recommendedStationItem.getStationTitle()) && internalEqualityCheck(getStationImageUrl(), recommendedStationItem.getStationImageUrl()) && internalEqualityCheck(isIsSonicRush(), recommendedStationItem.isIsSonicRush()) && internalEqualityCheck(getStationKey(), recommendedStationItem.getStationKey()) && internalEqualityCheck(getSubscriptionTierToDetails(), recommendedStationItem.getSubscriptionTierToDetails()) && internalEqualityCheck(isIsMusicSubscription(), recommendedStationItem.isIsMusicSubscription()) && internalEqualityCheck(isIsPrime(), recommendedStationItem.isIsPrime()) && internalEqualityCheck(getJustification(), recommendedStationItem.getJustification()) && internalEqualityCheck(getSeed(), recommendedStationItem.getSeed());
    }

    public Justification getJustification() {
        return this.justification;
    }

    public Seed getSeed() {
        return this.seed;
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
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isIsFree(), getStationTitle(), getStationImageUrl(), isIsSonicRush(), getStationKey(), getSubscriptionTierToDetails(), isIsMusicSubscription(), isIsPrime(), getJustification(), getSeed());
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

    public void setIsSonicRush(Boolean bool) {
        this.isSonicRush = bool;
    }

    public void setJustification(Justification justification) {
        this.justification = justification;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
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
