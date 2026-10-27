package com.amazon.musicensembleservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ArtistDetailsMetadataRequest extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.ArtistDetailsMetadataRequest");
    private AllowedParentalControls allowedParentalControls;
    private String asin;
    private List<String> features;
    private String lang;
    private List<String> languagesOfPerformance;
    private String metadataLang;
    private Boolean stub;
    private List<ArtistDetailsMetadataType> types;

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
        if (!(requestIdentity instanceof ArtistDetailsMetadataRequest)) {
            return 1;
        }
        ArtistDetailsMetadataRequest artistDetailsMetadataRequest = (ArtistDetailsMetadataRequest) requestIdentity;
        String asin = getAsin();
        String asin2 = artistDetailsMetadataRequest.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo = asin.compareTo(asin2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> languagesOfPerformance = getLanguagesOfPerformance();
        List<String> languagesOfPerformance2 = artistDetailsMetadataRequest.getLanguagesOfPerformance();
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
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = artistDetailsMetadataRequest.getAllowedParentalControls();
        if (allowedParentalControls != allowedParentalControls2) {
            if (allowedParentalControls == null) {
                return -1;
            }
            if (allowedParentalControls2 == null) {
                return 1;
            }
            int iCompareTo3 = allowedParentalControls.compareTo(allowedParentalControls2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String metadataLang = getMetadataLang();
        String metadataLang2 = artistDetailsMetadataRequest.getMetadataLang();
        if (metadataLang != metadataLang2) {
            if (metadataLang == null) {
                return -1;
            }
            if (metadataLang2 == null) {
                return 1;
            }
            int iCompareTo4 = metadataLang.compareTo(metadataLang2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsStub = isStub();
        Boolean boolIsStub2 = artistDetailsMetadataRequest.isStub();
        if (boolIsStub != boolIsStub2) {
            if (boolIsStub == null) {
                return -1;
            }
            if (boolIsStub2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsStub.compareTo(boolIsStub2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<String> features = getFeatures();
        List<String> features2 = artistDetailsMetadataRequest.getFeatures();
        if (features != features2) {
            if (features == null) {
                return -1;
            }
            if (features2 == null) {
                return 1;
            }
            if (features instanceof Comparable) {
                int iCompareTo6 = ((Comparable) features).compareTo(features2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!features.equals(features2)) {
                int iHashCode3 = features.hashCode();
                int iHashCode4 = features2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String lang = getLang();
        String lang2 = artistDetailsMetadataRequest.getLang();
        if (lang != lang2) {
            if (lang == null) {
                return -1;
            }
            if (lang2 == null) {
                return 1;
            }
            int iCompareTo7 = lang.compareTo(lang2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        List<ArtistDetailsMetadataType> types = getTypes();
        List<ArtistDetailsMetadataType> types2 = artistDetailsMetadataRequest.getTypes();
        if (types != types2) {
            if (types == null) {
                return -1;
            }
            if (types2 == null) {
                return 1;
            }
            if (types instanceof Comparable) {
                int iCompareTo8 = ((Comparable) types).compareTo(types2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!types.equals(types2)) {
                int iHashCode5 = types.hashCode();
                int iHashCode6 = types2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        return super.compareTo(requestIdentity);
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public boolean equals(Object obj) {
        if (!(obj instanceof ArtistDetailsMetadataRequest)) {
            return false;
        }
        ArtistDetailsMetadataRequest artistDetailsMetadataRequest = (ArtistDetailsMetadataRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getAsin(), artistDetailsMetadataRequest.getAsin()) && internalEqualityCheck(getLanguagesOfPerformance(), artistDetailsMetadataRequest.getLanguagesOfPerformance()) && internalEqualityCheck(getAllowedParentalControls(), artistDetailsMetadataRequest.getAllowedParentalControls()) && internalEqualityCheck(getMetadataLang(), artistDetailsMetadataRequest.getMetadataLang()) && internalEqualityCheck(isStub(), artistDetailsMetadataRequest.isStub()) && internalEqualityCheck(getFeatures(), artistDetailsMetadataRequest.getFeatures()) && internalEqualityCheck(getLang(), artistDetailsMetadataRequest.getLang()) && internalEqualityCheck(getTypes(), artistDetailsMetadataRequest.getTypes());
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public String getAsin() {
        return this.asin;
    }

    public List<String> getFeatures() {
        return this.features;
    }

    public String getLang() {
        return this.lang;
    }

    public List<String> getLanguagesOfPerformance() {
        return this.languagesOfPerformance;
    }

    public String getMetadataLang() {
        return this.metadataLang;
    }

    public List<ArtistDetailsMetadataType> getTypes() {
        return this.types;
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAsin(), getLanguagesOfPerformance(), getAllowedParentalControls(), getMetadataLang(), isStub(), getFeatures(), getLang(), getTypes());
    }

    public Boolean isStub() {
        return this.stub;
    }

    public void setAllowedParentalControls(AllowedParentalControls allowedParentalControls) {
        this.allowedParentalControls = allowedParentalControls;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setFeatures(List<String> list) {
        this.features = list;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public void setTypes(List<ArtistDetailsMetadataType> list) {
        this.types = list;
    }
}
