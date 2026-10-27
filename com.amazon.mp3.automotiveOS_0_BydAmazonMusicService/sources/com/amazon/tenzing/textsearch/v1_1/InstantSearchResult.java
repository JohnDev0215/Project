package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class InstantSearchResult implements Comparable<InstantSearchResult> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.InstantSearchResult");
    private String blockRef;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated InstantSearchResult instantSearchResult) {
        String blockRef;
        String blockRef2;
        if (instantSearchResult == null) {
            return -1;
        }
        if (instantSearchResult != this && (blockRef = getBlockRef()) != (blockRef2 = instantSearchResult.getBlockRef())) {
            if (blockRef == null) {
                return -1;
            }
            if (blockRef2 == null) {
                return 1;
            }
            int iCompareTo = blockRef.compareTo(blockRef2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof InstantSearchResult) {
            return internalEqualityCheck(getBlockRef(), ((InstantSearchResult) obj).getBlockRef());
        }
        return false;
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getBlockRef());
    }

    public void setBlockRef(String str) {
        this.blockRef = str;
    }
}
