package com.amazon.nimblymusicservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class GetRecentUnfilteredTrackResponse implements Comparable<GetRecentUnfilteredTrackResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.GetRecentUnfilteredTrackResponse");
    private String nextToken;
    private TrackEventList trackEventList;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetRecentUnfilteredTrackResponse getRecentUnfilteredTrackResponse) {
        if (getRecentUnfilteredTrackResponse == null) {
            return -1;
        }
        if (getRecentUnfilteredTrackResponse == this) {
            return 0;
        }
        String nextToken = getNextToken();
        String nextToken2 = getRecentUnfilteredTrackResponse.getNextToken();
        if (nextToken != nextToken2) {
            if (nextToken == null) {
                return -1;
            }
            if (nextToken2 == null) {
                return 1;
            }
            int iCompareTo = nextToken.compareTo(nextToken2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        TrackEventList trackEventList = getTrackEventList();
        TrackEventList trackEventList2 = getRecentUnfilteredTrackResponse.getTrackEventList();
        if (trackEventList != trackEventList2) {
            if (trackEventList == null) {
                return -1;
            }
            if (trackEventList2 == null) {
                return 1;
            }
            int iCompareTo2 = trackEventList.compareTo(trackEventList2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetRecentUnfilteredTrackResponse)) {
            return false;
        }
        GetRecentUnfilteredTrackResponse getRecentUnfilteredTrackResponse = (GetRecentUnfilteredTrackResponse) obj;
        return internalEqualityCheck(getNextToken(), getRecentUnfilteredTrackResponse.getNextToken()) && internalEqualityCheck(getTrackEventList(), getRecentUnfilteredTrackResponse.getTrackEventList());
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public TrackEventList getTrackEventList() {
        return this.trackEventList;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getNextToken(), getTrackEventList());
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setTrackEventList(TrackEventList trackEventList) {
        this.trackEventList = trackEventList;
    }
}
