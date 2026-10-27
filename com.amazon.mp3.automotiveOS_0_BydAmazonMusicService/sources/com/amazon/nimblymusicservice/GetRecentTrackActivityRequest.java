package com.amazon.nimblymusicservice;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContext;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetRecentTrackActivityRequest extends BaseRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.GetRecentTrackActivityRequest");
    private List<String> activityTypeFilters;
    private AllowedParentalControls allowedParentalControls;
    private List<String> features;
    private String languageLocale;
    private MusicRequestIdentityContext musicRequestIdentityContext;
    private String musicRequestIdentityContextToken;
    private String pageToken;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.nimblymusicservice.BaseRequest, com.amazon.nimblymusicservice.MarketplaceIdRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof GetRecentTrackActivityRequest)) {
            return 1;
        }
        GetRecentTrackActivityRequest getRecentTrackActivityRequest = (GetRecentTrackActivityRequest) cirrusBaseRequestV2;
        MusicRequestIdentityContext musicRequestIdentityContext = getMusicRequestIdentityContext();
        MusicRequestIdentityContext musicRequestIdentityContext2 = getRecentTrackActivityRequest.getMusicRequestIdentityContext();
        if (musicRequestIdentityContext != musicRequestIdentityContext2) {
            if (musicRequestIdentityContext == null) {
                return -1;
            }
            if (musicRequestIdentityContext2 == null) {
                return 1;
            }
            int iCompareTo = musicRequestIdentityContext.compareTo(musicRequestIdentityContext2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String languageLocale = getLanguageLocale();
        String languageLocale2 = getRecentTrackActivityRequest.getLanguageLocale();
        if (languageLocale != languageLocale2) {
            if (languageLocale == null) {
                return -1;
            }
            if (languageLocale2 == null) {
                return 1;
            }
            int iCompareTo2 = languageLocale.compareTo(languageLocale2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<String> features = getFeatures();
        List<String> features2 = getRecentTrackActivityRequest.getFeatures();
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
        String pageToken = getPageToken();
        String pageToken2 = getRecentTrackActivityRequest.getPageToken();
        if (pageToken != pageToken2) {
            if (pageToken == null) {
                return -1;
            }
            if (pageToken2 == null) {
                return 1;
            }
            int iCompareTo4 = pageToken.compareTo(pageToken2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = getRecentTrackActivityRequest.getAllowedParentalControls();
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
        List<String> activityTypeFilters = getActivityTypeFilters();
        List<String> activityTypeFilters2 = getRecentTrackActivityRequest.getActivityTypeFilters();
        if (activityTypeFilters != activityTypeFilters2) {
            if (activityTypeFilters == null) {
                return -1;
            }
            if (activityTypeFilters2 == null) {
                return 1;
            }
            if (activityTypeFilters instanceof Comparable) {
                int iCompareTo6 = ((Comparable) activityTypeFilters).compareTo(activityTypeFilters2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!activityTypeFilters.equals(activityTypeFilters2)) {
                int iHashCode3 = activityTypeFilters.hashCode();
                int iHashCode4 = activityTypeFilters2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String musicRequestIdentityContextToken = getMusicRequestIdentityContextToken();
        String musicRequestIdentityContextToken2 = getRecentTrackActivityRequest.getMusicRequestIdentityContextToken();
        if (musicRequestIdentityContextToken != musicRequestIdentityContextToken2) {
            if (musicRequestIdentityContextToken == null) {
                return -1;
            }
            if (musicRequestIdentityContextToken2 == null) {
                return 1;
            }
            int iCompareTo7 = musicRequestIdentityContextToken.compareTo(musicRequestIdentityContextToken2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.nimblymusicservice.BaseRequest, com.amazon.nimblymusicservice.MarketplaceIdRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof GetRecentTrackActivityRequest)) {
            return false;
        }
        GetRecentTrackActivityRequest getRecentTrackActivityRequest = (GetRecentTrackActivityRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getMusicRequestIdentityContext(), getRecentTrackActivityRequest.getMusicRequestIdentityContext()) && internalEqualityCheck(getLanguageLocale(), getRecentTrackActivityRequest.getLanguageLocale()) && internalEqualityCheck(getFeatures(), getRecentTrackActivityRequest.getFeatures()) && internalEqualityCheck(getPageToken(), getRecentTrackActivityRequest.getPageToken()) && internalEqualityCheck(getAllowedParentalControls(), getRecentTrackActivityRequest.getAllowedParentalControls()) && internalEqualityCheck(getActivityTypeFilters(), getRecentTrackActivityRequest.getActivityTypeFilters()) && internalEqualityCheck(getMusicRequestIdentityContextToken(), getRecentTrackActivityRequest.getMusicRequestIdentityContextToken());
    }

    public List<String> getActivityTypeFilters() {
        return this.activityTypeFilters;
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public List<String> getFeatures() {
        return this.features;
    }

    public String getLanguageLocale() {
        return this.languageLocale;
    }

    public MusicRequestIdentityContext getMusicRequestIdentityContext() {
        return this.musicRequestIdentityContext;
    }

    public String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    @Override // com.amazon.nimblymusicservice.BaseRequest, com.amazon.nimblymusicservice.MarketplaceIdRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMusicRequestIdentityContext(), getLanguageLocale(), getFeatures(), getPageToken(), getAllowedParentalControls(), getActivityTypeFilters(), getMusicRequestIdentityContextToken());
    }

    public void setActivityTypeFilters(List<String> list) {
        this.activityTypeFilters = list;
    }

    public void setFeatures(List<String> list) {
        this.features = list;
    }

    public void setMusicRequestIdentityContextToken(String str) {
        this.musicRequestIdentityContextToken = str;
    }

    public void setPageToken(String str) {
        this.pageToken = str;
    }
}
