package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CatalogPlaylistMetadata extends AbstractPlaylistMetadata {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.CatalogPlaylistMetadata");
    private String asin;
    private String curatedBy;
    private List<CurationDescriptorItem> curationDescriptor;
    private List<String> internalTags;
    private boolean isCustomerFollowing;
    private boolean isMusicSubscription;
    private boolean isNightwing;
    private boolean isNightwingOnDemandPlayable;
    private boolean isPrime;
    private String marketplaceId;
    private ParentalControls parentalControls;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplaylist.model.AbstractPlaylistMetadata, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractPlaylistMetadata abstractPlaylistMetadata) {
        if (abstractPlaylistMetadata == null) {
            return -1;
        }
        if (abstractPlaylistMetadata == this) {
            return 0;
        }
        if (!(abstractPlaylistMetadata instanceof CatalogPlaylistMetadata)) {
            return 1;
        }
        CatalogPlaylistMetadata catalogPlaylistMetadata = (CatalogPlaylistMetadata) abstractPlaylistMetadata;
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = catalogPlaylistMetadata.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo = parentalControls.compareTo(parentalControls2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<CurationDescriptorItem> curationDescriptor = getCurationDescriptor();
        List<CurationDescriptorItem> curationDescriptor2 = catalogPlaylistMetadata.getCurationDescriptor();
        if (curationDescriptor != curationDescriptor2) {
            if (curationDescriptor == null) {
                return -1;
            }
            if (curationDescriptor2 == null) {
                return 1;
            }
            if (curationDescriptor instanceof Comparable) {
                int iCompareTo2 = ((Comparable) curationDescriptor).compareTo(curationDescriptor2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!curationDescriptor.equals(curationDescriptor2)) {
                int iHashCode = curationDescriptor.hashCode();
                int iHashCode2 = curationDescriptor2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = catalogPlaylistMetadata.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo3 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        if (!isIsNightwingOnDemandPlayable() && catalogPlaylistMetadata.isIsNightwingOnDemandPlayable()) {
            return -1;
        }
        if (isIsNightwingOnDemandPlayable() && !catalogPlaylistMetadata.isIsNightwingOnDemandPlayable()) {
            return 1;
        }
        List<String> internalTags = getInternalTags();
        List<String> internalTags2 = catalogPlaylistMetadata.getInternalTags();
        if (internalTags != internalTags2) {
            if (internalTags == null) {
                return -1;
            }
            if (internalTags2 == null) {
                return 1;
            }
            if (internalTags instanceof Comparable) {
                int iCompareTo4 = ((Comparable) internalTags).compareTo(internalTags2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!internalTags.equals(internalTags2)) {
                int iHashCode3 = internalTags.hashCode();
                int iHashCode4 = internalTags2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String asin = getAsin();
        String asin2 = catalogPlaylistMetadata.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo5 = asin.compareTo(asin2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        if (!isIsMusicSubscription() && catalogPlaylistMetadata.isIsMusicSubscription()) {
            return -1;
        }
        if (isIsMusicSubscription() && !catalogPlaylistMetadata.isIsMusicSubscription()) {
            return 1;
        }
        if (!isIsNightwing() && catalogPlaylistMetadata.isIsNightwing()) {
            return -1;
        }
        if (isIsNightwing() && !catalogPlaylistMetadata.isIsNightwing()) {
            return 1;
        }
        if (!isIsCustomerFollowing() && catalogPlaylistMetadata.isIsCustomerFollowing()) {
            return -1;
        }
        if (isIsCustomerFollowing() && !catalogPlaylistMetadata.isIsCustomerFollowing()) {
            return 1;
        }
        String curatedBy = getCuratedBy();
        String curatedBy2 = catalogPlaylistMetadata.getCuratedBy();
        if (curatedBy != curatedBy2) {
            if (curatedBy == null) {
                return -1;
            }
            if (curatedBy2 == null) {
                return 1;
            }
            int iCompareTo6 = curatedBy.compareTo(curatedBy2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        if (!isIsPrime() && catalogPlaylistMetadata.isIsPrime()) {
            return -1;
        }
        if (!isIsPrime() || catalogPlaylistMetadata.isIsPrime()) {
            return super.compareTo(abstractPlaylistMetadata);
        }
        return 1;
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylistMetadata
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogPlaylistMetadata)) {
            return false;
        }
        CatalogPlaylistMetadata catalogPlaylistMetadata = (CatalogPlaylistMetadata) obj;
        return super.equals(obj) && internalEqualityCheck(getParentalControls(), catalogPlaylistMetadata.getParentalControls()) && internalEqualityCheck(getCurationDescriptor(), catalogPlaylistMetadata.getCurationDescriptor()) && internalEqualityCheck(getMarketplaceId(), catalogPlaylistMetadata.getMarketplaceId()) && internalEqualityCheck(Boolean.valueOf(isIsNightwingOnDemandPlayable()), Boolean.valueOf(catalogPlaylistMetadata.isIsNightwingOnDemandPlayable())) && internalEqualityCheck(getInternalTags(), catalogPlaylistMetadata.getInternalTags()) && internalEqualityCheck(getAsin(), catalogPlaylistMetadata.getAsin()) && internalEqualityCheck(Boolean.valueOf(isIsMusicSubscription()), Boolean.valueOf(catalogPlaylistMetadata.isIsMusicSubscription())) && internalEqualityCheck(Boolean.valueOf(isIsNightwing()), Boolean.valueOf(catalogPlaylistMetadata.isIsNightwing())) && internalEqualityCheck(Boolean.valueOf(isIsCustomerFollowing()), Boolean.valueOf(catalogPlaylistMetadata.isIsCustomerFollowing())) && internalEqualityCheck(getCuratedBy(), catalogPlaylistMetadata.getCuratedBy()) && internalEqualityCheck(Boolean.valueOf(isIsPrime()), Boolean.valueOf(catalogPlaylistMetadata.isIsPrime()));
    }

    public String getAsin() {
        return this.asin;
    }

    public String getCuratedBy() {
        return this.curatedBy;
    }

    public List<CurationDescriptorItem> getCurationDescriptor() {
        return this.curationDescriptor;
    }

    public List<String> getInternalTags() {
        return this.internalTags;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylistMetadata
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getParentalControls(), getCurationDescriptor(), getMarketplaceId(), Boolean.valueOf(isIsNightwingOnDemandPlayable()), getInternalTags(), getAsin(), Boolean.valueOf(isIsMusicSubscription()), Boolean.valueOf(isIsNightwing()), Boolean.valueOf(isIsCustomerFollowing()), getCuratedBy(), Boolean.valueOf(isIsPrime()));
    }

    public boolean isIsCustomerFollowing() {
        return this.isCustomerFollowing;
    }

    public boolean isIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public boolean isIsNightwing() {
        return this.isNightwing;
    }

    public boolean isIsNightwingOnDemandPlayable() {
        return this.isNightwingOnDemandPlayable;
    }

    public boolean isIsPrime() {
        return this.isPrime;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setCuratedBy(String str) {
        this.curatedBy = str;
    }

    public void setCurationDescriptor(List<CurationDescriptorItem> list) {
        this.curationDescriptor = list;
    }

    public void setInternalTags(List<String> list) {
        this.internalTags = list;
    }

    public void setIsCustomerFollowing(boolean z) {
        this.isCustomerFollowing = z;
    }

    public void setIsMusicSubscription(boolean z) {
        this.isMusicSubscription = z;
    }

    public void setIsNightwing(boolean z) {
        this.isNightwing = z;
    }

    public void setIsNightwingOnDemandPlayable(boolean z) {
        this.isNightwingOnDemandPlayable = z;
    }

    public void setIsPrime(boolean z) {
        this.isPrime = z;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }
}
