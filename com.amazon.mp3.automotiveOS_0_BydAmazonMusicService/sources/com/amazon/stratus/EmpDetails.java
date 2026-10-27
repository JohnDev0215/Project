package com.amazon.stratus;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class EmpDetails implements Comparable<EmpDetails> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.EmpDetails");
    private String provider;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated EmpDetails empDetails) {
        String provider;
        String provider2;
        if (empDetails == null) {
            return -1;
        }
        if (empDetails != this && (provider = getProvider()) != (provider2 = empDetails.getProvider())) {
            if (provider == null) {
                return -1;
            }
            if (provider2 == null) {
                return 1;
            }
            int iCompareTo = provider.compareTo(provider2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof EmpDetails) {
            return internalEqualityCheck(getProvider(), ((EmpDetails) obj).getProvider());
        }
        return false;
    }

    public String getProvider() {
        return this.provider;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getProvider());
    }

    public void setProvider(String str) {
        this.provider = str;
    }
}
