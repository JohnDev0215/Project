package com.amazon.musicensembleservice;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GetHomeRequest extends RequestIdentity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.GetHomeRequest");
    private AllowedParentalControls allowedParentalControls;
    private String allowedParentalControlsString;
    private String campaignsXml;
    private Boolean isDebug;
    private String lang;
    private List<String> languagesOfPerformance;
    private List<String> options;
    private String recsTestingConfigToken;
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
        if (!(requestIdentity instanceof GetHomeRequest)) {
            return 1;
        }
        GetHomeRequest getHomeRequest = (GetHomeRequest) requestIdentity;
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = getHomeRequest.getAllowedParentalControls();
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
        String allowedParentalControlsString = getAllowedParentalControlsString();
        String allowedParentalControlsString2 = getHomeRequest.getAllowedParentalControlsString();
        if (allowedParentalControlsString != allowedParentalControlsString2) {
            if (allowedParentalControlsString == null) {
                return -1;
            }
            if (allowedParentalControlsString2 == null) {
                return 1;
            }
            int iCompareTo2 = allowedParentalControlsString.compareTo(allowedParentalControlsString2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String lang = getLang();
        String lang2 = getHomeRequest.getLang();
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
        Boolean boolIsIsDebug = isIsDebug();
        Boolean boolIsIsDebug2 = getHomeRequest.isIsDebug();
        if (boolIsIsDebug != boolIsIsDebug2) {
            if (boolIsIsDebug == null) {
                return -1;
            }
            if (boolIsIsDebug2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsIsDebug.compareTo(boolIsIsDebug2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsStub = isStub();
        Boolean boolIsStub2 = getHomeRequest.isStub();
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
        String campaignsXml = getCampaignsXml();
        String campaignsXml2 = getHomeRequest.getCampaignsXml();
        if (campaignsXml != campaignsXml2) {
            if (campaignsXml == null) {
                return -1;
            }
            if (campaignsXml2 == null) {
                return 1;
            }
            int iCompareTo6 = campaignsXml.compareTo(campaignsXml2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        List<String> languagesOfPerformance = getLanguagesOfPerformance();
        List<String> languagesOfPerformance2 = getHomeRequest.getLanguagesOfPerformance();
        if (languagesOfPerformance != languagesOfPerformance2) {
            if (languagesOfPerformance == null) {
                return -1;
            }
            if (languagesOfPerformance2 == null) {
                return 1;
            }
            if (languagesOfPerformance instanceof Comparable) {
                int iCompareTo7 = ((Comparable) languagesOfPerformance).compareTo(languagesOfPerformance2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
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
        String recsTestingConfigToken = getRecsTestingConfigToken();
        String recsTestingConfigToken2 = getHomeRequest.getRecsTestingConfigToken();
        if (recsTestingConfigToken != recsTestingConfigToken2) {
            if (recsTestingConfigToken == null) {
                return -1;
            }
            if (recsTestingConfigToken2 == null) {
                return 1;
            }
            int iCompareTo8 = recsTestingConfigToken.compareTo(recsTestingConfigToken2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        List<String> options = getOptions();
        List<String> options2 = getHomeRequest.getOptions();
        if (options != options2) {
            if (options == null) {
                return -1;
            }
            if (options2 == null) {
                return 1;
            }
            if (options instanceof Comparable) {
                int iCompareTo9 = ((Comparable) options).compareTo(options2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
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
        return super.compareTo(requestIdentity);
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public boolean equals(Object obj) {
        if (!(obj instanceof GetHomeRequest)) {
            return false;
        }
        GetHomeRequest getHomeRequest = (GetHomeRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getAllowedParentalControls(), getHomeRequest.getAllowedParentalControls()) && internalEqualityCheck(getAllowedParentalControlsString(), getHomeRequest.getAllowedParentalControlsString()) && internalEqualityCheck(getLang(), getHomeRequest.getLang()) && internalEqualityCheck(isIsDebug(), getHomeRequest.isIsDebug()) && internalEqualityCheck(isStub(), getHomeRequest.isStub()) && internalEqualityCheck(getCampaignsXml(), getHomeRequest.getCampaignsXml()) && internalEqualityCheck(getLanguagesOfPerformance(), getHomeRequest.getLanguagesOfPerformance()) && internalEqualityCheck(getRecsTestingConfigToken(), getHomeRequest.getRecsTestingConfigToken()) && internalEqualityCheck(getOptions(), getHomeRequest.getOptions());
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public String getAllowedParentalControlsString() {
        return this.allowedParentalControlsString;
    }

    public String getCampaignsXml() {
        return this.campaignsXml;
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

    public String getRecsTestingConfigToken() {
        return this.recsTestingConfigToken;
    }

    @Override // com.amazon.musicensembleservice.RequestIdentity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAllowedParentalControls(), getAllowedParentalControlsString(), getLang(), isIsDebug(), isStub(), getCampaignsXml(), getLanguagesOfPerformance(), getRecsTestingConfigToken(), getOptions());
    }

    public Boolean isIsDebug() {
        return this.isDebug;
    }

    public Boolean isStub() {
        return this.stub;
    }
}
