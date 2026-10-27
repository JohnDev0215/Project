package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class InstantTextResult extends InstantSearchResult {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.InstantTextResult");
    private String id;
    private String term;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.tenzing.textsearch.v1_1.InstantSearchResult, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated InstantSearchResult instantSearchResult) {
        if (instantSearchResult == null) {
            return -1;
        }
        if (instantSearchResult == this) {
            return 0;
        }
        if (!(instantSearchResult instanceof InstantTextResult)) {
            return 1;
        }
        InstantTextResult instantTextResult = (InstantTextResult) instantSearchResult;
        String term = getTerm();
        String term2 = instantTextResult.getTerm();
        if (term != term2) {
            if (term == null) {
                return -1;
            }
            if (term2 == null) {
                return 1;
            }
            int iCompareTo = term.compareTo(term2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String id = getId();
        String id2 = instantTextResult.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo2 = id.compareTo(id2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(instantSearchResult);
    }

    @Override // com.amazon.tenzing.textsearch.v1_1.InstantSearchResult
    public boolean equals(Object obj) {
        if (!(obj instanceof InstantTextResult)) {
            return false;
        }
        InstantTextResult instantTextResult = (InstantTextResult) obj;
        return super.equals(obj) && internalEqualityCheck(getTerm(), instantTextResult.getTerm()) && internalEqualityCheck(getId(), instantTextResult.getId());
    }

    public String getId() {
        return this.id;
    }

    public String getTerm() {
        return this.term;
    }

    @Override // com.amazon.tenzing.textsearch.v1_1.InstantSearchResult
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTerm(), getId());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTerm(String str) {
        this.term = str;
    }
}
