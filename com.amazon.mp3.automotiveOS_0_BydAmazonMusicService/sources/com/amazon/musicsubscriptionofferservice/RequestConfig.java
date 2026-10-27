package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RequestConfig implements Comparable<RequestConfig> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.RequestConfig");
    private List<AlternateDevice> alternateDevices;
    private Boolean includeBuyability;
    private Boolean includeIneligibleOffers;
    private Boolean includeOfferMetadata;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RequestConfig requestConfig) {
        if (requestConfig == null) {
            return -1;
        }
        if (requestConfig == this) {
            return 0;
        }
        Boolean boolIsIncludeBuyability = isIncludeBuyability();
        Boolean boolIsIncludeBuyability2 = requestConfig.isIncludeBuyability();
        if (boolIsIncludeBuyability != boolIsIncludeBuyability2) {
            if (boolIsIncludeBuyability == null) {
                return -1;
            }
            if (boolIsIncludeBuyability2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIncludeBuyability.compareTo(boolIsIncludeBuyability2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<AlternateDevice> alternateDevices = getAlternateDevices();
        List<AlternateDevice> alternateDevices2 = requestConfig.getAlternateDevices();
        if (alternateDevices != alternateDevices2) {
            if (alternateDevices == null) {
                return -1;
            }
            if (alternateDevices2 == null) {
                return 1;
            }
            if (alternateDevices instanceof Comparable) {
                int iCompareTo2 = ((Comparable) alternateDevices).compareTo(alternateDevices2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!alternateDevices.equals(alternateDevices2)) {
                int iHashCode = alternateDevices.hashCode();
                int iHashCode2 = alternateDevices2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Boolean boolIsIncludeOfferMetadata = isIncludeOfferMetadata();
        Boolean boolIsIncludeOfferMetadata2 = requestConfig.isIncludeOfferMetadata();
        if (boolIsIncludeOfferMetadata != boolIsIncludeOfferMetadata2) {
            if (boolIsIncludeOfferMetadata == null) {
                return -1;
            }
            if (boolIsIncludeOfferMetadata2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsIncludeOfferMetadata.compareTo(boolIsIncludeOfferMetadata2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsIncludeIneligibleOffers = isIncludeIneligibleOffers();
        Boolean boolIsIncludeIneligibleOffers2 = requestConfig.isIncludeIneligibleOffers();
        if (boolIsIncludeIneligibleOffers != boolIsIncludeIneligibleOffers2) {
            if (boolIsIncludeIneligibleOffers == null) {
                return -1;
            }
            if (boolIsIncludeIneligibleOffers2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsIncludeIneligibleOffers.compareTo(boolIsIncludeIneligibleOffers2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RequestConfig)) {
            return false;
        }
        RequestConfig requestConfig = (RequestConfig) obj;
        return internalEqualityCheck(isIncludeBuyability(), requestConfig.isIncludeBuyability()) && internalEqualityCheck(getAlternateDevices(), requestConfig.getAlternateDevices()) && internalEqualityCheck(isIncludeOfferMetadata(), requestConfig.isIncludeOfferMetadata()) && internalEqualityCheck(isIncludeIneligibleOffers(), requestConfig.isIncludeIneligibleOffers());
    }

    public List<AlternateDevice> getAlternateDevices() {
        return this.alternateDevices;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isIncludeBuyability(), getAlternateDevices(), isIncludeOfferMetadata(), isIncludeIneligibleOffers());
    }

    public Boolean isIncludeBuyability() {
        return this.includeBuyability;
    }

    public Boolean isIncludeIneligibleOffers() {
        return this.includeIneligibleOffers;
    }

    public Boolean isIncludeOfferMetadata() {
        return this.includeOfferMetadata;
    }

    public void setAlternateDevices(List<AlternateDevice> list) {
        this.alternateDevices = list;
    }

    public void setIncludeBuyability(Boolean bool) {
        this.includeBuyability = bool;
    }

    public void setIncludeIneligibleOffers(Boolean bool) {
        this.includeIneligibleOffers = bool;
    }

    public void setIncludeOfferMetadata(Boolean bool) {
        this.includeOfferMetadata = bool;
    }
}
