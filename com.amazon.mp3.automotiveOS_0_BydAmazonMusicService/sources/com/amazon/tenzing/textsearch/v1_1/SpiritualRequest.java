package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class SpiritualRequest implements Comparable<SpiritualRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.SpiritualRequest");
    private Boolean enableFiltering;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SpiritualRequest spiritualRequest) {
        Boolean boolIsEnableFiltering;
        Boolean boolIsEnableFiltering2;
        if (spiritualRequest == null) {
            return -1;
        }
        if (spiritualRequest != this && (boolIsEnableFiltering = isEnableFiltering()) != (boolIsEnableFiltering2 = spiritualRequest.isEnableFiltering())) {
            if (boolIsEnableFiltering == null) {
                return -1;
            }
            if (boolIsEnableFiltering2 == null) {
                return 1;
            }
            int iCompareTo = boolIsEnableFiltering.compareTo(boolIsEnableFiltering2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof SpiritualRequest) {
            return internalEqualityCheck(isEnableFiltering(), ((SpiritualRequest) obj).isEnableFiltering());
        }
        return false;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isEnableFiltering());
    }

    public Boolean isEnableFiltering() {
        return this.enableFiltering;
    }
}
