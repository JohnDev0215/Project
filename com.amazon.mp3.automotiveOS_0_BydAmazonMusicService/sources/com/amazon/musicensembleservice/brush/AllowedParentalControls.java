package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class AllowedParentalControls implements Comparable<AllowedParentalControls> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.AllowedParentalControls");
    private Boolean hasExplicitLanguage;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AllowedParentalControls allowedParentalControls) {
        Boolean boolIsHasExplicitLanguage;
        Boolean boolIsHasExplicitLanguage2;
        if (allowedParentalControls == null) {
            return -1;
        }
        if (allowedParentalControls != this && (boolIsHasExplicitLanguage = isHasExplicitLanguage()) != (boolIsHasExplicitLanguage2 = allowedParentalControls.isHasExplicitLanguage())) {
            if (boolIsHasExplicitLanguage == null) {
                return -1;
            }
            if (boolIsHasExplicitLanguage2 == null) {
                return 1;
            }
            int iCompareTo = boolIsHasExplicitLanguage.compareTo(boolIsHasExplicitLanguage2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AllowedParentalControls) {
            return internalEqualityCheck(isHasExplicitLanguage(), ((AllowedParentalControls) obj).isHasExplicitLanguage());
        }
        return false;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isHasExplicitLanguage());
    }

    public Boolean isHasExplicitLanguage() {
        return this.hasExplicitLanguage;
    }

    public void setHasExplicitLanguage(Boolean bool) {
        this.hasExplicitLanguage = bool;
    }
}
