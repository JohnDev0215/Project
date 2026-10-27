package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class InstantSearchResponse implements Comparable<InstantSearchResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.InstantSearchResponse");
    private String blockRef;
    private String queryId;
    private List<InstantSearchResult> results;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated InstantSearchResponse instantSearchResponse) {
        if (instantSearchResponse == null) {
            return -1;
        }
        if (instantSearchResponse == this) {
            return 0;
        }
        String queryId = getQueryId();
        String queryId2 = instantSearchResponse.getQueryId();
        if (queryId != queryId2) {
            if (queryId == null) {
                return -1;
            }
            if (queryId2 == null) {
                return 1;
            }
            int iCompareTo = queryId.compareTo(queryId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<InstantSearchResult> results = getResults();
        List<InstantSearchResult> results2 = instantSearchResponse.getResults();
        if (results != results2) {
            if (results == null) {
                return -1;
            }
            if (results2 == null) {
                return 1;
            }
            if (results instanceof Comparable) {
                int iCompareTo2 = ((Comparable) results).compareTo(results2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!results.equals(results2)) {
                int iHashCode = results.hashCode();
                int iHashCode2 = results2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String blockRef = getBlockRef();
        String blockRef2 = instantSearchResponse.getBlockRef();
        if (blockRef != blockRef2) {
            if (blockRef == null) {
                return -1;
            }
            if (blockRef2 == null) {
                return 1;
            }
            int iCompareTo3 = blockRef.compareTo(blockRef2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof InstantSearchResponse)) {
            return false;
        }
        InstantSearchResponse instantSearchResponse = (InstantSearchResponse) obj;
        return internalEqualityCheck(getQueryId(), instantSearchResponse.getQueryId()) && internalEqualityCheck(getResults(), instantSearchResponse.getResults()) && internalEqualityCheck(getBlockRef(), instantSearchResponse.getBlockRef());
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public String getQueryId() {
        return this.queryId;
    }

    public List<InstantSearchResult> getResults() {
        return this.results;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getQueryId(), getResults(), getBlockRef());
    }

    public void setBlockRef(String str) {
        this.blockRef = str;
    }

    public void setQueryId(String str) {
        this.queryId = str;
    }

    public void setResults(List<InstantSearchResult> list) {
        this.results = list;
    }
}
