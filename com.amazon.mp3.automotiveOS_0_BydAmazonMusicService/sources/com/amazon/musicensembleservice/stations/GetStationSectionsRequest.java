package com.amazon.musicensembleservice.stations;

import com.amazon.musicensembleservice.AllowedParentalControls;
import com.amazon.musicensembleservice.RequestIdentity;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GetStationSectionsRequest extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.stations.GetStationSectionsRequest");
    private AllowedParentalControls allowedParentalControls;
    private String lang;
    private List<String> languagesOfPerformance;
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
        if (!(requestIdentity instanceof GetStationSectionsRequest)) {
            return 1;
        }
        GetStationSectionsRequest getStationSectionsRequest = (GetStationSectionsRequest) requestIdentity;
        String lang = getLang();
        String lang2 = getStationSectionsRequest.getLang();
        if (lang != lang2) {
            if (lang == null) {
                return -1;
            }
            if (lang2 == null) {
                return 1;
            }
            int iCompareTo = lang.compareTo(lang2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsStub = isStub();
        Boolean boolIsStub2 = getStationSectionsRequest.isStub();
        if (boolIsStub != boolIsStub2) {
            if (boolIsStub == null) {
                return -1;
            }
            if (boolIsStub2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsStub.compareTo(boolIsStub2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = getStationSectionsRequest.getAllowedParentalControls();
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
        List<String> languagesOfPerformance = getLanguagesOfPerformance();
        List<String> languagesOfPerformance2 = getStationSectionsRequest.getLanguagesOfPerformance();
        if (languagesOfPerformance != languagesOfPerformance2) {
            if (languagesOfPerformance == null) {
                return -1;
            }
            if (languagesOfPerformance2 == null) {
                return 1;
            }
            if (languagesOfPerformance instanceof Comparable) {
                int iCompareTo4 = ((Comparable) languagesOfPerformance).compareTo(languagesOfPerformance2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
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
        return super.compareTo(requestIdentity);
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public boolean equals(Object obj) {
        if (!(obj instanceof GetStationSectionsRequest)) {
            return false;
        }
        GetStationSectionsRequest getStationSectionsRequest = (GetStationSectionsRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getLang(), getStationSectionsRequest.getLang()) && internalEqualityCheck(isStub(), getStationSectionsRequest.isStub()) && internalEqualityCheck(getAllowedParentalControls(), getStationSectionsRequest.getAllowedParentalControls()) && internalEqualityCheck(getLanguagesOfPerformance(), getStationSectionsRequest.getLanguagesOfPerformance());
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public String getLang() {
        return this.lang;
    }

    public List<String> getLanguagesOfPerformance() {
        return this.languagesOfPerformance;
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getLang(), isStub(), getAllowedParentalControls(), getLanguagesOfPerformance());
    }

    public Boolean isStub() {
        return this.stub;
    }

    public void setAllowedParentalControls(AllowedParentalControls allowedParentalControls) {
        this.allowedParentalControls = allowedParentalControls;
    }

    public void setLang(String str) {
        this.lang = str;
    }
}
