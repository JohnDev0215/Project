package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CatalogArtist extends Artist {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.CatalogArtist");
    private List<String> alternativeName;
    private List<String> contentTier;
    private String contributorAsin;
    private String isMusicSubscription;
    private String primeStatus;
    private String titleUnlocalized;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Artist, com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof CatalogArtist)) {
            return 1;
        }
        CatalogArtist catalogArtist = (CatalogArtist) document;
        String titleUnlocalized = getTitleUnlocalized();
        String titleUnlocalized2 = catalogArtist.getTitleUnlocalized();
        if (titleUnlocalized != titleUnlocalized2) {
            if (titleUnlocalized == null) {
                return -1;
            }
            if (titleUnlocalized2 == null) {
                return 1;
            }
            int iCompareTo = titleUnlocalized.compareTo(titleUnlocalized2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String contributorAsin = getContributorAsin();
        String contributorAsin2 = catalogArtist.getContributorAsin();
        if (contributorAsin != contributorAsin2) {
            if (contributorAsin == null) {
                return -1;
            }
            if (contributorAsin2 == null) {
                return 1;
            }
            int iCompareTo2 = contributorAsin.compareTo(contributorAsin2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<String> alternativeName = getAlternativeName();
        List<String> alternativeName2 = catalogArtist.getAlternativeName();
        if (alternativeName != alternativeName2) {
            if (alternativeName == null) {
                return -1;
            }
            if (alternativeName2 == null) {
                return 1;
            }
            if (alternativeName instanceof Comparable) {
                int iCompareTo3 = ((Comparable) alternativeName).compareTo(alternativeName2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!alternativeName.equals(alternativeName2)) {
                int iHashCode = alternativeName.hashCode();
                int iHashCode2 = alternativeName2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String isMusicSubscription = getIsMusicSubscription();
        String isMusicSubscription2 = catalogArtist.getIsMusicSubscription();
        if (isMusicSubscription != isMusicSubscription2) {
            if (isMusicSubscription == null) {
                return -1;
            }
            if (isMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo4 = isMusicSubscription.compareTo(isMusicSubscription2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String primeStatus = getPrimeStatus();
        String primeStatus2 = catalogArtist.getPrimeStatus();
        if (primeStatus != primeStatus2) {
            if (primeStatus == null) {
                return -1;
            }
            if (primeStatus2 == null) {
                return 1;
            }
            int iCompareTo5 = primeStatus.compareTo(primeStatus2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<String> contentTier = getContentTier();
        List<String> contentTier2 = catalogArtist.getContentTier();
        if (contentTier != contentTier2) {
            if (contentTier == null) {
                return -1;
            }
            if (contentTier2 == null) {
                return 1;
            }
            if (contentTier instanceof Comparable) {
                int iCompareTo6 = ((Comparable) contentTier).compareTo(contentTier2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!contentTier.equals(contentTier2)) {
                int iHashCode3 = contentTier.hashCode();
                int iHashCode4 = contentTier2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Artist, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogArtist)) {
            return false;
        }
        CatalogArtist catalogArtist = (CatalogArtist) obj;
        return super.equals(obj) && internalEqualityCheck(getTitleUnlocalized(), catalogArtist.getTitleUnlocalized()) && internalEqualityCheck(getContributorAsin(), catalogArtist.getContributorAsin()) && internalEqualityCheck(getAlternativeName(), catalogArtist.getAlternativeName()) && internalEqualityCheck(getIsMusicSubscription(), catalogArtist.getIsMusicSubscription()) && internalEqualityCheck(getPrimeStatus(), catalogArtist.getPrimeStatus()) && internalEqualityCheck(getContentTier(), catalogArtist.getContentTier());
    }

    public List<String> getAlternativeName() {
        return this.alternativeName;
    }

    public List<String> getContentTier() {
        return this.contentTier;
    }

    public String getContributorAsin() {
        return this.contributorAsin;
    }

    public String getIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public String getPrimeStatus() {
        return this.primeStatus;
    }

    public String getTitleUnlocalized() {
        return this.titleUnlocalized;
    }

    @Override // com.amazon.music.platform.model.Artist, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitleUnlocalized(), getContributorAsin(), getAlternativeName(), getIsMusicSubscription(), getPrimeStatus(), getContentTier());
    }

    public void setAlternativeName(List<String> list) {
        this.alternativeName = list;
    }

    public void setContentTier(List<String> list) {
        this.contentTier = list;
    }

    public void setContributorAsin(String str) {
        this.contributorAsin = str;
    }

    public void setIsMusicSubscription(String str) {
        this.isMusicSubscription = str;
    }

    public void setPrimeStatus(String str) {
        this.primeStatus = str;
    }

    public void setTitleUnlocalized(String str) {
        this.titleUnlocalized = str;
    }
}
