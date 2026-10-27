package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class Highlight implements Comparable<Highlight> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.Highlight");
    private String field;
    private List<String> fragments;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Highlight highlight) {
        if (highlight == null) {
            return -1;
        }
        if (highlight == this) {
            return 0;
        }
        List<String> fragments = getFragments();
        List<String> fragments2 = highlight.getFragments();
        if (fragments != fragments2) {
            if (fragments == null) {
                return -1;
            }
            if (fragments2 == null) {
                return 1;
            }
            if (fragments instanceof Comparable) {
                int iCompareTo = ((Comparable) fragments).compareTo(fragments2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!fragments.equals(fragments2)) {
                int iHashCode = fragments.hashCode();
                int iHashCode2 = fragments2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String field = getField();
        String field2 = highlight.getField();
        if (field != field2) {
            if (field == null) {
                return -1;
            }
            if (field2 == null) {
                return 1;
            }
            int iCompareTo2 = field.compareTo(field2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Highlight)) {
            return false;
        }
        Highlight highlight = (Highlight) obj;
        return internalEqualityCheck(getFragments(), highlight.getFragments()) && internalEqualityCheck(getField(), highlight.getField());
    }

    public String getField() {
        return this.field;
    }

    public List<String> getFragments() {
        return this.fragments;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getFragments(), getField());
    }

    public void setField(String str) {
        this.field = str;
    }

    public void setFragments(List<String> list) {
        this.fragments = list;
    }
}
