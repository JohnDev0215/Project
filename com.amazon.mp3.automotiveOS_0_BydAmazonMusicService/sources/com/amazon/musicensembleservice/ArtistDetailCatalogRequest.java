package com.amazon.musicensembleservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ArtistDetailCatalogRequest extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.ArtistDetailCatalogRequest");
    private AllowedParentalControls allowedParentalControls;
    private String asin;
    private String lang;
    private List<String> languagesOfPerformance;
    private List<String> options;
    private Boolean stub;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.RequestIdentity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RequestIdentity requestIdentity) {
        if (requestIdentity == null) {
            return -1;
        }
        if (requestIdentity == this) {
            return 0;
        }
        if (!(requestIdentity instanceof ArtistDetailCatalogRequest)) {
            return 1;
        }
        ArtistDetailCatalogRequest artistDetailCatalogRequest = (ArtistDetailCatalogRequest) requestIdentity;
        Boolean boolIsStub = isStub();
        Boolean boolIsStub2 = artistDetailCatalogRequest.isStub();
        if (boolIsStub != boolIsStub2) {
            if (boolIsStub == null) {
                return -1;
            }
            if (boolIsStub2 == null) {
                return 1;
            }
            int iCompareTo = boolIsStub.compareTo(boolIsStub2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> languagesOfPerformance = getLanguagesOfPerformance();
        List<String> languagesOfPerformance2 = artistDetailCatalogRequest.getLanguagesOfPerformance();
        if (languagesOfPerformance != languagesOfPerformance2) {
            if (languagesOfPerformance == null) {
                return -1;
            }
            if (languagesOfPerformance2 == null) {
                return 1;
            }
            if (languagesOfPerformance instanceof Comparable) {
                int iCompareTo2 = ((Comparable) languagesOfPerformance).compareTo(languagesOfPerformance2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!languagesOfPerformance.equals(languagesOfPerformance2)) {
                int iHashCode = languagesOfPerformance.hashCode();
                int iHashCode2 = languagesOfPerformance2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String lang = getLang();
        String lang2 = artistDetailCatalogRequest.getLang();
        if (lang != lang2) {
            if (lang == null) {
                return -1;
            }
            if (lang2 == null) {
                return 1;
            }
            int iCompareTo3 = lang.compareTo(lang2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<String> options = getOptions();
        List<String> options2 = artistDetailCatalogRequest.getOptions();
        if (options != options2) {
            if (options == null) {
                return -1;
            }
            if (options2 == null) {
                return 1;
            }
            if (options instanceof Comparable) {
                int iCompareTo4 = ((Comparable) options).compareTo(options2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!options.equals(options2)) {
                int iHashCode3 = options.hashCode();
                int iHashCode4 = options2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = artistDetailCatalogRequest.getAllowedParentalControls();
        if (allowedParentalControls != allowedParentalControls2) {
            if (allowedParentalControls == null) {
                return -1;
            }
            if (allowedParentalControls2 == null) {
                return 1;
            }
            int iCompareTo5 = allowedParentalControls.compareTo(allowedParentalControls2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String asin = getAsin();
        String asin2 = artistDetailCatalogRequest.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo6 = asin.compareTo(asin2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return super.compareTo(requestIdentity);
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public boolean equals(Object obj) {
        if (!(obj instanceof ArtistDetailCatalogRequest)) {
            return false;
        }
        ArtistDetailCatalogRequest artistDetailCatalogRequest = (ArtistDetailCatalogRequest) obj;
        return super.equals(obj) && internalEqualityCheck(isStub(), artistDetailCatalogRequest.isStub()) && internalEqualityCheck(getLanguagesOfPerformance(), artistDetailCatalogRequest.getLanguagesOfPerformance()) && internalEqualityCheck(getLang(), artistDetailCatalogRequest.getLang()) && internalEqualityCheck(getOptions(), artistDetailCatalogRequest.getOptions()) && internalEqualityCheck(getAllowedParentalControls(), artistDetailCatalogRequest.getAllowedParentalControls()) && internalEqualityCheck(getAsin(), artistDetailCatalogRequest.getAsin());
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public String getAsin() {
        return this.asin;
    }

    public String getLang() {
        return this.lang;
    }

    public List<String> getLanguagesOfPerformance() {
        return this.languagesOfPerformance;
    }

    public List<String> getOptions() {
        return this.options;
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isStub(), getLanguagesOfPerformance(), getLang(), getOptions(), getAllowedParentalControls(), getAsin());
    }

    public Boolean isStub() {
        return this.stub;
    }
}
