package com.amazon.tenzing.textsearch.v1_1;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
@Deprecated
public class Eligibility implements Comparable<Eligibility> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.tenzing.textsearch.v1_1.Eligibility");
    private String tier;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Eligibility eligibility) {
        String tier;
        String tier2;
        if (eligibility == null) {
            return -1;
        }
        if (eligibility != this && (tier = getTier()) != (tier2 = eligibility.getTier())) {
            if (tier == null) {
                return -1;
            }
            if (tier2 == null) {
                return 1;
            }
            int iCompareTo = tier.compareTo(tier2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Eligibility) {
            return internalEqualityCheck(getTier(), ((Eligibility) obj).getTier());
        }
        return false;
    }

    @Deprecated
    public String getTier() {
        return this.tier;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTier());
    }
}
