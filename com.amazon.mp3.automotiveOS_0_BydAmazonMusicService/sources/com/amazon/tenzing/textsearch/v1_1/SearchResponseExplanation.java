package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class SearchResponseExplanation implements Comparable<SearchResponseExplanation> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SearchResponseExplanation");
    private Map<String, String> details;
    private Map<String, String> rankerExplanations;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SearchResponseExplanation searchResponseExplanation) {
        if (searchResponseExplanation == null) {
            return -1;
        }
        if (searchResponseExplanation == this) {
            return 0;
        }
        Map<String, String> details = getDetails();
        Map<String, String> details2 = searchResponseExplanation.getDetails();
        if (details != details2) {
            if (details == null) {
                return -1;
            }
            if (details2 == null) {
                return 1;
            }
            if (details instanceof Comparable) {
                int iCompareTo = ((Comparable) details).compareTo(details2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!details.equals(details2)) {
                int iHashCode = details.hashCode();
                int iHashCode2 = details2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Map<String, String> rankerExplanations = getRankerExplanations();
        Map<String, String> rankerExplanations2 = searchResponseExplanation.getRankerExplanations();
        if (rankerExplanations != rankerExplanations2) {
            if (rankerExplanations == null) {
                return -1;
            }
            if (rankerExplanations2 == null) {
                return 1;
            }
            if (rankerExplanations instanceof Comparable) {
                int iCompareTo2 = ((Comparable) rankerExplanations).compareTo(rankerExplanations2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!rankerExplanations.equals(rankerExplanations2)) {
                int iHashCode3 = rankerExplanations.hashCode();
                int iHashCode4 = rankerExplanations2.hashCode();
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
        if (!(obj instanceof SearchResponseExplanation)) {
            return false;
        }
        SearchResponseExplanation searchResponseExplanation = (SearchResponseExplanation) obj;
        return internalEqualityCheck(getDetails(), searchResponseExplanation.getDetails()) && internalEqualityCheck(getRankerExplanations(), searchResponseExplanation.getRankerExplanations());
    }

    public Map<String, String> getDetails() {
        return this.details;
    }

    public Map<String, String> getRankerExplanations() {
        return this.rankerExplanations;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDetails(), getRankerExplanations());
    }

    public void setDetails(Map<String, String> map) {
        this.details = map;
    }

    public void setRankerExplanations(Map<String, String> map) {
        this.rankerExplanations = map;
    }
}
