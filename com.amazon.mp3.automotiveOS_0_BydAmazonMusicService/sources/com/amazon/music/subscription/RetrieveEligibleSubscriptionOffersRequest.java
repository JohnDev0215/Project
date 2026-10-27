package com.amazon.music.subscription;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RetrieveEligibleSubscriptionOffersRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.subscription.RetrieveEligibleSubscriptionOffersRequest");
    private Boolean includeBuyability;
    private Boolean includeIneligibleOffers;
    private String ipAddress;
    private String marketplaceId;
    private String musicTerritory;
    private List<String> responseFilters;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof RetrieveEligibleSubscriptionOffersRequest)) {
            return 1;
        }
        RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest = (RetrieveEligibleSubscriptionOffersRequest) cirrusBaseRequestV2;
        Boolean boolIsIncludeIneligibleOffers = isIncludeIneligibleOffers();
        Boolean boolIsIncludeIneligibleOffers2 = retrieveEligibleSubscriptionOffersRequest.isIncludeIneligibleOffers();
        if (boolIsIncludeIneligibleOffers != boolIsIncludeIneligibleOffers2) {
            if (boolIsIncludeIneligibleOffers == null) {
                return -1;
            }
            if (boolIsIncludeIneligibleOffers2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIncludeIneligibleOffers.compareTo(boolIsIncludeIneligibleOffers2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = retrieveEligibleSubscriptionOffersRequest.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo2 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String ipAddress = getIpAddress();
        String ipAddress2 = retrieveEligibleSubscriptionOffersRequest.getIpAddress();
        if (ipAddress != ipAddress2) {
            if (ipAddress == null) {
                return -1;
            }
            if (ipAddress2 == null) {
                return 1;
            }
            int iCompareTo3 = ipAddress.compareTo(ipAddress2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<String> responseFilters = getResponseFilters();
        List<String> responseFilters2 = retrieveEligibleSubscriptionOffersRequest.getResponseFilters();
        if (responseFilters != responseFilters2) {
            if (responseFilters == null) {
                return -1;
            }
            if (responseFilters2 == null) {
                return 1;
            }
            if (responseFilters instanceof Comparable) {
                int iCompareTo4 = ((Comparable) responseFilters).compareTo(responseFilters2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!responseFilters.equals(responseFilters2)) {
                int iHashCode = responseFilters.hashCode();
                int iHashCode2 = responseFilters2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Boolean boolIsIncludeBuyability = isIncludeBuyability();
        Boolean boolIsIncludeBuyability2 = retrieveEligibleSubscriptionOffersRequest.isIncludeBuyability();
        if (boolIsIncludeBuyability != boolIsIncludeBuyability2) {
            if (boolIsIncludeBuyability == null) {
                return -1;
            }
            if (boolIsIncludeBuyability2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsIncludeBuyability.compareTo(boolIsIncludeBuyability2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = retrieveEligibleSubscriptionOffersRequest.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo6 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof RetrieveEligibleSubscriptionOffersRequest)) {
            return false;
        }
        RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest = (RetrieveEligibleSubscriptionOffersRequest) obj;
        return super.equals(obj) && internalEqualityCheck(isIncludeIneligibleOffers(), retrieveEligibleSubscriptionOffersRequest.isIncludeIneligibleOffers()) && internalEqualityCheck(getMarketplaceId(), retrieveEligibleSubscriptionOffersRequest.getMarketplaceId()) && internalEqualityCheck(getIpAddress(), retrieveEligibleSubscriptionOffersRequest.getIpAddress()) && internalEqualityCheck(getResponseFilters(), retrieveEligibleSubscriptionOffersRequest.getResponseFilters()) && internalEqualityCheck(isIncludeBuyability(), retrieveEligibleSubscriptionOffersRequest.isIncludeBuyability()) && internalEqualityCheck(getMusicTerritory(), retrieveEligibleSubscriptionOffersRequest.getMusicTerritory());
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public List<String> getResponseFilters() {
        return this.responseFilters;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isIncludeIneligibleOffers(), getMarketplaceId(), getIpAddress(), getResponseFilters(), isIncludeBuyability(), getMusicTerritory());
    }

    public Boolean isIncludeBuyability() {
        return this.includeBuyability;
    }

    public Boolean isIncludeIneligibleOffers() {
        return this.includeIneligibleOffers;
    }

    public void setIncludeIneligibleOffers(Boolean bool) {
        this.includeIneligibleOffers = bool;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setResponseFilters(List<String> list) {
        this.responseFilters = list;
    }
}
