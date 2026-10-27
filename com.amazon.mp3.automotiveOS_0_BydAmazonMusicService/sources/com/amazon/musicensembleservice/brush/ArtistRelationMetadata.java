package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class ArtistRelationMetadata implements Comparable<ArtistRelationMetadata> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.ArtistRelationMetadata");
    private Boolean isSuggested;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ArtistRelationMetadata artistRelationMetadata) {
        Boolean boolIsIsSuggested;
        Boolean boolIsIsSuggested2;
        if (artistRelationMetadata == null) {
            return -1;
        }
        if (artistRelationMetadata != this && (boolIsIsSuggested = isIsSuggested()) != (boolIsIsSuggested2 = artistRelationMetadata.isIsSuggested())) {
            if (boolIsIsSuggested == null) {
                return -1;
            }
            if (boolIsIsSuggested2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIsSuggested.compareTo(boolIsIsSuggested2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ArtistRelationMetadata) {
            return internalEqualityCheck(isIsSuggested(), ((ArtistRelationMetadata) obj).isIsSuggested());
        }
        return false;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isIsSuggested());
    }

    public Boolean isIsSuggested() {
        return this.isSuggested;
    }

    public void setIsSuggested(Boolean bool) {
        this.isSuggested = bool;
    }
}
