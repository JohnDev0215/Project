package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ParentalControls implements Comparable<ParentalControls> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.ParentalControls");
    private boolean hasExplicitLanguage;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ParentalControls parentalControls) {
        if (parentalControls == null) {
            return -1;
        }
        if (parentalControls == this) {
            return 0;
        }
        if (isHasExplicitLanguage() || !parentalControls.isHasExplicitLanguage()) {
            return (!isHasExplicitLanguage() || parentalControls.isHasExplicitLanguage()) ? 0 : 1;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ParentalControls) {
            return internalEqualityCheck(Boolean.valueOf(isHasExplicitLanguage()), Boolean.valueOf(((ParentalControls) obj).isHasExplicitLanguage()));
        }
        return false;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Boolean.valueOf(isHasExplicitLanguage()));
    }

    public boolean isHasExplicitLanguage() {
        return this.hasExplicitLanguage;
    }

    public void setHasExplicitLanguage(boolean z) {
        this.hasExplicitLanguage = z;
    }
}
