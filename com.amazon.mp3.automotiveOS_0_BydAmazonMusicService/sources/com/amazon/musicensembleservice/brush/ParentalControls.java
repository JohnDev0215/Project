package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class ParentalControls implements Comparable<ParentalControls> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.ParentalControls");
    private Boolean hasExplicitLanguage;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ParentalControls parentalControls) {
        Boolean boolIsHasExplicitLanguage;
        Boolean boolIsHasExplicitLanguage2;
        if (parentalControls == null) {
            return -1;
        }
        if (parentalControls != this && (boolIsHasExplicitLanguage = isHasExplicitLanguage()) != (boolIsHasExplicitLanguage2 = parentalControls.isHasExplicitLanguage())) {
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
        if (obj instanceof ParentalControls) {
            return internalEqualityCheck(isHasExplicitLanguage(), ((ParentalControls) obj).isHasExplicitLanguage());
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
