package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class GetLibraryCountsResponse implements Comparable<GetLibraryCountsResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.GetLibraryCountsResponse");
    private Map<String, Integer> counts;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetLibraryCountsResponse getLibraryCountsResponse) {
        Map<String, Integer> counts;
        Map<String, Integer> counts2;
        if (getLibraryCountsResponse == null) {
            return -1;
        }
        if (getLibraryCountsResponse != this && (counts = getCounts()) != (counts2 = getLibraryCountsResponse.getCounts())) {
            if (counts == null) {
                return -1;
            }
            if (counts2 == null) {
                return 1;
            }
            if (counts instanceof Comparable) {
                int iCompareTo = ((Comparable) counts).compareTo(counts2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!counts.equals(counts2)) {
                int iHashCode = counts.hashCode();
                int iHashCode2 = counts2.hashCode();
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
        if (obj instanceof GetLibraryCountsResponse) {
            return internalEqualityCheck(getCounts(), ((GetLibraryCountsResponse) obj).getCounts());
        }
        return false;
    }

    public Map<String, Integer> getCounts() {
        return this.counts;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCounts());
    }

    public void setCounts(Map<String, Integer> map) {
        this.counts = map;
    }
}
