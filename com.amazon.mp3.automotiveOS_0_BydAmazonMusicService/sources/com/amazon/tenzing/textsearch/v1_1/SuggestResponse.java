package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SuggestResponse implements Comparable<SuggestResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SuggestResponse");
    private List<Suggestion> suggestions;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SuggestResponse suggestResponse) {
        List<Suggestion> suggestions;
        List<Suggestion> suggestions2;
        if (suggestResponse == null) {
            return -1;
        }
        if (suggestResponse != this && (suggestions = getSuggestions()) != (suggestions2 = suggestResponse.getSuggestions())) {
            if (suggestions == null) {
                return -1;
            }
            if (suggestions2 == null) {
                return 1;
            }
            if (suggestions instanceof Comparable) {
                int iCompareTo = ((Comparable) suggestions).compareTo(suggestions2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!suggestions.equals(suggestions2)) {
                int iHashCode = suggestions.hashCode();
                int iHashCode2 = suggestions2.hashCode();
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
        if (obj instanceof SuggestResponse) {
            return internalEqualityCheck(getSuggestions(), ((SuggestResponse) obj).getSuggestions());
        }
        return false;
    }

    public List<Suggestion> getSuggestions() {
        return this.suggestions;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getSuggestions());
    }

    public void setSuggestions(List<Suggestion> list) {
        this.suggestions = list;
    }
}
