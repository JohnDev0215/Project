package com.amazon.nimblymusicservice;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class GetRecentTrackActivityResponse implements Comparable<GetRecentTrackActivityResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.GetRecentTrackActivityResponse");
    private Map<String, RecentTrackActivityWithToken> recentActivityMap;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetRecentTrackActivityResponse getRecentTrackActivityResponse) {
        Map<String, RecentTrackActivityWithToken> recentActivityMap;
        Map<String, RecentTrackActivityWithToken> recentActivityMap2;
        if (getRecentTrackActivityResponse == null) {
            return -1;
        }
        if (getRecentTrackActivityResponse != this && (recentActivityMap = getRecentActivityMap()) != (recentActivityMap2 = getRecentTrackActivityResponse.getRecentActivityMap())) {
            if (recentActivityMap == null) {
                return -1;
            }
            if (recentActivityMap2 == null) {
                return 1;
            }
            if (recentActivityMap instanceof Comparable) {
                int iCompareTo = ((Comparable) recentActivityMap).compareTo(recentActivityMap2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!recentActivityMap.equals(recentActivityMap2)) {
                int iHashCode = recentActivityMap.hashCode();
                int iHashCode2 = recentActivityMap2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GetRecentTrackActivityResponse) {
            return internalEqualityCheck(getRecentActivityMap(), ((GetRecentTrackActivityResponse) obj).getRecentActivityMap());
        }
        return false;
    }

    public Map<String, RecentTrackActivityWithToken> getRecentActivityMap() {
        return this.recentActivityMap;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getRecentActivityMap());
    }

    public void setRecentActivityMap(Map<String, RecentTrackActivityWithToken> map) {
        this.recentActivityMap = map;
    }
}
