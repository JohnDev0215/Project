package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.AllowedParentalControls;
import com.amazon.musicensembleservice.RequestIdentity;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class LookupRequest extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.LookupRequest");
    private AllowedParentalControls allowedParentalControls;
    private List<String> asins;
    private String currencyOfPreference;
    private List<String> features;
    private List<String> filters;
    private String lang;
    private String metadataLang;
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
        if (!(requestIdentity instanceof LookupRequest)) {
            return 1;
        }
        LookupRequest lookupRequest = (LookupRequest) requestIdentity;
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = lookupRequest.getAllowedParentalControls();
        if (allowedParentalControls != allowedParentalControls2) {
            if (allowedParentalControls == null) {
                return -1;
            }
            if (allowedParentalControls2 == null) {
                return 1;
            }
            int iCompareTo = allowedParentalControls.compareTo(allowedParentalControls2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String currencyOfPreference = getCurrencyOfPreference();
        String currencyOfPreference2 = lookupRequest.getCurrencyOfPreference();
        if (currencyOfPreference != currencyOfPreference2) {
            if (currencyOfPreference == null) {
                return -1;
            }
            if (currencyOfPreference2 == null) {
                return 1;
            }
            int iCompareTo2 = currencyOfPreference.compareTo(currencyOfPreference2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<String> features = getFeatures();
        List<String> features2 = lookupRequest.getFeatures();
        if (features != features2) {
            if (features == null) {
                return -1;
            }
            if (features2 == null) {
                return 1;
            }
            if (features instanceof Comparable) {
                int iCompareTo3 = ((Comparable) features).compareTo(features2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!features.equals(features2)) {
                int iHashCode = features.hashCode();
                int iHashCode2 = features2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String lang = getLang();
        String lang2 = lookupRequest.getLang();
        if (lang != lang2) {
            if (lang == null) {
                return -1;
            }
            if (lang2 == null) {
                return 1;
            }
            int iCompareTo4 = lang.compareTo(lang2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String metadataLang = getMetadataLang();
        String metadataLang2 = lookupRequest.getMetadataLang();
        if (metadataLang != metadataLang2) {
            if (metadataLang == null) {
                return -1;
            }
            if (metadataLang2 == null) {
                return 1;
            }
            int iCompareTo5 = metadataLang.compareTo(metadataLang2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Boolean boolIsStub = isStub();
        Boolean boolIsStub2 = lookupRequest.isStub();
        if (boolIsStub != boolIsStub2) {
            if (boolIsStub == null) {
                return -1;
            }
            if (boolIsStub2 == null) {
                return 1;
            }
            int iCompareTo6 = boolIsStub.compareTo(boolIsStub2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        List<String> asins = getAsins();
        List<String> asins2 = lookupRequest.getAsins();
        if (asins != asins2) {
            if (asins == null) {
                return -1;
            }
            if (asins2 == null) {
                return 1;
            }
            if (asins instanceof Comparable) {
                int iCompareTo7 = ((Comparable) asins).compareTo(asins2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!asins.equals(asins2)) {
                int iHashCode3 = asins.hashCode();
                int iHashCode4 = asins2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<String> filters = getFilters();
        List<String> filters2 = lookupRequest.getFilters();
        if (filters != filters2) {
            if (filters == null) {
                return -1;
            }
            if (filters2 == null) {
                return 1;
            }
            if (filters instanceof Comparable) {
                int iCompareTo8 = ((Comparable) filters).compareTo(filters2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!filters.equals(filters2)) {
                int iHashCode5 = filters.hashCode();
                int iHashCode6 = filters2.hashCode();
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
        if (!(obj instanceof LookupRequest)) {
            return false;
        }
        LookupRequest lookupRequest = (LookupRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getAllowedParentalControls(), lookupRequest.getAllowedParentalControls()) && internalEqualityCheck(getCurrencyOfPreference(), lookupRequest.getCurrencyOfPreference()) && internalEqualityCheck(getFeatures(), lookupRequest.getFeatures()) && internalEqualityCheck(getLang(), lookupRequest.getLang()) && internalEqualityCheck(getMetadataLang(), lookupRequest.getMetadataLang()) && internalEqualityCheck(isStub(), lookupRequest.isStub()) && internalEqualityCheck(getAsins(), lookupRequest.getAsins()) && internalEqualityCheck(getFilters(), lookupRequest.getFilters());
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public List<String> getAsins() {
        return this.asins;
    }

    public String getCurrencyOfPreference() {
        return this.currencyOfPreference;
    }

    public List<String> getFeatures() {
        return this.features;
    }

    public List<String> getFilters() {
        return this.filters;
    }

    public String getLang() {
        return this.lang;
    }

    public String getMetadataLang() {
        return this.metadataLang;
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAllowedParentalControls(), getCurrencyOfPreference(), getFeatures(), getLang(), getMetadataLang(), isStub(), getAsins(), getFilters());
    }

    public Boolean isStub() {
        return this.stub;
    }

    public void setAllowedParentalControls(AllowedParentalControls allowedParentalControls) {
        this.allowedParentalControls = allowedParentalControls;
    }

    public void setAsins(List<String> list) {
        this.asins = list;
    }

    public void setFeatures(List<String> list) {
        this.features = list;
    }

    public void setLang(String str) {
        this.lang = str;
    }

    public void setStub(Boolean bool) {
        this.stub = bool;
    }
}
