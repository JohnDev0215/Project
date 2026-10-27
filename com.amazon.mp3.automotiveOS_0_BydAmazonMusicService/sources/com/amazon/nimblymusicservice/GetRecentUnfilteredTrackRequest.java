package com.amazon.nimblymusicservice;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContext;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class GetRecentUnfilteredTrackRequest extends BaseRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.GetRecentUnfilteredTrackRequest");
    private Date endTime;
    private List<String> features;
    private boolean filterHiddenTracks;
    private boolean filterMoreThanThirtySeconds;
    private boolean flatten;
    private int maxResults;
    private MusicRequestIdentityContext musicRequestIdentityContext;
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
        if (!(cirrusBaseRequestV2 instanceof GetRecentUnfilteredTrackRequest)) {
            return 1;
        }
        GetRecentUnfilteredTrackRequest getRecentUnfilteredTrackRequest = (GetRecentUnfilteredTrackRequest) cirrusBaseRequestV2;
        MusicRequestIdentityContext musicRequestIdentityContext = getMusicRequestIdentityContext();
        MusicRequestIdentityContext musicRequestIdentityContext2 = getRecentUnfilteredTrackRequest.getMusicRequestIdentityContext();
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
        if (!isFlatten() && getRecentUnfilteredTrackRequest.isFlatten()) {
            return -1;
        }
        if (isFlatten() && !getRecentUnfilteredTrackRequest.isFlatten()) {
            return 1;
        }
        List<String> features = getFeatures();
        List<String> features2 = getRecentUnfilteredTrackRequest.getFeatures();
        if (features != features2) {
            if (features == null) {
                return -1;
            }
            if (features2 == null) {
                return 1;
            }
            if (features instanceof Comparable) {
                int iCompareTo2 = ((Comparable) features).compareTo(features2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
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
        Date startTime = getStartTime();
        Date startTime2 = getRecentUnfilteredTrackRequest.getStartTime();
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
        String pageToken = getPageToken();
        String pageToken2 = getRecentUnfilteredTrackRequest.getPageToken();
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
        Date endTime = getEndTime();
        Date endTime2 = getRecentUnfilteredTrackRequest.getEndTime();
        if (endTime != endTime2) {
            if (endTime == null) {
                return -1;
            }
            if (endTime2 == null) {
                return 1;
            }
            int iCompareTo5 = endTime.compareTo(endTime2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        if (getMaxResults() < getRecentUnfilteredTrackRequest.getMaxResults()) {
            return -1;
        }
        if (getMaxResults() > getRecentUnfilteredTrackRequest.getMaxResults()) {
            return 1;
        }
        if (!isFilterMoreThanThirtySeconds() && getRecentUnfilteredTrackRequest.isFilterMoreThanThirtySeconds()) {
            return -1;
        }
        if (isFilterMoreThanThirtySeconds() && !getRecentUnfilteredTrackRequest.isFilterMoreThanThirtySeconds()) {
            return 1;
        }
        if (!isFilterHiddenTracks() && getRecentUnfilteredTrackRequest.isFilterHiddenTracks()) {
            return -1;
        }
        if (!isFilterHiddenTracks() || getRecentUnfilteredTrackRequest.isFilterHiddenTracks()) {
            return super.compareTo(cirrusBaseRequestV2);
        }
        return 1;
    }

    @Override // com.amazon.nimblymusicservice.BaseRequest, com.amazon.nimblymusicservice.MarketplaceIdRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof GetRecentUnfilteredTrackRequest)) {
            return false;
        }
        GetRecentUnfilteredTrackRequest getRecentUnfilteredTrackRequest = (GetRecentUnfilteredTrackRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getMusicRequestIdentityContext(), getRecentUnfilteredTrackRequest.getMusicRequestIdentityContext()) && internalEqualityCheck(Boolean.valueOf(isFlatten()), Boolean.valueOf(getRecentUnfilteredTrackRequest.isFlatten())) && internalEqualityCheck(getFeatures(), getRecentUnfilteredTrackRequest.getFeatures()) && internalEqualityCheck(getStartTime(), getRecentUnfilteredTrackRequest.getStartTime()) && internalEqualityCheck(getPageToken(), getRecentUnfilteredTrackRequest.getPageToken()) && internalEqualityCheck(getEndTime(), getRecentUnfilteredTrackRequest.getEndTime()) && internalEqualityCheck(Integer.valueOf(getMaxResults()), Integer.valueOf(getRecentUnfilteredTrackRequest.getMaxResults())) && internalEqualityCheck(Boolean.valueOf(isFilterMoreThanThirtySeconds()), Boolean.valueOf(getRecentUnfilteredTrackRequest.isFilterMoreThanThirtySeconds())) && internalEqualityCheck(Boolean.valueOf(isFilterHiddenTracks()), Boolean.valueOf(getRecentUnfilteredTrackRequest.isFilterHiddenTracks()));
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public List<String> getFeatures() {
        return this.features;
    }

    public int getMaxResults() {
        return this.maxResults;
    }

    public MusicRequestIdentityContext getMusicRequestIdentityContext() {
        return this.musicRequestIdentityContext;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    @Override // com.amazon.nimblymusicservice.BaseRequest, com.amazon.nimblymusicservice.MarketplaceIdRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMusicRequestIdentityContext(), Boolean.valueOf(isFlatten()), getFeatures(), getStartTime(), getPageToken(), getEndTime(), Integer.valueOf(getMaxResults()), Boolean.valueOf(isFilterMoreThanThirtySeconds()), Boolean.valueOf(isFilterHiddenTracks()));
    }

    public boolean isFilterHiddenTracks() {
        return this.filterHiddenTracks;
    }

    public boolean isFilterMoreThanThirtySeconds() {
        return this.filterMoreThanThirtySeconds;
    }

    public boolean isFlatten() {
        return this.flatten;
    }

    public void setEndTime(Date date) {
        this.endTime = date;
    }

    public void setFilterMoreThanThirtySeconds(boolean z) {
        this.filterMoreThanThirtySeconds = z;
    }

    public void setFlatten(boolean z) {
        this.flatten = z;
    }

    public void setMaxResults(int i) {
        this.maxResults = i;
    }

    public void setPageToken(String str) {
        this.pageToken = str;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }
}
