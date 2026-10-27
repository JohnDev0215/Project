package com.amazon.nimblymusicservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ParentalControls implements Comparable<ParentalControls> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.ParentalControls");
    private String hasExplicitLanguage;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ParentalControls parentalControls) {
        String hasExplicitLanguage;
        String hasExplicitLanguage2;
        if (parentalControls == null) {
            return -1;
        }
        if (parentalControls != this && (hasExplicitLanguage = getHasExplicitLanguage()) != (hasExplicitLanguage2 = parentalControls.getHasExplicitLanguage())) {
            if (hasExplicitLanguage == null) {
                return -1;
            }
            if (hasExplicitLanguage2 == null) {
                return 1;
            }
            int iCompareTo = hasExplicitLanguage.compareTo(hasExplicitLanguage2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ParentalControls) {
            return internalEqualityCheck(getHasExplicitLanguage(), ((ParentalControls) obj).getHasExplicitLanguage());
        }
        return false;
    }

    public String getHasExplicitLanguage() {
        return this.hasExplicitLanguage;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getHasExplicitLanguage());
    }

    public void setHasExplicitLanguage(String str) {
        this.hasExplicitLanguage = str;
    }
}
