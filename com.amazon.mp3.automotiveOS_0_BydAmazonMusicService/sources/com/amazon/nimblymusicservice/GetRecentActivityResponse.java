package com.amazon.nimblymusicservice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class GetRecentActivityResponse implements Comparable<GetRecentActivityResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.GetRecentActivityResponse");
    private Map<String, List<RecentActivityData>> recentActivityMap;
    private Map<String, String> recentActivityPaginationTokenMap;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetRecentActivityResponse getRecentActivityResponse) {
        if (getRecentActivityResponse == null) {
            return -1;
        }
        if (getRecentActivityResponse == this) {
            return 0;
        }
        Map<String, List<RecentActivityData>> recentActivityMap = getRecentActivityMap();
        Map<String, List<RecentActivityData>> recentActivityMap2 = getRecentActivityResponse.getRecentActivityMap();
        if (recentActivityMap != recentActivityMap2) {
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
        Map<String, String> recentActivityPaginationTokenMap = getRecentActivityPaginationTokenMap();
        Map<String, String> recentActivityPaginationTokenMap2 = getRecentActivityResponse.getRecentActivityPaginationTokenMap();
        if (recentActivityPaginationTokenMap != recentActivityPaginationTokenMap2) {
            if (recentActivityPaginationTokenMap == null) {
                return -1;
            }
            if (recentActivityPaginationTokenMap2 == null) {
                return 1;
            }
            if (recentActivityPaginationTokenMap instanceof Comparable) {
                int iCompareTo2 = ((Comparable) recentActivityPaginationTokenMap).compareTo(recentActivityPaginationTokenMap2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!recentActivityPaginationTokenMap.equals(recentActivityPaginationTokenMap2)) {
                int iHashCode3 = recentActivityPaginationTokenMap.hashCode();
                int iHashCode4 = recentActivityPaginationTokenMap2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetRecentActivityResponse)) {
            return false;
        }
        GetRecentActivityResponse getRecentActivityResponse = (GetRecentActivityResponse) obj;
        return internalEqualityCheck(getRecentActivityMap(), getRecentActivityResponse.getRecentActivityMap()) && internalEqualityCheck(getRecentActivityPaginationTokenMap(), getRecentActivityResponse.getRecentActivityPaginationTokenMap());
    }

    public Map<String, List<RecentActivityData>> getRecentActivityMap() {
        return this.recentActivityMap;
    }

    public Map<String, String> getRecentActivityPaginationTokenMap() {
        return this.recentActivityPaginationTokenMap;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getRecentActivityMap(), getRecentActivityPaginationTokenMap());
    }

    public void setRecentActivityMap(Map<String, List<RecentActivityData>> map) {
        this.recentActivityMap = map;
    }

    public void setRecentActivityPaginationTokenMap(Map<String, String> map) {
        this.recentActivityPaginationTokenMap = map;
    }
}
