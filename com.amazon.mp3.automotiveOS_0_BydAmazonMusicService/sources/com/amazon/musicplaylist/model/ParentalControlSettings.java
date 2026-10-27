package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ParentalControlSettings implements Comparable<ParentalControlSettings> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.ParentalControlSettings");
    private boolean explicitLanguageAllowed;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ParentalControlSettings parentalControlSettings) {
        if (parentalControlSettings == null) {
            return -1;
        }
        if (parentalControlSettings == this) {
            return 0;
        }
        if (isExplicitLanguageAllowed() || !parentalControlSettings.isExplicitLanguageAllowed()) {
            return (!isExplicitLanguageAllowed() || parentalControlSettings.isExplicitLanguageAllowed()) ? 0 : 1;
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ParentalControlSettings) {
            return internalEqualityCheck(Boolean.valueOf(isExplicitLanguageAllowed()), Boolean.valueOf(((ParentalControlSettings) obj).isExplicitLanguageAllowed()));
        }
        return false;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Boolean.valueOf(isExplicitLanguageAllowed()));
    }

    public boolean isExplicitLanguageAllowed() {
        return this.explicitLanguageAllowed;
    }
}
