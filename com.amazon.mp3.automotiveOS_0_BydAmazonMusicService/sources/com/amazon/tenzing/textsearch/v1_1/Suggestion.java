package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class Suggestion implements Comparable<Suggestion> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.Suggestion");
    private String term;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Suggestion suggestion) {
        String term;
        String term2;
        if (suggestion == null) {
            return -1;
        }
        if (suggestion != this && (term = getTerm()) != (term2 = suggestion.getTerm())) {
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
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Suggestion) {
            return internalEqualityCheck(getTerm(), ((Suggestion) obj).getTerm());
        }
        return false;
    }

    public String getTerm() {
        return this.term;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTerm());
    }

    public void setTerm(String str) {
        this.term = str;
    }
}
