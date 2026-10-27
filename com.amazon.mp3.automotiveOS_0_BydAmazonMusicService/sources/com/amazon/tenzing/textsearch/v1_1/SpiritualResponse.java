package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class SpiritualResponse implements Comparable<SpiritualResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SpiritualResponse");
    private String category;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SpiritualResponse spiritualResponse) {
        String category;
        String category2;
        if (spiritualResponse == null) {
            return -1;
        }
        if (spiritualResponse != this && (category = getCategory()) != (category2 = spiritualResponse.getCategory())) {
            if (category == null) {
                return -1;
            }
            if (category2 == null) {
                return 1;
            }
            int iCompareTo = category.compareTo(category2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof SpiritualResponse) {
            return internalEqualityCheck(getCategory(), ((SpiritualResponse) obj).getCategory());
        }
        return false;
    }

    public String getCategory() {
        return this.category;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCategory());
    }

    public void setCategory(String str) {
        this.category = str;
    }
}
