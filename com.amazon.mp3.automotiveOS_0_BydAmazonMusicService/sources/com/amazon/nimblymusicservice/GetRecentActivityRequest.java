package com.amazon.nimblymusicservice;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContext;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetRecentActivityRequest extends BaseRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.GetRecentActivityRequest");
    private List<String> activityTypeFilters;
    private AllowedParentalControls allowedParentalControls;
    private Date endTime;
    private List<String> entityTypeFilters;
    private List<String> features;
    private String languageLocale;
    private Integer maxResultSize;
    private MusicRequestIdentityContext musicRequestIdentityContext;
    private String musicRequestIdentityContextToken;
    private String pageToken;
    private Date startTime;

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
        if (!(cirrusBaseRequestV2 instanceof GetRecentActivityRequest)) {
            return 1;
        }
        GetRecentActivityRequest getRecentActivityRequest = (GetRecentActivityRequest) cirrusBaseRequestV2;
        Integer maxResultSize = getMaxResultSize();
        Integer maxResultSize2 = getRecentActivityRequest.getMaxResultSize();
        if (maxResultSize != maxResultSize2) {
            if (maxResultSize == null) {
                return -1;
            }
            if (maxResultSize2 == null) {
                return 1;
            }
            int iCompareTo = maxResultSize.compareTo(maxResultSize2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Date endTime = getEndTime();
        Date endTime2 = getRecentActivityRequest.getEndTime();
        if (endTime != endTime2) {
            if (endTime == null) {
                return -1;
            }
            if (endTime2 == null) {
                return 1;
            }
            int iCompareTo2 = endTime.compareTo(endTime2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Date startTime = getStartTime();
        Date startTime2 = getRecentActivityRequest.getStartTime();
        if (startTime != startTime2) {
            if (startTime == null) {
                return -1;
            }
            if (startTime2 == null) {
                return 1;
            }
            int iCompareTo3 = startTime.compareTo(startTime2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<String> activityTypeFilters = getActivityTypeFilters();
        List<String> activityTypeFilters2 = getRecentActivityRequest.getActivityTypeFilters();
        if (activityTypeFilters != activityTypeFilters2) {
            if (activityTypeFilters == null) {
                return -1;
            }
            if (activityTypeFilters2 == null) {
                return 1;
            }
            if (activityTypeFilters instanceof Comparable) {
                int iCompareTo4 = ((Comparable) activityTypeFilters).compareTo(activityTypeFilters2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!activityTypeFilters.equals(activityTypeFilters2)) {
                int iHashCode = activityTypeFilters.hashCode();
                int iHashCode2 = activityTypeFilters2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<String> features = getFeatures();
        List<String> features2 = getRecentActivityRequest.getFeatures();
        if (features != features2) {
            if (features == null) {
                return -1;
            }
            if (features2 == null) {
                return 1;
            }
            if (features instanceof Comparable) {
                int iCompareTo5 = ((Comparable) features).compareTo(features2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
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
        AllowedParentalControls allowedParentalControls = getAllowedParentalControls();
        AllowedParentalControls allowedParentalControls2 = getRecentActivityRequest.getAllowedParentalControls();
        if (allowedParentalControls != allowedParentalControls2) {
            if (allowedParentalControls == null) {
                return -1;
            }
            if (allowedParentalControls2 == null) {
                return 1;
            }
            int iCompareTo6 = allowedParentalControls.compareTo(allowedParentalControls2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String musicRequestIdentityContextToken = getMusicRequestIdentityContextToken();
        String musicRequestIdentityContextToken2 = getRecentActivityRequest.getMusicRequestIdentityContextToken();
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
        String languageLocale = getLanguageLocale();
        String languageLocale2 = getRecentActivityRequest.getLanguageLocale();
        if (languageLocale != languageLocale2) {
            if (languageLocale == null) {
                return -1;
            }
            if (languageLocale2 == null) {
                return 1;
            }
            int iCompareTo8 = languageLocale.compareTo(languageLocale2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        MusicRequestIdentityContext musicRequestIdentityContext = getMusicRequestIdentityContext();
        MusicRequestIdentityContext musicRequestIdentityContext2 = getRecentActivityRequest.getMusicRequestIdentityContext();
        if (musicRequestIdentityContext != musicRequestIdentityContext2) {
            if (musicRequestIdentityContext == null) {
                return -1;
            }
            if (musicRequestIdentityContext2 == null) {
                return 1;
            }
            int iCompareTo9 = musicRequestIdentityContext.compareTo(musicRequestIdentityContext2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        List<String> entityTypeFilters = getEntityTypeFilters();
        List<String> entityTypeFilters2 = getRecentActivityRequest.getEntityTypeFilters();
        if (entityTypeFilters != entityTypeFilters2) {
            if (entityTypeFilters == null) {
                return -1;
            }
            if (entityTypeFilters2 == null) {
                return 1;
            }
            if (entityTypeFilters instanceof Comparable) {
                int iCompareTo10 = ((Comparable) entityTypeFilters).compareTo(entityTypeFilters2);
                if (iCompareTo10 != 0) {
                    return iCompareTo10;
                }
            } else if (!entityTypeFilters.equals(entityTypeFilters2)) {
                int iHashCode5 = entityTypeFilters.hashCode();
                int iHashCode6 = entityTypeFilters2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String pageToken = getPageToken();
        String pageToken2 = getRecentActivityRequest.getPageToken();
        if (pageToken != pageToken2) {
            if (pageToken == null) {
                return -1;
            }
            if (pageToken2 == null) {
                return 1;
            }
            int iCompareTo11 = pageToken.compareTo(pageToken2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.nimblymusicservice.BaseRequest, com.amazon.nimblymusicservice.MarketplaceIdRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof GetRecentActivityRequest)) {
            return false;
        }
        GetRecentActivityRequest getRecentActivityRequest = (GetRecentActivityRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getMaxResultSize(), getRecentActivityRequest.getMaxResultSize()) && internalEqualityCheck(getEndTime(), getRecentActivityRequest.getEndTime()) && internalEqualityCheck(getStartTime(), getRecentActivityRequest.getStartTime()) && internalEqualityCheck(getActivityTypeFilters(), getRecentActivityRequest.getActivityTypeFilters()) && internalEqualityCheck(getFeatures(), getRecentActivityRequest.getFeatures()) && internalEqualityCheck(getAllowedParentalControls(), getRecentActivityRequest.getAllowedParentalControls()) && internalEqualityCheck(getMusicRequestIdentityContextToken(), getRecentActivityRequest.getMusicRequestIdentityContextToken()) && internalEqualityCheck(getLanguageLocale(), getRecentActivityRequest.getLanguageLocale()) && internalEqualityCheck(getMusicRequestIdentityContext(), getRecentActivityRequest.getMusicRequestIdentityContext()) && internalEqualityCheck(getEntityTypeFilters(), getRecentActivityRequest.getEntityTypeFilters()) && internalEqualityCheck(getPageToken(), getRecentActivityRequest.getPageToken());
    }

    public List<String> getActivityTypeFilters() {
        return this.activityTypeFilters;
    }

    public AllowedParentalControls getAllowedParentalControls() {
        return this.allowedParentalControls;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public List<String> getEntityTypeFilters() {
        return this.entityTypeFilters;
    }

    public List<String> getFeatures() {
        return this.features;
    }

    public String getLanguageLocale() {
        return this.languageLocale;
    }

    public Integer getMaxResultSize() {
        return this.maxResultSize;
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

    public Date getStartTime() {
        return this.startTime;
    }

    @Override // com.amazon.nimblymusicservice.BaseRequest, com.amazon.nimblymusicservice.MarketplaceIdRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMaxResultSize(), getEndTime(), getStartTime(), getActivityTypeFilters(), getFeatures(), getAllowedParentalControls(), getMusicRequestIdentityContextToken(), getLanguageLocale(), getMusicRequestIdentityContext(), getEntityTypeFilters(), getPageToken());
    }

    public void setActivityTypeFilters(List<String> list) {
        this.activityTypeFilters = list;
    }

    public void setEntityTypeFilters(List<String> list) {
        this.entityTypeFilters = list;
    }

    public void setFeatures(List<String> list) {
        this.features = list;
    }

    public void setMaxResultSize(Integer num) {
        this.maxResultSize = num;
    }

    public void setMusicRequestIdentityContextToken(String str) {
        this.musicRequestIdentityContextToken = str;
    }
}
